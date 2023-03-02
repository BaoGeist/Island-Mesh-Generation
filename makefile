run:
 	cd generator && java -jar generator.jar -mf sample.mesh -mv regular -w 500 -h 500 -ss 25 -o 1.00f -t 1
#	cd generator && java -jar generator.jar sample.mesh irregular -w 500 -h 500 -num 200
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg irregular 500 500 200
