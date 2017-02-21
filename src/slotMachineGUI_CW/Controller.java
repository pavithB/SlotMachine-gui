package slotMachineGUI_CW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import javax.swing.border.Border;

import com.sun.javafx.geom.Dimension2D;

//import slotMachineGUI_CW.Reel.event;

public class Controller extends JFrame {
	private  int betCredit, resetValue, totalWin, totallose, totalbet, winCredit, no3Equal, no2Equal, noSpin,
			noLose, reel1Value, finalReelValue, coins, reel2Value, reel3Value, lostCredit;
	private  int credit = 10;
	private JButton addCoin, betOne, betMax, spin, statistics, reset;
	private JLabel reel1, reel2, reel3, creditArea, betArea, result, winArea;
	private ImageIcon reel1i, reel2i, reel3i;
	private JPanel bgPanel, resultLabel, decopanel;
	private JSeparator upreel, resultSep1, resultSep2;
	private static boolean mouse;
	static Symbol mostRecentValue;

	GridBagConstraints gbc = new GridBagConstraints();
	Border reelBorder = BorderFactory.createLineBorder(Color.black, 5);
	Border randBorder = BorderFactory.createLineBorder(randomColor(), 5);
	Border panelBorder = BorderFactory.createLineBorder(Color.black, 3);

	// create a object of the reel class to access the slot array
	Reel r1 = new Reel();
	Reel r2 = new Reel();
	// Reel r3 = new Reel();

	public Controller() {
		// constructor will call the method gui() every time object is created
		// from this class
		gui();

	}

