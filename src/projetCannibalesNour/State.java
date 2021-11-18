package projetCannibalesNour;

import java.util.ArrayList;

public class State {
	int nombreDeMissionnaires,nombreDeCannibales,positionSurRiviere,fValue,gValue;
	State parent;
	
	public State(int nombreDeMissionnaires,int nombreDeCannibales,int positionSurRiviere) {
		this.nombreDeMissionnaires = nombreDeMissionnaires;
		this.nombreDeCannibales = nombreDeCannibales;
		this.positionSurRiviere = positionSurRiviere;
		this.fValue = 0;
		this.gValue = 0;
		this.parent = null;
	}
	
	
	public State(int nombreDeMissionnaires,int nombreDeCannibales,int positionSurRiviere,State parent) {
		this.nombreDeMissionnaires = nombreDeMissionnaires;
		this.nombreDeCannibales = nombreDeCannibales;
		this.positionSurRiviere = positionSurRiviere;
		this.fValue = 0;
		this.gValue = 0;
		this.parent = parent;
	}
	
	
	public ArrayList<State> genererEnfant() {
		
		
		ArrayList<State> enfants = new ArrayList<State>();
		
		
		
		State tested;
		tested = this.prendre(0,1);
		if(tested != null) enfants.add(tested);
		tested = this.prendre(0,2);
		if(tested != null) enfants.add(tested);
		tested = this.prendre(1,0);
		if(tested != null) enfants.add(tested);
		tested = this.prendre(2,0);
		if(tested != null) enfants.add(tested);
		tested = this.prendre(1,1);
		if(tested != null) enfants.add(tested);
		return enfants;
	}
	
	private State prendre(int M,int C) {
		State newState = null ;
		
		if(M+C>2) return null;
		
		if(this.positionSurRiviere == 0) {
			int newM = this.nombreDeMissionnaires - M;
			int newC = this.nombreDeCannibales - C;
			
			if(newM < 0 || newC < 0) return null;
			
			
			
			newState = new State(newM,newC,1,this);
		}else {
			int newM = this.nombreDeMissionnaires + M;
			int newC = this.nombreDeCannibales + C;
			
			if(newM > 3 || newC > 3) return null;
			
			
			
			newState = new State(newM,newC,0,this);
		}
		
		
		if(!newState.testValid()) return null;
		
		
		return newState;
	}
	
	
	private boolean testValid() {
		if(this.nombreDeMissionnaires == 3 || this.nombreDeMissionnaires == 0) return true;
		if(this.nombreDeCannibales <= this.nombreDeMissionnaires && this.nombreDeCannibales >= this.nombreDeMissionnaires) return true;
		return false;
	}
	
	
	public String toString() {
		return ("["+this.nombreDeMissionnaires+","+this.nombreDeCannibales+","+this.positionSurRiviere+"]");
	}

	public boolean isGoal() {
		return (this.nombreDeMissionnaires==0 && this.nombreDeCannibales == 0 && this.positionSurRiviere==1);
	}


	public void calculateGValue() {
		this.gValue = gValue + ( (parent.nombreDeMissionnaires - this.nombreDeMissionnaires) + (parent.nombreDeCannibales - this.nombreDeCannibales));
	}
	
	public int calculateHValue() {
		return this.nombreDeMissionnaires + this.nombreDeCannibales;
	}
	
	
	public void calculateFValue() {
		this.fValue = this.gValue + this.calculateHValue();
	}


	public boolean isSame(State other) {
		return (this.nombreDeMissionnaires==other.nombreDeMissionnaires && this.nombreDeCannibales == other.nombreDeCannibales && this.positionSurRiviere==other.positionSurRiviere);
	}


	public void printChemin() {
		if(this.parent!=null)
			parent.printChemin();
		if(this.isGoal())
			System.out.print(this);
		else
			System.out.print(this + "-->");
	}
	
}
