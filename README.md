# API REST ADMINISTRACIÓN HOSPITALES
### Herramientas
+ Java 8
+ Maven 3.x
+ Base Datos MySql
+ Spring Boot Web
+ Spring Security
+ JPA

### Configuración del Properties

+ Colocar el password de su configuración de usuario MySql en el archivo *application.properties*

    ```properties
        spring.datasource.url = jdbc:mysql://localhost:3306/db_hospitales
        spring.datasource.username = root
        spring.datasource.password =
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
        spring.jpa.generate-ddl=true
        logging.level.org.hibernate.SQL = debug

        # App Properties
        com.dh.apiadmhospitales.jwtSecret=jwtSecretKey
        com.dh.apiadmhospitales.jwtExpiration=86400
    ```
+ Se debe crear una base datos con nombre de *db_hospitales* con configuración *utf8_general_ci*
+ Mapiar las tablas con los comandos:
1. Descargar
    ```shell
    mvn clean compile package
    ```
2. Ejecutar Api
    ```shell
    java -jar target/apiadmhospitales-0.0.1-SNAPSHOT.jar
    ```
3. Todo en uno
    ```shell
     mvn spring-boot:run
    ```
4. Ir a la url
[http://localhost:8080/](http://localhost:8080/)

+ Para concluir se ejecutá el script *bd_hospitales_insert* para tener datos ejemplo

### Estructura REST API
Crear la estructura basica del proyecto api:
  ```text
    src/main/java
    +- com.dh.apiadmhospitales
      +- controller
      |  +-AplicationController.java
      +- models
      |  +-AplicationDto.java  
      |  +-AplicationModel.java
      |  +-AplicationRespository.java
      +- service
      |  +-AplicationService.java
      +- security
      |  +-AplicationSecurity.java
      +- message
      |  +-response.java
    +- resources
  ```
DAO = Data Access Object
DTO = Data Transfer Object
