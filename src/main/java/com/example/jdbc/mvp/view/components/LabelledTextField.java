package com.example.jdbc.mvp.view.components;

import javax.swing.*;

public class LabelledTextField extends JPanel {

    private final JTextField jTextField;

    public LabelledTextField(String label, int size) {
        JLabel jLabel = new JLabel(label);
        jTextField = new JTextField(size);

        add(jLabel);
        add(jTextField);
    }

    public String getText() {
        return jTextField.getText();
    }

}
