FROM clojure:latest

RUN mkdir -p /var/jotes
WORKDIR /var/jotes

COPY project.clj /var/jotes
RUN lein deps

COPY . /var/jotes

RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" jotes-standalone.jar

ENTRYPOINT ["java", "-jar", "jotes-standalone.jar"]