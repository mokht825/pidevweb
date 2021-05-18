/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
public class Coach {
    
    
    private float Idcoach;

    
    private String Nomcoach;


    private String Prenomcoach;

    private  String Genre;

    
    private String Disponibilite;
 

  
    private String Localisation;

  
    private int typesesport;

    public Coach() {
    }

    public Coach(float Idcoach, String Nomcoach, String Prenomcoach, String Genre, String Disponibilite, String Localisation, int typesesport) {
        this.Idcoach = Idcoach;
        this.Nomcoach = Nomcoach;
        this.Prenomcoach = Prenomcoach;
        this.Genre = Genre;
        this.Disponibilite = Disponibilite;
        this.Localisation = Localisation;
        this.typesesport = typesesport;
    }

        public Coach( String Nomcoach, String Prenomcoach, String Genre, String Disponibilite, String Localisation, int typesesport) {
        this.Nomcoach = Nomcoach;
        this.Prenomcoach = Prenomcoach;
        this.Genre = Genre;
        this.Disponibilite = Disponibilite;
        this.Localisation = Localisation;
        this.typesesport = typesesport;
    }



    
    public float getIdcoach() {
        return Idcoach;
    }

    public void setIdcoach(float Idcoach) {
        this.Idcoach = Idcoach;
    }

    public String getNomcoach() {
        return Nomcoach;
    }

    public void setNomcoach(String Nomcoach) {
        this.Nomcoach = Nomcoach;
    }

    public String getPrenomcoach() {
        return Prenomcoach;
    }

    public void setPrenomcoach(String Prenomcoach) {
        this.Prenomcoach = Prenomcoach;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
    }

    public int getTypesesport() {
        return typesesport;
    }

    public void setTypesesport(int typesesport) {
        this.typesesport = typesesport;
    }
    
    
}
