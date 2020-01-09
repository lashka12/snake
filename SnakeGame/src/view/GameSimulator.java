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
import model.Block;
import model.Fruit;
import model.Game;
import model.GameObserver;
import model.Mouse;
import model.Question;
import model.SegmentIterator;
import utilities.Constants;
import utilities.FruiteType;
import utilities.Level;

/**
 * this class extends javafx.scene.layout.Pane and it is used to simulate the
 * game at the run time , the game logic will also run without this view class
 * 
 * it implements game observer in order to pop points when ever snake eats a
 * object automatically
 * 
 * @see GameObserver.java
 * @see design patterns.PDF fro more information
 * 
 */
public class GameSimulator extends Pane implements GameObserver {

	public static GameSimulator singleton;
	ArrayList<ImageView> ivList;
	private Game game; // game reference
	private int size;
	Color Snakeshadow;

	/**
	 * this method returns the game simulator instance
	 * 
	 * @return the singleton
	 */
	public static GameSimulator getInstance() {

		return singleton;

	}

	/**
	 * full constructor
	 */
	public GameSimulator() {

		if (singleton == null)
			singleton = this;

		reset();

	}

	/**
	 * @see GameObserver.java
	 * @see design patterns.PDF
	 */
	@Override
	public void update() {
		popPoints();

	}

