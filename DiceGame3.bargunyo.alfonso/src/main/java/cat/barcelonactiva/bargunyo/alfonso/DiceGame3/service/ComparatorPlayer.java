package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service;

import java.util.Comparator;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;

public class ComparatorPlayer implements Comparator<Player> {
	
	@Override
	public int compare(Player o1, Player o2) {
		
		    return o1.getVictory() < o2.getVictory() ? -1 :  o1.getVictory()== o2.getVictory() ? 0 : 1;

} }
