package org.l3monkeys.collection.components.base;

import com.jfoenix.controls.JFXButton;

import org.l3monkeys.collection.Collection;
import org.l3monkeys.collection.handlers.GlobalActionHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class WindowNavigation extends HBox {

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Variables
    /**---------------------------------------------------------------------------------------------------------------------*/
    @FXML private JFXButton btn_minimize;
    @FXML private JFXButton btn_maximize;
    @FXML private JFXButton btn_close;

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Constructors
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Default constructor which gets executed by JavaFX
    public WindowNavigation() {
        try {
            // Load the custom made Component
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WindowNavigation.fxml")); // FXMLLoader.load(getClass().getResource("WindowNavigation.fxml"));
            // Set this component as root for the children inside of the FXML file
            loader.setRoot(this);
            // This class is also the Controller-Object for the custom made Component
            loader.setController(this);
            // Finally load the component design
            loader.load();
        } catch (Exception exc) {
            exc.printStackTrace();
            try {
                Collection.LOGGER.warn("Component '"
                        + WindowNavigation.class.getSimpleName()
                        + "' cannot be loaded!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML void initialize() {
        // Bind the typical window actions (MINIMIZE, MAXIMIZE, CLOSE) to our buttons
        this.btn_minimize.addEventHandler(MouseEvent.MOUSE_CLICKED, GlobalActionHandler::minimize);
        this.btn_maximize.addEventHandler(MouseEvent.MOUSE_CLICKED, GlobalActionHandler::maximize);
        this.btn_close.addEventHandler(MouseEvent.MOUSE_CLICKED, GlobalActionHandler::close);
    }

}