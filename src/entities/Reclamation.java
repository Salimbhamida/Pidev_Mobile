/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lord
 */
public class Reclamation {
    private int id;
    private String date;
    private String desc;

    public Reclamation() {
    }

    public Reclamation(int id, String date, String desc) {
        this.id = id;
        this.date = date;
        this.desc = desc;
    }

    public Reclamation(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", date=" + date + ", desc=" + desc + '}';
    }
    
    
}
