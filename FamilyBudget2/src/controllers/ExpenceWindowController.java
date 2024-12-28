package controllers;

import db.Const;
import db.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExpenceWindowController {
    @FXML
    private Button addExpenceBtn;
    @FXML
    private ChoiceBox<String> expenceChoiceInput;
    @FXML
    private DatePicker expenceDateInput;
    @FXML
    private TextField expenxeAmountInput;
    @FXML
    private ChoiceBox<String> personChoiceInput;

    @FXML
    public void initialize() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        // загрузки
        loadExpences(dbHandler);
        loadFamilyMembers(dbHandler);

        addExpenceBtn.setOnAction(actionEvent -> {
            try {
                addExpence(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) addExpenceBtn.getScene().getWindow();
            stage.close();
        });
    }

    private void loadExpences(DatabaseHandler dbHandler) throws SQLException {
        ObservableList<String> spheres = FXCollections.observableArrayList();
        ResultSet rs = dbHandler.getExpences();
        while (rs.next()) {
            spheres.add(rs.getString(Const.Expences.EXPENCE));
        }
        expenceChoiceInput.setItems(spheres);
    }

    private void loadFamilyMembers(DatabaseHandler dbHandler) throws SQLException {
        ObservableList<String> familyMembers = FXCollections.observableArrayList();
        ResultSet rs = dbHandler.getPersons();
        while (rs.next()) {
            familyMembers.add(rs.getString(Const.Persons.NAME));
        }
        personChoiceInput.setItems(familyMembers);
    }

    private void addExpence(DatabaseHandler dbHandler) throws SQLException {
        String amount = expenxeAmountInput.getText();
        String sphere = expenceChoiceInput.getValue();
        String familyMember = personChoiceInput.getValue();
        LocalDate date = expenceDateInput.getValue();

        dbHandler.addExpenceL(Integer.parseInt(amount), sphere, familyMember, date);
    }
}
