# Visão geral

O projeto é uma aplicação back-end com objetivo de simular integração de emails utilizando os frameworks [Spring Boot](https://projects.spring.io/spring-boot), [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html).

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone,  com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.

- [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html) é um framework já consolidado no mercado, que a partir da versão fornece mecanismos simplificados para a criação de APIs RESTful através de anotação, além disso, também possui recursos de serialização e deserialização de objetos de forma transparente


# Setup da aplicação (local)

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 17
Maven 4.0.0
```

## Instalação da aplicação

Primeiramente, faça o clone do repositório:
```
https://github.com/gkl3in/email-integration.git
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean package
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http)
```

# APIs

O projeto disponibiliza apenas uma API: Email onde utiliza o padrão Rest de comunicação, produzindo e consumindo arquivos no formato JSON.

Segue abaixo a API disponível no projeto:

#### Email

- /email (POST)
    - Espera atributos para serem critérios de busca no body da requisição, exemplo:
   ```
    {
        "recipient": "gabriel@hotmail.com",
        "recipientName": "Gabriel",
        "sender": "gabriel@hotmail.com",
        "subject": "Reunião",
        "content": "Teste de conteúdo"
    }
   ```