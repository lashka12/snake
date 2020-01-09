package tests;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import controller.GameController;
import model.DAO;
import model.Fruit;
import model.Game;
import model.JsonDAO;
import model.Mouse;
import model.Question;
import model.Segment;
import model.Snake;
import model.SysData;
import utilities.Constants;
import utilities.Direction;
import utilities.FruiteType;
import utilities.Level;

public class UnitTesting { // test all the methods in each class !!!

	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

	@Test
	public void QuestionTest() {

		String content = "What's my name?";
		Level l = Level.EASY;
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Heisenberg");
		answers.add("Ameer");
		answers.add("Messi");
		answers.add("Lorans");
		String correct = "Heisenberg";
		String team = "Piranha";
		Question q = new Question(content, l, answers, correct, team);
		Assert.assertNotNull(q);
		Assert.assertFalse(q.isEaten()); // we just make the question so its not eaten yet!
		Assert.assertEquals(correct, q.getCorrectAnswer());
	}

	@SuppressWarnings("unused")
	@Test
	public void SysDataClassTest() {

		SysData data = new SysData();
		String content = "What's my name?";
		Level l = Level.EASY;
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Heisenberg");
		answers.add("Ameer");
		answers.add("Messi");
		answers.add("Lorans");
		String correct = "Heisenberg";
		String team = "Piranha";
		Question q = new Question(content, l, answers, correct, team);
		SysData.addQuestion(q);
		Question s = SysData.popRandomQuestion(l);
		Assert.assertNotNull(s);

	}

	@Test
	public void GameClassTest() {

		String nickName = "GeorgeB";
		int scoreGame = 15;
		double duration = 20;
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		Game game = new Game("George", new Date(), 0, 0, new HashMap<String, Integer>());

		Assert.assertNotNull(game);
		Assert.assertFalse(game.getNickName().equals(nickName));
		Assert.assertFalse(game.getScore() == (scoreGame));
		Assert.assertFalse(game.getDuration() == (duration));
		game.setEatenObjects(hs);
		Assert.assertEquals(game.getEatenObjects(), hs);

	}

	@Test
	public void SnakeClassTest() {

		int length = 9;
		Snake snake = new Snake(length);
		Assert.assertNotNull(snake.getBody());
		snake.addSegment();
		Assert.assertTrue(snake.getBody().size() == length + 1);
	}

	@Test
	public void DAOTest() {

		DAO datAccessObject = new JsonDAO();
		ArrayList<Question> question = new ArrayList<Question>();
		question = datAccessObject.getQuestions();
		Assert.assertTrue(question.size() != 0);
		ArrayList<Game> games = new ArrayList<Game>();
		games = datAccessObject.getGames();
		Assert.assertTrue(games.size() != 0);
	}

	@Test
	public void SnakeHitAndEatTest() {

		@SuppressWarnings("unused")
		SysData data = new SysData();
		Game gm = new Game();
		GameController gc = new GameController(gm, null);
		gm.getPlayGround().getSnake().getHead().setX(gm.getPlayGround().getSnake().getBody().get(2).getX());
		gm.getPlayGround().getSnake().getHead().setY(gm.getPlayGround().getSnake().getBody().get(2).getY());
		Assert.assertTrue(gc.snakeHitBody());
		gm.getPlayGround().getSnake().getHead().setY(gm.getPlayGround().getMouse().getY());
		gm.getPlayGround().getSnake().getHead().setX(gm.getPlayGround().getMouse().getX());
		Assert.assertTrue(gc.mouseWasEaten());
		gm.getPlayGround().getSnake().getHead().setX(Constants.GAME_WIDTH - 1);
		gm.getPlayGround().getSnake().getHead().setY(Constants.GAME_HIGHT - 1);
		Assert.assertTrue(gc.snakeHitWall());
		gm.getPlayGround().getSnake().getHead().setX(70);
		gm.getPlayGround().getSnake().getHead().setY(4);
		Fruit fruit = new Fruit(gm.getPlayGround().getSnake().getHead().getX(),
				gm.getPlayGround().getSnake().getHead().getY(), FruiteType.APPLE);
		Assert.assertTrue(gc.snakeHit(fruit));

	}

