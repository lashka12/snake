package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Game;
import model.SysData;
import utilities.Constants;
import utilities.Direction;
import utilities.SoundEffects;
import view.GameSimulator;

/**
 * © 2019 Piranha Team , MIS - Haifa University Some Rights Reserved
 * 
 * the main class that runs the application
 * 
 * @author Lawrence
 *
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// MVC design pattern

		Game game = new Game(); // model
		GameSimulator view = new GameSimulator(); // view
		GameController gameController = new GameController(game, view); // controller
		SoundEffects soundEffects = new SoundEffects();

		game.register(view);
		game.register(soundEffects);

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainPage.fxml"));
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(e -> {

			if (!game.isPaused()) {

				if (e.getCode().equals(KeyCode.UP)
						&& game.getPlayGround().getSnake().getDirection() != Direction.DOWN) {
					gameController.changeDiriction(Direction.UP);
				} else {
					if (e.getCode().equals(KeyCode.DOWN)
							&& game.getPlayGround().getSnake().getDirection() != Direction.UP) {
						gameController.changeDiriction(Direction.DOWN);
					} else {
						if (e.getCode().equals(KeyCode.LEFT)
								&& game.getPlayGround().getSnake().getDirection() != Direction.RIGHT) {
							gameController.changeDiriction(Direction.LEFT);
						} else {
							if (e.getCode().equals(KeyCode.RIGHT)
									&& game.getPlayGround().getSnake().getDirection() != Direction.LEFT) {
								gameController.changeDiriction(Direction.RIGHT);
							} else {
								if (e.getCode().equals(KeyCode.P)) {
									GameController.getInstance().pauseGame();
									try {

										FXMLLoader fxmlLoader1 = new FXMLLoader(
												getClass().getResource("/view/PausePage.fxml"));
										Parent root1 = (Parent) fxmlLoader1.load();
										Stage stage1 = new Stage();
										stage1.initModality(Modality.APPLICATION_MODAL);
										Scene scene1 = new Scene(root1);
										stage1.setScene(scene1);
										stage1.initStyle(StageStyle.TRANSPARENT);
										scene1.setFill(Color.TRANSPARENT);
										stage1.show();
									} catch (Exception ex) {
										ex.printStackTrace();
									}

								}

							}

						}
					}
				}
			}
		});

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(Constants.SNAKE_ICON);
		primaryStage.show();

	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		SysData data = new SysData(); // initializing the data
		SoundEffects.playStartSound();
		launch(args);

	}

}
