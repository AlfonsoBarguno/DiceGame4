package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Game;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.exception.PlayerNotFoundException;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service.IGameService;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service.IPlayerService;

@RestController
@RequestMapping
public class GameController {

	@Autowired
	IGameService gameService;

	@Autowired
	IPlayerService playerService;

	@GetMapping("/{id}/games")
	public List<Game> getGamesByPlayerId(@PathVariable("id") Long id) {

		List<Game> games = StreamSupport.stream(gameService.getGamesByPlayerId(id).spliterator(), false)
				.collect(Collectors.toList());

		return games;

	}

	@PostMapping("{id}/games")
	public ResponseEntity<?> playGame(@PathVariable(value = "id") Long id) throws Exception {

		return ResponseEntity.ok().body(gameService.playGame(id));
	}

	@DeleteMapping("{id}/games")
	public ResponseEntity<?> deleteGames(@PathVariable(value = "id") Long id) throws Exception {

		try {

			if (id != null && playerService.getPlayerById(id) != null && gameService.getGamesByPlayerId(id).isEmpty()) {

				gameService.deleteGamesByPlayerId(id);

				return ResponseEntity.ok().build();

			} else {

				throw new PlayerNotFoundException("Player with Id " + id + " does not exist.");
			}
		} catch (Exception ex) {

			System.out.println("ERROR: " + ex.getMessage());
		} return null;
	}

}
