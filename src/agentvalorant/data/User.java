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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class User extends Akun {
    private int id;
    private String username, password, nama;
    private final String level = "user";
    
    Connection conn = koneksi.getConnection();
    
    public User(int id, String username, String password, String nama) {
        super(username, password, nama);
        this.id = id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
    }
    
//    public boolean register(User user, boolean adm) {
    public boolean register(String username, String password, String nama, boolean adm) {
//        System.out.println(user.getUsername());
        String query = "SELECT * FROM akun WHERE username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        query = "INSERT INTO akun (username, password, nama, admin) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, nama);
            ps.setBoolean(4, adm);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }
    
    public void makePurchases(Purchase pur) {
        String query = "INSERT INTO purchase (username, agent, datetime, totalPayment, status) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, pur.getUsername());
            ps.setInt(2, pur.getIdAgent());
            ps.setObject(3, pur.getDatetime());
            ps.setString(4, pur.getTotalPayment());
            ps.setString(5, pur.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private String getName(int idAgent) {
        String name = "";
        try {
            String sql = "select nama from agent where id_agent = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAgent);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("nama");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return name;
    }
    
    public List<Object[]> viewPurchaseHistory(String usn) {
        List<Object[]> rows = new ArrayList<>();
        String query = "SELECT * FROM purchase WHERE username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, usn);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id_agent = rs.getInt("agent");
                    String namaAgent = getName(id_agent);
                    rows.add(new Object[] {
                        rs.getInt("id_purchase"),
                        namaAgent,
                        rs.getString("datetime"),
                        rs.getString("totalPayment"),
                        rs.getString("status")
                    });
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return rows;
    }
    
    public List<Object[]> viewPurchaseHistory(String usn, String search) {
        List<Object[]> rows = new ArrayList<>();
        String query = "SELECT * FROM purchase WHERE username = ? AND agent = (select id_agent from agent where nama like '%" + search + "%')";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, usn);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id_agent = rs.getInt("agent");
                    String namaAgent = getName(id_agent);
                    rows.add(new Object[] {
                        rs.getInt("id_purchase"),
                        namaAgent,
                        rs.getString("datetime"),
                        rs.getString("totalPayment"),
                        rs.getString("status")
                    });
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return rows;
    }
    
    @Override
    public List<Object[]> displaydata() {
        List<Object[]> rows = new ArrayList<>();
        String query = "SELECT * FROM agent";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
//                    String agentName = rs.getString("nama");
//                    String status = isClaimed(agentName) ? "Claimed" : "Unclaimed";
                    rows.add(new Object[] {
                        rs.getInt("id_agent"),
                        rs.getString("nama"),
                        rs.getString("role"),
                        rs.getString("skill"),
                        rs.getString("utility"),
                        rs.getString("price")
                    });
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return rows;
    }
    
    public List<Object[]> displaydata(String search) {
        List<Object[]> rows = new ArrayList<>();
        String query = "select * from agent where nama like '%" + search + "%'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String agentName = rs.getString("nama");
//                    String status = isClaimed(agentName) ? "Claimed" : "Unclaimed";
                    rows.add(new Object[] {
                        rs.getInt("id_agent"),
                        agentName,
                        rs.getString("role"),
                        rs.getString("skill"),
                        rs.getString("utility"),
                        rs.getString("price")
                    });
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return rows;
    }
}
