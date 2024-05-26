package bin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import bin.*;
/**
 * The {@code play} class represents the core functionality of the Bingo game.
 * It includes methods for user input based on their wish, generating random numbers for the computer without duplicates,
 * and managing the state of the game.
 */

public class play 
{
	int cho;
	int num;
	static int access=0;
	boolean changer ;
	
	Set<Integer> numbersSet;
        
     /**
     * Initializes a new instance of the {@code play} class.
     * This class instance is generate once and their instance are used to pass in two player
     * It also prints a welcome message to the console.
     * @author Tamilarasu, Surya, Thaimozhiarasan, Ragul, Nilavarasan
     */
    public play() 
    {
        numbersSet = new HashSet<>();
        initializeNumbersSet();
        System.out.println("Welcome to the Bingo Game!");
        System.out.println("Get ready to enjoy the excitement of Bingo.");
        
    }
    /**
     * Initializes the set of numbers for the game (1 to 25).
     */
    private void initializeNumbersSet() {
        for (int i = 1; i <= 25; i++) {
            numbersSet.add(i);
        }
    }
    /**
     * Allows the user to input a number and validates it against the set.
     *
     * @return The valid user input number.
     * @throws IllegalStateException If the set is empty.
     */
    public int Usernumber() {
        if (numbersSet.isEmpty()) {
            throw new IllegalStateException("The numbers in card is empty.");
        }

        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

        System.out.print("Enter your number : ");
        int userNumber = in.nextInt();

        if (numbersSet.contains(userNumber)) {
            numbersSet.remove(userNumber);
            return userNumber;
        } else {
            System.out.println("Invalid number. The number is choosen already.........");
            return Usernumber();
        }
    }
    /**
     * Generates a random number for the computer's turn from the available set.
     *
     * @return The randomly generated computer number.
     * @throws IllegalStateException If the set is empty.
     */
    public int Randomforcom() throws IllegalStateException {

        if (numbersSet.isEmpty()) {
            throw new IllegalStateException("The set is empty.");
        }

        List<Integer> shuffledList = new ArrayList<>(numbersSet);
        Collections.shuffle(shuffledList);

        int cNumber = shuffledList.remove(0);
        numbersSet.remove(cNumber);

        return cNumber;
    }
    /**
     * Gets the next number for the current player (user or computer) in a synchronized manner.
     * If the current player is the user, the method prompts the user for input by calling predefined functions,
     * validates it against the available set of numbers, removes the chosen number from the set,
     * and returns the validated user input.
     * If the current player is the computer, the method generates a random number for the computer
     * from the available set, removes the chosen number from the set, and returns the generated number.
     * The method uses thread synchronization to control the turn, ensuring that only one player can
     * access and modify the set of available numbers at a time. After processing, the access count is
     * incremented to switch to the next player's turn.
     *
     * @return The chosen number for the current player either user or computer, usually user got the first turn to choose number.
     */
    synchronized public int Getnumber()
    {
    	if(access%2==0) 
    	{
    		num =Usernumber();
    		access++;
    		return num;
    	}
    	else if (access%2==1) 
    	{
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
    		num =Randomforcom();
    		System.out.println("Computer choosen number is "+num);
    		access++;
    		return num;
    	}
    	return -1;
    }
}
