run:
	cd generator && java -jar generator.jar -mf new.mesh -mv irregular -num 200
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg

runip:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude plains -shape oval
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode graphic

runiv:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude volcanic -shape star
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode graphic
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island1.svg -mode heatmap_altitude

runic:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude crater

runi:
	cd island && java -jar island.jar -mf new.mesh -of island.mesh -shape star
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode heatmap_altitude
