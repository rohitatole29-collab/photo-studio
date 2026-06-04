package com.example.studio.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name="photo_id")
    private Photo photo;
    @ManyToOne @JoinColumn(name="order_id")
    private Order order;

    public OrderItem(){}
    public OrderItem(Photo p){ this.photo = p; }

    // getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public Photo getPhoto(){return photo;}
    public void setPhoto(Photo photo){this.photo = photo;}
    public Order getOrder(){return order;}
    public void setOrder(Order order){this.order = order;}
}
