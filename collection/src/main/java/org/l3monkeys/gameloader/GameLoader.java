package org.l3monkeys.gameloader;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class GameLoader {
    public static Set<GameInterface> getGameInterfaces() {
        //Reflections reflections = new Reflections("org.l3monkeys");
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                                                        .setUrls(ClasspathHelper.forPackage("org.l3monkeys"))
                                                        .addUrls(ClasspathHelper.forPackage("com.nystroem"))
                                                 );
        Set<Class<? extends GameInterface>> gameClasses = reflections.getSubTypesOf(GameInterface.class);

        Set<GameInterface> gameInterfaces = new HashSet<GameInterface>();
        for (Class<? extends GameInterface> gameClass : gameClasses) {
            try {
                GameInterface gameInterface = gameClass.getDeclaredConstructor().newInstance();
                gameInterfaces.add(gameInterface);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[Debug] Found " + gameInterfaces.size() + " games!");

        return gameInterfaces;
    }
}