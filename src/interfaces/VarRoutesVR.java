package interfaces;

import entities.Point;
import enums.PointType;
import interfaces.VRManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * K: number of vehicles (also the number of routes)
 * n: number of clients
 * Indexing:
 * * N = 2*K+n: total number of points
 * * clients are numbered 1, 2, ..., n
 * * (n+i, n+i+K) are respectively the starting end terminating points of vehicle i, \forall i = 1,...,K
 * * n+i and n+i+K are mapped to the physical depot
 * Modelling solutions:
 * * next[i] is the next point of the point i, next[i] \in {1,...,N}, \forall i = 1,...,N
 * * prev[i] is the previous point of the point i, prev[i]\in {1,...,N}, \forall i=1,...,N
 * * route[i] is the route index of the point i, route[i]\in {1,...,K}, forall i = 1,...,N
 */
public class VarRoutesVR {
    ArrayList<Point> startingPoints;
    ArrayList<Point> terminatingPoints;
    ArrayList<Point> clientPoints;
    ArrayList<Point> allPoints;
    private int[] next;
    private int[] prev;
    private int[] route;
    private int N;
    private int K;
    private int n;
    //store old values of next, prev, route
    private int[] old_next;
    private int[] old_prev;
    private int[] old_route;
    private int[] index;
    private PointType[] pointType;
    private HashMap<Point, Integer> mPoint2Index;
    private int maxNbPoints;
    private final VRManager mgr;

    public VarRoutesVR(VRManager mgr) {
        this.mgr = mgr;

    }

    public int[] getNext() {
        return next;
    }

    public int[] getPrev() {
        return prev;
    }

    public int[] getRoute() {
        return route;
    }

    public int getN() {
        return N;
    }

    public int[] getOld_next() {
        return old_next;
    }

    public int[] getOld_prev() {
        return old_prev;
    }

    public int[] getOld_route() {
        return old_route;
    }

    public PointType[] getPointType() {
        return pointType;
    }

    public ArrayList<Point> getStartingPoints() {
        return startingPoints;
    }

    public ArrayList<Point> getTerminatingPoints() {
        return terminatingPoints;
    }

    public ArrayList<Point> getClientPoints() {
        return clientPoints;
    }

    public ArrayList<Point> getAllPoints() {
        return allPoints;
    }

    public HashMap<Point, Integer> getmPoint2Index() {
        return mPoint2Index;
    }

    public int getMaxNbPoints() {
        return maxNbPoints;
    }

    public VRManager getMgr() {
        return mgr;
    }

    public Point next(Point x) {
        int idx = getIndex(x);
        if (idx == Constants.NULL_POINT) return null;
        return (next[idx] == Constants.NULL_POINT) ? null : allPoints.get(next[idx]);
    }

    public int getK() {
        return K;
    }

    private int getIndex(Point x) {
        return mPoint2Index.get(x);
    }

    public boolean isClientPoint(Point x) {
        int index = getIndex(x);
        return index >= 0 && pointType[getIndex(x)] == PointType.CLIENT;
    }

    public boolean isStartingPoint(Point x) {
        int index = getIndex(x);
        return index >= 0 && pointType[getIndex(x)] == PointType.STARTING_ROUTE;
    }

    public boolean checkPerformOnePointMove(Point x, Point y) {
        int idx = getIndex(x);
        int idy = getIndex(y);
        boolean oke1 = isClientPoint(x);
        boolean ok2 = (isStartingPoint(y) || isClientPoint(y));
        boolean ok3 = x != y;
        boolean ok4 = route[idx] != Constants.NULL_POINT && route[idy] != Constants.NULL_POINT && next(y) != x;
        return oke1 && ok2 && ok3 && ok4;
    }

    private void copySolution() {
        System.arraycopy(next, 0, old_next, 0, next.length);
        System.arraycopy(prev, 0, old_prev, 0, prev.length);
        System.arraycopy(route, 0, old_route, 0, route.length);
    }

    public void performOnePointMove(Point x, Point y) {
        if (!checkPerformOnePointMove(x, y)) {
            System.out.println("::performOnePointMove FAILED, x = " + x + ", y = " + y);
            System.exit(-1);
        }
        int idx = getIndex(x);
        int idy = getIndex(y);
        performOnePointMove(idx, idy);
    }

    public int getStartingPointOfRoute(int k) {
        return n + k;
    }

    public int getTerminatingPointOfRoute(int k) {
        return n + K + k;
    }

    private void move(int x, int y) {
        if (route[x] != Constants.NULL_POINT) {
            next[prev[x]] = next[x];
            prev[next[x]] = prev[x];
        }
        route[x] = route[y];
        next[x] = next[y];
        prev[next[y]] = x;
        prev[x] = y;
        next[y] = x;
    }

    private void update(int k) {
        int s = getStartingPointOfRoute(k);
        int t = getTerminatingPointOfRoute(k);
        index[s] = 0;
        for (int x = s; x != t; x = next[x]) {
            index[next[x]] = index[x] + 1;
        }
    }

    private void performOnePointMove(int x, int y) {
        copySolution();
        move(x, y);
        HashSet<Integer> S = new HashSet<Integer>();
        S.add(old_route[x]);
        S.add(old_route[y]);
        for (int r : S) {
            update(r);
        }
    }
}
