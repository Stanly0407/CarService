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
        final Scanner scan1;
        scan1 = new Scanner(System.in);

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
                    System.out.println("Для того, чтобы узнать адрес главного офиса обслуживающей компании:\n" +
                            "введите название компании (например, Lukoil, Gazprom) и нажмите <Enter>:");
                    if (lst.size() > 0) {

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
            PreparedStatement statement2 = null;
            String SQL2 = "select o.name, s.serviceName, sp.cost from  servicesprice sp  LEFT JOIN offices o on o.`officeID`=sp.`office__ID` LEFT JOIN services s on s.serviceID=sp.`service__ID`;";

            try {
                statement2 = connection.prepareStatement(SQL2);
                ResultSet rs2 = null;

                try {
                    rs2 = statement2.executeQuery();
                    ArrayList<Service> lst2 = new ArrayList<>();
                    while (rs2.next()) {
                        String companyOffice = rs2.getString(1);
                        String serviceName = rs2.getString(2);
                        int cost = rs2.getInt(3);
                        lst2.add(new Service(companyOffice, serviceName, cost));
                    }

                  //  System.out.println("\n" + lst2.get(1)); //УДАЛИТЬ ПОТОМ

                    System.out.println("\nЧтобы узнать расценки:\n" +
                            "введите название услуги (например, Fuel, Fuel95, Carwarsh, CarwarshGold) и нажмите <Enter>:");
                    if (lst2.size() > 0) {
                    //    String name2 = null;
                        scan1.skip("[\r\n]+");
                     //   while (scan1.hasNext()) {
                        String name2 = scan1.next();
                       // }

                        String search3 = lst2.get(0).getServiceName();
                        String search4 = lst2.get(1).getServiceName();
                        String search5 = lst2.get(2).getServiceName();
                        String search6 = lst2.get(3).getServiceName();

                        assert name2 != null;
                        if (name2.equals(search3)) {
                            System.out.println(lst2.get(0) + "\\n\"" + lst2.get(4));
                        } else if (name2.equals(search4)) {
                            System.out.println(lst2.get(1) + "\\n\"" + lst2.get(5));
                        } else if (name2.equals(search5)) {
                            System.out.println(lst2.get(2) + "\\n\"" + lst2.get(6));
                        } else if (name2.equals(search6)) {
                            System.out.println(lst2.get(3) + "\\n\"" + lst2.get(7));
                        } else {
                            System.out.println("Отсутсвует такая услуги, либо неверно введено название услуги.");
                        }

                    } else {
                        System.out.println("Not found.");
                    }

                    scan1.close();

                } finally {
                    //здесь закрытие ResultSet2
                    if (rs2 != null) {
                        rs2.close();
                    } else {
                        System.err.println("Ошибка во время второго чтения из базы данных.");
                    }
                }
            } finally {
                //здесь закрытие Statement2
                if (statement2 != null) {
                    statement2.close();
                } else {
                    System.err.println("Statement2 не создан.");
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

