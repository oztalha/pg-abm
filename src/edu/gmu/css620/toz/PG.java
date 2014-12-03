package edu.gmu.css620.toz;

import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.field.grid.SparseGrid2D;
import sim.util.Double2D;

public class PG extends SimState {

	private static final long serialVersionUID = 1L;
	public SparseGrid2D world;
	//public Continuous2D eurasia;
	
	public int gridWidth = 516/4; // width of the space
	public int gridHeight = 486/4; // Height of the space
	public int n = 100; //number of agents
	
	public PG(long seed) {super(seed);}
	public void start() {
		super.start();
		//eurasia = new Continuous2D(gridWidth*gridHeight/100, gridWidth, gridHeight);
		world = new SparseGrid2D(gridWidth, gridHeight);
		for(int i=0;i<n;i++){
			Agent a = new Agent();
			int x = random.nextInt(gridWidth);
			int y = random.nextInt(gridHeight); 
			world.setObjectLocation(a, x, y);
			//eurasia.setObjectLocation(a, new Double2D(x, y));
			schedule.scheduleRepeating(a);
		}
	}
	
	public static void main(String[] args) {
		doLoop(PG.class, args);
		System.exit(0);
	}

	
}
