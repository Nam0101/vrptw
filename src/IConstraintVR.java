import entities.Point;

// ConstraintVR interface for VRPTW problem. Each constraint has a violation.
// The violation is calculated by some move methods.
public interface IConstraintVR {
    int violations();

    int evaluateOnePointMove(Point x, Point y);
}
