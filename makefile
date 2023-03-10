run:
	cd generator && java -jar generator.jar sample.mesh irregular
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg
