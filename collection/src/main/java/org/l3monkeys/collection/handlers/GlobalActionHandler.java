package org.l3monkeys.collection.handlers;

import com.jfoenix.controls.JFXButton;

import org.l3monkeys.collection.Collection;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GlobalActionHandler {

    /** --------------------------------------------------------------------------------------------------------------------- */
	//  Public static methods
	/** --------------------------------------------------------------------------------------------------------------------- */
    public static void minimize(MouseEvent event) {
        ((Stage)((JFXButton)event.getSource()).getScene().getWindow()).toBack();
    }

    public static void maximize(MouseEvent event) {
        // Do nothing, maximizing isn't allowed in our application
    }

    public static void close(MouseEvent event) {
        ((Stage)((JFXButton)event.getSource()).getScene().getWindow()).close();
    }

    public static void backToLibrary(MouseEvent event) {
        Collection.sceneController().activate("dashboard");
    }

}