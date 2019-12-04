package org.l3monkeys.collection.components.card;

import org.l3monkeys.collection.Collection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GameCard extends Pane {
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Variables
    /**---------------------------------------------------------------------------------------------------------------------*/
    @FXML private ImageView gamePreview;

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Constructors
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Default constructor which gets executed by JavaFX
    public GameCard() {
        try {
            // Load the custom made Component
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameCard.fxml")); // FXMLLoader.load(getClass().getResource("GameCard.fxml"));
            // Set this component as root for the children inside of the FXML file
            loader.setRoot(this);
            // This class is also the Controller-Object for the custom made Component
            loader.setController(this);
            // Finally load the component design
            loader.load();

            // Set EventHandlers
            this.setEventHandler(MouseEvent.MOUSE_ENTERED, this::handleMouseOverState);
            this.setEventHandler(MouseEvent.MOUSE_EXITED, this::handleMouseLeaveState);
        } catch (Exception exc) {
            exc.printStackTrace();
            try {
                Collection.LOGGER.warn("Component '"
                        + GameCard.class.getSimpleName()
                        + "' cannot be loaded!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** --------------------------------------------------------------------------------------------------------------------- */
    //  Auto-injected event methods
    /** --------------------------------------------------------------------------------------------------------------------- */	
    private void handleMouseOverState(MouseEvent event) {
        ((GameCard)event.getSource()).setStyle(String.format("-fx-background-color: linear-gradient(from 25%% 25%% to 100%% 100%%, %s, %s)", GameCardColor.NETFLIX.getStopColor(), GameCardColor.NETFLIX.getStartColor()));
    }
    
    private void handleMouseLeaveState(MouseEvent event) {
        ((GameCard)event.getSource()).setStyle("-fx-background-color: #000000");
    }

}