run:
	cd generator && java -jar generator.jar -mf temp.mesh -mv irregular -num 1000
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg

runip:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude plains
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg

runiv:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude volcanic -shape star
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode heatmap_altitude

runic:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude crater
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg -mode heatmap_altitude