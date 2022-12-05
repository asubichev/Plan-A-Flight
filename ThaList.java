import java.security.InvalidParameterException;
import java.util.LinkedList;

public class ThaList {

    private LinkedList list;
    private CityNode head;

    public ThaList() {
        list = null;
    }

    // overloaded constructor not needed
    public boolean addCity(String name) {
        if (head.getName().equals(name))
            return false; // if node we're adding exists in head
        CityNode temp = head;
        while (temp.getDown() != null) {
            if (temp.getName().equals(name))
                return false;
            temp = temp.getDown();
        }
        temp.setDown(new CityNode(name, 0, 0));
        return true;
    }

    public boolean addPair(String name1, String name2, int cost, int duration) {
        if (name1.equals(name2))
            throw new IllegalArgumentException("City 1 == City 2");
        CityNode temp = head;
        if (!head.getName().equals(name1)) {
            // head ain't name1
            while (temp.getDown() != null) {
                if (temp.getName().equals(name1)) // we've found our node to add name2 to
                    break;
                temp = temp.getDown();
            }
            // if we broke, this if statement should fail
            if (temp.getDown() == null) {
                temp.setDown(new CityNode(name1, 0, 0));
                temp = temp.getDown();
            }
        }
        // we're now at the source node, need to add destination
        while (temp.getRight() != null) {
            if (temp.getName().equals(name2))
                return false; // name2 already existed in name1, shouldn't happen
            temp = temp.getRight();
        }
        temp.setRight(new CityNode(name2, cost, duration));

        // now same thing for name2
        temp = head;
        if (!head.getName().equals(name2)) {
            // name2 ain't head
            while (temp.getDown() != null) {
                if (temp.getName().equals(name2))
                    break;
                temp = temp.getDown();
            }
            // reached node, or end w/o find
            if (temp.getDown() == null) {
                temp.setDown(new CityNode(name2, 0, 0));
                temp = temp.getDown();
            }
        }
        while (temp.getRight() != null) {
            if (temp.getName().equals(name1))
                return false;
            temp = temp.getRight();
        }
        temp.setRight(new CityNode(name2, cost, duration));
        return true;
    }

    public CityNode getHead() {
        return head;
    }
}
