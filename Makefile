build:
		javac -cp ".:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./FONTS/presentation/FXML:./FONTS/presentation/CSS:./LIBS/jfxrt.jar:" ./FONTS/Main.java ./FONTS/test/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/presentation/FXMLControllers/*.java ./FONTS/data/*.java -d EXE

rebuild:
		rm -r ./EXE
		mkdir EXE
		javac -cp ".:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./FONTS/presentation/FXML:./FONTS/presentation/CSS:./LIBS/jfxrt.jar:" ./FONTS/Main.java ./FONTS/test/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/presentation/FXMLControllers/*.java ./FONTS/data/*.java -d EXE

run:
		java -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./FONTS/presentation/FXML:./FONTS/presentation/CSS:./LIBS/jfxrt.jar:" Main

clean:
		rm -r ./EXE


