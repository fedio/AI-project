package projetCannibalesNour;

import java.util.ArrayList;

public class Solution {
	State curr;
	
	ArrayList<State> aExploiter;
	ArrayList<State> dejaExploite;
	
	
	public Solution(State init) {
		
		this.aExploiter = new ArrayList<State>();
		aExploiter.add(init);
		
		this.dejaExploite = new ArrayList<State>();
		
		this.search();
		
	}
	
	
	
	public State search() {
		
		
		while(this.aExploiter.size()>0) {
			
			
			this.curr = this.aExploiter.get(this.stateWithLeastFValue());
			this.aExploiter.remove(this.curr);
			
			
			ArrayList<State> enfants = this.curr.genererEnfant();
			for(int i=0;i<enfants.size();i++) {
				
				
				State enfant = enfants.get(i);
				
				if(enfant.isGoal()) {
					System.out.println("trouvee :"+enfant);
					System.out.print("la solution est : ");
					enfant.printChemin();
					return (enfant);
				}
				
				
				enfant.calculateGValue();
				enfant.calculateFValue();
				
				
				if(testStateWithLessF(enfant, aExploiter)) continue;
				
				if(testStateWithLessF(enfant, dejaExploite)) {
					continue;
				}else {
					aExploiter.add(enfant);
				}
			}
			this.dejaExploite.add(curr);
		}
		
		return null;
	}
	
	
	
	
	private int stateWithLeastFValue() {
		int index = 0;
		
		for(int i=1 ; i<this.aExploiter.size();i++) {
			State s = this.aExploiter.get(i);
			if (s.fValue < this.aExploiter.get(index).fValue) index = i;
		}
		return index;
	}
	
	
	
	private boolean testStateWithLessF(State s,ArrayList<State> list) {
		
		for(int i=0;i<list.size();i++) {
			State c = list.get(i);
			if( s.isSame(c) && c.fValue<s.fValue) return true;
		}
		
		return false;
	}
	
	
}
