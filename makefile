run:
	cd generator && java -jar generator.jar -mf sample.mesh -mv irregular
	cd visualizer && java -jar visualizer.jar -mf ../generator/sample.mesh -of sample.svg
