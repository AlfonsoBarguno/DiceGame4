package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	
	boolean existsByName (String name);
}
