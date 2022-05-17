package cat.barcelonactiva.bargunyo.alfonso.DiceGame3.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGame;

	@Column(name = "Dice_1", nullable = false, updatable = false)
	private int dice1;

	@Column(name = "Dice_2", nullable = false, updatable = false)
	private int dice2;
	
	//¿Dónde crear el registro de la partida ganada, en Player o en Game? ¿Cómo comunicarlas?

	@Column(name = "Victory", nullable = false, updatable = false)
	private boolean win;
	
	/*CascadeType.PERSIST: Al persitir esta entity, 
	 * también persistimos las entidades que tenga en sus campos. 
	 * Se recomienda su uso para evitar errores con el
	 * EntityManager*/
	
	/*Fetch Lazy: se cargan los players cuando se los solicita. 
	 * Fetch Eager: ser cargan con el resto de campos */

	@JoinColumn(name = "Player_Id")
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Player player;

	public Game(Long idGame, int dice1, int dice2, boolean win, Player player) {

		this.idGame = idGame;
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.win = win;
		this.player = player;
	}

	public Game(Player player) {
		this.player = player;
	}

	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game playGame(Player player) {

		Game game = new Game(player);

		int dice1 = gameDice();
		int dice2 = gameDice();

		game.setDice1(dice1);
		game.setDice2(dice2);

		if (dice1 + dice2 == 7) {
			game.isWin();
			player.setVictory();
		} else {
			game.setWin(false);
		}

		return game;
	}

	// Play the game

	private int gameDice() {
		Random random = new Random();
		return random.nextInt(6) + 1;

	}
}
