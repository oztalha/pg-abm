package edu.gmu.css620.toz;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.grid.SparseGridPortrayal2D;
import sim.portrayal.simple.ImagePortrayal2D;
import sim.util.Bag;

public class GUI extends GUIState {

	public Display2D display;
	public JFrame displayFrame;
	SparseGridPortrayal2D agentsPortrayal = new SparseGridPortrayal2D();
	
	public GUI() {super(new PG(System.currentTimeMillis()));}
	public GUI(SimState state) {super(state);}
	public static String getName() { return "PG-ABM: An ABM of Politogenesis"; }
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		Console c = new Console(gui);
		c.setVisible(true);
	}

	public void start() {
		super.start();
		setupPortrayals();
	}
	
	public void load(SimState state) {
		super.load(state);
		setupPortrayals();
	}
	
	public void setupPortrayals() {
		PG pg = (PG) state;
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		 
		// tell the portrayals what to portray and how to portray them
		// http://cs.gmu.edu/~eclab/projects/mason/docs/howto.html#display2d
		agentsPortrayal.setField(pg.world);
		ImageIcon eurasia = new ImageIcon("eurasia.gif");
		int dimension = (int)Math.min(pg.world.getWidth(),pg.world.getHeight());
		agentsPortrayal.setPortrayalForAll(new ImagePortrayal2D(eurasia, dimension));
		display.attach(agentsPortrayal, "Eurasia");
		
		Bag agents = pg.world.getAllObjects();
		for (Object a : agents) {
			float red = (float)1.0;                              
            float green = (float)0.0;
            float blue = (float)0.0;
            float alpha = (float)1.0;
            agentsPortrayal.setPortrayalForObject ((Agent)a, new sim.portrayal.simple.OvalPortrayal2D (new Color(red,green,blue,alpha)));
		}
		
		// reschedule the displayer
        display.reset(); 
        display.setBackdrop(null);
        display.repaint();
	}
	
	public void init(Controller c) {
		super.init(c);
		display = new Display2D(600,600,this);
		display.setClipping(false);
		displayFrame = display.createFrame();
		displayFrame.setTitle("PG-ABM");
		c.registerFrame(displayFrame); // so the frame appears in the "Display" list
		displayFrame.setVisible(true);
		display.attach(agentsPortrayal, "Euroasia");
	}
	
	public void quit() {
		super.quit();
		if (displayFrame!=null) displayFrame.dispose();
		displayFrame = null;
		display = null;
	}
}
