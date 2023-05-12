/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.StringUtil;
import entities.Services;
import java.util.ArrayList;
import services.ServiceServices;

/**
 *
 * @author Lord
 */
public class AddService extends Form {

    public AddService(Form previous) {
        setTitle("Add Services");
        setLayout(BoxLayout.y());
        TextField tfName=new TextField(""," Nom Service");
         TextField tfNb = new TextField("", "Nb Freelance", 4, TextField.NUMERIC);

        
     
      
       Button btnValider = new Button("Add Service");
       
          btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int x =0;
               if (tfName.getText().length() == 0 || tfNb.getText().length() == 0 )
        {
            Dialog.show("Error","Nom Service ou Nb Frelance non valide","OK",null);
            x=1;
        }
          
               


              int nb = Integer.parseInt(tfNb.getText());

              if(nb < 0 || nb > 100 ){
                  Dialog.show("Error"," La valeur de Nb doit être comprise entre 0 et 100 ","OK",null);
                  x=1; 
              }
     
            if(x==0)
            {
                
               
                
                Services service = new Services(tfName.getText() ,nb);
                
               if(ServiceServices.getInstance().addService(service)){
                   Dialog.show("Success","service Added","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
            }
            
            }
        
    
});
          
          getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
new ShowService(previous).show();
});
addAll(tfName,tfNb,btnValider);
    }
    
    
    
}
