# Architecture
<img alt="Architecture" src="https://user-images.githubusercontent.com/38368820/69070402-6a55ae80-0a6b-11ea-825c-384e5d644b63.png">

# How To Start
```
$ git clone https://github.com/HyeranShin/Dodami_Backend.git
$ cd Dodami_Backend
$ cd src/main
$ mkdir resources
$ cd resources
$ vi application.yml
```
Enter your server information.
```yml
spring:
  h2:
    console:
      enabled: true
  jpa:
    properties:
      hibernate:
        temp:
          use_jdbc_metadata_defaults: false
  datasource:
    driver-class-name: org.postgresql.Driver
    url: RDS_URL
    username: RDS_USERNAME
    password: RDS_PASSWORD
    platform: POSTGRESQL
    jpa:
      hibernate:
        ddl-auto: create-drop
  servlet:
    multipart:
      max-request-size: -1
      max-file-size: -1

JWT:
  ISSUER: USERNAME
  SECRET: SECRETKEY

cloud:
  aws:
    credentials:
      accessKey: AWS_ACCESSKEY
      secretKey: AWS_SECRETKEY
    stack:
      auto: false
    s3:
      bucket: dodami-bucket
      bucket_url: AWS_BUCKET_URL
    region:
      auto: false
      static: AWS_REGION
```
```
$ sudo ./gradlew build
$ cd build/libs
$ java -jar dodami-0.0.1-SNAPSHOT.jar
```
