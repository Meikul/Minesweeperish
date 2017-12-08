package cs2410.assn8.view;

import cs2410.assn8.controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Cell extends Button{
    private boolean isBomb;
    public boolean isMined = false;
    public int neighbors;
    public int row;
    public int col;
    public boolean isFlagged = false;
    public char marker = 'n'; // n none, f flag, q question mark

    private static Image flagImgFile = new Image("flag.png");
    private static Image quesImgFile = new Image("questionMark.png");
    private static Image mineImgFile = new Image("mine.png");

    private ImageView flagImg = new ImageView(flagImgFile);
    private ImageView quesImg = new ImageView(quesImgFile);
    private ImageView mineImg = new ImageView(mineImgFile);

    public void setMarker(char c){
        char temp = marker;
        marker = c;
        if(c == 'n') {setGraphic(null); isFlagged = false;}
        else if (c == 'f') {setGraphic(flagImg); isFlagged = true;}
        else if (c == 'q') {setGraphic(quesImg); isFlagged = true;}
        else if (c == 'm') setGraphic(mineImg);
        else marker = temp;
    }

    public Cell(boolean bomb){
        isBomb = bomb;
        neighbors = -1;
        setOnMouseClicked(Controller::clickHandler);
        getStyleClass().add("cell");
        getStyleClass().add("live-cell");
        flagImg.setFitHeight(15);
        flagImg.setFitWidth(10);
        quesImg.setFitHeight(15);
        quesImg.setFitWidth(15);
        mineImg.setFitHeight(15);
        mineImg.setFitWidth(15);
    }

    public boolean isBomb(){
        return isBomb;
    }

    public void explode(){
        if(isBomb){
            setGraphic(mineImg);
            getStyleClass().add("exploded");
        }
        else{
            setMarker('n');
        }
        getStyleClass().remove("live-cell");
    }

//    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//            System.out.print("Button X");
//            getStyleClass().add("mined");
//        }
//    };
}
