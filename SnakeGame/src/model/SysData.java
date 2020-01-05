package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import utilities.Level;

public class SysData {

	private static SysData singleton;
	private static ArrayList<Game> games;
	private static HashMap<Level, ArrayList<Question>> questions;
	private static DAO dataAccessObject;

	public SysData() {
		if (singleton == null) {

			singleton = this;
			dataAccessObject = new JsonDAO();
			reset();

		} else {
			System.out.println("data class must be a singltone !");
		}

	}


	public static void reset() {
		questions = new HashMap<Level, ArrayList<Question>>();
		ArrayList<Question> result = dataAccessObject.getQuestions();

		for (Level level : Level.values()) {
			ArrayList<Question> questionsOfLevel = new ArrayList<Question>();
			for (Question q : result) {
				if (q.getLevel().equals(level))
					questionsOfLevel.add(q);
			}
			questions.put(level, questionsOfLevel);
		}

		games = dataAccessObject.getGames();

	}
	public static ArrayList<Game> getGames() {
		return games;
	}

	public static void addGame(Game game) {
		if (game != null)
			games.add(game);
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

	public static ArrayList<Question> getQuestions() {

		ArrayList<Question> allQuestions = new ArrayList<Question>();
		for (ArrayList<Question> questionsOfLevel : questions.values())
			allQuestions.addAll(questionsOfLevel);

		return allQuestions;

	}

	/**
	 * this method saves the changes to the .json files
	 * 
	 * @return true if data was saved ,false otherwise
	 */
	public static boolean Save() {

		try {
			dataAccessObject.writeGames(games);
			ArrayList<Question> allQuestions = new ArrayList<Question>();
			for (ArrayList<Question> questionsOfLevel : questions.values())
				allQuestions.addAll(questionsOfLevel);
			dataAccessObject.writeQuestions(allQuestions);

			reset();

			return true;

		} catch (Exception e) {

			System.out.println("somthing went wrong data was not saved");

		}
		return false;

	}

}
