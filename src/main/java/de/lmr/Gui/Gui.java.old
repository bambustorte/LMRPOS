package Gui;

import connector.PrinterConnector;
import connector.TouchscreenConnector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    static PrinterConnector printerConnector;
    static TouchscreenConnector touchscreenConnector;

    public static void main(String[] args) {
//        printerConnector = PrinterConnector.getInstance("/dev/ttyUSB0");

        touchscreenConnector = TouchscreenConnector.getInstance("/dev/ttyUSB0", null);

        Thread t = new Thread(touchscreenConnector);
        t.start();


        launch(args);
        printerConnector.close();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("LMR Kassensystem");
        Button buttonCashDrawer = new Button();
        buttonCashDrawer.setText("Open cash drawer");
        buttonCashDrawer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printerConnector.openCashDrawer((byte)0, (byte)100, (byte)50);
            }
        });

        TextField textField = new TextField();

        Button buttonPrint = new Button("Print");
        buttonPrint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.write(textField.getText(), true);
            }
        });

        Button buttonDickButt = new Button("Dickbutt");
        buttonDickButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.printLogo(6);
                printerConnector.write("Das LMR wuenscht einen schoenen Tag!", true);
            }
        });



        Button buttonC = new Button("C");
        buttonC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("C");
            }
        });
        Button buttonD = new Button("D");
        buttonD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("D");
            }
        });
        Button buttonE = new Button("E");
        buttonE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("E");
            }
        });
        Button buttonF = new Button("F");
        buttonF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("F");
            }
        });
        Button buttonG = new Button("G");
        buttonG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("G");
            }
        });
        Button buttonA = new Button("A");
        buttonA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("A");
            }
        });
        Button buttonB = new Button("B");
        buttonB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTone("B");
            }
        });
        Button buttonTetris = new Button("Tetris");
        buttonTetris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printerConnector.playTetris();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(false);
        gridPane.add(buttonC,0,0);
        gridPane.add(buttonD,1,0);
        gridPane.add(buttonE,2,0);
        gridPane.add(buttonF,3,0);
        gridPane.add(buttonG,4,0);
        gridPane.add(buttonA,5,0);
        gridPane.add(buttonB,6,0);
        gridPane.add(buttonTetris, 7, 0);

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().add(buttonCashDrawer);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(toolbar);
        borderPane.setLeft(textField);
        borderPane.setRight(buttonPrint);
        borderPane.setBottom(buttonDickButt);
        borderPane.setCenter(gridPane);

        primaryStage.setScene(new Scene(borderPane, 300, 250));
        primaryStage.show();
    }
}