/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentvalorant.data;

import agentvalorant.Interface.AdminInterface;
import agentvalorant.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Admin extends Akun implements AdminInterface {

    private final String level = "admin";
    Connection conn = koneksi.getConnection();
    
    public Admin (String username, String password, String nama) {
        super(username, password, nama);
    }
    
    @Override
    public boolean createAgent(Agent agent) {
        String query = "INSERT INTO agent (nama, role, skill, utility, price) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, agent.getNama());
            ps.setString(2, agent.getRole());
            ps.setString(3, agent.getSkill());
            ps.setString(4, agent.getUtility());
            ps.setString(5, agent.getPrice());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return false;
    }
    
    @Override
    public void updateAgent(Agent agent) {
        String query = "UPDATE agent SET nama = ?, role = ?, skill = ?, utility = ?, price = ? WHERE id_agent = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, agent.getNama());
            ps.setString(2, agent.getRole());
            ps.setString(3, agent.getSkill());
            ps.setString(4, agent.getUtility());
            ps.setString(5, agent.getPrice());
            ps.setInt(6, agent.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    @Override
    public void deleteAgent(int id) {
        String query = "DELETE FROM agent WHERE id_agent = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
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
        String query = "select * from agent WHERE nama LIKE '%" + search +"%'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
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
}
