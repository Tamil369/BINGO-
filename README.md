# Bingo Game in Java

This project is a simple implementation of the classic Bingo game in Java. It includes three main components:

- `bingo.java`: The main class that initializes and manages the game.
- `card.java`: Represents a Bingo card, handling card creation and number marking.
- `play.java`: Contains the logic for playing the game, including number drawing and checking for winners.

## Features

- **Random Card Generation**: Each game generates a new Bingo card with random numbers.
- **Number Drawing**: Simulates the drawing of numbers until a winner is found.
- **Winner Checking**: Automatically checks for winning conditions on each card by checking a continuous 0 either in rows or columns or diagonally.

## How to Play

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/bingo-game-java.git
   cd bingo-game-java
2. Compile the code: Use javac to compile the Java files.
      '''javac bingo.java card.java play.java'''
3. Run the game: Execute the main class to start the game.
        '''java bingo'''
   
