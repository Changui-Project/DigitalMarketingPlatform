# Digital Marketing Platform

## Table of Contents

[1. Introduce](#introduce)  
[2. Project Architecture](#project-architecture)  
[3. Team](#team)  
[4. What I Did](#what-i-did)  
[5. Tech Stack](#tech-stack)  
[6. View](#view)  

## Introduce
이 플랫폼은 소상공인들이 전문 지식 없이도 온라인에서 제품을 홍보하고 판매할 수 있도록 돕습니다. 디지털 마케팅 자료 제작, 광고 관리, 판매 관리 등 다양한 기능을 제공해 소상공인들의 비즈니스 성장을 지원합니다.  
<sup>2023. 08. 04. ~ 2023. 08. 25.</sup>   

소상공인들이 디지털 마케팅에 대한 지식과 자원이 부족해 온라인에서 제품이나 서비스를 효과적으로 홍보하고 판매하는 데 어려움을 겪고 있습니다.   
이를 해결하기 위해 소상공인들이 쉽게 디지털 마케팅을 활용할 수 있는 플랫폼이 필요합니다.

>  [배포된 사이트]

## Project Architecture
<img width="1454" alt="image" src="https://github.com/changuii/DigitalMarketing-Server/assets/122252160/c829afd3-72d2-43d6-b2e6-5d6964ecb4ca">

## Team
| 이름   | 역할   | 기타   |
| ------ | ------ | ------ |
| **이창의** | 백엔드 | Spring, ApiGatewayServer, Redis, Kafka |
| 오민규 | 백엔드 | Spring, SalesPostServer|
| 이민재 | 백엔드 | Django, Review-PromotionalPostServer |
| 이종현 | 백엔드 | Spring, SalesPostServer |
| 한동근 | 백엔드 | Spring, ImageServer, SalesPostServer |
| 김수환 | 프론트 | NextJS |


### front-end Repository
- [링크](https://github.com/Suhwan-Front/Digital-marketing)



## What I Did
- 프로젝트의 구조를 MSA로 설계하여, Django와 Spring Boot 두 종류의 기술을 사용하여 구현하였다.
- Spring Boot에서 요청을 받아 각 기능 서버로 요청을 전달하기 위해 Kafka로 데이터를 전달하는 기능 구현
- Spring Boot에서 응답을 Redis로 받아서 클라이언트에게 응답을 전달하는 기능 구현
- 카카오 로그인 및 회원가입 기능 구현


## Tech Stack
- Spring Boot
- Spring Security
- Spring For Kafka
- Kafka
- Redis
- MySQL

`Spring server`
- version : 2.7.14
- java : 11
- Gradle - Groovy 
- Java

`Django server`
| Package   | Version |
| --------- | ------- |
| Python    | 3.11.4  |
| Django    | 4.2.3   |
| pip       | 23.1.2  |

`Kafka cluster`
- zookeeper 1
- kafka 1
- wurstmeister/zookeeper:latest
- wurstmeister/kafka:latest


`redis`
- redis server 1
- redis 공식 이미지를 사용하여 배포


`배포`
- 네이버 클라우드 서버(우분투)를 통하여 컨테이너로 배포
- kafka, zookeeper : docker-compose 사용
- redis, MySQL : 공식 이미지 사용 


## View
#### 메인, 로그인
![메인 로그인](/public/1.gif)  

#### 판매 등록  
![판매 등록](/public/2.gif)  

#### 등록 확인  
![등록 확인](/public/3.gif)  

#### 홍보 게시판 등록  
![홍보 게시판 등록](/public/4.gif)

