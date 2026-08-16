package com.mycompany.analizadorlexico.modelos;

public class ErrorLexico {
    private String caracter;   
    private String descripcion; 
    private int fila;           
    private int columna;        


    public ErrorLexico(String caracter, String descripcion, int fila, int columna) {
        this.caracter = caracter;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
    }


    public String getCaracter() {
        return caracter; 
    }
    public String getDescripcion() {
        return descripcion; 
    }
    public int getFila() { 
        return fila; 
    }
    public int getColumna() {
        return columna; 
    }

    @Override
    public String toString() {
        return "Error Lexico -> Caracter: '" + caracter + "' | Descripcion: " + descripcion + " | Fila: " + fila + " | Columna: " + columna;
    }
}