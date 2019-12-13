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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class HeroCarousel {

    /*----------------------------------------------------------------------*\
                            Private Data Items
    \*----------------------------------------------------------------------*/
    @FXML private JFXButton btn_library;
    // HeroCarousel holder
    @FXML private HBox heroCarouselHolder;
    // HeroCarousel controls (<- & ->)
    @FXML private Polygon arrow_left;
    @FXML private Polygon arrow_right;
    // Visualization
    @FXML private Rectangle lgLR; // LinearGradient -> From LEFT to RIGHT
    @FXML private Rectangle lgTB; // LinearGradient -> From TOP to BOTTOM
    @FXML private ImageView backgroundPreview;
    // Play Button
    @FXML private Button btn_play;
    // Field for `getGameTitle`
    @FXML private Label lbl_gameTitle;
    /*----------------------------------------------------------------------*\
                            Public Data Items
    \*----------------------------------------------------------------------*/
    public static IntegerProperty activeGameCard = new SimpleIntegerProperty(0);
    public static List<GamePaginationItem> gamePagination = new ArrayList<GamePaginationItem>();
    /*----------------------------------------------------------------------*\
                            Private Data Items
    \*----------------------------------------------------------------------*/
    private static final int STEP = 1;
    /*----------------------------------------------------------------------*\
                                Constructor
    \*----------------------------------------------------------------------*/
    public HeroCarousel() { /* Do nothing here! */ }
    /*----------------------------------------------------------------------*\
                              Private Methods
    \*----------------------------------------------------------------------*/
    @FXML void initialize() {

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
            @Override public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (oldValue != newValue) {
                    gamePagination.stream().forEach(e -> e.defaults());
                    update();
                }
            }
        });

        ((StackPane) this.backgroundPreview.getParent()).heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableVal, Number oldVal, Number newVal) {
                lgLR.setHeight(newVal.doubleValue());
                lgTB.setHeight(newVal.doubleValue());
            }
        });

        // Add Pagination navigation - Slide to the left / right
        this.arrow_left.addEventHandler(MouseEvent.MOUSE_CLICKED, this::slideLeft);
        this.arrow_right.addEventHandler(MouseEvent.MOUSE_CLICKED, this::slideRight);

        this.btn_play.setOnAction((event) -> {
            GamePaginationItem selectedGame = gamePagination.get((HeroCarousel.activeGameCard.get() - 1));
            launchGame(selectedGame);
        });
    }

    private void loadGamesForPagination() {
        Set<GameInterface> gameInterfaces = GameLoader.getGameInterfaces();
        
        for (GameInterface gameInterface : gameInterfaces) {
            GamePaginationItem paginationItem = new GamePaginationItem(gameInterface);

            paginationItem.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    activeGameCard.set(gamePagination.indexOf(paginationItem) + 1);
                }
            });

            gamePagination.add(paginationItem);
            heroCarouselHolder.getChildren().add(paginationItem);
        }
    }
    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/
    public void update() {
        // Get the 'PaginationItem' with the reference to the active GameCard
        if (HeroCarousel.activeGameCard.get() > 0 && !gamePagination.isEmpty()) {
            GamePaginationItem activePagination = gamePagination.get((HeroCarousel.activeGameCard.get() - 1));
            activePagination.setActive();

            GameInterface gameInterface = activePagination.getGameInterface();
            Image gamePreviewImage = gameInterface.getGamePreviewImage();
            backgroundPreview.setImage(gamePreviewImage);
            String gameTitle = gameInterface.getGameTitle();
            lbl_gameTitle.setText(gameTitle);
        }
    }
    /*----------------------------------------------------------------------*\
                              Private Methods
    \*----------------------------------------------------------------------*/
    private void slideLeft(MouseEvent event) {
        // Check if we already at the most left element
        if (HeroCarousel.activeGameCard.get() == 1)
            return;
        // Go to the next GameCard - DIRECTION: LEFT
        activeGameCard.set(HeroCarousel.activeGameCard.get() - STEP);

    }

    private void slideRight(MouseEvent event) {
        // Check if we already at the most right element
        if (HeroCarousel.activeGameCard.get() == HeroCarousel.gamePagination.size())
            return;
        // Go to the next GameCard - DIRECTION: RIGHT
        activeGameCard.set(HeroCarousel.activeGameCard.get() + STEP);
    }

    private void launchGame(GamePaginationItem selectedGame) {
        selectedGame.getGameInterface().launchGame();
    }

}