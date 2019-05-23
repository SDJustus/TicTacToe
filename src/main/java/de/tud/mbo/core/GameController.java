package de.tud.mbo.core;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private TicTacToeView ticTacToeView;

    private GameController(){
        ticTacToeView = new TicTacToeView();
    }
    public static GameController getInstance(){
        return new GameController();
    }
    private void initialiseView(){
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                JButton button = new JButton();
                button.setText("");
                button.setFont(new Font("Times New Roman", Font.PLAIN, 70));
                button.setBackground(Color.WHITE);

            }
        }
    }



    public void resetGame(){

    }
    //Spiel soll beendet werden
    public void stopGame(){
        System.exit(0);
    }

    public void makeMove(){

    }
    public void makeMove(char row, int column){

    }
}
