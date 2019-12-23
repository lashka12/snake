package tests;

import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * we used this class to make sure that the main thread of the javafX runs
 * before starting the tests because the tests includes constructing javaFX
 * components (ImageView,Pane,...) and we can't access these components before
 * the main javaFX thread starts so this class initialize JavaFX thread before
 * testing .
 * 
 * @author lawrence
 *
 */

public class JavaFXThreadingRule implements TestRule {

	private static boolean jfxIsSetup;

	@Override
	public Statement apply(Statement statement, Description description) {

		return new OnJFXThreadStatement(statement);
	}

	private static class OnJFXThreadStatement extends Statement {

		private final Statement statement;

		public OnJFXThreadStatement(Statement aStatement) {
			statement = aStatement;
		}

		private Throwable rethrownException = null;

		@Override
		public void evaluate() throws Throwable {

			if (!jfxIsSetup) {
				setupJavaFX();

				jfxIsSetup = true;
			}

			final CountDownLatch countDownLatch = new CountDownLatch(1);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						statement.evaluate();
					} catch (Throwable e) {
						rethrownException = e;
					}
					countDownLatch.countDown();
				}
			});

			countDownLatch.await();

			if (rethrownException != null) {
				throw rethrownException;
			}
		}

		protected void setupJavaFX() throws InterruptedException {

			final CountDownLatch latch = new CountDownLatch(1);

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// initializes JavaFX
					new JFXPanel();

					latch.countDown();
				}
			});

			latch.await();
		}

	}
}