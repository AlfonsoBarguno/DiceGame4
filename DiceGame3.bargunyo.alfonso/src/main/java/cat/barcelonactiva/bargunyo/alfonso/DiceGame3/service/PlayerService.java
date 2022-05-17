package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.exception.NameAlreadyExistsException;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.exception.PlayerNotFoundException;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.repository.PlayerRepository;

@Service
public class PlayerService implements IPlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	/*En realidad, no hace falta anotar las clases
	 * con @transactionl porque SimpleJpaRepository,
	 * que es quien aplica los métodos de JpaRepository
	 * en playerRepository, ya es @transactional.*/

	@Override
	@Transactional(readOnly = true)
	public List<Player> findAll() {

		return playerRepository.findAll();
	}

	@Override
	@Transactional
	public Player save(Player player) {

		try {
			if (player.getName() == null || player.getName() == "") {
				player.setName("Anonymous");
				return playerRepository.save(player);
			} else if (playerRepository.existsByName(player.getName())) {
				throw new NameAlreadyExistsException("Player with name " + player.getName() + " already exists.");
			} else {
				return playerRepository.save(player);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Player> getPlayerById(Long id) {

		try {
			if (playerRepository.getById(id) == null) {

				throw new PlayerNotFoundException("Player with Id " + id + "does not exists.");
			} else {

				return Optional.of(playerRepository.getById(id));
			}

		} catch (Exception ex) {

			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deletePlayerbyId(Long id) {

		boolean result = false;

		if (playerRepository.findById(id) == null) {

			result = false;

			throw new PlayerNotFoundException("Player with Id " + id + "does not exists.");

		} else {
			result = true;

			playerRepository.deleteById(id);

		}

		return result;
	}

	@Override
	@Transactional
	public Optional<Player> getBestPlayer() {

		List<Player> playerList = playerRepository.findAll();

		if (playerList.isEmpty()) {

			System.out.println("There are no players.");
		}

		Collections.sort(playerList, new ComparatorPlayer());

		System.out.println("The best player is: " + playerList.get(0).toString());

		return Optional.of(playerList.get(0));
	}

	@Override
	@Transactional
	public Optional<Player> getWorstPlayer() {

		List<Player> playerList = playerRepository.findAll();

		if (playerList.isEmpty()) {

			System.out.println("There are no players.");
		}

		Collections.sort(playerList, new ComparatorPlayer());

		System.out.println("The worst player is: " + playerList.get(playerList.size() - 1).toString());

		return Optional.of(playerList.get(playerList.size() - 1));
	}

	@Override
	@Transactional
	public List<Player> getRanking() {

		List<Player> playerList = playerRepository.findAll();

		List<Player> playerRanking = new ArrayList<Player>();

		if (playerList.isEmpty()) {

			System.out.println("There are no players.");
		}

		else {

			Collections.sort(playerList, new ComparatorPlayer());

			for (Player player : playerList) {

				playerRanking.add(player);

				System.out.println("Nombre: " + player.getName());
				System.out.println("Rate: " + player.getVictory());

			}

		}
		return playerRanking;
	}

}
