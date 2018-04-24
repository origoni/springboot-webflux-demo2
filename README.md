# springboot-webflux-demo2 [![Build Status](https://travis-ci.org/origoni/springboot-webflux-demo2.svg?branch=master)](https://travis-ci.org/origoni/springboot-webflux-demo2)

spring-boot-webflux reactive rest demo
using spring-boot-starter-data-mongodb-reactive
+ Annotation-based Programming Model
+ validate, @Valid

Tested
- STS(Eclipse) 3.8.4
- IntelliJ IDEA 2018.1.1

//@formatter:off & //@formatter:on
eclipse : Preferences -> Java -> Code style -> Formatter -> Edit... (or New...) > Off/On Tags
intellij : Preferences -> Editor -> Code Style > Formatter Control > Enable formatter markers in comments


## Quick Start

- JDK 1.8
- Maven 3.5
- Git

```
git clone https://github.com/origoni/springboot-webflux-demo2.git
cd springboot-webflux-demo2
mvn spring-boot:run
```


## Test

### create
```
curl -d '{"subject":"webflux-demo2","content":"spring-boot-webflux reactive rest demo","createdBy":"origoni"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts'
curl -d '{"subject":"webflux-demo2","content":"spring-boot-webflux reactive rest demo","createdBy":"origoni"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts'
```
### validate
```
curl -d '{"subject":"webflux-demo2","content":"spring-boot-webflux reactive rest demo","createdBy":"origoni_origoni_origoni"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts'
curl -d '{"subject":"webflux-demo2","content":"spring-boot-webflux reactive rest demo","createdBy":"origoni_origoni_origoni"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts'
```
### create with ID
```
curl -d '{"id":"testAnnotationId","subject":"webflux-demo2","content":"spring-boot-webflux reactive rest demo","createdBy":"origoni","createdAt":"2018-04-24T21:21:21.021"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts'
curl -d '{"id":"testFunctionId","subject":"webflux-demo2","content":"spring-boot-webflux reactive rest demo","createdBy":"origoni","createdAt":"2018-04-24T21:21:21.021"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts'
```

### update
```
curl -X PATCH -d '{"subject":"UPDATED webflux-demo2","content":"UPDATED spring-boot-webflux reactive rest demo"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts/testAnnotationId'
curl -X PATCH -d '{"subject":"UPDATED webflux-demo2","content":"UPDATED spring-boot-webflux reactive rest demo"}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts/testFunctionId'
```
### validate
```
curl -X PATCH -d '{"subject":"UPDATED webflux-demo2","content":""}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts/testAnnotationId'
curl -X PATCH -d '{"subject":"UPDATED webflux-demo2","content":""}' -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts/testFunctionId'
```

### list
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts'
curl -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts'
```

### read
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts/testAnnotationId'
curl -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts/testFunctionId'
```

### delete
```
curl -X DELETE -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/annotation/posts/testAnnotationId'
curl -X DELETE -H 'Content-Type: application/json' -v 'http://localhost:8082/api/v1/function/posts/testFunctionId'
```


## Dependency

### Spring Boot 2.0.1.RELEASE
- spring-boot-starter-webflux
- spring-boot-starter-data-mongodb-reactive
- de.flapdoodle.embed.de.flapdoodle.embed.mongo
- org.projectlombok.lombok
