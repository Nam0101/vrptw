package entities;

import java.util.ArrayList;
import java.util.HashMap;

// ArcWeightsManager class for VRPTW problem. Each arc has a weight.
// The weight is the distance between two points.
// The distance is calculated by the Euclidean distance formula.
public class ArcWeightsManager {
    private final ArrayList<Point> points;
    private final HashMap<Point, Integer> map;
    private final double[][] weights;

    public ArcWeightsManager(ArrayList<Point> points) {
        this.points = points;
        map = new HashMap<Point, Integer>();
        for (int i = 0; i < points.size(); i++) {
            map.put(points.get(i), i);
        }
        weights = new double[points.size()][points.size()];
    }

    public void setWeight(Point p1, Point p2, double w) {
        weights[map.get(p1)][map.get(p2)] = w;
    }

    public double getWeight(Point p1, Point p2) {
        return weights[map.get(p1)][map.get(p2)];
    }

    public double getDistance(Point x, Point y) {
        return getWeight(x, y);
    }

    public double[][] getWeight() {
        return weights;
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }
}
