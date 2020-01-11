import java.util.ArrayList;

public class Edge implements Comparable<Edge> {
	String name;
	Vertex v1;
	Vertex v2;
	double weight;

  boolean visited;
	public Edge(Vertex one, Vertex two, String name) {
		this.name = name;
		this.v1 = one;

		this.v2 = two;
  
		this.weight = setWeight(v1, v2);
       this.visited=false;
	}

	public Vertex getNeighbor(Vertex current) {

		if (!(current.equals(v1) || current.equals(v2))) {

			return null;

		}

		return (current.equals(v1)) ? v2 : v1;

	}

	public Vertex getOne() {

		return this.v1;

	}

	public Vertex getTwo() {

		return this.v2;

	}

	public double getWeight() {

		return this.weight;

	}
	

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	

	public static double setWeight(Vertex one, Vertex two) {
		double R = 6371; // Radius of the earth in km
		double dLat = deg2rad(two.latitude - one.latitude); // deg2rad below
		double dLon = deg2rad(two.longditude - one.longditude);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(one.latitude))
				* Math.cos(deg2rad(two.latitude)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c; // Distance in km
		return d * 1000;
	}

@Override
	public int compareTo(Edge anotherEdge) {
	    if(this.weight<anotherEdge.weight){
	    	return -1;
	    }
	    if(this.weight==anotherEdge.weight){
	    	return 0;
	    }else{
	    	return 1;
	    }
	    
	}
	public String toString() {

		return "({" + v1 + ", " + v2 + "}, " + weight + ")";

	}

	public boolean equals(Object other) {

		if (!(other instanceof Edge)) {

			return false;

		}

		Edge e = (Edge) other;

		return e.v1.equals(this.v1) && e.v2.equals(this.v2);

	}

}