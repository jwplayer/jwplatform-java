JARS = libs/json-simple-1.1.1.jar:libs/httpcore-4.4.12.jar:libs/commons-codec-1.13.jar:libs/httpclient-4.5.10.jar:libs/commons-logging-1.2.jar
all : 
	javac -d bin -sourcepath src -cp $(JARS) src/*.java

run :
	java -cp bin:$(JARS) jwjava.JWJava

zip :
	zip JWJava.zip LICENSE README.md Makefile libs/* src/*

