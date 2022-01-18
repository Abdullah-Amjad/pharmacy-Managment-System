/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacy.managment.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public class Refund {
    
    
    public void makerefund(String id,String saleid,int quantityyy){
    
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system","root","");
            
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from items where code='"+id+"'");
            if(rs.next())
            {
                String previous_quantity=rs.getString("quantity");
                int pre_quantity=Integer.parseInt(previous_quantity);
                int new_quantity=pre_quantity+quantityyy;
                
                
                
                String updatequery="Update items set quantity='"+new_quantity+"' WHERE code='"+id+"'";
                stmt.executeUpdate(updatequery);
            }
            String query=("Update sales set status='"+"refunded"+"' where sale_id="+saleid);
                stmt.executeUpdate(query);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
    
    
    
    }
    
    public void searchh(String id, JTable billTable){
    
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system","root","");
            
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from sales where bill_id='"+id+"'");
            billTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
            
            //while(rs.next())
            catch(Exception e)
        {
            System.out.println(e);
        }          
    
    }
    
}
