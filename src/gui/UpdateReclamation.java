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
import entities.Reclamation;
import services.ServiceReclamation;

/**
 *
 * @author Lord
 */
public class UpdateReclamation extends Form {
    
   public UpdateReclamation(Form previous,Reclamation r){
         setTitle("Update Reclamation");
        setLayout(BoxLayout.y());
        TextField tfDesc=new TextField(r.getDesc()," Description");
        
         Button btnValider = new Button("Update Reclamation");
       
          btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int x =0;
                if(tfDesc.getText().length()==0){
                  Dialog.show("Error","Description vide","OK",null);
                }else{
                    r.setDesc(tfDesc.getText());
                
               if(ServiceReclamation.getInstance().updateReclamation(r)){
                   Dialog.show("Success","Reclamation Updated","OK",null);
                   new ReclamationFront(previous).show();
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
                   
               }
                }
            }
          });
       getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
new ReclamationFront(previous).show();
});
       
addAll(tfDesc,btnValider);
   }
}
