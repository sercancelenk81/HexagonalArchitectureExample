package com.ej.common;

public interface UseCaseHandler <E,T extends UseCase>{
    E handle(T UseCase);
}
