package com.example.studio.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity @Table(name="orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private double totalPrice;
    private String status; // PENDING/PAID/FAILED

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem i){ items.add(i); i.setOrder(this); }

    // getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public double getTotalPrice(){return totalPrice;}
    public void setTotalPrice(double totalPrice){this.totalPrice=totalPrice;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public Date getCreatedAt(){return createdAt;}
    public void setCreatedAt(Date d){this.createdAt=d;}
    public List<OrderItem> getItems(){return items;}
    public void setItems(List<OrderItem> items){this.items=items;}
}
