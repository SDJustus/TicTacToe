package de.tud.mbo.core;

import java.awt.*;
import javax.swing.*;

public class TicTacToeView extends JPanel {
    private CustomJButton ticTacToeField[][] = new CustomJButton[3][3];
    private static JPanel restartPanel = new JPanel();
    private JLabel label;
    private JButton restartButton;
    private static GameController gameController;

    public TicTacToeView() {
        this.gameController = new GameController(this);

        initialiseView();
    }
    public void initialiseView(){

        restartButton = new JButton();
        restartButton.setText("Restart Game");
        restartButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        restartButton.addActionListener(e -> gameController.resetGame());

        restartPanel.setLayout(new GridLayout(5,1,20,0));

        label = new JLabel();
        label.setText("Player X's turn!");
        restartPanel.add(label, CENTER_ALIGNMENT);
        restartPanel.add(restartButton, CENTER_ALIGNMENT);
        add(restartPanel, BorderLayout.PAGE_START);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3,3));
        //buttons.setBounds(150,150,400,1000);
        buttons.setSize(500,500);
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CustomJButton button = new CustomJButton();
                button.setText("");
                button.setFont(new Font("Times New Roman", Font.PLAIN, 70));
                button.setBackground(Color.WHITE);
                button.setRow(row);
                button.setCol(col);
                button.addActionListener(e -> {
                    CustomJButton clickedButton = (CustomJButton) e.getSource();
                    gameController.makeMove(clickedButton.getRow(),clickedButton.getCol());
                });
                this.setFieldButtons(row,col,button);
                setLayout(new GridLayout(3,3));
                buttons.add(ticTacToeField[row][col]);
            }
        }
        add(buttons,CENTER_ALIGNMENT);

    }


    public CustomJButton getFieldButton(int row, int col) {
        return ticTacToeField[row][col];
    }

    public void setFieldButtons(int row, int col, CustomJButton button) {
        this.ticTacToeField[row][col] = button;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public void setRestartButton(JButton restartButton) {
        this.restartButton = restartButton;
    }

    public JPanel getRestartPanel() {
        return this.restartPanel;
    }

    public void setRestartPanel(JPanel restartPanel) {
        this.restartPanel = restartPanel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public static GameController getGameController() {
        return gameController;
    }
}









/*
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Tic-Tac-Toe for MBO");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToeView(), BorderLayout.CENTER);
        window.getContentPane().add(restartPanel, BorderLayout.EAST);
        window.setBounds(300,200,600,500);
        window.setVisible(true);

        setLayout(new GridLayout(3, 3));
    }

    public void initializeButtons() {
        /*
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

        restartButton = new JButton();
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
/*           if( checkAdjacent(0,0,0,1) && checkAdjacent(0,1,0,2) ) //no need to put " == true" because the default check is for true
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


}
*/