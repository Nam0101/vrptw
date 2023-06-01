package entities;

import java.util.ArrayList;
import java.util.HashMap;
// NodeWeightsManager class for VRPTW problem. Each node has a weight.
//the NodeWeightsManager class provides methods to add points,
// set and retrieve weights associated with those points, and print the points with their weights.

public class NodeWeightsManager {
	protected ArrayList<Point> points;
	protected double[] weights;
	protected HashMap<Point, Integer> map;

	public NodeWeightsManager(ArrayList<Point> points) {
		this.points = points;
		map = new HashMap<Point, Integer>();
		for (int i = 0; i < points.size(); i++)
			map.put(points.get(i), i);
		//weights = new double[points.size()];
		weights = new double[points.size() < 1000 ? 1000 : points.size()];
	}

	private void scaleUp() {
		double[] t_w = new double[2 * weights.length];
		System.arraycopy(weights, 0, t_w, 0, weights.length);
		weights = t_w;
	}

	public void addPoint(Point p) {
		if (weights.length == points.size()) scaleUp();
		points.add(p);
		map.put(p, points.size() - 1);
	}

	public double getWeight(Point p) {
		return weights[map.get(p)];
	}

	public void setWeight(Point p, double w) {
		weights[map.get(p)] = w;
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}

	public void print() {
		for (int i = 0; i < points.size(); i++) {
			System.out.println("Point " + i + ": " + points.get(i).toString() + " - " + weights[i]);
		}
	}
}
