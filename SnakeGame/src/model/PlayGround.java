package model;

import java.awt.Point;
import java.util.ArrayList;
import controller.GameEngine;
import controller.MainPageController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import utilities.Constants;
import utilities.FruiteType;
import utilities.SoundEffects;

public class PlayGround extends Pane {

	private int w, h;
	private ArrayList<Segment> segments;
	private Snake snake;
	private Mouse mouse;
	private Fruit apple;
	private Fruit banana;
	private Fruit pear;
	private int score;

	private static PlayGround instance = null;

	public static PlayGround getInstance() {

		return instance;

	}

	public PlayGround(int width, int height) {

		if (instance == null) {
			instance = this;
		}

		segments = new ArrayList<Segment>();
		w = width;
		h = height;

		setMinSize(w * Constants.BLOCK_SIZE, h * Constants.BLOCK_SIZE);
		ImageView backGround = new ImageView();
		backGround.fitWidthProperty().bind(widthProperty());
		backGround.setPreserveRatio(true);
		getChildren().add(backGround);

		addApple();
		addBanana();
		addPear();
		addMouse();
		addQuestion();

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(800);
				Platform.runLater(() -> {
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
				});

				Thread.sleep(1700);
				Platform.runLater(() -> {
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
				});
				Thread.sleep(700);
				Platform.runLater(() -> GameEngine.getInstance().resumeGame());
			} catch (Exception exc) {
				throw new Error("Unexpected interruption");
			}
		});
		thread.start();

	}

	public void addSnake(Snake s) {

		setSnake(s);
		for (Segment segment : s.getBody()) {
			addSegment(segment);

		}

	}

	private void addSegment(Segment segment) {

		getChildren().add(segment); // logicly
		segments.add(segment); // visualy

	}

	public void update() { // updating the pos of each block

		for (Segment segment : segments) {
			segment.update();

		}

		if (mouseWasEaten()) {

			SoundEffects.playBubbleSound();
			getChildren().remove(mouse);
			Fire fire = new Fire(mouse.getPosX(), mouse.getPosY());
			getChildren().add(fire);
			mouse = new Mouse(-100, -100);

			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(300);

					Platform.runLater(() -> getChildren().remove(fire));

					Thread.sleep(5 * 1000);
					Platform.runLater(() -> addMouse());

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();
			addToScore(20);
			MainPageController.getInstance().updateScore(score);

		}

		if (isEaten(apple)) {

			popPoints(apple);

			SoundEffects.playBubbleSound();
			getChildren().remove(apple);
			apple = new Fruit(-100, -100, FruiteType.APPLE);
			Thread thread = new Thread(() -> {
				try {

					Thread.sleep(apple.getType().getSecondsDelay() * 1000);
					Platform.runLater(() -> addApple());

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();

			snake.addSegment();
			addSegment(snake.getBody().get(snake.getBody().size() - 1));
			addToScore(apple.getType().getPoints());
			MainPageController.getInstance().updateScore(score);

		}
		if (isEaten(banana)) {

			popPoints(banana);
			SoundEffects.playBubbleSound();
			getChildren().remove(banana);
			banana = new Fruit(-100, -100, FruiteType.APPLE);

			Thread thread = new Thread(() -> {

				try {

					Thread.sleep(banana.getType().getSecondsDelay() * 1000);
					Platform.runLater(() -> addBanana());

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();
			addToScore(banana.getType().getPoints());
			MainPageController.getInstance().updateScore(score);

			snake.addSegment();
			addSegment(snake.getBody().get(snake.getBody().size() - 1));

		}

		if (isEaten(pear)) {

			popPoints(pear);
			SoundEffects.playBubbleSound();

			getChildren().remove(pear);

			pear = new Fruit(-100, -100, FruiteType.PEAR);

			Thread thread = new Thread(() -> {
				try {

					Thread.sleep(pear.getType().getSecondsDelay() * 1000);
					Platform.runLater(() -> addPear());

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();

			snake.addSegment();
			addSegment(snake.getBody().get(snake.getBody().size() - 1));
			addToScore(pear.getType().getPoints());
			MainPageController.getInstance().updateScore(score);
		}

		mouse.update();

	}

	private void popPoints(Fruit fruit) {

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

		final ImageView iv = new ImageView(image);

		Thread thread = new Thread(() -> {
			try {

				Platform.runLater(() -> {
					iv.setTranslateX(fruit.getPosX() * Constants.BLOCK_SIZE);
					iv.setTranslateY(fruit.getPosY() * Constants.BLOCK_SIZE);
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

	private void addMouse() {

		int randX = (int) (Math.random() * w);
		int randY = (int) (Math.random() * h);
		mouse = new Mouse(randX, randY);
		getChildren().add(mouse);

	}

	public void addApple() {

		Point p = getEmptyPoint();
		apple = new Fruit(p.x, p.y, FruiteType.APPLE);
		getChildren().addAll(apple);

	}

	public void addQuestion() {

		Question q = SysData.popRandomQuestion();
		Point p = getEmptyPoint();
		q.setLayoutX(p.x * Constants.BLOCK_SIZE);
		q.setLayoutY(p.y * Constants.BLOCK_SIZE);
		getChildren().add(q);

	}

	public void addBanana() {

		Point p = getEmptyPoint();
		banana = new Fruit(p.x, p.y, FruiteType.BANANA);
		getChildren().add(banana);

	}

	public void addPear() {

		Point p = getEmptyPoint();
		pear = new Fruit((int) p.getX(), (int) p.getY(), FruiteType.PEAR);
		getChildren().add(pear);

	}

	/**
	 * this method must return a random point on the board make sure this point ia
	 * not on top/under another object on the board
	 * 
	 * @return
	 */
	public Point getEmptyPoint() {

		Point p = new Point((int) (Math.random() * w), (int) (Math.random() * h));

		// if point is not empty
		// loop to till finding an empty point

		return p;

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private void addToScore(int pointsToAdd) {

		this.score = this.score + pointsToAdd;

	}

	public boolean mouseWasEaten() {

		if ((Math.abs(mouse.getPosX() - getSnake().getHead().getPosX()) >= 0
				&& Math.abs(mouse.getPosX() - getSnake().getHead().getPosX()) < 2)
				&& (Math.abs(mouse.getPosY() - getSnake().getHead().getPosY()) >= 0
						&& Math.abs(mouse.getPosY() - getSnake().getHead().getPosY()) < 2))
			return true;

		return false;

	}

	public Boolean isEaten(Fruit fruit) {

		// check if food == null

		if ((Math.abs(fruit.getPosX() - getSnake().getHead().getPosX()) >= 0
				&& Math.abs(fruit.getPosX() - getSnake().getHead().getPosX()) < 2)
				&& (Math.abs(fruit.getPosY() - getSnake().getHead().getPosY()) >= 0
						&& Math.abs(fruit.getPosY() - getSnake().getHead().getPosY()) < 2))

			return true;

		return false;

	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

}
