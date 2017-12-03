package cs2410.assn8.controller;

import cs2410.assn8.view.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Button startBtn;
    @FXML
    private Text scoreText;
    @FXML
    private Text bombsText;

    public EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            MouseButton mouseBtn = event.getButton();
            System.out.println(event.getTarget().getClass());
//            if(mouseBtn == MouseButton.PRIMARY)
//            else if(mouseBtn == MouseButton.SECONDARY)
        }
    };

    public void initialize(){
        startBtn.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                System.out.println("Start clicked");
            }
        });
    }

    public static void clickHandler(MouseEvent event) {
        Cell cell = (Cell)(event.getSource());
        System.out.println(cell.row + " " + cell.col);
        MouseButton mouseBtn = event.getButton();
        if(mouseBtn == MouseButton.PRIMARY) mine(cell);
        else if(mouseBtn == MouseButton.SECONDARY) flag(cell);
//        if(mouseBtn == MouseButton.PRIMARY)
//        else if(mouseBtn == MouseButton.SECONDARY)
    }

    private static void mine(Cell cell){
        if(cell.isBomb()){

        }
        else{
            cell.getStyleClass().add("mined");
        }
    }
    private static void flag(Cell cell){
        cell.toggleFlag();
    }
}
