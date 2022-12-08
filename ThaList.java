import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class ThaList {

    private CityNode head;

    public ThaList() {
        head = null;
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
        if (head == null) {
            head = new CityNode(name1, 0, 0);
            head.setRight(new CityNode(name2, cost, duration));
            head.setDown(new CityNode(name2, 0, 0));
            head.getDown().setRight(new CityNode(name1, cost, duration));
        } else {
            if (name1.equals(name2))
                throw new IllegalArgumentException("City 1 is also City 2");
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
                    if (!temp.getName().equals(name1)) {
                        temp.setDown(new CityNode(name1, 0, 0));
                        temp = temp.getDown();
                    }
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
                    if (!temp.getName().equals(name2)) {
                        temp.setDown(new CityNode(name2, 0, 0));
                        temp = temp.getDown();
                    }
                }
            }
            while (temp.getRight() != null) {
                if (temp.getName().equals(name1))
                    return false;
                temp = temp.getRight();
            }
            temp.setRight(new CityNode(name1, cost, duration));
        }
        return true;
    }

    public String toString() {
        String output = "";
        CityNode one = head;
        CityNode two = head;
        while (one != null) {
            output += two.getName() + " -> ";
            while (two.getRight() != null) {
                two = two.getRight();
                output += two.getName() + " -> ";
            }
            output += "\n" + "V" + "\n";
            one = one.getDown();
            two = one;
        }
        return output;
    }

    public String findPath(String origin, String destination, int flightNum, boolean moneyIsAnIssue) {
        final int MAX_PATHS = 3;
        int pathCount = 0;

        String output = "";
        Stack<CityNode> stk = new Stack<>(); // keep nodes, not just name for backtracking purposes
        Set<String> visited = new HashSet<>();
        Map<Integer, String> routes = new HashMap<>();
        float cost = 0;
        int time = 0;

        CityNode temp = findOrigin(origin);
        stk.push(temp);

        while (!stk.isEmpty()) {
            if (temp.getRight() != null) {
                temp = temp.getRight();
                if (!visited.contains(temp.getName())) {
                    stk.push(temp);
                    // then jump to origin node
                    temp = findOrigin(temp.getName());
                    visited.add(temp.getName());
                } else if (temp.getName().equals(destination)) {
                    pathCount++;
                    Stack<CityNode> tmpstk = new Stack<>();
                    cost += temp.getCost();
                    time += temp.getTime();
                    while (!stk.isEmpty()) {
                        cost += stk.peek().getCost();
                        time += stk.peek().getTime();
                        tmpstk.push(stk.pop());
                    }
                    // at this point, tmpstk will pop in order of current path
                    // tmpstk is reverse of what stk was
                    String rout = "";
                    while (!tmpstk.isEmpty()) {
                        rout += tmpstk.pop().getName() + " -> ";
                    }
                    NumberFormat formattr = new DecimalFormat("0.00");
                    rout += destination + ". Time: " + time + " Cost: " + formattr.format(cost);
                    if (moneyIsAnIssue)
                        routes.put((int) cost, rout);
                    else
                        routes.put(time, rout);

                    if (pathCount == MAX_PATHS)
                        break;
                }
            } else {
                // reached dead end, pop and go to last thread
                stk.pop();
                temp = stk.peek();
            }
            output += "Flight" + flightNum + ": " + origin + ", " + destination + "(";
            if (moneyIsAnIssue)
                output += "Cost";
            else
                output += "Time";
            output += ")";
        }
        SortedSet<Integer> keys = new TreeSet<>(routes.keySet());
        int i = 1;
        for (int key : keys) {
            output += "Path " + i + ": " + routes.get(key);
            i++;
        }
        return output;

        // while (!stk.isEmpty()) {
        // CityNode node = stk.pop();
        // visited.add(node.getName());

        // if (node == destination) {
        // ArrayList<CityNode> route = new ArrayList<>();
        // route.add(node);
        // while (visited.get(node) != null) {
        // node = visited.get(node);
        // route.add(0, node);
        // }
        // routes.add(route);
        // }

        // CityNode temp = node;
        // while (temp != null) {
        // if (!visited.containsKey(temp)) {
        // stk.push(temp);
        // visited.put(temp, node);
        // }
        // }
        // }
        // convert routes to String
    }

    public CityNode findOrigin(String request) {
        CityNode temp = head;
        while (temp != null) {
            if (temp.getName().equals(request))
                return temp;
            temp = temp.getDown();
        }
        return null;
    }

    public CityNode getHead() {
        return head;
    }
}
