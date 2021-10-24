package com.dariusz.wolanski.tictaktoe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TicTakToeTest extends Application {

    GridPane gridPane = new GridPane();


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        ObservableList<Button> buttons = FXCollections.observableArrayList();


        for (int i = 0; i < 9; i++) {
            Button button = new Button();
            button.setMinWidth(100);
            button.setMinHeight(100);
            buttons.add(button);

        }
        gridPane.add(buttons.get(0), 0, 0);
        gridPane.add(buttons.get(1), 1, 0);
        gridPane.add(buttons.get(2), 2, 0);
        gridPane.add(buttons.get(3), 0, 1);
        gridPane.add(buttons.get(4), 1, 1);
        gridPane.add(buttons.get(5), 2, 1);
        gridPane.add(buttons.get(6), 0, 2);
        gridPane.add(buttons.get(7), 1, 2);
        gridPane.add(buttons.get(8), 2, 2);

        buttonPlayerAndComputer();

        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 300, 400, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TikTakToe");
        primaryStage.show();
    }

    // Ruch gracza i komputera

    public void buttonPlayerAndComputer() {

        List<Button> buttons = gridPane.getChildren()
                .stream().filter(s -> s instanceof Button)
                .map(s -> (Button) s)
                .collect(Collectors.toList());

        for (Button button : buttons) {
            button.setOnAction(event -> {
                if (button.getText() == null || button.getText().equals("")) {
                    int x = 0;
                    button.setText("X");
                    buttonComputer(buttons);

                    if (whetherThePlayerWon()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Win");
                        alert.showAndWait();
                        Platform.exit();
                    }
                    if (whetherTheComputerWon()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Loose");
                        alert.showAndWait();
                        Platform.exit();
                    }
                }

            });
        }

    }

    // Ruch komputera

    public void buttonComputer(List<Button> buttons) {

        int count = 0;
        Random random = new Random();
        List<Button> buttonComputer = new ArrayList<>();

        for (Button button : buttons) {
            if (button.getText() == null || button.getText().equals("")) {
                buttonComputer.add(button);
                count++;
            }
        }

        if (count >= 2) {
            int numberButtonOfComputer = random.nextInt(buttonComputer.size());
            buttonComputer.get(numberButtonOfComputer).setText("O");
        }
    }

    // Sprawdzenie czy gracz wygrał

    public boolean whetherThePlayerWon() {

        List<Button> buttons = gridPane.getChildren()
                .stream().filter(s -> s instanceof Button)
                .map(s -> (Button) s).collect(Collectors.toList());

        return horizontalCheckForX(buttons) || verticalCheckForX(buttons) || crossChecking(buttons);
    }

    private boolean horizontalCheckForX(List<Button> buttons) {
        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i % 3 == 0) {
                counter = 0;
            }
            if ("X".equals(button.getText())) {
                counter++;
            }
            if (counter == 3) {
                break;
            }

        }
        return counter == 3;
    }

    private boolean verticalCheckForX(List<Button> buttons) {

        return verticalCheckFirstColumnForX(buttons) || verticalCheckSecondColumnForX(buttons)
                || verticalCheckThirdColumnForX(buttons);
    }

    private boolean verticalCheckFirstColumnForX(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if ((i == 0 || i == 3 || i == 6)) {
                if ("X".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;
    }

    private boolean verticalCheckSecondColumnForX(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if ((i == 1 || i == 4 || i == 7)) {
                if ("X".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;
    }

    private boolean verticalCheckThirdColumnForX(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if ((i == 2 || i == 5 || i == 8)) {
                if ("X".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;
    }

    private boolean crossChecking(List<Button> buttons) {

        return crossCheckingFirstLine(buttons) || crossCheckingSecondLine(buttons);
    }

    private boolean crossCheckingFirstLine(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == 0 || i == 4 || i == 8) {
                if ("X".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;

    }

    private boolean crossCheckingSecondLine(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == 2 || i == 4 || i == 6) {
                if ("X".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;

    }


    // Sprawdzenie czy komputer wygrał

    public boolean whetherTheComputerWon() {

        List<Button> buttons = gridPane.getChildren()
                .stream().filter(s -> s instanceof Button)
                .map(s -> (Button) s).collect(Collectors.toList());

        return horizontalCheckForO(buttons) || verticalCheckForO(buttons) || crossCheckingForO(buttons);
    }

    private boolean horizontalCheckForO(List<Button> buttons) {
        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i % 3 == 0) {
                counter = 0;
            }
            if ("O".equals(button.getText())) {
                counter++;
            }
            if (counter == 3) {
                break;
            }

        }
        return counter == 3;
    }

    private boolean verticalCheckForO(List<Button> buttons) {

        return verticalCheckFirstColumnForO(buttons) || verticalCheckSecondColumnForO(buttons)
                || verticalCheckThirdColumnForO(buttons);
    }

    private boolean verticalCheckFirstColumnForO(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if ((i == 0 || i == 3 || i == 6)) {
                if ("O".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;
    }

    private boolean verticalCheckSecondColumnForO(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if ((i == 1 || i == 4 || i == 7)) {
                if ("O".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;
    }

    private boolean verticalCheckThirdColumnForO(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if ((i == 2 || i == 5 || i == 8)) {
                if ("O".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;
    }

    private boolean crossCheckingForO(List<Button> buttons) {

        return crossCheckingFirstLineForO(buttons) || crossCheckingSecondLineForO(buttons);
    }

    private boolean crossCheckingFirstLineForO(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == 0 || i == 4 || i == 8) {
                if ("O".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;

    }

    private boolean crossCheckingSecondLineForO(List<Button> buttons) {

        int counter = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == 2 || i == 4 || i == 6) {
                if ("O".equals(button.getText())) {
                    counter++;
                }
            }
            if (counter == 3) {
                break;
            }
        }
        return counter == 3;

    }

}
