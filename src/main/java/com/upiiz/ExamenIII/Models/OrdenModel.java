package com.upiiz.ExamenIII.Models;

public class OrdenModel {
    private Long id;
    private int ordenid;
    private int productoid;
    private int cantidad;

    public OrdenModel() {

    }
    public OrdenModel(int ordenid, int productoid, int cantidad) {
        this.ordenid = ordenid;
        this.productoid = productoid;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id;}

    public int getOrdenid() { return ordenid; }

    public void setOrdenid(int orden_id) { this.ordenid = ordenid; }

    public int getProductoid() { return productoid; }

    public void setProductoid(int productoid) { this.productoid = productoid; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
