run:
	cd generator && java -jar generator.jar -mf temp.mesh -mv irregular -num 1000
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg

runi:
	cd island && java -jar island.jar -mf new.mesh -of island.mesh
	cd visualizer && java -jar visualizer.jar -mf ../island/island.mesh -of island.svg