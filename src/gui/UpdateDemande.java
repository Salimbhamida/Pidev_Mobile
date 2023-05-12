/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;

import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.StringUtil;
import entities.Demande;
import java.util.ArrayList;
import java.util.Date;
import services.ServiceDemande;

/**
 *
 * @author Lord
 */
public class UpdateDemande extends Form {
     String experationD = "";
     
    public UpdateDemande(Form previous , Demande d){
    setTitle("Update Demande");
        setLayout(BoxLayout.y());
        
        String telS = String.valueOf(d.getTelephone());
        String experS = String.valueOf(d.getExperience());
        String renumS = String.valueOf(d.getRemuneration());
        
        TextField tfName=new TextField(d.getNomRecruteur()," nom Recruteur");
        TextField tfDesc= new TextField(d.getDesc()," Description");
        TextField tfExperi= new TextField(experS," Experience");
        TextField tfRemun= new TextField(renumS," Remuneration");
        TextField tfTel= new TextField(telS," Telephone");
      
        Label Experation = new Label("Experation date:"+ d.getExperation());
         
       Calendar cld = new Calendar();
cld.addActionListener((e) -> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    experationD = dateFormat.format(new Date(cld.getSelectedDay()));
  
});

        Button btnValider = new Button("Update Demande");
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int x =0;
               if(tfName.getText().length()==0 || tfDesc.getText().length()==0 || tfExperi.getText().length()==0 ||tfRemun.getText().length()==0 || tfTel.getText().length()==0 || experationD.length()==0  ){
                 Dialog.show("Error","empty fields","OK",null);
                 x=1;
            }
              
               if(x==0){
                    int teleph = Integer.parseInt(tfTel.getText());
                    int experience = Integer.parseInt(tfExperi.getText());
                    float    remuneration = Float.parseFloat(tfRemun.getText());
                    
                   
                   Demande dem = new Demande(d.getId(),tfName.getText(),tfDesc.getText(),experience,remuneration,teleph,experationD,1);
                   if(ServiceDemande.getInstance().updateDemande(dem)){
                   Dialog.show("Success","Demande Updated","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
               }
                
            }
         });
     
           getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
new ShowDemande(previous).show();
});
addAll(tfName,tfDesc,tfExperi,tfRemun,tfTel,Experation,cld,btnValider); 
    }
}
