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

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes up to 6 arguments, the name of the file where the generated mesh will be stored as binary, `regular` or `irregular` to determine what kind of mesh is built, an integer value for the width of the mesh, and an integer value for the height of the mesh. If a regular mesh is created 3 additional arguments follow: an integer value for square sizes, float value for transparency, and finally int value for thickness of segments. If a irregular mesh is created, 1 additional argument follows: an interger value for number of polygons to be created.

The following will create a regular 500x500 mesh with 25 vertice square length, 1.00f transparency, and 1 thickness. These are the default values for a regular mesh as well.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar sample.mesh irregular 500 500 25 1.00f 1
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

The following will create an irregular 500x500 mesh with 200 polygons
```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar sample.mesh irregular 500 500 200
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Visualizer

To visualize an existing mesh, go the `visualizer` directory, and use `java -jar` to run the product. The product take up to 3 arguments: the file containing the mesh, and the name of the file to store the visualization (as an SVG image).
To enter debug mode, use `-X` as the third argument.

```
mosser@azrael A2 % cd visualizer 
mosser@azrael visualizer % java -jar visualizer.jar ../generator/sample.mesh sample.svg

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```
To viualize the SVG file:

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

| Id | Feature title                                                                 | Who?                  | Start      | End        | Status |
|:--:|-------------------------------------------------------------------------------|-----------------------|------------|------------|--------|
| F01 | draw segments between vertices (coloured) to visualize the squares           | Baoze, Daniel         | 02.01.2023 | 02.02.2023 | D      |
| F02 | ability to turn debug mode option on                                         | Baoze, Alexis         | 02.16.2023 |            | I      |
| F03 | created  new polygons, segments, and vertices classes                        | Baoze, Alexis         | 02.15.2023 | 02.17.2023 | D      |
| F04 | produced a full mesh ADT with above F03 classes                              | Daniel                | 02.15.2023 | 02.17.2023 | D      |
| F05 | playing around with rendering                                                | Baoze                 | 02.17.2023 |            | B (04) |
| F06 | visualization mode with rendering                                            | Alexis                | 02.17.2023 |            | B (05) |
| F07 | generate random points in a precision model, one for each expected Polygon   | Baoze                 | 02.20.2023 | 02.20.2023 | D      |
| F08 | generate first voronoi diagram with above points                             | Baoze                 | 02.20.2023 | 02.20.2023 | D      |
| F09 | compute lloyd relaxation on said points                                      | Baoze                 | 02.20.2023 | 02.20.2023 | D      |
| F10 | crop the mesh to expected size, and keep centroids within the space          | Daniel                | 02.20.2023 | 02.20.2023 | D      |
| F11 | find neighbourhood relations with Delaunay's triangulation                   | Alexis, Daniel        | 02.27.2023 |            | I      |
| F12 | compute convex hull to reorder irregular polygon segments                    | Daniel                | 02.23.2023 | 02.23.2023 | D      |
| F13 | CLI                                                                          | Alexis, Daniel, Baoze | 02.25.2023 | 02.27.2023 | D      |
| F14 | 



