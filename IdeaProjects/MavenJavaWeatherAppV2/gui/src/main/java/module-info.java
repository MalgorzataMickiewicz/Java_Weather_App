module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.mm.gui to javafx.graphics;
    opens com.mm.gui.view;
    opens com.mm.gui.controller;
}