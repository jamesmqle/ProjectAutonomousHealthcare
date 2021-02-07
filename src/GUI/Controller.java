package GUI;

import dataCollect.DataBase;
import dataCollect.Exercise;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public static String feeling;
    
    @FXML
    Button buttonEnergetic, buttonNeutral, buttonSore, buttonReady;

    @FXML
    ImageView exerciseImage;

    @FXML
    Label exerciseName;
    @FXML
    Label exerciseDescription;
    
    @FXML
    public void handleButtonEnergetic(ActionEvent event) throws IOException {
        Parent energeticParent = FXMLLoader.load(getClass().getResource("energetic.fxml"));
        Scene energeticScene = new Scene(energeticParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
        feeling = "energetic";
    }
    
    @FXML
    public void handleButtonReady(ActionEvent event) throws IOException {
    	 Parent ready = FXMLLoader.load(getClass().getResource("exercise.fxml"));
         Scene readyScene = new Scene(ready);
         Stage readyWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
         readyWindow.setScene(readyScene);
         readyWindow.show();
    }

    public void handleButtonSore(ActionEvent event) throws IOException {
    	Parent energeticParent = FXMLLoader.load(getClass().getResource("sore.fxml"));
        Scene energeticScene = new Scene(energeticParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
        feeling = "sore";
    }
    public void handleButtonNeutral(ActionEvent event) throws IOException {
    	Parent energeticParent = FXMLLoader.load(getClass().getResource("neutral.fxml"));
        Scene energeticScene = new Scene(energeticParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
        feeling = "neutral";
    }

    public void refreshExercise(ActionEvent event) throws IOException {
        Exercise e = DataBase.getExerciseRecommendation(feeling);
        e.startSession(new ArrayList<>());
        exerciseName.setText(e.type+" - "+e.difficulty);
        exerciseDescription.setText(e.getInstructions());
        e.endSession(new ArrayList<>());

    }
    
    /*
    When this method is called, it will change the Scene to exercise view 
     */
    @FXML
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent exerciseParent = FXMLLoader.load(getClass().getResource("exercise.fxml"));
        Scene exerciseScene = new Scene(exerciseParent);
        //This line gets the Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(exerciseScene);
        window.show();
    }

    public void goToMain(ActionEvent event) throws IOException {
        Parent intro = FXMLLoader.load(getClass().getResource("introScreen.fxml"));
        Scene energeticScene = new Scene(intro);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
    }

    public void goToMainForSore(ActionEvent event) throws IOException {
        Parent intro = FXMLLoader.load(getClass().getResource("introScreen.fxml"));
        Scene energeticScene = new Scene(intro);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
    }
    
}
