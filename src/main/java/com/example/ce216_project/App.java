package com.example.ce216_project;

import com.example.ce216_project.model.Artifact;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



import com.example.ce216_project.model.Artifact;
import com.example.ce216_project.util.ArtifactJsonHandler;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



    public class App extends Application {
        private final ObservableList<Artifact> artifactList = FXCollections.observableArrayList();
        private final ObservableList<Artifact> filteredList = FXCollections.observableArrayList();
        private final TableView<Artifact> tableView = new TableView<>();
        private final ImageView imageView = new ImageView();
        private ToggleButton homeBtn,listBtn, addBtn, searchBtn,adminBtn,guideBtn,faqBtn;

        @Override
        public void start(Stage stage) {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/history.png")));
            stage.setTitle("Historical Artifact Catalog");

            VBox root = new VBox();
            root.setSpacing(10);
            root.setPadding(new Insets(10));

            ToggleGroup toggleGroup = new ToggleGroup();
            homeBtn = new ToggleButton("\uD83C\uDFE0 Home");
            this.listBtn = new ToggleButton("\uD83D\uDCDC All Artifacts");
            this.addBtn = new ToggleButton("➕ Add Artifact");
            this.searchBtn = new ToggleButton("\uD83D\uDD0D Search");
            this.guideBtn = new ToggleButton("📘 Guide");
            this.faqBtn = new ToggleButton("❓ FAQ");
            this.adminBtn = new ToggleButton("🔐 Admin ");



            homeBtn.setToggleGroup(toggleGroup);
            listBtn.setToggleGroup(toggleGroup);
            addBtn.setToggleGroup(toggleGroup);
            searchBtn.setToggleGroup(toggleGroup);
            guideBtn.setToggleGroup(toggleGroup);
            faqBtn.setToggleGroup(toggleGroup);
            adminBtn.setToggleGroup(toggleGroup);



            HBox navigation = new HBox(10, homeBtn, listBtn, addBtn, searchBtn, guideBtn,faqBtn,adminBtn);
            navigation.setAlignment(Pos.CENTER);



            StackPane contentPane = new StackPane();

            Node homePane = createHomePane();
            Node artifactsPane = createAllArtifactsPane(stage);
            Node addPane = createAddArtifactPane(stage);
            Node searchPane = createSearchPane();
            Node guidePane = createGuidePane();
            Node faqPane = createFaqPane();
            Node adminPane = createAdminPane();
            adminBtn.setOnAction(e -> {
                if (!adminPane.isVisible()) {
                    showLoginDialog(stage, () -> {
                        adminPane.setVisible(true);
                        homePane.setVisible(false);
                        artifactsPane.setVisible(false);
                        addPane.setVisible(false);
                        searchPane.setVisible(false);
                        guidePane.setVisible(false);
                        faqPane.setVisible(false);
                    }, () -> {
                        toggleGroup.selectToggle(homeBtn);
                    });
                } else {
                    adminPane.setVisible(true);
                    homePane.setVisible(false);
                    artifactsPane.setVisible(false);
                    addPane.setVisible(false);
                    searchPane.setVisible(false);
                    guidePane.setVisible(false);
                }
            });




            contentPane.getChildren().addAll(homePane, artifactsPane, addPane, searchPane, guidePane,faqPane,adminPane);


            Runnable updateVisibility = () -> {
                homePane.setVisible(homeBtn.isSelected());
                artifactsPane.setVisible(listBtn.isSelected());
                addPane.setVisible(addBtn.isSelected());
                searchPane.setVisible(searchBtn.isSelected());
                guidePane.setVisible(guideBtn.isSelected());
                faqPane.setVisible(faqBtn.isSelected());
                adminPane.setVisible(adminBtn.isSelected());


            };

            homeBtn.setOnAction(e -> updateVisibility.run());
            listBtn.setOnAction(e -> updateVisibility.run());
            addBtn.setOnAction(e -> updateVisibility.run());
            searchBtn.setOnAction(e -> updateVisibility.run());
            guideBtn.setOnAction(e -> updateVisibility.run());
            faqBtn.setOnAction(e -> updateVisibility.run());


            homeBtn.setSelected(true);
            updateVisibility.run();

            root.getChildren().addAll(navigation, contentPane);
            VBox.setVgrow(contentPane, Priority.ALWAYS);
            Scene scene = new Scene(root, 1200, 700);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

        private ScrollPane createHomePane() {
            VBox box = new VBox(20);
            box.setPadding(new Insets(60));
            box.setAlignment(Pos.CENTER);
            box.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");


            Label title = new Label("Izmir University of Economics\nArtifact Catalog");
            title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #333; -fx-text-alignment: center;");

            Label contributors = new Label("Kaan Ege Soylu\n       Birsu Hacıkadiroğlu\n  Görke Göynügür");
            contributors.setStyle("-fx-font-size: 18px; -fx-text-fill: #555; -fx-text-alignment: center;");

            ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/ieulogo.png")));
            logo.setFitWidth(300);
            logo.setPreserveRatio(true);

            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(2);
            shadow.setOffsetY(2);
            shadow.setColor(Color.rgb(0, 0, 0, 0.3));
            logo.setEffect(shadow);

            FadeTransition ft = new FadeTransition(Duration.millis(1500), logo);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();

            box.getChildren().addAll(logo, title, contributors);

            ScrollPane scrollHome=new ScrollPane(box);
            scrollHome.setFitToHeight(true);
            scrollHome.setFitToWidth(true);

            return scrollHome;
        }
        private ScrollPane createSearchPane() {
            VBox searchPane = new VBox(15);
            searchPane.setPadding(new Insets(30));
            searchPane.setAlignment(Pos.TOP_CENTER);
            searchPane.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");


            Label title = new Label("🔍 Search Artifacts");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            ComboBox<String> filterCombo = new ComboBox<>();
            filterCombo.getItems().addAll("All", "ID", "Name", "Category", "Civilization", "Location", "Tags",
                    "Discovery Location", "Composition", "Discovery Date", "Weight" , "Dimensions");
            filterCombo.setValue("All");

            TextField searchField = new TextField();
            searchField.setPromptText("Enter keyword...");

            HBox searchControls = new HBox(10, new Label("Filter by:"), filterCombo, searchField);
            searchControls.setAlignment(Pos.CENTER);

            TableView<Artifact> searchTable = new TableView<>();
            searchTable.setItems(filteredList);
            searchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

            searchTable.setRowFactory(tv -> {
                TableRow<Artifact> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && !row.isEmpty()) {
                        Artifact artifact = row.getItem();
                        if (artifact.getImagePath() != null && !artifact.getImagePath().isEmpty()) {
                            showImagePopup(artifact.getImagePath());
                        }
                    }
                });
                return row;
            });



            TableColumn<Artifact, String> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("artifactId"));

            TableColumn<Artifact, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("artifactName"));

            TableColumn<Artifact, String> catCol = new TableColumn<>("Category");
            catCol.setCellValueFactory(new PropertyValueFactory<>("category"));

            TableColumn<Artifact, String> civCol = new TableColumn<>("Civilization");
            civCol.setCellValueFactory(new PropertyValueFactory<>("civilization"));

            TableColumn<Artifact, String> placeCol = new TableColumn<>("Current Location");
            placeCol.setCellValueFactory(new PropertyValueFactory<>("currentPlace"));

            TableColumn<Artifact, String> discLocCol = new TableColumn<>("Discovery Location");
            discLocCol.setCellValueFactory(new PropertyValueFactory<>("discoveryLocation"));

            TableColumn<Artifact, String> compCol = new TableColumn<>("Composition");
            compCol.setCellValueFactory(new PropertyValueFactory<>("composition"));

            TableColumn<Artifact, String> discDateCol = new TableColumn<>("Discovery Date");
            discDateCol.setCellValueFactory(new PropertyValueFactory<>("discoveryDate"));

            TableColumn<Artifact, Number> weightCol = new TableColumn<>("Weight (kg)");
            weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

            TableColumn<Artifact, Number> tagsCol = new TableColumn<>("Tags");
            tagsCol.setCellValueFactory(new PropertyValueFactory<>("tags"));

            TableColumn<Artifact, String> dimensionsCol = new TableColumn<>("Dimensions (cm)");
            dimensionsCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getWidth() + " x " +
                            cellData.getValue().getLength() + " x " +
                            cellData.getValue().getHeight()
            ));


            searchTable.getColumns().addAll(idCol, nameCol, catCol, civCol, placeCol, tagsCol,
                    discLocCol, compCol, discDateCol, weightCol, dimensionsCol);

            searchField.textProperty().addListener((obs, oldVal, newVal) -> {
                String keyword = newVal.toLowerCase();
                String filterBy = filterCombo.getValue();

                filteredList.setAll(artifactList.stream().filter(a -> {
                    switch (filterBy) {
                        case "ID": return a.getArtifactId().toLowerCase().contains(keyword);
                        case "Name": return a.getArtifactName().toLowerCase().contains(keyword);
                        case "Category": return a.getCategory().toLowerCase().contains(keyword);
                        case "Civilization": return a.getCivilization().toLowerCase().contains(keyword);
                        case "Location": return a.getCurrentPlace().toLowerCase().contains(keyword);
                        case "Tags": return a.getTags().toString().toLowerCase().contains(keyword);
                        case "Discovery Location": return a.getDiscoveryLocation().toString().toLowerCase().contains(keyword);
                        case "Composition" : return a.getComposition().toString().toLowerCase().contains(keyword);
                        case "Discovery Date": return a.getDiscoveryDate().toString().toLowerCase().contains(keyword);
                        case "Weight": return String.valueOf(a.getWeight()).contains(keyword);
                        case "Dimensions": return  String.valueOf(a.getHeight()).contains(keyword) ||
                                String.valueOf(a.getWidth()).contains(keyword)  ||
                                String.valueOf(a.getLength()).contains(keyword);

                        case "All":
                        default:
                            return a.getArtifactId().toLowerCase().contains(keyword) ||
                                    a.getArtifactName().toLowerCase().contains(keyword) ||
                                    a.getCategory().toLowerCase().contains(keyword) ||
                                    a.getCivilization().toLowerCase().contains(keyword) ||
                                    a.getCurrentPlace().toLowerCase().contains(keyword) ||
                                    a.getTags().toString().toLowerCase().contains(keyword) ||
                                    a.getDiscoveryLocation().toString().toLowerCase().contains(keyword) ||
                                    a.getComposition().toString().toLowerCase().contains(keyword) ||
                                    a.getDiscoveryDate().toString().toLowerCase().contains(keyword) ||
                                    String.valueOf(a.getWeight()).contains(keyword)  ||
                                    String.valueOf(a.getWidth()).contains(keyword)  ||
                                    String.valueOf(a.getLength()).contains(keyword)  ||
                                    String.valueOf(a.getHeight()).contains(keyword);


                    }
                }).collect(Collectors.toList()));
            });

            searchPane.getChildren().addAll(title, searchControls, searchTable);
            ScrollPane scroll = new ScrollPane(searchPane);
            searchPane.setMargin(scroll, new Insets(0));

            scroll.setFitToWidth(true);
            scroll.setFitToHeight(true);
            return scroll;
        }





        public static void main(String[] args) {
            launch();
        }
    }


