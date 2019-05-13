package de.tud.mbo.core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel {
    private JButton ticTacToeField[][] = new JButton[3][3];
    private static JPanel restartPanel = new JPanel();
    private int playerXorO = 0;
    private String player = "X";
    private Object[] options  = {"Restart", "Exit Game"};
    private JLabel label = new JLabel();

    public TicTacToe()
    {
        setLayout(new GridLayout(3,3));
        initializeButtons();
    }

    public static void main(String[] args)
    {
        JFrame window = new JFrame("Tic-Tac-Toe for MBO");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe(), BorderLayout.CENTER);
        window.getContentPane().add(restartPanel, BorderLayout.EAST);
        window.setBounds(300,200,600,500);
        window.setVisible(true);
    }

    public void initializeButtons() {
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                ticTacToeField[row][col] = new JButton();
                ticTacToeField[row][col].setText("");
                ticTacToeField[row][col].setFont(new Font("Times New Roman", Font.PLAIN, 70));
                ticTacToeField[row][col].setBackground(Color.WHITE);
                ticTacToeField[row][col].addActionListener(new ButtonClickListener());
                add(ticTacToeField[row][col]);
            }
        }
        JButton restartButton = new JButton();
        restartButton.setText("Restart Game");
        restartButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtons();
            }
        });
        restartPanel.setLayout(new GridLayout(5,1,20,0));


        label.setText("Player X's turn!");
        restartPanel.add(label, BorderLayout.CENTER);
        restartPanel.add(restartButton, BorderLayout.CENTER);

    }

    public void resetButtons() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++){
                ticTacToeField[row][col].setText("");
                ticTacToeField[row][col].setEnabled(true);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(playerXorO %2 == 0) {
                buttonClicked.setText("X");
                label.setText("Player O's turn!");
                setPlayer("X");
            }
            else {
                buttonClicked.setText("O");
                label.setText("Player X's turn!");
                setPlayer("O");
            }
            buttonClicked.setEnabled(false);
            if(checkForWin() == true ) {
                int selected = JOptionPane.showOptionDialog(null,
                        "Player "+getPlayer()+" has won the game", "Congratulations!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if(selected == 0)
                    resetButtons();
                else
                    System.exit(0); }
            int notEmptyTextCounter = 0;
            for (int row = 0; row < 3; row ++){
                for(int col = 0;col < 3; col++){
                    if (!ticTacToeField[row][col].getText().isEmpty())
                        notEmptyTextCounter++;
                }
            }
            if (notEmptyTextCounter == 9){
                int selected = JOptionPane.showOptionDialog(null,
                        "No more turns left!", "Game Over!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if(selected == 0)
                    resetButtons();
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
            if ( ticTacToeField[leftrow][leftcol].getText().equals(ticTacToeField[rightrow][rightcol].getText()) && !ticTacToeField[leftrow][leftcol].getText().equals("") )
                return true;
            else
                return false;
        }

    }
    private class VoiceRecognitionListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: implement methods for voice Recognition
        }
    }
    private class GestureRecognitionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: implement methods for gesture Recognistion
        }
    }

    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }
}
