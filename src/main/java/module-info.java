module com.example.pathfindingdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pathfindingdemo to javafx.fxml;
    exports com.example.pathfindingdemo;
}