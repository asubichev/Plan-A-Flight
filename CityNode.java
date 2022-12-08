public class CityNode {

    private String city;
    private double cost;
    private int duration;
    private CityNode down;
    private CityNode right;

    public CityNode() {
        city = "";
        cost = 0;
        duration = 0;
        down = null;
        right = null;
    }

    public CityNode(String city, double cost, int duration) {
        this.city = city;
        this.cost = cost;
        this.duration = duration;
        down = null;
        right = null;
    }

    public String getName() {
        return this.city;
    }

    public void setName(String city) {
        this.city = city;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTime() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CityNode getDown() {
        return this.down;
    }

    public void setDown(CityNode next) {
        this.down = next;
    }

    public CityNode getRight() {
        return right;
    }

    public void setRight(CityNode right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        return ((CityNode) o).getName().equals(this.city);
    }

    public String toString() {
        return this.city;
    }
}
