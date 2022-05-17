package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;


@Entity
@Table(name="Player")
public class Player implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	/*strategy=GenerationType.IDENTITY: Se basa en una 
	 * columna de base de datos con incremento 
	 * automático y permite que la base de datos genere un 
	 * nuevo valor con cada operación de inserción. */
	
	@Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idPlayer;
	
	@Column(name="Name", nullable = false, length=30, unique=true)
	private String name;
	
	@Column(name="Registration_date")
	private LocalDate date;
	
	@Column(name="Results_games")
	@OneToMany(mappedBy = "player")
	private List<Game> games;
	
	@Column(name="Percentage_victorys")
	private double victory;
	
	@Column(name="Wins")
	private int win;
	
	/*campo int victory:  
	 * 
	 * campo percentage_Victorys: games.sice()/victory
	 * 
	 * */
	
	/* Por alguna razón, el programa da 
	 * error si activo este campo.
	 * 
	 * @Column(name="Players_List")
	@OneToMany(mappedBy = "player")

	private List<Player> players;*/

	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

   public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public double getVictory() {
		return victory;
	}
	
	public double setVictory() {
		
		double n = (win/(games.size()-1))*100;
		
		//bucle for each para captar cada win de cada juego.
		//esto se convierte en la variable win que se divide
		//entre los games
		
		return n;
	}

	/*public double setPercentageOfVictory(Player player) {
		
		List<Game> listGames = player.getGames();
		
		int wins = listGames.forEach((game.getVicory)-> true);
		
		double percentage = (listGames.size()-1)/wins;
		
		return percentage;
	}

	/*public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}*/

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", date=" + date + ", games=" + games + ", victory="
				+ victory + "]";
	}
	
	
	

}
