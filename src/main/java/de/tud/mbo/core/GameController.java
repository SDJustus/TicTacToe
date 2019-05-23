package de.tud.mbo.core;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private TicTacToeView ticTacToeView;
    private int playerXorO = 0;
    private String player = "X";
    private Object[] options  = {"Restart", "Exit Game"};

    private GameController(){
        ticTacToeView = new TicTacToeView();
        initialiseView();
    }

    public static GameController getInstance(){
        return new GameController();
    }
    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }


    private void initialiseView(){
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CustomJButton button = new CustomJButton();
                button.setText("");
                button.setFont(new Font("Times New Roman", Font.PLAIN, 70));
                button.setBackground(Color.WHITE);
                button.setRow(row);
                button.setCol(col);
                button.addActionListener(new ButtonClickListener());
                ticTacToeView.setFieldButtons(row,col,button);
            }
        }

        JButton restartButton = new JButton();
        restartButton.setText("Restart Game");
        restartButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        restartButton.addActionListener(e -> resetGame());
        ticTacToeView.setRestartButton(restartButton);

        JPanel restartPanel = new JPanel();
        restartPanel.setLayout(new GridLayout(5,1,20,0));

        JLabel label = new JLabel();
        label.setText("Player X's turn!");
        restartPanel.add(label, BorderLayout.CENTER);
        restartPanel.add(restartButton, BorderLayout.CENTER);
        ticTacToeView.setRestartPanel(restartPanel);
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
    }


    //Spiel soll beendet werden
    public void stopGame(){
        System.exit(0);
    }

    public void makeMove(int row, int column){
        this.playGame(row,column);
    }
    public void makeMove(String row, int column){
        switch (row){
            case "A": this.playGame(1,column);
            break;
            case "B": this.playGame(2,column);
            break;
            case "C": this.playGame(3,column);
            break;
            default: System.out.println("Da ist wohl etwas schief gelaufen!!!");
        }

    }

    private void playGame(int row, int col){
        if(playerXorO %2 == 0) {
            ticTacToeView.getFieldButton(row,col).setText("X");
            ticTacToeView.getLabel().setText("Player O's turn!");
            setPlayer("X");
        }
        else {
            ticTacToeView.getFieldButton(row,col).setText("O");
            ticTacToeView.getLabel().setText("Player X's turn!");
            setPlayer("O");
        }
        ticTacToeView.getFieldButton(row,col).setEnabled(false);
        if(checkForWin() == true ) {
            int selected = JOptionPane.showOptionDialog(null,
                    "Player "+getPlayer()+" has won the game", "Congratulations!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if(selected == 0)
                resetGame();
            else
                System.exit(0); }
        int notEmptyTextCounter = 0;
        for (int rowP = 0; rowP < 3; rowP ++){
            for(int colP = 0;colP < 3; colP++){
                if (!ticTacToeView.getFieldButton(rowP,colP).getText().isEmpty())
                    notEmptyTextCounter++;
            }
        }
        if (notEmptyTextCounter == 9){
            int selected = JOptionPane.showOptionDialog(null,
                    "No more turns left!", "Game Over!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if(selected == 0)
                resetGame();
            else
                System.exit(0);

        }

        playerXorO++;
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
