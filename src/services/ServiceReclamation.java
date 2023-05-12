/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Lord
 */
public class ServiceReclamation {
    
     public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceReclamation instance=null;
     public ArrayList<Reclamation> tasks;
      Reclamation reclamation;

    public ServiceReclamation() {
        req=new ConnectionRequest();
    }
    
     public static ServiceReclamation getInstance() {
        if(instance==null)
                instance=new ServiceReclamation();
        return instance;
    }
     
     
     public ArrayList<Reclamation> parsereclamation(){
      ArrayList<Reclamation> result = new ArrayList<>();
         String url = Statics.BASE_URL+"reclamationMobile/getr";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj: listofMaps){
                   Reclamation m = new Reclamation(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String desc= obj.get("description").toString();
                   String date = obj.get("date").toString();
                  
                
                  m.setId((int) id);
                  m.setDesc(desc);
                  m.setDate(date);
                   
                  result.add(m);
               }
           }
          catch(Exception ex){
              ex.printStackTrace();
           }
               
            
        }
  });
       
         NetworkManager.getInstance().addToQueueAndWait(req);
         return result;
  }
     
     
       public boolean addReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "reclamationMobile/addr?description="+t.getDesc(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/      
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       
       
       public boolean deletereclamation(int x) {


       String url = Statics.BASE_URL + "reclamationMobile/deleter?id="+x;
       //String url = Statics.BASE_URL + "addTournoij";
       req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }
       
       
        public boolean updateReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "reclamationMobile/updater?description="+t.getDesc()+"&id="+t.getId(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/      
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        
        
         public ArrayList<Reclamation> parseReclamationSort(int val ){
      ArrayList<Reclamation> result = new ArrayList<>();
         String url = Statics.BASE_URL+"reclamationMobile/sortReclamation/" + val;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj: listofMaps){
                   Reclamation m = new Reclamation(); 
                    float id = Float.parseFloat(obj.get("id").toString());
                   String desc= obj.get("description").toString();
                   String date = obj.get("date").toString();
                  
                
                  m.setId((int) id);
                  m.setDesc(desc);
                  m.setDate(date);
                   
                  result.add(m);
               }
           }
          catch(Exception ex){
              ex.printStackTrace();
           }
               
            
        }
  });
       
         NetworkManager.getInstance().addToQueueAndWait(req);
         return result;
  }
       
       
       
}
