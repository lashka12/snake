package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.Question;
import model.SysData;
import utilities.Level;
import view.Alerts;

/**
 * this class controls the PausePage.MainPage.FXML page , initialize it and
 * handle it's actions
 * 
 * objects with @FXML sign are references to the graphic components of the FXML
 * file
 * 
 * methods with @FXML sign are the methods used to handle actions triggered by
 * the FXML File
 * 
 * @author Lawrence Ashkar
 *
 */

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
	private TableColumn<Question, String> theAnswer;
	@FXML
	private TableColumn<Question, String> content;
	@FXML
	private TableColumn<Question, String> ans1;
	@FXML
	private TableColumn<Question, String> ans2;
	@FXML
	private TableColumn<Question, String> ans3;
	@FXML
	private TableColumn<Question, String> ans4;
	@FXML
	private TableColumn<Question, String> level;
	@FXML
	private TableColumn<Question, String> team;

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
		newCorrectAnswer.getItems().add(4);
		questionsTbl.setFixedCellSize(150.0);
		fillQuestionsTbl();

	}

	@FXML
	void addQuestion(ActionEvent event) {

		try {
			if (newAns1.getText().equals("") || newAns2.getText().equals("") || newAns3.getText().equals("")
					|| newAns4.getText().equals(""))
				throw new NullPointerException();

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
		} catch (Exception e) {
			Alerts.warning("missing fields , please make sure all required fields are filled out");

		}

	}

	public void fillQuestionsTbl() {

		ObservableList<Question> data;

		data = FXCollections.observableArrayList();

		for (Question obj : SysData.getQuestions()) {
			data.add(obj);

		}

		content.setCellValueFactory(new PropertyValueFactory<>("contentAsLines"));
		content.setCellFactory(TextFieldTableCell.forTableColumn());
		content.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {

				try {
					if (Alerts.update() == 1) {

						SysData.deleteQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setContent(t.getNewValue().trim());
						SysData.addQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						SysData.Save();
						Alerts.confirmation("updated");
					}
				} catch (Exception e) {
					System.out.println("no selection");
				}
				fillQuestionsTbl();

			}
		});

		ans1.setCellValueFactory(new PropertyValueFactory<>("ans1AsLines"));
		ans1.setCellFactory(TextFieldTableCell.forTableColumn());
		ans1.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {

				try {
					if (Alerts.update() == 1) {

						SysData.deleteQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setAns1(t.getNewValue().trim());
						SysData.addQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						SysData.Save();
						Alerts.confirmation("updated");
					}
				} catch (Exception e) {
					System.out.println("no selection");
				}
				fillQuestionsTbl();

			}
		});
		ans2.setCellValueFactory(new PropertyValueFactory<>("ans2AsLines"));
		ans2.setCellFactory(TextFieldTableCell.forTableColumn());
		ans2.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {

				try {
					if (Alerts.update() == 1) {

						SysData.deleteQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setAns2(t.getNewValue().trim());
						SysData.addQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						SysData.Save();
						Alerts.confirmation("updated");
					}
				} catch (Exception e) {
					System.out.println("no selection");
				}
				fillQuestionsTbl();

			}
		});
		ans3.setCellValueFactory(new PropertyValueFactory<>("ans3AsLines"));
		ans3.setCellFactory(TextFieldTableCell.forTableColumn());
		ans3.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {

				try {
					if (Alerts.update() == 1) {

						SysData.deleteQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setAns3(t.getNewValue().trim());
						SysData.addQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						SysData.Save();
						Alerts.confirmation("updated");
					}
				} catch (Exception e) {
					System.out.println("no selection");
				}
				fillQuestionsTbl();
			}
		});
		ans4.setCellValueFactory(new PropertyValueFactory<>("ans4AsLines"));
		ans4.setCellFactory(TextFieldTableCell.forTableColumn());
		ans4.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {

				try {
					if (Alerts.update() == 1) {

						SysData.deleteQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setAns4(t.getNewValue().trim());
						SysData.addQuestion(
								((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
						SysData.Save();
						Alerts.confirmation("updated");
					}
				} catch (Exception e) {
					System.out.println("no selection");
				}
				fillQuestionsTbl();

			}
		});
		theAnswer.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
		theAnswer.setCellFactory(TextFieldTableCell.forTableColumn());
		theAnswer.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {

				try {
					if (Alerts.update() == 1) {

						if (t.getNewValue().equals("1") || t.getNewValue().equals("2") || t.getNewValue().equals("3")
								|| t.getNewValue().equals("4")) {

							SysData.deleteQuestion(
									((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
							((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
									.setCorrectAnswer(t.getNewValue().trim());
							SysData.addQuestion(
									((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())));
							SysData.Save();
							Alerts.confirmation("updated");

						} else {
							Alerts.warning("Please make sure you choose a legal number");
						}

					}
				} catch (Exception e) {
					System.out.println("no selection");
				}
				fillQuestionsTbl();

			}
		});

		level.setCellValueFactory(new PropertyValueFactory<>("level"));
		team.setCellValueFactory(new PropertyValueFactory<>("team"));

		questionsTbl.setItems(data);

		if (questionsTbl.getColumns().size() == 8) {
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

								try {
									if (Alerts.delete(data.getContent()) == 1) {
										SysData.deleteQuestion(data);
										SysData.Save();
										fillQuestionsTbl();
										Alerts.confirmation("deleted");

									}
								} catch (Exception e) {
									System.out.println("no selection");
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

	@FXML
	void openFileChooser() {

		Alerts.warning("take it easy , we are still working on this feature :)");
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Choose .jar file");
//		Stage stg = new Stage();
//		stg.setAlwaysOnTop(true);
//		fileChooser.showOpenDialog(stg);

	}

}
