rung1000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 1000 -ln 25

rung2000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 2000 -ln 25

rung100:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 200 -ln 25

runip:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains -shape circle -lake 3 -aquifer 1 -river 5 -seed 938610703 -soil fertile
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg -mode haltitude
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg -mode hmoisture

runiv:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape circle -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg -mode haltitude
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg -mode hmoisture

runic:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude crater -shape circle -lake 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg -mode haltitude
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg -mode hmoisture

runcountry:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape country -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg -mode haltitude
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg -mode hmoisture

runivs:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape star -seed 593 -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/elevation.svg -mode haltitude
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/moisture.svg -mode hmoisture

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