	// gui methos to create the gui to intract with user and
	public void gui() {

		try {

			decopanel = new JPanel(new GridLayout(1, 1));
			// decopanel.setSize();
			bgPanel = new JPanel(new GridBagLayout());
			// 179, 179, 179
			bgPanel.setBackground(new Color(166, 166, 166));
			// bgPanel.setBackground(Color.darkGray);

			bgPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
			// set border properties
			Border labelBorder = BorderFactory.createLineBorder(Color.black, 2);

			// set space between two componunts
			gbc.insets = new Insets(3, 3, 3, 3);
			// gbc.gridheight=3;
			// gbc.gridwidth=3;
			gbc.anchor = GridBagConstraints.NORTHWEST;

			gbc.weighty = 2;
			// set layout properties using grid ag constraint (gbc)
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH;
			creditArea = new JLabel("\nCurrrent Credit:   " + Integer.toString(credit) + "\n\n"); // display
																									// the
																									// current
																									// credits
			creditArea.setHorizontalAlignment(SwingConstants.CENTER);
			creditArea.setBorder(BorderFactory.createTitledBorder(labelBorder, "-#C R E D I T _ A R E A"));
			bgPanel.add(creditArea, gbc);

			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH;
			winArea = new JLabel("Spin The Reel To *WIN* "); // display the
																// winning
																// credits
			winArea.setHorizontalAlignment(SwingConstants.CENTER);
			winArea.setBorder(BorderFactory.createTitledBorder(labelBorder, "-# W I N _ A R E A"));
			bgPanel.add(winArea, gbc);

			gbc.gridx = 3;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH; // display the current bet
			betArea = new JLabel("\nBet Credit:   " + Integer.toString(betCredit) + "\n\n");
			betArea.setHorizontalAlignment(SwingConstants.CENTER);
			betArea.setBorder(BorderFactory.createTitledBorder(labelBorder, "-# B E T _ A R E A"));
			bgPanel.add(betArea, gbc);

			JSeparator upreel = new JSeparator(); /// seperrator to seperate the
													/// componunts

			upreel.setPreferredSize(new Dimension(1, 1));
			gbc.fill = GridBagConstraints.HORIZONTAL;

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 10;
			gbc.fill = GridBagConstraints.BOTH;
			bgPanel.add(upreel, gbc);

			gbc.gridx = 1;
			gbc.gridy = 2; // create the first reel
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH;
			reel1i = new ImageIcon(r1.slot[2].getImage());
			reel1 = new JLabel(reel1i);
			// reel1.setBorder(BorderFactory.createTitledBorder("REEL1"));
			reel1.setBorder(BorderFactory.createTitledBorder(reelBorder,
					"v a l u e: " + Integer.toString(r1.slot[2].getValue())));
			reel1.setOpaque(true);
			// reel1.setBackground(Color.yellow);
			reel1.setBackground(new Color(255, 215, 0));
			bgPanel.add(reel1, gbc);

			gbc.gridx = 2;
			gbc.gridy = 2;
			reel2i = new ImageIcon(r1.slot[2].getImage());
			reel2 = new JLabel(reel2i); // create the second reel
			gbc.fill = GridBagConstraints.BOTH;
			// reel2.setBackground(Color.yellow);
			// reel2.setBorder(BorderFactory.createTitledBorder("REEL2"));
			reel2.setBorder(BorderFactory.createTitledBorder(reelBorder,
					"v a l u e: " + Integer.toString(r1.slot[2].getValue())));
			reel2.setOpaque(true);
			// reel1.setBackground(Color.yellow);
			reel2.setBackground(new Color(255, 215, 0));
			bgPanel.add(reel2, gbc);

			gbc.gridx = 3;
			gbc.gridy = 2;
			reel3i = new ImageIcon(r1.slot[2].getImage());
			/// reel3.setBackground(Color.yellow);
			reel3 = new JLabel(reel3i);
			// reel3.setBorder(BorderFactory.createTitledBorder("REEL3"));
			// reel3.setBorder(BorderFactory.createTitledBorder(reelBorder,
			// "REEL3"));
			reel3.setBorder(BorderFactory.createTitledBorder(reelBorder,
					"v a l u e: " + Integer.toString(r1.slot[2].getValue())));
			reel3.setOpaque(true);
			// reel1.setBackground(Color.yellow); create the third reel
			reel3.setBackground(new Color(255, 215, 0));
			reel3.setSize(100, 100);
			gbc.fill = GridBagConstraints.BOTH;
			bgPanel.add(reel3, gbc);

			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridheight = 3;
			gbc.gridwidth = 1;
			gbc.weighty = 20;
			// gbc.ipadx=0;
			// gbc.ipady=0;
			gbc.fill = GridBagConstraints.BOTH;

			spin = new JButton("S P I N"); // create the spin button
			spin.addActionListener(new spinHandle());
			// spin.setBackground(null);
			bgPanel.add(spin, gbc);

			JSeparator resultSep1 = new JSeparator();
			resultSep1.setPreferredSize(new Dimension(1, 1));
			gbc.fill = GridBagConstraints.BOTH;
			// gbc.weighty = 2;
			gbc.gridx = 0;
			gbc.gridy = 6;
			gbc.gridwidth = 10;
			gbc.gridheight = 1;
			gbc.fill = GridBagConstraints.BOTH;
			bgPanel.add(resultSep1, gbc);

			resultLabel = new JPanel(new GridLayout(1, 1));

			// gbc.gridwidth=6;
			// gbc.weighty=20;
			// gbc.weightx=20;

			gbc.gridheight = 3;
			gbc.fill = GridBagConstraints.BOTH; //////////////////////////////////// create
												//////////////////////////////////// the
												//////////////////////////////////// label
												//////////////////////////////////// that
												//////////////////////////////////// shows
												//////////////////////////////////// the
												//////////////////////////////////// responss
			gbc.gridx = 0;
			gbc.gridy = 8;
			result = new JLabel("S P I N _ T H E _ L U C K Y _ R E E L");
			result.setHorizontalAlignment(SwingConstants.CENTER);
			result.setFont(new Font("calibri", Font.BOLD, 20));
			result.setBackground(Color.orange);
			result.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
			resultLabel.add(result);
			bgPanel.add(resultLabel, gbc);

			JSeparator resultSep2 = new JSeparator(); ///////////// seperator
			resultSep2.setPreferredSize(new Dimension());
			gbc.fill = GridBagConstraints.BOTH;

			gbc.weighty = 5;
			gbc.gridx = 0;
			gbc.gridy = 11;
			gbc.gridwidth = 10;
			gbc.gridheight = 1;
			gbc.fill = GridBagConstraints.BOTH;
			bgPanel.add(resultSep2, gbc);

			gbc.gridx = 1;
			gbc.gridy = 12;
			gbc.gridheight = 1;
			gbc.gridwidth = 1; ///////////////// button to bet oe credit per a
								///////////////// click
			gbc.fill = GridBagConstraints.BOTH;
			betOne = new JButton("Bet_One");
			betOne.addActionListener(new betOneHandle());
			bgPanel.add(betOne, gbc);

			gbc.gridx = 3;
			gbc.gridy = 12; //////////////// button to bet 3 credits per click
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH;
			betMax = new JButton("Bet_3");
			betMax.addActionListener(new betMaxHandle());
			bgPanel.add(betMax, gbc);

			gbc.gridx = 3;
			gbc.gridy = 13;
			gbc.gridheight = 1;
			gbc.gridwidth = 1; //////////////// button to add 1 credits to
								//////////////// current
								//////////////// credit per click
			gbc.fill = GridBagConstraints.BOTH;
			addCoin = new JButton("Add_Coin");
			addCoin.addActionListener(new addCoinHandle());
			bgPanel.add(addCoin, gbc);

			gbc.gridx = 1;
			gbc.gridy = 13;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH; ////////////////////// button to
												////////////////////// reset the
												////////////////////// current
												////////////////////// bet
			reset = new JButton("Reset");
			reset.addActionListener(new resetHandle());
			bgPanel.add(reset, gbc);

			gbc.gridx = 2;
			gbc.gridy = 13;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;

			gbc.fill = GridBagConstraints.BOTH; ////////////// button to view
												////////////// statistics window
			statistics = new JButton("Statistics");
			statistics.addActionListener(new statisticsHandle());
			bgPanel.add(statistics, gbc);

			//////////////////////////////////////////// create the menu bar
			JMenuBar menubar = new JMenuBar();
			setJMenuBar(menubar);

			JMenu file = new JMenu("FILE");
			menubar.add(file);
			JMenuItem restart = new JMenuItem("Restart");
			file.add(restart);
			JMenuItem exit = new JMenuItem("Exit");
			file.add(exit);

			JMenu statistics = new JMenu("Statistics");
			menubar.add(statistics);
			JMenuItem stat = new JMenuItem("View Stats");
			statistics.add(stat);
			JMenuItem saveStat = new JMenuItem("Save Stats");
			statistics.add(saveStat);

			JMenu help = new JMenu("Help");
			menubar.add(help);
			JMenuItem about = new JMenuItem("About");
			help.add(about);

			////////////////////// create the action listner for exit menu
			class exitAction implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					System.exit(0);

				}
			}
			exit.addActionListener(new exitAction());
			////////////////////// create action listner for statistic menu
			stat.addActionListener(new statisticsHandle());

