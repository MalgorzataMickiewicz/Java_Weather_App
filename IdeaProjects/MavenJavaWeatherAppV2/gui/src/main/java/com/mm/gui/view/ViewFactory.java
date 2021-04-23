package com.mm.gui.view;

import com.mm.gui.controller.AbstractController;
import com.mm.gui.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewFactory {

    private List<Stage> activeStages;

    public ViewFactory() {

        activeStages = new ArrayList<>();
    }

    public void showMainWindow() {
        AbstractController controller = new MainWindowController(this, "/MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(AbstractController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

