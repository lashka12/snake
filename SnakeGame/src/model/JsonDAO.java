package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utilities.Level;

/**
 * this class implements DAO interface it is used to process the data if the
 * given database is a Json file
 * 
 * @see DAO.java
 * @author L.A
 *
 */
public class JsonDAO implements DAO {

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	public ArrayList<Question> getQuestions() {

		ArrayList<Question> questions = new ArrayList<Question>();

		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader("questions.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("questions");

			for (Object o : arr) {
				JSONObject question = (JSONObject) o;
				String content = (String) question.get("question");
				JSONArray answers = (JSONArray) question.get("answers");
				@SuppressWarnings("unchecked")
				ArrayList<String> qs = (ArrayList<String>) answers;
				String correct = (String) question.get("correct_ans");
				Integer level = Integer.valueOf((String) question.get("level"));

				Level lvl = null;
				if (level == 1)
					lvl = Level.EASY;
				if (level == 2)
					lvl = Level.INTERMEDIATE;
				if (level == 3)
					lvl = Level.HARD;

				String team = (String) question.get("team");
				Question q = new Question(content, lvl, qs, correct, team);
				questions.add(q);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

	@SuppressWarnings("unchecked")

	@Override
	public void writeQuestions(ArrayList<Question> questions) {

		try {
			JSONArray array = new JSONArray();
			JSONObject quesJson = new JSONObject();
			JSONObject quesDetails;
			for (Question quesToAdd : questions) {
				quesDetails = new JSONObject();
				quesDetails.put("question", quesToAdd.getContent());

				String levelAsString = "";
				switch (quesToAdd.getLevel()) {
				case EASY:
					levelAsString = "1";
					break;
				case INTERMEDIATE:
					levelAsString = "2";
					break;
				case HARD:
					levelAsString = "3";
					break;
				}
				ArrayList<String> answers = new ArrayList<String>();
				answers.add(quesToAdd.getAns1());
				answers.add(quesToAdd.getAns2());
				answers.add(quesToAdd.getAns3());
				answers.add(quesToAdd.getAns4());

				quesDetails.put("answers", answers);
				quesDetails.put("correct_ans", quesToAdd.getCorrectAnswer());
				quesDetails.put("level", levelAsString);
				quesDetails.put("team", quesToAdd.getTeam());
				array.add(quesDetails);
			}
			quesJson.put("questions", array);
			FileWriter file;
			file = new FileWriter("questions.json");
			file.write(quesJson.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Game> getGames() {
		ArrayList<Game> games = new ArrayList<Game>();
		try {

			Object obj = new JSONParser().parse(new FileReader("games.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("games");

			for (Object o : arr) {

				JSONObject game = (JSONObject) o;
				String playerName = game.get("playerName").toString();
				String date = game.get("date").toString();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date gameDate = format.parse(date);

				int score = Integer.parseInt(game.get("score").toString());
				double dur = Double.parseDouble(game.get("duaration").toString());

				JSONArray history = (JSONArray) game.get("history");
				@SuppressWarnings("unchecked")
				ArrayList<String> gArray = (ArrayList<String>) history;
				HashMap<String, Integer> gamesHistory = new HashMap<>();

				for (String str : gArray) {
					String[] mapValues = str.split("#");
					Integer val = Integer.parseInt(mapValues[1]);
					gamesHistory.put(mapValues[0], val);
				}

				Game gm = new Game(playerName, gameDate, score, dur, gamesHistory);
				games.add(gm);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return games;
	}

	@SuppressWarnings("unchecked")

	@Override
	public void writeGames(ArrayList<Game> gamesHistory) {

		try {
			JSONArray array = new JSONArray();
			JSONObject gamesJson = new JSONObject();
			JSONObject gameDetails;
			for (Game gameToAdd : gamesHistory) {
				gameDetails = new JSONObject();
				ArrayList<String> arr = new ArrayList<>();
				HashMap<String, Integer> map = gameToAdd.getEatenObjects();
				for (Entry<String, Integer> mapElement : map.entrySet())
					arr.add(mapElement.getKey() + "#" + mapElement.getValue());
				gameDetails.put("playerName", gameToAdd.getNickName().toString());
				gameDetails.put("date", simpleDateFormat.format(gameToAdd.getDate()));
				gameDetails.put("score", String.valueOf(gameToAdd.getScore()));
				gameDetails.put("duaration", gameToAdd.getDuration() + "");
				gameDetails.put("history", arr);
				array.add(gameDetails);
			}
			gamesJson.put("games", array);
			FileWriter file;
			file = new FileWriter("games.json");
			file.write(gamesJson.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
