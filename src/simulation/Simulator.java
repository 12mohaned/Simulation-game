package simulation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
public class Simulator{
private int currentCycle;
private ArrayList<ResidentialBuilding> buildings;
private ArrayList<Citizen> citizens;
private ArrayList<Unit> emergencyUnits;
private ArrayList<Disaster> plannedDisasters;
private ArrayList<Disaster> executedDisasters;
private Address[][]world;
public Simulator()throws IOException{
buildings= new ArrayList<>();
citizens= new ArrayList<>();
emergencyUnits= new ArrayList<>();
plannedDisasters= new ArrayList<>();
executedDisasters= new ArrayList<>();
world = new Address[10][10];
for(int i =0; i < 10;i++) {
for(int j =0 ; j < 10;j++) {
world[i][j] = new Address(i,j);	
}	
}
loadBuildings("buildings.csv");
loadCitizens("citizens.csv");
loadUnits("units.csv");
loadDisasters("disasters.csv");
//matching citizens with buildings by location where Citizens who have same location as Citizens is added in Occupants ArrayList
for(int i=0; i < buildings.size();i++){
for(int j=0; j < citizens.size();j++){
if(buildings.get(i).getLocation() == citizens.get(j).getLocation()){
buildings.get(i).getOccupants().add(citizens.get(j));
}
}	
}
}
private void loadUnits(String filepath)throws IOException
{
String currentLine = "";		
FileReader fileReader= new FileReader(filepath);
BufferedReader br = new BufferedReader(fileReader);
while((currentLine = br.readLine()) != null){
String[]result = currentLine.split(",");
if(result[0].equals("AMB")){
emergencyUnits.add(new Ambulance(result[1], world[0][0], Integer.parseInt(result[2])));
}
else{
if(result[0].equals("DCU")){
emergencyUnits.add(new DiseaseControlUnit(result[1], world[0][0], Integer.parseInt(result[2])));	
}
	else{
if(result[0].equals("FTK")) {
emergencyUnits.add(new FireTruck(result[1], world[0][0], Integer.parseInt(result[2])));	
}
	else{
if(result[0].equals("GCU")){
	emergencyUnits.add(new GasControlUnit(result[1],world[0][0], Integer.parseInt(result[2])));		
}
	else{
if(result[0].equals("EVC")){
emergencyUnits.add(new Evacuator(result[1],world[0][0], Integer.parseInt(result[2]),Integer.parseInt(result[3])));			
}
}
}
}
}
}
}
private void loadBuildings(String filePath)
throws IOException
{
FileReader fileReader= new FileReader(filePath);
BufferedReader br = new BufferedReader(fileReader);
String currentLine = "";
while((currentLine = br.readLine())!= null){
String[]result = currentLine.split(",");
buildings.add(new ResidentialBuilding(world[Integer.parseInt(result[0])][Integer.parseInt(result[1])]));
}
}
private void loadCitizens(String filePath)throws IOException{
FileReader fileReader= new FileReader(filePath);
BufferedReader br = new BufferedReader(fileReader);	
String currentLine = "";
while((currentLine = br.readLine()) != null)
{
String[]result = currentLine.split(",");
citizens.add(new Citizen(world[Integer.parseInt(result[0])][Integer.parseInt(result[1])],result[2],result[3],Integer.parseInt(result[4])));
}
}
private void loadDisasters(String filePath)throws IOException
{
FileReader fileReader= new FileReader(filePath);
BufferedReader br = new BufferedReader(fileReader);	
String currentLine = "";
while((currentLine = br.readLine())!= null){
int i = 0;
	String[]result = currentLine.split(",");
	if(result[1].equals("INJ") | result[1].equals("INF"))
{
for(i =0; i < citizens.size();i++){
if(result[2].equals((citizens.get(i)).getNationalID()))
{
	break;
}	
}	
}
	else{
if(result[1].equals("GLK") | result[1].equals("FIR"))
{
for(i =0; i < buildings.size();i++){
if((buildings.get(i)).getLocation().equals(world[(Integer.parseInt(result[2]))][(Integer.parseInt(result[3]))])){
break;
}	
}
}
}
switch(result[1]){
case "INJ" :plannedDisasters.add(new Injury(Integer.parseInt(result[0]),citizens.get(i)));break;
case "INF" :plannedDisasters.add(new Infection (Integer.parseInt(result[0]),citizens.get(i)));break;
case "GLK" :plannedDisasters.add(new GasLeak(Integer.parseInt(result[0]),buildings.get(i)));break;
case "FIR" :plannedDisasters.add(new Fire (Integer.parseInt(result[0]),buildings.get(i)));break;
}	
}
}
}