# tic-tac-toe

<b>HOW TO RUN</b>

Build and run using gradle (https://gradle.org):
  - to build: '[ROOT_FOLDER]/gradlew build';
  - to run: '[ROOT_FOLDER]/build/libs/tic-tac-toe.jar [relative path to configuration file]'

<b>CONFIGURATION FILE FORMAT</b>
  
  Text file with mulitple row:
  - First row contains play field dimension size
  - Next two rows contain a single character each - labels for human players. 
  - Final row contains the label for computer player.
  
  Sample: 
  ```
    3
    A
    B
    C
  ```

<b>DESIGN</b>
  * Players:
    - Abstract Player class implements player configuration and taking a move action. It has reference to a playfield to make the move on.
    - Concrete player implementations are HumanPlayer and ComputerPlayer implement next move selection process.
  * The state of the game is descrbed with three main data structure:
    - Board: represents the play field with all the player labels on the positions corresponding to made moves. Used to output the state of the play field.
    - Winnable: represents possible paths or sequence of play filed positions to win the game. The path is considered eligible for winning the game if the single or no plyer occupied positions in the path. As soon as two different players put their labels in the positions of the path the path can't be used to win the game. Used to detect a game draw.
    - Available: lists all available (not occupied by any player yet) for moves positions on the play field. Mainly used by computer player to pick a position for the next move.
  * The game process:
    - The players make moves one after another starting with the random player.
    - The game stops if some player wins the game or there is no winnable path for the game.
