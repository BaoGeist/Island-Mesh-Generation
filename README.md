# Assignment A3: Terrain Generator
  - Author #1 linb44@mcmaster.ca
  - Author #2 hus44@mcmaster.ca
  - Author #3 moutafia@mcmaster.ca

## Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A3`

To install the different tooling on your computer, simply run `mvn clean install`

After installation, you'll find:
- an application named `generator.jar` in the `generator` directory
- an application named `island.jar` in the `island` directory
- an application named `visualizer.jar` in the `visualizer` directory. 

## Quick-Start Guide
A make file with sample commands has been provided in the root project directory to demonstrate all functionalities. They will create .mesh and .svg files in the `img` directory. The easiest commands to run are:
- `make runfullplains`, Creates a mesh of 1000 polygons and runs a plains elevation profile with 3 lakes, 3 aquifers and 3 rivers. Visualized as island.svg (normal), elevation.svg (elevation heatmap) and moisture.svg (moisture heatmap)
- `make runfullvolcano`, Creates a mesh of 1000 polygons and runs a volcano elevation profile with 3 lakes, 3 aquifers and 3 rivers. Ditto visualization
- `make runfullcrater`, Creates a mesh of 1000 polygons and runs a crater elevation profile with 3 lakes, 3 aquifers and 3 rivers. Ditto visualization
- `make runfullcountry`, Creates a mesh of 1000 polygons and runs an Egypt elevation profile with 3 lakes, 3 aquifers and 3 rivers. Ditto visualization

Separately, the above commands can be split into two parts: the mesh generation, and island generation, and island visualization. The mesh generation commands are:
- `make rung1000`, Creates a mesh of 1000
- `make rung2000`, Creates a mesh of 2000
- `make rung100`, Creates a mesh of 100

The island generation (along with visualization) commands are:
- `make runip`, Runs Plains and creates an elevation and moisture heatmap
- `make runiv`, Runs Volcano and creates an elevation and moisture heatmap
- `make runic`, Runs Crater and creates an elevation and moisture heatmap
- `make runcountry`, Runs Egypt and creates an elevation and moisture heatmap
- `make runivs`, Runs Volcano and creates an elevation and moisture heatmap with a set seed of 593


## Individual Run
Below are the commands to run the jar's by oneself

### Individual Run - Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes up to 6 arguments, the name of the file where the generated mesh will be stored as binary with the option `-mv`, `regular` or `irregular` to determine what kind of mesh is built with the option `-mv`, and an integer value for the length of the side of the mesh with the option `-s`. If a regular mesh is created 1 additional argument follows: an integer value for square sizes `-ss`. If a irregular mesh is created, 2 additional arguments follow: an integer value for number of polygons to be created `-num` and the relaxation level `-ln`.

The following will create a regular 500x500 mesh with 25 vertice square length. These are the default values for a regular mesh as well. 

If a mesh is ran with a square length that is not a factor of the width OR length, the code will not run.

If help is needed with the CLI, please use `-h` after `-mf` and `-mv`, as they are required arguments.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar -mf sample.mesh -mv regular -s 500 -ss 25
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

