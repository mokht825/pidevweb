/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.mycompany.myapp.Services.Services;
import com.mycompany.myapp.entities.Client;
import java.util.ArrayList;

/**
 *
 * @author belha
 */
public class ShowClient extends Form {
    
    
  private    int selectedRow = -1;
  private int idToDel=0;
  Dialog d = new Dialog();
  private String notifText ="";
  private Button notifBtn = new Button(notifText);
  private ArrayList<String> ids = new ArrayList<>();
  
  // ta3mil tablou fih les id w baad tekhou lid hasb il row umber fi kol mara taamil create cell w jaw hadheka
  // but how can i ammaget thi
 // izaaayy
  
  //



    public ShowClient(Form previous) {
        ArrayList<Client> listc = Services.getInstance().afficheClient();
        ArrayList<ArrayList<String>> rrr = new ArrayList<>();
           Form current;
           current=this;
        
        Button btnDel = new Button();
        btnDel.setText("Delte Selected sportType");

        for(Client c: listc){
        
            ArrayList<String> rr = new ArrayList<>();
            rr.add(String.valueOf(c.getID()));
            rr.add(String.valueOf(c.getNom()));
            rr.add(String.valueOf(c.getPrenom()));
            rr.add(String.valueOf(c.getSexe()));
            rr.add(String.valueOf(c.getDateNaissance()));
            rr.add(String.valueOf(c.getEmail()));
            rr.add(String.valueOf(c.getAdresse()));
            rr.add(String.valueOf(c.isHandicap()));
            rr.add(String.valueOf(c.getDesHandicap()));
            rr.add(String.valueOf(c.getTel()));
            
            // kamil getter
            
            rrr.add(rr);
        
        }
    

        // try over
        String[][] all = new String[listc.size()][10];
          int i=0;
                  for (Client c : listc) {
                    
                all[i][0] = String.valueOf(c.getID());
                all[i][1] = c.getNom();
                all[i][2] = c.getPrenom();
                all[i][3] = c.getSexe();
                all[i][4] = c.getDateNaissance();
                all[i][5] = c.getEmail();
                all[i][6] = c.getAdresse();
                all[i][7] = String.valueOf(c.isHandicap());
                all[i][8] = c.getDesHandicap();
                all[i][9] = String.valueOf(c.getTel());
                
                i++;
     
            }
        

        setTitle("Liste des Clients");
        String[] s = new String[]{"ID ", "Nom", "Prenom", "Sexe", "Date de Naissance", "Email",  "Adresse","Besioins Specifique","Des Handicap","Tel"};
        boolean b = true;
        

        TableModel model = new DefaultTableModel(s, all, b);
        Table table = new Table(model) {
        
    @Override
    protected Component createCell(Object value, int row, int column, boolean editable) { 
        Component cell;
      cell = super.createCell(value, row, column, editable);

        if (row < 0) {
            cell.getAllStyles().setBgColor(0255255);
            cell.getAllStyles().setBgTransparency(255);
        } else {
           
            cell = new Button(value.toString());
            cell.setUIID("TableCell");
              Dialog d = new Dialog();
            ((Button) cell).addActionListener(e -> {
                selectedRow = row;
                setModel(getModel());
                
                // create dialog here
         
                
                if(d.show("Title","Ech bech taamil ", "Ok","Cancel") ) {
                
                //delete
             
                    System.out.println("deleted");
                 
                               int id=Integer.parseInt(all[row][0]);
                    Services.getInstance().SuppClient(id);
                 
                    new ShowClient(current).show();
 
                } else{
                
                // close
                    System.out.println("closed");
            
                    System.out.println("End and clodse"+s);
                  
                    
                }
           

            });
        }

        if (row > -1 && row % 2 == 0) {
            // pinstripe effect
            cell.getAllStyles().setBgColor(0xeeeeee);
            cell.getAllStyles().setBgTransparency(255);
        }


     if(selectedRow > -1 && selectedRow == row) {
            cell.getAllStyles().setBgColor(0xff0000);
            cell.getAllStyles().setBgTransparency(100);
        }
       
        return cell;
    }

        };
        
        add(table);
        btnDel.getAllStyles().setBgColor(0255255);
      
      
        add(btnDel);
        add(notifBtn);
        
            if(selectedRow !=-1){
       
             System.out.println("selectesd  row"+selectedRow);
       }
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public ShowClient(Form previous){
        
//        setTitle("Liste des Clients");
//        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(Services.getInstance().afficheClient().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    

