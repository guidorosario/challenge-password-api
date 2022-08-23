# Password-Api

API responsável por validar senhas 

![technology java](https://img.shields.io/badge/technology-Java-purple.svg)
![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)
![technology Webflux](https://img.shields.io/badge/tecnology-WebFlux-green)


- ### Pré-requisitos
    - [**Java 17**](https://www.oracle.com/java/technologies/downloads/#java17)
    - [**Gradle**](https://docs.gradle.org/current/userguide/userguide.html) | _or use the wrapper ./gradlew_
    - [**Spring Boot 2**](https://spring.io/projects/spring-boot)
    - [**Docker**](https://docs.docker.com/docker-for-mac/install/#download-docker-for-mac)

O Spring Webflux é uma tecnologia que possibilita desenvolver aplicações web com Spring do lado servidor trabalhar de 
forma reativa, sendo capaz de trabalhar com assincronia e melhorar o tempo de respostas das chamadas.

### Descrição
Pensando no melhor entendimento do código foi utilizado o Spring Webflux e o design pattern strategy para logica de validação de senha, onde existe uma camada de serviço
que realiza toda a lógica da aplicação, foi criado uma interface com validação e serviços implementando essa interface e uma validação não 
na outra, no final caso tenha algum erro de validação da senha é retornado que precisa ser mudado
para que todas as validações estejam corretas. 


Para a validação foi utilizado o Regex, pois podemos fazer várias validações sem precisa manipular a request

A chamada é realizado no endpoint `password/v1/validate`, pensando em versionamento foi adicionado v1 no endpoint, pois
futuramente pode ter uma v2 da validação de senha, assim também tendo um entendimento melhor na identificação do componente e qual versão
ele está atualmente.

Para a resposta do endpoint foi utilizado dois retornos.

- Se o usuário digite senha valida é retornado o status 200 e um booleano true
  `````
  {
     "checkedPassword": true
  }
  `````
- Se o usuário digite uma senha não valida é retornado um status 422 com uma lista especificando 
o que precisa se mudado para ser uma senha valida, assim o que for realizar a integração com a 
api sendo o front ou outra aplicação, não precisa realizar lógica para saber o que precisa mudar para
se uma senha válida.
   `````
  {
      "error": "Senha invalida",
      "error_description": "[Senha não deve conter espaços, Senha deve ao menos uma letra maiuscula]"
  }
   `````
  
## Gerando docker

````
./gradlew docker
````

## Rodando a aplicação:

A aplicação irá subir na porta 8080


### Instalando dependências

````
./gradlew clean build
````

### Rodando os testes

```
./gradlew clean test
```

### Via IDE

Em `Run/Debug Configuration`, crie uma configuração Gradle e define conforme as opções abaixo:

Gradle project

```
challenge-password-api
```

Task

```
bootRun
```

### Via terminal

Execute o comando na pasta raiz do projeto

````
./gradlew dockerRun
````


### Executando os testes

Execute o comando abaixo para executar os testes da aplicação.

```./gradlew clean test```

## Documentação no swagger

 ````
http://localhost:8080/swagger-ui.html
````
