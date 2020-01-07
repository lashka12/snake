package view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import utilities.Constants;

public class Alerts {

	public static int delete(String content) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText("Are you sure want to delete this item ?");
		alert.setContentText(content);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Constants.STOP_IMAGE);
		stage.setAlwaysOnTop(true);
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
			return 1;
		return -1;

	}

	public static int update() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Updating");
		alert.setHeaderText("Are you sure want to update the item ?");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Constants.WARNING_IMAGE); // change
		stage.setAlwaysOnTop(true);
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
			return 1;
		return -1;

	}

	public static void confirmation(String operation) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("item was " + operation + " successfully");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Constants.SUCCESS_IMAGE); // change
		stage.setAlwaysOnTop(true);
		alert.show();

	}
	
	public static void warning(String content) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning");
		alert.setHeaderText(content);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(Constants.STOP_IMAGE); // change
		stage.setAlwaysOnTop(true);
		alert.show();

	}

}
