package ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

public interface Renderer {
    public void render(Structs.Mesh aMesh, Graphics2D canvas);
}
