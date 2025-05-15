/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Timestamp;

/**
 *
 * @author saray
 */
public class Movement {
    private int idMovement;
    private int idProduct;
    private String productName; // opcional para mostrar directamente
    private String username;
    private String type; // entrada o salida
    private int quantity;
    private Timestamp timestamp;
    
    public int getIdMovement() {
    return idMovement;
    }

    public void setIdMovement(int idMovement) {
    this.idMovement = idMovement;
    }

    public int getIdProduct() {
    return idProduct;
    }

    public void setIdProduct(int idProduct) {
    this.idProduct = idProduct;
    }

    public String getProductName() {
    return productName;
    }

    public void setProductName(String productName) {
    this.productName = productName;
    }

    public String getUser() {
    return username;
    }

    public void setUser(String user) {
    this.username = user;
    }

    public String getType() {
    return type;
    }

    public void setType(String type) {
    this.type = type;
    }

    public int getQuantity() {
    return quantity;
    }

    public void setQuantity(int quantity) {
    this.quantity = quantity;
    }

    public Timestamp getTimestamp() {
    return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
    }
}
