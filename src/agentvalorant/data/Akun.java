/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentvalorant.data;

import agentvalorant.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public abstract class Akun {
    protected String username, password, nama;
    static Connection conn = koneksi.getConnection();
    public abstract List<Object[]> displaydata();
    
    public Akun (String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }
    
    public static String login(String username, String password) {
        String login = "";
        String query = "SELECT * FROM akun WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    if (rs.getBoolean("admin") == true) {
                        return "admin";
                    } else {
                        return username;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return login;
    }
}
