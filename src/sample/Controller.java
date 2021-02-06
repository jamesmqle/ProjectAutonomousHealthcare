package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {
    
    
    @FXML
    public void handleButtonEnergetic(ActionEvent event){
        System.out.println("GET HYPEEDD!!!");
    }

    public void handleButtonSore(ActionEvent event){
        System.out.println("Too bad so sad!!!");
    }
    public void handleButtonNeutral(ActionEvent event){
        System.out.println("bleh");
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
    
}
