# 도담이
<img width="150" alt="스크린샷 2020-05-19 오후 4 37 45" src="https://user-images.githubusercontent.com/38368820/82300902-74447400-99f2-11ea-85c5-e240c415f85d.png">

## Introduction
### 아빠의 목소리로 사랑을 들려주는 태교 서비스
이 서비스는 아빠가 부재 시에도 언제든지 아빠의 목소리로 태담 태교를 할 수 있는 앱 서비스입니다. 아빠들이 태교의 중요성을 인식하고 있음에도 불구하고 직장 등 시간적인 제약으로 태교에 적극적으로 참여하지 못하는 문제를 해결하기 위해 딥러닝으로 아빠의 목소리를 학습하여 목소리 모델을 생성하고, 이를 태교 콘텐츠에 음성 합성하여 들려주는 서비스를 만들었습니다. 

개발자 세 명이 진행한 프로젝트로 다 같이 기획을 하였고, 개발은 iOS 앱과 백엔드, 딥러닝 개발로 역할을 나누었습니다. 저는 그중 백엔드 개발을 담당하여 사용자, 콘텐츠, 음성 모델 등을 위한 다양한 API를 개발하였습니다.

- 소속: 소프트웨어 마에스트로 10기 도담팀
- 기간: 2019.05.11~2019.11.23 (기획부터 최종 발표까지)

## Development Environment
- Language - Java 8
- Framework - Spring Boot 2
- IDE - IntelliJ
- Cloud - AWS EC2, RDS, S3
- RDBMS - PostgreSQL

## Libraries
- Spring MVC 
- Spring Data JPA - ORM
- Spring Security - 사용자 인증 처리
- Spring Cloud - AWS 사용
- PostgreSQL - RDBMS
- Lombok - 반복적인 코드 자동 생성
- JWT - Json Web Token 사용
- Swagger - API 문서 자동화

## Architecture
<img width="1387" alt="스크린샷 2020-05-19 오후 4 37 45" src="https://user-images.githubusercontent.com/38368820/82298649-62150680-99ef-11ea-95b5-2395a9f4bd00.png">

## ERD Diagram
<img alt="ERD Diagram" src="https://user-images.githubusercontent.com/38368820/77454335-b9f21f80-6e3b-11ea-9e50-33442d348c86.png">

## API
<img width="880" alt="스크린샷 2020-03-25 오전 1 57 02" src="https://user-images.githubusercontent.com/38368820/77454481-eefe7200-6e3b-11ea-8172-a2c5b04253a5.png">
