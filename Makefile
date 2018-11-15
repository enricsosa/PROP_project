build:
		mkdir EXE
		javac -cp "./LIBS/json-simple-1.1.1.jar" ./FONTS/Main.java ./FONTS/tests/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/data/*.java -d EXE

run:
		java -cp "./LIBS/json-simple-1.1.1.jar" ./EXE/Main.class ./EXE/domain/*.class ./EXE/domaincontrollers/*.class ./EXE/presentation/*.class ./EXE/data/*.class

clean:
		rm -r ./EXE