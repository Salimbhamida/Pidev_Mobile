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
import entities.Services;
import services.ServiceServices;

/**
 *
 * @author Lord
 */
public class UpdateService extends Form {

    public UpdateService(Form previous, Services s) {
        setTitle("Update Services");
        setLayout(BoxLayout.y());
        TextField tfName=new TextField(s.getNom()," Nom Service");
        String nbs = String.valueOf(s.getNb());
        TextField tfNb = new TextField(nbs, "Nb Freelance", 4, TextField.NUMERIC);
       Button btnValider = new Button("Update Service");
       
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
              s.setNom(tfName.getText());
              s.setNb(nb);
               if(ServiceServices.getInstance().modifiercat(s)){
                   Dialog.show("Success","Service Updated","OK",null);
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
