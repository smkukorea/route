import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.HTMLDocument.Iterator;
//seungmin kuk
public class Map extends JComponent implements ActionListener, ChangeListener {

	static String from;
	static String to;
	JPanel panel1;
	int draw = 10;
	JButton type1;
	JButton type2;
      static int show;
      static int choice;
	JButton fire;
	JLabel types;
	JLabel velocity;
	JLabel angle;
	int launchVelocity = 0;
	int launchAngle = 0;
	JSlider velocitySlider;
	JSlider angleSlider;
	boolean launch = false;
	boolean click = false;
	JTextField field;
	JTextField field2;
     static String fileName;
	public Map() {
		setLayout(null);
		panel1 = new JPanel();

		panel1.setBounds(0, 0, 800, 30);
		panel1.setBorder(getBorder());
		panel1.setBackground(Color.green);

		velocity = new JLabel("From");
		panel1.add(velocity);

		field = new JTextField("starting point");
		field.addActionListener(this);
		panel1.add(field);

		angle = new JLabel("To");
		panel1.add(angle);

		field2 = new JTextField("destination");
		field2.addActionListener(this);
		panel1.add(field2);

		type1 = new JButton("Show Shorest Path");
		type1.addActionListener(this);
		panel1.add(type1);
		// type2
		type2 = new JButton("Show MST");
		type2.addActionListener(this);
		panel1.add(type2);

		add(panel1);
	}

	public static void main(String[] args) throws FileNotFoundException {

		  Scanner sc=new Scanner(System.in);
		  Scanner c=new Scanner(System.in);
	      System.out.println("Enter File name:");
	      fileName=sc.nextLine();
		Map canvas = new Map();
		  System.out.println("Do you want to see map: (type '1' see)");
		  show=c.nextInt();
			Graph graph;
			graph = createFromFile(fileName);

		if(show==1){
		JFrame frame = new JFrame("Map");
		frame.add(canvas);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		}else{
			System.out.println("Type  '0 use shortest path function. Type '1' to use MST function: ");
			
			choice=sc.nextInt();
			if(choice==0){
				
				Scanner s=new Scanner(System.in);
				System.out.println("Type starting position: ");
				from=s.nextLine();
				System.out.println(" ");
				System.out.println("Type destination: ");
				to=s.nextLine();
				Dijkstra d = new Dijkstra(graph, from);
			
				System.out.print("Path is:  ");
				System.out.println(d.getPathTo(to));
				System.out.print("Distance is:  ");
				//convert to miles
				System.out.println(d.getDistanceTo(to)/1.6);
			}
			if(choice==1){
				ArrayList<Edge> e = Mstt.Mst(graph);
				System.out.print("roads traveled are: ");
				
				for (Edge value : e) {
				
                 System.out.print("["+value.name+"] "); 
				}
				}
			}
		}
			
	

	@Override
	public void paintComponent(Graphics g) {

		try {

			g.setColor(Color.CYAN);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			Graph graph;
			graph = createFromFile(fileName);

		

			// finds minimum and maximum latitude and londitude
			double latMax = 0, latMin = 0, lonMax = 0, lonMin = 0;
			for (Vertex value : graph.vertices.values()) {
				if (latMax == 0) {
					latMax = value.latitude;
					latMin = value.latitude;
					lonMax = value.longditude;
					lonMin = value.longditude;
				} else {
					if (latMax < value.latitude) {
						latMax = value.latitude;
					}
					if (latMin > value.latitude) {
						latMin = value.latitude;
					}
					if (lonMax < value.longditude) {
						lonMax = value.longditude;
					}
					if (lonMin > value.longditude) {
						lonMin = value.longditude;
					}
				}

			}
			double xRatio = (latMax - latMin) / getWidth();
			double yRatio = (lonMax - lonMin) / (getHeight()  );

			// draw lines
			for (Edge value : graph.edges.values()) {
				double x1;
				double x2;
				double y1;
				double y2;

				x1 = (value.v1.latitude - latMin) / xRatio;
				x2 = (value.v2.latitude - latMin) / xRatio;
	
				y1 = (lonMax - value.v1.longditude) / yRatio;
				y2 = (lonMax - value.v2.longditude) / yRatio;

				g.drawLine((int) x1, (int) y1 , (int) x2, (int) y2) ;

			}

			if(draw==0){
				Dijkstra d = new Dijkstra(graph, from);
				for (int i = 0; i < d.getPathTo(to).size() - 1; i++) {
					double x1;
					double x2;
					double y1;
					double y2;
					x1 = (d.getPathTo(to).get(i).latitude - latMin) / xRatio;
					x2 = (d.getPathTo(to).get(i + 1).latitude - latMin) / xRatio;
					y1 = (d.getPathTo(to).get(i).longditude - lonMin) / yRatio;
					y1 = ( lonMax-d.getPathTo(to).get(i).longditude) / yRatio;
					y2 = ( lonMax-d.getPathTo(to).get(i + 1).longditude) / yRatio;
	
					g.setColor(Color.RED);
                  if(i==1){
                	  g.drawString(from, (int)x1, (int)y1);
                	  
                  }
                  if(i==d.getPathTo(to).size() - 2){
                	  g.drawString(to, (int)x1, (int)y1);
                  }
					g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
					
				}
			
				System.out.print("Path is:  ");
				System.out.println(d.getPathTo(to));
				System.out.print("Distance is:  ");
				//convert to miles
				System.out.println(d.getDistanceTo(to)/1.6);
			}

			if (draw == 1) {

				ArrayList<Edge> e = Mstt.Mst(graph);
				System.out.print("roads traveled are: ");
				for (Edge value : e) {
					double x1;
					double x2;
					double y1;
					double y2;

					x1 = (value.v1.latitude - latMin) / xRatio;
					x2 = (value.v2.latitude - latMin) / xRatio;
					// y1 = (value.v1.longditude - lonMin) / yRatio;
					// y2 = (value.v2.longditude - lonMin) / yRatio;
					y1 = (lonMax - value.v1.longditude) / yRatio;
					y2 = (lonMax - value.v2.longditude) / yRatio;

					g.setColor(Color.pink);
					g.drawLine((int) x1, (int) y1 , (int) x2, (int) y2);
				
                 System.out.print("["+value.name+"] "); 
				}
			
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Graph createFromFile(String filename) throws FileNotFoundException {

		Graph graph = new Graph();
		File text = new File(filename);
		Scanner s = new Scanner(text);

		while (s.hasNextLine()) {

			Scanner xb = new Scanner(s.nextLine());
			String category = xb.next();
			String name = xb.next();

			if (category.equals("i")) {

				double longditude = Double.parseDouble(xb.next());
				double latitude = Double.parseDouble(xb.next());
				Vertex vertex = new Vertex(longditude, latitude, name);
				graph.addVertex(vertex);
			}
			if (category.equals("r")) {
				String v1 = xb.next();

				String v2 = xb.next();
				graph.addEdge(graph.getVertex(v1), graph.getVertex(v2),
						Edge.setWeight(graph.getVertex(v1), graph.getVertex(v1)), name);

			}

		}

		return graph;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == field) {
			from = field.getText();

		}
		if (e.getSource() == field2) {
			to = field.getText();

		}
		if (e.getSource() == type1) {
			from = field.getText();
			to = field2.getText();
			System.out.println(from);
			draw = 0;
			repaint();
		}
		if (e.getSource() == type2) {
			draw = 1;
			repaint();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

}
