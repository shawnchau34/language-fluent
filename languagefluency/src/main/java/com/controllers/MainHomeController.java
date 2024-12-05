package com.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.model.LanguageLearningFacade;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainHomeController implements Initializable {

    @FXML private Label dynamicUser;
    @FXML private GridPane grid_books;
    private LanguageLearningFacade facade;
    private User user;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Properly initialize the facade and user
        facade = LanguageLearningFacade.getInstance(); // Correct method call
        user = facade.getCurrentUser();

        // Update ui
        if (user != null) {
            dynamicUser.setText("Welcome, " + user.getUsername());
        } else {
            dynamicUser.setText("lbl_title or user is null! Check your FXML or facade.");
        }

    }
}