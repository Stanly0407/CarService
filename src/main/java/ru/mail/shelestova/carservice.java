package ru.mail.shelestova;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class carservice {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/CarService";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Driver driver;

        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);}
        catch (SQLException ex){
            System.out.println("Прозошла ошибка установки драйвера.");
            return;}

        try {
        DriverManager.registerDriver(driver);}
        catch (SQLException ex) {
            System.out.println("Не удалось зарегистрировать драйвер.");
        return;}

        try {
            connection = DriverManager.getConnection (URL, USERNAME, PASSWORD);
            System.out.println("code...");
        }
        catch (SQLException ex) {
            System.out.println("Не удалось создать соединение!");
        return;}

        finally {
            if (connection != null)
                connection.close();}
}
}