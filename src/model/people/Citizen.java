package model.people;
import simulation.*;
import model.disasters.Disaster;
public class Citizen implements Simulatable,Rescuable {
private CitizenState state=CitizenState.SAFE;;
private Disaster disaster;
private Address location;
private String nationalID;
private String name;
private int age;
private int hp = 100;
private int bloodLoss =0;
private int toxicity = 0;
public Citizen(Address location, String nationalID, String name, int age){
this.location = location;
this.nationalID = nationalID;
this.name= name;
this.age = age;
}
public CitizenState getState(){
return state;	
}
public void setState(CitizenState state){
this.state = state;	
}
public Disaster getDisaster(){
return disaster;	
}
public Address getLocation(){
return location;	
}
public void setLocation(Address Location){
location = Location;	
}
public String getNationalID(){
return nationalID;	
}
public String getName(){
return name;	
}
public int getAge(){
return age;	
}
public int getHp(){
return hp;	
}
public void setHp(int hp){
this.hp = hp;	
}
public int getBloodLoss(){
return bloodLoss;	
}
public void setBloodLoss(int bloodloss){
bloodLoss = bloodloss; 	
}
public int getToxicity(){
return toxicity;	
}
public void setToxicity(int toxicity){
this.toxicity = toxicity;	
}
}