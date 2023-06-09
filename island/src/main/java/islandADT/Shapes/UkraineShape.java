package islandADT.Shapes;


import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import java.util.List;
import java.util.Map;


public class UkraineShape implements Shape{

    private Polygon Urkaine;
    @Override
    public Geometry generateIsland(int radius, GeometryContainer geometryContainer) {
        GeometryFactory factory = new GeometryFactory();
        String urkaine = "[38.2166, 47.1033],[37.5807, 47.0904],[37.3352, 46.8825],[37.2082, 46.9336],[36.8862, 46.804],[36.1895, 46.669],[35.9016, 46.6527],[35.5105, 46.4572],[35.2914, 46.2934],[35.075, 46.2711],[34.8113, 46.1662],[34.8744, 45.9261],[35.0444, 45.6701],[35.3322, 45.3712],[35.4807, 45.2978],[35.7918, 45.4123],[36.0223, 45.3681],[36.3, 45.4733],[36.6135, 45.4089],[36.418, 45.2299],[36.4401, 45.0675],[36.2246, 45.0075],[36.0479, 45.0488],[35.848, 44.9924],[35.5474, 45.1197],[35.3894, 45.0019],[35.0057, 44.8352],[34.757, 44.8205],[34.469, 44.7298],[34.3412, 44.5585],[33.9579, 44.383],[33.721, 44.3996],[33.4774, 44.6066],[33.6196, 44.9315],[33.5625, 45.0939],[33.4134, 45.1845],[33.2596, 45.1561],[32.8993, 45.3542],[32.5129, 45.3406],[32.5252, 45.4582],[32.9368, 45.6625],[33.7087, 45.9097],[33.6135, 46.1452],[33.4238, 46.0533],[33.2935, 46.1357],[32.7898, 46.1343],[32.5034, 46.0761],[32.2625, 46.1282],[31.9683, 46.3511],[32.1117, 46.5124],[31.8584, 46.6291],[31.481, 46.6464],[31.3377, 46.6013],[30.9914, 46.6013],[30.7549, 46.5159],[30.7725, 46.3964],[30.4942, 46.0805],[30.1384, 45.8198],[29.9685, 45.8389],[29.865, 45.6723],[29.5897, 45.5591],[29.7474, 45.4634],[29.659, 45.2159],[29.6503, 45.3461],[29.3221, 45.4438],[28.7101, 45.227],[28.3302, 45.3229],[28.1995, 45.4618],[28.4809, 45.502],[28.474, 45.6579],[28.7383, 45.8376],[28.7576, 45.9612],[28.932, 45.9932],[29.0154, 46.1826],[28.9335, 46.259],[28.9458, 46.4548],[29.1627, 46.5381],[29.2007, 46.3571],[29.7269, 46.4558],[29.8478, 46.3415],[30.0811, 46.3742],[29.9161, 46.5188],[29.9526, 46.7247],[29.8436, 46.8541],[29.5587, 46.9457],[29.5566, 47.324],[29.3589, 47.3527],[29.1174, 47.5333],[29.2386, 47.756],[29.124, 47.976],[28.9504, 47.9348],[28.7993, 48.1118],[28.1999, 48.2117],[27.7518, 48.452],[27.5038, 48.4724],[27.1758, 48.3618],[26.8428, 48.3937],[26.6179, 48.259],[26.3035, 48.212],[26.1731, 47.9931],[25.2617, 47.8986],[25.1219, 47.7703],[24.8966, 47.7101],[24.5616, 47.9405],[24.231, 47.897],[23.9773, 47.9623],[23.3601, 47.9931],[23.2314, 48.0797],[22.8776, 47.9467],[22.831, 48.0724],[22.6001, 48.1011],[22.3571, 48.2431],[22.2718, 48.4035],[22.1328, 48.4048],[22.1537, 48.5859],[22.3225, 48.7003],[22.5396, 49.0722],[22.7445, 49.0455],[22.7242, 49.3671],[22.6409, 49.5288],[23.1015, 49.9571],[23.7475, 50.3894],[23.9813, 50.4048],[24.1065, 50.5386],[23.8747, 51.1361],[23.634, 51.3393],[23.6062, 51.5174],[23.7497, 51.6445],[23.9813, 51.586],[24.2441, 51.7182],[24.3908, 51.88],[24.7218, 51.8823],[25.1833, 51.9498],[25.7679, 51.9285],[26.4078, 51.8506],[26.8548, 51.7493],[27.1779, 51.7471],[27.254, 51.5954],[27.4773, 51.6237],[28.3469, 51.5252],[28.7999, 51.5326],[29.1604, 51.6033],[29.297, 51.3737],[29.618, 51.4856],[29.8286, 51.43],[30.1486, 51.4844],[30.3551, 51.3053],[30.6383, 51.3359],[30.5341, 51.5533],[30.662, 51.8194],[30.9405, 52.0201],[31.4749, 52.1178],[31.7643, 52.1006],[32.0956, 52.0407],[32.3067, 52.1412],[32.3395, 52.2872],[32.5284, 52.3162],[32.9269, 52.2469],[33.1965, 52.3689],[33.8041, 52.3546],[34.0557, 52.1703],[34.0682, 52.031],[34.3857, 51.8177],[34.3763, 51.7086],[34.128, 51.6807],[34.2803, 51.3613],[34.409, 51.2531],[34.7481, 51.1647],[34.9618, 51.2153],[35.3417, 50.9159],[35.4454, 50.6875],[35.4253, 50.5005],[35.6969, 50.3451],[35.8374, 50.4232],[36.1058, 50.4211],[36.269, 50.2814],[36.6826, 50.2607],[36.9141, 50.3391],[37.4353, 50.4249],[37.5992, 50.2909],[37.7277, 50.0787],[37.9953, 49.9213],[38.3442, 49.9921],[38.8522, 49.8658],[39.1828, 49.8585],[39.238, 49.7651],[39.5704, 49.7133],[39.8015, 49.5422],[40.0409, 49.4555],[40.1417, 49.2458],[39.9187, 49.0478],[39.6811, 49.0203],[39.7587, 48.8954],[39.6317, 48.5869],[39.8092, 48.5838],[39.9932, 48.2732],[39.8431, 48.1198],[39.7591, 47.8329],[39.3916, 47.8226],[38.8772, 47.8612],[38.7345, 47.6771],[38.3529, 47.6071],[38.201, 47.2906],[38.2166, 47.1033]";
        String egypt = "[34.2003, 31.3143],[34.2484, 31.2114],[34.4804, 30.6512],[34.5338, 30.4002],[34.5995, 30.3445],[34.8244, 29.7417],[34.8867, 29.4901],[34.7366, 29.3051],[34.6746, 29.1065],[34.6335, 28.7707],[34.4069, 28.308],[34.4553, 28.1675],[34.4274, 27.9831],[34.2395, 27.7941],[34.0785, 27.8007],[33.7634, 28.024],[33.5647, 28.2971],[33.4339, 28.3664],[33.2287, 28.5694],[33.145, 29.0317],[32.8856, 29.2431],[32.6808, 29.5999],[32.6907, 29.7336],[32.5635, 29.9724],[32.3377, 29.5999],[32.5915, 29.3495],[32.6597, 29.0891],[32.6255, 28.9727],[32.8206, 28.7479],[32.8689, 28.5789],[33.0029, 28.4895],[33.1265, 28.2875],[33.5125, 27.9591],[33.5579, 27.5368],[33.6917, 27.3419],[33.8254, 27.2597],[33.8368, 27.1208],[34.0036, 26.8489],[33.9414, 26.6535],[34.014, 26.6116],[34.3387, 26.0018],[34.5466, 25.725],[34.6819, 25.4287],[34.9898, 24.905],[35.2355, 24.4001],[35.3919, 24.2828],[35.5139, 23.9771],[35.4894, 23.497],[35.6953, 22.9321],[35.886, 22.7357],[36.1978, 22.6517],[36.4398, 22.3564],[36.8954, 22.0661],[36.8836, 21.9957],[35.4864, 21.9956],[34.3231, 21.9955],[33.1811, 21.9954],[31.4355, 21.9954],[31.4906, 22.1482],[31.3595, 22.1881],[31.2484, 21.9944],[30.4315, 21.9945],[29.4582, 21.9946],[28.2903, 21.9948],[27.317, 21.9949],[26.3438, 21.9951],[24.9812, 21.9954],[24.9812, 22.579],[24.9812, 23.7463],[24.9812, 25.2054],[24.9812, 26.6645],[24.9812, 27.6665],[24.9812, 29.1814],[24.8738, 29.4683],[24.8116, 29.8909],[24.6883, 30.1442],[24.9077, 30.4987],[24.9949, 30.7851],[24.8595, 31.146],[24.8613, 31.3804],[25.1509, 31.6565],[25.1909, 31.5315],[25.4019, 31.5032],[25.8241, 31.6149],[25.9668, 31.6166],[26.3622, 31.5219],[26.9655, 31.4497],[27.3206, 31.381],[27.3835, 31.2685],[27.6603, 31.1802],[27.8633, 31.2335],[27.8971, 31.1144],[28.3184, 31.0598],[28.405, 31.0865],[28.818, 30.9512],[29.0281, 30.8271],[29.2189, 30.838],[29.5033, 30.9567],[29.8384, 31.1541],[30.0422, 31.3205],[30.2678, 31.2573],[30.3622, 31.5087],[30.7407, 31.4055],[30.9094, 31.4238],[31.221, 31.5799],[31.5237, 31.4497],[31.8379, 31.5253],[31.7902, 31.3435],[32.057, 31.0797],[32.2482, 31.1069],[32.3005, 31.2814],[32.5095, 31.1069],[32.7313, 31.0388],[32.9688, 31.0865],[33.0986, 31.1895],[33.1877, 31.0617],[33.4995, 31.1281],[33.648, 31.1173],[34.047, 31.2292],[34.2003, 31.3143]";
        String[] string_array = egypt.split("],");
        double[] x_coords = new double[string_array.length];
        double[] y_coords = new double[string_array.length];
        Coordinate[] vertices = new Coordinate[string_array.length];
        double x_min = Integer.MAX_VALUE, x_max = Integer.MIN_VALUE;
        double y_min = Integer.MAX_VALUE, y_max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < vertices.length; i++) {
            String item = string_array[i];
            String string_mid = item.replace("[", "");
            string_mid = string_mid.replace("]", "");
            string_mid = string_mid.replace(" ", "");
            String[] split = string_mid.split((","));

            x_coords[i] = Double.parseDouble(split[0]);
            y_coords[i] = Double.parseDouble(split[1]);

            x_max = Math.max(x_coords[i], x_max);
            x_min = Math.min(x_coords[i], x_min);
            x_max = Math.max(y_coords[i], x_max);
            x_min = Math.min(y_coords[i], x_min);
        }
        max = Math.max(x_max, y_max);
        min = Math.min(x_min, y_min);

