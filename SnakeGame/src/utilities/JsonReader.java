package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Game;
import model.Question;

public class JsonReader {

	public static ArrayList<Question> readQuestionsFile() {

		ArrayList<Question> questions = new ArrayList<Question>();
		try {

			Object obj = new JSONParser().parse(new FileReader("questions.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("questions");

			for (Object o : arr) {
				JSONObject question = (JSONObject) o;
				String content = (String) question.get("question");
				JSONArray answers = (JSONArray) question.get("answers");
				ArrayList<String> qs = (ArrayList<String>) answers;
				String correct = (String) question.get("correct_ans");
				Integer level = Integer.valueOf((String) question.get("level"));

				Level lvl;
				if (level == 1)
					lvl = Level.EASY;
				if (level == 2)
					lvl = Level.INTERMEDIATE;
				else
					lvl = Level.HARD;

				String team = (String) question.get("team");
				Question q = new Question(content, lvl, qs, correct, team);
				questions.add(q);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return questions;
	}

	public static ArrayList<Game> readGamesFile() {
		ArrayList<Game> games = new ArrayList<Game>();
		try {

			Object obj = new JSONParser().parse(new FileReader("games.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("games");

				for (Object o : arr) {
				
//					JSONObject game = (JSONObject) o;
//					String playerName = (String) game.get("nickName");
//					Date gameDate = (Date) game.get("date");
				
	
					

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return games;
	}

}