package model;

import java.util.ArrayList;
import utilities.Constants;
import utilities.Direction;

/**
 * Snake class is an object that contains a body which is a set of segments and
 * a head
 * 
 * @author L.A
 *
 */
public class Snake {

	private ArrayList<Segment> body;
	private Segment head;

	/**
	 * full constructor
	 * 
	 * @param length - the snake initial length
	 */
	public Snake(int length) {

		body = new ArrayList<Segment>();
		int ipx = Constants.GAME_WIDTH / 2;
		int ipy = Constants.GAME_HIGHT / 2;
		Segment.restartCounter();

		head = new Segment(ipx, ipy, null);
		body.add(head);

		Segment previous = head;
		for (int i = 1; i < length; i++) {

			Segment newSegment = new Segment(ipx + i, ipy, previous);
			body.add(newSegment);
			previous = newSegment;

		}

	}

	/**
	 * this method adds a new segment to the snake's body
	 */
	public void addSegment() {

		Segment previous = body.get(body.size() - 1);
		Segment tail = new Segment(previous.getX() + 1, previous.getY() + 1, previous);
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

	public Segment getHead() {
		return head;
	}

	public SegmentIteratorImpl getIterator() {
		return new SegmentIteratorImpl(this);
	}

	@Override
	public String toString() {
		return "Snake [body=" + body + ", head=" + head + "]";
	}

}
