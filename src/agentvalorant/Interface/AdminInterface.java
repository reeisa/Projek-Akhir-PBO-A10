/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package agentvalorant.Interface;

import agentvalorant.data.Agent;

/**
 *
 * @author ASUS
 */
public interface AdminInterface {

    /**
     *
     */
    public boolean createAgent(Agent agent);
    public void updateAgent(Agent agent);
    public void deleteAgent(int id);
}
