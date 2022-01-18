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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author abdul
 */
public class Admin {
    
    
    private String name;
    private String password;
    
    
   
    
     Employee e=new Employee();
    Items i=new Items();
    Stock stuk=new Stock();
    Order ordur=new Order();
     ArrayList<Employee> temp = new ArrayList<Employee>();
     
     
      public void setname(String name){
    
    this.name=name;
    }
    
    public void setpassword(String Password){
    
    this.password=Password;
    
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    
    public void display() throws SQLException{
    
     boolean check=false;
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system","root","");
            int count=0;
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user WHERE type=2");
           
           temp.removeAll(temp);
    
            while(rs.next()){
                String name=rs.getString("name");
                String adress=rs.getString("address");
                String cnic=rs.getString("cnic");
                Date date=rs.getDate("Birth");
                int type=rs.getInt("type");
               float sal=rs.getFloat("sallery");
       
                String pass=rs.getString("Password");
                String email=rs.getString("email");
                String Status=rs.getString("status");
                
                Employee e=new Employee(adress,sal,cnic,date,email,pass,name,type,Status);
            
                temp.add(e);
                
                }

    
    
    }
    
    public void search(String Search,JTable MedicineTable1){
    
    DefaultTableModel model;
        model=(DefaultTableModel) MedicineTable1.getModel();
        TableRowSorter<DefaultTableModel> trs=new TableRowSorter(model);
        MedicineTable1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(Search));
    }
    
      public ArrayList<Order> displayrecord() throws SQLException{
    
        
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system","root","");
            
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from sales");
            
        ArrayList<Order> temp=new ArrayList<Order>();
                   
        
                temp.removeAll(temp);
                
                while(rs.next()){
                
                    ordur=new Order();
                ordur.setCustomername(rs.getString("customer_name"));
                ordur.setSale_id(rs.getString("sale_id"));
                ordur.setBill_id(rs.getString("bill_id"));
                ordur.setInventoryname(rs.getString("inventory_name"));
                ordur.setInventory_id(rs.getString("inventory_id"));
                ordur.setQuantity(rs.getString("quantity"));
                ordur.setPrice(rs.getString("price"));
                ordur.setTotal(rs.getString("total"));
                ordur.setStatus(rs.getString("status"));
                
   
           
                temp.add(ordur);
                
                }
        
        
        return temp;
            
  
           
            
    
    
    
    }
    
}
