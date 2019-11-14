package ru.mail.shelestova;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class carservice {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "503911";
    private static final String URL = "jdbc:mysql://localhost:3306/carservice";

    public static void main(String[] args) {
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
                        //создать лист снова
                    }
                    System.out.println("Для того, чтобы узнать адрес главного офиса обслуживающей компании:\n" +
                            "введите название компании (например, Lukoil, Gazprom) и нажмите <Enter>:");
                    if (lst.size() > 0) {
                        Scanner scan1 = new Scanner(System.in);
                        String name = scan1.nextLine();
                        String search1 = lst.get(0).getCompanyTitle(); //не очень, т.к. м.б. много записей... тогда через цикл м.б....
                        String search2 = lst.get(1).getCompanyTitle();

                        if (name.equals(search1)) {
                            System.out.println(lst.get(0));
                        } else if (name.equals(search2)) {
                            System.out.println(lst.get(1));
                        } else {
                            System.out.println("Компания не обслуживает, либо неверно введено название компании.");
                        }
                        scan1.close();

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
            //здесь снова снова создаем Statement читаю базу данных и новый лист.
                /* String SQL2 = "SELECT * FROM companies";
                try {
                statement2 = connection.prepareStatement(SQL2);
                ResultSet rs2 = null;
                try {
                    rs2 = statement2.executeQuery();
                    ArrayList<Service> lst2 = new ArrayList<>();
                    while (rs2.next()) {
                        int id = rs2.getInt(1); !!!!!!!!!!!!!
                        String companyTitle = rs2.getString(2); !!!!!!!!!!
                        String companyAdress = rs2.getString(3); !!!!!!!!!!!
                        lst2.add(new HeadOffice(id, companyTitle, companyAdress)); !!!!!!!!!!!!!!!!!!!!!
                        //создать лист снова
                    }
                    System.out.println("Для того, чтобы узнать адрес главного офиса обслуживающей компании:\n" +
                            "введите название компании (например, Lukoil, Gazprom) и нажмите <Enter>:");
                    if (lst2.size() > 0) {
                        Scanner scan2 = new Scanner(System.in);
                        String name = scan2.nextLine();

                        String search1 = lst.get(0).getCompanyTitle(); //не очень, т.к. м.б. много записей... тогда через цикл м.б....
                        String search2 = lst.get(1).getCompanyTitle();

                        if (name.equals(search1)) {
                            System.out.println(lst.get(0));
                        } else if (name.equals(search2)) {
                            System.out.println(lst.get(1));
                        } else {
                            System.out.println("Компания не обслуживает, либо неверно введено название компании.");
                        }
                        scan2.close();

                    } else {
                        System.out.println("Not found.");
                    }
                } finally {
                    //здесь закрытие ResultSet
                    if (rs2 != null) {
                        rs2.close();
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
                    */


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

