run:
	cd generator && java -jar generator.jar -mf temp.mesh -mv irregular -num 1000
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg

runip:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude plains
	cd visualizer && java -jar visualizer.jar -mf ../island/island.mesh -of island.svg

runiv:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude volcanic -shape star
	cd visualizer && java -jar visualizer.jar -mf ../island/island.mesh -of island.svg

runic:
	cd island && java -jar island.jar -i new.mesh -o island.mesh -altitude crater
runi:
	cd island && java -jar island.jar -mf temp.mesh -of island.mesh -shape star
	cd visualizer && java -jar visualizer.jar -mf ../island/island.mesh -of island.svg