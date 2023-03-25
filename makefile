rung1000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 1000 -ln 25

rung2000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 2000 -ln 25

rung100:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 100 -ln 25

runip:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains -shape circle -lake 3 -aquifer 1 -river 5 -seed 93861 -soil fertile -mode moisture
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains -shape circle -lake 3 -aquifer 1 -river 5 -seed 93861 -soil fertile -mode normal
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains -shape circle -lake 3 -aquifer 1 -river 5 -seed 93861 -soil fertile -mode height
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg

runiv:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape oval -lake 3 -aquifer 3 -river 6 -seed 938610703 -mode normal
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape oval -lake 3 -aquifer 3 -river 6 -seed 938610703 -mode moisture
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape oval -lake 3 -aquifer 3 -river 6 -seed 938610703 -mode height
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg

runivmoisture:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape star -lake 3 -aquifer 3 -river 6 -mode height -seed -519454777
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg

runivmoisturen:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape circle -lake 3 -aquifer 3 -river 6 -mode height
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg

runic:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude crater -shape circle -lake 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg

runcountry:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape country -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg

runivs:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape star -seed 593 -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg

runfullplains:
	make rung1000
	make runip

runfullvolcano:
	make rung1000
	make runiv


runfullcrater:
	make rung1000
	make runic

runfullcountry:
	make rung1000
	make runcountry

runtest:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains
