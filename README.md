# Assignment A2: Mesh Generator
  - Author #1 linb44@mcmaster.ca
  - Author #2 hus44@mcmaster.ca
  - Author #3 moutafia@mcmaster.ca

## How to run the product

Read below, pay attention to the generator section

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn clean install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator

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

### Visualizer

To visualize an existing mesh, go the `visualizer` directory, and use `java -jar` to run the product. The product take up to 3 arguments: the file containing the mesh with the option `-mf`, and the name of the file to store the visualization (as an SVG image) with the option `-of`.
To enter debug mode, use `-X` as the third argument.
For help, use `-h` after `-mf` and `-of`

```
mosser@azrael A2 % cd visualizer 
mosser@azrael visualizer % generator.jar -mf sample.mesh -mv irregular

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```
To visualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

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
| F05 | Generate island with random humidity values for tiles                | Baoze, Alexis, Daniel |            |            | P      |
| F06 | Generate island with more elevation profiles (volcanic, mountains)   | Baoze, Alexis, Daniel | 03.14.2023 | 03.14.2023 | D      |
| F07 | Integrate reproducibility/seeds                                      | Baoze                 | 03.15.2023 | 03.15.2023 | D      |
| F08 | Generate a lake at a random point                                    | Baoze                 | 03.15.2023 | 03.18.2023 | D      |
| F09 | Generate multiple lakes with CLI input                               | Baoze                 | 03.18.2023 | 03.18.2023 | D      |
| F10 | Generate a river at a random point                                   | Daniel                |            |            | P      |
| F11 | Generate multiple rivers                                             | Daniel                |            |            | B(F10) |
| F12 | Calculate river discharge, show changes                              | Daniel                |            |            | B(F11) |
| F13 | Generate aquifers                                                    | Baoze                 | 03.18.2023 | 03.18.2023 | D      |
| F14 | Calculate soil absorption with soil profile input                    | Daniel                |            |            | B(F    |
| F15 | Generate biomes based on humidity + temperature                      | Alexis                |            |            | P      |
| F16 | Generate islands based on Whittaker diagrams + CLI                   | Alexis                |            |            | P      |
| F17 | Generate resources based on biome                                    | Daniel, Baoze         |            |            | P      |
| F18 | Generate resources based on biome + humidity + elevation             | Daniel, Baoze         |            |            | P      |
| F19 | Generate heatmap for one parameter(i.e. elevation)                   | Daniel, Baoze         | 03.14.2023 | 03.14.2023 | D      |
| F20 | Generate heatmap for all parameters (humidity, elevation, resources) | Daniel, Baoze         |            |            | P      |






