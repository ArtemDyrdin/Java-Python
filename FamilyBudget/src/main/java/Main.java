import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private double totalMoney = 0.0;
    private double spendingGoal = 10000.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Домашний Бюджет");

        // Создание боковой панели навигации
        VBox navigationPane = new VBox();
        navigationPane.setSpacing(10);
        navigationPane.setPadding(new Insets(10));
        navigationPane.setStyle("-fx-background-color: #f0f0f0;");

        Button mainPageButton = new Button("Главная");
        Button expensesPageButton = new Button("Расходы");
        Button incomePageButton = new Button("Доходы");

        navigationPane.getChildren().addAll(mainPageButton, expensesPageButton, incomePageButton);

        // Основной контент
        VBox mainContent = new VBox();
        mainContent.setSpacing(20);
        mainContent.setAlignment(javafx.geometry.Pos.CENTER);

        Label totalMoneyLabel = new Label("Всего денег: " + totalMoney + " руб.");
        Label spendingGoalLabel = new Label("Цель по расходам: " + spendingGoal + " руб.");

        Button addExpenseButton = new Button("Расход");
        Button addIncomeButton = new Button("Доход");

        // Открытие окна для добавления расхода
        addExpenseButton.setOnAction(e -> showAddExpenseWindow());

        // Открытие окна для добавления дохода
        addIncomeButton.setOnAction(e -> showAddIncomeWindow());

        mainContent.getChildren().addAll(totalMoneyLabel, spendingGoalLabel, addExpenseButton, addIncomeButton);

        // Главный макет
        BorderPane root = new BorderPane();
        root.setLeft(navigationPane);
        root.setCenter(mainContent);

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
        layout.setAlignment(Pos.CENTER);

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Еда", "Транспорт", "Развлечения", "Другое");

        ComboBox<String> familyMemberBox = new ComboBox<>();
        familyMemberBox.getItems().addAll("Мама", "Папа", "Ребенок", "Другое");

        Button submitButton = new Button("Добавить");

        submitButton.setOnAction(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryBox.getValue();
            String familyMember = familyMemberBox.getValue();

            totalMoney -= amount;
            expenseStage.close();
        });

        layout.getChildren().addAll(new Label("Сумма:"), amountField, new Label("Сфера расхода:"), categoryBox, new Label("Член семьи:"), familyMemberBox, submitButton);

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
        layout.setAlignment(Pos.CENTER);

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма");

        ComboBox<String> sourceBox = new ComboBox<>();
        sourceBox.getItems().addAll("Зарплата", "Подарок", "Продажа", "Другое");

        ComboBox<String> familyMemberBox = new ComboBox<>();
        familyMemberBox.getItems().addAll("Мама", "Папа", "Ребенок", "Другое");

        Button submitButton = new Button("Добавить");

        submitButton.setOnAction(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String source = sourceBox.getValue();
            String familyMember = familyMemberBox.getValue();

            totalMoney += amount;
            incomeStage.close();
        });

        layout.getChildren().addAll(new Label("Сумма:"), amountField, new Label("Источник дохода:"), sourceBox, new Label("Член семьи:"), familyMemberBox, submitButton);

        Scene scene = new Scene(layout, 300, 400);
        incomeStage.setScene(scene);
        incomeStage.show();
    }
}