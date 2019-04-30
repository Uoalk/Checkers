# Checkers
Division of work:
Grant - min max and advanced classes
Jude - Basic classes for gameplay, UI / print
Uly - debug, docs, min max help and efficiency

How min max works:
0.1 * how close each of your paws in from becoming a king + 2 * your kings - opponents score.
We currently look 3 turns into the future. At about 5 the recursion takes a very long time and is not reasonable for gameplay.

You can play the UI or you can watch the UI’s play. We use depth-first search and recursion for our min max. This makes it hard to identify similar branches but is more efficient in the long run. If we had more time we might try implementing threading to speed up the search but more then 2X.

![Image 1](https://github.com/Uoalk/Checkers/blob/master/Screenshot%201.jpg)
![Image 2](https://github.com/Uoalk/Checkers/blob/master/Screenshot%202.jpg )

# Files
Main - has the UI, runs the demo/play options
Space - the empty slot on the board where there are no pieces present
Piece - each piece on the board, using these you can get moves for the human or board class to play
Vector - just an x, y coordinate for the game
Move - keeps track of where you are, where you're moving to, and all the legal options for the player/board
Board - plays the actual game using spaces, pieces, and moves, also does the min max algorithm to find the best moves into the future
Human - allows a human to play the game and make moves against the AI
