package com.example.jdbc.mvp;

import com.example.jdbc.mvp.model.CustomerDao;
import com.example.jdbc.mvp.model.CustomerJDBCDaoImpl;
import com.example.jdbc.mvp.presenter.CreateUserPresenter;
import com.example.jdbc.mvp.presenter.CreateUserPresenterImpl;
import com.example.jdbc.mvp.view.CreateUserPanel;

import javax.swing.*;

public class MainForm extends JFrame {
    private static final String title = "Praca z bazą danych".toUpperCase();

    private final JTabbedPane tabbedPane = new JTabbedPane();
    private CustomerDao customerDao = new CustomerJDBCDaoImpl();

    public MainForm() {
        super(title);

        initWindow();
        initTabs();
    }

    private void initTabs() {
        // create user tab
        CreateUserPanel createUserPanel = new CreateUserPanel();
        CreateUserPresenter createUserPresenter = new CreateUserPresenterImpl(createUserPanel, customerDao);
        createUserPanel.setPresenter(createUserPresenter);

        // TODO delete empty panels
        // panel 2
        JPanel panel2 = new JPanel();

        // panel 3
        JPanel panel3 = new JPanel();

        // TODO refactor views from MyForm into MVP pattern and add them as tabs

        tabbedPane.add("Utwórz użytkownika", createUserPanel);
        tabbedPane.add("tab 2", panel2);
        tabbedPane.add("tab 3", panel3);
        tabbedPane.setBounds(10, 10, 1020, 300);

        add(tabbedPane);
    }

    private void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(300, 200);
        setResizable(false);

        setSize(1040, 450);
        setLayout(null);
    }

}
