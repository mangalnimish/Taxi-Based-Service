
import java.util.Scanner;

public class TaxiService{
	Graph gr=new Graph();
	public TaxiService() {
	}
	public Taxi taxitocust(String cust,int t)
	{
		int a,min=0;
		int c=0;
		Taxi ans=gr.taxi.top.data;
		if(gr.isvertex(cust))
		{
			ob<Taxi> temp=gr.taxi.top;
			while(temp.data!=null)
			{
				Vertex src=gr.getvertex(temp.data.pos);
				if(temp.data.time<=t)
				{
					Vertex b=gr.getvertex(cust);
					//System.out.println("taxi name-"+temp.data.name);
					a=gr.shortestpath(src, b);
					System.out.print("Path of Taxi "+temp.data.name+": " );
					ob<Vertex> temp2=b.path.top;

					while(temp2.data!=null)
					{
						System.out.print(temp2.data.name+", ");
						temp2=temp2.next;
					}
					System.out.println(cust+". time taken is "+a+" units.");
					if(c==0)
					{
						min=a;
						c++;
					}
					else if(min>=a)
					{
						min=a;
						ans=temp.data;
					}
				}
				temp=temp.next;
			}
		}
		ans.time=t+gr.shortestpath(gr.getvertex(ans.pos), gr.getvertex(cust));

		return ans;
	}

	public void performAction(String actionMessage) {
		System.out.println("action to be performed: " + actionMessage);
		//....
		Scanner s=new Scanner(actionMessage);
		String str=s.next();
		switch(str)
		{
		case "edge":
		{

			String src="";
			String dst="";
			int c;
			try
			{
				src=s.next();
				dst=s.next();
				c=s.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Error: Wrong input format");
				break;
			}
			boolean b1=gr.isvertex(src);
			boolean b2=gr.isvertex(dst);
			if(b1 && !b2)
			{
				Vertex n=gr.getvertex(src) ;
				Vertex temp2=new Vertex() ;
				temp2.name=dst;
				Edge e=new Edge();
				e.end=temp2;
				e.cost=c;
				n.edgeset.Insert(e);
				Edge e2= new Edge();
				e2.end=n;
				e2.cost=c;
				temp2.edgeset.Insert(e2);
				gr.graph.Insert(temp2);
			}
			else if(b2 && ! b1)
			{
				Vertex n=gr.getvertex(dst);
				Vertex temp2=new Vertex();
				temp2.name=src;
				Edge e=new Edge();
				e.end=temp2;
				e.cost=c;
				n.edgeset.Insert(e);
				Edge e2= new Edge();
				e2.end=n;
				e2.cost=c;
				temp2.edgeset.Insert(e2);
				gr.graph.Insert(temp2);
			}
			else if(b1 && b2)
			{
				Vertex n=gr.getvertex(dst);
				Vertex temp2=gr.getvertex(src);
				Edge e=new Edge();
				e.end=temp2;
				e.cost=c;
				n.edgeset.Insert(e);
				Edge e2= new Edge();
				e2.end=n;
				e2.cost=c;
				temp2.edgeset.Insert(e2);
			}
			else
			{
				Vertex sr=new Vertex();
				Vertex d=new Vertex();
				sr.name=src;
				d.name=dst;
				Edge temp1=new Edge();
				temp1.end=d;
				temp1.cost=c ;
				Edge temp2=new Edge();
				temp2.end=sr ;
				temp2.cost=c ;
				sr.edgeset.Insert(temp1) ;
				d.edgeset.Insert(temp2) ;
				gr.graph.Insert(d) ;
				gr.graph.Insert(sr) ;
			}
			break;
		}
		case "taxi":
		{
			String name="";
			String pos="";
			try
			{
				name=s.next();
				pos=s.next();
			}
			catch(Exception e)
			{
				System.out.println("Error: wrong input format");
				break;
			}
			if(gr.isvertex(pos))
			{
				Taxi temp=new Taxi();
				temp.name=name;
				temp.pos=pos;
				temp.time=0;
				gr.taxi.Insert(temp);
			}
			else
			{
				System.out.println("Error: Given position doesn't exist");
			}
			break;
		}
		case "customer":
		{
			String src="";
			String dst="";
			int time;
			try
			{
				src=s.next();
				dst=s.next();
				time=s.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Error: Wrong input format");
				break;
			}
			boolean sr=gr.isvertex(src);
			boolean ds=gr.isvertex(dst);
			if(sr && ds)
			{
				Taxi taxi=taxitocust(src,time);
				System.out.println("** Choose taxi "+taxi.name+" to service customer request ***");
				taxi.pos=dst;
				Vertex b=gr.getvertex(dst);
				int cost=gr.shortestpath(gr.getvertex(src),b);
				taxi.time=taxi.time+cost;
				System.out.print("path of customer: ");
				ob<Vertex> temp=b.path.top;
				while(temp.data!=null)
				{
					System.out.print(temp.data.name+", ");
					temp=temp.next;
				}
				System.out.println(dst+ ". Time taken is "+cost+" units.");
			}
			else if(sr!= true && ds==true)
			{
				System.out.println("Error: "+src+" doesn't exist");
			}
			else if(sr== true && ds!=true)
			{
				System.out.println("Error: "+dst+" doesn't exist");
			}
			else
			{
				System.out.println("Error: "+dst+" "+src+" doesn't exist");
			}
			break;
		}
		case "printTaxiPosition":
		{
			int a;
			try{
				a=s.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Error: wrong input format");
				break;
			}
			ob<Taxi> temp=gr.taxi.top;
			if(gr.availability(a)!=true)
			{
				System.out.println("No taxi available");
				break;
			}
			while(temp.data!=null)
			{
				if(temp.data.time<=a)
				{
					System.out.println("Taxi "+temp.data.name+": "+temp.data.pos);
				}
				temp=temp.next;
			}
			break;
		}
		default:{
			System.out.println("Error: Wrong Query Entered");
			break;
		}
		}
		s.close();

	}
}
