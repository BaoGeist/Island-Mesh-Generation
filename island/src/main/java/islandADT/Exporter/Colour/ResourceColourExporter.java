package islandADT.Exporter.Colour;

import islandADT.GeometryWrappers.PolygonWrapper;

import java.util.Map;

public class ResourceColourExporter extends ColourExporter {
    @Override
    public void set_increments(Map<Integer, PolygonWrapper> polygons) {
        int increment = 1;
    }

    @Override
    public int[] export(PolygonWrapper p) {
        int value = p.getScore();
        if(value <= 2 || p.isWaterOrNah()) return new int[]{0, 0, 0};
        if (p.getResource() == "snow") {
            if(value <= 3) return new int[]{188, 227, 255};
            else if(value <= 4) return new int[]{128, 163, 255};
            else if(value <= 5) return new int[]{63, 106, 255};
            else if(value <= 6) return new int[]{8, 78, 255};
            else if(value <= 7) return new int[]{51, 31, 182};
            else if(value <= 8) return new int[]{34, 9, 138};
            else if(value <= 9) return new int[]{24, 5, 103};
            else if (value > 9) return new int[]{14, 2, 58};
            else {
                return new int[]{255, 255, 255};
            }
        }
        else if (p.getResource() == "wood") {
            if(value <= 3) return new int[]{184, 155, 132};
            else if(value <= 4) return new int[]{181, 140, 107};
            else if(value <= 5) return new int[]{179, 123, 79};
            else if(value <= 6) return new int[]{171, 107, 56};
            else if(value <= 7) return new int[]{166, 93, 35};
            else if(value <= 8) return new int[]{143, 74, 19};
            else if(value <= 9) return new int[]{125, 60, 7};
            else if (value > 9) return new int[]{54, 24, 1};
            else {
                return new int[]{255, 255, 255};
            }
        }
        else if (p.getResource() == "sand") {
            if(value <= 3) return new int[]{235, 228, 181};
            else if(value <= 4) return new int[]{227, 218, 157};
            else if(value <= 5) return new int[]{207, 196, 124};
            else if(value <= 6) return new int[]{189, 176, 94};
            else if(value <= 7) return new int[]{176, 161, 67};
            else if(value <= 8) return new int[]{158, 142, 40};
            else if(value <= 9) return new int[]{135, 120, 20};
            else if (value > 9) return new int[]{115, 100, 5};
            else {
                return new int[]{255, 255, 255};
            }
        }
        else if (p.getResource() == "bamboo") {
            if(value <= 3) return new int[]{213, 240, 204};
            else if(value <= 4) return new int[]{179, 227, 163};
            else if(value <= 5) return new int[]{150, 212, 129};
            else if(value <= 6) return new int[]{123, 194, 99};
            else if(value <= 7) return new int[]{101, 184, 73};
            else if(value <= 8) return new int[]{68, 150, 41};
            else if(value <= 9) return new int[]{47, 130, 20};
            else if (value > 9) return new int[]{22, 84, 2};
            else {
                return new int[]{255, 255, 255};
            }
        }
        else if (p.getResource() == "livestock") {
            if(value <= 3) return new int[]{218, 219, 217};
            else if(value <= 4) return new int[]{193, 194, 192};
            else if(value <= 5) return new int[]{165, 166, 164};
            else if(value <= 6) return new int[]{142, 143, 141};
            else if(value <= 7) return new int[]{114, 115, 114};
            else if(value <= 8) return new int[]{90, 92, 90};
            else if(value <= 9) return new int[]{62, 64, 62};
            else if (value > 9) return new int[]{38, 38, 38};
            else {
                return new int[]{255, 255, 255};
            }
        }
        else if (p.getResource() == "berries") {
            if(value <= 3) return new int[]{234, 217, 252};
            else if(value <= 4) return new int[]{210, 185, 237};
            else if(value <= 5) return new int[]{180, 145, 217};
            else if(value <= 6) return new int[]{156, 110, 204};
            else if(value <= 7) return new int[]{129, 74, 186};
            else if(value <= 8) return new int[]{109, 47, 173};
            else if(value <= 9) return new int[]{89, 25, 156};
            else if (value > 9) return new int[]{64, 7, 122};
            else {
                return new int[]{255, 255, 255};
            }
        }
        else {//mushrooms (see: mushroom biome in minecraft)
            if(value <= 3) return new int[]{252, 222, 227};
            else if(value <= 4) return new int[]{235, 176, 186};
            else if(value <= 5) return new int[]{227, 109, 129};
            else if(value <= 6) return new int[]{209, 69, 93};
            else if(value <= 7) return new int[]{204, 43, 70};
            else if(value <= 8) return new int[]{179, 5, 34};
            else if(value <= 9) return new int[]{148, 3, 27};
            else if (value > 9) return new int[]{97, 2, 18};
            else {
                return new int[]{255, 255, 255};
            }
        }
    }
}
