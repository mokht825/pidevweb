/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Services.Services;
import com.mycompany.myapp.entities.Client;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author belha
 */
public class AddClient extends Form {
    
    private Map<String, Object> createListEntry(String Line1, String Line2) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("fr", Line1);
    entry.put("ang", Line2);
    return entry;
}
    
    public AddClient(Form previous){
        
        setTitle("Ajouter un nouveau client");
        setLayout(BoxLayout.y());
        
        TextField ctnom = new TextField("","Nom");
        TextField ctprenom= new TextField("", "Prenom");
//        TextField ctsexe = new TextField("","Sexe"); 
        
        Form hi = new Form("ComboBox", new BoxLayout(BoxLayout.Y_AXIS));
        ComboBox<Map<String, Object>> ctsexe = new ComboBox<> (
          createListEntry("Homme", "Male"),
          createListEntry("Femme", "Female"));
        
        TextField ctdatenaiss = new TextField("","Date de Naissance");        
        TextField ctemail = new TextField("","Email");
        TextField ctadresse = new TextField("","Adresse");
        CheckBox cthandicap = new CheckBox("handicap");
        TextField ctdeshabdic = new TextField("","Description handicap");
        TextField cttel= new TextField("","Tel");
        Button btnValider = new Button("Ajouter Client");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((ctnom.getText().length()==0)||(ctprenom.getText().length()==0 || ctdatenaiss.getText().length()==0 || ctemail.getText().length()==0 || ctadresse.getText().length()==0 || cttel.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Client c = new Client(ctnom.getText(), ctprenom.getText(),ctsexe.getSelectCommandText(), ctdatenaiss.getText(),ctemail.getText(),ctadresse.getText(),cthandicap.isSelected(),ctdeshabdic.getText(),Integer.parseInt(cttel.getText()));
                        if( Services.getInstance().ajoutClient(c))
                        {
                            Dialog.show("Success","Client ajouté",new Command("OK"));
                            new Home().show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(ctnom,ctprenom,ctsexe,ctdatenaiss,ctemail,ctadresse,cthandicap,ctdeshabdic,cttel,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
        
    }
    
}
