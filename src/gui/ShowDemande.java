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
public class ShowDemande extends Form {
    
  Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  ArrayList<Demande> list=ServiceDemande.getInstance().parsedemande();
  Button btnAdd=new Button("Add Demande");
  
    public ShowDemande(Form previous){
      setTitle("Show Demande");
        f=this;
      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Demande");
        //setTitle("List Categories");
        btnAdd.addActionListener(e -> new AddDemande(this).show());
        C2.add(btnAdd);
        
         adddemande(list);
         addAll(C2);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
         public void adddemande(ArrayList<Demande> list){
         
  for( Demande c : list){
       

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
b.addActionListener((evt) ->{
     ServiceDemande.getInstance().deletedemande(c.getId());
       
       C2.removeComponent(l);
       C2.removeComponent(l2);
       C2.removeComponent(l2);
       C2.removeComponent(l3);
       C2.removeComponent(l4);
       C2.removeComponent(l5);
       C2.removeComponent(l6);
       C2.removeComponent(C1);
       C2.removeComponent(C3);
       C2.removeComponent(C4);
       C2.removeComponent(C5);
       C2.removeComponent(C6);
       C2.removeComponent(C7);
       C2.removeComponent(C8);
       
       // remove the label from the container
       // remove the button container from the container
    f.refreshTheme(); // refresh the form's theme
});
mm.addActionListener((evt) ->{

     new UpdateDemande(this,c).show();
     
     
});
   C1.addAll(l);
   C3.add(l2);
   C4.add(l3);
   C5.add(l4);
   C6.add(l5);
   C7.addAll(l6);
   C8.addAll(b,mm);
       

C2.addAll(C1,C3,C4,C5,C6,C7,C8);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
    
    
}
