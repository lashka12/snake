package tests;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;

import model.Fruit;
import model.Game;
import model.PlayGround;
import model.Question;
import model.Snake;
import model.SysData;
import utilities.FruiteType;
import utilities.JsonReader;
import utilities.Level;

public class AcceptenceTests2And3 {

	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

	@Test
	public void GameClassTest() {

		@SuppressWarnings("unused")
		SysData sd = new SysData();
		String nickName = "Natalie";
		Date date = new Date();
		int scoreGame = 15;
		double duration = 20;
		Game game = new Game(nickName, date, scoreGame, duration);
		game.setNickName(nickName);
		Assert.assertTrue(game.getNickName().equals(nickName));
		game.setDate(date);
		Assert.assertTrue(game.getDate().equals(date));
		game.setScore(scoreGame);
		Assert.assertTrue(game.getScore() == (scoreGame));
		game.setDuration(duration);
		Assert.assertTrue(game.getDuration() == (duration));

	}

	@Test
	public void JsonReaderClassTest() {

		ArrayList<Question> result = new ArrayList<Question>();
		result = JsonReader.readQuestionsFile();
		Assert.assertTrue(result.size() != 0);
	}

	@Test
	public void SysDataClassTest() {

		Assert.assertTrue(SysData.popRandomQuestion() != null);

	}

	@Test
	public void QuestionClassTest() {

		@SuppressWarnings("unused")
		SysData sd = new SysData();
		String content = "What is the software design pattern?";
		Level level = Level.EASY;
		ArrayList<String> answers = new ArrayList<String>();
		String correctAnswer = "MVC";
		String team = "Perhana";
		answers.add("MVC");
		answers.add("UML");
		answers.add("API");
		answers.add("DNS");
		Question ques = new Question(content, level, answers, correctAnswer, team);
		ques.setContent(content);
		Assert.assertTrue(ques.getContent().equals(content));
		ques.setcorrectAnswer(correctAnswer);
		Assert.assertTrue(ques.getcorrectAnswer().equals(correctAnswer));
		ques.setLevel(level);
		Assert.assertTrue(ques.getLevel().equals(level));
		ques.setTeam(team);
		Assert.assertTrue(ques.getTeam().equals(team));
		ques.setAnswers(answers);
		Assert.assertTrue(ques.getAnswers().equals(answers));
	}

	@Test
	public void PlayGroundClassTest() {

		@SuppressWarnings("unused")
		SysData sd = new SysData();
		int w = 100, h = 100;
		Snake snake = new Snake(5);
		int score = 10;
		PlayGround playground = new PlayGround(w, h);
		playground.addSnake(snake);
		Assert.assertTrue(playground.getSnake() != null);
		playground.setScore(score);
		Assert.assertTrue(playground.getScore() == score);
		playground.addToScore(5);
		Assert.assertTrue(playground.getScore() == score + 5);
		Assert.assertTrue(playground.getH() == h);
		Assert.assertTrue(playground.getW() == w);

	}
	
	@Test
	public void SnakeClassTest() {
		
		Snake snake = new Snake(7);
		Assert.assertTrue(snake.getHead() != null);
		Assert.assertTrue(snake.getBody() != null);
		
	}
	
	
	@Test
	public void FruitClassTest() {

		Fruit apple = new Fruit(-100, -100 , FruiteType.APPLE);
		Assert.assertTrue(apple.getType().equals(FruiteType.APPLE) );
		Assert.assertTrue(apple.getType().name().equals("APPLE"));
		Assert.assertTrue(apple.getType().getSecondsDelay() == 5);
		Assert.assertTrue(apple.getType().getPoints() == 10);
		Assert.assertTrue(apple.getType().getTailExtension() == 1);
		Assert.assertTrue(apple.getType().getImage() != null);
		
		Fruit pear = new Fruit(-100, -100 , FruiteType.PEAR);
		Assert.assertTrue(pear.getType().equals(FruiteType.PEAR) );
		Assert.assertTrue(pear.getType().name().equals("PEAR"));
		Assert.assertTrue(pear.getType().getSecondsDelay() == 0);
		Assert.assertTrue(pear.getType().getPoints() == 20);
		Assert.assertTrue(pear.getType().getTailExtension() == 1);
		Assert.assertTrue(pear.getType().getImage() != null);
		
		Fruit bannana = new Fruit(-100, -100 , FruiteType.BANANA);
		Assert.assertTrue(bannana.getType().equals(FruiteType.BANANA) );
		Assert.assertTrue(bannana.getType().name().equals("BANANA"));
		Assert.assertTrue(bannana.getType().getSecondsDelay() == 10);
		Assert.assertTrue(bannana.getType().getPoints() == 15);
		Assert.assertTrue(bannana.getType().getTailExtension() == 1);
		Assert.assertTrue(bannana.getType().getImage() != null);



	}
	

}