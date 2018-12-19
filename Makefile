buildTest:
		mkdir EXE
		javac -cp ".:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:" ./FONTS/test/TestHorarioRunner.java ./FONTS/test/HorarioTest.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/Aux.java -d EXE

runTest:
		java -cp ".:./EXE:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:" TestHorarioRunner

build:
		mkdir EXE
		javac -cp ".:./EXE/test:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./FONTS/presentation/FXML:./FONTS/presentation/CSS:./LIBS/jfxrt.jar:" ./FONTS/Main.java ./FONTS/test/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/presentation/FXMLControllers/*.java ./FONTS/data/*.java

rebuild:
		rm -r ./EXE
		mkdir EXE
		javac -cp ".:./EXE/test:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./FONTS/presentation/FXML:./FONTS/presentation/CSS:./LIBS/jfxrt.jar:" ./FONTS/Main.java ./FONTS/test/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/presentation/FXMLControllers/*.java ./FONTS/data/*.java -d EXE

run:
		java -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./FONTS/presentation/FXML:./FONTS/presentation/CSS:./LIBS/jfxrt.jar:" Main

clean:
		rm -r ./EXE


