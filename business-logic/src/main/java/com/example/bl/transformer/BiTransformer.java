package com.example.bl.transformer;

public interface BiTransformer<Source, Target> {

    Target createTarget(Source source);

    Source createSource(Target target);

    void copyToTarget(Source source, Target target);

    void copyToSource(Target target, Source source);
}
