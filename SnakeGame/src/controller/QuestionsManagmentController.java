package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Question;
import model.SysData;

public class QuestionsManagmentController implements Initializable {
	@FXML
	private JFXTextArea newContent;
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
		questionsTbl.setFixedCellSize(60.0);
		fillQuestionsTbl();

	}

	@FXML
	void addQuestion(ActionEvent event) {

		String text = newContent.getText();
		String[] lines = text.split(System.getProperty("line.separator"));
		String content = "";
		for (int i = 0; i < lines.length; i++) {
			content = content + "\n" + lines[i];
		}
		System.out.println(content);

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

			TableColumn<Question, Void> deleteCol = new TableColumn<>("Action");
			deleteCol.setPrefWidth(100);
			deleteCol.setMinWidth(100);
			deleteCol.setMaxWidth(100);

			Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
				@Override
				public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
					final TableCell<Question, Void> cell = new TableCell<Question, Void>() {

						private final JFXButton deleteBtn = new JFXButton();

						private final HBox pane = new HBox(deleteBtn);

						{
							deleteBtn.getStyleClass().add("deleteBtn");

							deleteBtn.setOnAction((ActionEvent event) -> {
								Question data = getTableView().getItems().get(getIndex());

								System.out.println(data);
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
