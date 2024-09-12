/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.model;

/**
 *
 * @author hcadavid
 */
public class Point {
   
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Point() {
    }    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Este metodo Compara las coordenadas y determina si son iguales.
     * @param punto Punto con el que se desea comprar.
     * @return true si los puntos son iguales.
     */
    public boolean compare(Point punto){
        if(this.x == punto.getX() && this.y == punto.getY()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Punto: (" + "x = " + x + ", y = " + y + ')';
    }
}
    
    

