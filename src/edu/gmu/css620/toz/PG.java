package edu.gmu.css620.toz;

import sim.engine.SimState;
import sim.field.grid.SparseGrid2D;

public class PG extends SimState {

	private static final long serialVersionUID = 1L;
	public SparseGrid2D world;
	public int gridWidth = 100; // width of the space
	public int gridHeight = 100; // Height of the space
	public int n = 100; //number of agents
	
	public PG(long seed) {super(seed);}
	public void start() {
		super.start();
		world = new SparseGrid2D(gridWidth, gridHeight);
		for(int i=0;i<n;i++){
			Agent a = new Agent();
			int x = random.nextInt(gridWidth);
			int y = random.nextInt(gridHeight); 
			world.setObjectLocation(a, x, y);
			schedule.scheduleRepeating(a);
		}
	}
	
	public static void main(String[] args) {
		doLoop(PG.class, args);
		System.exit(0);
	}

	
}
