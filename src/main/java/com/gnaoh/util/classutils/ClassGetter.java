package com.gnaoh.util.classutils;

import java.util.ArrayList;
import java.util.Collection;

import org.clapper.util.classutil.AndClassFilter;
import org.clapper.util.classutil.ClassFilter;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;
import org.clapper.util.classutil.InterfaceOnlyClassFilter;
import org.clapper.util.classutil.NotClassFilter;
import org.clapper.util.classutil.SubclassClassFilter;

public class ClassGetter {
    public static ClassGetter INSTANCE = new ClassGetter();

    ClassFinder finder = null;

    ClassGetter() {
        finder = new ClassFinder();
        finder.addClassPath();
    }

    public Collection<ClassInfo> getSubClasses(Class<?> baseClassOrInterface) {
        ClassFilter filter = new AndClassFilter(
            new SubclassClassFilter(baseClassOrInterface),
            new NotClassFilter(new InterfaceOnlyClassFilter())
        );

        Collection<ClassInfo> classes = new ArrayList<ClassInfo>();

        finder.findClasses(classes, filter);
        return classes;
    }

    public Collection<ClassInfo> getAllClasses() {
        Collection<ClassInfo> classes = new ArrayList<ClassInfo>();
        finder.findClasses(classes);

        return classes;
    }
}
