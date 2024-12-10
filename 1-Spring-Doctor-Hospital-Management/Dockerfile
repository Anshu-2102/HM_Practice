FROM openjdk:8u131-jdk-alpine
MAINTAINER "Anshu"
EXPOSE 7000
COPY ./target/DoctorService.jar   DoctorService.jar 
CMD ["java","-jar","DoctorService.jar"]

#CMD ["java","-Dspring.profiles.active="development", "-jar","product.war"]
#WORKDIR  /usr/local/bin/