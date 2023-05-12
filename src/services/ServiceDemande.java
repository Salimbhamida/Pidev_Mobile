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
import entities.Demande;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Lord
 */
public class ServiceDemande {
    
    public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceDemande instance=null;
     public ArrayList<Demande> tasks;
      Demande demande;

    public ServiceDemande() {
        req=new ConnectionRequest();
    }
    
     public static ServiceDemande getInstance() {
        if(instance==null)
                instance=new ServiceDemande();
        return instance;
    }
     
      public boolean addDemande(Demande t) {
        String url = Statics.BASE_URL + "demandeMobile/addD?nomRecruteur=" +t.getNomRecruteur()+ "&description=" +t.getDesc()+ "&experience=" +t.getExperience()+ "&remuneration=" +t.getRemuneration()+ "&telephone=" +t.getTelephone()+ "&expiration=" +t.getExperation()+ "&idRecruteur=" +t.getIdRecruteur();
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
      
      
         public ArrayList<Demande> parsedemande(){
      ArrayList<Demande> result = new ArrayList<>();
         String url = Statics.BASE_URL+"demandeMobile/getd";
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
                   Demande m = new Demande(); 
                   
                   
                   float id = Float.parseFloat(obj.get("idDemande").toString());
                   String nomRecruteur = obj.get("nomRecruteur").toString();
                   float experience = Float.parseFloat(obj.get("experience").toString());
                   String description = obj.get("description").toString();    
                   float remuneration = Float.parseFloat(obj.get("remuneration").toString());
                   float telephone = Float.parseFloat(obj.get("telephone").toString());
                   String expiration = obj.get("expiration").toString();
                   

                   
                  
                  m.setId((int) id);
                  m.setNomRecruteur(nomRecruteur);
                  m.setExperience((int)experience);
                  m.setDesc(description);
                  m.setRemuneration(remuneration);
                  m.setTelephone((int)telephone);
                  m.setExperation(expiration);
                  
                   
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
         
         
          public boolean deletedemande(int x) {


       String url = Statics.BASE_URL + "demandeMobile/deleted?id="+x;
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
          
          
          
           public boolean updateDemande(Demande t) {
        String url = Statics.BASE_URL + "demandeMobile/updated?nomRecruteur=" +t.getNomRecruteur()+ "&description=" +t.getDesc()+ "&experience=" +t.getExperience()+ "&remuneration=" +t.getRemuneration()+ "&telephone=" +t.getTelephone()+ "&expiration=" +t.getExperation()+ "&idRecruteur=" +t.getIdRecruteur()+"&id="+ t.getId();
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
           
                 public ArrayList<Demande> parseDemandeSearch(String val ){
      ArrayList<Demande> result = new ArrayList<>();
         String url = Statics.BASE_URL+"demandeMobile/searchdemande/" + val;
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
                    Demande m = new Demande(); 
                   
                   
                   float id = Float.parseFloat(obj.get("idDemande").toString());
                   String nomRecruteur = obj.get("nomRecruteur").toString();
                   float experience = Float.parseFloat(obj.get("experience").toString());
                   String description = obj.get("description").toString();    
                   float remuneration = Float.parseFloat(obj.get("remuneration").toString());
                   float telephone = Float.parseFloat(obj.get("telephone").toString());
                   String expiration = obj.get("expiration").toString();
                   

                   
                  
                  m.setId((int) id);
                  m.setNomRecruteur(nomRecruteur);
                  m.setExperience((int)experience);
                  m.setDesc(description);
                  m.setRemuneration(remuneration);
                  m.setTelephone((int)telephone);
                  m.setExperation(expiration);
                  
                   
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
