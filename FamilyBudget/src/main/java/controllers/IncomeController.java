package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class IncomeController {
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<String> familyMemberBox;
    @FXML
    private ComboBox<String> familyMemberIncomes;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        submitButton.setOnAction(e -> addExpense());
    }

    private void addExpense() {
        String amount = amountField.getText();
        String member = familyMemberBox.getValue();
        String income = familyMemberIncomes.getValue();
        String date = datePicker.getValue().toString();

        System.out.println("Доход добавлен: Сумма " + amount + ", Член семьи " + member + ", Источник " + income + ", Дата " + date);
    }
}