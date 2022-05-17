package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	
	//public List<Game> getGamesByPlayerId(Long id);
}
