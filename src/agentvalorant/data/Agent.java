/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentvalorant.data;

/**
 *
 * @author ASUS
 */
public class Agent {
    private int id;
    private String nama, role, skill, utility, price;
    
    public Agent(int id, String nama, String role, String skill, String utility, String price) {
        this.id = id;
        this.nama = nama;
        this.role = role;
        this.skill = skill;
        this.utility = utility;
        this.price = price;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public String getSkill() {
        return skill;
    }
    
    public void setUtility(String utility) {
        this.utility = utility;
    }
    
    public String getUtility() {
        return utility;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getPrice() {
        return price;
    }
}
