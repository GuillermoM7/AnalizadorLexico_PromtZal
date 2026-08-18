package com.mycompany.analizadorlexico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static final String RESET = "\033[0m";
    public static final String VERDE = "\033[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String CELESTE = "\u001B[36m";
    public static final String ROJO = "\033[31m";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        System.out.println(AZUL + "+---------------------------------------+");
        System.out.println("|     Analizador Lexico - PromptZal     |");
        System.out.println("+---------------------------------------+" + RESET);
        System.out.print(VERDE + "Ingresa la ruta absoluta del archivo .pz: " + RESET);        
        String rutaArchivo = entrada.nextLine();
        rutaArchivo = rutaArchivo.replace("\"", "");
        
        try {
            Path rutaConvertida = Paths.get(rutaArchivo);
            byte[] bytesDelArchivo = Files.readAllBytes(rutaConvertida);
            String contenido = new String(bytesDelArchivo);           
            char[] caracteres = contenido.toCharArray();
            
            System.out.println(VERDE + "\n[EXITO] Archivo cargado correctamente.");
            System.out.println("Total de caracteres a analizar: " + caracteres.length + RESET);
            
            AnalizadorLexico analizador = new AnalizadorLexico();
            analizador.analizar(caracteres);
            
        } catch (IOException e) {
            System.err.println(ROJO +"\n[ERROR] No se pudo leer el archivo.");
            System.err.println("Verifica que la ruta sea correcta y que el archivo exista." + RESET);
            System.err.println("Detalle técnico: " + e.getMessage());
        } finally {
            entrada.close();
        }
    }
}