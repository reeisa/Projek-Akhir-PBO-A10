/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentvalorant.data;

/**
 *
 * @author ASUS
 */
public class Purchase {
    private int id, id_agent;
    private String username, datetime, totalPayment, status;
    
    public Purchase(int id, String username, int id_agent, String datetime, String totalPayment, String status) {
        this.username = username;
        this.id_agent = id_agent;
        this.datetime = datetime;
        this.totalPayment = totalPayment;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdAgent() {
        return id_agent;
    }

    public void setAgent(int id_agent) {
        this.id_agent = id_agent;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
