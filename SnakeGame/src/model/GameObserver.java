package model;

/**
 * this interface is a super class of the classes the will observes the game
 * state (GameSimulator.java,SoundEffects.java)
 * 
 * the update method is called from the Game.java whenever we need to change the
 * observers
 * 
 * @see SoundEffects.java -> plays a bubble sound when object is eaten in Game
 * @see GameSimulator.java -> pop a points graphics when object is eaten in Game
 * 
 * @author Lawrence Ashkar
 * 
 */
public interface GameObserver {

	public void update();

}
