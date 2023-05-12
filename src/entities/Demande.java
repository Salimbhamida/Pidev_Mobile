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
public class Demande {
    private int id;
    private String nomRecruteur;
    private String desc;
    private int experience;
    private float remuneration;
    private int telephone;
    private String experation;
    private int idRecruteur;

    public Demande() {
    }

    public Demande(int id, String nomRecruteur, String desc, int experience, float remuneration, int telephone, String experation, int idRecruteur) {
        this.id = id;
        this.nomRecruteur = nomRecruteur;
        this.desc = desc;
        this.experience = experience;
        this.remuneration = remuneration;
        this.telephone = telephone;
        this.experation = experation;
        this.idRecruteur = idRecruteur;
    }

    public Demande(String nomRecruteur, String desc, int experience, float remuneration, int telephone, String experation, int idRecruteur) {
        this.nomRecruteur = nomRecruteur;
        this.desc = desc;
        this.experience = experience;
        this.remuneration = remuneration;
        this.telephone = telephone;
        this.experation = experation;
        this.idRecruteur = idRecruteur;
    }
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRecruteur() {
        return nomRecruteur;
    }

    public void setNomRecruteur(String nomRecruteur) {
        this.nomRecruteur = nomRecruteur;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(float remuneration) {
        this.remuneration = remuneration;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getExperation() {
        return experation;
    }

    public void setExperation(String experation) {
        this.experation = experation;
    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", nomRecruteur=" + nomRecruteur + ", desc=" + desc + ", experience=" + experience + ", remuneration=" + remuneration + ", telephone=" + telephone + ", experation=" + experation + ", idRecruteur=" + idRecruteur + '}';
    }
    
    
    
}
