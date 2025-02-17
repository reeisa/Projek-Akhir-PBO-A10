/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package agentvalorant;

import agentvalorant.data.Purchase;
import agentvalorant.data.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Lenovo - GK
 */
public class InterfaceMenuUser extends javax.swing.JFrame {

    /**
     * Creates new form InterfaceMenu
     */
    private Connection con;
    private Statement stat;
    private ResultSet res;

    public static int ID;
    private String username;
    
    User u = new User(-1, "", "", "");

    public InterfaceMenuUser(String username) {
        initComponents();
        this.username = username;
        koneksi();
        tabel();
        btnPurchase.setEnabled(false);
        setSize(890, 430); 
        setLocationRelativeTo(null); 
        setResizable(false); 
    }

    public void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_valorant", "root", "");
            stat = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tabel() {
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb.setRowCount(0);

        try {
            List<Object[]> rows = u.displaydata();
            for (Object[] row : rows) {
                Object[] newrow = new Object[row.length + 1];
                System.arraycopy(row, 0, newrow, 0, row.length);
                String agentName = (String) row[1];
                String status = isClaimed(agentName) ? "Claimed" : "Unclaimed";
                newrow[row.length] = status;
                tb.addRow(newrow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    private void tabel(String search) {
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb.setRowCount(0);

        try {
            List<Object[]> rows = u.displaydata(search);
            for (Object[] row : rows) {
                Object[] newrow = new Object[row.length + 1];
                System.arraycopy(row, 0, newrow, 0, row.length);
                String agentName = (String) row[1];
                String status = isClaimed(agentName) ? "Claimed" : "Unclaimed";
                newrow[row.length] = status;
                tb.addRow(newrow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    private boolean isClaimed(String agentName) {
        boolean claimed = false;
        int id_agent = getIdAgent(agentName);
        try {
            String sql = "select * from purchase where username = ? and agent = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, id_agent);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                claimed = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return claimed;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnlogout = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnDisplay = new javax.swing.JButton();
        btnPurchase = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtsearch = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtsearch1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Username = new javax.swing.JLabel();
        Username1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("InterfaceMenu"); // NOI18N
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DASHBOARD");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(60, 40, 120, 25);

        btnlogout.setBackground(new java.awt.Color(102, 0, 0));
        btnlogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnlogout.setForeground(new java.awt.Color(255, 255, 255));
        btnlogout.setText("Logout");
        btnlogout.setBorder(null);
        btnlogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogout.setName("btnLogout"); // NOI18N
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnlogout);
        btnlogout.setBounds(80, 340, 50, 31);

        btnProfile.setBackground(new java.awt.Color(102, 0, 0));
        btnProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnProfile.setText("Profile");
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.setName("btnTambah"); // NOI18N
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });
        jPanel1.add(btnProfile);
        btnProfile.setBounds(30, 230, 172, 29);

        btnHistory.setBackground(new java.awt.Color(102, 0, 0));
        btnHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnHistory.setText("Purchase History");
        btnHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistory.setName("btnTambah"); // NOI18N
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });
        jPanel1.add(btnHistory);
        btnHistory.setBounds(30, 190, 172, 29);

        btnDisplay.setBackground(new java.awt.Color(102, 0, 0));
        btnDisplay.setForeground(new java.awt.Color(255, 255, 255));
        btnDisplay.setText("Display Agent");
        btnDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDisplay.setName("btnTambah"); // NOI18N
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });
        jPanel1.add(btnDisplay);
        btnDisplay.setBounds(30, 110, 172, 29);

        btnPurchase.setBackground(new java.awt.Color(102, 0, 0));
        btnPurchase.setForeground(new java.awt.Color(255, 255, 255));
        btnPurchase.setText("Purchase Agent");
        btnPurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPurchase.setName("btnTambah"); // NOI18N
        btnPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchaseActionPerformed(evt);
            }
        });
        jPanel1.add(btnPurchase);
        btnPurchase.setBounds(30, 150, 172, 29);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 229, 394);

        jTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("User");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Agent", "Nama Agent", "Role Agent", "Skill Agent", "Utility Agent", "Price Agent", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        txtsearch.setText("Search");
        txtsearch.setToolTipText("");
        txtsearch.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 0, 0)));
        txtsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtsearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsearchFocusLost(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(430, 430, 430)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtsearch)
                        .addGap(6, 6, 6)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        jTabbedPane.addTab("Display Agent", jPanel2);

        jLabel3.setText("User");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Agent", "Tanggal Waktu", "Total Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
            table1.getColumnModel().getColumn(0).setPreferredWidth(20);
            table1.getColumnModel().getColumn(1).setResizable(false);
            table1.getColumnModel().getColumn(2).setResizable(false);
            table1.getColumnModel().getColumn(3).setResizable(false);
            table1.getColumnModel().getColumn(4).setResizable(false);
        }

        txtsearch1.setText("Search");
        txtsearch1.setToolTipText("");
        txtsearch1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 0, 0)));
        txtsearch1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtsearch1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsearch1FocusLost(evt);
            }
        });
        txtsearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearch1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(430, 430, 430)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtsearch1)
                        .addGap(6, 6, 6)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        jTabbedPane.addTab("Purchase History", jPanel3);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Profile");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Username");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Name");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agentvalorant/profile.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");

        Username.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Username.setText("Username");

        Username1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Username1.setText("Name");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Username1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Username1)
                    .addComponent(jLabel8))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Profile", jPanel4);

        getContentPane().add(jTabbedPane);
        jTabbedPane.setBounds(230, 0, 648, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        btnPurchase.setEnabled((true));
        int baris = table.getSelectedRow();
        ID = (int) table.getValueAt(baris, 0);
        System.out.println(ID);
    }//GEN-LAST:event_tableMouseClicked

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        String cari = txtsearch.getText();
        tabel(cari);
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtsearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearchFocusGained
        String sc = txtsearch.getText();
        if (sc.equals("Search")) {
            txtsearch.setText("");
        }
    }//GEN-LAST:event_txtsearchFocusGained

    private void txtsearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearchFocusLost
        String sc = txtsearch.getText();
        if (sc.equals("") || sc.equals("Search")) {
            txtsearch.setText("Search");
        }
    }//GEN-LAST:event_txtsearchFocusLost

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        InterfaceLogin IL = new InterfaceLogin();
        IL.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        jLabel6.setText(username);
        jLabel8.setText(getName(username));
        jTabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        koneksi();
        table1();
        jTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        jTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchaseActionPerformed
