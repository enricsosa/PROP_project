build:
		mkdir EXE
		javac -cp "./LIBS/json-simple-1.1.1.jar" ./FONTS/Main.java ./FONTS/tests/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/data/*.java -d EXE

run:
		java -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:" Main

clean:
		rm -r ./EXE