package org.l3monkeys.games.dummygame;

import org.l3monkeys.gameloader.GameInterface;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DummyGame extends Stage implements GameInterface {

    @Override
    public String getGameTitle() {
        return "Dummy Game";
    }

    @Override
    public String getGameDescrption() {
        return "This is a dummy game.";
    }

    @Override
    public Image getGameThumbnailImage() {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("testBG.png"));
    }

    @Override
    public Image getGamePreviewImage() {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("testBG.png"));
    }

    @Override
    public void launchGame() {
        System.out.println("Launching Dummy Game");
    }

}