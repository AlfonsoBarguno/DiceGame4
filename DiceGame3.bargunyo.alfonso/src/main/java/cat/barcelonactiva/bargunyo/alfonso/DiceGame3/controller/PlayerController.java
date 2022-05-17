package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity.Player;
import cat.barcelonactiva.bargunyo.alfonso.DiceGame3.service.IPlayerService;

@RestController
@RequestMapping("/players)")
public class PlayerController {
	
	@Autowired
	private IPlayerService playerService;
	
	//create a new player
	@PostMapping ("/add")
	public ResponseEntity<?> add (@RequestBody Player player){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(player));
	}
	
	
	//get one player
	@GetMapping("/player/{id}")
	public ResponseEntity<?> getPlayerById (@PathVariable Long id){

	Optional<Player> opPlayer = playerService.getPlayerById(id);

	if (!opPlayer.isPresent()) {
		return ResponseEntity.notFound().build();
	}

	return ResponseEntity.ok(opPlayer);
	
}
	
	//update player name
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody Player playerDetails, @PathVariable Long id){
		
		Optional<Player> opPlayer = playerService.getPlayerById(id);
		
		if(!opPlayer.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		opPlayer.get().setName(playerDetails.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(opPlayer.get()));
}
	
	
	 //delete player
	 @DeleteMapping("delete/{id}")
	  public ResponseEntity<?> delete (@PathVariable Long id){
		  
		  if(!playerService.getPlayerById(id).isPresent()) {
			  
			  return ResponseEntity.notFound().build();
		  }
		  
		  playerService.deletePlayerbyId(id);
		  return ResponseEntity.ok().build();
	  }
	  
	  //get all players
	  @GetMapping("/getAll")
	  public List<Player> getAllPlayers(){
		  
		  List<Player> players = StreamSupport
				  .stream(playerService.findAll()
						  .spliterator(), false)
				  .collect(Collectors.toList());
		  
		  return players;
				  
				  
	  }
	  
	  //get Ranking
	  @GetMapping("/ranking")
	  public List<Player> getRanking() throws Exception{
		  
		  List<Player> players = StreamSupport
				  .stream(playerService.getRanking()
						  .spliterator(), false)
				  .collect(Collectors.toList());
		  
		 return players;
	  }
	  
	  //get worst Player
	  @GetMapping("/ranking/loser")
	  public ResponseEntity<?> getWorstPlayer () throws Exception {
		  
		  Optional<Player> opPlayer = playerService.getWorstPlayer();

			if (!opPlayer.isPresent()) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(opPlayer);
	  }
	  
	  
	  //get best player
	  @GetMapping("/ranking/winner")
	  public ResponseEntity<?> getBesttPlayer () throws Exception {
		  
		  Optional<Player> opPlayer = playerService.getBestPlayer();

			if (!opPlayer.isPresent()) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(opPlayer);
	  }

}


