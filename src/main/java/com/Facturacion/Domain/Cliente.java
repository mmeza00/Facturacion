package com.Facturacion.Domain;


public class Cliente {
    private int idCliente;
    private String nombreCliente;
    private String cedula;
    private String correoCliente;
    private String telefono;
    private int idCredito;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public Cliente(int idCliente, String nombreCliente, String cedula, String correoCliente, String telefono, int idCredito) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.cedula = cedula;
        this.correoCliente = correoCliente;
        this.telefono = telefono;
        this.idCredito = idCredito;
    }
    
    
}
