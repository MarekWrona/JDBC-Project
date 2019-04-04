package com.example.jdbc.mvp.view;

import com.example.jdbc.mvp.presenter.CreateUserPresenter;
import com.example.jdbc.mvp.view.components.LabelledTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CreateUserPanel extends JPanel implements CreateUserView, ActionListener {

    private LabelledTextField nameField, surnameField, ageField, addressField, salaryField;

    private JLabel alertLabel;

    private CreateUserPresenter presenter;

    public CreateUserPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nameField = new LabelledTextField("Podaj imię:", 15);
        surnameField = new LabelledTextField("Podaj nazwisko:", 20);
        ageField = new LabelledTextField("Podaj wiek:", 3);
        addressField = new LabelledTextField("Podaj adres:", 32);
        salaryField = new LabelledTextField("Podaj wynagrodzenie:", 6);

        add(nameField);
        add(surnameField);
        add(ageField);
        add(addressField);
        add(salaryField);

        JButton buttonRunCreate = new JButton("WYKONAJ");
        buttonRunCreate.addActionListener(this);
        add(buttonRunCreate);

        alertLabel = new JLabel("");
        add(alertLabel);
    }

    @Override
    public void setPresenter(CreateUserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getName() {
        return nameField.getText();
    }

    @Override
    public String getSurname() {
        return surnameField.getText();
    }

    @Override
    public int getAge() {
        return Integer.valueOf(ageField.getText());
    }

    @Override
    public String getAddress() {
        return addressField.getText();
    }

    @Override
    public BigDecimal getSalary() {
        return BigDecimal.valueOf(Double.valueOf(salaryField.getText()));
    }

    @Override
    public void showAlert(String message, Color color) {
        alertLabel.setBackground(color);
        alertLabel.setText(message);
        alertLabel.setVisible(true);
    }

    @Override
    public void hideAlert() {
        alertLabel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        presenter.onClick();
    }
}
