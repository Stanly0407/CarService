package ru.mail.shelestova;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;

public class carservice {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "503911";
    private static final String URL = "jdbc:mysql://localhost:3306/carservice";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Driver driver;

        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            System.out.println("Прозошла ошибка установки драйвера.");
            return;
        }

        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            System.out.println("Не удалось зарегистрировать драйвер.");
            return;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = null;
            String SQL1 = "SELECT * FROM companies";
            try {
                statement = connection.prepareStatement(SQL1);
                ResultSet rs = null;
                try {
                    rs = statement.executeQuery();
                    ArrayList<HeadOffice> lst = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String companyTitle = rs.getString(2);
                        String companyAdress = rs.getString(3);
                        lst.add(new HeadOffice(id, companyTitle, companyAdress));
                    }
                    if (lst.size() > 0) {
                        System.out.println(lst); //пока так, но потом вывод будет через пользовательский интерфейс, а здесь написать lst = какой-нибудь переменной.
                    } else {
                        System.out.println("Not found.");
                    }
                } finally {
                    //здесь закрытие ResultSet
                    if (rs != null) {
                        rs.close();
                    } else {
                        System.err.println("Ошибка во время чтения из базы данных.");
                    }
                }
            } finally {
                //здесь закрытие Statement
                if (statement != null) {
                    statement.close();
                } else {
                    System.err.println("Statement не создан.");
                }
            }

        } catch (SQLException ex) {
            System.err.println("Не удалось создать соединение:" + ex);
            return;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.err.println("Не удалось закрыть соединение:" + ex);
                }

            }
        }
    }
}

