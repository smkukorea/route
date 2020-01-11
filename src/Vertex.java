import java.util.ArrayList;

public class Vertex {
	public String name;
	public double longditude;
	public double latitude;
	private ArrayList<Edge> neighborhood;
	public String root;
	private Node n;

	public Vertex(double longditude2, double latitude2, String name) {
		this.longditude = longditude2;
		this.latitude = latitude2;
		this.name = name;
		this.neighborhood = new ArrayList<Edge>();
		this.root = name;

	}

	public void setNode(Node n) {
		this.n = n;
	}

	public Node getNode() {
		return this.n;
	}

	public void addNeighbor(Edge edge) {

		if (this.neighborhood.contains(edge)) {

			return;

		}

		this.neighborhood.add(edge);

	}

	public boolean containsNeighbor(Edge other) {

		return this.neighborhood.contains(other);

	}

	public Edge getNeighbor(int index) {

		return this.neighborhood.get(index);

	}

	Edge removeNeighbor(int index) {

		return this.neighborhood.remove(index);

	}

	public int getNeighborCount() {

		return this.neighborhood.size();

	}

	public String getName() {

		return this.name;

	}

	public String toString() {

		return "Vertex " + name;

	}

	public boolean equals(Object other) {

		if (!(other instanceof Vertex)) {

			return false;

		}

		Vertex v = (Vertex) other;
		return this.name.equals(v.name);

	}

	public ArrayList<Edge> getNeighbors() {

		return new ArrayList<Edge>(this.neighborhood);

	}

}
