package interfaces;

import entities.Point;

import java.util.ArrayList;

import interfaces.InvariantVR;

public class VRManager {
    private final ArrayList<InvariantVR> invariants;
    private VarRoutesVR X;
    private final ArrayList<ConstraintSystemVR> constraints;

    public VRManager() {
        invariants = new ArrayList<InvariantVR>();
        X = null;
        constraints = new ArrayList<ConstraintSystemVR>();
    }

    public void postConstraintSystemVR(ConstraintSystemVR c) {
        constraints.add(c);
    }

    public void post(InvariantVR c) {
        invariants.add(c);
    }

    public void post(VarRoutesVR XR) {
        if (X != null) {
            System.out.println("interfaces.VRManager.post(X) error: X is not null");
            System.exit(-1);
        }
        this.X = XR;
    }

    public void initPropagation() {
        for (InvariantVR inv : invariants) {
            inv.initPropagation();
        }
    }

    public void close() {
        for (ConstraintSystemVR S : constraints) {
            invariants.add((InvariantVR) S);
        }
        initPropagation();
    }

    public void performOnePointMove(Point x, Point y) {
        X.performOnePointMove(x, y);
        for (InvariantVR f : invariants) {
            f.propagateOnePointMove(x, y);
        }
    }
}
