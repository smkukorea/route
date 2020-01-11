import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.Element;

public class Mstt {
	public static ArrayList<Edge> Mst(Graph graph) {

	

//List<Object> list = Arrays.asList(graph.vertices.values().toArray());
ArrayList<Vertex> arrayList = new ArrayList(Arrays.asList(graph.vertices.values().toArray()));
	DisjointSet d = new DisjointSet(arrayList);
	ArrayList<Edge> edges =  new ArrayList(Arrays.asList(graph.edges.values().toArray()));
	 ArrayList<Edge> tree = new ArrayList<Edge>();
	for(Edge e:edges)
	{
		Vertex u = e.v1;
		Vertex v = e.v2;
		if (d.find(u.getNode()) != d.find(v.getNode())) {
		
			tree.add(e);

			/* Union them in the tree */
			d.union(u.getNode(), v.getNode());
		}
	}
	
	return tree;
	}
}
