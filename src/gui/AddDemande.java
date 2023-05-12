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
public class AddDemande extends Form {
    String experationD = "";
    
    public AddDemande(Form previous){
        setTitle("Add Demande");
        setLayout(BoxLayout.y());
        
        TextField tfName=new TextField(""," nom Recruteur");
        TextField tfDesc= new TextField(""," Description");
        TextField tfExperi= new TextField(""," Experience");
        TextField tfRemun= new TextField(""," Remuneration");
        TextField tfTel= new TextField(""," Telephone");
      
        Label Experation = new Label("Experation date:");
         
       Calendar cld = new Calendar();
cld.addActionListener((e) -> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    experationD = dateFormat.format(new Date(cld.getSelectedDay()));
  
});

        Button btnValider = new Button("Add Demande");
        
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
               
                   Demande d = new Demande(tfName.getText(),tfDesc.getText(),experience,remuneration,teleph,experationD,1);
                   if(ServiceDemande.getInstance().addDemande(d)){
                   Dialog.show("Success","Demande Added","OK",null);
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
