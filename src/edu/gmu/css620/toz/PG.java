package edu.gmu.css620.toz;

import sim.engine.SimState;

public class PG extends SimState {

	private static final long serialVersionUID = 1L;
	public PG(long seed) {super(seed);}
	public void start() {super.start();}
	
	public static void main(String[] args) {
		doLoop(PG.class, args);
		System.exit(0);
	}

	
}
