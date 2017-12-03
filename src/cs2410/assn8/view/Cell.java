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
    public int neighbors;
    public int row;
    public int col;
    private boolean isFlagged = false;

    private static Image flagImgFile= new Image("flag.png");

    private ImageView flagImg = new ImageView(flagImgFile);

    public boolean toggleFlag(){
        if(isFlagged) setGraphic(null);
        else setGraphic(flagImg);
        isFlagged = !isFlagged;
        return isFlagged;
    }

    public Cell(boolean bomb){
        isBomb = bomb;
        neighbors = -1;
        setOnMouseClicked(Controller::clickHandler);
        flagImg.setFitHeight(15);
        flagImg.setFitWidth(10);
    }

    public boolean isBomb(){
        return isBomb;
    }

//    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//            System.out.print("Button X");
//            getStyleClass().add("mined");
//        }
//    };
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            MouseButton mouseBtn = event.getButton();
            if(mouseBtn == MouseButton.PRIMARY) col=0;
            else if(mouseBtn == MouseButton.SECONDARY) toggleFlag();
        }
    };
}
