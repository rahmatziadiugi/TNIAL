/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aldebaran
 */
public class Login {
    private String username ;
    private String password ;
    private int role ;
    
    public Login(String username, String password, int role){
        this.username = username ;
        this.password = password ;
        this.role = role ;
    }
    public String getUsername(){
        return this.username ;
    }
    public String getPassword(){
        return this.password ;
    }
    public int getRole(){
        return this.role ;
    }
}
