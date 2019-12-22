package model;

import java.util.ArrayList;
import utilities.Level;

public class Question extends Block {

	private String content;
	private Level level;
	private ArrayList<String> answers;
	private String correctAnswer;
	private String team;
	private boolean eaten;

	public Question(String content, Level level, ArrayList<String> answers, String correctAnswer, String team) {

		super(0, 0);
		this.content = content;
		this.level = level;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.team = team;
		this.eaten = false;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public String getcorrectAnswer() {
		return correctAnswer;
	}

	public void setcorrectAnswer(String correct_ans) {
		this.correctAnswer = correct_ans;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [ x=" + getX() + ", y=" + getY() + ", content=" + content + ", level=" + level + ", answers="
				+ answers + ", correctAnswer=" + correctAnswer + ", team=" + team + "]";
	}

}