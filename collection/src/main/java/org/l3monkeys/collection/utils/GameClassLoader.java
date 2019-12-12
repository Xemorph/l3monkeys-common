package org.l3monkeys.collection.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.l3monkeys.reflect.util.AbstractClassFilter;
import org.l3monkeys.reflect.util.AndClassFilter;
import org.l3monkeys.reflect.util.ClassFilter;
import org.l3monkeys.reflect.util.ClassFinder;
import org.l3monkeys.reflect.util.ClassInfo;
import org.l3monkeys.reflect.util.InterfaceOnlyClassFilter;
import org.l3monkeys.reflect.util.NotClassFilter;
import org.l3monkeys.reflect.util.SubclassClassFilter;

public class GameClassLoader {

    public static void classLoader() {
        ClassFinder finder = new ClassFinder(); finder.addClassPath();
        ClassFilter filter = 
                        new AndClassFilter(
                            new NotClassFilter(new InterfaceOnlyClassFilter()),
                            new SubclassClassFilter(IGamePrerequisites.class),
                            new NotClassFilter(new AbstractClassFilter())
                        );

        Collection<ClassInfo> foundClasses = new ArrayList<ClassInfo>();
        finder.findClasses(foundClasses, filter);
  
        for (ClassInfo classInfo : foundClasses)
            System.out.println ("Found " + classInfo.getClassName());
    }
}