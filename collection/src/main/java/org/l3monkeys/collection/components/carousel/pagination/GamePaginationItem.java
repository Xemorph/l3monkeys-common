package org.l3monkeys.collection.components.carousel.pagination;

import org.l3monkeys.collection.components.carousel.pagination.PaginationItem;
import org.l3monkeys.gameloader.GameInterface;

public class GamePaginationItem extends PaginationItem {

    private GameInterface gameInterface;

    public GamePaginationItem(GameInterface gameInterface) {
        super(gameInterface.getGameThumbnailImage());
        this.gameInterface = gameInterface;
    }

	public GameInterface getGameInterface() {
        return gameInterface;
	}

}
