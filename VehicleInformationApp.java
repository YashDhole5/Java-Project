import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class VehicleInformationApp extends Application {

    private final Map<String, Map<String, Map<String, String>>> mechanicInformation = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vehicle Information and Mechanic Finder");

        // Main container
        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        mainContainer.setSpacing(20);

        // Labels
        Label titleLabel = new Label("🚗 Vehicle Information and Mechanic Finder 🛠");
        titleLabel.setStyle("-fx-font-size: 24;");

        // Tabs for Vehicle Types
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: lightblue;");

        // Location Input
        HBox locationInput = new HBox();
        Label locationLabel = new Label("🏙 Location:");
        locationLabel.setStyle("-fx-font-size: 16;");
        TextField locationTextField = new TextField();
        locationInput.getChildren().addAll(locationLabel, locationTextField);
        locationInput.setSpacing(10);

        // Engine Type Selection
        HBox engineInput = new HBox();
        Label engineLabel = new Label("⛽ Engine Type:");
        engineLabel.setStyle("-fx-font-size: 16;");
        ComboBox<String> engineComboBox = new ComboBox<>();
        engineComboBox.setStyle("-fx-font-size: 16;");
        engineComboBox.getItems().addAll("Petrol", "Diesel", "EV-Car", "EV-Bike", "CNG-Car");
        engineComboBox.setValue("Petrol");
        engineInput.getChildren().addAll(engineLabel, engineComboBox);
        engineInput.setSpacing(10);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 16;");
        submitButton.setOnAction(e -> handleSubmitButton(locationTextField, engineComboBox, tabPane));

        // Refresh Button
        Button refreshButton = new Button("Refresh Data");
        refreshButton.setStyle("-fx-font-size: 16;");
        refreshButton.setOnAction(e -> handleRefreshButton());

        // Information Display Area
        TextArea informationArea = new TextArea();
        informationArea.setEditable(false);
        informationArea.setWrapText(true);
        informationArea.setStyle("-fx-font-size: 16; -fx-background-color: lightyellow;");

        // Main container styling
        mainContainer.getChildren().addAll(titleLabel, locationInput, engineInput, tabPane, submitButton, refreshButton, informationArea);

        // Create tabs for different vehicle types
        String[] vehicleTypes = {"🚗 Sedan", "🚙 SUV", "🚚 Truck", "🏍 Motorcycle"};

        for (String type : vehicleTypes) {
            Tab tab = new Tab(type);
            tab.setClosable(false);

            VBox tabContent = new VBox();
            tabContent.setPadding(new Insets(20));
            tabContent.setSpacing(10);

            // Mechanics List
            ListView<String> mechanicsList = new ListView<>();
            mechanicsList.setStyle("-fx-font-size: 16;");
            tabContent.getChildren().add(mechanicsList);

            // Mechanic Information
            Label mechanicInfoLabel = new Label("🔧 Mechanic Information:");
            mechanicInfoLabel.setStyle("-fx-font-size: 18;");
            tabContent.getChildren().add(mechanicInfoLabel);

            TextArea expertiseArea = new TextArea();
            expertiseArea.setEditable(false);
            expertiseArea.setWrapText(true);
            expertiseArea.setStyle("-fx-font-size: 16; -fx-background-color: lightyellow;");
            tabContent.getChildren().add(expertiseArea);

            tab.setContent(tabContent);
            tabPane.getTabs().add(tab);
        }

        Scene scene = new Scene(mainContainer, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Populate mechanic information
        populateMechanicInformation();
    }

    private void populateMechanicInformation() {
        // Clear existing data
        mechanicInformation.clear();

        // Populate mechanic information with Indian names, Pune city's street names, and real data
        // For each vehicle type and engine type combination, add mechanics and their information
        // Example: Sedan - Petrol, SUV - Diesel, etc.
        Map<String, Map<String, String>> sedanPetrolMechanics = new HashMap<>();
        sedanPetrolMechanics.put("Raj Auto Service", createMechanicInfo("MG Road, Pune", "(123) 456-7890"));
        sedanPetrolMechanics.put("Suman Car Repairs", createMechanicInfo("FC Road, Pune", "(987) 654-3210"));
        sedanPetrolMechanics.put("Pune Car Care", createMechanicInfo("JM Road, Pune", "(789) 123-4567"));
        mechanicInformation.put("🚗 Sedan-Petrol", sedanPetrolMechanics);

        Map<String, Map<String, String>> suvDieselMechanics = new HashMap<>();
        suvDieselMechanics.put("Amit's SUV Service", createMechanicInfo("Laxmi Road, Pune", "(456) 789-0123"));
        suvDieselMechanics.put("Deepa Diesel Repairs", createMechanicInfo("Karve Road, Pune", "(789) 123-4567"));
        suvDieselMechanics.put("Pune SUV Masters", createMechanicInfo("FC Road, Pune", "(123) 456-7890"));
        mechanicInformation.put("🚙 SUV-Diesel", suvDieselMechanics);

        // Add mechanics for other vehicle types, engine types, and locations
        // SUV - Petrol
        Map<String, Map<String, String>> suvPetrolMechanics = new HashMap<>();
        suvPetrolMechanics.put("SUV Pro Service", createMechanicInfo("JM Road, Pune", "(111) 222-3333"));
        suvPetrolMechanics.put("SUV King Repairs", createMechanicInfo("MG Road, Pune", "(444) 555-6666"));
        suvPetrolMechanics.put("Pune SUV Experts", createMechanicInfo("Karve Road, Pune", "(777) 888-9999"));
        mechanicInformation.put("SUV-Petrol", suvPetrolMechanics);

        // Truck - Diesel
        Map<String, Map<String, String>> truckDieselMechanics = new HashMap<>();
        truckDieselMechanics.put("Truck Master Service", createMechanicInfo("Laxmi Road, Pune", "(101) 202-3030"));
        truckDieselMechanics.put("Diesel Truck Repairs", createMechanicInfo("JM Road, Pune", "(303) 404-5050"));
        truckDieselMechanics.put("Heavy Truck Pros", createMechanicInfo("MG Road, Pune", "(505) 606-7070"));
        mechanicInformation.put("Truck-Diesel", truckDieselMechanics);

        // Truck - CNG
        Map<String, Map<String, String>> truckCNGMechanics = new HashMap<>();
        truckCNGMechanics.put("CNG Truck Service", createMechanicInfo("Karve Road, Pune", "(808) 909-1010"));
        truckCNGMechanics.put("Truck CNG Repairs", createMechanicInfo("FC Road, Pune", "(909) 010-1111"));
        truckCNGMechanics.put("Pune CNG Truck Experts", createMechanicInfo("JM Road, Pune", "(111) 212-3131"));
        mechanicInformation.put("Truck-CNG", truckCNGMechanics);

        // Motorcycle - Petrol
        Map<String, Map<String, String>> motorcyclePetrolMechanics = new HashMap<>();
        motorcyclePetrolMechanics.put("Motorcycle Pro Service", createMechanicInfo("Laxmi Road, Pune", "(212) 313-4141"));
        motorcyclePetrolMechanics.put("Pune Bike Repairs", createMechanicInfo("MG Road, Pune", "(414) 515-6161"));
        motorcyclePetrolMechanics.put("Motorcycle Gurus", createMechanicInfo("JM Road, Pune", "(616) 717-8181"));
        mechanicInformation.put("Motorcycle-Petrol", motorcyclePetrolMechanics);

        // Continue to add more mechanics and data for different vehicle types, engine types, and locations
    }

    private Map<String, String> createMechanicInfo(String address, String phone) {
        Map<String, String> info = new HashMap<>();
        info.put("Address", address);
        info.put("Phone", phone);
        return info;
    }

    private void displayMechanicInformation(String selectedMechanic, String type, TextArea expertiseArea) {
        expertiseArea.clear();

        if (mechanicInformation.containsKey(type)) {
            Map<String, Map<String, String>> mechanics = mechanicInformation.get(type);
            if (mechanics != null && mechanics.containsKey(selectedMechanic)) {
                Map<String, String> mechanicInfo = mechanics.get(selectedMechanic);
                StringBuilder infoText = new StringBuilder();
                for (Map.Entry<String, String> entry : mechanicInfo.entrySet()) {
                    infoText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                expertiseArea.setText(infoText.toString());
            } else {
                expertiseArea.setText("Mechanic information not found for this selection.");
            }
        } else {
            expertiseArea.setText("Mechanic information not found for this selection.");
        }
    }

    private void handleSubmitButton(TextField locationTextField, ComboBox<String> engineComboBox, TabPane tabPane) {
        // Get the selected location and engine type
        String selectedLocation = locationTextField.getText();
        String selectedEngine = engineComboBox.getValue();

        // Display information
        for (Tab tab : tabPane.getTabs()) {
            VBox tabContent = (VBox) tab.getContent();
            ListView<String> mechanicsList = (ListView<String>) tabContent.getChildren().get(0);
            mechanicsList.getItems().clear(); // Clear previous data

            String tabType = tab.getText() + "-" + selectedEngine;
            Map<String, Map<String, String>> mechanicsData = mechanicInformation.get(tabType);
            if (mechanicsData != null) {
                ObservableList<String> filteredMechanics = filterMechanicsByLocation(mechanicsData, selectedLocation);
                mechanicsList.getItems().addAll(filteredMechanics);
                mechanicsList.setOnMouseClicked(event -> {
                    String selectedMechanic = mechanicsList.getSelectionModel().getSelectedItem();
                    displayMechanicInformation(selectedMechanic, tabType, (TextArea) tabContent.getChildren().get(2));
                });
            } else {
                TextArea expertiseArea = (TextArea) tabContent.getChildren().get(2);
                expertiseArea.setText("No mechanics found for this selection.");
            }
        }
    }

    private void handleRefreshButton() {
        // Populate mechanic information with Pune city's street names
        populateMechanicInformation();
    }

    private ObservableList<String> filterMechanicsByLocation(Map<String, Map<String, String>> mechanicsData, String location) {
        ObservableList<String> filteredMechanics = FXCollections.observableArrayList();
        for (Map.Entry<String, Map<String, String>> entry : mechanicsData.entrySet()) {
            String mechanicLocation = entry.getValue().get("Address");
            if (mechanicLocation != null && mechanicLocation.contains(location)) {
                filteredMechanics.add(entry.getKey());
            }
        }
        return filteredMechanics;
    }
}
