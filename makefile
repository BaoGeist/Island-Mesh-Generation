rung1000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 1000 -ln 25

rung2000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 2000 -ln 25

rung100:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 200 -ln 25

runip:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains -shape circle -lake 3 -aquifer 1 -river 5 -seed 938610703 -soil arid -mode moisture
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg

runiv:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape circle -lake 3 -aquifer 3 -river 6 -mode normal
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg

runivmoisture:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape circle -lake 3 -aquifer 3 -river 6 -seed 127540672 -mode height
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
