package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import utilities.JsonProcessor;
import utilities.Level;

public class SysData {

	private static SysData instance;
	private static ArrayList<Game> games;
	private static HashMap<Level, ArrayList<Question>> questions;

	public SysData() {
		if (instance == null) {
			instance = this;

			questions = new HashMap<Level, ArrayList<Question>>();
			for (Level level : Level.values()) {
				ArrayList<Question> questionsOfLevel = new ArrayList<Question>();
				for (Question q : JsonProcessor.readQuestionsFile()) {

					if (q.getLevel().equals(level))
						questionsOfLevel.add(q);
				}
				questions.put(level, questionsOfLevel);
			}

			games = JsonProcessor.readGamesFile();

		} else {
			System.out.println("data class must be a singltone !");
		}

	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public static boolean addGame(Game game) {

		if (game != null) {
			if (!games.contains(game)) {
				games.add(game);
				return true;
			}
		}
		return false;
	}

	/**
	 * this method adds a new question to the data
	 * 
	 * @param question : the question to be added
	 * @return true if question was added successfully , false otherwise
	 */
	public static boolean addQuestion(Question question) {

		if (question != null) {
			if (!questions.get(question.getLevel()).contains(question)) {
				questions.get(question.getLevel()).add(question);
				return true;
			}
		}
		return false;
	}

	public static void updateQuestion(Question question) {

		if (question != null) {
			questions.get(question.getLevel()).remove(question);
			questions.get(question.getLevel()).add(question);

		}

	}

	public static void deleteQuestion(Question question) {
		if (question != null) {
			questions.get(question.getLevel()).remove(question);

		}
	}

	/**
	 * this method generate a random question from the questions data
	 * 
	 * @return
	 */
	public static Question popRandomQuestion(Level level) {

		Random rand = new Random();
		return questions.get(level).get(rand.nextInt(questions.get(level).size()));

	}

	/**
	 * this method saves the changes to the .json files
	 * 
	 * @return true if data was saved ,false otherwise
	 */
	public static boolean Save() {

		return false;
	}

}
