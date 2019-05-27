package de.tud.mbo.core;

import javafx.scene.layout.Border;

import java.awt.*;
import javax.swing.*;

public class TicTacToeView extends JPanel {
    private CustomJButton ticTacToeField[][] = new CustomJButton[3][3];
    private static JPanel restartPanel = new JPanel();
    private JLabel label;
    private JButton restartButton;
    private static GameController gameController;


    public static void main(String[] args){
        JFrame window = new JFrame("Tic-Tac-Toe for MBO");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add( new TicTacToeView(), BorderLayout.CENTER);
        //window.getContentPane().add(ticTacToeView.getRestartPanel(), BorderLayout.EAST);
        window.setBounds(300,200,600,500);
        window.setVisible(true);
    }
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        //buttons.setBounds(150,150,400,1000);
        buttonPanel.setSize(500,500);
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
                buttonPanel.add(ticTacToeField[row][col]);
            }
        }
        add(buttonPanel, BorderLayout.WEST);

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