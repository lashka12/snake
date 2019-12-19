package view;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import java.util.ArrayList;
import javafx.scene.CacheHint;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Fruit;
import model.Game;
import model.Segment;
import utilities.Constants;
import utilities.FruiteType;

public class GameSimulator extends Pane {

	public static GameSimulator instance;
	private int size;
	ArrayList<ImageView> ivList; // could be removed and adding children each one alone
	private Game game;

	// Maybe adding a data structure to hold images is more efficient than lookUp

	public GameSimulator() {

		if (instance == null)
			instance = this;
		reset();

	}

	public void reset() {

		getChildren().clear();
		game = Game.getInstance();
		ivList = new ArrayList<ImageView>();
		size = game.getPlayGround().getSnake().getBody().size();
		setStyle("-fx-background-color: rgba(50, 50, 50, 0.5); -fx-border-color:rgba(255, 188, 43,0.7);");
		setMinWidth(Constants.GAME_WIDTH * Constants.BLOCK_SIZE);
		setMinHeight(Constants.GAME_HIGHT * Constants.BLOCK_SIZE);

		for (Segment s : game.getPlayGround().getSnake().getBody()) { // updating snake on screen

			ImageView snakePartImage = new ImageView(Constants.SNAKE_BODY_IMAGE);
			snakePartImage.setTranslateX(s.getX() * Constants.BLOCK_SIZE);
			snakePartImage.setTranslateY(s.getY() * Constants.BLOCK_SIZE);
			snakePartImage.setEffect(new DropShadow(10, Color.BLACK));
			snakePartImage.setCache(true);
			snakePartImage.setCacheHint(CacheHint.SPEED);
			snakePartImage.setId(s.getId() + "");
			ivList.add(snakePartImage);

		}

		ImageView appleImage = new ImageView(Constants.APPLE_IMAGE);
		ImageView banaImage = new ImageView(Constants.BANANA_IMAGE);
		ImageView pearImage = new ImageView(Constants.PEAR_IMAGE);
		ImageView mouseImage = new ImageView(Constants.MOUSE_RIGHT_IMAGE);
		appleImage.setTranslateX(game.getPlayGround().getFruits().get(FruiteType.APPLE).getX() * Constants.BLOCK_SIZE);
		appleImage.setTranslateY(game.getPlayGround().getFruits().get(FruiteType.APPLE).getY() * Constants.BLOCK_SIZE);
		appleImage.setEffect(new DropShadow(5, Color.BLACK));
		appleImage.setId("APPLE");
		banaImage.setTranslateX(game.getPlayGround().getFruits().get(FruiteType.BANANA).getX() * Constants.BLOCK_SIZE);
		banaImage.setTranslateY(game.getPlayGround().getFruits().get(FruiteType.BANANA).getY() * Constants.BLOCK_SIZE);
		banaImage.setEffect(new DropShadow(5, Color.BLACK));
		banaImage.setId("BANANA");
		pearImage.setTranslateX(game.getPlayGround().getFruits().get(FruiteType.PEAR).getX() * Constants.BLOCK_SIZE);
		pearImage.setTranslateY(game.getPlayGround().getFruits().get(FruiteType.PEAR).getY() * Constants.BLOCK_SIZE);
		pearImage.setEffect(new DropShadow(5, Color.BLACK));
		pearImage.setId("PEAR");
		mouseImage.setTranslateX(game.getPlayGround().getMouse().getX() * Constants.BLOCK_SIZE);
		mouseImage.setTranslateY(game.getPlayGround().getMouse().getY() * Constants.BLOCK_SIZE);
		mouseImage.setEffect(new DropShadow(5, Color.BLACK));
		mouseImage.setId("mouse");
		ivList.add(appleImage);
		ivList.add(banaImage);
		ivList.add(pearImage);
		ivList.add(mouseImage);
		getChildren().addAll(ivList); // or each one separately , check this later
	}

