package org.l3monkeys.collection;

import java.io.IOException;
import java.net.URL;
import java.util.function.Function;

import org.l3monkeys.collection.components.carousel.HeroCarousel;
import org.l3monkeys.reflect.logging.Logger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Collection extends Application {

    /**---------------------------------------------------------------------------------------------------------------------*/
    // Variables
    /**---------------------------------------------------------------------------------------------------------------------*/
    private static SceneController controller;

    // Public accessible Logger
    public static final Logger LOGGER = new Logger(Collection.class);

    // Define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    @Override public void start(Stage stage) throws Exception {

        LOGGER.info("Dashboard.fxml >> " + getClass().getResource("components/Dashboard.fxml").getPath());

        try {
            Parent root = FXMLLoader.load(getClass().getResource("components/Dashboard.fxml"));
            // You can use underdecorated or transparent.
            stage.initStyle(StageStyle.TRANSPARENT);

            // Make application window draggable again
            draggableWindow(stage, root);

            // Finally, create our RootScene
            Scene scene = new Scene(root);
            // Create a new instanceof the SceneController with the above created scene as a
            // parent
            controller = new SceneController(scene);

            // Load Stylesheets
            scene.getStylesheets().add(getClass().getResource("/base.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/navigation.css").toExternalForm());

            // Load scene templates for the application while the Stage object is accessible
            controller.addScreen("dashboard", new Function<URL, Parent>() {
                @Override
                public Parent apply(URL input) {
                    Parent view = null;
                    try {
                        view = FXMLLoader.load(input);
                        // Make application window draggable again
                        draggableWindow(stage, view);
                    } catch (IOException exc) {
                        exc.printStackTrace();
                        // Logging the Exception
                        LOGGER.error("FXML 'Dashboard' cannot be loaded!", exc);
                        System.exit(-1);
                    }
                    return view;
                }
            }.apply(getClass().getResource("components/Dashboard.fxml")));
            controller.addScreen("game", new Function<URL, Parent>() {
                @Override
                public Parent apply(URL input) {
                    FXMLLoader loader = new FXMLLoader(input);
                    HeroCarousel ctrl = new HeroCarousel();
                    loader.setController(ctrl);
                    Parent view = null;
                    try {
                        view = loader.load();
                        // Make application window draggable again
                        draggableWindow(stage, view);
                    } catch (IOException exc) {
                        exc.printStackTrace();
                        // Logging the Exception
                        LOGGER.error("FXML 'HeroCarousel' cannot be loaded!", exc);
                        System.exit(-1);
                    }
                    return view;
                }
            }.apply(getClass().getResource("components/carousel/HeroCarousel.fxml")));

            // Scene is not visible yet, so staged it to the provided Stage by the JavaFX-Framework
            stage.setScene(scene);
            // Add TaskIcon to our JavaFX-Application
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("l3monkeys_ico.png")));
            // Set title
            stage.setTitle("13Monkeys | Games Collection");
            // Finally, make the stage visible
            stage.show();
        } catch(Exception exc) {
            exc.printStackTrace();
            // Logging the Exception
            LOGGER.fatal("The underlying JavaFX framework cannot be loaded!", exc);
            System.exit(-1);
        }
    }

    // Let the ApplicationEntry execute this function
    public static void main(String[] args) {
        // Enable logging
        Logger.enableLogging();
        // Launch JavaFX application
        launch(args);
    }

    /** --------------------------------------------------------------------------------------------------------------------- */
    //  Internal methods
    /** --------------------------------------------------------------------------------------------------------------------- */
    private void draggableWindow(Stage stage, Parent view) {
        // Make our custom window of the Collection draggable
        // Grab our window here -> Make it grabable
        view.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        // Now, move our window around -> Make it moveable
        view.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    /** --------------------------------------------------------------------------------------------------------------------- */
    //  Instances
    /** --------------------------------------------------------------------------------------------------------------------- */
    // Return the created SceneController instance.
    // This instance is important because it allows us to swap
    // between different Scenes in our application
    public static SceneController sceneController() {
        return controller;
    }
}