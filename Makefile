#Compila el programa.
build:
		mkdir EXE
		javac -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./LIBS/jfxrt.jar:" ./FONTS/Main.java ./FONTS/Main1a.java ./FONTS/test/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/presentation/FXMLControllers/*.java ./FONTS/data/*.java -d EXE
		cp -r ./FONTS/presentation/CSS ./EXE/presentation/CSS
		cp -r ./FONTS/presentation/FXML ./EXE/presentation/FXML

#Recompila el programa.
rebuild:
		rm -r ./EXE
		mkdir EXE
		javac -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:./LIBS/jfxrt.jar:" ./FONTS/Main.java ./FONTS/Main1a.java ./FONTS/test/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/presentation/FXMLControllers/*.java ./FONTS/data/*.java -d EXE
		cp -r ./FONTS/presentation/CSS ./EXE/presentation/CSS
		cp -r ./FONTS/presentation/FXML ./EXE/presentation/FXML

#Ejecuta el programa.
run:
		java -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:./LIBS/jfxrt.jar:" Main

#Ejecuta el programa en modo 1a entrega.
run1a:
		java -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:" Main1a

#Ejecuta test JUnit de Horario.
runTest:
		java -cp ".:./EXE:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:" test.TestHorarioRunner

#Elimina el programa compilado.
clean:
		rm -r ./EXE


