/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author belha
 */
public class Client {
    private int ID;
    private String Nom;
    private String Prenom;
    private String Sexe;
    private String DateNaissance;
    private String Email;
    private String Adresse;
    private boolean Handicap;
    private String DesHandicap;
    private int Tel;

    public Client(int ID, String Nom, String Prenom, String Sexe, String DateNaissance, String Email, String Adresse, boolean Handicap, String DesHandicap, int Tel) {
        this.ID = ID;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Sexe = Sexe;
        this.DateNaissance = DateNaissance;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Handicap = Handicap;
        this.DesHandicap = DesHandicap;
        this.Tel = Tel;
    }

    public Client(String Nom, String Prenom, String Sexe, String DateNaissance, String Email, String Adresse, boolean Handicap, String DesHandicap, int Tel) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Sexe = Sexe;
        this.DateNaissance = DateNaissance;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Handicap = Handicap;
        this.DesHandicap = DesHandicap;
        this.Tel = Tel;
    }

    public Client() {
        
    }
    
    public int getID() {
        return ID;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getSexe() {
        return Sexe;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public String getEmail() {
        return Email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public boolean isHandicap() {
        return Handicap;
    }

    public String getDesHandicap() {
        return DesHandicap;
    }

    public int getTel() {
        return Tel;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public void setDateNaissance(String DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setHandicap(boolean Handicap) {
        this.Handicap = Handicap;
    }

    public void setDesHandicap(String DesHandicap) {
        this.DesHandicap = DesHandicap;
    }

    public void setTel(int Tel) {
        this.Tel = Tel;
    }

    @Override
    public String toString() {
        return "Client{" + "ID=" + ID + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Sexe=" + Sexe + ", DateNaissance=" + DateNaissance + ", Email=" + Email + ", Adresse=" + Adresse + ", Handicap=" + Handicap + ", DesHandicap=" + DesHandicap + ", Tel=" + Tel + '}';
    } 
       
}