//        User u = new User(-1, "", "", "");
        btnPurchase.setEnabled(false);
        int baris = table.getSelectedRow();
        int idAgent = (int) table.getValueAt(baris, 0);
        String namaAgent = (String) table.getValueAt(baris, 1);
        LocalDateTime dateTime = LocalDateTime.now();
        int id_agent = getIdAgent(namaAgent);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedDateTime = dateTime.format(formatter);
        
        String totalPayment = getPrice(idAgent);
        String status = "Success";
        
        Connection conn = this.con;
        String query = "SELECT agent FROM purchase WHERE username = ? AND agent = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setInt(2, id_agent);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Agent sudah diclaim", "SQL Information", JOptionPane.INFORMATION_MESSAGE);
                    rs.close();
                    ps.close();
                    return;
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda ingin melakukan pembelian agent ini? " + namaAgent, "Konfirmasi", JOptionPane.YES_NO_OPTION);
        switch (response) {
            case JOptionPane.YES_OPTION:
                try {
                    u.makePurchases(new Purchase(-1, username, id_agent, formattedDateTime, totalPayment, status));
                    JOptionPane.showMessageDialog(this, "Pembelian berhasil!", "Purchase Information", JOptionPane.INFORMATION_MESSAGE);
                    String struk = String.format(
                    "Purchase Receipt\n"
                    + "=====================================\n"
                    + "username: " + username + "\n"
                    + "Agent: " + namaAgent + "\n"
                    + "Date of Purchase: " + formattedDateTime + "\n"
                    + "Total Price: Rp " + totalPayment + "\n"
                    );
                    JOptionPane.showMessageDialog(this, struk, "Purchase Receipt", JOptionPane.INFORMATION_MESSAGE);
                    tabel();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnPurchaseActionPerformed
    
    private int getIdAgent(String nama) {
        int id = -1;
        try {
            Connection conn = this.con;
            String sql = "select id_agent from agent where nama = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_agent");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Invalid price format", "Conversion Error", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
    private String getPrice(int idAgent) {
        String price = "0";
        try {
            Connection conn = this.con;
            String sql = "select price from agent where id_agent = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAgent);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                price = rs.getString("price");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Invalid price format", "Conversion Error", JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }
    
    private String getName(String username) {
        String name = "";
        try {
            Connection conn = this.con;
            String sql = "select nama from akun where username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("nama");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        return name;
    }


    private void table1() {
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb.setRowCount(0);

        try {
            List<Object[]> rows = u.viewPurchaseHistory(username);
            for (Object[] row : rows) {
                tb.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    private void table1(String search) {
        koneksi();
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb.setRowCount(0);

        try {
            List<Object[]> rows = u.viewPurchaseHistory(username, search);
            for (Object[] row : rows) {
                tb.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }


    private void txtsearch1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearch1FocusGained
        String sc = txtsearch1.getText();
        if (sc.equals("Search")) {
            txtsearch1.setText("");
        }    }//GEN-LAST:event_txtsearch1FocusGained

    private void txtsearch1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearch1FocusLost
        String sc = txtsearch1.getText();
        if (sc.equals("") || sc.equals("Search")) {
            txtsearch1.setText("Search");
        }
    }//GEN-LAST:event_txtsearch1FocusLost

    private void txtsearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearch1KeyReleased
        String cari = txtsearch1.getText();
        table1(cari);    }//GEN-LAST:event_txtsearch1KeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Username;
    private javax.swing.JLabel Username1;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnPurchase;
    private javax.swing.JButton btnlogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearch1;
    // End of variables declaration//GEN-END:variables
}
