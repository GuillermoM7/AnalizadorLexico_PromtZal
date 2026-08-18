package com.mycompany.analizadorlexico.reportes;

import com.mycompany.analizadorlexico.modelos.Token;
import com.mycompany.analizadorlexico.modelos.ErrorLexico;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorReportes {
    public static final String RESET = "\033[0m";
    public static final String VERDE = "\033[32m";
    public static final String ROJO = "\033[31m";

    public void generarReporteTokens(List<Token> listaTokens) {
        String nombreArchivo = "Reporte_Tokens_" + obtenerMarcaDeTiempo() + ".html";
        StringBuilder html = new StringBuilder();

        
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Reporte de Tokens</title>\n");
        html.append("<style>\n");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("table { border-collapse: collapse; width: 100%; }\n");
        html.append("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }\n");
        html.append("th { background-color: #007BFF; color: black; }\n");
        html.append("</style>\n</head>\n<body>\n");

        html.append("<h2>Reporte de Tokens</h2>\n");
        html.append("<table>\n");
        html.append("<tr><th>#</th><th>Lexema</th><th>Tipo de Token</th><th>Fila</th><th>Columna</th></tr>\n");

        for (Token t : listaTokens) {
            html.append("<tr>")
                .append("<td>").append(t.getId()).append("</td>")
                .append("<td>").append(escaparHTML(t.getLexema())).append("</td>")
                .append("<td>").append(t.getTipo()).append("</td>")
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

        
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Reporte de Errores Lexicos</title>\n");
        html.append("<style>\n");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("table { border-collapse: collapse; width: 100%; }\n");
        html.append("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }\n");
        html.append("th { background-color: #f44336; color: black; }\n"); // Rojo para errores
        html.append("</style>\n</head>\n<body>\n");

        html.append("<h2>Reporte de Errores Lexicos</h2>\n");

        if (listaErrores.isEmpty()) {
            html.append("<h3>No se encontraron errores léxicos en el archivo analizado. ¡Análisis exitoso!</h3>\n");
        } else {
            html.append("<table>\n");
            html.append("<tr><th>Lexema/Carácter</th><th>Descripción</th><th>Fila</th><th>Columna</th></tr>\n");

            for (ErrorLexico e : listaErrores) {
                html.append("<tr>")
                    .append("<td>").append(escaparHTML(e.getCaracter())).append("</td>")
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
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;");
    }
    
    
    private String obtenerMarcaDeTiempo() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return ahora.format(formato);
    }
}