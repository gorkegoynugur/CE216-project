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
        private ScrollPane createAllArtifactsPane(Stage stage) {
            BorderPane pane = new BorderPane();
            pane.setPadding(new Insets(10));
            pane.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");


            tableView.setItems(artifactList);
            tableView.setId("allArtifactsTable");
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

            TableColumn<Artifact, String> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("artifactId"));
            idCol.setStyle("-fx-alignment: CENTER;");

            TableColumn<Artifact, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("artifactName"));
            nameCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> categoryCol = new TableColumn<>("Category");
            categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
            categoryCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> civCol = new TableColumn<>("Civilization");
            civCol.setCellValueFactory(new PropertyValueFactory<>("civilization"));
            civCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> placeCol = new TableColumn<>("Current Location");
            placeCol.setCellValueFactory(new PropertyValueFactory<>("currentPlace"));
            placeCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> discLocCol = new TableColumn<>("Discovery Location");
            discLocCol.setCellValueFactory(new PropertyValueFactory<>("discoveryLocation"));
            discLocCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> compCol = new TableColumn<>("Composition");
            compCol.setCellValueFactory(new PropertyValueFactory<>("composition"));
            compCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> discDateCol = new TableColumn<>("Discovery Date");
            discDateCol.setCellValueFactory(new PropertyValueFactory<>("discoveryDate"));
            discDateCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> wCol = new TableColumn<>("Weight");
            wCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
            wCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> dCol = new TableColumn<>("Dimensions");
            dCol.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
            dCol.setStyle("-fx-alignment: CENTER-LEFT;");

            TableColumn<Artifact, String> tagsCol = new TableColumn<>("Tags");
            tagsCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
            tagsCol.setStyle("-fx-alignment: CENTER-LEFT;");

            tableView.getColumns().setAll(idCol, nameCol, categoryCol, civCol, placeCol,discLocCol,
                    compCol,discDateCol,wCol,dCol,tagsCol);

            tableView.setRowFactory(tv -> {
                TableRow<Artifact> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && !row.isEmpty()) {
                        Artifact artifact = row.getItem();
                        showImagePopup(artifact.getImagePath());
                    }
                });
                return row;
            });

            Button importBtn = new Button("Import JSON");
            Button exportBtn = new Button("Export JSON");

            importBtn.setOnAction(e -> importArtifacts(stage));
            exportBtn.setOnAction(e -> exportArtifacts(stage));

            HBox controls = new HBox(10, importBtn, exportBtn);
            controls.setPadding(new Insets(10));
            controls.setAlignment(Pos.CENTER_LEFT);

            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);

            VBox right = new VBox(10, new Label("Artifact Image"), imageView );
            right.setPadding(new Insets(10));
            right.setAlignment(Pos.TOP_CENTER);

            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> showArtifactImage(newSel));


            pane.setTop(controls);
            pane.setCenter(tableView);
            pane.setRight(right);

            ScrollPane scrollAAPane=new ScrollPane(pane);
            scrollAAPane.setFitToWidth(true);
            scrollAAPane.setFitToHeight(true);



            return scrollAAPane;
        }



        private ScrollPane createAddArtifactPane(Stage stage) {
            VBox box = new VBox(10);
            box.setPadding(new Insets(20));
            box.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");


            TextField idField = new TextField(); idField.setPromptText("Artifact ID");
            TextField nameField = new TextField(); nameField.setPromptText("Name");
            TextField categoryField = new TextField(); categoryField.setPromptText("Category");
            TextField civField = new TextField(); civField.setPromptText("Civilization");
            TextField placeField = new TextField(); placeField.setPromptText("Current Location");
            TextField tagsField = new TextField(); tagsField.setPromptText("Tags (comma separated)");
            TextField discoveryPlaceField = new TextField(); discoveryPlaceField.setPromptText("Discovery Location");
            TextField compositionField = new TextField(); compositionField.setPromptText("Composition");
            TextField discoveryDateField = new TextField(); discoveryDateField.setPromptText("Discovery Date");
            TextField weightField = new TextField(); weightField.setPromptText("Weight (kg)");
            TextField widthField = new TextField(); widthField.setPromptText("Width (cm)");
            TextField lengthField = new TextField(); lengthField.setPromptText("Length (cm)");
            TextField heightField = new TextField(); heightField.setPromptText("Height (cm)");
            TextField imagePathField = new TextField(); imagePathField.setPromptText("Image Path");


            Button browse = new Button("Browse Image");
            browse.setOnAction(e -> {
                FileChooser fc = new FileChooser();
                File f = fc.showOpenDialog(stage);
                if (f != null) imagePathField.setText(f.getAbsolutePath());
            });

            Button clearBtn = new Button("Clear");
            Runnable clearFields = () -> {
                idField.clear();
                nameField.clear();
                categoryField.clear();
                civField.clear();
                placeField.clear();
                tagsField.clear();
                discoveryPlaceField.clear();
                compositionField.clear();
                discoveryDateField.clear();
                weightField.clear();
                widthField.clear();
                lengthField.clear();
                heightField.clear();
                imagePathField.clear();
            };
            clearBtn.setOnAction(e -> clearFields.run());

            Button saveBtn = new Button("Save");
            saveBtn.setOnAction(e -> {
                String enteredId = idField.getText().trim();
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                String civilization = civField.getText().trim();



                if (enteredId.isEmpty() || name.isEmpty() ||
                        category.isEmpty() || civilization.isEmpty()) {
                    showAlert("Missing Fields", "ID, Name, Category, and Civilization cannot be empty.");
                    return;
                }



                boolean exists = artifactList.stream()
                        .anyMatch(a -> a.getArtifactId().equalsIgnoreCase(enteredId));

                if (exists) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Duplicate ID");
                    alert.setHeaderText("Artifact ID already exists.");
                    alert.setContentText("Please enter a unique Artifact ID.");
                    alert.showAndWait();
                    return;
                }


                Artifact a = new Artifact();
                a.setArtifactId(enteredId);
                a.setArtifactName(nameField.getText());
                a.setCategory(categoryField.getText());
                a.setCivilization(civField.getText());
                a.setCurrentPlace(placeField.getText());
                a.setTags(List.of(tagsField.getText().split(",\\s*")));
                a.setDiscoveryLocation(discoveryPlaceField.getText());
                a.setComposition(compositionField.getText());
                a.setDiscoveryDate(discoveryDateField.getText());
                a.setImagePath(imagePathField.getText());

                try {
                    a.setWeight(Double.parseDouble(weightField.getText()));
                    a.setWidth(Double.parseDouble(widthField.getText()));
                    a.setLength(Double.parseDouble(lengthField.getText()));
                    a.setHeight(Double.parseDouble(heightField.getText()));
                } catch (NumberFormatException ex) {
                    a.setWeight(0); a.setWidth(0); a.setLength(0); a.setHeight(0);
                }



                artifactList.add(a);
                clearFields.run();
            });


            HBox buttons = new HBox(10, saveBtn, clearBtn);
            box.getChildren().addAll(idField, nameField, categoryField, civField, placeField,
                    discoveryPlaceField, compositionField, discoveryDateField,
                    weightField, widthField, lengthField, heightField,
                    tagsField, imagePathField, browse, buttons);

            ScrollPane scrollAddPane=new ScrollPane(box);
            scrollAddPane.setFitToWidth(true);
            scrollAddPane.setFitToHeight(true);


            return scrollAddPane;
        }
        private ScrollPane createGuidePane() {

            VBox vbox = new VBox(20);
            vbox.setPadding(new Insets(40));
            vbox.setAlignment(Pos.TOP_LEFT);
            vbox.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");

            Label title = new Label("📘 Quick Guide");
            title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2b2b2b;");

            String[][] guide = {
                    {"📦 Import", "Click to load artifacts from a JSON file into the system."},
                    {"💾 Export", "Save all artifacts currently in the list to a .json file."},
                    {"➕ Add Artifact", "Fill out the form with artifact details and click Save."},
                    {"🖼 View Image", "Double-click an artifact to preview its image in full size."},
                    {"🧹 Clear", "Use the Clear button to reset all form fields."},
                    {"🔍 Search", "Navigate to Search tab and type to filter artifacts live."}
            };

            VBox guideCards = new VBox(15);
            for (String[] g : guide) {
                Label header = new Label(g[0]);
                header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #f37021;");
                Label desc = new Label(g[1]);
                desc.setWrapText(true);
                desc.setStyle("-fx-text-fill: #555; -fx-font-size: 14px;");
                VBox card = new VBox(5, header, desc);
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ddd; -fx-border-radius: 8; -fx-padding: 12; -fx-background-radius: 8;");
                guideCards.getChildren().add(card);
            }

            vbox.getChildren().addAll(title, guideCards);

            ScrollPane scrollGuide=new ScrollPane(vbox);
            scrollGuide.setFitToHeight(true);
            scrollGuide.setFitToWidth(true);
            return scrollGuide;

        }

        private ScrollPane createFaqPane() {
            VBox wrapper = new VBox(20);
            wrapper.setPadding(new Insets(30));
            wrapper.setAlignment(Pos.TOP_LEFT);
            wrapper.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");

            Label title = new Label("❓ Frequently Asked Questions");
            title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #2b2b2b;");

            Accordion accordion = new Accordion();
            accordion.setStyle("-fx-background-color: transparent;");
            VBox.setVgrow(accordion, Priority.ALWAYS); // bu satır önemli

            String[][] faq = {
                    {"How can I add a new artifact?", "Go to the ➕ Add Artifact tab, fill in the fields and click Save."},
                    {"What file format should I use to import/export artifacts?", "Use .json files structured like artifact data."},
                    {"Can I preview artifact images?", "Double-click any row in All Artifacts to view the full image."},
                    {"How do I search for a specific artifact?", "Use the Search tab and type keywords to filter live."},
                    {"What happens when I click ‘Clear’?", "It resets all input fields in the Add tab."},
                    {"Can I edit or delete existing artifacts?", "Currently not, but you can export/import updated lists."},
                    {"What if an image is not showing?", "Ensure the file path is correct and the image exists."},
                    {"How is data saved?", "Exporting creates a JSON file using Gson."}
            };

            for (String[] pair : faq) {
                Label content = new Label(pair[1]);
                content.setWrapText(true);
                content.setStyle("-fx-padding: 10; -fx-font-size: 14px; -fx-text-fill: #444;");
                TitledPane pane = new TitledPane("❓ " + pair[0], content);
                pane.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #f37021;");
                accordion.getPanes().add(pane);
            }

            wrapper.getChildren().addAll(title, accordion);

            ScrollPane scrollFaq=new ScrollPane(wrapper);
            scrollFaq.setFitToHeight(true);
            scrollFaq.setFitToWidth(true);

            return scrollFaq;
        }

        private ScrollPane createAdminPane() {
            VBox adminPane = new VBox(20);
            adminPane.setPadding(new Insets(30));
            adminPane.setAlignment(Pos.TOP_CENTER);

            Label title = new Label("🔐 Admin - Manage Artifacts");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            adminPane.setStyle("-fx-background-color: rgba(243, 112, 33, 0.15);");

            TableView<Artifact> adminTable = new TableView<>();
            adminTable.setItems(artifactList); // aynı listeyi kullanıyoruz
            adminTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

            TableColumn<Artifact, String> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("artifactId"));
            TableColumn<Artifact, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("artifactName"));
            TableColumn<Artifact, String> categoryCol = new TableColumn<>("Category");
            categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
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
            TableColumn<Artifact, String> wCol = new TableColumn<>("Weight");
            wCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
            TableColumn<Artifact, String> dCol = new TableColumn<>("Dimensions");
            dCol.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
            TableColumn<Artifact, String> tagsCol = new TableColumn<>("Tags");
            tagsCol.setCellValueFactory(new PropertyValueFactory<>("Tags"));
            adminTable.getColumns().addAll(idCol, nameCol, categoryCol, civCol, placeCol,discLocCol,
                    compCol,discDateCol,wCol,dCol,tagsCol);

            Button editBtn = new Button("✏️ Edit");
            Button deleteBtn = new Button("🗑️ Delete");

            editBtn.setOnAction(e -> {
                Artifact selected = adminTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    showEditPopup(selected);
                    adminTable.refresh();
                } else {
                    showAlert("No Selection", "Please select an artifact to edit.");
                }
            });

            deleteBtn.setOnAction(e -> {
                Artifact selected = adminTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    artifactList.remove(selected);
                } else {
                    showAlert("No Selection", "Please select an artifact to delete.");
                }
            });

            HBox buttons = new HBox(10, editBtn, deleteBtn);
            buttons.setAlignment(Pos.CENTER);

            adminPane.getChildren().addAll(title, adminTable, buttons);

            ScrollPane scrollAdmin=new ScrollPane(adminPane);
            scrollAdmin.setFitToHeight(true);
            scrollAdmin.setFitToWidth(true);

            return scrollAdmin;
        }


        private void showLoginDialog(Stage owner, Runnable onSuccess, Runnable onFail) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(owner);
            dialog.setTitle("🔐 Admin Login");

            Label title = new Label("Admin Access Required");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #f37021;");

            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");
            usernameField.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-padding: 8;");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            passwordField.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-padding: 8;");

            Label errorLabel = new Label();
            errorLabel.setStyle("-fx-text-fill: red;");

            Button loginBtn = new Button("Login");
            loginBtn.setStyle(
                    "-fx-background-color: #f37021; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-weight: bold; " +
                            "-fx-background-radius: 6;"
            );
            loginBtn.setDefaultButton(true);

            loginBtn.setOnAction(e -> {
                String user = usernameField.getText().trim();
                String pass = passwordField.getText().trim();

                if (user.equals("admin") && pass.equals("şifre")) {
                    dialog.close();
                    onSuccess.run();
                } else {
                    errorLabel.setText("Incorrect username or password.");
                }
            });
            ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/ieulogo.png")));
            logo.setFitWidth(100);
            logo.setPreserveRatio(true);

            DropShadow shadow = new DropShadow();
            shadow.setRadius(5);
            shadow.setOffsetX(3);
            shadow.setOffsetY(3);
            shadow.setColor(Color.rgb(0, 0, 0, 0.3));
            logo.setEffect(shadow);

            VBox layout = new VBox(12, logo, title, usernameField, passwordField, loginBtn, errorLabel);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(30));
            VBox.setMargin(logo, new Insets(10, 0, -5, 0));
            Scene scene = new Scene(layout, 400, 340); // yeni boyut

            layout.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #ffffff, #f2f2f2); " +
                            "-fx-border-color: #ddd; " +
                            "-fx-border-width: 1; " +
                            "-fx-border-radius: 10; " +
                            "-fx-background-radius: 10;"
            );


            dialog.setScene(scene);
            dialog.showAndWait();

            if (!dialog.isShowing()) {
                onFail.run();
            }
        }


        private void showImagePopup(String imagePath) {
            if (imagePath == null || imagePath.isEmpty()) return;

            File file = new File(imagePath);
            if (!file.exists()) {
                showAlert("Error", "Image file not found.");
                return;
            }

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Artifact Image");

            ImageView fullImage = new ImageView(new Image(file.toURI().toString()));
            fullImage.setPreserveRatio(true);
            fullImage.setFitWidth(600);

            Button closeBtn = new Button("Close");
            closeBtn.setOnAction(e -> popup.close());

            VBox layout = new VBox(10, fullImage, closeBtn);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(30));

            Scene scene = new Scene(layout);
            popup.setScene(scene);
            popup.showAndWait();
        }



        private void showEditPopup(Artifact artifact) {
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("✏️ Edit Artifact");

            TextField idField = new TextField(artifact.getArtifactId());
            TextField nameField = new TextField(artifact.getArtifactName());
            TextField categoryField = new TextField(artifact.getCategory());
            TextField civField = new TextField(artifact.getCivilization());
            TextField placeField = new TextField(artifact.getCurrentPlace());
            TextField discLocField = new TextField(artifact.getDiscoveryLocation());
            TextField compField = new TextField(artifact.getComposition());
            TextField discDateField = new TextField(artifact.getDiscoveryDate());
            TextField weightField = new TextField();
            TextField widthField = new TextField();
            TextField lengthField = new TextField();
            TextField heightField = new TextField();
            TextField imagePathField = new TextField();
            TextField tagsField = new TextField(String.join(", ", artifact.getTags()));


            Button saveBtn = new Button("Save");
            saveBtn.setOnAction(e -> {
                artifact.setArtifactId(idField.getText().trim());
                artifact.setArtifactName(nameField.getText().trim());
                artifact.setCategory(categoryField.getText().trim());
                artifact.setCivilization(civField.getText().trim());
                artifact.setCurrentPlace(placeField.getText().trim());
                artifact.setDiscoveryLocation(discLocField.getText().trim());
                artifact.setComposition(compField.getText().trim());
                artifact.setDiscoveryDate(discDateField.getText().trim());
                artifact.setTags(List.of(tagsField.getText().split(",\\s*")));
                try {
                    artifact.setWeight(Double.parseDouble(weightField.getText()));
                    artifact.setWidth(Double.parseDouble(widthField.getText()));
                    artifact.setLength(Double.parseDouble(lengthField.getText()));
                    artifact.setHeight(Double.parseDouble(heightField.getText()));
                } catch (NumberFormatException ex) {
                    artifact.setWeight(0); artifact.setWidth(0); artifact.setLength(0); artifact.setHeight(0);
                }
                popup.close();
            });

            VBox layout = new VBox(10,
                    new Label("ID:"),idField,
                    new Label("Name:"), nameField,
                    new Label("Category:"), categoryField,
                    new Label("Civilization:"), civField,
                    new Label("Current Location:"), placeField,
                    new Label("Discovery Location:"),discLocField,
                    new Label("Composition:"),compField,
                    new Label("Discovery Date:"),discDateField,
                    new Label("Weight:"),weightField,
                    new Label("Dimension: %s x %s x %s"),heightField, widthField,lengthField,
                    new Label("Tags:"), tagsField,
                    saveBtn
            );
            layout.setPadding(new Insets(20));
            layout.setAlignment(Pos.CENTER_LEFT);

            popup.setScene(new Scene(layout));
            popup.showAndWait();
        }


        private void setupTableColumns() {
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

            tableView.getColumns().addAll(idCol, nameCol, catCol, civCol, placeCol);
        }

        private void showArtifactImage(Artifact artifact) {
            if (artifact != null && artifact.getImagePath() != null && !artifact.getImagePath().isEmpty()) {
                try {
                    imageView.setImage(new Image(new File(artifact.getImagePath()).toURI().toString()));
                } catch (Exception e) {
                    imageView.setImage(null);
                }
            } else {
                imageView.setImage(null);
            }
        }

        private void importArtifacts(Stage stage) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open JSON File");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    List<Artifact> loaded = ArtifactJsonHandler.loadArtifacts(file.getAbsolutePath());
                    artifactList.setAll(loaded);
                } catch (IOException e) {
                    showAlert("Error", "Could not load file: " + e.getMessage());
                }
            }
        }

        private void exportArtifacts(Stage stage) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save JSON File");
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    ArtifactJsonHandler.saveArtifacts(file.getAbsolutePath(), new ArrayList<>(artifactList));
                } catch (IOException e) {
                    showAlert("Error", "Could not save file: " + e.getMessage());
                }
            }
        }

        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }




        public static void main(String[] args) {
            launch();
        }
    }


