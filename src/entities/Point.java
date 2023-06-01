package entities;

import java.text.DecimalFormat;

// Point class for VRPTW problem. Each point has an ID, x and y coordinates, and location code.
public class Point {
    public int ID;
    double x, y;
    String locationCode;

    public Point(int ID, double x, double y) {
        this.ID = ID;
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.ID = 0;
        this.x = 0;
        this.y = 0;
    }

    public Point(int ID) {
        this.ID = ID;
        this.x = 0;
        this.y = 0;
    }

    public Point(int ID, String locationCode) {
        this.ID = ID;
        this.locationCode = locationCode;
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        //this.bucketIDs = new ArrayList<Integer>();
    }

    public static void main(String[] args) {
        System.out.print(Math.toDegrees(Math.atan2(-0.5, 1)));
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    double degrees(Point p) {
        double X = p.x - x;
        double Y = p.y - y;
        double d = Math.toDegrees(Math.atan2(Y, X));
        if (d < 0) d += 360;
        return d;
    }

    public double distance(Point p) {
        return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
    }

    public double manhattanDistance(Point p) {
        Point p1 = new Point(x, p.y);
        return distance(p1) + p1.distance(p);
    }

    public Point clone() {
        return new Point(ID, x, y);
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return ID + " (" + df.format(x) + "," + df.format(y) + ")";
    }
}
