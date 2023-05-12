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
import entities.Services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;
/**
 *
 * @author Lord
 */
public class ServiceServices {
     public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceServices instance=null;
     public ArrayList<Services> tasks;
      Services service;

    public ServiceServices() {
        req=new ConnectionRequest();
    }
    
     public static ServiceServices getInstance() {
        if(instance==null)
                instance=new ServiceServices();
        return instance;
    }
     public boolean addService(Services t) {
        String url = Statics.BASE_URL + "servicesMobile/adds?nom=" + t.getNom() + "&nb="+ t.getNb(); //création de l'URL
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
     
     public ArrayList<Services> parseservice(){
      ArrayList<Services> result = new ArrayList<>();
         String url = Statics.BASE_URL+"servicesMobile/gets";
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
                   Services m = new Services(); 
                   float id = Float.parseFloat(obj.get("idService").toString());
                   String nom= obj.get("nomService").toString();
                   float nb = Float.parseFloat(obj.get("nbTotFreelance").toString());
                  
                   System.out.println(nom);
                  m.setId((int) id);
                  m.setNom(nom);
                  m.setNb((int)nb);
                   
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
     
      public boolean deleteservice(int x) {


       String url = Statics.BASE_URL + "servicesMobile/deletes?id="+x;
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
      public boolean modifiercat(Services t)
    {
        //String url = Statics.BASE_URL+"/addBadgej?nomB="badge.getNomB()"&nb="badge.getNb()"&logoB="badge.getLogoB();
        String url = Statics.BASE_URL+"servicesMobile/updates?nom=" + t.getNom() +"&nb="+ t.getNb()+"&id="+t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt){
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
      
      
       public ArrayList<Services> parseServiceSearch(String val ){
      ArrayList<Services> result = new ArrayList<>();
         String url = Statics.BASE_URL+"servicesMobile/searchservice/" + val;
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
                   Services m = new Services(); 
                   float id = Float.parseFloat(obj.get("idService").toString());
                   String nom= obj.get("nomService").toString();
                   float nb = Float.parseFloat(obj.get("nbTotFreelance").toString());
                  
                   System.out.println(nom);
                  m.setId((int) id);
                  m.setNom(nom);
                  m.setNb((int)nb);
                   
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
