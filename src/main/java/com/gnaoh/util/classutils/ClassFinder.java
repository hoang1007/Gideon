package com.gnaoh.util.classutils;

import java.util.Set;

import org.reflections.Reflections;

public class ClassFinder<T> {
    final String packageName = "com.gnaoh";
    Reflections reflections;
    Class<T> clazz;

    public ClassFinder(Class<T> clazz) {
        reflections = new Reflections(packageName);
        this.clazz = clazz;
    }

    public Set<Class<? extends T>> getSubClasses() {
        Set<Class<? extends T>> subClasses = reflections.getSubTypesOf(clazz);
        subClasses.removeIf(clazz -> clazz.isInterface());
        
        return subClasses;
    }
}
