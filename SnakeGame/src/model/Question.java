package model;

import java.util.ArrayList;
import utilities.Level;

/**
 * this class represents a question's logic in the game
 * 
 * @author L.A
 *
 */
public class Question extends Block {

	private String content;
	private String ans1;
	private String ans2;
	private String ans3;
	private String ans4;
	private String correctAnswer;
	private Level level;
	private String team;

	// to be used later in the view tables
	private String contentAsLines;
	private String ans1AsLines;
	private String ans2AsLines;
	private String ans3AsLines;
	private String ans4AsLines;

	private boolean eaten;

	/**
	 * full constructor
	 * 
	 * @param content       - question's content
	 * @param level         - question's level
	 * @param answers       - question's answers
	 * @param correctAnswer - question's right answer
	 * @param team          - the team who wrote the question
	 */
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

		contentAsLines = makeAsLines(content);
		ans1AsLines = makeAsLines(ans1);
		ans2AsLines = makeAsLines(ans2);
		ans3AsLines = makeAsLines(ans3);
		ans4AsLines = makeAsLines(ans4);

	}

	/**
	 * this method generate string as lines , it was used to make the text in the
	 * table more human friendly
	 * 
	 * @param str string we wish to manipulate
	 * @return string as lines (4 words a line)
	 */
	private String makeAsLines(String str) {

		String[] wordsArray = str.split(" ");
		int i = 0;
		String StringAsLines = "";
		for (String word : wordsArray) {
			if (i == 4) {
				StringAsLines = StringAsLines + "\n";
				i = 0;
			}
			StringAsLines += " " + word;
			i++;
		}
		return StringAsLines;
	}

	public String getContentAsLines() {
		return contentAsLines;
	}

	public String getAns1AsLines() {
		return ans1AsLines;
	}

	public String getAns2AsLines() {
		return ans2AsLines;
	}

	public String getAns3AsLines() {
		return ans3AsLines;
	}

	public String getAns4AsLines() {
		return ans4AsLines;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
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