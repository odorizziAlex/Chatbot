package com.company.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window extends JFrame {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    public Window(){
        setLayout(null);
        setVisible(true);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Clofy | The E-Commerce Chatbot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
    }
}
