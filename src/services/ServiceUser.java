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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

import gui.ProfileForm;
import gui.SessionManager;

/**
 *
 * @author Salim Ben Hamida
 */
public class ServiceUser {

    public ConnectionRequest req;
    public boolean resultOK;
    private static ServiceUser instance = null;
    public ArrayList<User> tasks;
    User user;
     String json;

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public void signup(TextField username, TextField password, TextField email, TextField confirmPassword, ComboBox<String> roles, Resources res) {

        String url = Statics.BASE_URL + "usersMobile/signUp?username=" + username.getText().toString() + "&email=" + email.getText().toString()
                + "&password=" + password.getText().toString() + "&role=" + roles.getSelectedItem().toString();

        req.setUrl(url);

        //Control saisi
        if (username.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            //njib data ly7atithom fi form 
            byte[] data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 

            System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void signin(TextField email, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "usersMobile/logIn?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setUserName(user.get("username").toString());
                    SessionManager.setEmail(user.get("email").toString());
                    SessionManager.setRole(user.get("role").toString());

                    //photo 
                    //if(user.get("photo") != null)
                    //  SessionManager.setPhoto(user.get("photo").toString());
                    if (user.size() > 0) // l9a user
                    // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    {
                        new ProfileForm(rs).show();
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean addUser(User u) {
        String url = Statics.BASE_URL + "usersMobile/adds?nom=" + u.getUsername() + "&email=" + u.getEmail() + "&password=" + u.getPassword() + "&role=" + u.getRole(); //création de l'URL
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

    public ArrayList<User> parseservice() {
        ArrayList<User> result = new ArrayList<>();
        String url = Statics.BASE_URL + ""
                + "usersMobile/gets";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapReclamations.get("root");
                    for (Map<String, Object> obj : listofMaps) {
                        User u = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("username").toString();
                        String password = obj.get("password").toString();
                        String role = obj.get("role").toString();
                         String email = obj.get("email").toString();

                        System.out.println(nom);
                        u.setId((int) id);
                        u.setUsername(nom);
                        u.setPassword(password);
                        u.setRole(role);
                        u.setEmail(email);

                        result.add(u);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public boolean deleteservice(int x) {

        String url = Statics.BASE_URL + "usersMobile/deletes?id=" + x;
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

    public boolean modifiercat(User u, int id) {
        //String url = Statics.BASE_URL+"/addBadgej?nomB="badge.getNomB()"&nb="badge.getNb()"&logoB="badge.getLogoB();
        String url = Statics.BASE_URL + "/usersMobile/updates?username=" + u.getUsername().toString() + "&email=" + u.getEmail().toString() + "&password=" + u.getPassword().toString() + "&id=" + Integer.toString(id);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    public ArrayList<User> parseDemandeSearch(String val) {
        ArrayList<User> result = new ArrayList<>();
        String url = Statics.BASE_URL + "usersMobile/searchUser/" + val;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapReclamations.get("root");
                    for (Map<String, Object> obj : listofMaps) {
                        User m = new User();

                        float id = Float.parseFloat(obj.get("id").toString());
                        String username = obj.get("username").toString();
                        String email = obj.get("email").toString();
                        String password = obj.get("password").toString();
                        String role = obj.get("role").toString();

                        m.setId((int) id);
                        m.setUsername(username);
                        m.setEmail(email);
                        m.setPassword(password);
                        m.setRole(role);
                   

                        result.add(m);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
        public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

}
