package com.example.bl.transformer;

import com.example.domain.AbstractEntity;
import com.example.rest.dto.AbstractEntityDto;

public abstract class AbstractEntityTransformer<Source extends AbstractEntity, Target extends AbstractEntityDto> implements BiTransformer<Source, Target> {

    @Override
    public void copyToTarget(Source source, Target target) {
        target.setId(source.getId());
    }

    @Override
    public void copyToSource(Target target, Source source) {
        target.setId(source.getId());
    }
}
