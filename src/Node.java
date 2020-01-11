class Node {
  int rank;      
  int index;     
  Node parent;

  public Node(int r, int i, Node p) {
    this.rank = r;
    this.index = i;
    this.parent = p;
  }
}