/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;

import entities.Demande;
import services.ServiceDemande;

/**
 *
 * @author Lord
 */
public class DemandeFront extends Form {
    
    Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  ArrayList<Demande> list=ServiceDemande.getInstance().parsedemande();
    
  public  DemandeFront(Form previous){
      
      setTitle("Demande Front");
      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Demande");
      
      
      TextField searchField = new TextField("", "Search");

  searchField.addDataChangeListener((i1, i2) -> {
    // Implement your search logic here 
   String text = searchField.getText();
   
   if(text.length()!=0)    
   {list=ServiceDemande.getInstance().parseDemandeSearch(text);

 
            C2.removeAll(); // remove all the existing components from the container
               
            adddemande(list); // add the filtered categories to the container
         
            refreshTheme();
    } 
   else{
      list=ServiceDemande.getInstance().parsedemande(); 
       C2.removeAll(); // remove all the existing components from the container
       
       adddemande(list); // add the filtered categories to the container
       
       refreshTheme();
   }
       
});
      
      adddemande(list);
      
      addAll(C2);
      
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
      getToolbar().setTitleComponent(searchField);
      
  }
  
  

  
   public void adddemande(ArrayList<Demande> list){
         
  for(Demande c : list){
       
        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C5 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C6 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C7 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C8 = new Container(new BoxLayout (BoxLayout.X_AXIS));

       
Button b = new Button("remove");
Button mm = new Button("update");
Label l = new Label("NomRecruteur: "+c.getNomRecruteur());
Label l2 = new Label("Description:" +c.getDesc());
String exper = String.valueOf(c.getExperience());
Label l3 = new Label("Experience: "+exper);
String renum = String.valueOf(c.getRemuneration());
Label l4 = new Label("Remuneration: "+renum);
String tel = String.valueOf(c.getTelephone());
Label l5 = new Label("Telephone: "+ tel);
Label l6 = new Label("Experation: "+ c.getExperation());

   C1.addAll(l);
   C3.add(l2);
   C4.add(l3);
   C5.add(l4);
   C6.add(l5);
   C7.addAll(l6);
   Label sep = new Label("_____________________________");
   C2.addAll(C1,C3,C4,C5,C6,C7,C8,sep);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
    
}
