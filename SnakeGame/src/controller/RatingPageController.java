package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RatingPageController implements Initializable{

	
    @FXML
    private StackPane root;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

		
	}

}
