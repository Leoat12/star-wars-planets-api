# Star Wars Planets API

API para armazenamento de informações sobre os planetas da série Star Wars.
Utiliza informações da [API do Star Wars](https://swapi.co) para verificar a existência do planeta.

## Endpoints

+ **GET** /planets - Lista os planets armazenados. 
+ **GET** /planets?name=planetName - Retorna o planeta com o nome fornecido. 
+ **GET** /planets/{id} - Retorna o planeta com o ID fornecido.
+ **POST** /planets - Salva um planeta.
+ **DELETE** /planets/{id} - Deleta um planeta com o ID fornecido.
