# Projeto de uma API Rest para um supermercado - Desafio TQI

<p align="center">
    <a alt="Kotlin">
          <img src="https://img.shields.io/badge/Kotlin-v17-blue.svg" />
    </a>
  <a alt="JDK">
          <img src="https://img.shields.io/badge/JDK-v17-blue.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring_Boot-v2.7.6-red.svg" />
    </a>
    <a alt="Maven">
        <img src="https://img.shields.io/badge/Maven-v4.0.0-brightgreen.svg" />
    </a>
    <a alt="JPA">
        <img src="https://img.shields.io/badge/JPA-v3.1.0-brightgreen.svg" />
    </a>
     <a alt="Validation">
        <img src="https://img.shields.io/badge/Validation-v2.0.2-red.svg" />
    </a>
     <a alt="Flyway">
        <img src="https://img.shields.io/badge/Flyway-v7.15.0-blueviolet.svg" />
    </a>
    <a alt="MySQL">
        <img src="https://img.shields.io/badge/MySQL-v2.1.214-brightgreen.svg" />
    </a>
  <a alt="Mockito">
        <img src="https://img.shields.io/badge/Mockito-v4.0.0-red.svg" />
    </a>
</p>


## Definição do Problema proposto

Uma mercearia do Bairro Bom Descanso chamado JuMarket necessita de uma solução para venda de auto-atendimento, para tanto necessitamos desenvolver as seguintes funcionalidades.

**Cadastro de Categorias:** Um cadastro de categoria de produtos, apenas contendo o nome da categoria, por exemplo: Produtos de Limpeza, Bebidas, Bombonier, Salgadinhos, Chás e Cafés, Grãos e Cereais e outros.

**Cadastro de Produtos:** Um cadastro contendo os produtosdo mercado, este cadastro poderá conter os seguintes campos: Nome do Produto, Unidade de Medida, Preço Unitário

**Carrinho:** O carrinho é a funcionalidade na qual o usuário selecionou os produtos que deseja adquirir, neste caso conter os seguintes dados: produto, quantidade de intens e o preço da venda

**Finalização da Venda:** Ao finalizar a venda deverá ser informado o valor total e a forma de pagamento escolhida. As opções são: Cartão de crédito/débito, dinheiro e pix


## Modelo do banco de dados

<figure>
<p align="center">
  <img src="https://github.com/myllamachaado/tqi_Kotlin_backend_developer_2023/blob/master/files/bd.PNG"/><br>
</p>
</figure>
    
## - Executando o projeto localmente

1)  Faça um clone do repositório:
`git clone https://github.com/myllamachaado/projeto-algalog-api-entregas.git`
2) Compile o projeto:
`mvn clean install`
3) Ajuste o application.properties para comportaras configurações do seu banco de dados nos seguintes localis:
```
spring.datasource.url=jdbc:mysql://<endereço do seu servidor local>:3306/jumarket
spring.datasource.username=<seu usuário>
spring.datasource.password=<sua senha>

spring.flyway.url=jdbc:mysql://<endereço do seu servidor local>:3306/jumarket
spring.flyway.user=<seu usuario>
spring.flyway.password=<sua senha>
```
4)  Execute o projeto:
`mvn spring-boot::run` 


## - Executando o projeto via Docker

1)  Faça um clone do repositório:
`git clone https://github.com/myllamachaado/projeto-algalog-api-entregas.git`
2) Compile o projeto:
`mvn clean install`
3)  Execute o projeto:
`docker-compose up -d`


## - Postman collection

A coleção com todos os endpoints para a execução se encontra na pasta files com o nome `TQI - JuMarket.postman_collection.json`. Basta importar o arquivo json no postman para ter acesso a todos os endpoints. 