	public void render() {
//		ImageView apple = (ImageView) lookup("#APPLE");
//		System.out.println("apple graphics : " + apple.getTranslateX() / Constants.BLOCK_SIZE + ","
//				+ apple.getTranslateY() / Constants.BLOCK_SIZE);
//		System.out.println("apple Logic : " + game.getPlayGround().getFruits().get(FruiteType.APPLE).getX() + ","
//				+ game.getPlayGround().getFruits().get(FruiteType.APPLE).getY());
//		System.out.println("-------------------------------------------");
//		ImageView banana = (ImageView) lookup("#BANANA");
//		System.out.println("banana graphics : " + banana.getTranslateX() / Constants.BLOCK_SIZE + ","
//				+ banana.getTranslateY() / Constants.BLOCK_SIZE);
//		System.out.println("banana Logic : " + game.getPlayGround().getFruits().get(FruiteType.BANANA).getX() + ","
//				+ game.getPlayGround().getFruits().get(FruiteType.BANANA).getY());
//		System.out.println("-------------------------------------------");
//		ImageView pear = (ImageView) lookup("#PEAR");
//		System.out.println("pear graphics : " + pear.getTranslateX() / Constants.BLOCK_SIZE + ","
//				+ pear.getTranslateY() / Constants.BLOCK_SIZE);
//		System.out.println("pear Logic : " + game.getPlayGround().getFruits().get(FruiteType.PEAR).getX() + ","
//				+ game.getPlayGround().getFruits().get(FruiteType.PEAR).getY());
//		System.out.println("-------------------------------------------");

		if (game.isOver()) {
			// stopmouse
			Thread thread = new Thread(() -> {
				try {

					Thread.sleep(150);
					Platform.runLater(() -> {
						showGameOver();
					});

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();
		} else {

			if (game.getPlayGround().isHit()) {
				Thread thread = new Thread(() -> {
					try {
						Thread.sleep(150);
						Platform.runLater(() -> {
							showHit();
						});

					} catch (Exception exc) {
						throw new Error("Unexpected interruption");
					}
				});
				thread.start();
			}
		}

		if (game.getPlayGround().getSnake().getBody().size() > size) { // check if there is need to add new block

			for (int i = game.getPlayGround().getSnake().getBody().size() - size; i > 0; i--) {// view leftBehind blocks
				ImageView iv = new ImageView(Constants.SNAKE_BODY_IMAGE);
				iv.setEffect(new DropShadow(10, Color.BLACK));
				iv.setCache(true);
				iv.setCacheHint(CacheHint.SPEED);

				iv.setId(game.getPlayGround().getSnake().getBody().size() - i + "");
				getChildren().add(iv); // adding snake part image to screen

			}

		}

		for (Segment segmentToUpdate : game.getPlayGround().getSnake().getBody()) { // updating the position of snake on
																					// screen
			ImageView tb = (ImageView) lookup("#" + segmentToUpdate.getId());
			tb.setTranslateX(segmentToUpdate.getX() * Constants.BLOCK_SIZE);
			tb.setTranslateY(segmentToUpdate.getY() * Constants.BLOCK_SIZE);

		}

		ImageView mouseImage = (ImageView) lookup("#mouse");
		mouseImage.setTranslateX(game.getPlayGround().getMouse().getX() * Constants.BLOCK_SIZE); // update mouse pic
																									// position
		// on screen
		mouseImage.setTranslateY(game.getPlayGround().getMouse().getY() * Constants.BLOCK_SIZE);

		switch (game.getPlayGround().getMouse().getDirection()) {
		case UP:
			mouseImage.setImage(Constants.MOUSE_UP_IMAGE);
			break;
		case DOWN:
			mouseImage.setImage(Constants.MOUSE_DOWN_IMAGE);
			break;
		case LEFT:
			mouseImage.setImage(Constants.MOUSE_LEFT_IMAGE);
			break;
		case RIGHT:
			mouseImage.setImage(Constants.MOUSE_RIGHT_IMAGE);
			break;
		}

		for (Fruit fruit : game.getPlayGround().getFruits().values()) {

			ImageView fruitImage = (ImageView) lookup("#" + fruit.getType());

			if (fruit.isEaten()) {
				if (fruitImage.isVisible()) {
					fruitImage.setVisible(false);
					popPoints(fruit);
				}
			} else {
				if (!fruitImage.isVisible()) { // it means it was hidden and now is the time to show it again
					fruitImage.setTranslateX(fruit.getX() * Constants.BLOCK_SIZE);
					fruitImage.setTranslateY(fruit.getY() * Constants.BLOCK_SIZE);
					fruitImage.setVisible(true);
				}

			}
		}
		size = game.getPlayGround().getSnake().getBody().size();

	}

	public void showReady() {// can merge with showGo as the same function

		ImageView Ready = new ImageView(Constants.READY_IMAGE);
		getChildren().add(Ready);
		Ready.setTranslateX((Constants.GAME_WIDTH * Constants.BLOCK_SIZE) - 650);
		Ready.setTranslateY((Constants.GAME_HIGHT * Constants.BLOCK_SIZE) - 300);
		ScaleTransition st = new ScaleTransition(Duration.millis(1000), Ready);
		st.setFromX(0.1);
		st.setFromY(0.1);
		st.setToX(2);
		st.setToY(2);
		st.play();
		FadeTransition ft = new FadeTransition(Duration.millis(1000), Ready);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();

	}

	public void showGo() {

		ImageView Go = new ImageView(Constants.GO_IMAGE);
		getChildren().add(Go);
		Go.setTranslateX((Constants.GAME_WIDTH * Constants.BLOCK_SIZE) - 550);
		Go.setTranslateY((Constants.GAME_HIGHT * Constants.BLOCK_SIZE) - 300);
		ScaleTransition st2 = new ScaleTransition(Duration.millis(800), Go);
		st2.setFromX(0.1);
		st2.setFromY(0.1);
		st2.setToX(2);
		st2.setToY(2);
		st2.play();

		FadeTransition ft2 = new FadeTransition(Duration.millis(1000), Go);
		ft2.setFromValue(1.0);
		ft2.setToValue(0.0);
		ft2.play();
	}

	public void showHit() {

		ImageView hit = new ImageView(Constants.HIT_IMAGE);
		getChildren().add(hit);
		hit.setTranslateX((Constants.GAME_WIDTH * Constants.BLOCK_SIZE) - 550);
		hit.setTranslateY((Constants.GAME_HIGHT * Constants.BLOCK_SIZE) - 300);
		ScaleTransition st2 = new ScaleTransition(Duration.millis(800), hit);
		st2.setFromX(0.1);
		st2.setFromY(0.1);
		st2.setToX(2);
		st2.setToY(2);
		st2.play();

		FadeTransition ft2 = new FadeTransition(Duration.millis(1000), hit);
		ft2.setFromValue(1.0);
		ft2.setToValue(0.0);
		ft2.play();
	}

	public void showGameOver() {

		ImageView Go = new ImageView(Constants.GAME_OVER_IMAGE);
		getChildren().add(Go);
		Go.setTranslateX((Constants.GAME_WIDTH * Constants.BLOCK_SIZE) - 750);
		Go.setTranslateY((Constants.GAME_HIGHT * Constants.BLOCK_SIZE) - 300);
		ScaleTransition st2 = new ScaleTransition(Duration.millis(800), Go);
		st2.setFromX(0.1);
		st2.setFromY(0.1);
		st2.setToX(1);
		st2.setToY(1);
		st2.play();

		FadeTransition ft2 = new FadeTransition(Duration.millis(4000), Go);
		ft2.setFromValue(1.0);
		ft2.setToValue(0.0);
		ft2.play();
	}

	public void popPoints(Fruit fruit) {

		Image image = null;
		switch (fruit.getType().getPoints()) {
		case 10:
			image = Constants.POINTS10_IMAGE;
			break;
		case 15:
			image = Constants.POINTS15_IMAGE;
			break;
		case 20:
			image = Constants.POINTS20_IMAGE;
			break;
		}
		ImageView iv = new ImageView(image);

		Thread thread = new Thread(() -> {

			try {

				Platform.runLater(() -> {
					iv.setTranslateX(fruit.getX() * Constants.BLOCK_SIZE);
					iv.setTranslateY(fruit.getY() * Constants.BLOCK_SIZE);

					ScaleTransition st = new ScaleTransition(Duration.millis(500), iv);
					st.setFromX(0.1);
					st.setFromY(0.1);
					st.setToX(2);
					st.setToY(2);
					st.play();
					FadeTransition ft = new FadeTransition(Duration.millis(500), iv);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();
					getChildren().add(iv);
				});

				Thread.sleep(500);
				Platform.runLater(() -> {
					getChildren().remove(iv);
				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	public static GameSimulator getInstance() {

		return instance;

	}

}
