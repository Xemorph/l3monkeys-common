package org.l3monkeys.games.dummygame2;

import org.l3monkeys.gameloader.GameInterface;
import org.l3monkeys.games.dummygame2.MainWindow;

import javafx.scene.image.Image;

public class DummyGameInterface implements GameInterface {

    @Override
    public String getGameTitle() {
        return "Dummy Game 2";
    }

    @Override
    public String getGameDescrption() {
        return "This is another dummy game.";
    }

    @Override
    public Image getGameThumbnailImage() {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("13Monkeys_Wallpaper.jpg"));
    }

    @Override
    public Image getGamePreviewImage() {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("13Monkeys_Wallpaper.jpg"));
    }

    @Override
    public void launchGame() {
        System.out.println("Launching Dummy Game 2");

        new MainWindow();
    }

}