package com.mycompany.analizadorlexico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("======================================");
        System.out.println("   Analizador Lexico - PromptZal");
        System.out.println("======================================");
        System.out.print("Ingresa la ruta absoluta del archivo .pz: ");        
        String rutaArchivo = entrada.nextLine();
        rutaArchivo = rutaArchivo.replace("\"", "");
        
        try {
            Path rutaConvertida = Paths.get(rutaArchivo);
            byte[] bytesDelArchivo = Files.readAllBytes(rutaConvertida);
            String contenido = new String(bytesDelArchivo);           
            char[] caracteres = contenido.toCharArray();
            
            System.out.println("\n[EXITO] Archivo cargado correctamente.");
            System.out.println("Total de caracteres a analizar: " + caracteres.length);
            
            AnalizadorLexico analizador = new AnalizadorLexico();
            analizador.analizar(caracteres);
            
        } catch (IOException e) {
            System.err.println("\n[ERROR] No se pudo leer el archivo.");
            System.err.println("Verifica que la ruta sea correcta y que el archivo exista.");
            System.err.println("Detalle técnico: " + e.getMessage());
        } finally {
            entrada.close();
        }
    }
}