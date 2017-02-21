package slotMachineGUI_CW;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import slotMachineGUI_CW.GUI.event;

//import MainFrame.event;

public class Reel extends JFrame {

	// create a array slot with the size of 7
	// this array's index holds the information about images
	static Symbol[] slot = new Symbol[7];
	// private ArrayList <Symbol> slot = new ArrayList<Symbol>() ;

	private static boolean mouse;

	// after calling constructor create and initialised the 6 objects for 6
	// images
	public Reel() {
		try {

			Symbol cherry = new Symbol();
			cherry.setImage("cherry");
			cherry.setValue(2);
			slot[1] = cherry;

			Symbol lemon = new Symbol();
			lemon.setImage("lemon");
			lemon.setValue(3);
			// slot.add(lemon);
			slot[2] = lemon;

			Symbol plum = new Symbol();
			plum.setImage("plum");
			plum.setValue(4);
			// slot.add(plum);
			slot[3] = plum;

			Symbol watermelon = new Symbol();
			watermelon.setImage("watermelon");
			watermelon.setValue(5);
			// slot.add(watermelon);
			slot[4] = watermelon;

			Symbol bell = new Symbol();
			bell.setImage("bell");
			bell.setValue(6);
			// slot.add(bell);
			slot[5] = bell;

			Symbol seven = new Symbol();
			seven.setImage("redseven");
			seven.setValue(7);
			// slot.add(seven);
			slot[6] = seven;

		} catch (ArrayIndexOutOfBoundsException aiob) {
			System.out.println("error while creating 6 objects");
		}

		// call spin method what shuffle the and retur the slot array
		spin();

	}

	// to shuffle the array
	public Symbol[] spin() {
		try {

			Random rnd = ThreadLocalRandom.current();
			for (int i = slot.length - 1; i > 0; i--) {
				int index = rnd.nextInt(i + 1);
				// Simple swap
				Symbol a = slot[index];
				slot[index] = slot[i];
				slot[i] = a;
			}

		} catch (InputMismatchException ex) {
			System.out.println("error while array shuffling missmatching");
		} catch (NullPointerException e) {
			System.out.println("error while array shuffling");
		}
		return slot;
	}

}