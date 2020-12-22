FROM ubuntu:latest

RUN apt update

ARG DEBIAN_FRONTEND=noninteractive

RUN apt install -y postgresql wget maven git vim sudo apache2
RUN wget https://download.java.net/java/GA/jdk15.0.1/51f4f36ad4ef43e39d0dfdbaf6549e32/9/GPL/openjdk-15.0.1_linux-x64_bin.tar.gz -O java_installer.tar.gz
RUN mkdir -p /usr/java/openjdk && mv ./java_installer.tar.gz /usr/java/openjdk/java_installer.tar.gz && cd /usr/java/openjdk && ls
RUN tar -xzvf /usr/java/openjdk/java_installer.tar.gz
RUN export JAVA_HOME=$(pwd)/jdk-15
RUN export PATH=$PATH:$JAVA_HOME/bin
RUN git clone https://github.com/JoniMilczarek/CustomerApplication
RUN mv CustomerApplication app 
RUN mkdir -p /var/www/html/
COPY resources/index.html /var/www/html
COPY resources/index.css /var/www/html
COPY resources/functions.js /var/www/html
COPY start-app.sh .
COPY run.txt .

EXPOSE 8080
EXPOSE 80

CMD ./start-app.sh && tail -f run.txt
