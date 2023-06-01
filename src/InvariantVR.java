import entities.Point;

public interface InvariantVR {
    // InvariantVR interface for VRPTW problem.
    // defines several methods for performing moves and propagating impacts
    VRManager getVRManager();

    void initPropagation();

    void propagateOnePointMove(Point x, Point y);
}
