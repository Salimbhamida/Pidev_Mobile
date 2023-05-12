/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.User;
import services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author Salim Ben Hamida
 */
public class DashboardForm extends BaseForm {

    Form f;
    Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    ArrayList<User> list = ServiceUser.getInstance().parseservice();

    public DashboardForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Dashboard");
        getContentPane().setScrollVisible(false);

        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("User");

        TextField searchField = new TextField("", "Search");
        searchField.getStyle().setFgColor(0xFF0000);

        searchField.addDataChangeListener((i1, i2) -> {
            // Implement your search logic here 
            String text = searchField.getText();

            if (text.length() != 0) {
                list = ServiceUser.getInstance().parseDemandeSearch(text);

                C2.removeAll(); // remove all the existing components from the container

                addUser(list,res); // add the filtered categories to the container

                refreshTheme();
            } else {
                list = ServiceUser.getInstance().parseservice();
                C2.removeAll(); // remove all the existing components from the container

                addUser(list,res); // add the filtered categories to the container

                refreshTheme();
            }

        });

        addUser(list,res);

        addAll(C2);
        getToolbar().setTitleComponent(searchField);

    }

    public void addUser(ArrayList<User> list,Resources res) {

        for (User c : list) {

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Button b = new Button("remove");
            Button mm = new Button("update");
            Label id = new Label("Id: " + c.getId());
            Label l = new Label("Username: " + c.getUsername());
            Label l2 = new Label("Email:" + c.getEmail());
            String pwd = String.valueOf(c.getPassword());
            Label l3 = new Label("Password: " + pwd);
            String role = String.valueOf(c.getRole());
            Label l4 = new Label("Role: " + role);
            String tel = String.valueOf(c.getRole());

            C1.addAll(l);
            C3.add(l2);
            C4.add(l3);
            C5.add(l4);
            C6.add(id);
            Label sep = new Label("_____________________________");
            C2.addAll(C1, C3, C4, C5, C6, b, mm, sep);
            mm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
                     new UpdateAdminDashboard(res,c).show();

                }
            });
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ArrayList<User> listU = ServiceUser.getInstance().parseservice();
                    Boolean result = Dialog.show("Confirmation !", "Are You Sure to Delete the Current User", "OK", "Cancel");
                    if (result) {
                        ServiceUser.getInstance().deleteservice(c.getId());
                        C2.removeAll(); // remove all the existing components from the container
                        listU = ServiceUser.getInstance().parseservice();
                        addUser(listU,res); // add the filtered categories to the container

                        refreshTheme();

                    }

                }
            });
            // f.add(C2);
//f.refreshTheme();

        }

    }

}
