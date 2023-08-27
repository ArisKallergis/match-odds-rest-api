FROM maven:3.8.5-openjdk-17

WORKDIR /match-odds-app
COPY . .
RUN mvn -DskipTests clean install

CMD mvn spring-boot:run