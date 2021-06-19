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
public class Bus {
    int id;
    String placa;
    int año;
    String marca;
   Cooperativa coopetativa;

   public Bus() {
 
    }
    
    public Bus(int id, String placa, int año, String marca, Cooperativa coopetativa) {
        this.id = id;
        this.placa = placa;
        this.año = año;
        this.marca = marca;
        this.coopetativa = coopetativa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Cooperativa getCoopetativa() {
        return coopetativa;
    }

    public void setCoopetativa(Cooperativa coopetativa) {
        this.coopetativa = coopetativa;
    }
   
    
    
}
