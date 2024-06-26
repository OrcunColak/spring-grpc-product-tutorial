# Request Response

The original idea is from  
https://towardsdev.com/building-scalable-microservices-with-grpc-spring-boot-and-maven-fe49c377e450

# Server Streaming

The original idea is from  
https://www.javainuse.com/boot3/grpc3
# Client Streaming

The original idea is from  
https://www.javainuse.com/boot3/grpc4

# maven

Server and client have separate dependencies

For **gRPC Server** add

```xml

<dependency>
    <groupId>net.devh</groupId>
    <artifactId>grpc-server-spring-boot-starter</artifactId>
    <version>2.15.0.RELEASE</version>
</dependency>
```

For **gRPC Client** add

```xml

<dependency>
    <groupId>net.devh</groupId>
    <artifactId>grpc-client-spring-boot-starter</artifactId>
    <version>2.15.0.RELEASE</version>
</dependency>
```

But for server + client add

```xml

<dependency>
    <groupId>net.devh</groupId>
    <artifactId>grpc-spring-boot-starter</artifactId>
    <version>2.15.0.RELEASE</version>
</dependency>
```

See  
https://github.com/yidongnan/grpc-spring-boot-starter/tree/master?tab=readme-ov-file#grpc-server--client

# gRPC Error Handling Example

The original idea is from  
https://medium.com/@javainuse/spring-boot-3-grpc-error-handling-example-a63711fc45ae

# grpcurl

To list grpc services use this command
See https://piotrminkowski.com/2023/08/29/introduction-to-grpc-with-spring-boot/

```
grpcurl --plaintext localhost:9090 list
```

# Proto field examples

bool
string
float

uint32
uint64
