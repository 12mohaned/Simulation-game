package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.units.*;
import controller.CommandCenter;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Simulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
public class SimulationView extends JFrame{
private ArrayList<Unit>Units;
private ArrayList<Citizen>Citizens;
private ArrayList<ResidentialBuilding>Buildings;
private JPanel Unit;
private JPanel Map;
private JPanel Info;
private JPanel Controls;
private JScrollPane scroll;
//private JButton[][] world;
private JButton[][]rescued;
private JTextField Casualities;
private CommandCenter Command;
private Simulator engine;
private JButton EndCurrentCycle;
private JTextField Casulaities;
private JTextField CurrentCycle;
private JTextArea f;
private JButton respond;
private JButton AddnewUnit;
private JScrollPane Scroll;
public JButton getEndCurrentCycle() {
return EndCurrentCycle;	
}
public void setUnit(ArrayList<JButton>l) {
for(int i =0; i < l.size();i++) {
Unit.add(l.get(i));
}	
}
public void setMap(JButton[][]map){
for(int i =0; i < 10;i++) {
for(int j =0; j < 10;j++) {
Map.add(map[i][j]);	
}
}	
}
public void setBuildings(ArrayList<JButton> Buildings) {
for(int i =0; i < Buildings.size();i++){
Map.add(Buildings.get(i));
}	
}
public void AddToPanel(String s) {
f.setText(s);
}
public void AddCurrentCycle(int Current,int Casualities) {
CurrentCycle.setText(Current+"");	
Casulaities.setText(Casualities + "");
}
public SimulationView(CommandCenter Command) throws Exception{
super("SimulationGame");
this.Command = Command;
setExtendedState(MAXIMIZED_BOTH);
Container container = getContentPane();	
respond = new JButton("Respond");
respond.setPreferredSize(new Dimension(100,40));
respond.addActionListener(Command);
Unit = new JPanel();
Map = new JPanel();
Info = new JPanel();
Controls = new JPanel();
AddnewUnit = new JButton();
Map.setLayout(new GridLayout(10,10,1,1));
Unit.setLayout(new GridLayout(10,5,1,1));
Info.setLayout(new BorderLayout());
Controls.setLayout(new GridLayout(35,35));
f = new JTextArea();
Info.add(f,BorderLayout.CENTER);
f.setEditable(false);
scroll = new JScrollPane(f);
Info.add(scroll);
container.setLayout(new BorderLayout());
EndCurrentCycle = new JButton("Next Cycle");
EndCurrentCycle.setPreferredSize(new Dimension(60,60));
EndCurrentCycle.addActionListener(Command);
Casulaities = new JTextField("Number of Casualities");
Casulaities.setPreferredSize(new Dimension(25,25));
Casulaities.setEditable(false);
CurrentCycle = new JTextField("The Current Cycle");
CurrentCycle.setLocation(new Point(10,10));
CurrentCycle.setPreferredSize(new Dimension(29,29));
CurrentCycle.setEditable(false);
//this.setResizable(false);
Controls.add(EndCurrentCycle);
Controls.add(Casulaities);
Controls.add(CurrentCycle);
Controls.add(respond);
Info.setPreferredSize(new Dimension(10,160));
container.add(Map,BorderLayout.WEST);
container.add(Unit,BorderLayout.EAST);
container.add(Controls,BorderLayout.CENTER);
container.add(Info,BorderLayout.SOUTH);
this.setSize(new Dimension(1200,1200));
this.setVisible(true);
this.validate();
this.repaint();
this.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public JButton getRespond() {
return respond;
}
public void addComboBoxToView(JComboBox c) {
Controls.add(c);	
}
public JButton getAddUnit() {
return AddnewUnit;	
}
}