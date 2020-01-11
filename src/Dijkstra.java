import java.util.*;

//seungmin kuk
public class Dijkstra {
    
    private Graph graph;
    private static String initialVertexLabel;
    private HashMap<String, String> predecessors;
    private HashMap<String, Integer> distances; 
    private PriorityQueue<Vertex> availableVertices;
    private HashSet<Vertex> visitedVertices; 
   
    
 
    public Dijkstra(Graph graph, String initialVertexLabel){
        this.graph = graph;
        Set<String> vertexKeys = this.graph.vertexKeys();
        
        if(!vertexKeys.contains(initialVertexLabel)){
            throw new IllegalArgumentException("The graph must contain the initial vertex.");
        }
        
        this.initialVertexLabel = initialVertexLabel;
        this.predecessors = new HashMap<String, String>();
        this.distances = new HashMap<String, Integer>();
        this.availableVertices = new PriorityQueue<Vertex>(vertexKeys.size(), new Comparator<Vertex>(){
            
            public int compare(Vertex one, Vertex two){
                int weightOne = Dijkstra.this.distances.get(one.getName());
                int weightTwo = Dijkstra.this.distances.get(two.getName());
                return weightOne - weightTwo;
            }
        });
        
        this.visitedVertices = new HashSet<Vertex>();
        
        
        for(String key: vertexKeys){
            this.predecessors.put(key, null);
            this.distances.put(key, Integer.MAX_VALUE);
        }
        
        
  
        this.distances.put(initialVertexLabel, 0);
        
       
        Vertex initialVertex = this.graph.getVertex(initialVertexLabel);
        ArrayList<Edge> initialVertexNeighbors = initialVertex.getNeighbors();
        for(Edge e : initialVertexNeighbors){
            Vertex other = e.getNeighbor(initialVertex);
            this.predecessors.put(other.getName(), initialVertexLabel);
            this.distances.put(other.getName(), (int) e.getWeight());
            this.availableVertices.add(other);
        }
        
        this.visitedVertices.add(initialVertex);
        
        //now apply Dijkstra's algorithm to the Graph
        processGraph();
        
    }
  
    private void processGraph(){
        
      
        while(this.availableVertices.size() > 0){
            
            //pick the cheapest vertex
            Vertex next = this.availableVertices.poll();
            int distanceToNext = this.distances.get(next.getName());
            
            //and for each available neighbor of the chosen vertex
            List<Edge> nextNeighbors = next.getNeighbors();     
            for(Edge e: nextNeighbors){
                Vertex other = e.getNeighbor(next);
                if(this.visitedVertices.contains(other)){
                    continue;
                }
               
                int currentWeight = this.distances.get(other.getName());
                int newWeight = (int) (distanceToNext + e.getWeight());
                
                if(newWeight < currentWeight){
                    this.predecessors.put(other.getName(), next.getName());
                    this.distances.put(other.getName(), newWeight);
                    this.availableVertices.remove(other);
                    this.availableVertices.add(other);
                }
                
            }
            
            // finally, mark the selected vertex as visited 
            // so we don't revisit it
            this.visitedVertices.add(next);
        }
    }
    
    
 
    boolean sPath=true;
    public List<Vertex> getPathTo(String destinationLabel){
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        path.add(graph.getVertex(destinationLabel));
        
        while(!destinationLabel.equals(this.initialVertexLabel)){
            Vertex predecessor = graph.getVertex(this.predecessors.get(destinationLabel));
             if(predecessor==null){
            	 System.out.print("There is no path to ");
            	 sPath=false;
            	break;
             }
             
            destinationLabel = predecessor.getName();
            path.add(0, predecessor);
        }
        
        return path;
    }
    
    
   
    public double getDistanceTo(String destinationLabel){
    	if(sPath){
        return this.distances.get(destinationLabel).doubleValue()/1000;
    }else{
    	System.out.println("distance not possible will print 0");
    	return 0;
    }
    
    
  
}
}