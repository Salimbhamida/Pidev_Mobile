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
public class Services {
    private int id;
    private String nom;
    private int nb;

    public Services() {
    }

    public Services(String nom, int nb) {
        this.nom = nom;
        this.nb = nb;
    }

    public Services(int id, String nom, int nb) {
        this.id = id;
        this.nom = nom;
        this.nb = nb;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
   
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    
     @Override
    public String toString() {
        return "Services{" + "id=" + id + ", nom=" + nom + ", nb=" + nb + '}';
    }

    
}
