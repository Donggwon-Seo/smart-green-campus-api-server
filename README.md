## 💡Overview
### 스마트 그린 캠퍼스란?
- 한밭대학교에서 **다학제간 캡스톤 디자인**을 수강하며 진행하는 프로젝트입니다.
- 여러 학과가 모여 학교에 대한 여러 데이터들을 다루며 프로젝트를 구현하고 팀워크 능력을 배양합니다.

<br/>

###  각 학과의 역할
- 건축학과 및 화학생명공학과 : 일조량, 습도 등을 측정하고, InfluxDB에 저장합니다.
- 전자공학과 : 측정하는 과정에서 센서를 관리합니다.
- 정보통신공학과 
  - API server 팀 : 수신한 데이터를 DB에 저장하고, API를 제공합니다.
    - 현재 레포지토리 입니다.
  - Frontend server 팀 : API server로 필요한 데이터를 요청한 뒤, 해당 데이터를 가지고 웹 화면을 구성합니다.



<br/>

## 💡 Description
기존에 구현한 API server를 SpringBoot를 이용해 다시 구축합니다.
- [Node.js로 구현한 API server](https://github.com/2dongyeop/smart-green-campus)
- [Nest.js로 구현한 API server](https://github.com/2dongyeop/smart-green-campus-v2)

<br/>

### 프로젝트 구성
- Java 17
- SpringBoot 3.0.4
- PostgreSQL

<br/>

## 💡 Document
- API 설계도 
- Setting file

<br/>

## 💡 Setting File
##### src/main/resources에 아래의 설정 파일들을 설정해야 합니다.
- application-slack.yml
```
slack:
  webhook:
    token: // your-slack token
```

- application-jwt.yml
```
security:
  jwt:
    token:
      secret-key: // token secret key
      expire-length: //token expire time 
```

- application-influxdb.yml
```
influxdb:
  url: // InfluxDB url
  token: // InfluxDB token
  org: belab
  bucket: belab
  ```
