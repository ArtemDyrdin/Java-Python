package controllers;

import db.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {

    @FXML
    public Text budgetDisplay;
    @FXML
    public Text weekDisplay;
    @FXML
    public Text monthDisplay;
    @FXML
    public Text expenceEver;
    @FXML
    public Text incomeEver;

    @FXML
    private Button openExpenceWindow;

    @FXML
    private Button openIncomeWindow;

    @FXML
    private Button openSettingPage;

    @FXML
    void initialize() throws Exception {
        DatabaseHandler dbHandler = new DatabaseHandler();

        updateBudgetDisplay(dbHandler);
        updateWeekDisplay(dbHandler);
        updateMonthDisplay(dbHandler);
        updateFinancialData(dbHandler);

        // страница настроек
        openSettingPage.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/setting.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            openSettingPage.getScene().getWindow().hide();
            stage.show();
        });

        // окно добавления расхода
        openExpenceWindow.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/expenceWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        // окно добавления расхода
        openIncomeWindow.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/incomeWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    // обновление показателя бюджета
    private void updateBudgetDisplay(DatabaseHandler dbHandler) throws SQLException {
        int budgett = dbHandler.getBudget();
        budgetDisplay.setText(String.valueOf(budgett));
    }
    // обновление показателя недельной цели
    private void updateWeekDisplay(DatabaseHandler dbHandler) throws SQLException {
        int week = dbHandler.getWeek();
        weekDisplay.setText(String.valueOf(week));
    }
    // обновление показателя месячной цели
    private void updateMonthDisplay(DatabaseHandler dbHandler) throws SQLException {
        int month = dbHandler.getMonth();
        monthDisplay.setText(String.valueOf(month));
    }
    // обновление показателей расходов и доходов
    private void updateFinancialData(DatabaseHandler dbHandler) throws Exception {
        int totalIncome = dbHandler.getTotalIncome();
        int totalExpenses = dbHandler.getTotalExpenses();
        incomeEver.setText(String.valueOf(totalIncome));
        expenceEver.setText(String.valueOf(totalExpenses));
    }

}
