module com.fb.familybudget {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fb.familybudget to javafx.fxml;
    exports com.fb.familybudget;
}