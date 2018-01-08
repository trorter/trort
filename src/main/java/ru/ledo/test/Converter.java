package ru.ledo.test;

@FunctionalInterface
public interface Converter<F, T> {
    T conver(F from);

    //T parse(F from);
}
