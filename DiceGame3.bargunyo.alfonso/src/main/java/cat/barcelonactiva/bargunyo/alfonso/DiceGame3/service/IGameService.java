package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service;

import java.util.List;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Game;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;


public interface IGameService {
	
	
    public List<Game> findAll();
	
	public Game saveGame (Game game);
	
	public boolean deleteGamesByPlayerId(Long Id);
	
	public List<Game> getGamesByPlayerId (Long id);
	
	public Game playGame(Long playerId);
	
	
}
