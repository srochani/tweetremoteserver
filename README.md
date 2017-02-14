# tweetremoteserver

It's Microservice application using spring-boot and Java8, which can be deployable independantly
 
### Maven compile

Following command to compile the application
```
mvn clean install
```

Following command to package the application, which will generate executable jar file
```
mvn package
```


### Run the Application

The simplest way to start the application is:

```
java -jar /target/tweetremoteserver-1.0-SNAPSHOT.jar
```

End points 
http://localhost:8765/tweetremoteserver/postmessage
http://localhost:8765/tweetremoteserver/getmessages
http://localhost:8765/tweetremoteserver/following
http://localhost:8765/tweetremoteserver/timeline


