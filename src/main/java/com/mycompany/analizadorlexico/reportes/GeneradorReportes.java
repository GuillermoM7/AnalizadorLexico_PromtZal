package com.mycompany.analizadorlexico.reportes;

import com.mycompany.analizadorlexico.modelos.Token;
import com.mycompany.analizadorlexico.modelos.ErrorLexico;
import com.mycompany.analizadorlexico.modelos.TipoToken;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GeneradorReportes {
    public static final String RESET = "\033[0m";
    public static final String VERDE = "\033[32m";
    public static final String ROJO = "\033[31m";
    
    private final String CSS_COMUN = 
        "<style>\n" +
        "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f2f5; margin: 40px; color: #333; }\n" +
        "h2 { color: #2c3e50; border-bottom: 3px solid #3498db; padding-bottom: 10px; display: inline-block; }\n" +
        "table { border-collapse: collapse; width: 100%; margin-top: 20px; background-color: #fff; box-shadow: 0 4px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }\n" +
        "th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }\n" +
        "th { background-color: #2c3e50; color: #ffffff; text-transform: uppercase; font-size: 0.9em; letter-spacing: 0.5px; }\n" +
        "tr:hover { background-color: #f9f9f9; }\n" +
        ".etiqueta { padding: 5px 10px; border-radius: 12px; color: white; font-weight: bold; font-size: 0.85em; text-align: center; display: inline-block; }\n" +
        ".resumen { background-color: #fff; padding: 20px; border-left: 5px solid #27ae60; box-shadow: 0 2px 5px rgba(0,0,0,0.1); border-radius: 4px; margin-bottom: 20px; font-size: 1.1em; }\n" +
        "</style>\n";
    
    
   
    public void generarReporteTokens(List<Token> listaTokens) {
        String nombreArchivo = "Reporte_Tokens_" + obtenerMarcaDeTiempo() + ".html";
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Reporte de Tokens</title>\n");
        html.append(CSS_COMUN);
        html.append("</head>\n<body>\n");
        html.append("<h2>Reporte de Tokens - PromptZal V2</h2>\n");
        html.append("<table>\n");
        html.append("<tr><th>#</th><th>Lexema</th><th>Tipo de Token</th><th>Fila</th><th>Columna</th></tr>\n");

        for (Token t : listaTokens) {
            html.append("<tr>")
                .append("<td>").append(t.getId()).append("</td>")
                .append("<td><strong>").append(escaparHTML(t.getLexema())).append("</strong></td>")
                .append("<td><span class=\"etiqueta\" style=\"background-color: ").append(obtenerColorToken(t.getTipo())).append(";\">").append(t.getTipo()).append("</span></td>")
                .append("<td>").append(t.getFila()).append("</td>")
                .append("<td>").append(t.getColumna()).append("</td>")
                .append("</tr>\n");
        }
        html.append("</table>\n</body>\n</html>");
        guardarArchivo(nombreArchivo, html.toString());
    }

       
    public void generarReporteErrores(List<ErrorLexico> listaErrores) {
        String nombreArchivo = "Reporte_Errores_" + obtenerMarcaDeTiempo() + ".html";
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Reporte de Errores</title>\n");
        html.append(CSS_COMUN);
        html.append("<style>th { background-color: #e74c3c; }</style>\n"); 
        html.append("</head>\n<body>\n");
        html.append("<h2>Reporte de Errores Léxicos</h2>\n");

        if (listaErrores.isEmpty()) {
            html.append("<div class=\"resumen\" style=\"border-left-color: #3498db;\"><strong>¡Excelente!</strong> No se encontraron errores léxicos en el código analizado.</div>\n");
        } else {
            html.append("<table>\n");
            html.append("<tr><th>Lexema / Carácter</th><th>Descripción del Error</th><th>Fila</th><th>Columna</th></tr>\n");

            for (ErrorLexico e : listaErrores) {
                html.append("<tr>")
                    .append("<td style=\"color: #e74c3c; font-weight: bold;\">").append(escaparHTML(e.getCaracter())).append("</td>")
                    .append("<td>").append(e.getDescripcion()).append("</td>")
                    .append("<td>").append(e.getFila()).append("</td>")
                    .append("<td>").append(e.getColumna()).append("</td>")
                    .append("</tr>\n");
            }
            html.append("</table>\n");
        }
        html.append("</body>\n</html>");
        guardarArchivo(nombreArchivo, html.toString());
    }
    
    public void generarReporteEstadisticas(List<Token> listaTokens, List<ErrorLexico> listaErrores) {
        String nombreArchivo = "Reporte_Estadisticas_" + obtenerMarcaDeTiempo() + ".html";
        List<TipoToken> tipos = new ArrayList<>();
        List<Integer> conteos = new ArrayList<>();
        int totalLineas = 1;

        for (Token t : listaTokens) {
            if (t.getFila() > totalLineas) totalLineas = t.getFila();
            if (tipos.contains(t.getTipo())) {
                int i = tipos.indexOf(t.getTipo());
                conteos.set(i, conteos.get(i) + 1);
            } else {
                tipos.add(t.getTipo());
                conteos.add(1);
            }
        }
        for (ErrorLexico e : listaErrores) {
            if (e.getFila() > totalLineas) totalLineas = e.getFila();
        }

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Estadísticas</title>\n");
        html.append(CSS_COMUN);
        html.append("<style>th { background-color: #27ae60; }</style>\n"); // Verde
        html.append("</head>\n<body>\n");
        html.append("<h2>Estadísticas del Análisis</h2>\n");

        html.append("<div class=\"resumen\">\n");
        html.append("<p>Tokens Válidos: <strong>").append(listaTokens.size()).append("</strong></p>\n");
        html.append("<p>Errores Léxicos: <strong>").append(listaErrores.size()).append("</strong></p>\n");
        html.append("<p>Líneas Analizadas: <strong>").append(totalLineas).append("</strong></p>\n");
        html.append("</div>\n");

        if (!tipos.isEmpty()) {
            html.append("<table>\n<tr><th>Tipo de Token</th><th>Cantidad Encontrada</th></tr>\n");
            for (int i = 0; i < tipos.size(); i++) {
                html.append("<tr>")
                    .append("<td><span class=\"etiqueta\" style=\"background-color: ").append(obtenerColorToken(tipos.get(i))).append(";\">").append(tipos.get(i)).append("</span></td>")
                    .append("<td><strong>").append(conteos.get(i)).append("</strong></td>")
                    .append("</tr>\n");
            }
            html.append("</table>\n");
        }
        html.append("</body>\n</html>");
        guardarArchivo(nombreArchivo, html.toString());
    }
    
    private String obtenerColorToken(TipoToken tipo) {
        String nombre = tipo.name();
        if (nombre.startsWith("DIRECTIVA")) return "#8e44ad"; // Morado
        if (nombre.startsWith("RESERVADA")) return "#2980b9"; // Azul
        if (nombre.startsWith("COMANDO")) return "#27ae60";   // Verde
        if (nombre.startsWith("FUNCION")) return "#d35400";   // Naranja
        if (nombre.startsWith("CONECTOR")) return "#16a085";  // Turquesa
        if (nombre.startsWith("LITERAL")) return "#c0392b";   // Rojo Oscuro
        if (nombre.startsWith("IDENTIFICADOR")) return "#34495e"; // Gris oscuro
        return "#7f8c8d"; // Gris 
    }
    
    

    
    private void guardarArchivo(String ruta, String contenido) {
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write(contenido);
            System.out.println(VERDE + "[ÉXITO] Se generó el archivo: " + ruta + RESET);
        } catch (IOException e) {
            System.out.println(ROJO + "[ERROR] No se pudo generar el archivo HTML: " + e.getMessage() + RESET);
        }
    }

    
    private String escaparHTML(String texto) {
        if (texto == null) return "";
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
    
    
    private String obtenerMarcaDeTiempo() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return ahora.format(formato);
    }
}