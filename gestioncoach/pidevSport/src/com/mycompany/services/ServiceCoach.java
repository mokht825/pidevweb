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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceCoach {
    
    //singleton 
    public static ServiceCoach instance = null ;
    
    public static boolean resultOk = true;
          public Coach coachs;   

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCoach getInstance() {
        if(instance == null )
            instance = new ServiceCoach();
        return instance ;
    }
    
    
    
    public ServiceCoach() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutCoach(Coach coach) {
        
        String url =Statics.BASE_URL+"/addCoach?Nomcoach="+coach.getNomcoach()+"&Prenomcoach="+coach.getPrenomcoach()+"&Genre="+coach.getGenre()+
                "&Disponibilite="+coach.getDisponibilite()+"&Localisation="+coach.getLocalisation()+"&typesesport="+coach.getTypesesport();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Coach>affichageCoach() {
        ArrayList<Coach> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayCoach";
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
                        Coach re = new Coach();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("Idcoach").toString());
                        
                        String Nomcoach = obj.get("Nomcoach").toString();
                        
                        String Prenomcoach = obj.get("Prenomcoach").toString();
                        String Genre = obj.get("Genre").toString();
                        String Disponibilite = obj.get("Disponibilite").toString();
                        String Localisation = obj.get("Localisation").toString();
                    //   String typesesport = (obj.get("typesesport").toString());
 
                        
                        re.setIdcoach((int)id);
                        re.setNomcoach(Nomcoach);
                        re.setPrenomcoach(Prenomcoach);
                        re.setGenre(Genre);
                        //re.setTypesesport();
                       re.setLocalisation(Localisation);
                        re.setDisponibilite(Disponibilite);
                        
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
    
    
    
    
    //Delete 
    public boolean deleteCoach(int id ) {
        String url = Statics.BASE_URL +"/deleteCoach?Idcoach="+id;
        
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
   public boolean modifierCoach(Coach coach) {
        String url = Statics.BASE_URL +"/updateCoach?Idcoach="+coach.getIdcoach()+"&Nomcoach="+coach.getNomcoach()+"&Prenomcoach="+coach.getPrenomcoach()+"&Genre="+coach.getGenre()+"&Localisation="+coach.getLocalisation()+"&Disponibilite="+coach.getDisponibilite()                +"&typesesport="+coach.getTypesesport();
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
    

      public Diplome getDiplomeByTypeSport( int id) {
        
          Diplome d = new Diplome();
          
        String url = Statics.BASE_URL+"/getTypeSport?typesesport="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                d.setNom(obj.get("nom").toString());
                d.setColor(obj.get("color").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return d;
        
        
    }

      //Recherche
                      Coach d = new Coach();

    public Coach parseCoach(String jsonText){
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
               
                d.setNomcoach(obj.get("Nomcoach").toString());
                
                 System.out.println("coach ssssss = "+d.getNomcoach());
                
               
                
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return d;
    }
    
      public Coach chercherCoach(String Nomcoach){
                    System.out.println("data coach="+Nomcoach);

        String url = Statics.BASE_URL+"/chercherCoach?Nomcoach="+Nomcoach;
     req.setUrl(url);
        
        String strx  = new String(req.getResponseData());        
          System.out.println("coach recherche = "+strx);
          
          
                
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                coachs = parseCoach(strx);
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return coachs;
    }
      
      String nbrBeforeConvert;
      int nbrAfterConvert ;
       public int getNbrCoach( ) {
  
        String url =Statics.BASE_URL +"/countCoach";
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