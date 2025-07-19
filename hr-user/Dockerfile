FROM openjdk:21
ADD ./target/hr-user-0.0.1-SNAPSHOT.jar hr-user.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["/wait-for-it.sh", "config-server:8888", "--", "java", "-jar", "/hr-user.jar"]
