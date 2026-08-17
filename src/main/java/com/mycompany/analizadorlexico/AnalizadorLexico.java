package com.mycompany.analizadorlexico;

import com.mycompany.analizadorlexico.modelos.Token;
import com.mycompany.analizadorlexico.modelos.ErrorLexico;
import com.mycompany.analizadorlexico.modelos.TipoToken;
import com.mycompany.analizadorlexico.reportes.GeneradorReportes;
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
            if (esEspacioOSalto(c)){
                manejarEspacios(c);
            } else if (esDelimitador(c)){
                manejarDelimitador(c);
            } else if (esOperadorOConector(c)){
                manejarOperadores(c);
            } else if (esLetraOGuionBajo(c)){
                manejarPalabra();
            } else if (esComilla(c)){
                manejarCadena();
            } else if (esDirectiva(c)){   
                manejarDirectiva();
            } else if (esNumero(c)){     
                manejarNumero();    
            } else if (esInicioComentario(c)){ 
                manejarComentario();
            } else {
                manejarError(c);
            }
        }
        //Final del while se imprime la tabla en consola y se crean los reportes HTML
        imprimirResultados();
        GeneradorReportes reportador = new GeneradorReportes();
        reportador.generarReporteTokens(this.listaTokens);
        reportador.generarReporteErrores(this.listaErrores);
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
    
    private boolean esLetraOGuionBajo(char c) {
        return Character.isLetter(c) || c == '_';
    }
    
    private boolean esComilla(char c) {
        return c == '"';
    }
    
    private boolean esDirectiva(char c) {
        return c == '@';
    }

    private boolean esNumero(char c) {
        return Character.isDigit(c);
    }
    
    private boolean esInicioComentario(char c) {
        return c == '/';
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
                columna = columna + 2; 
                i = i + 2;       
            } else {
                listaErrores.add(new ErrorLexico("-", "Guion suelto no reconocido (se esperaba '->')", fila, columna));
                columna++; i++;
            }
        }
    }


    private void manejarPalabra() {
        //Armar la palabra letra por letra, termina cuando la siguiente letra no cumple con las condiciones
        String lexema = "";
        int colInicial = columna; 
        while (i < caracteres.length && (Character.isLetter(caracteres[i]) || Character.isDigit(caracteres[i]) || caracteres[i] == '_')) {
            lexema = lexema + caracteres[i];
            columna++;
            i++;
        }

        //Con la palabra armada se verifica que tipo de token es
        TipoToken tipo = clasificarPalabra(lexema);
        
        listaTokens.add(new Token(contadorTokens++, lexema, tipo, fila, colInicial));
    }
    
    
    private TipoToken clasificarPalabra(String lexema) {
        switch (lexema) {
            // Palabras reservadas
            case "AGENTE": return TipoToken.RESERVADA_AGENTE;
            case "contexto": return TipoToken.RESERVADA_CONTEXTO;
            case "variable": return TipoToken.RESERVADA_VARIABLE;
            case "EJECUTAR": return TipoToken.RESERVADA_EJECUTAR;
            case "EXPORTAR": return TipoToken.RESERVADA_EXPORTAR;
            
            // Comandos de IA
            case "PREGUNTAR": return TipoToken.COMANDO_PREGUNTAR;
            case "GENERAR": return TipoToken.COMANDO_GENERAR;
            case "RESUMIR": return TipoToken.COMANDO_RESUMIR;
            case "ANALIZAR": return TipoToken.COMANDO_ANALIZAR;
            case "TRADUCIR": return TipoToken.COMANDO_TRADUCIR;
            case "CLASIFICAR": return TipoToken.COMANDO_CLASIFICAR;
            case "EXTRAER": return TipoToken.COMANDO_EXTRAER;
            
            // Funciones
            case "CARGAR": return TipoToken.FUNCION_CARGAR;
            
            // Conectores
            case "SOBRE": return TipoToken.CONECTOR_SOBRE;
            case "DESDE": return TipoToken.CONECTOR_DESDE;
            case "EN": return TipoToken.CONECTOR_EN;
            case "COMO": return TipoToken.CONECTOR_COMO;
            
            default: return TipoToken.IDENTIFICADOR;
        }
    }
    
    private void manejarCadena() {
        String lexema = "\""; 
        int colInicial = columna;       
        columna++;
        i++; 

        //Se arma el lexema caracter por caracter hasta encontrar la siguiente comilla
        while (i < caracteres.length && caracteres[i] != '"') {
            lexema = lexema + caracteres[i];          
            if (caracteres[i] == '\n') {
                fila++;
                columna = 1;
            } else {
                columna++;
            }
            i++;
        }

        //Si el caracter final fue una comilla 
        if (i < caracteres.length && caracteres[i] == '"') {
            lexema = lexema + '"';
            listaTokens.add(new Token(contadorTokens++, lexema, TipoToken.LITERAL_CADENA, fila, colInicial));
            columna++;
            i++;
        //Si no es un error    
        } else {
            listaErrores.add(new ErrorLexico(lexema, "Cadena de texto sin cerrar", fila, colInicial));
        }
    }
    
    
    private void manejarDirectiva() {
        String lexema = "@";
        int colInicial = columna;       
        columna++;
        i++;
        
        //Armar la palabra caracter por caracter si cumple con las condiciones 
        while (i < caracteres.length && (Character.isLetter(caracteres[i]) || Character.isDigit(caracteres[i]) || caracteres[i] == '_')) {
            lexema = lexema + caracteres[i];
            columna++;
            i++;
        }

        //Verificar si es uno de los 3 tipos validos
        TipoToken tipo;
        switch (lexema) {
            case "@modelo": tipo = TipoToken.DIRECTIVA_MODELO; 
            break;
            case "@rol": tipo = TipoToken.DIRECTIVA_ROL;
            break;
            case "@formato": tipo = TipoToken.DIRECTIVA_FORMATO;
            break;
            default:
                //Si no es ninguno se guarda como error
                listaErrores.add(new ErrorLexico(lexema, "Directiva no reconocida", fila, colInicial));
                return; 
        }

        listaTokens.add(new Token(contadorTokens++, lexema, tipo, fila, colInicial));
    }

    
    private void manejarNumero() {
        String lexema = "";
        int colInicial = columna;
        boolean tienePunto = false;
        //Arma el numero numero por numero si cumple las condiciones
        while (i < caracteres.length && (Character.isDigit(caracteres[i]) || caracteres[i] == '.')) {
            //Si es un punto verifica si ya habia uno antes
            if (caracteres[i] == '.') {
                //Si hay corta el numero
                if (tienePunto) {
                    break; 
                }
                //Si no ponemos la bandera
                tienePunto = true;
            }
            lexema = lexema + caracteres[i];
            columna++;
            i++;
        }

        TipoToken tipo = tienePunto ? TipoToken.LITERAL_DECIMAL : TipoToken.LITERAL_ENTERO;
        
        listaTokens.add(new Token(contadorTokens++, lexema, tipo, fila, colInicial));
    }
    
    
    private void manejarComentario() {
        //Verificar que no sea el ultimo
        if (i + 1 < caracteres.length) {
            char siguiente = caracteres[i + 1];
            //si es otro / es un comentario 
            if (siguiente == '/') {
                i = i + 2;
                columna = columna + 2;
                //leer caracter por caracter hasta el salto de linea
                while (i < caracteres.length && caracteres[i] != '\n') {
                    i++;
                    columna++;
                }
                return; 
            } 
            
            //Si es * es un cometario de bloque
            else if (siguiente == '*') {
                int filaInicial = fila;
                int colInicial = columna;               
                i = i + 2;      
                columna += 2;
                boolean cerrado = false;

                while (i < caracteres.length) {
                    if (caracteres[i] == '\n') {
                        fila++;
                        columna = 1;
                        i++;
                    } 
                    //Si se encuentra el cierre se activa la bandera 
                    else if (caracteres[i] == '*' && i + 1 < caracteres.length && caracteres[i + 1] == '/') {
                        i = i + 2;
                        columna = columna + 2;
                        cerrado = true;
                        break;
                    } 
                    else {
                        columna++;
                        i++;
                    }
                }
                //Si no hay cierre es un error
                if (!cerrado) {
                    listaErrores.add(new ErrorLexico("/*", "Comentario de bloque sin cerrar", filaInicial, colInicial));
                }
                return;
            }
        }
        //Si no es ninguno de los 2 tipos de comentarios es un error
        listaErrores.add(new ErrorLexico("/", "Carácter no reconocido (se esperaba '//' o '/*')", fila, columna));
        columna++;
        i++;
    }
    

        //Metodo para los errores
    private void manejarError(char c) {
        listaErrores.add(new ErrorLexico(String.valueOf(c), "Caracter no reconocido", fila, columna));
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