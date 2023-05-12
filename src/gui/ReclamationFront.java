/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import entities.Reclamation;
import entities.Services;
import java.util.ArrayList;
import services.ServiceReclamation;
import services.ServiceServices;

/**
 *
 * @author Lord
 */
public class ReclamationFront extends Form  {
    
     Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  ArrayList<Reclamation> list=ServiceReclamation.getInstance().parsereclamation();
  Button btnAdd=new Button("Add Reclamation");
  ComboBox<String> comboBox = new ComboBox<>();


    public ReclamationFront(Form previous) {
        f=this;
        
        comboBox.addItem("Filter By Date");
        comboBox.addItem("Croissant");
        comboBox.addItem("Decroissant");
        Label spa = new Label();
         btnAdd.addActionListener(e -> new AddReclamation(this).show());
         C2.addAll(btnAdd,comboBox,spa);
         
        comboBox.addActionListener(e -> {
        String selectedValue = (String) comboBox.getSelectedItem();
            if(selectedValue.equals("Croissant")){
        list =ServiceReclamation.getInstance().parseReclamationSort(1); 
            C2.removeAll(); // remove all the existing components from the container
            C2.addAll(btnAdd,comboBox,spa);
            addreclamation(list); // add the filtered categories to the container
         
            refreshTheme();
        
            }
            if(selectedValue.equals("Decroissant")){
         list =ServiceReclamation.getInstance().parseReclamationSort(2);  
          C2.removeAll(); // remove all the existing components from the container
             
            C2.addAll(btnAdd,comboBox,spa);
            addreclamation(list); // add the filtered categories to the container
         
            refreshTheme();
            }
        });
        
     
      addreclamation(list);  
      
      addAll(C2);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
      public void addreclamation(ArrayList<Reclamation> list){
         
  for( Reclamation c : list){
       

        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout (BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       
Button b = new Button();
Button mm = new Button();

FontImage.setMaterialIcon(b, FontImage.MATERIAL_DELETE);
FontImage.setMaterialIcon(mm, FontImage.MATERIAL_UPDATE);

Label l = new Label("Description: "+c.getDesc());
Label l2 = new Label("Date Creation: " +c.getDate());
b.addActionListener((evt) ->{
     ServiceReclamation.getInstance().deletereclamation(c.getId());
         C2.removeComponent(l);
         C2.removeComponent(l2); // remove the label from the container
         C2.removeComponent(C1);
         C2.removeComponent(C3);
         C2.removeComponent(C4);// remove the button container from the container
         f.refreshTheme(); // refresh the form's theme
});
mm.addActionListener((evt) ->{

     new UpdateReclamation(this,c).show();
     
     
});
   C1.addAll(l);    
   C3.addAll(l2);
   C4.addAll(b,mm);

C2.addAll(C1,C3,C4);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
    
    
    
}
