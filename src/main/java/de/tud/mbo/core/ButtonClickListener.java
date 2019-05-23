package de.tud.mbo.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CustomJButton buttonClicked = (CustomJButton) e.getSource();
        GameController.getInstance().makeMove(buttonClicked.getRow(),buttonClicked.getCol());
    }
}
