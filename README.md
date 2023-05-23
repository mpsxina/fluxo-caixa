# Desafio Fluxo de Caixa

Desenvolvimento de uma simples API REST para controle de Fluxo de Caixa.

## Características

- CRUD
- API REST
- Validation
- Enum

## Requisitos

- Java JDK 17
- Apache Maven >= 3.8.6
- MySql 8
- Docker (Opcional)

## Tecnologias

- Java
- JPA
- Maven
- Spring
- Swagger
- Docker

## Instalação

### Docker

Para rodar o projeto via Docker-Compose, basta executar o comando:

```
$ docker-compose up
```

Aguarde baixar as dependências e carregar todo o projeto, esse processo é demorado. <br>
Caso conclua e não rode pela primeira vez, tente novamente executando o mesmo comando. <br>

Aguarde carregar todo o serviço web. <br>
Após concluído, digite o endereço abaixo em seu navegador. <br>

http://localhost:8000

Para encerrar tudo digite:

```
$ docker-compose down
```

OBS: É necessário ter o Docker e docker-compose instalado.
Caso não tenha instalado o Docker ou dê alguma falha na instalação, tente com os comandos abaixo.

### Maven

Para rodar o projeto com Maven, é necessário ter a versão 3.8.6 instalada.<br>
Além disso, é preciso ter o Java 11 e o MySql 8 instalado.<br>

Tendo tudo instalado e rodando localmente, basta executar o seguinte comando:

```
$ cd pdv-spring
$ mvn clean spring-boot:run
```

### MySql
Crie um banco de dados com o nome "fluxocaixa" no seu Mysql.<br>
Abra o arquivo .env e efetue a configuração correta para conexão do seu banco de dados.<br>

```
$ mysql -u<seu usuário> -p<sua senha>

mysql> create database `fluxocaixa`;
```

## Swagger 

Documentação da API REST: <br>

http://localhost:8000/swagger-ui.html

## Licença

Projeto licenciado sob <a href="LICENSE">The MIT License (MIT)</a>.<br><br>


Desenvolvido por<br>
Danilo Meneghel<br>
danilo.meneghel@gmail.com<br>
http://danilomeneghel.github.io/<br>
