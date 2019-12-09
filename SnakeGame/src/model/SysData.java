package model;

import java.util.ArrayList;
import java.util.Random;
import utilities.JsonProcessor;

public class SysData {

	private static SysData instance;
	private ArrayList<Game> gamesHistory;
	private static ArrayList<Question> questions;

	public static SysData getInstance() {
		return instance;
	}

	public SysData() {
		if (instance == null) {
			instance = this;
		}

		questions = JsonProcessor.readQuestionsFile();
		gamesHistory = JsonProcessor.readGamesFile();

	}

	public ArrayList<Game> getGames() {
		return gamesHistory;
	}

	/**
	 * this method adds a new question to the data
	 * 
	 * @param question : the question to be added
	 * @return true if question was added successfully , false otherwise
	 */
	public static boolean addQuestion(Question question) {

		if (question != null) {
			if (!questions.contains(question)) {
				questions.add(question);
				return true;
			}
		}
		return false;
	}

	public static void UpdateQuestion(Question question) {

		if (question != null) {
			questions.remove(question);
			questions.add(question);

		}

	}

	public static void deleteQuestion(Question question) {
		if (question != null) {
			questions.remove(question);

		}
	}

	/**
	 * this method generate a random question from the questions list
	 * 
	 * @return
	 */
	public static Question popRandomQuestion() {

		Random rand = new Random();
		return questions.get(rand.nextInt(questions.size()));

	}

	/**
	 * this method saves the changes to the .json files
	 * 
	 * @return true if data was saved ,false otherwise
	 */
	public static boolean Save() {

		return false;
	}

	public static ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setGamesHistory(ArrayList<Game> gamesHistory) {
		this.gamesHistory = gamesHistory;
	}

}
