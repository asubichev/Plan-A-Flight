import java.util.LinkedList;

public class ThaList
{
    private LinkedList list;
    private CityNode head;
    
    public ThaList()
    {
        list = null;
    }

    //overloaded constructor not needed
    public boolean addCity(String name)
    {
        if (head.getName().equals(name)) return false; //if node we're adding exists in head
        CityNode temp = head;
        while(temp.getNext() != null)
        {
            if(temp.getName().equals(name)) return false;
            temp = temp.getNext();
        }
        temp.setNext(new CityNode(name, 0, 0));
        return true;
    }

    public CityNode getHead(){ return head; }
}
