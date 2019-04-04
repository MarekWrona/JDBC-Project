package com.example.jdbc.mvp;

import java.awt.*;

public class JDBCRunner {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm();
            }
        });

    }
}