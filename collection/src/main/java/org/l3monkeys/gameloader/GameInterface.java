package org.l3monkeys.gameloader;

import javafx.scene.image.Image;

public interface GameInterface {
    public String getGameTitle();
    public String getGameDescrption();
    public Image getGamePreviewImage();
	public Image getGameThumbnailImage();

    public void launchGame();
}