        double x_increment = 500 / (max - min);


        for(int i = 0; i < x_coords.length;i++) {
            Double new_x = (x_coords[i] - x_min) * x_increment;
            Double new_y = 400-(y_coords[i] - x_min) * x_increment;
            vertices[i] = new Coordinate(new_x, new_y);
        }

        LinearRing shell = factory.createLinearRing(vertices);
        Urkaine = factory.createPolygon(shell, null);
        set_tiles_inside_shape(geometryContainer, Urkaine);
        return Urkaine;
    }
    private TileTypeWrapper ocean = new TileTypeWrapper("Ocean");
    private TileTypeWrapper sand = new TileTypeWrapper("Sand");
    private TileTypeWrapper land = new TileTypeWrapper("Land");

    private void set_segment_vertex_land(PolygonWrapper p, GeometryContainer geometryContainer) {
        List<Integer> p_segments = p.getSegments_group();
        for(Integer segment_id: p_segments) {
            SegmentWrapper s = geometryContainer.get_segments().get(segment_id);
            //TODO B make a better method name/implementation
            s.setLandornah(true);
            geometryContainer.get_vertices().get(s.getV1id()).setLandornah(true);
            geometryContainer.get_vertices().get(s.getV2id()).setLandornah(true);
        }
    }

    private void set_tiles_inside_shape(GeometryContainer geometryContainer, Geometry IslandShape){
        Map<Integer, PolygonWrapper> PolygonList = geometryContainer.get_polygons();

        for (PolygonWrapper p: PolygonList.values()){

            double[] centroid_coords = geometryContainer.get_vertices().get(p.getId_centroid()).getCoords();
            Coordinate newCentroidCoords = new Coordinate(centroid_coords[0], centroid_coords[1]);
            Point PolygonCenter = new GeometryFactory().createPoint(newCentroidCoords);

            if (IslandShape.contains(PolygonCenter)){
                p.setTileType(land);
                set_segment_vertex_land(p, geometryContainer);
            } else {
                p.setTileType(ocean);
            }
        }

        for (PolygonWrapper p: PolygonList.values()){
            if (p.getTileType() == land){
                for (int i = 0; i < p.get_neighbours().size(); i++){
                    PolygonWrapper neighbor = PolygonList.get(p.get_neighbours().get(i));
                    if (neighbor.getTileType() == ocean){
                        p.setTileType(sand);
                        set_segment_vertex_land(p, geometryContainer);
                    };
                }
            }
        }
    }


}
