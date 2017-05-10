

public class Vertex
{
	String name="";
	int count;
	MyLinkedList<Edge> edgeset=new MyLinkedList<Edge>();
	MyLinkedList<Vertex> path ;
	Vertex(){
		path = new MyLinkedList<Vertex>() ;
	}
	public void add(Edge o)
	{
		edgeset.Insert(o);
	}
	public boolean containsEdge(Edge e)
	{
		boolean ans=false;
		ans=edgeset.IsMember(e);
		return ans;
	}
	public Edge getEdge(Edge e)
	{
		return edgeset.getMember(e);
	}

	public String getname()
	{
		return name;
	}
	public int getcount()
	{
		return count;
	}
}

