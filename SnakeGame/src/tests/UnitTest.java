package tests;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import model.Game;
import model.Question;
import model.Snake;
import model.SysData;
import utilities.JsonProcessor;
import utilities.Level;

public class UnitTest {
	
	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
	
	
	
	
	@Test
	public void GameClassTest() {

		
		String nickName = "HadiSam";
		Date date = new Date();
		int scoreGame = 15;
		double duration = 20;
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		Game game = new Game(nickName, date, scoreGame, duration,hs);
		game.setNickName(nickName);
		Assert.assertTrue(game.getNickName().equals(nickName));
		game.setDate(date);
		Assert.assertTrue(game.getDate().equals(date));
		game.setScore(scoreGame);
		Assert.assertTrue(game.getScore() == (scoreGame));
		game.setDuration(duration);
		Assert.assertTrue(game.getDuration() == (duration));
		game.setEatenObjects(hs);
		Assert.assertTrue(game.getEatenObjects().equals(hs));

	}
	
	
	@Test
	public void QuestionTest()
	{
		
		String content = "What's my name?";
		Level l = Level.EASY;
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Heisenberg");
		answers.add("Ameer");
		answers.add("Messi");
		answers.add("Lorans");
		String correct = "Heisenberg";
		String team = "Piranha";
		Question q = new Question(content,l,answers,correct,team);
		Assert.assertNotNull(q);
		Assert.assertFalse(q.isEaten()); // we just make the question so its not eaten yet!
		Assert.assertEquals(correct,q.getcorrectAnswer());	
	}
	

	
	 
	@Test
	public void SysDataClassTest() {

		SysData sys = new SysData();
		String content = "What's my name?";
		Level l = Level.EASY;
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Heisenberg");
		answers.add("Ameer");
		answers.add("Messi");
		answers.add("Lorans");
		String correct = "Heisenberg";
		String team = "Piranha";
		Question q = new Question(content,l,answers,correct,team);
		
		SysData.addQuestion(q);
		
		Question s = sys.popRandomQuestion(l);
		Assert.assertNotNull(s);

	}
	
	
	@Test
	public void SnakeClassTest() {
		SysData sys = new SysData();
		int length = 9;
		Snake snake = new Snake(length);
		Assert.assertNotNull(snake.getBody());
		snake.addSegment();
		Assert.assertTrue(snake.getBody().size()== length + 1);
	}
	
	@Test
	public void JsonReaderClassTest() {

		ArrayList<Question> result = new ArrayList<Question>();
		result = JsonProcessor.readQuestionsFile();
		Assert.assertTrue(result.size() != 0);
	}
	

}
