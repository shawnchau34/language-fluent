package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.Course;
import com.model.FlashcardQuestion;
import com.model.LanguageLearningFacade;
import com.model.Lesson;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CourseHomeController implements Initializable {
        
    @FXML private Label test;
    @FXML private Label courseDescription;
    @FXML private Label courseTitle;
    @FXML private VBox flashcardsVBox;
    @FXML private VBox lessonsVBox;

    
    private LanguageLearningFacade facade;
    private User user;
    private Course currentCourse;
    private FlashcardQuestion flashcards;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageLearningFacade.getInstance();
        user = facade.getCurrentUser();
        currentCourse = facade.getCurrentCourseDetails();

        if (currentCourse != null) {
            // Set course title and description
            courseTitle.setText(currentCourse.getName());
            courseDescription.setText(currentCourse.getDescription());

            // Display lessons
            displayLessons(currentCourse.getAllLessons());
            displayFlashcards(currentCourse.getFlashcards());
        } else {
            courseTitle.setText("Course not found.");
            courseDescription.setText("Please select a valid course.");
        }
    }

        private void displayLessons(ArrayList<Lesson> lessons) {
        lessonsVBox.getChildren().clear(); // Clear any previous data

        for (Lesson lesson : lessons) {
            VBox lessonBox = new VBox();
            lessonBox.setSpacing(10);
            lessonBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray;");

            Label lessonName = new Label(lesson.getLessonName());
            lessonName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label lessonProgress = new Label("Progress: " + lesson.getLessonProgress() + "%");

            lessonBox.getChildren().addAll(lessonName, lessonProgress);

            // Add lessonBox to the lessonsVBox
            lessonsVBox.getChildren().add(lessonBox);
        }
    }

        private void displayFlashcards(ArrayList<FlashcardQuestion> flashcards) {
        flashcardsVBox.getChildren().clear(); // Clear existing flashcard nodes

        for (FlashcardQuestion flashcard : flashcards) {
            VBox flashcardBox = new VBox();
            flashcardBox.setSpacing(10);
            flashcardBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray;");

            Label flashcardName = new Label(flashcard.getFlashcardName());
            flashcardName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label flashcardProgress = new Label("Progress: " + flashcard.getFlashcardProgress() + "%");

            flashcardBox.getChildren().addAll(flashcardName, flashcardProgress);
            flashcardsVBox.getChildren().add(flashcardBox);
        }
    }



    @FXML
    public void lessonsVBox(MouseEvent event) throws IOException{
        App.setRoot("StoryTelling");
    }

    @FXML
    private void onHomeButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("MainHome");
    }



}