	/**
	 * this method reset the view of the game
	 */
	public void reset() {

		getChildren().clear();
		game = Game.getInstance();
		ivList = new ArrayList<ImageView>();
		size = game.getPlayGround().getSnake().getBody().size();
		setStyle("-fx-background-color: rgba(50, 50, 50, 0.5); -fx-border-color:rgba(255, 188, 43,0.7);");
		setMinWidth(Constants.GAME_WIDTH * Constants.BLOCK_SIZE);
		setMinHeight(Constants.GAME_HIGHT * Constants.BLOCK_SIZE);
		Snakeshadow = Color.BLACK;
		SegmentIterator iterator = game.getPlayGround().getSnake().getIterator();
		for (iterator.first(); !iterator.isDone(); iterator.next()) { // updating snake on screen

			ImageView snakePartImage = new ImageView(Constants.SNAKE_BODY_IMAGE);
			snakePartImage.setTranslateX(iterator.currentValue().getX() * Constants.BLOCK_SIZE);
			snakePartImage.setTranslateY(iterator.currentValue().getY() * Constants.BLOCK_SIZE);
			snakePartImage.setEffect(new DropShadow(20, Color.BLACK));
			snakePartImage.setCache(true);
			snakePartImage.setCacheHint(CacheHint.SPEED);
			snakePartImage.setId(iterator.currentValue().getId() + "");
			ivList.add(snakePartImage);

		}

		ImageView appleImage = new ImageView(Constants.APPLE_IMAGE);
		ImageView banaImage = new ImageView(Constants.BANANA_IMAGE);
		ImageView pearImage = new ImageView(Constants.PEAR_IMAGE);
		ImageView mouseImage = new ImageView(Constants.MOUSE_RIGHT_STABLE_IMAGE);
		ImageView easyQuestionImage = new ImageView(Constants.EASY_QUESTION);
		ImageView intermediateQuestionImage = new ImageView(Constants.INTER_QUESTION);
		ImageView hardQuestion = new ImageView(Constants.HARD_QUESTION);
		ImageView secretGateEnterance = new ImageView(Constants.SECRET_ENTER);
		ImageView secretGateExit = new ImageView(Constants.SECRET_EXIT);

		easyQuestionImage
				.setTranslateX(game.getPlayGround().getQuestions().get(Level.EASY).getX() * Constants.BLOCK_SIZE);
		easyQuestionImage
				.setTranslateY(game.getPlayGround().getQuestions().get(Level.EASY).getY() * Constants.BLOCK_SIZE);
		easyQuestionImage.setEffect(new DropShadow(20, Color.BLACK));
		easyQuestionImage.setId("EASY");
		intermediateQuestionImage.setTranslateX(
				game.getPlayGround().getQuestions().get(Level.INTERMEDIATE).getX() * Constants.BLOCK_SIZE);
		intermediateQuestionImage.setTranslateY(
				game.getPlayGround().getQuestions().get(Level.INTERMEDIATE).getY() * Constants.BLOCK_SIZE);
		intermediateQuestionImage.setEffect(new DropShadow(20, Color.BLACK));
		intermediateQuestionImage.setId("INTERMEDIATE");
		hardQuestion.setTranslateX(game.getPlayGround().getQuestions().get(Level.HARD).getX() * Constants.BLOCK_SIZE);
		hardQuestion.setTranslateY(game.getPlayGround().getQuestions().get(Level.HARD).getY() * Constants.BLOCK_SIZE);
		hardQuestion.setEffect(new DropShadow(20, Color.BLACK));
		hardQuestion.setId("HARD");
		appleImage.setTranslateX(game.getPlayGround().getFruits().get(FruiteType.APPLE).getX() * Constants.BLOCK_SIZE);
		appleImage.setTranslateY(game.getPlayGround().getFruits().get(FruiteType.APPLE).getY() * Constants.BLOCK_SIZE);
		appleImage.setEffect(new DropShadow(20, Color.BLACK));
		appleImage.setId("APPLE");
		banaImage.setTranslateX(game.getPlayGround().getFruits().get(FruiteType.BANANA).getX() * Constants.BLOCK_SIZE);
		banaImage.setTranslateY(game.getPlayGround().getFruits().get(FruiteType.BANANA).getY() * Constants.BLOCK_SIZE);
		banaImage.setEffect(new DropShadow(20, Color.BLACK));
		banaImage.setId("BANANA");
		pearImage.setTranslateX(game.getPlayGround().getFruits().get(FruiteType.PEAR).getX() * Constants.BLOCK_SIZE);
		pearImage.setTranslateY(game.getPlayGround().getFruits().get(FruiteType.PEAR).getY() * Constants.BLOCK_SIZE);
		pearImage.setEffect(new DropShadow(20, Color.BLACK));
		pearImage.setId("PEAR");
		mouseImage.setTranslateX(game.getPlayGround().getMouse().getX() * Constants.BLOCK_SIZE);
		mouseImage.setTranslateY(game.getPlayGround().getMouse().getY() * Constants.BLOCK_SIZE);
		mouseImage.setEffect(new DropShadow(20, Color.BLACK));
		mouseImage.setId("mouse");
		secretGateEnterance.setTranslateX(game.getPlayGround().getSecretGate().getEnterX() * Constants.BLOCK_SIZE);
		secretGateEnterance.setTranslateY(game.getPlayGround().getSecretGate().getEnterY() * Constants.BLOCK_SIZE);
		secretGateEnterance.setEffect(new DropShadow(20, Color.BLACK));
		secretGateEnterance.setId("secretEnter");
		secretGateExit.setTranslateX(game.getPlayGround().getSecretGate().getExitX() * Constants.BLOCK_SIZE);
		secretGateExit.setTranslateY(game.getPlayGround().getSecretGate().getExitY() * Constants.BLOCK_SIZE);
		secretGateExit.setEffect(new DropShadow(20, Color.BLACK));
		secretGateExit.setId("secretExit");
		ivList.add(secretGateEnterance);
		ivList.add(secretGateExit);
		ivList.add(appleImage);
		ivList.add(banaImage);
		ivList.add(pearImage);
		ivList.add(mouseImage);
		ivList.add(easyQuestionImage);
		ivList.add(intermediateQuestionImage);
		ivList.add(hardQuestion);
		getChildren().addAll(ivList);

	}

