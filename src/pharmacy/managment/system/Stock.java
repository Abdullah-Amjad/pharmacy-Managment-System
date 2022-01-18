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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author abdul
 */
public class Stock {
    
     Items i;
     ArrayList<Items> temp = new ArrayList<Items>();
    
    public void displaystock() throws SQLException{
        
            boolean check=false;
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system","root","");
            int count=0;
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from items");
           
           temp.removeAll(temp);
    
            while(rs.next()){
                String name=rs.getString("name");
                String code=rs.getString("code");
                Date M_date=rs.getDate("M_date");
                Date E_date=rs.getDate("E_date");
                int quantity=rs.getInt("quantity");
                double price=rs.getFloat("price");
       
                String type=rs.getString("type");
                String des=rs.getString("description");
                  
              
            Items i=new Items(code,name,M_date,E_date,quantity,price,type,des);
            
                temp.add(i);
                
                }

            
  
           
            
    
    }
    
    public void search(String Search,JTable MedicineTable1){
    
    DefaultTableModel model;
        model=(DefaultTableModel) MedicineTable1.getModel();
        TableRowSorter<DefaultTableModel> trs=new TableRowSorter(model);
        MedicineTable1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(Search));
    }
    
}
