package com.mycompany.analizadorlexico;

import com.mycompany.analizadorlexico.modelos.Token;
import com.mycompany.analizadorlexico.modelos.ErrorLexico;
import com.mycompany.analizadorlexico.modelos.TipoToken;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorLexico {

    private List<Token> listaTokens;
    private List<ErrorLexico> listaErrores;
    private int contadorTokens;

    private char[] caracteres;
    private int i;
    private int fila;
    private int columna;

    public AnalizadorLexico() {
        this.listaTokens = new ArrayList<>();
        this.listaErrores = new ArrayList<>();
        this.contadorTokens = 1;
    }

   
    
    public void analizar(char[] entrada) {
        this.caracteres = entrada;
        this.i = 0;
        this.fila = 1;
        this.columna = 1;

        while (i < caracteres.length) {
            char c = caracteres[i];
            
            //Consulta uno por uno el tipo de token por medio de metodos
            if (esEspacioOSalto(c)) {
                manejarEspacios(c);
            } else if (esDelimitador(c)) {
                manejarDelimitador(c);
            } else if (esOperadorOConector(c)) {
                manejarOperadores(c);
            } else {
                manejarError(c);
            }
        }
        //Final del while se imprime la tabla
        imprimirResultados();
    }


    
    
    
    
    
    //Consultas
    private boolean esEspacioOSalto(char c) {
        return c == '\n' || c == ' ' || c == '\t' || c == '\r';
    }

    private boolean esDelimitador(char c) {
        return c == '{' || c == '}' || c == '(' || c == ')' || c == ',';
    }

    private boolean esOperadorOConector(char c) {
        return c == '=' || c == '+' || c == '-';
    }


    
    
    
    //Guardar token
    private void manejarEspacios(char c) {
        if (c == '\n') {
            fila++;
            columna = 1;
        } else {
            columna++;
        }
        i++;
    }

    private void manejarDelimitador(char c) {
        if (c == '{') {
            listaTokens.add(new Token(contadorTokens++, "{", TipoToken.LLAVE_ABRE, fila, columna));
        } else if (c == '}') {
            listaTokens.add(new Token(contadorTokens++, "}", TipoToken.LLAVE_CIERRA, fila, columna));
        } else if (c == '(') {
            listaTokens.add(new Token(contadorTokens++, "(", TipoToken.PARENTESIS_ABRE, fila, columna));
        } else if (c == ')') {
            listaTokens.add(new Token(contadorTokens++, ")", TipoToken.PARENTESIS_CIERRA, fila, columna));
        } else if (c == ',') {
            listaTokens.add(new Token(contadorTokens++, ",", TipoToken.COMA, fila, columna));
        }
        columna++;
        i++;
    }

    
    private void manejarOperadores(char c) {
        if (c == '=') {
            listaTokens.add(new Token(contadorTokens++, "=", TipoToken.OPERADOR_ASIGNACION, fila, columna));
            columna++; i++;
        } else if (c == '+') {
            listaTokens.add(new Token(contadorTokens++, "+", TipoToken.OPERADOR_CONCATENACION, fila, columna));
            columna++; i++;
        } else if (c == '-') {
            //Manejo del conector flecha por ser simbolo
            if (i + 1 < caracteres.length && caracteres[i + 1] == '>') {
                listaTokens.add(new Token(contadorTokens++, "->", TipoToken.CONECTOR_FLECHA, fila, columna));
                columna += 2; 
                i += 2;       
            } else {
                listaErrores.add(new ErrorLexico("-", "Guion suelto no reconocido (se esperaba '->')", fila, columna));
                columna++; i++;
            }
        }
    }

    //Metodo para los errores
    private void manejarError(char c) {
        listaErrores.add(new ErrorLexico(String.valueOf(c), "Carácter no reconocido", fila, columna));
        columna++;
        i++;
    }

    
    
    //Metodo para imprimir los resultados
    private void imprimirResultados() {
        System.out.println("\n--- TOKENS ENCONTRADOS ---");
        for (Token t : listaTokens) {
            System.out.println(t.toString());
        }

        System.out.println("\n--- ERRORES ENCONTRADOS ---");
        if (listaErrores.isEmpty()) {
            System.out.println("No se encontraron errores léxicos.");
        } else {
            for (ErrorLexico e : listaErrores) {
                System.out.println(e.toString());
            }
        }
    }
}