import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HammingDist extends JFrame{
	private JPanel panel1;
	
	private JButton showStation = new JButton("Show Station");		//Button to show different stations with matching hamming distance
	private JTextArea intext = new JTextArea(24,12);
	private JScrollPane showStationScroll = new JScrollPane(intext);
	private JButton calcHD = new JButton("Calculate HD");
	private JButton addStat = new JButton("Add Station");
	private JSlider enterHam = new JSlider(1,4);
	private JLabel hamVal = new JLabel();
	private JTextField addStationShow = new JTextField(10);
	private JComboBox<String> comparedList;
	private JLabel dist0 = new JLabel("Distance 0");
	private JLabel dist1 = new JLabel("Distance 1");
	private JLabel dist2 = new JLabel("Distance 2");
	private JLabel dist3 = new JLabel("Distance 3");
	private JLabel dist4 = new JLabel("Distance 4");
	private JLabel sliderVal = new JLabel("Enter Hamming Dist:");
	private JLabel compareLabel = new JLabel("Compare with");
	private JLabel dist0Show = new JLabel();
	private JLabel dist1Show = new JLabel();
	private JLabel dist2Show = new JLabel();
	private JLabel dist3Show = new JLabel();
	private JLabel dist4Show = new JLabel();
	private Color only = new Color(50,150,230);
	private BufferedImage image;
	private JLabel picture;
	
	
	
	public HammingDist() throws IOException{
	setFrame();
	setSlider();
	setTextFields();
	setCB();
	setButtons();
	setPicture();
	panel1.add(showStation);
	panel1.add(calcHD);
	panel1.add(addStat);
	panel1.add(addStationShow);
	panel1.add(enterHam);
	panel1.add(comparedList);
	panel1.add(sliderVal);
	panel1.add(compareLabel);
	panel1.add(dist0Show);
	panel1.add(dist1Show);
	panel1.add(dist2Show);
	panel1.add(dist3Show);
	panel1.add(dist4Show);
	panel1.add(dist0);
	panel1.add(dist1);
	panel1.add(dist2);
	panel1.add(dist3);
	panel1.add(dist4);
	panel1.add(hamVal);
	panel1.add(showStationScroll);
	panel1.add(picture);
	this.add(panel1);
	}
	
	private void setTextFields() {
	addStationShow.setText("ZERO");
	addStationShow.setBounds(150,700,100,25);
	
	//setting boundaries of each label field to create consistency
	sliderVal.setBounds(10,8,120,15);
	compareLabel.setBounds(10,415,120,15);
	dist0Show.setBorder(BorderFactory.createLineBorder(only,1));
	dist0Show.setBounds(150,495,100,20);
	dist1Show.setBorder(BorderFactory.createLineBorder(only,1));
	dist1Show.setBounds(150,535,100,20);
	dist2Show.setBorder(BorderFactory.createLineBorder(only,1));
	dist2Show.setBounds(150,585,100,20);
	dist3Show.setBorder(BorderFactory.createLineBorder(only,1));
	dist3Show.setBounds(150,625,100,20);
	dist4Show.setBorder(BorderFactory.createLineBorder(only,1));
	dist4Show.setBounds(150,660,100,20);

	
	dist0.setBounds(15,500,120,10);
	dist1.setBounds(15,545,120,10);
	dist2.setBounds(15,585,120,10);
	dist3.setBounds(15,625,120,10);
	dist4.setBounds(15,665,120,10);
	
	showStationScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	showStationScroll.setBackground(Color.WHITE);
	showStationScroll.setOpaque(true);
	showStationScroll.setBounds(25,150,200,250);
	
	}
	private void setSlider() { 
		enterHam.setBounds(10,25,200,50);
		enterHam.setPaintTicks(true);
		enterHam.setMajorTickSpacing(1);
		enterHam.setPaintLabels(true);
		hamVal.setBorder(BorderFactory.createLineBorder(only, 1));
		hamVal.setBounds(130, 5, 100, 15);
		hamVal.setText(" " + enterHam.getValue());
		
		enterHam.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
			hamVal.setText(" " + enterHam.getValue());
		}
		
	});
	}
	
	
	private void setButtons() {
		showStation.setBounds(17,100,115,25);
		calcHD.setBounds(17, 450, 115, 25);
		addStat.setBounds(17, 700, 115, 25);
		showStation.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				intext.setText("");
				ArrayList<String> ham1 = new ArrayList<String>();
				ArrayList<String> ham2 = new ArrayList<String>();
				ArrayList<String> ham3 = new ArrayList<String>();
				ArrayList<String> ham4 = new ArrayList<String>();


				String word = (String)comparedList.getSelectedItem();
				String[] wordSplit = word.split("");
				ArrayList<String> mesoNow = new ArrayList<String>();

				for(int index = 0 ; index < comparedList.getItemCount(); index++) {
					mesoNow.add(comparedList.getItemAt(index));
				}
				
				
				for(int index = 0; index < mesoNow.size(); index++) {
					int counter = 0;
					String[] secondSplit = mesoNow.get(index).split("");
					for(int jndex = 0; jndex < wordSplit.length; jndex++) {
						if(!(wordSplit[jndex].equals (secondSplit[jndex]))){
							counter++;
						}
					}	
					
					if(counter == 1) {
						ham1.add(mesoNow.get(index));			
					}
					else if(counter ==2) {
						ham2.add(mesoNow.get(index));
					}
					else if(counter==3) {
						ham3.add(mesoNow.get(index));
					}
					else if(counter==4) {
						ham4.add(mesoNow.get(index));
					}
					

				}
					if(enterHam.getValue()==1) {
						for(int here = 0; here < ham1.size(); here++) {
							intext.append(ham1.get(here) + '\n');
							
						}
				}
					else if(enterHam.getValue()==2) {
						for(int here = 0; here < ham2.size(); here++) {
							intext.append(ham2.get(here)+ '\n');
						}
				}
					else if(enterHam.getValue()==3) {
						for(int here = 0; here < ham3.size(); here++) {
							intext.append(ham3.get(here)+ '\n');
						}
				}
					else if(enterHam.getValue()==4) {
						for(int here = 0; here < ham4.size(); here++) {
							intext.append(ham4.get(here)+ '\n');
						}
				}
				
				
				
				
			}
		});
		
		calcHD.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = (String)comparedList.getSelectedItem();
				String[] wordSplit = word.split("");
				ArrayList<String> mesoNow = new ArrayList<String>();
				int[] hamCounts = new int[5];

				for(int index = 0 ; index < comparedList.getItemCount(); index++) {
					mesoNow.add(comparedList.getItemAt(index));
				}
				
				
				for(int index = 0; index < mesoNow.size(); index++) {
					int counter = 0;
					String[] secondSplit = mesoNow.get(index).split("");
					for(int jndex = 0; jndex < wordSplit.length; jndex++) {
						if(!(wordSplit[jndex].equals (secondSplit[jndex]))){
							counter++;
						}
					}	
					hamCounts[counter] = hamCounts[counter] + 1;
				}
				dist0Show.setText(Integer.toString(hamCounts[0]));
				dist1Show.setText(Integer.toString(hamCounts[1]));
				dist2Show.setText(Integer.toString(hamCounts[2]));
				dist3Show.setText(Integer.toString(hamCounts[3]));
				dist4Show.setText(Integer.toString(hamCounts[4]));
				
				
			}
		});
		
		addStat.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
				comparedList.addItem(addStationShow.getText());	
			
			} 
		 });
	}
	
	
	private void setCB() throws IOException{
	BufferedReader br = new BufferedReader(new FileReader("Mesonet.txt"));
	
	
	List<String> meso = new ArrayList<String>();
	try {
		String line = null;
		while((line = br.readLine()) != null) {
			meso.add(line);
			
		}
	}
	catch(FileNotFoundException e) {
		System.err.println("Error, file Mesonet.txt didn't exist.");
	}
	finally {
		br.close();
	}
	
	String[] lines = meso.toArray(new String[] {});
	comparedList = new JComboBox<>(lines);
	comparedList.setBounds(145,410,70,20);
	}
	
	private void setFrame() {
	setTitle("Hamming Distance");
	setSize(900,1080);
	panel1 = new JPanel(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	}
	private void setPicture() throws IOException 
	{
		image = ImageIO.read(new File("FortDab.png"));
		picture = new JLabel(new ImageIcon(image));
		picture.setBounds(0, 20, 1000, 1000);
	}
	
	public static void main(String[] args) throws IOException{
		HammingDist ha = new HammingDist();
		ha.revalidate();
		
	}
}