package slotMachineGUI_CW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import javafx.scene.chart.PieChart;
import slotMachineGUI_CW.Controller.addCoinHandle;

public class StatisticsWindow extends JFrame {

	///////////////////////////////////////////////////////////////////////
	
	
	//iuse jprograss bar to display the players averages and use labels to to display number of wins lost and credit
	///		so its for readability
	//	
	// Font f1 = new Font();
	Border statsBoader = BorderFactory.createLineBorder(Color.black, 2);

	private static int totalwin, noLose, no3Equal, noSpin, no2Equal, creditaverage, equalAverage, nowins, lostCredit;
	private static double winAveragePerGame, equalAveragePerGame;
	private JProgressBar creditAvgBar, equalAvgBar;
	private JLabel creditWinPerGame, winsPerGame, totalCreditWin, nowin, noOfLose, noOf2Equal, noOf3Equal, noOfSpin,
			lostCreditLabel;
	private JPanel statPanel;
	private JButton print,graphic;
	private JSeparator sep1, sep2, sep3, sep4;
	GridBagConstraints gbc2 = new GridBagConstraints();

	// this constructor for menu bar save static button
	public StatisticsWindow() {

		// save the statistics of the user

		try {

			Date date = new Date();
			// create a file name using current date and time
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			// File file = new File(dateFormat.format(date) + ".tsv") ;

			PrintWriter writer = new PrintWriter(dateFormat.format(date) + ".txt", "UTF-8");
			// create a print writer classs object to write to the file
			// PrintWriter writer = new PrintWriter("PLAYER STATISTICS.txt",
			// "UTF-8");

			writer.println("total credit win = \t\t\t$" + totalwin);
			writer.println("numer of Lost =\t\t\t\t" + noLose);
			writer.println("3 reel matchings =\t\t\t" + no3Equal);
			writer.println("2 reel matchings =\t\t\t" + no2Equal);
			writer.println("Number of Spins (play times)=  \t\t" + noSpin);
			writer.println("Credit wining average per spin =\t" + creditaverage + "%");
			writer.println("matching reel average per spin =\t" + equalAverage + "%");
			writer.println("Total win =\t\t\t\t" + nowins);
			writer.println("Total credit lost= \t\t\t$" + lostCredit);
			// writer.println("winAveragePerGame=" + winAveragePerGame);
			// writer.println("equalAveragePerGame=" +
			// equalAveragePerGame);

			writer.close();
		} catch (IOException ecc) {
			System.out.println("error while write to the file(menu)");
		}

	}

