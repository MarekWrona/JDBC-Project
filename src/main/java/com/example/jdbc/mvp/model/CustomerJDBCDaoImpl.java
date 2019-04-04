package com.example.jdbc.mvp.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class CustomerJDBCDaoImpl implements CustomerDao {

    private String sql;
    private Connection conn;
    private PreparedStatement statement;
    private String wynikString = "";

    public CustomerJDBCDaoImpl() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", ""); // in-memory database, change as needed

            createTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Nie połączyłeś się z bazą");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        System.out.println("CREATING TABLE!!!");
        conn.prepareStatement("create table if not exists customer\n" +
                "(\n" +
                "\tcustomer_id long auto_increment,\n" +
                "\tname varchar(256),\n" +
                "\tsurname varchar(256),\n" +
                "\tage int,\n" +
                "\taddress varchar(256),\n" +
                "\tsalary decimal,\n" +
                "\tconstraint customer_pk\n" +
                "\t\tprimary key (customer_id)\n" +
                ");\n" +
                "\n").execute();
    }


    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //metoda wyszukująca użytkownika o nr ID
    public Customer findById(long customerId) {

        Customer cust = null;
        try {
            sql = "select * from CUSTOMER where CUSTOMER_ID=?";
            statement = conn.prepareStatement(sql);
            statement.setLong(1, customerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                cust = fillCustomer(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cust;
    }


    public String filtrSearch(StringBuffer zapytanie, boolean[] uzytePole, String name, String surname,
                              int age, String address, BigDecimal salary) {

        int numerZapytania = 0;
        String wynikSerch = "";

        try {
            sql = "select * from CUSTOMER where " + zapytanie;
            statement = conn.prepareStatement(sql);

            //ustalenie numerów kolumn do ustawienia zapytania
            for (int i = 0; i <= 4; i++) {
                if (uzytePole[i]) {
                    numerZapytania++;
                    if (i == 0) {
                        statement.setString(numerZapytania, name);
                    } else if (i == 1) {
                        statement.setString(numerZapytania, surname);
                    } else if (i == 2) {
                        statement.setInt(numerZapytania, age);
                    } else if (i == 3) {
                        statement.setString(numerZapytania, address);
                    } else statement.setBigDecimal(numerZapytania, salary);
                }
            }

            ResultSet rs = statement.executeQuery();
            int liczbaWynikow = 0;
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                liczbaWynikow++;
                sb.append(liczbaWynikow).append(". ").append(rs.getString(2));
                sb.append(" ").append(rs.getString(3).toUpperCase()).append(", ");
                sb.append(rs.getInt(4)).append(", ");
                sb.append(rs.getString(5)).append(", ");
                sb.append(rs.getBigDecimal(6)).append("\n");
            }
            wynikSerch = sb.toString();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return wynikSerch;
    }


    //metoda zmieniająca dane wksazanego użytkownika
    public String update(Customer cust) {

        sql = "UPDATE CUSTOMER SET "
                + "NAME = ?, "
                + "SURNAME = ?, "
                + "AGE = ?, "
                + "ADDRESS = ?, "
                + "SALARY = ? "
                + "WHERE CUSTOMER_ID = ?";
        try {
            statement = conn.prepareStatement(sql);     //tworzymy zapytanie
            statement.setString(1, cust.getName());   //ustawiamy wartości pod pytajniki
            statement.setString(2, cust.getSurname());
            statement.setInt(3, cust.getAge());
            statement.setString(4, cust.getAddress());
            statement.setBigDecimal(5, cust.getSalary());
            statement.setLong(6, cust.getId());
            statement.execute();                                //wywołujemy zapytanie

            wynikString = cust.getId() + ". " + cust.getName() + " " + cust.getSurname().toUpperCase()
                    + ", " + cust.getAge() + ", " + cust.getAddress() + ", " + cust.getSalary();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return wynikString;
    }


    //metoda która tworzy użytkownika w bazie/rekord
    public String create(Customer cust) {

        try {
            sql = "INSERT INTO CUSTOMER VALUES (null, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, cust.getName());        //ustawiamy wartości pod pytajniki
            statement.setString(2, cust.getSurname());
            statement.setInt(3, cust.getAge());
            statement.setString(4, cust.getAddress());
            statement.setBigDecimal(5, cust.getSalary());
            statement.execute();                                        //wywołujemy zapytanie

            wynikString = cust.getName() + " " + cust.getSurname().toUpperCase()
                    + ", " + cust.getAge() + ", " + cust.getAddress() + ", " + cust.getSalary();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Created customer: " + wynikString);
        return wynikString;
    }


    //metoda wykonująca usuwanie rekordu/encji
    public String delete(long customerId) {
        try {
            sql = "delete from CUSTOMER where CUSTOMER_ID=?";
            statement = conn.prepareStatement(sql);
            statement.setLong(1, customerId);
            statement.executeUpdate();

            wynikString = "" + customerId;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return wynikString;
    }


    public List<Customer> getallCustomers() {
        List<Customer> retList = new ArrayList<Customer>();
        StringBuilder sb = new StringBuilder();
        int liczbaRekordow = 0;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from CUSTOMER");
            while (rs.next()) {
                Customer cust = fillCustomer(rs);
                retList.add(cust);
                liczbaRekordow++;
                sb.append(liczbaRekordow).append(". ").append(rs.getString(2));
                sb.append(" ").append(rs.getString(3).toUpperCase()).append(", ");
                sb.append(rs.getInt(4)).append(", ");
                sb.append(rs.getString(5)).append(", ");
                sb.append(rs.getBigDecimal(6)).append("\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retList;
    }


    private Customer fillCustomer(ResultSet rs) throws SQLException {
        Customer c = null;
        c = new Customer(rs.getLong("CUSTOMER_ID"),
                rs.getString("NAME"),
                rs.getString("SURNAME"),
                rs.getInt("AGE"),
                rs.getString("ADDRESS"),
                rs.getBigDecimal("SALARY"));
        return c;
    }

}
