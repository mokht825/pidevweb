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
public class Diplome {
   
    private int id;

   
    private String nom;

  
    private String color;

  
    private Coach coaches;

    public Diplome() {
    }

    public Diplome(int id, String nom, String color, Coach coaches) {
        this.id = id;
        this.nom = nom;
        this.color = color;
        this.coaches = coaches;
    }

   

    public float getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Coach getCoaches() {
        return coaches;
    }

    public void setCoaches(Coach coaches) {
        this.coaches = coaches;
    }

    public Diplome(String nom, String color, Coach coaches) {
        this.nom = nom;
        this.color = color;
        this.coaches = coaches;
    }

    public Diplome(String nom, String color) {
        this.nom = nom;
        this.color = color;
    }
    

}
