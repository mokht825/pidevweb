/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Coach;
import com.mycomany.entities.Diplome;
import com.mycompany.services.ServiceCoach;
import com.mycompany.services.ServiceDiplome;

/**
 *
 * @author Lenovo
 */
public class ModifierDiplomeForm extends BaseForm {
    
    Form current;
    public ModifierDiplomeForm(Resources res , Diplome  r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Diplome");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
     
           TextField objet = new TextField(r.getNom(), "Nom" , 20 , TextField.ANY);
        TextField description = new TextField(r.getColor(), "Couleur" , 20 , TextField.ANY);
  
        
        
        
        objet.setUIID("NewsTopLine");
       description.setUIID("NewsTopLine");
        
      objet.setSingleLineTextArea(true);
      description.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
          r.setNom(objet.getText());
        r.setColor(description.getText());
           
      
       
       //appel fonction modfier reclamation men service
       
    if(ServiceDiplome.getInstance().modifierDiplome(r)) { // if true
      new ListDiplomeForm(res).show();
      }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListDiplomeForm(res).show();
       });
       
       

        Container content = BoxLayout.encloseY(
                new FloatingHint(objet),
                new FloatingHint(description),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
