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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author abdul
 */
public class Employee {
    
    private String address;
    private float sallary;
    private String cnic;
    private Date dateofbirth;
    private String email;
    private String name;
    private String password;
    private int Type;
    private String status;

  
    Stock s=new Stock();
    
    
    
    Employee(){
     this.address="";
     this.sallary=(float) 0.0;
     this.cnic="";
     this.dateofbirth=new Date(0,0,0);
     this.email="";
     this.password="";
     this.name="";
     this.Type=2;
     this.status="Active";
     
    
    }
    
    Employee(String adress,float sal,String cnic,Date d,String email,String password,String name,int Type,String status){
     this.address=adress;
     this.sallary=sal;
     this.cnic=cnic;
     this.dateofbirth=d;
     this.email=email;
     this.password=password;
     this.name=name;
     this.Type=Type;
     this.status=status;
     
    
    }

    public String getAddress() {
        return address;
    }

    public float getSallary() {
        return sallary;
    }

    public String getCnic() {
        return cnic;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return Type;
    }
      public String getStatus() {
        return status;
    }
    
    
    // these are the objects of Other classes
//    private stock S;
//    private item i;
//    private order o;
    
    public void AddEmployee(String name,String address,double sallary,String cnic,Date date,String Email) throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
            String sql="select * from user WHERE cnic='"+cnic+"' OR email='"+Email+"' OR name='"+name+"'";
            
            
             ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            
         JOptionPane.showMessageDialog(null, "User Already Registered");
         
        }else{
            
            this.address=address;
            this.cnic=cnic;
            this.dateofbirth=date;
            this.email=Email;
            this.sallary=(float) sallary;
            this.name=name;
            this.password=passwordGenerator();
            
           
            this.Type=2;
            
          
            
            String query="Insert into user (name,password,Type,address,cnic,email,Birth,sallery) values('"+name+"','"+password+"','"+Type+"','"+address+"','"+cnic+"','"+email+"','"+date+"','"+sallary+"')";
             stmt.execute(query); // insert query execution
            
           // System.out.println(query);
            
            
            mailsend(email,password);
            
            
            
            
             JOptionPane.showMessageDialog(null, "Password has been send to Email Sucessfully");
             JOptionPane.showMessageDialog(null, "Registration Sucessfully");
        }
    }
    
    public String passwordGenerator(){
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    String password = buffer.toString();
    return password;
    
                }
                                                                                    //later On it will be Date Type // Id = Cnic old
    public void UpdateEmployee(String name,String address,double sallary,String cnic,Date date,String Email,String id) throws SQLException, ClassNotFoundException{
        
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
            String sql="select * from user WHERE cnic='"+id+"' AND Type='"+2+"'";
             ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            
             String updatequery="Update user set name='"+name+"',address='"+address+"',cnic='"+cnic+"',email='"+Email+"',Birth='"+date+"',sallery='"+sallary+"'  WHERE cnic='"+id+"'";

            
            stmt.executeUpdate(updatequery);
            JOptionPane.showMessageDialog(null, "Updated Successfull");
            
        }
        else{
        
             JOptionPane.showMessageDialog(null, "User Not Found");
            
        }
            
            
           
    
    }
    public void DeleteEmployee(String cnic) throws ClassNotFoundException, SQLException{
        
       Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy managment system ","root","");
            Statement stmt = conn.createStatement();
            String sql="select * from user WHERE cnic='"+cnic+"' AND Type='"+2+"'";
             ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            
            int result = JOptionPane.showConfirmDialog(null,"Sure? You want to Delete?", "Swing Tester",
               JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
              String del="DELETE FROM user WHERE cnic='"+cnic+"'";
            
            stmt.executeUpdate(del);
            JOptionPane.showMessageDialog(null, "Delete Successfull");
            }else if (result == JOptionPane.NO_OPTION){
               
            JOptionPane.showMessageDialog(null, "Operation Canceled");
            }
            
            
        }
        else{
        
             JOptionPane.showMessageDialog(null, "User Not Found");
            
        }
        
    }
    
    public void mailsend(String reciver,String password){
        
        // Recipient's email ID needs to be mentioned.
        String to = reciver;

        // Sender's email ID needs to be mentioned
        String from = "storepharmacy20@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("storepharmacy20@gmail.com", "12kafour");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Your password!");

            // Now set the actual message
            message.setText("Your Password is : "+password);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
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