	// this constructor take the al neccessory information to create the
	// statistics report
	public StatisticsWindow(int tempTotalWin, int tempNoLose, int tempLosecredit, int tempNo3Equal, int tempNo2Equal, int tempNoSpin) {
		// right after object is create intially put all the variables to 0
		this.totalwin = 0;
		this.noLose = 0;
		this.no3Equal = 0;
		this.noSpin = 0;
		this.no2Equal = 0;
		this.nowins = 0;
		this.lostCredit = 0;

		// then reintialize the variables using passed values
		setLayout(new GridBagLayout());
		// winning credit
		this.totalwin = tempTotalWin;
		// number of lost
		this.noLose = tempNoLose;
		// no of equaling all 3 reels
		this.no3Equal = tempNo3Equal;
		// no of spins
		this.noSpin = tempNoSpin;
		// no of two symboles get matched
		this.no2Equal = tempNo2Equal;
		// no of wins
		this.nowins = tempNoSpin - tempNoLose;
		// amount f lost credit
		this.lostCredit = tempLosecredit;
		// create panel and set the layout
		statPanel = new JPanel(new GridBagLayout());
		statPanel.setBackground(Color.LIGHT_GRAY);

		try {

			// calculate the average winning credit
			winAveragePerGame = ((this.totalwin - lostCredit) / this.noSpin);

			// (winAveragePerGame) = Integer.parseInt("",
			// totalwin/this.noSpin)*100;
			// calculate average win per game
			equalAveragePerGame = this.nowins / this.noSpin;

			// average credit per game in integer
			creditaverage = (int) (winAveragePerGame);

			// average win per game in integer
			equalAverage = (int) (equalAveragePerGame * 100);

		} catch (ArithmeticException m) {
			System.out.println("trying to divide in zero");
		}

		// set layout positioning using grid bag constraint
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		// gbc2.weightx=2;
		// gbc2.weighty=2;
		// gbc2.gridheight=2;
		// gbc2.gridwidth=3;
		//
		gbc2.insets = new Insets(5, 5, 5, 5);
		gbc2.fill = GridBagConstraints.BOTH;
		totalCreditWin = new JLabel("<html><h3>TOTAL WINING:  $<b>" + this.totalwin + "</b></h3></html>");
		// totalCreditWin = new JLabel("TOTAL WINING: $" + this.totalwin + "");
		// totalCreditWin.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(totalCreditWin, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 1;
		nowin = new JLabel("<html><b><h3>NO OF WIN:  " + this.nowins + " times</h3></b></html>");
		// nowin.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(nowin, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 2;
		noOf2Equal = new JLabel("<html><b><h3>TWO REEL MATCH:  " + this.no2Equal + " times</h3></b></html>");
		// noOf2Equal.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(noOf2Equal, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 3;
		noOf3Equal = new JLabel("<html><b><h3>ALL REEL MATCH:  " + this.no3Equal + " times</h3></b></html>");
		// noOf3Equal.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(noOf3Equal, gbc2);

		sep1 = new JSeparator();
		sep1.setPreferredSize(new Dimension(1, 1));
		// gbc2.fill = GridBagConstraints.HORIZONTAL;

		gbc2.gridx = 0;
		gbc2.gridy = 4;
		gbc2.gridwidth = 10;
		gbc2.fill = GridBagConstraints.BOTH;
		statPanel.add(sep1, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 5;
		gbc2.gridwidth = 1;
		lostCreditLabel = new JLabel("<html><b><h3>LOST CREDIT:  $" + this.lostCredit + " </h3></b></html>");
		// noOfLose.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(lostCreditLabel, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 6;
		noOfLose = new JLabel("<html><b><h3>NO ofLOST:  " + this.noLose + " times</h3></b></html>");
		// noOfLose.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(noOfLose, gbc2);

		sep2 = new JSeparator();
		sep2.setPreferredSize(new Dimension(1, 1));
		// gbc2.fill = GridBagConstraints.HORIZONTAL;

		gbc2.gridx = 0;
		gbc2.gridy = 7;
		gbc2.gridwidth = 10;
		gbc2.fill = GridBagConstraints.BOTH;
		statPanel.add(sep2, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 10;
		gbc2.gridwidth = 1;
		winsPerGame = new JLabel("<html><b><h3>WINING PER GAME:  " + equalAveragePerGame + "%</h3></b></html>");
		// winsPerGame.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(winsPerGame, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 11;
		equalAvgBar = new JProgressBar(0, 100);
		equalAvgBar.setStringPainted(true);
		equalAvgBar.setString("WIN AVERAGE: " + equalAverage + "%");
		equalAvgBar.setValue(equalAverage);
		statPanel.add(equalAvgBar, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 8;
		creditWinPerGame = new JLabel("<html><b><h3>AVERAGE CREDITWIN:  " + creditaverage + "%</h3></b></html>");
		// creditWinPerGame.setHorizontalAlignment(SwingConstants.CENTER);
		statPanel.add(creditWinPerGame, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 9;
		creditAvgBar = new JProgressBar(0, 100);
		creditAvgBar.setStringPainted(true);
		creditAvgBar.setString("CREDIT AVERAGE: " + creditaverage + "%");
		creditAvgBar.setValue(equalAverage);
		statPanel.add(creditAvgBar, gbc2);

		sep3 = new JSeparator();
		sep3.setPreferredSize(new Dimension(1, 1));
		// gbc2.fill = GridBagConstraints.HORIZONTAL;

		gbc2.gridx = 0;
		gbc2.gridy = 12;
		gbc2.gridwidth = 10;
		gbc2.fill = GridBagConstraints.BOTH;
		statPanel.add(sep3, gbc2);

		
		
		
		
		
		
		gbc2.gridx = 0;
		gbc2.gridy = 13;
		gbc2.gridheight = 1;
		gbc2.gridwidth = 5;
		graphic = new JButton("View PieChart");
		// print.addActionListener(new printHandle());
		statPanel.add(graphic, gbc2);
		// handle the save button action
		graphic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					StatisticChart piechart = new StatisticChart(nowins,tempNoLose,tempNoSpin,creditaverage,tempNo2Equal);
					piechart.setTitle("Slot_Machine_Statistics");
					piechart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					piechart.setSize(1000,600);
					piechart.setLocationRelativeTo(null);
					piechart.setBackground(Color.DARK_GRAY);
					piechart.pack();
					piechart.setVisible(true);
					piechart.setAlwaysOnTop(true);
					piechart.toFront(); 
					piechart.setTitle(" S T A T _ C H A R T ");
					
					
					
				} catch (Exception ee) {
					System.out.println("error");
				}

			}
		});
		statPanel.add(graphic, gbc2);
		
		
		
		
		
		
		
		
		
		
		
		// save button
		gbc2.gridx = 0;
		gbc2.gridy = 14;
		gbc2.gridheight = 1;
		gbc2.gridwidth = 5;
		print = new JButton("SAVE Statistics");
		// print.addActionListener(new printHandle());
		statPanel.add(print, gbc2);
		// handle the save button action
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// to save text in to the txt file
					// create date class object
					Date date = new Date();
					// create a file name using current date and time
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
					// File file = new File(dateFormat.format(date) + ".tsv") ;

					PrintWriter writer = new PrintWriter(dateFormat.format(date) + ".txt", "UTF-8");

					writer.println("total credit win = \t\t\t$" + totalwin);
					writer.println("numer of Lost =\t\t\t\t" + tempNoLose);
					writer.println("3 reel matchings =\t\t\t" + tempNo3Equal);
					writer.println("2 reel matchings =\t\t\t" + tempNo2Equal);
					writer.println("Number of Spins (play times)=  \t\t" + tempNoSpin);
					writer.println("Credit wining average per spin =\t" + creditaverage + "%");
					writer.println("matching reel average per spin =\t" + equalAverage + "%");
					writer.println("Total win =\t\t\t\t" + nowins);
					writer.println("Total credit lost= \t\t\t$" + lostCredit);
					// writer.println("winAveragePerGame=" + winAveragePerGame);
					// writer.println("equalAveragePerGame=" +
					// equalAveragePerGame);

					writer.close();
				} catch (IOException ecc) {
					System.out.println("error while write to the file");
				} catch (Exception ee) {
					System.out.println("error");
				}

			}
		});
		statPanel.add(print, gbc2);
		add(statPanel);

	}

	public String printmssage() {
		return "totalwin=" + totalwin + ",\n\n noLose=" + noLose + ", no3Equal=" + no3Equal + ", noSpin=" + noSpin
				+ ", no2Equal=" + no2Equal + ", creditaverage=" + creditaverage + ", equalAverage=" + equalAverage
				+ ", nowins=" + nowins + ", lostCredit=" + lostCredit + ", winAveragePerGame=" + winAveragePerGame
				+ ", equalAveragePerGame=" + equalAveragePerGame + "]";
	}

}
