package com.mycompany.analizadorlexico.reportes;

import java.io.FileWriter;

public class GeneradorGrafo {

    public void generarGrafoGraphviz() {
        String codigoDOT = "digraph AFD {\n"
            + "  // Configuración de espaciado y dirección (De arriba hacia abajo)\n"
            + "  rankdir=TB;\n" 
            + "  nodesep=0.4;\n"
            + "  ranksep=0.7;\n"
            + "  \n"
            + "  // Nodos de aceptación (Doble círculo azul)\n"
            + "  node [shape = doublecircle, style=filled, fillcolor=lightblue]; \n"
            + "  S1 S2 S4 S6 S8 S10 S13 S14 S16;\n"
            + "  \n"
            + "  // Nodo de Error (Círculo rojo, texto blanco)\n"
            + "  node [shape = circle, style=filled, fillcolor=red, fontcolor=white, color=darkred]; \n"
            + "  ERROR;\n"
            + "  \n"
            + "  // Nodos normales de transición (Círculo blanco)\n"
            + "  node [shape = circle, style=solid, fillcolor=white, fontcolor=black]; \n"
            + "  \n"
            + "  // 0. Ciclo de espacios en blanco\n"
            + "  S0 -> S0 [label=\" espacios (\\n, \\t, \\r) \"];\n"
            + "  \n"
            + "  // 1. Identificadores, Reservadas y Comandos\n"
            + "  S0 -> S1 [label=\" letra | _ \"];\n"
            + "  S1 -> S1 [label=\" letra | dígito | _ \"];\n"
            + "  \n"
            + "  // 2. Números (Enteros y Decimales)\n"
            + "  S0 -> S2 [label=\" dígito \"];\n"
            + "  S2 -> S2 [label=\" dígito \"];\n"
            + "  S2 -> S3 [label=\" . \"];\n"
            + "  S3 -> S4 [label=\" dígito \"];\n"
            + "  S4 -> S4 [label=\" dígito \"];\n"
            + "  S3 -> ERROR [label=\" distinto de dígito \"];\n"
            + "  \n"
            + "  // 3. Directivas (@modelo, @rol, @formato)\n"
            + "  S0 -> S5 [label=\" @ \"];\n"
            + "  S5 -> S6 [label=\" letra | dígito | _ \"];\n"
            + "  S6 -> S6 [label=\" letra | dígito | _ \"];\n"
            + "  S5 -> ERROR [label=\" distinto de alfanumérico (ej. otro @) \"];\n" 
            + "  S6 -> ERROR [label=\" no coincide con @modelo, @rol o @formato \"];\n" 
            + "  \n"
            + "  // 4. Literales de Cadena\n"
            + "  S0 -> S7 [label=\" \\\" \"];\n"
            + "  S7 -> S7 [label=\" cualquier carácter excepto \\n y \\\" \"];\n"
            + "  S7 -> S8 [label=\" \\\" \"];\n"
            + "  S7 -> ERROR [label=\" \\n (Modo Pánico) \"];\n" 
            + "  \n"
            + "  // 5. Conector Flecha (->)\n"
            + "  S0 -> S15 [label=\" - \"];\n"
            + "  S15 -> S16 [label=\" > \"];\n"
            + "  S15 -> ERROR [label=\" distinto de > \"];\n" 
            + "  \n"
            + "  // 6. Delimitadores y Operadores\n"
            + "  S0 -> S14 [label=\" { | } | ( | ) | , | = | + \"];\n"
            + "  \n"
            + "  // 7. Comentarios de Línea y Bloque\n"
            + "  S0 -> S9 [label=\" / \"];\n"
            + "  S9 -> S10 [label=\" / (Línea) \"];\n"
            + "  S9 -> S11 [label=\" * (Bloque) \"];\n"
            + "  S11 -> S11 [label=\" distinto de * \"];\n"
            + "  S11 -> S12 [label=\" * \"];\n"
            + "  S12 -> S11 [label=\" distinto de / \"];\n"
            + "  S12 -> S13 [label=\" / \"];\n"
            + "  S9 -> ERROR [label=\" distinto de / o * \"];\n" 
            + "  S11 -> ERROR [label=\" Fin de archivo (Bloque sin cerrar) \"];\n" 
            + "  \n"
            + "  // 8. Cualquier otro símbolo inválido al inicio (ej. $, &, ?)\n"
            + "  S0 -> ERROR [label=\" carácter no reconocido \"];\n"
            + "}";

        try {
            FileWriter escritor = new FileWriter("automata.dot");
            escritor.write(codigoDOT);
            escritor.close();

            ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng", "-o", "automata.png", "automata.dot");
            proceso.start().waitFor();
            
            System.out.println("Imagen del AFD generada con éxito.");
        } catch (Exception ex) {
            System.out.println("Error al generar el grafo: " + ex.getMessage());
        }
    }
}