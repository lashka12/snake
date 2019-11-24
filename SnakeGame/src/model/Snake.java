package model;

import java.util.ArrayList;
import utilities.Constants;
import utilities.Direction;

public class Snake {

	private ArrayList<Segment> body;
	private Segment head;

	public Snake(int length) {

		body = new ArrayList<Segment>();
		int ipx = Constants.GAME_WIDTH / 2; // in center
		int ipy = Constants.GAME_HIGHT / 2; // in center

		head = new Segment(ipx, ipy, null);
		body.add(head);

		Segment previous = head;
		for (int i = 1; i < length; i++) {

			Segment newSegment = new Segment(ipx + i, ipy, previous);
			body.add(newSegment);
			previous = newSegment;

		}

	}

	public void addSegment() {

		Segment previous=body.get(body.size()-1);
		Segment tail = new Segment(previous.getPosX()+1, previous.getPosY()+1, previous);
		body.add(tail);

	}

	public Direction getDirection() {
		return head.getDirection();
	}

	public void setDirection(Direction direction) {
		head.setDirection(direction);
	}

	public ArrayList<Segment> getBody() {
		return body;
	}

	public void setBody(ArrayList<Segment> body) {
		this.body = body;
	}

	public Segment getHead() {
		return head;
	}

	public void setHead(Segment head) {
		this.head = head;
	}

}