			///////////////////////// create action listner for restart utton
			class restartAction implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					dispose();
					new Controller();
					// main(args);

				}
			}
			restart.addActionListener(new restartAction());

			////////////////////////////// create action lister forr save
			////////////////////////////// statistics
			////////////////////////////// button

			class saveStatAction implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					new StatisticsWindow();

				}
			}
			saveStat.addActionListener(new saveStatAction());

			/////////////// add the panel what holds the all the above
			/////////////// componunts to
			/////////////// the frame(window)
			// bgPanel.setSize(720,480);
			add(bgPanel);
			// add(decopanel);

			////////////////////////////////// set the frame size
			setSize(730, 480);
			////////////////////////////////// to set exit when clicked close
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			////////////////////////////////// make the frame visible
			setVisible(true);
			////////////////////////////////// pack everything iside frame
			pack();
			////////////////////////////////// le window open center of the
			////////////////////////////////// screen
			setLocationRelativeTo(null);
			////////////////////////////////// set the frame size
			setTitle(
					"-------------------------------------- L U C K Y _ R E E L [v 0.1] ------------------------------------");
			/////////////////////////////// to set the minimum size of the
			/////////////////////////////// window
			setMinimumSize(new Dimension(720, 480));

		} catch (Exception cc) {
			//System.out.println("");
		}

	}

	//////////////////////////////////////// create betone button action

	class betOneHandle implements ActionListener {
		public void actionPerformed(ActionEvent e2) {

			// if maximum value of the bet is 3 this also need to execute

			if (betCredit == 3) {

				result.setText("M A X I M U M _ V A L U E _ O F _ B E T _ I S _ 3");
				resultLabel.setBackground(new Color(255, 51, 51));
				result.setHorizontalAlignment(SwingConstants.CENTER);
				timer();
			} else {
				///////////////////// if no current credit is empty to bet
				///////////////////// sdisplay
				if (credit == 0) {
					result.setText("N O _ C R E D I T _ T O _ B E T");
					result.setHorizontalAlignment(SwingConstants.CENTER);
					resultLabel.setBackground(new Color(255, 51, 51));
					/// set delay to reset the displayed msg
					timer();
				} else if (credit < 0) {
					credit = 0;
				} else {
					// if use have the credit

					credit--;
					betCredit++;
					creditArea.setText("\nCurrrent Credit :            $" + Integer.toString(credit) + "\n\n");
					betArea.setText("\nBet Credit :            $" + Integer.toString(betCredit) + "\n\n");
				}

			}
		}
	}

	////////////////////////////// betmax button action
	class betMaxHandle implements ActionListener {
		public void actionPerformed(ActionEvent e3) {

			// if maximum value of the bet is 3 this also need to execute

			if (betCredit != 0) {
				////////////////// user trying to bet more than 3 credits

				result.setText("M A X I M U M _ V A L U E _ O F _ B E T _ I S _ 3");
				resultLabel.setBackground(new Color(255, 51, 51));
				result.setHorizontalAlignment(SwingConstants.CENTER);
				timer();
			} else {
				// if user dont have credits
				if (credit == 0) {
					result.setText("N O _ C R E D I T _ T O _ B E T");
					resultLabel.setBackground(new Color(255, 51, 51));
					result.setHorizontalAlignment(SwingConstants.CENTER);
					////////// to delay
					timer();

				} else if (credit < 3) {
					// if user trying to bet more than 3 credits
					result.setText("L O W _ C R E D I T _ U N A B L E _ T O _ B E T _ 3 _ C R E D I T");
					resultLabel.setBackground(new Color(255, 133, 51));
					result.setHorizontalAlignment(SwingConstants.CENTER);
					timer();
				} else if (credit < 0) {

					credit = 0;
				} else {
					credit = credit - 3;
					betCredit = betCredit + 3;
					if (credit < 0) {
						credit = 0;
					}
					/////// update the labels in gui
					creditArea.setText("\nCurrrent Credit :            $" + Integer.toString(credit) + "\n\n");
					betArea.setText("\nBet Credit :            $" + Integer.toString(betCredit) + "\n\n");

				}
			}
		}
	}

	//////////////// add coin button action
	class addCoinHandle implements ActionListener {
		// public void actionPerformed(ActionEvent e2) {
		// if (winCredit <= 0) {
		// result.setText("N O _ W I N I N G _ C O I N S _ T O _ A D D");
		// resultLabel.setBackground(new Color(255, 51, 51));
		// result.setHorizontalAlignment(SwingConstants.CENTER);
		//
		// timer();
		// } else {
		// credit++;
		// winCredit--;
		// creditArea.setText("\nCurrrent Credit : $" + Integer.toString(credit)
		// + "\n\n");
		// winArea.setText("\nWIN Credit : $" + Integer.toString(winCredit) +
		// "\n\n");
		//
		// }
		//
		// }
		public void actionPerformed(ActionEvent e2) {

			// if users current credit 0 make the game over
			if (credit == 0 && winCredit == 0 && betCredit == 0) {
				result.setText("G A M E _ O V E R");
				resultLabel.setBackground(new Color(255, 51, 51));
				result.setHorizontalAlignment(SwingConstants.CENTER);
				
			}else if(credit == 0){
								
				result.setText("C A N ' T _ A D D _ C O I N _ W H E N _ C R E D I T = 0");
				resultLabel.setBackground(new Color(255, 51, 51));
				result.setHorizontalAlignment(SwingConstants.CENTER);
				timer();
				 
			} else {
				// increment current credit by one per click
				credit++;
				coins++;
				creditArea.setText("Currrent Credit :            $" + Integer.toString(credit));
				// winArea.setText("\nWIN Credit : $" +
				// Integer.toString(winCredit) + "\n\n");

			}

		}
	}

	//////////////////// spin button action
	class spinHandle implements ActionListener {

		public void actionPerformed(ActionEvent e3) {

			//////////////////////// if user dont have any credit let the game
			//////////////////////// over
			if (credit == 0 && winCredit == 0 && betCredit == 0) {

				result.setText("G A M E _ O V E R !");
				result.setHorizontalAlignment(SwingConstants.CENTER);
				resultLabel.setBackground(new Color(255, 51, 51));

			} else if (betCredit == 0) {
				////////////////////////// when user have credit let him/her
				////////////////////////// spin the reels

				result.setText("P L A C E _ Y O U R _ B E T _ F I R S T");
				result.setHorizontalAlignment(SwingConstants.CENTER);
				resultLabel.setBackground(new Color(255, 51, 51));
				timer();

			} else {
				/////// increment the no of spin by one
				try {
					noSpin++;

					mouse = false;

					/////////// SwingUtilities to schedule our update code to
					/////////// run on
					/////////// the event dispatch thread.
					SwingUtilities.invokeLater(new Runnable() {
						//////////// run method will create a new tread
						public void run() {
							// call start method to spin the reels
							start(reel1);

						}

					});
					SwingUtilities.invokeLater(new Runnable() {

						public void run() {
							start(reel2);///////////////////////// create 3
											///////////////////////// thread
											///////////////////////// for 3
											///////////////////////// reels

						}

					});
					SwingUtilities.invokeLater(new Runnable() {

						public void run() {
							start(reel3);

						}

					});

				} catch (Exception threads) {
					//System.out.println("");
				}
			}
		}
	}

	//////// create reset button action
	class resetHandle implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			//////////// set bet credit 0 and add those credits to the current
			//////////// credit
			if (betCredit > 0) {
				credit = credit + betCredit;
				betCredit = 0;
				////// update the labels
				creditArea.setText("\nCurrrent Credit:  " + Integer.toString(credit) + "\n\n");
				creditArea.setHorizontalAlignment(SwingConstants.CENTER);
				betArea.setText("\nBet Credit:  " + Integer.toString(betCredit) + "\n\n");
				betArea.setHorizontalAlignment(SwingConstants.CENTER);
			}

		}
	}

	///////////// create action for statistics button
	class statisticsHandle implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			/////////////////////// user not alllowed to view the statistics
			/////////////////////// until 1 spin
			if (noSpin > 0) {
				try {
					// StatisticsWindow stat = new StatisticsWindow(totalWin ,
					// totalWlose , totalbet ,noWin , noSpin);
					StatisticsWindow stat = new StatisticsWindow(totalWin, noLose, lostCredit, no3Equal, no2Equal,
							noSpin);
					////////////// create the new window in a new class
					/////////////// pass the arguments to the that class
					////////////// constructor
					stat.setSize(720, 480);
					stat.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					stat.setAlwaysOnTop(true);
					stat.setVisible(true);
					stat.toFront();
					stat.setLocationRelativeTo(null);
					stat.pack();
					stat.setTitle(" S T A T _ W I N D O W");
				
					// stat.setExtendedState(stat);

					setAlwaysOnTop(false);

				} catch (Exception newwin) {
					//System.out.println("while create statistics window");
				}

			} else {

				result.setText("N O _ S T A T I S T I C S _ T O _ D I S P L A Y");
				result.setHorizontalAlignment(SwingConstants.CENTER);
				resultLabel.setBackground(new Color(255, 51, 51));
				timer();

			}
		}
	}

	public void timer() {
		//////////////////// timer to set a delay to the result label and set
		//////////////////// the default propoties of that label
		Timer t = new Timer(1500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				result.setText("S P I N _ T H E _ L U C K Y _ R E E L");
				resultLabel.setBackground(Color.white);
				// result.setHorizontalAlignment(SwingConstants.CENTER);
			}
		});
		t.setRepeats(false);
		t.start();
	}

	//////////////////////////////// this methos to update the gui using multi
	//////////////////////////////// threads
	public void start(JLabel label) {

		// SwingWorker is an alternative to using the Thread class, specifically
		// designed for Swing. It's an abstract class and it takes two template
		// parameters
		// first parameter make sure the threads state
		// 2nd parameter used to update the gui
		SwingWorker<Boolean, Symbol> worker = new SwingWorker<Boolean, Symbol>() {

			//// this method control the limit of the reeeel spin
			@Override
			protected Boolean doInBackground() throws Exception {
				// to make sure to did't call the method while its already
				// running
				if (mouse == false) {
					do {

						// The type we pass to publish() is determined
						// by the second template parameter.
						// generate a random index and pass it to publish method
						// then it passed in t process method
						Random ranNumIndex = new Random();
						int n = ranNumIndex.nextInt(5);

						publish(r1.slot[n]);

						// onluy for the test (win all time)

						try {
							Thread.sleep(100);
						} catch (Exception e) {
							System.out.println("eror while sleeping thread");

						}

						event e1 = new event();
						// Controller guiC = new Controller();

						// 1
						// Controller guiC = new Controller();
						label.addMouseListener(e1);

					} while (mouse != true);

				}
				// Here we can return some object of whatever type
				// we specified for the first template parameter.
				// (in this case we're auto-boxing 'true').
				return true;
			}

			// Can safely update the GUI from this method.
			protected void done() {
				// this method execute after reels are stop
				///////////////// we can check for the result here
				boolean status;
				try {
					// Retrieve the return value of doInBackground.
					status = get();
					//////////////// if 3 symbols get matched
					if (reel1Value == reel2Value && reel2Value == reel3Value) {
//						result.setText("You matched THREE symbols|You Won $" + betCredit * reel1Value);

						reel1.setBackground(new java.awt.Color(255, 51, 51)); // Highlights
																				// matched
																				// icons.
						reel2.setBackground(new java.awt.Color(255, 51, 51));
						reel3.setBackground(new java.awt.Color(255, 51, 51));

						finalReelValue = 1;

						timer();

						//////////////// if two symboles get matched
					} else if (reel1Value == reel2Value || reel1Value == reel3Value) {

						if (reel1Value == reel2Value) {
							reel1.setBackground(new java.awt.Color(255, 51, 51)); // Highlights
																					// matched
																					// icons.
							reel2.setBackground(new java.awt.Color(255, 51, 51));

							finalReelValue = 3;

						} else if (reel1Value == reel3Value) {
							reel1.setBackground(new java.awt.Color(255, 51, 51)); // Highlights
																					// matched
																					// icons.
							reel3.setBackground(new java.awt.Color(255, 51, 51));

							finalReelValue = 3;
						}
						result.setText("You matched TWO symbols !");
						timer();
						// result.setText("F R E E _ S P I N");
						// result.setHorizontalAlignment(SwingConstants.CENTER);
						// resultLabel.setBackground(randomColor());
						// timer();
						finalReelValue = 3;

					} else if (reel2Value == reel3Value) {
						// result.setText("You matched TWO symbols
						// ("+images.get(reel2).getDescription()+")!
						// +£"+df.format(getPrize(payout))+"!");
						result.setText("You matched TWO symbols !");
						reel2.setBackground(new java.awt.Color(255, 51, 51)); // Highlights
																				// matched
																				// icons.
						reel3.setBackground(new java.awt.Color(255, 51, 51));
						finalReelValue = 3;
						finalReelValue = 3;
						timer();

					} else {
						////////// if non of symbols get matched

						finalReelValue = 2;
						timer();
						if (credit == 0 && winCredit == 0) {
							result.setText("G A M E _ O V E R !");
							result.setHorizontalAlignment(SwingConstants.CENTER);
							resultLabel.setBackground(new Color(255, 51, 51));
						}

						// lblLost.setText("Lost: "+lose());
					}
					///////////////////// calculate the result using the reels
					///////////////////// symbols
					if (mouse == true) {

						// to always win
						// finalReelValue = 1;
						if (finalReelValue == 1) {
							int tempWinCredit = betCredit * reel1Value;
							result.setText("You matched THREE symbols|You Won $" + tempWinCredit);

							no3Equal++;
							totalWin = totalWin + (betCredit * reel1Value);
							winCredit = winCredit + (betCredit * reel1Value);
							credit = credit + (betCredit * reel1Value);
							betCredit = 0;

							creditArea.setText("\nCurrrent Credit :            $" + Integer.toString(credit));
							winArea.setText("WIN Credit                $" + Integer.toString(winCredit));
							betArea.setText("Bet Credit :            $" + Integer.toString(betCredit));

							finalReelValue = 0;

						} else if (finalReelValue == 2) {

							result.setText("You lose! " + betCredit + " credits");
							reel2.setBackground(new java.awt.Color(255, 51, 51));
							timer();

							noLose++;
							lostCredit = lostCredit + betCredit;
							betCredit = 0;
							betArea.setText("Bet Credit :            $" + Integer.toString(betCredit));
							finalReelValue = 0;

						} else if (finalReelValue == 3) {
							no2Equal++;

							Timer t2 = new Timer(1000, new ActionListener() {

								
								public void actionPerformed(ActionEvent e) {
									result.setText("F R E E _ S P I N");
									result.setHorizontalAlignment(SwingConstants.CENTER);
									resultLabel.setBackground(randomColor());
									// result.setHorizontalAlignment(SwingConstants.CENTER);
									timer();
								}
							});
							t2.setRepeats(false);
							t2.start();

							// result.setText("F R E E _ S P I N");
							// result.setHorizontalAlignment(SwingConstants.CENTER);
							// resultLabel.setBackground(randomColor());

							//no2Equal++;
							finalReelValue = 0;

						}
					}
					//// reset the all the componunts propoties after reel stops
					bgPanel.setBackground(new Color(166, 166, 166));
					betMax.setBackground(Color.white);
					betOne.setBackground(Color.WHITE);
					statistics.setBackground(Color.WHITE);
					reset.setBackground(Color.WHITE);
					addCoin.setBackground(Color.white);
					bgPanel.setBorder(panelBorder);

					mouse = false;
				} catch (InterruptedException e) {
					// This is thrown if the thread's interrupted.
				} catch (ExecutionException e) {
					// This is thrown if we throw an exception
					// from doInBackground.
				}
			}

			@Override
			// Can safely update the GUI from this method.

			// this method update the gui while threads are running
			protected void process(List<Symbol> guiSymbol) {
				// Here we receive the values that we publish().
				// They may come grouped in chunks.
				// chunk array contain the list of symbols
				////// get the newwest symbol from the list to update the gui
				mostRecentValue = guiSymbol.get(guiSymbol.size() - 1);

				try {

					label.setIcon(new ImageIcon(mostRecentValue.getImage()));

					// to win always
					// label.setIcon(new ImageIcon(r1.slot[6].getImage()));

					label.setBorder(BorderFactory.createTitledBorder(reelBorder,
							"v a l u e: " + Integer.toString(mostRecentValue.getValue())));
					label.setBackground(randomColor());

					result.setText("C L I C K _ O N _ A _ R E E L _ T O _ S T O P");
					result.setHorizontalAlignment(SwingConstants.CENTER);
					resultLabel.setBackground(new Color(255, 51, 51));
					betMax.setBackground(randomColor());
					betOne.setBackground(randomColor());
					statistics.setBackground(randomColor());
					reset.setBackground(randomColor());
					addCoin.setBackground(randomColor());
					// decopanel.setBackground(randomColor());
					bgPanel.setBorder(randBorder);
					// creditArea.setBackground(randomColor());

					// bgPanel.setBackground(randomColor());

					if (label == reel1) {

						reel1Value = mostRecentValue.getValue();

					} else if (label == reel2) {
						reel2Value = mostRecentValue.getValue();

					} else if (label == reel3) {
						reel3Value = mostRecentValue.getValue();
					}

					// to win always
					// reel1Value = r1.slot[6].getValue();

					// timer();

					// countLabel1.setIcon(ii);

				} catch (Exception e) {
					//System.out.println("error occured while threads updating the gui");
				}
				// System.out.println();
			}

		};

		worker.execute();

	}

	/////////////////////// create mouse listner to stop the reels on a mouse
	/////////////////////// click
	public class event implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e1) {
			mouse = true;
		}

		public void mousePressed(MouseEvent e1) {
			mouse = true;
		}

		public void mouseReleased(MouseEvent e1) {
			mouse = true;
		}

		public void mouseEntered(MouseEvent e1) {
			/// mouse = true;
		}

		public void mouseExited(MouseEvent e1) {
			// mouse = true;
		}
	}

	/////// to generate rgb value and return it
	public Color randomColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return (new Color(r, g, b));
	}

	// public static void main(String args[]) {
	// Controller c1 = new Controller();
	//
	//
	// }

}