	@Test
	public void SnakeEatAndScoreTest() {

		Game game = new Game();
		Mouse mouse = new Mouse(60, 60);
		int prevScore = game.getScore();
		game.addEatenObject(mouse);

		Assert.assertEquals(game.getScore(), prevScore + 30);
	}

	@Test
	public void AddObjectsToPlaygroundTest() {

		Game gm = new Game(); // model

		gm.getPlayGround().addSecretGate();
		Assert.assertNotNull(gm.getPlayGround().getSecretGate());

		gm.getPlayGround().addMouse();
		Assert.assertNotNull(gm.getPlayGround().getMouse());

		gm.getPlayGround().addFruit(FruiteType.APPLE);
		HashMap<FruiteType, Fruit> fruits = gm.getPlayGround().getFruits();
		Assert.assertSame(fruits, gm.getPlayGround().getFruits());
		Assert.assertTrue(gm.getPlayGround().getFruits().containsKey(FruiteType.APPLE));

	}

	@Test
	public void GetEmptyPointTest() {

		@SuppressWarnings("unused")
		SysData data = new SysData();
		Game gm = new Game(); // model

		Point point = gm.getPlayGround().getEmptyPoint();
		Point point2 = gm.getPlayGround().getEmptyPoint();

		Assert.assertNotSame(point, point2);

	}

	@Test
	public void GetCornerPointTest() {

		@SuppressWarnings("unused")
		SysData data = new SysData();
		Game gm = new Game(); // model

		Point point = gm.getPlayGround().getCornerPoint();
		Point point2 = gm.getPlayGround().getCornerPoint();

		Assert.assertNotSame(point, point2);

	}

	@Test
	public void AddGameTest() {

		Game game = new Game("Bisharat", new Date(), 210, 2, new HashMap<>());
		int games = SysData.getGames().size();
		SysData.addGame(game);

		Assert.assertNotEquals(games, SysData.getGames().size());
	}

	@Test
	public void AddQuestionTest() {

		ArrayList<String> answers = new ArrayList<String>();
		answers.add("George");
		answers.add("Lorans");
		answers.add("Natalie");
		answers.add("Hadi");
		Question question = new Question("Testing System Data", Level.INTERMEDIATE, answers, "Answer Content",
				"Piranha");
		int quesNo = SysData.getQuestions().size();
		SysData.addQuestion(question);

		Assert.assertNotEquals(quesNo, SysData.getQuestions().size());
	}

	@Test
	public void PopRandomQuestionTest() {

		Question question = SysData.popRandomQuestion(Level.EASY);
		Assert.assertNotNull(question);

	}

	@Test
	public void DeleteQuestionTest() {

		ArrayList<String> answers = new ArrayList<String>();
		answers.add("George");
		answers.add("Lorans");
		answers.add("Natalie");
		answers.add("Hadi");
		Question question = new Question("Testing System Data", Level.INTERMEDIATE, answers, "Answer Content",
				"Piranha");
		SysData.addQuestion(question);
		int quesNo = SysData.getQuestions().size();
		SysData.deleteQuestion(question);

		Assert.assertNotEquals(quesNo, SysData.getQuestions().size());
	}

	@Test
	public void MouseSteeringTest() {

		Mouse mouse = new Mouse(80, 80);

		int prevUpY = mouse.getY();
		mouse.moveUp();
		Assert.assertNotEquals(prevUpY, mouse.getY());

		int prevRightX = mouse.getX();
		mouse.moveRight();
		Assert.assertNotEquals(prevRightX, mouse.getX());

		int prevDownY = mouse.getY();
		mouse.moveDown();
		Assert.assertNotEquals(prevDownY, mouse.getX());

		int prevLeftX = mouse.getX();
		mouse.moveLeft();
		Assert.assertNotEquals(prevLeftX, mouse.getX());

	}

	@Test
	public void MoveSegmentTest() {
		Segment segment = new Segment(77, 100, null);
		segment.move();
		Direction dir = segment.getDirection();
		if (dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT)) {
			Assert.assertNotEquals(77, segment.getX());
		} else {
			Assert.assertNotEquals(100, segment.getY());
		}
	}

}