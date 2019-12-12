package org.l3monkeys.collection.components.carousel;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import org.l3monkeys.collection.components.carousel.pagination.PaginationItem;
import org.l3monkeys.collection.handlers.GlobalActionHandler;
import org.l3monkeys.reflect.logging.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class HeroCarousel {

    private static Logger LOGGER = new Logger(HeroCarousel.class);

    /**---------------------------------------------------------------------------------------------------------------------*/
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
    @FXML private Rectangle lgLR; // LinearGradient -> From LEFT to RIGHT
    @FXML private Rectangle lgTB; // LinearGradient -> From TOP to BOTTOM
    @FXML private ImageView backgroundPreview;
    // Play button
    @FXML private JFXButton btn_play;

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Public Static Variables
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    public static IntegerProperty activeGameCard = new SimpleIntegerProperty();
    public static List<PaginationItem> pagination = new ArrayList<PaginationItem>();
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
        // Move `btn_play` to the front
        this.btn_play.toFront();
        // Add Game Previe Image -> GPI
        this.backgroundPreview.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("game_stud_rouge.png")));
        // Add Visualization to the background image
        this.lgLR.setFill(LinearGradient.valueOf(
                "linear-gradient(to right, #18191c 0%,#18191c 4%,rgba(24,25,28, 0.9) 7%,rgba(54,57,63,0) 40%,rgba(54,57,63,0) 60%, #18191c)"));
        this.lgTB.setFill(LinearGradient.valueOf(
                "linear-gradient(to top, #18191c 0%,  #18191c 2%,rgba(24,25,28, 0.7) 12%,rgba(54,57,63,0) 25%)"));

        // Fillup the List with available 'PaginationItem' objects
        for (Node e : this.heroCarouselHolder.getChildrenUnmodifiable()) {
            if (e instanceof PaginationItem) {
                pagination.add(((PaginationItem) e));
                // TODO replace with dynamic Game loading
            }
        }

        activeGameCard.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (oldValue != newValue) {
                    pagination.stream().forEach(e -> e.defaults());
                    HeroCarousel.update();
                }
            }
        });

        ((StackPane)this.backgroundPreview.getParent()).heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableVal, Number oldVal, Number newVal) {
                LOGGER.info("Stackpane: " + oldVal + " -> " +newVal);
                LOGGER.info("ImageView: " + new Image(this.getClass().getClassLoader().getResourceAsStream("game_stud_rouge.png")).getHeight());
                double transY = newVal.doubleValue() - new Image(this.getClass().getClassLoader().getResourceAsStream("game_stud_rouge.png")).getHeight();
                LOGGER.info("TransY: " + transY);
                lgLR.setHeight(newVal.doubleValue());
                lgTB.setHeight(newVal.doubleValue());
                lgTB.setHeight(lgTB.getHeight() - transY);
                lgTB.setTranslateY(-(transY / 2));
            }
        });

        // Add Pagination navigation - Slide to the left / right
        this.arrow_left.addEventHandler(MouseEvent.MOUSE_CLICKED, this::slideLeft);
        this.arrow_right.addEventHandler(MouseEvent.MOUSE_CLICKED, this::slideRight);

        this.btn_play.setOnAction((event) -> {
        });
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    // Methods
    /**
     * ---------------------------------------------------------------------------------------------------------------------
     */
    public static void update() {
        // Get the 'PaginationItem' with the reference to the active GameCard
        if (HeroCarousel.activeGameCard.get() > 0 && !pagination.isEmpty())
            pagination.get((HeroCarousel.activeGameCard.get() - 1)).setActive();
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
        if (HeroCarousel.activeGameCard.get() == HeroCarousel.pagination.size())
            return;
        // Go to the next GameCard - DIRECTION: RIGHT
        HeroCarousel.activeGameCard.set(HeroCarousel.activeGameCard.get() + STEP);
    }
}