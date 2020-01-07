package tests;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import controller.GameController;
import model.Block;
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
import utilities.FruiteType;
import utilities.Level;
import view.GameSimulator;

public class UnitTest { // test all the methods in each class !!!

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
		Date date = new Date();
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
	public void JsonReaderClassTest() { // change it and check all methods !!!

		DAO datAccessObject= new JsonDAO();
		ArrayList<Question> result = new ArrayList<Question>();
		result = datAccessObject.getQuestions();
		Assert.assertTrue(result.size() != 0);

	}
	
	
	@Test
	public void SnakeHitAndEatTest() {

		SysData data = new SysData();
		Game gm = new Game(); // model
		GameController gc = new GameController(gm, null); // controller

		gm.getPlayGround().getSnake().getHead().setX(gm.getPlayGround().getSnake().getBody().get(2).getX());
		gm.getPlayGround().getSnake().getHead().setY(gm.getPlayGround().getSnake().getBody().get(2).getY());
		Assert.assertTrue(gc.snakeHitBody());
		
		gm.getPlayGround().getSnake().getHead().setY(gm.getPlayGround().getMouse().getY());
		gm.getPlayGround().getSnake().getHead().setX(gm.getPlayGround().getMouse().getX());
		Assert.assertTrue(gc.mouseWasEaten());
		
		gm.getPlayGround().getSnake().getHead().setX(Constants.GAME_WIDTH-1);
		gm.getPlayGround().getSnake().getHead().setY(Constants.GAME_HIGHT-1);
		Assert.assertTrue(gc.snakeHitWall());
		
		gm.getPlayGround().getSnake().getHead().setX(70);
		gm.getPlayGround().getSnake().getHead().setY(4);
		Fruit fruit = new Fruit(gm.getPlayGround().getSnake().getHead().getX(), gm.getPlayGround().getSnake().getHead().getY(), FruiteType.APPLE);
		Assert.assertTrue(gc.snakeHit(fruit));

	}

	@Test
	public void SnakeEatAndScoreTest() {
		
		Game game = new Game();
		Mouse mouse = new Mouse(60, 60);
		int prevScore = game.getScore();
		game.addEatenObject(mouse);
		
		Assert.assertEquals(game.getScore(),prevScore+30 );
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
		
		SysData data = new SysData();
		Game gm = new Game(); // model
		
		Point point = gm.getPlayGround().getEmptyPoint();
		Point point2 = gm.getPlayGround().getEmptyPoint();

		Assert.assertNotSame(point, point2);
 
	}
	
	@Test
	public void GetCornerPointTest() {
		
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
		Question question = new Question("Testing System Data", Level.INTERMEDIATE, answers, "Answer Content" ,"Piranha" );
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
		Question question = new Question("Testing System Data", Level.INTERMEDIATE, answers, "Answer Content" ,"Piranha" );
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



}