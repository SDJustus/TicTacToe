package de.tud.mbo.core;



import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class TicTacToeView extends JPanel {
    private CustomJButton ticTacToeField[][] = new CustomJButton[4][4];
    private static JPanel restartPanel;
    private JLabel label;
    private JButton restartButton;
    private static GameController gameController;
    private JPanel buttonPanel;
   // private static JFrame window;

    public static void main(String[] args){
        JFrame window = new JFrame("Tic-Tac-Toe for MBO");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add( new TicTacToeView());
        window.setResizable(true);
        //window.setLayout(new BorderLayout());
        //window.getContentPane().add(ticTacToeView.getRestartPanel(), BorderLayout.EAST);
        window.setBounds(1,1,800,800);
        window.setVisible(true);
    }
    public TicTacToeView() {
        this.gameController = new GameController(this);

        initialiseView();
    }
    public void initialiseView(){
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //setLayout(new BorderLayout());
        //setSize(600,600);
        //setVisible(true);
        restartButton = new JButton();
        restartButton.setText("Restart Game");
        restartButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        restartButton.addActionListener(e -> gameController.resetGame());

        restartPanel = new JPanel();
        restartPanel.setLayout(new BorderLayout());
        //restartPanel.setSize(1000,1000);
        restartPanel.setVisible(true);


        //restartPanel = new JPanel();
        //restartPanel.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //restartPanel.setSize(600,600);
        //restartPanel.setVisible(true);

        label = new JLabel();
        label.setText("Spieler X ist am Zug");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman",Font.PLAIN,30));

        //add(restartPanel, BorderLayout.PAGE_START);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4));
        //buttons.setBounds(150,150,400,1000);
        //buttonPanel.setSize(800,800);
        String[] helperRow = {" ", "A", "B", "C"};
        String[] helperCol = {"1", "2", "3"};


        for(int row = 0; row < 4; row++) {
            CustomJButton button = new CustomJButton();
            button.setText(helperRow[row]);
            button.setEnabled(false);
            button.setRow(row);
            button.setCol(0);
            this.setFieldButtons(row,0,button);
            buttonPanel.add(ticTacToeField[row][0]);
        }
        for (int col = 0; col < 3; col++) {
            CustomJButton button = new CustomJButton();
            button.setText(helperCol[col]);
            button.setEnabled(false);
            button.setRow(0);
            button.setCol(col+1);
            this.setFieldButtons(0,col+1,button);
            buttonPanel.add(ticTacToeField[0][col+1]);
        }


        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CustomJButton button = new CustomJButton();
                button.setText("");
                button.setFont(new Font("Times New Roman", Font.PLAIN, 70));
                button.setBackground(Color.WHITE);
                button.setRow(row+1);
                button.setCol(col+1);
                button.addActionListener(e -> {
                    CustomJButton clickedButton = (CustomJButton) e.getSource();
                    gameController.makeMove(clickedButton.getRow(),clickedButton.getCol());
                });
                this.setFieldButtons(row+1,col+1,button);
                //setLayout(new GridLayout(4,4));
                buttonPanel.add(ticTacToeField[row+1][col+1]);
            }
        }
        /*
        restartPanel.add(label,BorderLayout.NORTH);
        restartPanel.add(restartButton,BorderLayout.SOUTH);
        restartPanel.add(buttonPanel,BorderLayout.CENTER);
        add(restartPanel);
        //restartPanel.add(restartButton);
        //restartPanel.add(buttonPanel);
        //add(restartPanel);
*/
        setLayout(new BorderLayout());
        add(label,BorderLayout.SOUTH);
        add(restartButton, BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);

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