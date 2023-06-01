import entities.Point;

import java.util.ArrayList;
// ConstraintSystemVR class for VRPTW problem. Each constraint has a violation.
// The violation is calculated by some move methods.

public class ConstraintSystemVR implements IConstraintVR {
    private ArrayList<IConstraintVR> constraints;
    private int violations;
    private VRManager mgr;

    public VRManager getVRManager() {
        return mgr;
    }

    public void post(IConstraintVR c) {
        constraints.add(c);
    }

    @Override
    public int violations() {
        return violations;
    }

    @Override
    public int evaluateOnePointMove(Point x, Point y) {
        int res = 0;
        for (IConstraintVR c : constraints) {
            res += c.evaluateOnePointMove(x, y);
        }
        return res;
    }
}
