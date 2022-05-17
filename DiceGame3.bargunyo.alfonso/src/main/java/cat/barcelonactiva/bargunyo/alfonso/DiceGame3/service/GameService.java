package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Game;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.exception.PlayerNotFoundException;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.repository.GameRepository;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.repository.PlayerRepository;

@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Game> findAll() {

		return gameRepository.findAll();
	}

	@Override
	@Transactional
	public Game saveGame(Game game) {

		return gameRepository.save(game);
	}

	@Override
	@Transactional
	public boolean deleteGamesByPlayerId(Long Id) {

		boolean result = false;

		if (gameRepository.findById(Id) == null) {

			throw new PlayerNotFoundException("Player with id " + Id + " does not exists.");

		} else {

			gameRepository.deleteById(Id);

			result = true;
		}

		return result;
	}

	@Override
	public Game playGame(Long player_id) {

		if (playerRepository.getById(player_id) != null) {

			try {
				Game game = new Game(playerRepository.getById(player_id));

				game.playGame(playerRepository.getById(player_id));

				return gameRepository.save(game);

			} catch (Exception ex) {

				System.out.println("ERROR: " + ex.getMessage());

			}
		} else {

			throw new PlayerNotFoundException("Player with id " + player_id + "does not exists.");
		}
		return null;

	}

	@Override
	public List<Game> getGamesByPlayerId(Long id) {

		Player player = playerRepository.getById(id);

		return player.getGames();

	}
}
