package bin;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import bin.*;

/**
 * The {@code card} class represents a player's Bingo card and its functionality.
 * It extends the {@code Thread} class to run the game concurrently.
 * 
 * 
 */
public class card  extends Thread 
{
	String name;
	int row=5,col=5;
	int card[][];
	card other;
	int selectnum;
	play p;
    boolean mode;
    /**
     * Initializes a new instance of the {@code card} class for the computer.
     * 
     * This constructor will work only if they creating instance of  {@code card } for computer .
     *
     * @param x The name of the player.
     * @param v The instance of the {@code play} class.
     */
	public card(String x,play v)
	{
		card= new int[row][col];
		name=x;
		p=v;
		generateCard();
		this.mode = false;
	}
        /**
     * Initializes a new instance of the {@code card} class for the user.
     * This constructor will work only if they creating instance of  {@code card } for user .
     * @param x    The name of the player.
     * @param v    The instance of the {@code play} class.
     * @param mode The mode indicating if it's a user or computer card.
     */
	public card(String x,play v,String mode)
	{
		card= new int[row][col];
		name=x;
		p=v;
		this.mode = true;
		generateCard();
	}
    /**
     * Generates random numbers to fill the Bingo card.
     * Using  Random class 
     */
	private void generateCard()
	{
		Random random = new Random();
        Set<Integer> generatedNumbers = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int randomNumber;
                do 
                {
                    randomNumber = random.nextInt(25) + 1;
                } while (generatedNumbers.contains(randomNumber));

                card[i][j] = randomNumber;
                generatedNumbers.add(randomNumber);
            }
        }
	}
     /**
     * Displays the current state of the Bingo card.
     */
	public void DisplayCard()
	{
		System.out.println(name + "\tCard is :\n");
		 for (int i = 0; i < row; i++) 
		 {
	            for (int j = 0; j < col; j++) 
	            {
	                System.out.printf("%2d ", card[i][j]);
	            }
	            System.out.println();
		 }
	}
    /**
     * Checks whether the Bingo card has a complete line with zeros.
     * This method examines both horizontal, vertical, and diagonal lines of zeros on the card
     * to determine if all elements in a line are zeros. If any complete line with zeros is found,
     * the method returns {@code true}, indicating a winning condition. Otherwise, it returns {@code false}.
     *
     * @return {@code true} if there is a complete line with zeros on the Bingo card, otherwise {@code false}.
     */
	public boolean hasZeroLine() {
        return hasHorizontalZeroLine() || hasVerticalZeroLine() || hasDiagonalZeroLine();
    }
    /**
     * Checks if the Bingo card has a complete horizontal line with zeros.
     *
     * @return {@code true} if there is a complete horizontal line with zeros, otherwise {@code false}.
     */
    private boolean hasHorizontalZeroLine() {
        for (int i = 0; i < row; i++) {
            boolean hasZeroLine = true;
            for (int j = 0; j < col; j++) {
                if (card[i][j] != 0) {
                    hasZeroLine = false;
                    break;
                }
            }
            if (hasZeroLine) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the Bingo card has a complete vertical line with zeros.
     *
     * @return {@code true} if there is a complete vertical line with zeros, otherwise {@code false}.
     */
    private boolean hasVerticalZeroLine() {
        for (int j = 0; j < col; j++) {
            boolean hasZeroLine = true;
            for (int i = 0; i < row; i++) {
                if (card[i][j] != 0) {
                    hasZeroLine = false;
                    break;
                }
            }
            if (hasZeroLine) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the Bingo card has a complete diagonal line with zeros.
     *
     * @return {@code true} if there is a complete diagonal line with zeros, otherwise {@code false}.
     */
    private boolean hasDiagonalZeroLine() {
        boolean hasZeroLine1 = true;
        boolean hasZeroLine2 = true;

        for (int i = 0; i < row; i++) {
            if (card[i][i] != 0) {
                hasZeroLine1 = false;
            }

            int oppositeIndex = col - 1 - i;
            if (card[i][oppositeIndex] != 0) {
                hasZeroLine2 = false;
            }
        }

        return hasZeroLine1 || hasZeroLine2;
    }
    /**
     * Updates the Bingo card by marking the specified number as zero.
     *
     * @param num The number to be marked as zero in card .
     */
    public void updateCard(int num) 
    {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (card[i][j] == num) {
                    card[i][j] = 0;
                    return; 
                }
            }
        }
    }
     /**
     * Runs the Bingo game for the player (user or computer) in a separate thread.
     * If the card is in user mode, it prints a welcome message and the initial state of the user's card.
     * The method then enters a loop where it continues to prompt the current player for a number
     * and updates both the current player's card and the opponent's card until a complete line with zeros is achieved.
     * If the current player wins, it prints the winning card and the opponent's losing card, and a concluding message.
     * The loop includes a delay of 1000 milliseconds between each iteration to slow down the game for better visibility.
     * This function almost Call all previous function like update, checking has any zero lines functions 
     * and ready to play with @code play once the Thread is started 
     */
	@Override
	public void run()
	{
		if(mode)
		{
			System.out.println(name + "	card  :");
			System.out.println("Your Bingo card is ready. Let the game begin!");
			
		}
		while (!(hasZeroLine()))
		{
			selectnum=p.Getnumber();
			this.updateCard(selectnum);
			other.updateCard(selectnum);
			if(mode) DisplayCard();
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
		}
		if(hasZeroLine())
		{
			System.out.println(name + "   is winner :");
			DisplayCard();
			System.out.println(other.name + " is loser :");
			other.DisplayCard();
			System.out.println("Thanks for playing BINGO "+ name+ "  is WINNER <3");
		}
	}
}
