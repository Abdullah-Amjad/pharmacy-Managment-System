/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacy.managment.system;

/**
 *
 * @author abdul
 */
public class Order {
    
    customer Customer=new customer();
    Stock Stok=new Stock();
    Bill bill=new Bill();
    Refund refund=new Refund();
    
    String sale_id;
    String bill_id;
    String inventory_id;

    
    String customername;
    String inventoryname;
    String quantity;
    String price;
    String total;
    String status;

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }
    public String getInventory_id() {
        return inventory_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

   
    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public void setInventoryname(String inventoryname) {
        this.inventoryname = inventoryname;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSale_id() {
        return sale_id;
    }

    public String getBill_id() {
        return bill_id;
    }

    public String getCustomername() {
        return customername;
    }

    public String getInventoryname() {
        return inventoryname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
    
    
    
    
    public float balance(String totalamount,String pay)
            
    {  float totall=Float.parseFloat(totalamount);
            float payy=Float.parseFloat(pay);
        
            float balancee=payy-totall;
           // balance.setText(String.valueOf(balancee));
            return balancee;
        
            
        
    }
    
    
    
}
