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
import entities.Services;
import services.ServiceServices;


/**
 *
 * @author Lord
 */
public class ShowService extends Form {
    
    Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  ArrayList<Services> list=ServiceServices.getInstance().parseservice();
  Button btnAdd=new Button("Add Service");

    public ShowService(Form previous) {
         f=this;

      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Categorie");
        //setTitle("List Categories");
        btnAdd.addActionListener(e -> new AddService(this).show());
        C2.add(btnAdd);
        
         addservice(list);
         addAll(C2);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
     public void addservice(ArrayList<Services> list){
         
  for( Services c : list){
       

        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       
Button b = new Button("remove");
Button mm = new Button("update");
Label l = new Label("Nom: "+c.getNom());
Label l2 = new Label("Nb:" +c.getNb());
b.addActionListener((evt) ->{
     ServiceServices.getInstance().deleteservice(c.getId());
      C2.removeComponent(l);
       C2.removeComponent(l2); // remove the label from the container
    C2.removeComponent(C1); // remove the button container from the container
    f.refreshTheme(); // refresh the form's theme
});
mm.addActionListener((evt) ->{

     new UpdateService(this,c).show();
     
     
});
   C1.addAll(l,l2,b,mm);    
       

C2.addAll(C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
  
  
    
}
