package org.l3monkeys.gameloader;

public interface GameInterface {
    public String getGameTitle();
    public String getGameDescrption();
    public String getGameThumbnailPath();
    public String getGamePreviewPath();

    public void launchGame();
}