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
public class Reserva {
    int id;
    String estado;
    Ruta ruta;
    Cliente cliente;
    Asiento asiento;
    
    public Reserva() {
       
    }

    public Reserva(int id, String estado, Ruta ruta, Cliente cliente, Asiento asiento) {
        this.id = id;
        this.estado = estado;
        this.ruta = ruta;
        this.cliente = cliente;
        this.asiento = asiento;
    }
    
            
    
}
