package de.tud.mbo.core;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private TicTacToeView ticTacToeView;
    private int playerXorO = 0;
    private String player = "X";
    private Object[] options  = {"Neustart", "Verlassen"};
    private Object[] optionsForAlreadySetField  = {"OK"};

    public GameController(TicTacToeView ticTacToeView){
        this.ticTacToeView = ticTacToeView;
    }


    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }


    public void resetGame(){
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++){
                CustomJButton button = ticTacToeView.getFieldButton(row,col);
                button.setText("");
                button.setEnabled(true);
                ticTacToeView.setFieldButtons(row,col,button);
            }
        }
        player = "X";
        playerXorO = 0;
        ticTacToeView.getLabel().setText("Spieler X ist am Zug");
    }


    //Spiel soll beendet werden
    public void stopGame(){
        System.exit(0);
    }

    public void makeMove(int row, int column){
        this.playGame(row,column);
    }

    /**
     * For Alexa
     * @param row
     * @param column
     */

    public void makeMove(String row, int column){
        switch (row){
            case "a": this.playGame(0,column-1);
            break;
            case "b": this.playGame(1,column-1);
            break;
            case "c": this.playGame(2,column-1);
            break;
            default: System.out.println("Da ist wohl etwas schief gelaufen!!!");
        }

    }

    private void playGame(int row, int col){
        if (!ticTacToeView.getFieldButton(row, col).getText().isEmpty()){
            JOptionPane.showOptionDialog(null,
                    "Bitte wählen Sie ein anderes Feld", "Feld bereits belegt",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, optionsForAlreadySetField, optionsForAlreadySetField[0]);
        }

        else {
            if (playerXorO % 2 == 0) {
                ticTacToeView.getFieldButton(row, col).setText("X");
                ticTacToeView.getLabel().setText("Spieler O ist am Zug!");
                setPlayer("X");
            } else {
                ticTacToeView.getFieldButton(row, col).setText("O");
                ticTacToeView.getLabel().setText("Spieler X ist am Zug!");
                setPlayer("O");
            }
            ticTacToeView.getFieldButton(row, col).setEnabled(false);
            if (checkForWin() == true) {
                int selected = JOptionPane.showOptionDialog(null,
                        "Spieler " + getPlayer() + " hat das Spiel gewonnen!", "Herzlichen Glückwunsch!!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if (selected == 0)
                    resetGame();
                else
                    System.exit(0);
            }
            int notEmptyTextCounter = 0;
            for (int rowP = 0; rowP < 3; rowP++) {
                for (int colP = 0; colP < 3; colP++) {
                    if (!ticTacToeView.getFieldButton(rowP, colP).getText().isEmpty())
                        notEmptyTextCounter++;
                }
            }
            if (notEmptyTextCounter == 9) {
                int selected = JOptionPane.showOptionDialog(null,
                        "Keine Züge mehr übrig!", "Das Spiel ist vorbei!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if (selected == 0)
                    resetGame();
                else
                    System.exit(0);

            }

            playerXorO++;
        }
    }

    public boolean checkForWin() {
        /**   Reference: the button array is arranged like this as the board
         *      0,0 | 0,1 | 0,2
         *      1,0 | 1,1 | 1,2
         *      2,0 | 2,1 | 2,2
         */
        //horizontal win check
        if( checkAdjacent(0,0,0,1) && checkAdjacent(0,1,0,2) ) //no need to put " == true" because the default check is for true
            return true;
        else if( checkAdjacent(1,0,1,1) && checkAdjacent(1,1,1,2) )
            return true;
        else if ( checkAdjacent(2,0,2,1) && checkAdjacent(2,1,2,2))
            return true;

            //vertical win check
        else if ( checkAdjacent(0,0,1,0) && checkAdjacent(1,0,2,0))
            return true;
        else if ( checkAdjacent(0,1,1,1) && checkAdjacent(1,1,2,1))
            return true;
        else if ( checkAdjacent(0,2,1,2) && checkAdjacent(1,2,2,2))
            return true;

            //diagonal win check
        else if ( checkAdjacent(0,0,1,1) && checkAdjacent(1,1,2,2))
            return true;
        else if ( checkAdjacent(0,2,1,1) && checkAdjacent(1,1,2,0))
            return true;
        else
            return false;


    }

    public boolean checkAdjacent(int leftrow, int leftcol, int rightrow, int rightcol) {
        if ( ticTacToeView.getFieldButton(leftrow,leftcol).getText().equals(ticTacToeView.getFieldButton(rightrow,rightcol).getText()) && !ticTacToeView.getFieldButton(leftrow,leftcol).getText().equals("") )
            return true;
        else
            return false;
    }
}
