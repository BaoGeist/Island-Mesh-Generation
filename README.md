# Assignment A2: Mesh Generator
  - Author #1 linb44@mcmaster.ca
  - Author #2 hus44@mcmaster.ca
  - Author #3 moutafia@mcmaster.ca

## How to run the product

_This section needs to be edited to reflect how the user can interact with the feature released in your project_

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar sample.mesh
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Visualizer

To visualize an existing mesh, go the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

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

-- Insert here your definition of done for your features --

### Product Backlog

| Id | Feature title                                                       | Who?                  | Start      | End        | Status |
|:--:|---------------------------------------------------------------------|-----------------------|------------|------------|--------|
| F01 | draw segments between vertices (coloured) to visualize the squares | Baoze, Daniel         | 02.01.2023 | 02.02.2023 | D      |
| F02 | debug mode option on                                               | Baoze                 |            |            | P      |
| F03 | creating a mesh (using polygons, segments, and vertices)           | Baoze, Daniel, Alexis | 02.15.2023 |            | P      |
| F04 | producing a full mesh                                              | Daniel                |            |            |        |
| F05 | playing around with rendering                                      | Baoze, Alexis         | 02.17.2023 |            | P      |


