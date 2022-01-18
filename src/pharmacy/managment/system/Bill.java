/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacy.managment.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author abdul
 */
public class Bill {
    
    

    
   
    public int bill_id(){
    int j=0;
    try{
        
        
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system","root","");
            
            Statement stmt=con.createStatement();
            
                ResultSet rs=stmt.executeQuery("SELECT * FROM sales ORDER BY bill_id DESC LIMIT 1");
                if(rs.next())
                {
                    j=rs.getInt("bill_id");
                }

        }catch(Exception e)
        {
            System.out.println(e);
        }
      
    
    return j;
    
    }
    
    
}
