/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author belha
 */
public class Home extends Form{
    
     Form current;
     
     public Home(){
         
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Ajouter un Client");
        Button btnListTasks = new Button("Afficher les Client");

        btnAddTask.addActionListener(e -> new AddClient(current).show());
        btnListTasks.addActionListener(e -> new ShowClient(current).show());
        addAll(btnAddTask, btnListTasks);
     }
    
    
    
}
