# challenge-spring
Desafió propuesto para Proyecto MuleSoft.

## Introducción
Magneto quiere reclutar la mayor cantidad de mutantes para poder
luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si
un humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa JAVA con un método o función con la siguiente
firma:

                                 boolean isMutant(String[] dna);
 
En donde recibirás como parámetro un array de Strings que representan cada fila de una
tabla de (NxN) con la secuencia del ADN. Las letras de los **(A,T,C,G)** ,
las cuales representa cada base nitrogenada del ADN.

***Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro
letras iguales, de forma oblicua, horizontal o vertical.***

## Desafios 
1. Programa JAVA que cumpla con el método pedido por Magneto

2. Crear una API REST, hostear esa API en un cloud computing libre (Pivotal
CloudFoundry, Google App Engine, Amazon AWS, etc), crear el servicio “/mutant/” en donde
se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un
HTTP POST con un Json el cual tenga el siguiente formato:

     POST → /mutant/ {“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

     *En caso
de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden*

3. Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN. Exponer un servicio extra “/stats” que devuelva un Json con
las estadísticas de las verificaciones de ADN: {“count_mutant_dna”:40,
“count_human_dna”:100, “ratio”:0.4}

## Entrega 
- Código Fuente En repositorio github.
- Instrucciones de cómo ejecutar el programa o la API En README de github.
- URL de la API (Nivel 2 y 3).

## Tecnolologías
Decici realizar el proyecto con java 1.8, maven y spring boot, utilice JUnit y Mockito para los test, decidi encaminar la base por una 
relacional ya que mi experiencia laboral viene mas por el lado de nosql, asi que para probar algo nuevo, y uso la plataforma de Heroku.

## Funcionalidad

### Endpoint mutant
El cual se encarga a partir de un array de string (String[]) comprabar si el adn es mutante o humano

### Peticio HTTP
```
POST https://challenge-app-rm.herokuapp.com/mutant
Content-Type: application/json
{ "dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"] }
```

### Endpoint stats
El cual se encarga de devolver los datos del servidor y promediarlos

### Peticio HTTP
```
GET https://challenge-app-rm.herokuapp.com/stats
```
