package model;

/**
 * this iterator is used whenever we want to iterate over a set of Segments.
 * 
 * if we changed the data structure that holds the segments we will not need to
 * change the iteration technique in all the places where we used the set , we
 * can iterate over it the same way with iterator
 * 
 * @author Lawrence Ashkar
 *
 */
public interface SegmentIterator {
	/**
	 * initialize the iterator
	 */
	void first();

	/**
	 * this method update the next entry for the iterator
	 */
	void next();

	/**
	 * checks if segments are done
	 * 
	 * @return true if it is true false otherwise
	 */
	boolean isDone();

	/**
	 * 
	 * @return the current value of the iterator
	 */
	Segment currentValue();

}
