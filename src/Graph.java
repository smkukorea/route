import java.util.*;

public class Graph {

	HashMap<String, Vertex> vertices;
	HashMap<String, Edge> edges;

	public Graph() {
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<String, Edge>();
	}

	public Graph(ArrayList<Vertex> vertices) {
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<String, Edge>();

		for (Vertex v : vertices) {
			this.vertices.put(v.getName(), v);
		}

	}


	public boolean addEdge(Vertex one, Vertex two, double weight, String name) {
		if (one.equals(two)) {
			return false;
		}

		// ensures the Edge is not in the Graph
		Edge e = new Edge(one, two, name);
		if (edges.containsKey(e.name)) {
			return false;
		}

		// and that the Edge isn't already incident to one of the vertices
		else if (one.containsNeighbor(e) || two.containsNeighbor(e)) {
			return false;
		}

		edges.put(e.name, e);
		one.addNeighbor(e);
		two.addNeighbor(e);
		return true;
	}

	
	public boolean containsEdge(Edge e) {
		if (e.getOne() == null || e.getTwo() == null) {
			return false;
		}

		return this.edges.containsKey(e.hashCode());
	}

	
	public boolean containsVertex(Vertex vertex) {
		return this.vertices.get(vertex.getName()) != null;
	}

	
	public Vertex getVertex(String label) {
		return vertices.get(label);
	}

	
	public boolean addVertex(Vertex vertex) {

		vertices.put(vertex.getName(), vertex);
		return true;
	}


	
	public Set<String> vertexKeys() {
		return this.vertices.keySet();
	}


	public Set<Edge> getEdges() {
		return new HashSet<Edge>(this.edges.values());
	}

}
