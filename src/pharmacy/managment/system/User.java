/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacy.managment.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author abdul
 */
public class User {
    
    protected String name;

    protected String password;
   
    
    public User(){
    
        
        this.name="";
        this.password="";
     
        
        
        
        
    }
    
    public void setname(String name){
        
        this.name=name;

    }
    public String getname(){
    
    return name;
    
            }

    
    public void setpassword(String password){
        
        this.password=password;

    }
    public String getpassword(){
    
    return password;
    
            }
    
 
    
    
    public boolean login(String name,String password) throws SQLException, ClassNotFoundException{
    
        //return 1 then go to admin else
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
             
           // String sql="select * from user WHERE name='"+name+"' AND password='"+password+"'";
            String sql="select * from user WHERE name='"+name+"'";
            
             ResultSet rs = stmt.executeQuery(sql);
             
           
             
             if(rs.next()){
                 
                 if(rs.getInt("type")==1){
                 
                 
                 
                 if(password.equals(rs.getString("password"))){
                     
                     adminAcess a=new adminAcess();
                        a.setVisible(true);
                  
                   return true;
                 
                 }else{
                     
                     JOptionPane.showMessageDialog(null, "wrong name/password","Warning",JOptionPane.ERROR_MESSAGE);
                     return false;
                 
                 }
                 }else{
                 
                 
                     //System.out.println("employee check");
                     
                     if(password.equals(rs.getString("password"))){
                     
                         if(rs.getInt("attempts")==3){
                         
                         
                         JOptionPane.showMessageDialog(null, "Account Blocked","Warning",JOptionPane.ERROR_MESSAGE);
                         
                         }else{
                      employeeAccess e=new employeeAccess();
                      e.setVisible(true);
                     return true;}
                     }
                     else{
                         
                         if(rs.getInt("attempts")==3){
                         
                             
                          
                         JOptionPane.showMessageDialog(null, "Account Blocked","Warning",JOptionPane.ERROR_MESSAGE);
                         
                         }else{
                         int id=rs.getInt("id");
                         int attempt=rs.getInt("attempts");
                         attempt++;
                        String updatequery= "Update user set attempts='"+attempt+"' WHERE id='"+id+"'";
                        stmt.executeUpdate(updatequery);
                     
                        if(attempt==3){
                         JOptionPane.showMessageDialog(null, "wrong name/password","Warning",JOptionPane.ERROR_MESSAGE);
                     
                         String updatequeryy= "Update user set status='"+"Blocked"+"' WHERE id='"+id+"'";
                        stmt.executeUpdate(updatequeryy);
                         
                          JOptionPane.showMessageDialog(null, "Your Account is Blocked","Warning",JOptionPane.ERROR_MESSAGE);
                     
                         return false;
                         }else{
                          
                           JOptionPane.showMessageDialog(null, "wrong name/password","Warning",JOptionPane.ERROR_MESSAGE);
                     
                          return false;
                         }
                         
                         
                     
                         }
                     
                     }
                 
                 
                 }
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
             }else{
             
                 
                 
                 JOptionPane.showMessageDialog(null, "No user found");
                 return false;
             
             }
             
             
             
             return false;
    }
    
    
    
}
