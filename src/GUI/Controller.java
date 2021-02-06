package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {
    
    @FXML
    Button buttonEnergetic, buttonNeutral, buttonSore;
    
    @FXML
    public void handleButtonEnergetic(ActionEvent event) throws IOException {
        Parent energeticParent = FXMLLoader.load(getClass().getResource("energetic.fxml"));
        Scene energeticScene = new Scene(energeticParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
    }

    public void handleButtonSore(ActionEvent event) throws IOException {
    	Parent energeticParent = FXMLLoader.load(getClass().getResource("sore.fxml"));
        Scene energeticScene = new Scene(energeticParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
    }
    public void handleButtonNeutral(ActionEvent event) throws IOException {
    	Parent energeticParent = FXMLLoader.load(getClass().getResource("neutral.fxml"));
        Scene energeticScene = new Scene(energeticParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(energeticScene);
        window.show();
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
