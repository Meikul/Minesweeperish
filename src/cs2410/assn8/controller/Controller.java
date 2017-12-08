package cs2410.assn8.controller;

import cs2410.assn8.view.Cell;
import cs2410.assn8.view.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Button startBtn;
    @FXML
    private Text timeText;
    @FXML
    private Text bombsText;

    private static boolean live = true;

    private static Text bombsTextRef;
    private static Text timeTextRef;
    private static int time = 0;

    public static int bombsLeft;

    public void initialize(){
        startBtn.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                System.out.println("Start clicked");
            }
        });
        bombsTextRef = bombsText;
        timeTextRef = timeText;
    }

    public static void clickHandler(MouseEvent event) {
        if(live){
            Cell cell = (Cell)(event.getSource());
            System.out.println(cell.row + " " + cell.col);
            MouseButton mouseBtn = event.getButton();
            if(mouseBtn == MouseButton.PRIMARY) mine(cell);
            else if(mouseBtn == MouseButton.SECONDARY) flag(cell);
//            if(mouseBtn == MouseButton.PRIMARY)
//            else if(mouseBtn == MouseButton.SECONDARY)
        }
    }

    private static void explode(){
        live = false;
        for(ArrayList<Cell> row: Game.gameBoard){
            for(Cell cell: row){
                cell.explode();
            }
        }
    }

    private static void mine(Cell cell){
        if(cell.isFlagged){
        }
        else if(cell.isBomb()) {
            explode();
        }
        else{
            System.out.println("Not Mined");
            cell.isMined = true;
            cell.getStyleClass().add("mined");
            if(cell.neighbors == 0){
                mineNeighbors(cell);
            }
            else{
                cell.setText(Integer.toString(cell.neighbors));
            }
        }
    }

    private static void mineNeighbors(Cell cell){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(!(i == 1 && j == 1)) {
                    int xind = cell.col-1+i;
                    int yind = cell.row-1+j;
                    if(xind >= 0 && xind < Game.boardSize && yind >= 0 && yind < Game.boardSize){
                        Cell checkCell = Game.gameBoard.get(yind).get(xind);
                        if(!checkCell.isMined){
                            mine(checkCell);
                        }
                    }
                }
            }
        }
    }

    private static void flag(Cell cell){
        if(cell.marker == 'n'){
            cell.setMarker('f');
            bombsLeft--;
        }
        else if(cell.marker == 'f'){
            cell.setMarker('q');
        }
        else if(cell.marker == 'q'){
            cell.setMarker('n');
            bombsLeft++;
        }
        bombsTextRef.setText(Integer.toString(bombsLeft));
    }

}
