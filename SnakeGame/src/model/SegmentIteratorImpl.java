package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * a concrete class implements SgementIterator to iterate over Segments set
 * 
 * @see SegmentIterator.java
 * @see design patterns.PDF for more information
 * 
 * @author L.A
 *
 */
public class SegmentIteratorImpl implements SegmentIterator {

	private Snake segmentsBox;
	private Iterator<Segment> iterator;
	private Segment value;

	/**
	 * constructor
	 * 
	 * @param segmentsBox segments list we wish to iterate over
	 */
	public SegmentIteratorImpl(Snake segmentsBox) {
		this.segmentsBox = segmentsBox;
	}

	/**
	 * initialize the iterator
	 */
	@Override
	public void first() {
		iterator = segmentsBox.getBody().iterator();
		next();
	}

	/**
	 * this method update the next entry for the iterator
	 */
	@Override
	public void next() {
		try {
			value = (Segment) iterator.next();
		} catch (NoSuchElementException ex) {
			value = null;
		}
	}

	/**
	 * checks if segments are done
	 * 
	 * @return true if it is true false otherwise
	 */
	@Override
	public boolean isDone() {
		return value == null;
	}

	/**
	 * 
	 * @return the current value of the iterator
	 */
	@Override
	public Segment currentValue() {
		return value;
	}
}
