package bin;

import bin.*;

/**
 * The {@code Bingo} class is the entry point for the Bingo game.
 * It creates instances of the {@code play}, {@code card} (user), and {@code card} (computer) classes
 * and starts the game by invoking the {@code start()} method on the user and computer cards.
 * 
 * throws Exception for joining all the stared to threads to finish the program
 */

public class Bingo {

     /**
     * The main method that initializes the game and starts the user and computer cards.
     *
     * @param args Command-line arguments (not used in this application).
     * throws Exception If an exception occurs during the execution of the game and
     *  for joining all the stared to threads to finish the program
     */
	public static void main(String[] args) throws Exception
	{
			play player =new play();
			card user = new card("User",player,"user");
			card com= new card("Computer",player);
			user.other=com;
			com.other=user;
			user.DisplayCard();
			user.start();
			com.start();
                        
                        
                        user.join();
                        com.join();
	}

}
