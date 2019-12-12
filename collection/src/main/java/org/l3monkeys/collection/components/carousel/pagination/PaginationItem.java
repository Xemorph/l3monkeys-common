package org.l3monkeys.collection.components.carousel.pagination;

import org.l3monkeys.collection.Collection;

import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PaginationItem extends StackPane {

    /** --------------------------------------------------------------------------------------------------------------------- */
    //  Public Static Variables
    /** --------------------------------------------------------------------------------------------------------------------- */
    private int refGameCardId;
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Variables
    /**---------------------------------------------------------------------------------------------------------------------*/
    private static int _instanceCounter = 0;

    private Image thumbnailImage;

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Constructors
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Default constructor which gets executed by JavaFX
    public PaginationItem(Image thumbnailImage) {
        this.thumbnailImage = thumbnailImage;

        try {
            // Load the custom made Component
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaginationItem.fxml")); // FXMLLoader.load(getClass().getResource("PaginationItem.fxml"));
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
                        + PaginationItem.class.getSimpleName()
                        + "' cannot be loaded!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.refGameCardId = _instanceCounter + 1;
        _instanceCounter++;
    }

    @FXML void initialize() {
        // Set default visualization
        this.setMaxWidth(80.0);
        this.setMaxHeight(40.0);
        this.setPrefWidth(80.0);
        this.setPrefHeight(40.0);

        // Clip GameCardImage to the PaginationItem
        Rectangle shape = new Rectangle(80.0, 40.0);
        //System.out.println("[Debug] Path: " + Thread.currentThread().getContextClassLoader().getResource("testBG.png").toExternalForm());
        shape.setFill(new ImagePattern(thumbnailImage));
        Pane innerPane = new Pane(shape);
        //Clip
        final Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(3.0);
        outputClip.setArcHeight(3.0);
        innerPane.setClip(outputClip);
        innerPane.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
        this.getChildren().add(innerPane);

        // Execute defaults
        this.defaults();
    }

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Methods
    /**---------------------------------------------------------------------------------------------------------------------*/
    // Returns the id of a GameCard to which this object references
    public int getRefGameCardId() {
        return this.refGameCardId;
    }

    // Adds a blue border to show the selected & active GameCard
    public void setActive() {
        this.setBorder(new Border(new BorderStroke(Color.web(PaginationColor.NETFLIX_DARK.getValue()), BorderStrokeStyle.SOLID, new CornerRadii(3.0), new BorderWidths(2.0))));
    }

    // Adds a default border to the PaginationItem
    public void defaults() {
        this.setBorder(new Border(new BorderStroke(Color.web(PaginationColor.DEFAULT.getValue()), BorderStrokeStyle.SOLID, new CornerRadii(3.0), new BorderWidths(2.0))));
    }

}