FROM ubuntu:14.04

MAINTAINER Chernova Ekaterina

ENV DEBIAN_FRONTEND noninteractive
RUN echo "debconf shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections
RUN echo "debconf shared/accepted-oracle-license-v1-1 seen true" | debconf-set-selections

RUN apt-get update \
	&& apt-get -y install software-properties-common python-software-properties \
	bzip2 unzip openssh-client git \
	lib32stdc++6 lib32z1 \
	expect build-essential

# Add oracle-jdk8 to repositories
RUN add-apt-repository ppa:webupd8team/java -y

# Update apt
RUN apt-get update

# Install oracle-jdk8
RUN apt-get install -y oracle-java8-installer \
	git

# Install Gradle
RUN wget https://services.gradle.org/distributions/gradle-3.1-bin.zip
RUN unzip gradle-3.1-bin.zip
RUN mv gradle-3.1 /opt/
RUN rm gradle-3.1-bin.zip

# Environment variables
ENV GRADLE_HOME /opt/gradle-3.1
ENV PATH $PATH:$GRADLE_HOME/bin

# Export JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

RUN cd ~/
RUN git clone https://github.com/pyataevaev/auth-service.git auth

WORKDIR "auth"
RUN gradle build
#ADD build/libs/test-1.0.jar app.jar
EXPOSE 8080
#RUN bash -c 'touch /app.jar'

CMD ["java", "-jar", "build/libs/auth-service-1.0.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/app.jar"]

#RUN java -jar build/libs/test-1.0.jar
