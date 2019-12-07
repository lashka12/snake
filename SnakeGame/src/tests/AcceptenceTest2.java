package tests;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.Assert;

import model.Fruit;
import model.Game;
import model.Mouse;
import model.PlayGround;
import model.Question;
import model.Segment;
import model.Snake;
import utilities.FruiteType;
import utilities.Level;

public class AcceptenceTest2 {
	@Test
	public static void runTests() {
		
		// Testing Class PlayGround
		 int w=100, h=100;
		 Snake snake=new Snake(5);
		 int score=10;
		 PlayGround playground=new PlayGround(w,h);
		 playground.addSnake(snake);
		 Assert.assertTrue( !playground.getSnake().equals(null));
		 playground.setScore(score);
		 Assert.assertTrue(playground.getScore()==score);
		 playground.addToScore(5);
		 Assert.assertTrue(playground.getScore()==score+5);
		 Assert.assertTrue(playground.getH()==h);
		 Assert.assertTrue(playground.getW()==w);
		 
		 // Testing Class Game
		 String nickName ="Natalie";
		 Date date=new Date();
		 int scoreGame=15;
	     double duration=20;
	     Game game=new Game(nickName,date,scoreGame,duration);
		 game.setNickName(nickName);
		 Assert.assertTrue( game.getNickName().equals(nickName));
		 game.setDate(date);
		 Assert.assertTrue( game.getDate().equals(date));
		 game.setScore(scoreGame);
		 Assert.assertTrue( game.getScore()==(scoreGame));
		 game.setDuration(duration);
		 Assert.assertTrue( game.getDuration()==(duration));
		 
		// Testing Class Questions
		 String content="What is the software design pattern?";
		 Level level=Level.EASY;
		 ArrayList<String> answers = null;
		 String correctAnswer="MVC";
		  String team="Perhana";
		  answers.add("MVC");
		  answers.add("UML");
		  answers.add("API");
		  answers.add("DNS");
		  Question ques=new Question( content,  level,  answers,  correctAnswer,  team);
		  ques.setContent(content);
	     Assert.assertTrue(ques.getContent().equals(content));
	     ques.setcorrectAnswer(correctAnswer);
	     Assert.assertTrue(ques.getcorrectAnswer().equals(correctAnswer));
	     ques.setLevel(level);
	     Assert.assertTrue( ques.getLevel().equals(level));
	     ques.setTeam(team);
	     Assert.assertTrue( ques.getTeam().equals(team));
	     ques.setAnswers(answers);
	     Assert.assertTrue( ques.getAnswers().equals(answers));
		 
		 
		 
		  
		 
		 
		 
	}
	}