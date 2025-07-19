FROM openjdk:21
VOLUME /tmp
EXPOSE 8765
ADD ./target/hr-api-gateway-0.0.1-SNAPSHOT.jar hr-api-gateway.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["/wait-for-it.sh", "config-server:8888", "--", "java","-jar","/hr-api-gateway.jar"]