	public void putShield() {

		ImageView shieldImage = new ImageView(Constants.DEFENCE_ENTER);
		shieldImage.setTranslateX(game.getPlayGround().getShield().getX() * Constants.BLOCK_SIZE);
		shieldImage.setTranslateY(game.getPlayGround().getShield().getY() * Constants.BLOCK_SIZE);
		shieldImage.setEffect(new DropShadow(25, Color.GREEN));
		shieldImage.setId("shield");
		getChildren().add(shieldImage);
	}

	public void removeShield() {
		ImageView shieldImage = (ImageView) lookup("#shield");
		getChildren().remove(shieldImage);
	}

	public void setShieldStatus(int status) {

		switch (status) {
		case 1:
			Snakeshadow = Color.GREEN;
			break;
		case 2:
			Snakeshadow = Color.ORANGE;
			break;
		case 3:
			Snakeshadow = Color.RED;
			break;
		case 4:
			Snakeshadow = Color.BLACK;
			break;
		}

		SegmentIterator iterator = game.getPlayGround().getSnake().getIterator();
		for (iterator.first(); !iterator.isDone(); iterator.next()) {
			ImageView tb = (ImageView) lookup("#" + iterator.currentValue().getId());
			tb.setEffect(new DropShadow(20, Snakeshadow));

		}

	}

	/**
	 * this is the main method that is called from the GameController.java each time
	 * the model is updated
	 */
	public void render() {

		if (game.isOver()) {
			// stopmouse
			ImageView mouseImage = (ImageView) lookup("#mouse");
			switch (game.getPlayGround().getMouse().getDirection()) {
			case LEFT:
				mouseImage.setImage(Constants.MOUSE_LEFT_STABLE_IMAGE);
				break;
			case RIGHT:
				mouseImage.setImage(Constants.MOUSE_RIGHT_STABLE_IMAGE);
				break;
			case UP:
				mouseImage.setImage(Constants.MOUSE_UP_STABLE_IMAGE);
				break;
			case DOWN:
				mouseImage.setImage(Constants.MOUSE_DOWN_STABLE_IMAGE);
				break;

			}

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

			if (game.getPlayGround().getSnake().getBody().size() > size) { // check if there is need to add new block on
																			// the screen

				for (int i = game.getPlayGround().getSnake().getBody().size() - size; i > 0; i--) {// view leftBehind
																									// blocks
					ImageView iv = new ImageView(Constants.SNAKE_BODY_IMAGE);
					iv.setEffect(new DropShadow(20, Snakeshadow));
					iv.setCache(true);
					iv.setCacheHint(CacheHint.SPEED);
					iv.setId(game.getPlayGround().getSnake().getBody().size() - i + "");
					iv.toBack();
					getChildren().add(iv); // adding snake part image to screen

				}

			}

			// updating the position of snake on screen
			SegmentIterator iterator = game.getPlayGround().getSnake().getIterator();
			for (iterator.first(); !iterator.isDone(); iterator.next()) {
				ImageView tb = (ImageView) lookup("#" + iterator.currentValue().getId());
				tb.setTranslateX(iterator.currentValue().getX() * Constants.BLOCK_SIZE);
				tb.setTranslateY(iterator.currentValue().getY() * Constants.BLOCK_SIZE);
				tb.setVisible(true);

			}

			if (game.isPaused()) {
				ImageView tb = (ImageView) lookup("#" + (game.getPlayGround().getSnake().getBody().size() - 1));
				tb.setVisible(false);
			}

			ImageView mouseImage = (ImageView) lookup("#mouse");

			if (!game.getPlayGround().getMouse().isEaten()) {
				if (!mouseImage.isVisible())
					mouseImage.setVisible(true);
				// update visually position
				mouseImage.setTranslateX(game.getPlayGround().getMouse().getX() * Constants.BLOCK_SIZE);
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
			} else {
				if (mouseImage.isVisible()) {
					mouseImage.setVisible(false);
					showFire();
				}

			}

			for (Fruit fruit : game.getPlayGround().getFruits().values()) {

				ImageView fruitImage = (ImageView) lookup("#" + fruit.getType());

				if (fruit.isEaten()) {
					if (fruitImage.isVisible()) {
						fruitImage.setVisible(false);
						// popPoints(fruit);
					}
				} else {
					if (!fruitImage.isVisible()) { // it means it was hidden and now is the time to show it again
						fruitImage.setTranslateX(fruit.getX() * Constants.BLOCK_SIZE);
						fruitImage.setTranslateY(fruit.getY() * Constants.BLOCK_SIZE);
						fruitImage.setVisible(true);
					}

				}
			}

			for (Question question : game.getPlayGround().getQuestions().values()) {

				ImageView questionImage = (ImageView) lookup("#" + question.getLevel());
				if (question.isEaten()) {
					if (questionImage.isVisible()) {
						questionImage.setVisible(false);
					}
				} else {
					if (!questionImage.isVisible()) { // it means it was hidden and now is the time to show it again
						questionImage.setTranslateX(question.getX() * Constants.BLOCK_SIZE);
						questionImage.setTranslateY(question.getY() * Constants.BLOCK_SIZE);
						questionImage.setVisible(true);
					}

				}
			}

			size = game.getPlayGround().getSnake().getBody().size();
		}
	}

