package slotMachineGUI_CW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;



public class StatisticChart extends JFrame {

	Container contentPane1;

	public StatisticChart(int wintime, int losetime,int count,int percentage,int freespins) {

		statistic(wintime,losetime,count,percentage,freespins);
	}

	public void statistic(int wintime, int losetime,int count,int percentage,int freespins ) {

		// g.wintime = g.count - g.losetime;
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Wins", wintime);
		pieDataset.setValue("Loses", losetime);
		JFreeChart chart = ChartFactory.createPieChart3D("Pie_Chart", pieDataset, true,	true, true);
		setBackground(Color.darkGray);
		chart.setBackgroundPaint(Color.GRAY);

			
		PiePlot3D p = (PiePlot3D) chart.getPlot();
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint("Wins", Color.GREEN);
        plot.setSectionPaint("Loses", Color.DARK_GRAY);
        
		ChartPanel chartpanel = new ChartPanel(chart);
		//JButton saveStats = new JButton("Save Statistic");
		//saveStats.addActionListener(new SaveStatistics(wintime,losetime,count,percentage,freespins));
		JLabel attempts = new JLabel("Attempts   = " + count);
		JLabel wons = new JLabel    ("Wons       = " + wintime);
		JLabel losses = new JLabel  ("Losses     = " + losetime);
		JLabel winp = new JLabel    ("Percentage = " + percentage);
		JLabel spin = new JLabel    ("Free Spins = " +freespins);

		final JPanel content = new JPanel(new GridBagLayout());
		final JPanel label = new JPanel(new GridBagLayout());
		label.setBackground(Color.lightGray);

		GridBagConstraints gc = new GridBagConstraints();
		GridBagConstraints gc2 = new GridBagConstraints();
		
		gc2.insets= new Insets(19, 2, 19, 2);
		gc2.fill= GridBagConstraints.BOTH;
		
		gc2.gridx = 0;
		gc2.gridy = 0;
		label.add(wons, gc2);

		gc2.gridx = 0;
		gc2.gridy = 4;
		label.add(winp, gc2);
		
		gc2.gridx = 0;
		gc2.gridy = 1;
		label.add(losses, gc2);
		

		
		gc2.gridx = 0;
		gc2.gridy = 2;
		label.add(attempts, gc2);
		
//		gc.gridx = 1;
//		gc.gridy = 2;
//		content.add(saveStats, gc);
		
		gc2.gridx = 0;
		gc2.gridy = 3;
		label.add(spin, gc2);
		
		
		gc.gridx = 0;
		gc.gridy = 0;
		content.add(label, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		content.add(chartpanel, gc);

		

		// content.add(losses,BorderLayout.EAST);
		chartpanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(content);

	}

}