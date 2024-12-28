package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class IncomeWindowController {

    @FXML
    private Button addIncomeBtn;

    @FXML
    private TextField incomeAmountInput;

    @FXML
    private ChoiceBox<?> incomeChoiceInput;

    @FXML
    private DatePicker incomeDateInput;

    @FXML
    private ChoiceBox<?> personChoiceInput;

}
