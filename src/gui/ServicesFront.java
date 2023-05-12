/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import entities.Services;
import java.util.ArrayList;
import services.ServiceServices;

/**
 *
 * @author Lord
 */
public class ServicesFront extends Form {
     ArrayList<Services> list=ServiceServices.getInstance().parseservice();
      Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
    public ServicesFront(Form previous){
         f=this;

      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Reclamation");
   
  TextField searchField = new TextField("", "Search");

  searchField.addDataChangeListener((i1, i2) -> {
    // Implement your search logic here 
   String text = searchField.getText();
   
   if(text.length()!=0)    
   {list=ServiceServices.getInstance().parseServiceSearch(text);

 
            C2.removeAll(); // remove all the existing components from the container
               
            addservice(list); // add the filtered categories to the container
         
            refreshTheme();
    } 
   else{
      list=ServiceServices.getInstance().parseservice(); 
       C2.removeAll(); // remove all the existing components from the container
       
       addservice(list); // add the filtered categories to the container
       
       refreshTheme();
   }
       
});
  
    addservice(list);
       //f= new Form("Marques",BoxLayout.y());
       
        
        
        
        
     
      
//f.show(); 

addAll(C2);

  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
  getToolbar().setTitleComponent(searchField);
  
    }
    
         public void addservice(ArrayList<Services> list){
         
  for( Services c : list){
       

        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       

Label l = new Label("Nom: "+c.getNom());
Label l2 = new Label("Nb Freelance: " +c.getNb());

   C1.addAll(l,l2);    
       

C2.addAll(C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
}
