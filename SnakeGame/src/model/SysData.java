package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import utilities.JsonReader;
import utilities.JsonWriter;

public class SysData {

	private static SysData instance;
	private ArrayList<Game> gamesHistory;
	private static ArrayList<Question> questions;

	public static SysData getInstance() {
		return instance;
	}

	public SysData() throws ParseException, org.json.simple.parser.ParseException {
		if (instance == null) {
			instance = this;
		}

		questions = JsonReader.readQuestionsFile();
		System.out.println(questions);

		gamesHistory=JsonReader.readGamesFile(); 
		System.out.println(gamesHistory);

	
		JsonWriter.writeGameHistory(gamesHistory);
		//System.out.println(gamesHistory);

		// this code line

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

	public ArrayList<Game> getGamesHistory() {
		return gamesHistory;
	}

	public static ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setGamesHistory(ArrayList<Game> gamesHistory) {
		this.gamesHistory = gamesHistory;
	}

}
