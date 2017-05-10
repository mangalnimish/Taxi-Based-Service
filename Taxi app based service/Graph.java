

public class Graph {
MyLinkedList<Vertex> graph=new MyLinkedList<Vertex>();
MyLinkedList<Taxi> taxi =new MyLinkedList<Taxi>();
public boolean availability(int t)
{
	ob<Taxi> temp=taxi.top;
	boolean ans=false;
	while(temp.data!=null)
	{
		if(temp.data.time<=t)
		{
			ans=true;
			break;
		}
		temp=temp.next;
	}
	return ans;
}
public boolean isvertex(String a)
{
	boolean ans=false;
	ob<Vertex> temp=graph.top;
	while(temp.data!=null)
	{
		if(temp.data.name.equals(a))
		{
			ans=true;
			break;
		}
		temp=temp.next;
	}
	return ans;
}
public Vertex getvertex(String a)
{
	Vertex ans =new Vertex();
	ob<Vertex> temp=graph.top;
	while(temp.data!=null)
	{
		if(temp.data.name.equals(a))
		{
			ans=temp.data;
			break;
		}
		temp=temp.next;
	}
	return ans;
}
public Vertex min(MyLinkedList<Vertex> a)
{
	ob<Vertex> temp=a.top;
	int min=temp.data.count;
	Vertex ans=new Vertex();
	while(temp.data!=null)
	{
		if(min>=temp.data.count)
		{
			min=temp.data.count;
			ans=temp.data;
		}
		temp=temp.next;
	}
	return ans;
}
public int shortestpath(Vertex src, Vertex dst)
{
	int n;
	int i=1;
	MyLinkedList<Vertex> unvisited=new MyLinkedList<Vertex>();
	if (graph.IsMember(src) && graph.IsMember(dst))
	{
		ob<Vertex> temp=graph.top;
		while(temp.data!=null)
		{
			temp.data.path = new MyLinkedList<Vertex>() ;
			if(temp.data==src)
			{
				temp.data.count=0;
			}
			else
			{
				temp.data.count=10000;
			}
			unvisited.Insert(temp.data);
			temp=temp.next;

		}

		while(i<=graph.Size())
		{
			Vertex t=min(unvisited);
			Vertex v=getvertex(t.name);
			unvisited.Delete(t);
			ob<Edge> etemp=v.edgeset.top ;
			while(etemp.data!=null)
			{

				n=v.count+etemp.data.cost;
				if(n<etemp.data.end.count)
				{
					etemp.data.end.count=n;
					etemp.data.end.path=new MyLinkedList<Vertex>();
				    etemp.data.end.path.Insert(v);
				    MyLinkedList<Vertex> inverse=new MyLinkedList<Vertex>();
				    ob<Vertex> temp2=new ob<Vertex>();
				    temp2=v.path.top;
				    while(temp2.data!=null)
				    {
				    	inverse.Insert(temp2.data);
				    	temp2=temp2.next;
				    }
				    etemp.data.end.path.Insertall(inverse);
				   // System.out.println("path to "+etemp.data.end.name+" from "+src.name);
				    //ob<Vertex> tm=etemp.data.end.path.top;
					//while(tm.data!=null)
					//{
					//	System.out.println(tm.data.name);
					//	tm=tm.next;
					//}

				}

				etemp=etemp.next;
			}
			i++;
		}
	}
	int ans;
	ans=graph.getMember(dst).count;
		return ans;
}
}
