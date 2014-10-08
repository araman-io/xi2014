FROM java8:latest

#Running Scripts file
ADD target/listing-1.0-SNAPSHOT-jar-with-dependencies.jar  /tmp/

ADD run.sh /tmp/run.sh
RUN chmod +x /tmp/*.sh

WORKDIR /tmp/

EXPOSE 4568
CMD ["/tmp/run.sh"]
