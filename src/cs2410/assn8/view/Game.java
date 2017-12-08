package cs2410.assn8.view;

import cs2410.assn8.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Main class for the game
 */
public class Game extends Application{

    public static int boardSize = 20;
    private int bombCount = 50;
    public static ArrayList<ArrayList<Cell>> gameBoard;
    private int cellSize = 30;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent template = loader.load();

        Scene scene = new Scene(template);
        scene.getStylesheets().add("style.css");

        Controller.bombsLeft = bombCount;

        System.out.println("Calling create board");
        gameBoard = createBoard(bombCount);

        for(ArrayList<Cell> row: gameBoard){
            for(Cell cell: row){
                if(cell.isBomb()) System.out.print(1);
                else System.out.print(0);
            }
            System.out.println("");
        }

        System.out.println("calling draw board");

        GridPane gamePane = drawBoard();

        BorderPane mainPane = (BorderPane)loader.getNamespace().get("mainPane");
        mainPane.setCenter(gamePane);

        primaryStage.getIcons().add(new Image("mine.png"));

        primaryStage.setTitle("Totally Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int bombCount(){
        return bombCount;
    }

    private GridPane drawBoard(){
        System.out.println("Drawring board");
        int cells = boardSize * boardSize;
        GridPane grid = new GridPane();
        for(int row=0; row<boardSize; row++){
            for(int col=0; col<boardSize; col++){
                Cell cell = gameBoard.get(row).get(col);
                cell.setPrefSize(cellSize, cellSize);
                cell.col = col;
                cell.row = row;
                grid.add(cell, col, row);
            }
        }
        setNeighbors();
        for(Cell cell: gameBoard.get(0)){
            grid.getColumnConstraints().add(new ColumnConstraints(30));
        }
        System.out.println("Drew board");
        return grid;
    }

    private void setNeighbors(){
        for(int y=0; y<boardSize; y++){
            for(int x=0; x<boardSize; x++){
                Cell cell = gameBoard.get(y).get(x);
                cell.neighbors = getCellNieghbors(x,y);
            }
        }
    }

    private int getCellNieghbors(int x, int y){
        int neighbors = 0;
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i == 1 && j == 1)) {
                    int xind = x - 1 + i;
                    int yind = y - 1 + j;
                    if (xind >= 0 && xind < boardSize && yind >= 0 && yind < boardSize) {
                        Cell checkCell = gameBoard.get(yind).get(xind);
                        if (checkCell.isBomb()) neighbors++;
                    }
                }
            }
        }
        return neighbors;
    }

    private ArrayList<ArrayList<Cell>> createBoard(int bombs){
        System.out.println("Creating board");
        int cells = (boardSize * boardSize);
        System.out.println(cells);
        ArrayList<Cell> cellsFlat = new ArrayList<Cell>();
        System.out.println(cellsFlat.size());
        Cell cell;
        for(int i=0; i<cells; i++){
            int row = i / (int)Math.floor(boardSize);
            int col = i%boardSize;
            if(i<bombs) cell = new Cell(true);
            else cell = new Cell(false);
            cellsFlat.add(cell);
        }
        Collections.shuffle(cellsFlat);
        ArrayList<ArrayList<Cell>> cellsFull = new ArrayList<ArrayList<Cell>>();
        System.out.println(cellsFlat.size());
        System.out.println(boardSize);
        for(int i=0; i<boardSize; i++){
            ArrayList<Cell> row = new ArrayList<Cell>();
            for(int j=0; j<boardSize; j++){
                cell = cellsFlat.get(boardSize*i+j);
                row.add(cell);
            }
            cellsFull.add(row);
        }
        return cellsFull;
    }
}
