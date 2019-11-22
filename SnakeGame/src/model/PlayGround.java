package model;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utilities.Constants;
import utilities.FruiteType;

public class PlayGround extends Pane {

	private int w, h;
	private ArrayList<Segment> segments;
	private Snake snake;
	private Mouse mouse;
	private Fruit apple;
	private Fruit banana;
	private Fruit pear;

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
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		ImageView backGround = new ImageView();
		backGround.setImage(Constants.DIRT_BACKGROUND_IMAGE);
		backGround.fitWidthProperty().bind(widthProperty());
		backGround.setPreserveRatio(true);
		getChildren().add(backGround);

		addApple();
		addBanana();
		addPear();
		addMouse();

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
		System.out.println(snake.getBody().size());

	}

	public void update() { // updating the pos of each block

		for (Segment segment : segments) {
			segment.update();

		}

		if (mouseWasEaten()) {

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
			System.out.println("+Mouse ");

		}

		if (isEaten(apple)) {
			getChildren().remove(apple);
			apple = new Fruit(-100, -100, FruiteType.APPLE);
			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(5 * 1000);
					Platform.runLater(() -> addApple());

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();

			snake.addSegment();
			addSegment(snake.getBody().get(snake.getBody().size() - 1));
			System.out.println("+Apple " + apple);

		}
		if (isEaten(banana)) {
			getChildren().remove(banana);
			banana = new Fruit(-100, -100, FruiteType.APPLE);

			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(10 * 1000);
					Platform.runLater(() -> addBanana());

				} catch (Exception exc) {
					throw new Error("Unexpected interruption");
				}
			});
			thread.start();
			System.out.println("+Banana " + banana);
			snake.addSegment(); // should fix
			addSegment(snake.getBody().get(snake.getBody().size() - 1));

		}

		if (isEaten(pear)) {
			getChildren().remove(pear);
			addPear();

			System.out.println("+pear " + pear);
			snake.addSegment(); // should fix
			addSegment(snake.getBody().get(snake.getBody().size() - 1));

		}

		mouse.update();

	}

	private void addMouse() {

		int randX = (int) (Math.random() * w);
		int randY = (int) (Math.random() * h);
		mouse = new Mouse(randX, randY);
		getChildren().add(mouse);

	}

	public void addApple() {

		int randX = (int) (Math.random() * w);
		int randY = (int) (Math.random() * h);

		apple = new Fruit(randX, randY, FruiteType.APPLE);
		getChildren().addAll(apple);

	}

	public void addBanana() {

		int randX = (int) (Math.random() * w);
		int randY = (int) (Math.random() * h);

		banana = new Fruit(randX, randY, FruiteType.BANANA);
		getChildren().add(banana);

	}

	public void addPear() {

		int randX = (int) (Math.random() * w);
		int randY = (int) (Math.random() * h);

		pear = new Fruit(randX, randY, FruiteType.PEAR);
		getChildren().add(pear);

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
