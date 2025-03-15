module com.example.ce216_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ce216_project to javafx.fxml;
    exports com.example.ce216_project;
}