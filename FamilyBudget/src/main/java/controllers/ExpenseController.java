package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ExpenseController {
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<String> categoryBox;
    @FXML
    private ComboBox<String> familyMemberBox;
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
        String category = categoryBox.getValue();
        String member = familyMemberBox.getValue();
        String date = datePicker.getValue().toString();

        System.out.println("Расход добавлен: Сумма " + amount + ", Категория " + category + ", Член семьи " + member + ", Дата " + date);
    }
}