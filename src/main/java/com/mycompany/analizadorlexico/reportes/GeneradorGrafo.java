package com.mycompany.analizadorlexico.reportes;

import java.io.FileWriter;

public class GeneradorGrafo {

    public void generarGrafoGraphviz() {
        String codigoDOT = "digraph AFD {\n"
            + "  rankdir=LR;\n" 
            + "  size=\"15,10\";\n"
            + "  node [shape = doublecircle, style=filled, fillcolor=lightblue]; \n"
            + "  S1 S2 S4 S6 S8 S10 S13 S14 S16; // Estados de aceptación\n"
            + "  node [shape = circle, style=solid, fillcolor=white]; \n"
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
            + "  \n"
            + "  // 3. Directivas (@modelo, @rol)\n"
            + "  S0 -> S5 [label=\" @ \"];\n"
            + "  S5 -> S6 [label=\" letra \"];\n"
            + "  S6 -> S6 [label=\" letra | dígito | _ \"];\n"
            + "  \n"
            + "  // 4. Literales de Cadena\n"
            + "  S0 -> S7 [label=\" \\\" \"];\n"
            + "  S7 -> S7 [label=\" cualquier carácter excepto \\n y \\\" \"];\n"
            + "  S7 -> S8 [label=\" \\\" \"];\n"
            + "  \n"
            + "  // 5. Conector Flecha (->)\n"
            + "  S0 -> S15 [label=\" - \"];\n"
            + "  S15 -> S16 [label=\" > \"];\n"
            + "  \n"
            + "  // 6. Delimitadores y Operadores de un solo carácter\n"
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