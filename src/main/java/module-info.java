module com.example.ce216_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    // JavaFX PropertyValueFactory ve Gson erişimi için model paketi açılıyor
    opens com.example.ce216_project.model to javafx.base, com.google.gson;

    // FXML ve UI erişimleri için diğer gerekli açmalar
    opens com.example.ce216_project to javafx.fxml;
    opens com.example.ce216_project.util to javafx.fxml;

    // Exportlar (kendi sınıflarını dışa açık hale getiriyorsun)
    exports com.example.ce216_project;
    exports com.example.ce216_project.model;
    exports com.example.ce216_project.util;
}
