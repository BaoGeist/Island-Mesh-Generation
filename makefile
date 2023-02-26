run:
# 	cd generator && java -jar generator.jar sample.mesh regular 500 500 25 1.00f 1
	cd generator && java -jar generator.jar sample.mesh irregular 500 500 200
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg irregular 500 500 200