The following will create an irregular 500x500 mesh with 200 polygons with a relaxation level of 5
```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar -mf sample.mesh -mv irregular -s 500 -num 200 -ln 5
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Individual Run - Island 
To run island, go to `island` directory, and use `java -jar` to run the product. This must run after the above section. It takes up to ___ arguments in no particular order.
- `i`, Specifies the relative address of the input mesh file
- `o`, Specifies the relative address of the output mesh file 
- `shape`, Specifies the shape profile of the island, options are `circle`, `star`, `country`, `oval`
- `altitude`, Specifies the elevation profile of the island, options are `plains`, `volcanic`, `crater`
- `seed`, Specifies the seed the island generates with
- `lake`, Specifies the maximum amount of lakes
- `river`, Specifies the maximum amount of rivers
- `aquifer`, Specifies the maximum amount of aquifers
- `help`, Provides help

Sample runs are provided below:
- `java -jar island.jar -help`, Basic help command
- `java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape circle -lake 3 -aquifer 3 -river 3`, Creates new island mesh
- `java -jar island.jar -i ../img/new.mesh -o ../img/island.mesh -altitude volcanic -shape star -seed 69 -lake 3 -aquifer 3 -river 3`, Creates new island mesh with set seed

### Individual Run - Visualizer

To visualize an existing mesh, go the `visualizer` directory, and use `java -jar` to run the product. These must be run after the above two sections. The product take up to 3 arguments.
- `i` The file containing the mesh
- `o`, The name of the file to store the visualization (as an SVG image)
- `mode`, The mode of the visualization, options are `debug`, `graphic`, `haltitude`, `hmoisture`
- `help`, For help

```
mosser@azrael A2 % cd visualizer 
mosser@azrael visualizer % generator.jar -mf sample.mesh -mv irregular

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```



## Backlog

### Definition of Done
Our Definition of Done includes the following criteria:
- Testing functions reveals no defects
- Both regular and irregular meshes generate and can be "played" around with
- All other rubric criteria are hit from an A-tier to S-tier (hopefully)
- Lecture Software Development concepts are applied

### Product Backlog

| Id  | Feature title                                                        | Who?                  | Start      | End        | Status |
|:---:|----------------------------------------------------------------------|-----------------------|------------|------------|--------|
| F01 | Initial Island Generation for Step 1                                 | Daniel, Baoze         | 03.05.2023 | 03.12.2023 | D      |
| F02 | Generates a shaped island (circle)                                   | Baoze, Alexis, Daniel | 03.10.2023 | 03.11.2023 | D      |
| F03 | Generates multiple shapes of island (stars, rectangle)               | Baoze, Alexis, Daniel | 03.11.2023 | 03.14.2023 | D      |
| F04 | Generate island with plains elevation profile                        | Baoze, Alexis, Daniel | 03.14.2023 | 03.14.2023 | D      |
| F05 | Generate island with assigned humidity values for tiles              | Baoze, Alexis, Daniel | 03.18.2023 | 03.18.2023 | D      |
| F06 | Generate island with more elevation profiles (volcanic, mountains)   | Baoze, Alexis, Daniel | 03.14.2023 | 03.14.2023 | D      |
| F07 | Integrate reproducibility/seeds                                      | Baoze                 | 03.15.2023 | 03.15.2023 | D      |
| F08 | Generate a lake at a random point                                    | Baoze                 | 03.15.2023 | 03.18.2023 | D      |
| F09 | Generate multiple lakes with CLI input                               | Baoze                 | 03.18.2023 | 03.18.2023 | D      |
| F10 | Generate a river at a random point                                   | Daniel                | 03.15.2023 | 03.16.2023 | D      |
| F11 | Generate multiple rivers                                             | Daniel                | 03.16.2023 | 03.18.2023 | D      |
| F12 | Calculate river discharge, show changes                              | Daniel                | 03.16.2023 | 03.19.2023 | D      |
| F13 | Generate aquifers                                                    | Baoze                 | 03.18.2023 | 03.18.2023 | D      |
| F14 | Calculate soil absorption with soil profile input                    | Daniel, Baoze         | 03.18.2023 | 03.21.2023 | D      |
| F15 | Generate biomes based on humidity + temperature                      | Alexis                | 03.21.2023 | 03.25.2023 | D      |
| F16 | Generate islands based on Whittaker diagrams + CLI                   | Alexis                |            |            | P      |
| F17 | Generate resources based on biome                                    | Daniel, Baoze         |            |            | P      |
| F18 | Generate resources based on biome + humidity + elevation             | Daniel, Baoze         |            |            | P      |
| F19 | Generate heatmap for one parameter(i.e. elevation)                   | Daniel, Baoze         | 03.14.2023 | 03.14.2023 | D      |
| F20 | Generate heatmap for all parameters (humidity, elevation, resources) | Daniel, Baoze         | 03.18.2023 |            | I      |






