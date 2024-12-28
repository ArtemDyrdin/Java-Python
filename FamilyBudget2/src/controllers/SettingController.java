package controllers;

import db.Const;
import db.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingController {
    // боковое меню
    @FXML
    private Button openMainPage;

    // бюджет
    @FXML
    private TextField setBudgetInput;
    @FXML
    public Text budgetDisplay;
    @FXML
    private Button setBudgetBtn;

    // цели
    @FXML
    private Button setMonthGoalBtn;
    @FXML
    private TextField setMonthGoalInput;
    @FXML
    private Button setWeekGoalBtn;
    @FXML
    private TextField setWeekGoalInput;
    @FXML
    private Text monthDisplay;
    @FXML
    private Text weekDisplay;

    // члены семьи
    @FXML
    private Button addFamilyPersonBtn;
    @FXML
    private TextField addFamilyPersonInput;
    @FXML
    private TableView<String> familyTable;
    @FXML
    private TableColumn<String, String> nameColumn;
    @FXML
    private Button deleteFamilyPersonBtn;

    // доходы семьи
    @FXML
    private TextField PersonIncomeField;
    @FXML
    private Button PersonIncomeBtn;
    @FXML
    public TableView<String> IncomesTable;
    @FXML
    private TableColumn<String, String> incomeColumn;
    @FXML
    private Button deleteIncomeBtn;

    // расходы семьи
    @FXML
    private TextField ExpenceField;
    @FXML
    private Button PersonExpenceBtn;
    @FXML
    public TableView<String> ExpencesTable;
    @FXML
    private TableColumn<String, String> expenceColumn;
    @FXML
    private Button deleteExpenceBtn;

    // зависимости с таблицами
    private ObservableList<String> familyList = FXCollections.observableArrayList();
    private ObservableList<String> originsList = FXCollections.observableArrayList();
    private ObservableList<String> expencesList = FXCollections.observableArrayList();


    @FXML
    void initialize() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        // загрузки
        loadFamilyPersons(dbHandler);
        loadFamilyIncomes(dbHandler);
        loadFamilyExpences(dbHandler);
        updateBudgetDisplay(dbHandler);
        updateWeekDisplay(dbHandler);
        updateMonthDisplay(dbHandler);

        // Инициализация таблицы семьи
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        familyTable.setItems(familyList);

        // Инициализация таблицы доходов
        incomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        IncomesTable.setItems(originsList);

        // Инициализация таблицы доходов
        expenceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        ExpencesTable.setItems(expencesList);

        // открытие главной страницы
        openMainPage.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/main.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            openMainPage.getScene().getWindow().hide();
            stage.show();
        });

        setBudgetBtn.setOnAction(actionEvent -> {
            int budget = Integer.parseInt(setBudgetInput.getText());
            try {
                dbHandler.setBudget(budget);
                updateBudgetDisplay(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setBudgetInput.clear();
        });

        setWeekGoalBtn.setOnAction(actionEvent -> {
            int week = Integer.parseInt(setWeekGoalInput.getText());
            try {
                dbHandler.setWeek(week);
                updateWeekDisplay(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setWeekGoalInput.clear();
        });

        setMonthGoalBtn.setOnAction(actionEvent -> {
            int month = Integer.parseInt(setMonthGoalInput.getText());
            try {
                dbHandler.setMonth(month);
                updateMonthDisplay(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setMonthGoalInput.clear();
        });

        // добавление члена семьи
        addFamilyPersonBtn.setOnAction(actionEvent -> {
            addFamilyPerson(dbHandler);
        });

        // добавление источника дохода
        PersonIncomeBtn.setOnAction(actionEvent -> {
            addFamilyOrigin(dbHandler);
        });

        // добавление вида расхода
        PersonExpenceBtn.setOnAction(actionEvent -> {
            addFamilyExpence(dbHandler);
        });

        // удаление члена семьи
        deleteFamilyPersonBtn.setOnAction(event -> {
            try {
                deleteSelectedFamilyPerson(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // удаление источника дохода
        deleteIncomeBtn.setOnAction(event -> {
            try {
                deleteSelectedFamilyOrigin(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // удаление источника дохода
        deleteExpenceBtn.setOnAction(event -> {
            try {
                deleteSelectedFamilyExpence(dbHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void addFamilyPerson(DatabaseHandler dbHandler) {
        String name = addFamilyPersonInput.getText();
        if (!name.isEmpty()) {
            familyList.add(name);
            try {
                System.out.println(addFamilyPersonInput.getText());
                dbHandler.addPerson(addFamilyPersonInput.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            addFamilyPersonInput.clear();
        }
    }

    private void addFamilyOrigin(DatabaseHandler dbHandler) {
        String origin = PersonIncomeField.getText();
        if (!origin.isEmpty()) {
            originsList.add(origin);
            try {
                System.out.println(PersonIncomeField.getText());
                dbHandler.addOrigin(PersonIncomeField.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            PersonIncomeField.clear();
        }
    }

    private void addFamilyExpence(DatabaseHandler dbHandler) {
        String expence = ExpenceField.getText();
        if (!expence.isEmpty()) {
            expencesList.add(expence);
            try {
                System.out.println(ExpenceField.getText());
                dbHandler.addExpence(ExpenceField.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ExpenceField.clear();
        }
    }

    private void deleteSelectedFamilyPerson(DatabaseHandler dbHandler) throws SQLException {
        String selectedName = familyTable.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            familyList.remove(selectedName);
            dbHandler.deletePerson(selectedName);
        }
    }

    private void deleteSelectedFamilyOrigin(DatabaseHandler dbHandler) throws SQLException {
        String selectedOrigin = IncomesTable.getSelectionModel().getSelectedItem();
        if (selectedOrigin != null) {
            originsList.remove(selectedOrigin);
            dbHandler.deleteOrigin(selectedOrigin);
        }
    }

    private void deleteSelectedFamilyExpence(DatabaseHandler dbHandler) throws SQLException {
        String selectedExpence = ExpencesTable.getSelectionModel().getSelectedItem();
        if (selectedExpence != null) {
            expencesList.remove(selectedExpence);
            dbHandler.deleteExpence(selectedExpence);
        }
    }

    private void loadFamilyPersons(DatabaseHandler dbHandler) throws SQLException {
        ResultSet rs = dbHandler.getPersons();
        while (rs.next()) {
            familyList.add(rs.getString(Const.Persons.NAME));
        }
    }

    private void loadFamilyIncomes(DatabaseHandler dbHandler) throws SQLException {
        ResultSet rs = dbHandler.getOrigins();
        while (rs.next()) {
            originsList.add(rs.getString(Const.Origins.INCOME));
        }
    }

    private void loadFamilyExpences(DatabaseHandler dbHandler) throws SQLException {
        ResultSet rs = dbHandler.getExpences();
        while (rs.next()) {
            expencesList.add(rs.getString(Const.Expences.EXPENCE));
        }
    }

    private void updateBudgetDisplay(DatabaseHandler dbHandler) throws SQLException {
        int budgett = dbHandler.getBudget();
        budgetDisplay.setText("Текущий бюджет: " + budgett);
    }

    private void updateWeekDisplay(DatabaseHandler dbHandler) throws SQLException {
        int week = dbHandler.getWeek();
        weekDisplay.setText("Текущая цель: " + week);
    }

    private void updateMonthDisplay(DatabaseHandler dbHandler) throws SQLException {
        int month = dbHandler.getMonth();
        monthDisplay.setText("Текущая цель: " + month);
    }
}
