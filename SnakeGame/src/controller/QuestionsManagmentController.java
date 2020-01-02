package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import model.Question;
import model.SysData;
import utilities.Constants;
import utilities.Level;

public class QuestionsManagmentController implements Initializable {

	@FXML
	private StackPane root;

	@FXML
	private TextArea newContent;

	@FXML
	private TextArea newAns1;

	@FXML
	private TextArea newAns2;

	@FXML
	private TextArea newAns3;

	@FXML
	private TextArea newAns4;

	@FXML
	private ComboBox<Integer> newCorrectAnswer;

	@FXML
	private ComboBox<String> newLevel;

	@FXML
	private TableView<Question> questionsTbl;

	@FXML
	private TableColumn<?, ?> content;

	@FXML
	private TableColumn<?, ?> ans1;

	@FXML
	private TableColumn<?, ?> ans2;

	@FXML
	private TableColumn<?, ?> ans3;

	@FXML
	private TableColumn<?, ?> ans4;

	@FXML
	private TableColumn<?, ?> level;

	@FXML
	private TableColumn<?, ?> team;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

		for (Level l : Level.values())
			newLevel.getItems().add(l.toString());
		newCorrectAnswer.getItems().add(1);
		newCorrectAnswer.getItems().add(2);
		newCorrectAnswer.getItems().add(3);

		questionsTbl.setFixedCellSize(90.0);
		fillQuestionsTbl();

	}

	@FXML
	void addQuestion(ActionEvent event) {

//		String text = newContent.getText();
//		String[] lines = text.split(System.getProperty("line.separator"));
//		String content = "";
//		for (int i = 0; i < lines.length; i++) {
//			content = content + "\n" + lines[i];
//		}
//		System.out.println(content);

		// check for empty and null with try and catch on the hole code piece

		ArrayList<String> answers = new ArrayList<String>();
		answers.add(newAns1.getText());
		answers.add(newAns2.getText());
		answers.add(newAns3.getText());
		answers.add(newAns4.getText());

		Question questionToAdd = new Question(newContent.getText(), Level.valueOf(newLevel.getValue().toString()),
				answers, newCorrectAnswer.getValue() + "", "Piranha");

		SysData.addQuestion(questionToAdd);
		SysData.Save();

		fillQuestionsTbl(); // refresh the table after adding question

		newContent.setText("");
		newAns1.setText("");
		newAns2.setText("");
		newAns3.setText("");
		newAns4.setText("");
		newCorrectAnswer.setValue(null);
		newLevel.setValue(null);

	}

	public void fillQuestionsTbl() {

		ObservableList<Question> data;

		data = FXCollections.observableArrayList();

		for (Question obj : SysData.getQuestions()) {
			data.add(obj);

		}

		content.setCellValueFactory(new PropertyValueFactory<>("content"));
		ans1.setCellValueFactory(new PropertyValueFactory<>("ans1"));
		ans2.setCellValueFactory(new PropertyValueFactory<>("ans2"));
		ans3.setCellValueFactory(new PropertyValueFactory<>("ans3"));
		ans4.setCellValueFactory(new PropertyValueFactory<>("ans4"));
		level.setCellValueFactory(new PropertyValueFactory<>("level"));
		team.setCellValueFactory(new PropertyValueFactory<>("team"));

		questionsTbl.setItems(data);

		if (questionsTbl.getColumns().size() == 7) {

			TableColumn<Question, Void> deleteCol = new TableColumn<>();
			deleteCol.setPrefWidth(50);
			deleteCol.setMinWidth(50);
			deleteCol.setMaxWidth(50);

			Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
				@Override
				public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
					final TableCell<Question, Void> cell = new TableCell<Question, Void>() {

						private final JFXButton deleteBtn = new JFXButton();

						private final VBox pane = new VBox(deleteBtn);

						{
							pane.setAlignment(Pos.CENTER);
							deleteBtn.getStyleClass().add("deleteBtn");
							deleteBtn.setCursor(Cursor.HAND);
							deleteBtn.setOnAction((ActionEvent event) -> {
								Question data = getTableView().getItems().get(getIndex());
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Delete Question");
								alert.setHeaderText("Are you sure want to delete this question ?");
								alert.setContentText(data.getContent());
								Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
								stage.getIcons().add(Constants.STOP_IMAGE);
								Optional<ButtonType> option = alert.showAndWait();
								if (option.get() == ButtonType.OK) {
									SysData.deleteQuestion(data);
									SysData.Save();
									fillQuestionsTbl();

								}
							});

						}

						@Override
						protected void updateItem(Void item, boolean empty) {
							super.updateItem(item, empty);

							setGraphic(empty ? null : pane);
						}
					};
					return cell;
				}
			};

			deleteCol.setCellFactory(cellFactory);
			questionsTbl.getColumns().add(deleteCol);

		}

	}

}
