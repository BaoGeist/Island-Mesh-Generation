rung1000:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 1000

rung2000:
	cd generator && java -jar generator.jar -mf t../img/new.mesh -mv irregular -num 2000

rung100:
	cd generator && java -jar generator.jar -mf ../img/new.mesh -mv irregular -num 200

runip:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude plains -shape circle -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg -mode heatmap_altitude

runiv:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape circle -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg -mode heatmap_altitude

runic:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude crater -shape circle -lake 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode heatmap_altitude

runcountry:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape country -lake 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic

runivs:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude crater -shape circle -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg -mode heatmap_altitude

runcountry:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape country -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg -mode heatmap_altitude

runivs:
	cd island && java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape star -seed 593 -lake 3 -aquifer 3 -river 3
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../img/island.mesh -o ../img/island1.svg -mode heatmap_altitude
