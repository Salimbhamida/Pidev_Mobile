/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author Salim Ben Hamida
 */
public class User {

    int Id;
    String Username, email, Password, Role, Remember_Token, Created_at, Updated_at;

    public User() {
    }

    public User(String Username, String email, String Password) {
        this.Username = Username;
        this.email = email;
        this.Password = Password;
    }

   

    public User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public User(String Username, String email, String Password, String Role) {
        this.Username = Username;
        this.email = email;
        //Cryptage Mot de passe
        this.Password = Password;
        this.Role = Role;

    }

    public User(int Id, String Username, String email, String Password, String Role) {
        this.Id = Id;
        this.Username = Username;
        this.email = email;
        this.Password = Password;
        this.Role = Role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password =Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

 
    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Username=" + Username + ", email=" + email + ", Password=" + Password + ", Role=" + Role +  '}';
    }

    
}
