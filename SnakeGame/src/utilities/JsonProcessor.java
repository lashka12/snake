package utilities;

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
import model.Game;
import model.Question;

/*
 * this class will write the games history to JSON file
 */
public class JsonProcessor {

	@SuppressWarnings("unchecked")
	public static void writeGameHistory(ArrayList<Game> gamesHistory) {

		JSONArray array = new JSONArray();
		JSONObject gameDetails = new JSONObject();
		JSONObject gamesJson = new JSONObject();

		for (Game gameToAdd : gamesHistory) {
			if (gameToAdd != null) {

				String nickName = gameToAdd.getNickName().toString();
				String score = String.valueOf(gameToAdd.getScore());
				String duaration = Double.toString(gameToAdd.getDuration());

				Date date = gameToAdd.getDate();
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String gameDate = simpleDateFormat.format(date);

				ArrayList<String> arr = new ArrayList<>();
				HashMap<String, Integer> map = gameToAdd.getEatenObjects();
				for (Entry<String, Integer> mapElement : map.entrySet()) {
					arr.add(mapElement.getKey() + "#" + mapElement.getValue());
				}

				gameDetails.put("playerName", nickName);
				gameDetails.put("date", gameDate);
				gameDetails.put("score", score);
				gameDetails.put("duaration", duaration);
				gameDetails.put("history", arr);

				// Write JSON file
				FileWriter file;
				try {
					file = new FileWriter("games.json");
					array.add(gameDetails);
					gamesJson.put("games", array);
					file.write(gamesJson.toJSONString());
					file.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public static ArrayList<Question> readQuestionsFile() {

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
			// TODO Auto-generated catch block
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

				JSONObject game = (JSONObject) o;
				String playerName = game.get("playerName").toString();
				String date = game.get("date").toString();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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

}
