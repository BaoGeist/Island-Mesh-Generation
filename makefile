run:
	cd generator && java -jar generator.jar -mf temp.mesh -mv irregular -num 2000
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg

runip:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude plains -shape circle
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode heatmap_altitude

runiv:
	cd island && java -jar island.jar -i temp.mesh -o island.mesh -altitude volcanic -shape star
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode graphic

runic:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude crater -shape circle
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode heatmap_altitude