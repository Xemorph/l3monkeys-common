package org.l3monkeys.collection.components;

import org.l3monkeys.collection.Collection;
import org.l3monkeys.collection.components.card.GameCard;
import org.l3monkeys.collection.components.carousel.HeroCarousel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Dashboard {
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Variables
    /**---------------------------------------------------------------------------------------------------------------------*/
    @FXML private ImageView bgImage;
    @FXML private GameCard btn_game_1;
    @FXML private GameCard btn_game_2;
    @FXML private GameCard btn_game_3;
    @FXML private GameCard btn_game_4;
    @FXML private GameCard btn_game_5;

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Constructors
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Default constructor which gets executed by JavaFX
    public void Dashboard() {
        // Do nothing here!
    }

    @FXML void initialize() {
        try {
            this.bgImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("13Monkeys_Wallpaper.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Adding routing to the next scene 'Game'
        this.btn_game_1.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goToGameLauncher);
        this.btn_game_2.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goToGameLauncher);
        this.btn_game_3.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goToGameLauncher);
        this.btn_game_4.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goToGameLauncher);
        this.btn_game_5.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goToGameLauncher);
    }

    /** --------------------------------------------------------------------------------------------------------------------- */
    //  Private / protected methods
    /** --------------------------------------------------------------------------------------------------------------------- */
    private void goToGameLauncher(MouseEvent event) {
        // Get the 'id' property of the clicked GameCard
        IntegerProperty id = new SimpleIntegerProperty();
        id.setValue(new Integer(((GameCard)event.getSource()).getId().split("_")[2]));

        // Tell HeroCarousel which GameLauncher should be started
        HeroCarousel.activeGameCard.set(id.get());
        // Debug
        System.out.println("[Debug] Active GameCard: " + HeroCarousel.activeGameCard.get());

        Collection.sceneController().activate("game");
    }

}