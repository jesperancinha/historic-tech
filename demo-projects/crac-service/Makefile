include ../Makefile.mk

SETUP := export SDKMAN_DIR="$(HOME)/.sdkman"; \
         	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
			source "$(HOME)/.sdkman/bin/sdkman-init.sh" ;\
			sdk use java 21.0.1.crac-zulu

first-install:
	$(SETUP); \
	sdk install java 21.0.1.crac-zulu
jdk-setup:
	$(SETUP); \
	sudo chown root:root $(JAVA_HOME)/lib/criu ; \
	sudo chmod u+s $(JAVA_HOME)/lib/criu
b: build-gradle
build-gradle:
	$(SETUP); \
	./gradlew build
start-service: cleanup
	$(SETUP); \
	java -jar build/libs/crac-service-0.0.1-SNAPSHOT.jar
start-service-crac: cleanup
	$(SETUP); \
	java -version; \
	java -XX:CRaCCheckpointTo=./tmp_checkpoint -jar build/libs/crac-service-0.0.1-SNAPSHOT.jar
create-checkpoint:
	$(SETUP); \
	jcmd build/libs/crac-service-0.0.1-SNAPSHOT.jar JDK.checkpoint
start-checkpoint:
	$(SETUP); \
	java -XX:CRaCRestoreFrom=./tmp_checkpoint
	make cleanup
cleanup:
	rm -rf tmp_checkpoint
docker-build-start:
	docker-compose up --build
docker-start:
	docker-compose up -d
docker-start-service:
	docker-compose exec crac_service java -jar /opt/app/crac-service-0.0.1-SNAPSHOT.jar
docker-start-service-crac:
	docker-compose exec crac_service java -XX:CRaCCheckpointTo=./tmp_checkpoint -jar /opt/app/crac-service-0.0.1-SNAPSHOT.jar
docker-checkpoint-service-crac:
	docker-compose exec crac_service jcmd /opt/app/crac-service-0.0.1-SNAPSHOT.jar JDK.checkpoint
docker-resume-service-crac:
	docker-compose exec crac_service java -XX:CRaCRestoreFrom=./tmp_checkpoint