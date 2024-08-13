package com.example.account_management_service;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.account_management_service.model.Account;
import com.example.account_management_service.repository.AccountRepository;
import com.example.account_management_service.service.AccountServiceImpl;

@SpringBootTest
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setEmail("test@example.com");
        account.setPassword("password");

        when(accountRepository.save(account)).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testGetAccountById() {
        UUID id = UUID.randomUUID();
        Account account = new Account();
        account.setId(id);

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(id);

        assertNotNull(foundAccount);
        assertEquals(id, foundAccount.getId());
    }
    @Test
    public void testGetAccountById_NotFound() {
        UUID id = UUID.randomUUID();

        // Simulate account not found
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        // Verify that an exception is thrown
        Exception exception = assertThrows(RuntimeException.class, () -> {
            accountService.getAccountById(id);
        });

        // Check the exception message
        assertEquals("Account not found", exception.getMessage());

        // Verify that findById was called once
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateAccount() {
        UUID id = UUID.randomUUID();
        Account existingAccount = new Account();
        existingAccount.setId(id);
        existingAccount.setEmail("old@example.com");
        existingAccount.setPassword("oldpassword");

        Account updatedAccount = new Account();
        updatedAccount.setId(id);
        updatedAccount.setEmail("new@example.com");
        updatedAccount.setPassword("newpassword");

        when(accountRepository.findById(id)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(updatedAccount);

        Account result = accountService.updateAccount(id, updatedAccount);

        assertNotNull(result);
        assertEquals("new@example.com", result.getEmail());
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(existingAccount);
    }

    @Test
    public void testUpdateAccount_NotFound() {
        UUID id = UUID.randomUUID();
        Account updatedAccount = new Account();
        updatedAccount.setId(id);
        updatedAccount.setEmail("new@example.com");
        updatedAccount.setPassword("newpassword");

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            accountService.updateAccount(id, updatedAccount);
        });

        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void testMarkAccountForDeletion() {
        UUID id = UUID.randomUUID();
        Account account = new Account();
        account.setId(id);
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
