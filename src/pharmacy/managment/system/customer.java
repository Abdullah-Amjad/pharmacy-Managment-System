/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacy.managment.system;

/**
 *
 * @author abdul
 */
public class customer {
    
    private String name;
    
    customer(){
    
        this.name="";
    }
    customer(String name){
    
        this.name=name;
    
    }
    
    public void setname(String name){
    
        this.name=name;
    
    }
    
    public String getname(){
    
        return this.name;
    }
    
}
