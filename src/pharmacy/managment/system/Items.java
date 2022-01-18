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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author abdul
 */
public class Items {
    
    private String code;
    private String name;
    private Date M_date;
    private Date E_date;
    private int quantity;
    private double price;
    private String type;
    private String description;
    
    
    public String getcode(){
    
       return this.code; 
    }
    public String getdescription(){
    
       return this.description; 
    }
    public String getname(){
    
       return this.name;
    }
    public Date getM_date(){
    
       return this.M_date;
    }
    public Date getE_date(){
    
       return this.E_date;
    }
    public int getquantity(){
    
       return this.quantity;
    }
    public double getprice(){
    
       return this.price; 
    }
    public String gettype(){
    
       return this.type; 
    }
    
    Items(){
    
    this.code="";
    this.name="";
    this.E_date=null;
    this.M_date=null;
    this.price=0.0;
    this.quantity=0;
    this.type="";
        
    }
    Items(String code,String name,Date M_date,Date E_date,int quantity,double price,String type,String dis){
    
    this.code=code;
    this.name=name;
    this.E_date=E_date;
    this.M_date=M_date;
    this.price=price;
    this.quantity=quantity;
    this.type=type;
    this.description=dis;
    
    }
    
    public void addItem(String code,String name,Date M_date,Date E_date,int quantity,double price,String type,String description) throws ClassNotFoundException, SQLException{
    
     Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
            String sql="select * from items WHERE code='"+code+"'";
             ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            
         int result = JOptionPane.showConfirmDialog(null,"Item Already Exists Do you Want to increase its quantity ?", "Swing Tester",
               JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
              
                int q=rs.getInt("quantity");
                q=q+quantity;
                String query="Update items set quantity='"+q+"' WHERE code='"+code+"'";
                stmt.execute(query); 
            
             JOptionPane.showMessageDialog(null, "Quantity increased Sucessfully");
         
            }else if (result == JOptionPane.NO_OPTION){
               
            JOptionPane.showMessageDialog(null, "Operation Canceled");
            }
            
         
        }else{
            
            
            this.code=code;
            this.name=name;
            this.price=price;
            this.quantity=quantity;
//            this.E_date=E_date;
//            this.M_date=M_date;
            this.type=type;
     

            String query="Insert into items (code,name,M_date,E_date,quantity,price,type,description) values('"+code+"','"+name+"','"+M_date+"','"+E_date+"','"+quantity+"','"+price+"','"+type+"','"+description+"')";
            
             stmt.execute(query); // insert query execution
            
           // System.out.println(query);
            
             JOptionPane.showMessageDialog(null, "Item Added Sucessfully");
             
        
        
        
        }
        
        
    }
    
    public void updateItem(String code,String name,Date M_date,Date E_date,int quantity,double price,String type) throws ClassNotFoundException, SQLException{
    
         Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
            String sql="select * from items WHERE code='"+code+"'";
             ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            
             String updatequery="Update items set code='"+code+"',name='"+name+"',M_date='"+M_date+"',E_date='"+E_date+"',quantity='"+quantity+"',price='"+price+"',type='"+type+"'  WHERE code='"+code+"'";
            stmt.executeUpdate(updatequery);
            JOptionPane.showMessageDialog(null, "Updated Successfull");
            
        }
        else{
        
             JOptionPane.showMessageDialog(null, "Item Not Found");
            
        }
            
            
           
    
    }
    public void deleteItem(String code) throws ClassNotFoundException, SQLException{
    
     Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
            String sql="select * from items WHERE code='"+code+"'";
             ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            
            int result = JOptionPane.showConfirmDialog(null,"Sure? You want to Delete?", "Swing Tester",
               JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
              String del="DELETE FROM items WHERE code='"+code+"'";
            
            stmt.executeUpdate(del);
            JOptionPane.showMessageDialog(null, "Delete Successfull");
            }else if (result == JOptionPane.NO_OPTION){
               
            JOptionPane.showMessageDialog(null, "Operation Canceled");
            }
            
            
        }
        else{
        
             JOptionPane.showMessageDialog(null, "Item Not Found");
            
        }
        
        
    }
    
    
    
    
}
