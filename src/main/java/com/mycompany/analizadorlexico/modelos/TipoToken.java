package com.mycompany.analizadorlexico.modelos;

public enum TipoToken {
    //Directivas
    DIRECTIVA_MODELO, 
    DIRECTIVA_ROL, 
    DIRECTIVA_FORMATO,
    
    //Palabras Reservadas
    RESERVADA_AGENTE, 
    RESERVADA_CONTEXTO, 
    RESERVADA_VARIABLE, 
    RESERVADA_EJECUTAR, 
    RESERVADA_EXPORTAR,
    
    //Comandos de IA 
    COMANDO_PREGUNTAR, 
    COMANDO_GENERAR, 
    COMANDO_RESUMIR, 
    COMANDO_ANALIZAR, 
    COMANDO_TRADUCIR, 
    COMANDO_CLASIFICAR, 
    COMANDO_EXTRAER,
    
    //Funciones
    FUNCION_CARGAR,
    
    //Conectores
    CONECTOR_SOBRE, 
    CONECTOR_DESDE, 
    CONECTOR_EN, 
    CONECTOR_COMO, 
    CONECTOR_FLECHA,
    
    //Identificadores y Literales
    IDENTIFICADOR,
    LITERAL_CADENA, 
    LITERAL_ENTERO, 
    LITERAL_DECIMAL,
    
    //Operadores
    OPERADOR_ASIGNACION, 
    OPERADOR_CONCATENACION,
    
    //Delimitadores
    LLAVE_ABRE, 
    LLAVE_CIERRA, 
    PARENTESIS_ABRE, 
    PARENTESIS_CIERRA, 
    COMA,

    EOF
}