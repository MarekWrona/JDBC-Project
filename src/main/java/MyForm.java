import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import static java.awt.Color.*;
import java.util.List;

public class MyForm extends JFrame implements ActionListener {

    private static String tytul = "Praca z bazą danych".toUpperCase();
    private Customer cust;
    private CustomerJDBCDaoImpl customerJDBCDao;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JButton buttonDeleteRecord, buttonFindRecord, buttonFiltrData, buttonUpdateRecord,
            buttonCreateRecord, buttonShowAll;
    private JButton buttonRunFindById, buttonRunFiltr, buttonRunUpdate, buttonRunCreate, buttonRunDelete;
    private JLabel lblId, lblName, lblSurname, lblAge, lblAddress, lblSalary, lblArea, lblKomunikat;
    private JTextField txtId, txtName, txtSurname, txtAge, txtAddress, txtSalary;
    private JTextArea txtArea;
    private int rows;

    public MyForm (){

        super(tytul);
        setBackground(Color.yellow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(300, 200);
        setResizable(false);

        setSize(1040, 360);
        setLayout(null);

        panel = new JPanel();
        panel.setBounds(250, 70, 500, 200);
        panel.setOpaque(false);
        add(panel);

        txtArea = new JTextArea(rows,35);
        //txtArea.setBounds(0, 0, 400, 100);
        txtArea.setOpaque(false);
        txtArea.setAutoscrolls(true);
        txtArea.setEditable(false);
        scrollPane = new JScrollPane(txtArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        panel.add(scrollPane);

        buttonFindRecord = new JButton("Znajdź użytkownika");
        buttonFindRecord.setBounds(20, 20, 150, 20);
        add(buttonFindRecord);
        buttonFindRecord.addActionListener(this);

        buttonFiltrData = new JButton("Filtruj");
        buttonFiltrData.setBounds(190, 20, 150, 20);
        add(buttonFiltrData);
        buttonFiltrData.addActionListener(this);

        buttonShowAll = new JButton("Pokaż wszystkich");
        buttonShowAll.setBounds(360, 20, 150, 20);
        add(buttonShowAll);
        buttonShowAll.addActionListener(this);

        buttonUpdateRecord = new JButton("Aktualizuj dane");
        buttonUpdateRecord.setBounds(530, 20, 150, 20);
        add(buttonUpdateRecord);
        buttonUpdateRecord.addActionListener(this);

        buttonCreateRecord = new JButton("Utwórz użytkownika");
        buttonCreateRecord.setBounds(700, 20, 150, 20);
        add(buttonCreateRecord);
        buttonCreateRecord.addActionListener(this);

        buttonDeleteRecord = new JButton("Usuń użytkownika");
        buttonDeleteRecord.setBounds(870, 20, 150, 20);
        add(buttonDeleteRecord);
        buttonDeleteRecord.addActionListener(this);

        buttonRunFindById = new JButton("WYKONAJ");
        buttonRunFindById.setBounds(840, 260, 180, 20);
        add(buttonRunFindById);
        buttonRunFindById.addActionListener(this);

        buttonRunFiltr = new JButton("WYKONAJ");
        buttonRunFiltr.setBounds(840, 260, 180, 20);
        add(buttonRunFiltr);
        buttonRunFiltr.addActionListener(this);

        buttonRunUpdate = new JButton("WYKONAJ");
        buttonRunUpdate.setBounds(840, 260, 180, 20);
        add(buttonRunUpdate);
        buttonRunUpdate.addActionListener(this);

        buttonRunCreate = new JButton("WYKONAJ");
        buttonRunCreate.setBounds(840, 260, 180, 20);
        add(buttonRunCreate);
        buttonRunCreate.addActionListener(this);

        buttonRunDelete = new JButton("WYKONAJ");
        buttonRunDelete.setBounds(840, 260, 180, 20);
        add(buttonRunDelete);
        buttonRunDelete.addActionListener(this);

        lblArea = new JLabel("Wykaz wszystkich użytkowników:");
        lblArea.setBounds(20, 70, 250, 20);
        add(lblArea);

        lblId = new JLabel("Podaj nr Id rekordu:");
        lblId.setBounds(20, 100, 200, 20);
        add(lblId);

        txtId = new JTextField("");
        txtId.setBounds(150, 100, 60, 20);
        add(txtId);

        lblName = new JLabel("Podaj imię:");
        lblName.setBounds(320, 100, 200, 20);
        add(lblName);

        txtName = new JTextField("");
        txtName.setBounds(400, 100, 100, 20);
        add(txtName);

        lblSurname = new JLabel("Podaj nazwisko:");
        lblSurname.setBounds(700, 100, 200, 20);
        add(lblSurname);

        txtSurname = new JTextField("");
        txtSurname.setBounds(850, 100, 100, 20);
        add(txtSurname);

        lblAge = new JLabel("Podaj wiek:");
        lblAge.setBounds(20, 180, 200, 20);
        add(lblAge);

        txtAge = new JTextField("");
        txtAge.setBounds(150, 180, 60, 20);
        add(txtAge);

        lblAddress = new JLabel("Podaj adres:");
        lblAddress.setBounds(320, 180, 200, 20);
        add(lblAddress);

        txtAddress = new JTextField("");
        txtAddress.setBounds(400, 180, 200, 20);
        add(txtAddress);

        lblSalary = new JLabel("Podaj wynagrodzenie:");
        lblSalary.setBounds(700, 180, 180, 20);
        add(lblSalary);

        lblKomunikat = new JLabel("");
        lblKomunikat.setBounds(20, 260, 500, 20);
        lblKomunikat.setOpaque(true);
        add(lblKomunikat);

        txtSalary = new JTextField("");
        txtSalary.setBounds(850, 180, 60, 20);
        add(txtSalary);

        hideAll();

        customerJDBCDao = new CustomerJDBCDaoImpl();

    }

    private void hideAll() {
        panel.setVisible(false);
        txtArea.setRows(2);
        //txtArea.setVisible(false);
        txtArea.setText("");
        lblArea.setVisible(false);
        lblArea.setText("");
        lblId.setVisible(false);
        lblName.setVisible(false);
        lblSurname.setVisible(false);
        lblAge.setVisible(false);
        lblAddress.setVisible(false);
        lblSalary.setVisible(false);
        lblKomunikat.setVisible(false);
        txtId.setVisible(false);
        txtId.setText("");
        txtName.setVisible(false);
        txtName.setText("");
        txtSurname.setVisible(false);
        txtSurname.setText("");
        txtAge.setVisible(false);
        txtAge.setText("");
        txtAddress.setVisible(false);
        txtAddress.setText("");
        txtSalary.setVisible(false);
        txtSalary.setText("");
        buttonRunCreate.setVisible(false);
        buttonRunDelete.setVisible(false);
        buttonRunFiltr.setVisible(false);
        buttonRunFindById.setVisible(false);
        buttonRunUpdate.setVisible(false);

    }

    private void showAll(){
        lblName.setVisible(true);
        lblSalary.setVisible(true);
        lblId.setVisible(true);
        lblAge.setVisible(true);
        lblAddress.setVisible(true);
        lblSurname.setVisible(true);
        txtName.setVisible(true);
        txtSalary.setVisible(true);
        txtId.setVisible(true);
        txtAddress.setVisible(true);
        txtAge.setVisible(true);
        txtSurname.setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == buttonFindRecord) {
            hideAll();
            lblId.setVisible(true);
            txtId.setVisible(true);
            txtId.requestFocus();
            buttonRunFindById.setVisible(true);
            super.setTitle(tytul + " - szukanie rekordu o określonym nr ID".toUpperCase());

        }else if (source == buttonFiltrData) {
            hideAll();
            showAll();
            lblId.setVisible(false);
            txtId.setVisible(false);
            txtName.requestFocus();
            buttonRunFiltr.setVisible(true);
            super.setTitle(tytul + " - wyszukiwanie złożonych danych".toUpperCase());

        }else if(source == buttonShowAll) {
            hideAll();
            super.setTitle(tytul + " - reprezntacja wszystkich danych bazy".toUpperCase());
            customerJDBCDao = new CustomerJDBCDaoImpl();
            rows = customerJDBCDao.getallCustomers().size();
            if (rows > 10) rows = 10;
            txtArea.setRows(rows);
            txtArea.setText(printCustomers(customerJDBCDao.getallCustomers()));
            lblArea.setText("Lista wyników spełniających zapytanie:");
            lblArea.setVisible(true);
            panel.setVisible(true);
            //txtArea.setVisible(true);

        }else if ((source == buttonUpdateRecord)) {
            hideAll();
            showAll();
            txtId.requestFocus();
            buttonRunUpdate.setVisible(true);
            super.setTitle(tytul + " - modyfikacja wybranych pól bazy".toUpperCase());

        }else if ((source == buttonCreateRecord)) {
            hideAll();
            showAll();
            lblId.setVisible(false);
            txtId.setVisible(false);
            txtName.requestFocus();
            buttonRunCreate.setVisible(true);
            super.setTitle(tytul + " - tworzenie nowego rekordu".toUpperCase());

        }else if ((source == buttonDeleteRecord)) {
            hideAll();
            lblId.setVisible(true);
            txtId.setVisible(true);
            txtId.requestFocus();
            buttonRunDelete.setVisible(true);
            super.setTitle(tytul + " - usuwanie rekordu o określonym nr ID".toUpperCase());

        }else if (source == buttonRunFindById) {
            //tutaj sprawdzenie wpisywanych danych / czy liczba
            if (txtId.getText().isEmpty()) {
                lblKomunikat.setText("Pole numeru ID wyszukiwanego rekordu jest puste!");
                lblKomunikat.setVisible(true);
            } else {
                if (checkIsInt(txtId.getText())) {
                    long customerId = Long.parseLong(txtId.getText());
                    hideAll();
                    lblArea.setText("Użytkownik o nr Id " + customerId);
                    lblArea.setVisible(true);
                    txtArea.setText(customerJDBCDao.findById(customerId).toString());
                    panel.setVisible(true);
                } else {
                    lblKomunikat.setText("Uzytkownk o padanym nr ID nie istnieje");
                    lblKomunikat.setVisible(true);
                    txtId.setText("");
                    buttonFindRecord.requestFocus();
                }
            }

        }else if (source == buttonRunFiltr) {
            //System.out.println("Zapytanie dotyczące wyświetlenia rekordów o wybranym wartościach, np: Name i Age");
            String name = "";
            String surname = "";
            int age = 0;
            String address = "";
            BigDecimal salary = null;
            boolean uzytePole[] = {false, false, false, false, false};
            StringBuffer zapytanie = new StringBuffer();
            String fragment[] = {"","","","",""};

            //sprawdzenie czy pola są zapełnione i ustawienie fragmentów zapytania
            if (!txtName.getText().isEmpty()) {
                name = txtName.getText();
                uzytePole[0] = true;
                fragment[0] = "NAME=?";
            }

            if (!txtSurname.getText().isEmpty()) {
                surname = txtSurname.getText();
                uzytePole[1] = true;
                fragment[1] = "SURNAME=?";
            }

            if (!txtAge.getText().isEmpty()) {
                if (checkIsInt(txtAge.getText())) {
                    age = Integer.parseInt(txtAge.getText());
                    uzytePole[2] = true;
                    fragment[2] = "AGE=?";
                } else {
                    lblKomunikat.setVisible(true);
                    txtAge.setText("");
                    txtAge.requestFocus();
                }
            }

            if (!txtAddress.getText().isEmpty()) {
                address = txtAddress.getText();
                uzytePole[3] = true;
                fragment[3] = "ADDRESS=?";
            }

            if (!txtSalary.getText().isEmpty()) {
                try {
                    salary = new BigDecimal(txtSalary.getText());
                    uzytePole[4] = true;
                    fragment[4] = "SALARY=?";
                } catch (Exception ex2) {
                    lblKomunikat.setText("Pole Wynagrodzenie musi zawierać liczbę!");
                    lblKomunikat.setBackground(red);
                    lblKomunikat.setVisible(true);
                    txtSalary.setText("");
                    txtSalary.requestFocus();
                }
            }

            //sprawdzenie które pola zostały wypełnione i umozliwienie wstawienia "AND" między pola
            for (int i = 0; i <= 4; i++) {
                if (uzytePole[i]) {
                    zapytanie.append(fragment[i]);
                    if (i != 4) {
                        for (int j=i+1; j<=4; j++) {
                            if (uzytePole[j]) {
                                zapytanie.append(" AND ");
                                break;
                            }
                        }
                    }
                }
            }

            System.out.println(zapytanie);
            hideAll();
            txtArea.setText(customerJDBCDao.filtrSearch(zapytanie, uzytePole, name, surname, age, address, salary));
            lblArea.setText("Lista wyników spełniających zapytanie:");
            lblArea.setVisible(true);
            if (customerJDBCDao.filtrSearch(zapytanie, uzytePole, name, surname, age, address, salary).length() <= 10)
                rows = customerJDBCDao.filtrSearch(zapytanie, uzytePole, name, surname, age, address, salary).length();
            else rows = 10;
            txtArea.setRows(rows);

            panel.setVisible(true);

        }else if (source == buttonRunUpdate) {
            //System.out.println("Aktualizacja rekordu w bazie");

            String name = "";
            String surname = "";
            int age = 0;
            String address = "";
            BigDecimal salary = null;
            long customerId = 0;

            //spr czy jakiekolwiek pole oprócz Id zostało wypełnione
            if (txtName.getText().isEmpty()&&txtSurname.getText().isEmpty()
                    &&txtAge.getText().isEmpty()&&txtAddress.getText().isEmpty()&&txtSalary.getText().isEmpty()) {
                lblKomunikat.setText("Aby zaktualizować użytkownika należy podać przynajmniej jedną wartość do zmiany");
                lblKomunikat.setVisible(true);
            } else {
                if (!txtId.getText().isEmpty()) {       //spr czy pole Id nie jest puste
                    if (checkIsInt(txtId.getText())) {     //spr czy pole id jest liczbą
                        customerId = Long.parseLong(txtId.getText());
                        cust = customerJDBCDao.findById(customerId);

                        if (!txtName.getText().isEmpty()) {
                            name = txtName.getText();
                            cust.setName(name);
                        }

                        if (!txtSurname.getText().isEmpty()) {
                            surname = txtSurname.getText();
                            cust.setSurname(surname);
                        }

                        if (!txtAge.getText().isEmpty()) {
                            if (checkIsInt(txtAge.getText())) {
                                age = Integer.parseInt(txtAge.getText());
                                cust.setAge(age);
                            }else {
                                lblKomunikat.setVisible(true);
                                txtAge.setText("");
                                txtAge.requestFocus();
                            }
                        }

                        if (!txtAddress.getText().isEmpty()) {
                            address = txtAddress.getText();
                            cust.setAddress(address);
                        }

                        if (!txtSalary.getText().isEmpty()) {
                            try {
                                salary = new BigDecimal(txtSalary.getText());
                                cust.setSalary(salary);
                            } catch (Exception ex2) {
                                lblKomunikat.setText("Pole Wynagrodzenie musi zawierać liczbę!");
                                lblKomunikat.setBackground(red);
                                lblKomunikat.setVisible(true);
                                txtSalary.setText("");
                                txtSalary.requestFocus();
                            }
                        }

                    } else {
                        lblKomunikat.setText("Pole nr ID nie zawiera liczby");
                        txtId.setText("");
                        lblKomunikat.setVisible(true);
                    }
                } else {
                    lblKomunikat.setText("Pole numeru ID wyszukiwanego rekordu jest puste!");
                    lblKomunikat.setVisible(true);
                }

                hideAll();
                lblArea.setText("Zaktualizowany użytkownik: ");
                lblArea.setVisible(true);
                txtArea.setText(customerJDBCDao.update(cust));
                panel.setVisible(true);
            }

        }else if (source == buttonRunCreate) {
            //System.out.println("Utworzenie nowego rekordu");
            int age = 0;
            BigDecimal salary = null;
            String name = txtName.getText();
            String surname = txtSurname.getText();
            if (checkIsInt(txtAge.getText())) {
                age = Integer.parseInt(txtAge.getText());
            } else {
                lblKomunikat.setVisible(true);
                txtAge.setText("");
                txtAge.requestFocus();
            }
            String address = txtAddress.getText();
            try {
                salary = new BigDecimal(txtSalary.getText());
            } catch (Exception ex) {
                lblKomunikat.setText("Pole Wynagrodzenie musi zawierać liczbę!");
                lblKomunikat.setBackground(red);
                lblKomunikat.setVisible(true);
                txtSalary.setText("");
                txtSalary.requestFocus();
            }
            //hideAll();
            if (!txtName.getText().isEmpty() && !txtSurname.getText().isEmpty() && !txtAge.getText().isEmpty()
                        && !txtAddress.getText().isEmpty() && !txtSalary.getText().isEmpty()) {
                cust = new Customer(1, name, surname, age, address, salary);
                hideAll();
                lblArea.setText("Utworzony użytkownik: ");
                lblArea.setVisible(true);
                txtArea.setText(customerJDBCDao.create(cust));
                panel.setVisible(true);
            }

        }else if (source == buttonRunDelete) {
            //System.out.println("Usuwanie rekordu o wybranym ID");
            if (txtId.getText().isEmpty()) {
                lblKomunikat.setText("Pole numeru ID usuwanego rekordu jest puste!");
                lblKomunikat.setVisible(true);
            } else {
                if (checkIsInt(txtId.getText())) {
                    long customerId = Long.parseLong(txtId.getText());
                    hideAll();
                    lblArea.setText("Usunięty użytkownik z bazy: ");
                    lblArea.setVisible(true);
                    txtArea.setText(customerJDBCDao.delete(customerId));
                    panel.setVisible(true);
                } else {
                    lblKomunikat.setVisible(true);
                }
            }
        }
    }

    private String printCustomers(List<Customer> customers) {
        StringBuffer sb = new StringBuffer();
        for (Customer cust : customers) {
            sb.append(cust.toString());
        }
        return  sb.toString().substring(0,sb.toString().length()-2);
    }


    private boolean checkIsInt (String wpis) {
        boolean spr = true;
        for (int i = 0; i < wpis.length(); i++) {
            if (!Character.isDigit(wpis.charAt(i))) {
                lblKomunikat.setText("Pole ID musi zawierać liczbę!");
                lblKomunikat.setBackground(red);
                spr = false;
                break;
            }
        }
        return spr;
    }

}


