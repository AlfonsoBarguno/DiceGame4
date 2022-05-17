package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service;

import java.util.List;
import java.util.Optional;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;


public interface IPlayerService {
	
	
    public List<Player> findAll(); 
	
	public Player save (Player player);
	
	Optional<Player> getPlayerById(Long id);
	
	boolean deletePlayerbyId(Long id);
	
	Optional<Player> getBestPlayer();
	
	Optional<Player> getWorstPlayer();
	
	List<Player> getRanking();
}
