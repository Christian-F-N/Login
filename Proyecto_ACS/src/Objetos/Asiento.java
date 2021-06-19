/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author USUARIO
 */
public class Asiento {
    int id;
    String numero;
    String ubicacion;
    Bus bus;
    
    
    public Asiento() {
        
    }

    public Asiento(int id, String numero, String ubicacion, Bus bus) {
        this.id = id;
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.bus = bus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
    
    
}
