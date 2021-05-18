/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Coach;
import com.mycomany.entities.Diplome;
import com.mycomany.utils.Statics;
import static com.mycompany.services.ServiceCoach.instance;
import static com.mycompany.services.ServiceCoach.resultOk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceDiplome {
    
    
       //singleton 
    public static ServiceDiplome instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
     
    public static ServiceDiplome getInstance() {
        if(instance == null )
            instance = new ServiceDiplome();
        return instance ;
    }
    
    
    
    public ServiceDiplome() {
        req = new ConnectionRequest();
        
    }
    
    
      //ajout 
    public void ajoutDiplome(Diplome diplome) {
        
        String url =Statics.BASE_URL+"/addDiplome?nom="+diplome.getNom()+"&color="+diplome.getColor();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    
    
      //Delete 
    public boolean deleteDiplome(int id ) {
        String url = Statics.BASE_URL +"/deleteDiplome?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
        
    //Update 
   public boolean modifierDiplome(Diplome coach) {
        String url = Statics.BASE_URL +"/updateDiplome?id="+coach.getId()+"&nom="+coach.getNom()+"&color="+coach.getColor();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
        public ArrayList<Diplome>affichageDiplome() {
        ArrayList<Diplome> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayDiplome";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapcoach = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapcoach.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Diplome re = new Diplome();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        String nom = obj.get("nom").toString();
                        String color = obj.get("color").toString();
               
 
                        
                        re.setId((int)id);
                        re.setNom(nom);
                        re.setColor(color);
                     
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        String nbrBeforeConvert;
        int nbrAfterConvert;
        
        
  public int getNbrDiplome( ) {
  
        String url =Statics.BASE_URL +"/countDiplome";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
        
            @Override
            public void actionPerformed(NetworkEvent evt) {
               nbrBeforeConvert  = new String(req.getResponseData());
              nbrAfterConvert   = Integer.parseInt(nbrBeforeConvert);
            }
            
        });
               NetworkManager.getInstance().addToQueueAndWait(req);
 
        return nbrAfterConvert;
    }
        
        
        
        
        
        
        
        
        
        
 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
}
