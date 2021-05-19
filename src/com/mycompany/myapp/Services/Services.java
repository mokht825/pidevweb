/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.utils.Lien;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author belha
 */
public class Services {
    
    public ArrayList<Client> listec;
    
    public static Services instance = null;
    public ConnectionRequest req;
    public boolean var;
    
    public static Services getInstance(){
    if (instance == null)
    
        instance = new Services();
    return instance;
    }
    
    public Services(){
        req = new ConnectionRequest();
    }
    
    
    
    public boolean ajoutClient (Client c){
        
        String url = Lien.BASE_URL+"/client/addClient/new?nom="+c.getNom()+"&prenom="+c.getPrenom()+"&sexe="+c.getSexe()+"&datenaissance="+c.getDateNaissance()+"&email="+c.getEmail()+"&adresse="+c.getAdresse()+"&handicap="+c.isHandicap()+"&deshandicap="+c.getDesHandicap()+"&tel="+c.getTel();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                var = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
        });
//            String str = new String(req.getResponseData());
//            System.out.println("data ="+str);
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        Message m = new Message("Body of message");
        String textAttachmentUri = null;
        m.getAttachments().put(textAttachmentUri, "text/plain");
        String imageAttachmentUri = null;
        m.getAttachments().put(imageAttachmentUri, "image/png");
        Display.getInstance().sendMessage(new String[] {c.getEmail()}, "Subject of message", m);
        
        return var;
    }
    
    
    public ArrayList<Client> parseclient(String jsonText){
        try {
            listec = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> clientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)clientListJson.get("root");
            
            
            for(Map<String,Object> obj : list){
                
                Client c = new Client();
                float id = Float.parseFloat(obj.get("id").toString());
                        c.setID((int)id);
                        c.setNom(obj.get("Nom").toString());
                        c.setPrenom(obj.get("Prenom").toString());
                        c.setSexe( obj.get("Sexe").toString());
                        
//                        String DateConverter = obj.get("DateNaissance").toString().substring(obj.get("DateNaissance").toString().indexOf("timestamp") + 10,obj.get("DateNaissance").toString().lastIndexOf("}"));
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//                        String dateString = formatter.format(currentTime);
                        
                        c.setEmail(obj.get("Email").toString());
                        c.setAdresse(obj.get("Adresse").toString());
//                      boolean handicap = Boolean.valueOf(obj.get("Handicap").toString());
                        
//                        String Handicap = String.valueOf(c.isHandicap());
//                        obj.get("Handicap").toString();
                        
                        c.setDesHandicap(obj.get("DesHandicap").toString());
                        float tel = Float.parseFloat(obj.get("Tel").toString()); 
                        c.setTel((int)tel);
//                        c.setTel((int)Float.parseFloat(obj.get("tel").toString()));
                listec.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return listec;
    }
    
    public ArrayList<Client> afficheClient(){
        String url = Lien.BASE_URL+"/client/Clientshow";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listec = parseclient(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listec;
    }
    
    
    
    
//    public ArrayList<Client>afficheClient (){
//        ArrayList<Client> liste = new ArrayList<>();
//        
//        String url = Lien.BASE_URL+"/client/Clientshow";
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp;
//                jsonp = new JSONParser();
//                
//                try{
//                    Map<String,Object>mapClient = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> Listmap = (List<Map<String,Object>>) mapClient.get("root");
//                    
//                    for(Map<String,Object> obj : Listmap){
//                        
//                        Client c = new Client();
//                        
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        
//                        c.setNom(obj.get("nom").toString());
//                        c.setPrenom(obj.get("prenom").toString());
//                        c.setSexe( obj.get("sexe").toString());
//                        
//                        String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10,obj.get("obj").toString().lastIndexOf("}"));
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//                        String dateString = formatter.format(currentTime);
//                        
//                        c.setEmail(obj.get("email").toString());
//                        c.setAdresse(obj.get("adresse").toString());
//                        c.setDesHandicap(obj.get("DesHandicap").toString());
//                        
//                        c.setTel((int)Float.parseFloat(obj.get("tel").toString()));
//                        
//                        liste.add(c);
//                        
//                    }
//                    
//                } catch (IOException ex) {
//                    System.err.println(ex);
//                }
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        
//        return liste;
//        
//        
//    }
    
    
//    public Client DetailClient(int id, Client c){
//        
//        String url = Lien.BASE_URL+"/client/ClientId/"+id;
//        req.setUrl(url);
        
//        String str = new String(req.getResponseData());
//         req.addResponseListener (((evt)-> {
//             
//            JSONParser jsonp = new JSONParser();
//            try{
//                
//                Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//                c.setObjet(obj.get(req))
//            }
             
             
             
//         }));
        
        
//    }


    public boolean SuppClient(int id){
        
        String url = Lien.BASE_URL+"/client/suppClient/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return var;
    }
    
    
    public boolean modifClient(Client c,int id){
        
       String url = Lien.BASE_URL+"/client/editClient/"+id+"?nom="+c.getNom()+"&prenom="+c.getPrenom()+"&sexe="+c.getSexe()+"&datenaissance="+c.getDateNaissance()+"&email="+c.getEmail()+"&adresse="+c.getAdresse()+"&deshandicap="+c.getDesHandicap()+"&tel="+c.getTel(); 
       req.setUrl(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               var = req.getResponseCode() == 200;
               req.removeResponseListener(this);
       }
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
       return var;
       
    }  
       
    
    
    
}
        
        
        
   

