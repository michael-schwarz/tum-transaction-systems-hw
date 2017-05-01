cp=out:lib/jgrapht-core-1.0.1.jar


run:
	mkdir -p out
	javac -classpath $(cp) -d out src/*.java
	java -classpath $(cp) Main
