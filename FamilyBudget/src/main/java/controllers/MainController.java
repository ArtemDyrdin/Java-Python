package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    public Button mainPageButton;
    @FXML
    private Label totalMoneyLabel;
    @FXML
    private Label spendingGoalLabel;
    @FXML
    private Button addExpenseButton;
    @FXML
    private Button addIncomeButton;
    @FXML
    private Button settingsPageButton;
    @FXML
    private void initialize() {
        // Логика инициализации
        totalMoneyLabel.setText("Всего денег: 0 руб.");
        spendingGoalLabel.setText("Цели расходов: Неделя 0 руб. | Месяц 0 руб.");

        addExpenseButton.setOnAction(e -> openExpensePage());
        addIncomeButton.setOnAction(e -> openIncomePage());
        settingsPageButton.setOnAction(e -> openSettingsPage());
    }

    private void openExpensePage() {
        System.out.println("Открытие окна добавления расходов");
        // Реализовать логику перехода
    }

    private void openIncomePage() {
        System.out.println("Открытие окна добавления доходов");
        // Реализовать логику перехода
    }

    private void openSettingsPage() {
        System.out.println("Открытие окна настроек");
        // Реализовать логику перехода
    }
}