build:
		rm -r ./EXE
		mkdir EXE
		javac -cp ".:./LIBS/json-simple-1.1.1.jar:./LIBS/hamcrest-core-1.3.jar:./LIBS/junit-4.12.jar:" ./FONTS/Main.java ./FONTS/tests/*.java ./FONTS/domain/*.java ./FONTS/domaincontrollers/*.java ./FONTS/presentation/*.java ./FONTS/data/*.java ./FONTS/tests/driversAutomaticos/*.java -d EXE

asignacion:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverAsignacion

asignatura:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverAsignatura

aula:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverAula

clase:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverClase

correquisito:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverCorrequisito

dia:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverDia

dialibre:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverDiaLibre

franjaasignatura:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverFranjaAsignatura

franjanivel:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverFranjaNivel

franjatrabajo:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverFranjaTrabajo

grupo:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverGrupo

hora:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverHora

horario:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverHorario

nivel:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverNivel

nivelhora:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverNivelHora

planestudios:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverPlanEstudios

prerrequisito:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverPrerequisito

sesion:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverSesion

subgrupo:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driverSubGrupo

asignacionauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverAsignacionAuto

asignaturaauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverAsignaturaAuto

aulaauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverAulaAuto

claseauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverClaseAuto

correquisitoauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverCorrequisitoAuto

diaauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverDiaAuto

dialibreauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverDiaLibreAuto

franjaasignaturaauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverFranjaAsignaturaAuto

franjanivelauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverFranjaNivelAuto

franjatrabajoauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverFranjaTrabajoAuto

grupoauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverGrupoAuto

horaauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverHoraAuto

horarioauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverHorarioAuto

nivelauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverNivelAuto

nivelhoraauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverNivelHoraAuto

planestudiosauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverPlanEstudiosAuto

prerrequisitoauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverPrerequisitoAuto

sesionauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverSesionAuto

subgrupoauto:
		java -cp ./EXE:./LIBS/json-simple-1.1.1.jar tests.driversAutomaticos.driverSubGrupoAuto

run:
		java -cp ".:./EXE:./LIBS/json-simple-1.1.1.jar:" Main

clean:
		rm -r ./EXE


