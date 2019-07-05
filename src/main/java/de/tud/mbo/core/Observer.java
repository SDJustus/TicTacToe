package de.tud.mbo.core;

import javax.swing.*;

public abstract class Observer extends JPanel {
    protected MouseMotionDrawAndPointSaver subject;
    public abstract void updateButton(char row, int col);
}
