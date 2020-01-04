package model;

import java.util.ArrayList;

/**
 * DAO stands for Data Access Object. DAO Design Pattern is used to separate the
 * data persistence logic . This way, the service remains completely in dark
 * about how the low-level operations to access the database is done. This is
 * known as the principle of Separation of Logic. For example, if you shift from
 * .json mechanism to SQL Database, your change will be limited to data access
 * object and won't impact controller layer or model Objects and all you have to
 * do is to add a new SqlDAO that implements these methods
 * 
 * 
 * PROS:
 * 
 * 1.DAO design pattern keeps coupling low between different parts of an
 * application.
 * 
 * 2.DAO design pattern allows JUnit test to run faster as it allows to create
 * Mock and avoid connecting to database to run tests. It improves testing
 * because it's easy to write test with Mock objects, rather than an Integration
 * test with the database. In the case of any issue, while running Unit test,
 * you only need to check code and not database. Also shields with database
 * connectivity and environment issues.
 * 
 * 3.Since DAO pattern is based on interface, it also promotes Object oriented
 * design principle "programming for interface than implementation" which
 * results in flexible and quality code.
 * 
 * @see JsonDAO
 * @author Lawrence Ashkar
 *
 */
public interface DAO {

	ArrayList<Question> getQuestions();

	ArrayList<Game> getGames();

	void writeQuestions(ArrayList<Question> questions);

	void writeGames(ArrayList<Game> games);

	// other data manipulation methods can be added here and overridden

	// ArrayList<Question> getQuestionsByLevel();
	// ArrayList<Games> getGamesByNickName();
	// .......
	// .......

}
