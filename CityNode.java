public class CityNode
{
    private String city;
    private int cost;
    private int duration;
    private CityNode next;

    public CityNode()
    {
        city = "";
        cost = 0;
        duration = 0;
        next = null;
    }

    public CityNode(String city, int cost, int duration)
    {
        this.city = city;
        this.cost = cost;
        this.duration = duration;
        next = null;
    }


    public String getName() {
        return this.city;
    }

    public void setName(String city) {
        this.city = city;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CityNode getNext() {
        return this.next;
    }

    public void setNext(CityNode next) {
        this.next = next;
    }
}
