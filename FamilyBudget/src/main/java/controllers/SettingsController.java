package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsController {
    @FXML
    private TextField budgetField;
    @FXML
    private Button setFamilyBudgetButton;

    @FXML
    private TextField spendingGoalWeekField;
    @FXML
    private Button setSpendingGoalWeekButton;

    @FXML
    private TextField spendingGoalMonthField;
    @FXML
    private Button setSpendingGoalMonthButton;

    @FXML
    private TextField familyMemberField;
    @FXML
    private Button addFamilyMemberButton;

    @FXML
    private TextField expenseCategoryField;
    @FXML
    private Button addExpenseCategoryButton;

    @FXML
    private void initialize() {
        setFamilyBudgetButton.setOnAction(e -> setBudget());
        setSpendingGoalWeekButton.setOnAction(e -> setWeeklyGoal());
        setSpendingGoalMonthButton.setOnAction(e -> setMonthlyGoal());
        addFamilyMemberButton.setOnAction(e -> addFamilyMember());
        addExpenseCategoryButton.setOnAction(e -> addExpenseCategory());
    }

    private void setBudget() {
        System.out.println("Установлен бюджет: " + budgetField.getText());
    }

    private void setWeeklyGoal() {
        System.out.println("Цель на неделю: " + spendingGoalWeekField.getText());
    }

    private void setMonthlyGoal() {
        System.out.println("Цель на месяц: " + spendingGoalMonthField.getText());
    }

    private void addFamilyMember() {
        System.out.println("Добавлен член семьи: " + familyMemberField.getText());
    }

    private void addExpenseCategory() {
        System.out.println("Добавлена категория расхода: " + expenseCategoryField.getText());
    }
}