	// ---------------------------------------Animation_methods--------------------------------------------

	/**
	 * this method shows a fire animation
	 */
	private void showFire() {

		ImageView fire = new ImageView(Constants.FIRE_IMAGE);
		Thread thread = new Thread(() -> {

			try {

				Platform.runLater(() -> {
					fire.setTranslateX(game.getPlayGround().getMouse().getX() * Constants.BLOCK_SIZE);
					fire.setTranslateY(game.getPlayGround().getMouse().getY() * Constants.BLOCK_SIZE);
					getChildren().add(fire);
				});

				Thread.sleep(500);
				Platform.runLater(() -> {
					getChildren().remove(fire);
				});

			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	public void showReady() {

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

	/**
	 * this method pops a game over animation on the screen when game is done in the
	 * model
	 * 
	 */
	public void showGameOver() {

		ImageView Go = new ImageView(Constants.GAME_OVER_IMAGE);
		getChildren().add(Go);
		Go.setTranslateX((Constants.GAME_WIDTH * Constants.BLOCK_SIZE) - 750);
		Go.setTranslateY((Constants.GAME_HIGHT * Constants.BLOCK_SIZE) - 300);
		ScaleTransition st2 = new ScaleTransition(Duration.millis(600), Go);
		st2.setFromX(0.1);
		st2.setFromY(0.1);
		st2.setToX(1);
		st2.setToY(1);
		st2.play();

		FadeTransition ft2 = new FadeTransition(Duration.millis(2500), Go);
		ft2.setFromValue(1.0);
		ft2.setToValue(0.0);
		ft2.play();
	}

	/**
	 * this method pops a points animation on the screen when object is eaten on the
	 * model
	 */
	public void popPoints() {

		Image image = null;
		Block b = game.getLastEatenBlock();

		if (b instanceof Fruit) {
			switch (((Fruit) b).getType()) {
			case APPLE:
				image = Constants.POINTS10_IMAGE;
				break;
			case BANANA:
				image = Constants.POINTS15_IMAGE;
				break;
			case PEAR:
				image = Constants.POINTS20_IMAGE;
				break;

			}

		}
		if (b instanceof Mouse) {
			// image = Constants.POINTS30_IMAGE;

		}

		ImageView iv = new ImageView(image);

		Thread thread = new Thread(() -> {

			try {

				Platform.runLater(() -> {
					iv.setTranslateX(b.getX() * Constants.BLOCK_SIZE);
					iv.setTranslateY(b.getY() * Constants.BLOCK_SIZE);

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

}
