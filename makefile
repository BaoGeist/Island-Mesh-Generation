run:
	cd generator && java -jar generator.jar -mf temp.mesh -mv irregular
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg

runi:
	cd island && java -jar island.jar -mf temp.mesh -of island.mesh -shape star
	cd visualizer && java -jar visualizer.jar -mf ../island/island.mesh -of island.svg