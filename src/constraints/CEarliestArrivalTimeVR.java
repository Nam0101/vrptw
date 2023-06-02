package constraints;
import interfaces.IConstraintVR;
import entities.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
public class CEarliestArrivalTimeVR implements IConstraintVR{

    /**
     * @return
     */
    @Override
    public int violations() {
        return 0;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    public int evaluateOnePointMove(Point x, Point y) {
        return 0;
    }
}