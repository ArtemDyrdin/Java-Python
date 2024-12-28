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

public class IncomeWindowController {
    @FXML
    private Button addIncomeBtn;
    @FXML
    private TextField incomeAmountInput;
    @FXML
    private ChoiceBox<String> incomeChoiceInput;
    @FXML
    private DatePicker incomeDateInput;
    @FXML
    private ChoiceBox<String> personChoiceInput;

    @FXML
    public void initialize() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        // загрузки
        loadIncomes(dbHandler);
        loadFamilyMembers(dbHandler);

        addIncomeBtn.setOnAction(actionEvent -> {
            try {
                addIncome(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) addIncomeBtn.getScene().getWindow();
            stage.close();
        });
    }
    private void loadIncomes(DatabaseHandler dbHandler) throws SQLException {
        ObservableList<String> origins = FXCollections.observableArrayList();
        ResultSet rs = dbHandler.getOrigins();
        while (rs.next()) {
            origins.add(rs.getString(Const.Expences.EXPENCE));
        }
        incomeChoiceInput.setItems(origins);
    }

    private void loadFamilyMembers(DatabaseHandler dbHandler) throws SQLException {
        ObservableList<String> familyMembers = FXCollections.observableArrayList();
        ResultSet rs = dbHandler.getPersons();
        while (rs.next()) {
            familyMembers.add(rs.getString(Const.Persons.NAME));
        }
        personChoiceInput.setItems(familyMembers);
    }

    private void addIncome(DatabaseHandler dbHandler) throws SQLException {
        String amount = incomeAmountInput.getText();
        String origin = incomeChoiceInput.getValue();
        String familyMember = personChoiceInput.getValue();
        LocalDate date = incomeDateInput.getValue();

        dbHandler.addIncome(Integer.parseInt(amount), origin, familyMember, date);
    }
}
