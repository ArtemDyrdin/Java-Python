package main.java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    private double totalMoney = 0.0;
    private double spendingGoalWeek = 0.0;
    private double spendingGoalMonth = 0.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Система контроля семейного бюджета");

        // Создание боковой панели навигации
        VBox navigationPane = new VBox();
        navigationPane.setSpacing(10);
        navigationPane.setPadding(new Insets(10));
        navigationPane.setStyle("-fx-background-color: #f0f0f0;");

        Button mainPageButton = new Button("Главная");
        Button settingsPageButton = new Button("Настроить");


        navigationPane.getChildren().addAll(mainPageButton, settingsPageButton);

        // Основной контент
        VBox mainContent = new VBox();
        mainContent.setSpacing(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-alignment: center;");

        Label totalMoneyLabel = new Label("Всего денег: " + totalMoney + " руб.");
        Label spendingGoalLabel = new Label("Цель по расходам на неделю: " + spendingGoalWeek + " руб. | Цель на месяц: " + spendingGoalMonth + " руб.");

        Button addExpenseButton = new Button("Расход");
        Button addIncomeButton = new Button("Доход");

        mainContent.getChildren().addAll(totalMoneyLabel, spendingGoalLabel, addExpenseButton, addIncomeButton);

        // Главный макет
        BorderPane root = new BorderPane();
        root.setLeft(navigationPane);
        root.setCenter(mainContent);

        mainPageButton.setOnAction(e -> start(primaryStage));
        settingsPageButton.setOnAction(e -> showSettingsPage(primaryStage, root));
        addExpenseButton.setOnAction(e -> showAddExpenseWindow());
        addIncomeButton.setOnAction(e -> showAddIncomeWindow());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddExpenseWindow() {
        Stage expenseStage = new Stage();
        expenseStage.setTitle("Добавить Расход");

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Еда", "Транспорт", "Развлечения", "Другое");

        ComboBox<String> familyMemberBox = new ComboBox<>();
        familyMemberBox.getItems().addAll("Мама", "Папа", "Ребенок", "Другое");

        // Поле для выбора даты
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Выберите дату");

        Button submitButton = new Button("Добавить");

        submitButton.setOnAction(e -> {
            expenseStage.close();
        });

        layout.getChildren().addAll(new Label("Сумма:"), amountField, new Label("Сфера расхода:"),
                categoryBox, new Label("Член семьи:"), familyMemberBox, new Label("Дата:"), datePicker, submitButton);

        Scene scene = new Scene(layout, 300, 400);
        expenseStage.setScene(scene);
        expenseStage.show();
    }

    private void showAddIncomeWindow() {
        Stage incomeStage = new Stage();
        incomeStage.setTitle("Добавить Доход");

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма");

        ComboBox<String> sourceBox = new ComboBox<>();
        sourceBox.getItems().addAll("Зарплата", "Подарок", "Продажа", "Другое");

        ComboBox<String> familyMemberBox = new ComboBox<>();
        familyMemberBox.getItems().addAll("Мама", "Папа", "Ребенок", "Другое");

        // Поле для выбора даты
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Выберите дату");

        Button submitButton = new Button("Добавить");

        submitButton.setOnAction(e -> {
            incomeStage.close();
        });

        layout.getChildren().addAll(new Label("Сумма:"), amountField, new Label("Источник дохода:"),
                sourceBox, new Label("Член семьи:"), familyMemberBox, new Label("Дата:"), datePicker, submitButton);

        Scene scene = new Scene(layout, 300, 400);
        incomeStage.setScene(scene);
        incomeStage.show();
    }

    // Метод для отображения страницы настроек
    private void showSettingsPage(Stage primaryStage, BorderPane root) {
        VBox settingsContent = new VBox();
        settingsContent.setSpacing(15);
        settingsContent.setPadding(new Insets(15));

        // Поле для установки текущего бюджета
        TextField budgetField = new TextField();
        budgetField.setPromptText("Введите текущий бюджет");

        Button setFamilyBudget = new Button("Установить бюджет семьи");
        setFamilyBudget.setOnAction(e -> {

        });

        // Поля для установки целей по расходам
        TextField spendingGoalWeekField = new TextField();
        spendingGoalWeekField.setPromptText("Введите цель по расходам на неделю");

        Button setSpendingGoalWeek = new Button("Установить цель на неделю");
        setSpendingGoalWeek.setOnAction(e -> {

        });

        TextField spendingGoalMonthField = new TextField();
        spendingGoalMonthField.setPromptText("Введите цель по расходам на месяц");

        Button setSpendingGoalMonth = new Button("Установить цель на месяц");
        setSpendingGoalMonth.setOnAction(e -> {

        });

        // Список для добавления членов семьи
        TextField familyMemberField = new TextField();
        familyMemberField.setPromptText("Введите имя члена семьи");

        Button addFamilyMemberButton = new Button("Добавить члена семьи");
        addFamilyMemberButton.setOnAction(e -> {

        });

        // Список для добавления источников дохода для выбранного члена семьи
        ComboBox<String> familyMemberComboBox = new ComboBox<>();
        familyMemberComboBox.getItems().addAll();
        familyMemberComboBox.setPromptText("Выберите члена семьи");

        TextField incomeSourceField = new TextField();
        incomeSourceField.setPromptText("Введите источник дохода");

        Button addIncomeSourceButton = new Button("Добавить источник дохода");
        addIncomeSourceButton.setOnAction(e -> {

        });

        // Список для добавления сфер расходов
        TextField expenseCategoryField = new TextField();
        expenseCategoryField.setPromptText("Введите сферу расхода");

        Button addExpenseCategoryButton = new Button("Добавить сферу расхода");
        addExpenseCategoryButton.setOnAction(e -> {

        });

        settingsContent.getChildren().addAll(
                new Label("Настройка бюджета:"),
                budgetField, setFamilyBudget,
                new Label("Цель по расходам на неделю:"),
                spendingGoalWeekField, setSpendingGoalWeek,
                new Label("Цель по расходам на месяц:"),
                spendingGoalMonthField, setSpendingGoalMonth,
                new Label("Добавить члена семьи:"),
                familyMemberField, addFamilyMemberButton,
                new Label("Выберите члена семьи и добавьте источник дохода:"),
                familyMemberComboBox, incomeSourceField, addIncomeSourceButton,
                new Label("Добавить сферу расхода:"),
                expenseCategoryField, addExpenseCategoryButton
        );

        // Добавление прокрутки на страницу настроек
        ScrollPane scrollPane = new ScrollPane(settingsContent);
        scrollPane.setFitToWidth(true);

        root.setCenter(scrollPane);
    }
}