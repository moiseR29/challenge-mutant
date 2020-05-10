# challenge-Mutant

## Aclaración
Encontre este challenge y decidi hacerlo, no tiene endpoint en nube, pero estan las instrucciones para ejutarlo usando Docker :D

- [Introduccion](#introduccion)
- [Desafio](#desafios)
- [Forma de Uso](#forma-de-uso)

## Introducción
Magneto quiere reclutar la mayor cantidad de mutantes para poder
luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si
un humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa NodeJS / Python  / JAVA con un método o función con la siguiente
firma:

                                 boolean isMutant(String[] dna);

En donde recibirás como parámetro un array de Strings que representan cada fila de una
tabla de (NxN) con la secuencia del ADN. Las letras de los **(A,T,C,G)** ,
las cuales representa cada base nitrogenada del ADN.

***Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro
letras iguales, de forma oblicua, horizontal o vertical.***

## Desafios
1. Programa el codigo que cumpla con el método pedido por Magneto

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

-----

## Forma de Uso

## Tecnolologías
Se uso NodeJs, Mongo y Docker para realizar el codigo.

## Requisitos
- Si no tenes mongodb instalado
  - instalar docker
  - en consola en la raiz del proyecto ```docker-compose up -d```

### Endpoint mutant
El cual se encarga a partir de un array de string (String[]) comprabar si el adn es mutante o humano

### Peticion HTTP
```
POST localhost:3000/mutant
Content-Type: application/json
{ "dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"] }
```

### Endpoint stats
El cual se encarga de devolver los datos del servidor y promediarlos

### Peticio HTTP
```
GET localhost:3000/stats
```
