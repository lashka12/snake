package model;

import java.util.ArrayList;
import utilities.Level;

public class Question extends Block {

	private String content;
	private Level level;
	private String ans1;
	private String ans2;
	private String ans3;
	private String ans4;
	private String correctAnswer;
	private String team;
	private boolean eaten;

	public Question(String content, Level level, ArrayList<String> answers, String correctAnswer, String team) {

		super(0, 0);
		this.content = content;
		this.level = level;
		this.ans1 = answers.get(0);
		this.ans2 = answers.get(1);
		this.ans3 = answers.get(2);
		this.ans4 = answers.get(3);
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

	@Override
	public String toString() {
		return "Question [ x=" + getX() + ", y=" + getY() + ", content=" + content + ", level=" + level
				+ ", correctAnswer=" + correctAnswer + ", team=" + team + "]";
	}

	public String getAns1() {
		return ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public String getAns4() {
		return ans4;
	}

}