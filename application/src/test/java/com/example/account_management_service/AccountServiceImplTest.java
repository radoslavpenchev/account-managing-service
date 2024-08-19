package com.example.account_management_service;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.bl.account.AccountService;
import com.example.domain.Account;
import com.example.persistence.AccountRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setEmail("test@example.com");
        account.setPassword("password");

        when(accountRepository.save(account)).thenReturn(account);

//        Account createdAccount = accountService.createAccount(account); FIXME

//        assertNotNull(createdAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testGetAccountById() {
        UUID id = UUID.randomUUID();
        Account account = new Account(id);

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

//        Account foundAccount = accountService.getAccountById(id); FIXME

//        assertNotNull(foundAccount);
//        assertEquals(id, foundAccount.getId());
    }
    @Test
    public void testGetAccountById_NotFound() {
        UUID id = UUID.randomUUID();

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            accountService.getAccountById(id);
        });

        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateAccount() {
        UUID id = UUID.randomUUID();
        Account existingAccount = new Account(id);
        existingAccount.setEmail("old@example.com");
        existingAccount.setPassword("oldpassword");

        Account updatedAccount = new Account(id);
        updatedAccount.setEmail("new@example.com");
        updatedAccount.setPassword("newpassword");

        when(accountRepository.findById(id)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(updatedAccount);

//        Account result = accountService.updateAccount(id, updatedAccount); FIXME

//        assertNotNull(result);
//        assertEquals("new@example.com", result.getEmail());
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(existingAccount);
    }

    @Test
    public void testUpdateAccount_NotFound() {
        UUID id = UUID.randomUUID();
        Account updatedAccount = new Account(id);
        updatedAccount.setEmail("new@example.com");
        updatedAccount.setPassword("newpassword");

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

//        Exception exception = assertThrows(RuntimeException.class, () -> accountService.updateAccount(id, updatedAccount)); FIXME

//        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void testMarkAccountForDeletion() {
        UUID id = UUID.randomUUID();
        Account account = new Account(id);
        account.setMarkedForDeletion(false);

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        accountService.markAccountForDeletion(id);

        assertTrue(account.isMarkedForDeletion());
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testMarkAccountForDeletion_NotFound() {
        UUID id = UUID.randomUUID();

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            accountService.markAccountForDeletion(id);
        });

        assertEquals("Account not found", exception.getMessage());
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, never()).save(any(Account.class));
    }
}
