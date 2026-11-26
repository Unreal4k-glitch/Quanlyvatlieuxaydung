/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author V
 */
public class DatabaseConnection {
    private static final String URL =
        "jdbc:sqlserver://LAPTOP-JUEILH7F;"
        + "instanceName=SQLEXPRESS;"
        + "databaseName=Vat_lieu_xay_dung;"
        + "integratedSecurity=true;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";

    public static Connection getConnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(URL);
            
            System.out.println("Ket noi SQL Server thanh cong!");

            return conn;

        } catch (Exception e) {

            System.out.println("Ket noi that bai!");
            e.printStackTrace();

            return null;
        }
    }
}
