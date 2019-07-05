package de.tud.mbo.core;



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import javax.swing.*;

public class TicTacToeView extends JPanel {
    private CustomJButton ticTacToeField[][] = new CustomJButton[4][4];
    private static JPanel restartPanel;
    private JLabel label;
    private JButton restartButton;
    private static GameController gameController;
    private JPanel buttonPanel;
    private Point prevPoint;
    private Point newPoint;


    public TicTacToeView() {
        this.gameController = new GameController(this);

        initialiseView();
    }
    public void initialiseView(){
        restartButton = new JButton();
        restartButton.setText("Restart Game");
        restartButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        restartButton.addActionListener(e -> gameController.resetGame());

        label = new JLabel();
        label.setText("Spieler X ist am Zug");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman",Font.PLAIN,30));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4));

        String[] helperRow = {" ", "A", "B", "C"};
        String[] helperCol = {"1", "2", "3"};

        for(int col = 0; col < 4; col++) {
            CustomJButton button = new CustomJButton();
            if(col == 0){
               button.setText("Gesteneingabe");
               button.setEnabled(true);

            } else {
                button.setText(helperRow[col]);
                button.setEnabled(false);
            }
                button.setRow(0);
                button.setCol(col);
                this.setFieldButtons(0, col, button);
                buttonPanel.add(ticTacToeField[0][col]);
                if(col == 0){button.addActionListener(e -> {
                    initialiseGesture();
                });}


        }
        int helperColCounter = 0;
        for(int row = 1; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if(col == 0){CustomJButton button = new CustomJButton();
                    button.setText(helperCol[helperColCounter]);
                    button.setEnabled(false);
                    button.setRow(row);
                    button.setCol(col);
                    this.setFieldButtons(row,col,button);
                    buttonPanel.add(ticTacToeField[row][col]);
                    helperColCounter++;
                }else {
                    CustomJButton button = new CustomJButton();
                    button.setText("");
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 70));
                    button.setBackground(Color.WHITE);
                    button.setRow(row);
                    button.setCol(col);
                    button.addActionListener(e -> {
                        CustomJButton clickedButton = (CustomJButton) e.getSource();
                        gameController.makeMove(clickedButton.getRow(), clickedButton.getCol());
                    });
                    this.setFieldButtons(row, col, button);
                    buttonPanel.add(ticTacToeField[row][col]);
                } }
        }

        setLayout(new BorderLayout());
        add(label,BorderLayout.SOUTH);
        add(restartButton, BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);
    }

    public void initialiseGesture(){
        new MouseMotionDrawAndPointSaver();
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