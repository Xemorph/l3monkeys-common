package org.l3monkeys.collection.components.carousel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jfoenix.controls.JFXButton;

import org.l3monkeys.collection.components.carousel.pagination.GamePaginationItem;
import org.l3monkeys.collection.handlers.GlobalActionHandler;
import org.l3monkeys.gameloader.GameInterface;
import org.l3monkeys.gameloader.GameLoader;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class HeroCarousel {

    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    // FXML Components
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    @FXML
    private JFXButton btn_library;
    // HeroCarousel holder
    @FXML
    private HBox heroCarouselHolder;
    // HeroCarousel controls (<- & ->)
    @FXML
    private Polygon arrow_left;
    @FXML
    private Polygon arrow_right;
    // Visualization
    @FXML
    private Rectangle lgLR; // LinearGradient -> From LEFT to RIGHT
    @FXML
    private Rectangle lgTB; // LinearGradient -> From TOP to BOTTOM
    @FXML
    private ImageView backgroundPreview;
    // Play Button
    @FXML
    private Button playButton;

    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    // Public Static Variables
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    public static IntegerProperty activeGameCard = new SimpleIntegerProperty(0);
    public static List<GamePaginationItem> gamePagination = new ArrayList<GamePaginationItem>();
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    // Constant
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    private static final int STEP = 1;

    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    // Constructor
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    public void HeroCarousel() {
        // Do nothing here!
    }

    @FXML
    void initialize() {

        // Debug
        System.out.println("[Debug] HeroCarousel.activeGameCard: " + HeroCarousel.activeGameCard.get());

        // Add EventHandlers
        this.btn_library.addEventHandler(MouseEvent.MOUSE_CLICKED, GlobalActionHandler::backToLibrary);

        // Add Visualization to the background image
        this.lgLR.setFill(LinearGradient.valueOf(
                "linear-gradient(to right, #18191c 0%,#18191c 4%,rgba(24,25,28, 0.9) 7%,rgba(54,57,63,0) 40%,rgba(54,57,63,0) 60%, #18191c)"));
        this.lgTB.setFill(LinearGradient.valueOf(
                "linear-gradient(to top, #18191c 0%,  #18191c 2%,rgba(24,25,28, 0.7) 12%,rgba(54,57,63,0) 25%)"));

        // Fill up the List with available Games
        loadGamesForPagination();

        activeGameCard.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (oldValue != newValue) {
                    gamePagination.stream().forEach(e -> e.defaults());
                    update();
                }
            }
        });

        // Add Pagination navigation - Slide to the left / right
        this.arrow_left.addEventHandler(MouseEvent.MOUSE_CLICKED, this::slideLeft);
        this.arrow_right.addEventHandler(MouseEvent.MOUSE_CLICKED, this::slideRight);

        this.playButton.setOnAction((event) -> {
            GamePaginationItem selectedGame = gamePagination.get((HeroCarousel.activeGameCard.get() - 1));
            launchGame(selectedGame);
        });
    }

    private void loadGamesForPagination() {
        Set<GameInterface> gameInterfaces = GameLoader.getGameInterfaces();
        
        for (GameInterface gameInterface : gameInterfaces) {
            GamePaginationItem paginationItem = new GamePaginationItem(gameInterface);
            gamePagination.add(paginationItem);
            heroCarouselHolder.getChildren().add(paginationItem);
        }
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    // Methods
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    public void update() {
        // Get the 'PaginationItem' with the reference to the active GameCard
        if (HeroCarousel.activeGameCard.get() > 0 && !gamePagination.isEmpty()) {
            GamePaginationItem activePagination = gamePagination.get((HeroCarousel.activeGameCard.get() - 1));
            activePagination.setActive();

            GameInterface gameInterface = activePagination.getGameInterface();
            Image gamePreviewImage = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(gameInterface.getGamePreviewPath()));
            backgroundPreview.setImage(gamePreviewImage);
        }
    }

    private void slideLeft(MouseEvent event) {
        // Check if we already at the most left element
        if (HeroCarousel.activeGameCard.get() == 1)
            return;
        // Go to the next GameCard - DIRECTION: LEFT
        HeroCarousel.activeGameCard.set(HeroCarousel.activeGameCard.get() - STEP);

    }

    private void slideRight(MouseEvent event) {
        // Check if we already at the most right element
        if (HeroCarousel.activeGameCard.get() == HeroCarousel.gamePagination.size())
            return;
        // Go to the next GameCard - DIRECTION: RIGHT
        HeroCarousel.activeGameCard.set(HeroCarousel.activeGameCard.get() + STEP);
    }

    private void launchGame(GamePaginationItem selectedGame) {
        selectedGame.getGameInterface().launchGame();
    }

}