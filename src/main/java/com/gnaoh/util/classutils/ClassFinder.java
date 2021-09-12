package com.gnaoh.util.classutils;

import java.util.Set;

import com.gnaoh.Config;

import org.reflections.Reflections;

public class ClassFinder<T> {
    Reflections reflections;
    Class<T> clazz;

    public ClassFinder(Class<T> clazz) {
        reflections = new Reflections(Config.INSTANCE.get("PACKAGE_NAME"));
        this.clazz = clazz;
    }

    public Set<Class<? extends T>> getSubClasses() {
        Set<Class<? extends T>> subClasses = reflections.getSubTypesOf(clazz);
        subClasses.removeIf(clazz -> clazz.isInterface());
        
        return subClasses;
    }
}
