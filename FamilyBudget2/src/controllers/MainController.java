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
    private Button openExpenceWindow;

    @FXML
    private Button openIncomeWindow;

    @FXML
    private Button openSettingPage;

    @FXML
    void initialize() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        updateBudgetDisplay(dbHandler);
        updateWeekDisplay(dbHandler);
        updateMonthDisplay(dbHandler);

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

    private void updateBudgetDisplay(DatabaseHandler dbHandler) throws SQLException {
        int budgett = dbHandler.getBudget();
        budgetDisplay.setText(String.valueOf(budgett));
    }
    private void updateWeekDisplay(DatabaseHandler dbHandler) throws SQLException {
        int week = dbHandler.getWeek();
        weekDisplay.setText(String.valueOf(week));
    }
    private void updateMonthDisplay(DatabaseHandler dbHandler) throws SQLException {
        int month = dbHandler.getMonth();
        monthDisplay.setText(String.valueOf(month));
    }

}
