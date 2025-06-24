package com.upiiz.ExamenIII.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_orden")
public class OrdenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ordenid")
    private int ordenid;

    @Column(name = "productoid")
    private int productoid;

    @Column(name = "cantidad")
    private int cantidad;

    public OrdenModel() {
    }

    public OrdenModel(int ordenid, int productoid, int cantidad) {
        this.ordenid = ordenid;
        this.productoid = productoid;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getOrdenid() { return ordenid; }

    public void setOrdenid(int ordenid) { this.ordenid = ordenid; }

    public int getProductoid() { return productoid; }

    public void setProductoid(int productoid) { this.productoid = productoid; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
