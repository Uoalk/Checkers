# Checkers
Division of work:
Grant - min max and advanced classes
Jude - Basic classes for gameplay, UI / print
Uly - debug, docs, min max help and efficiency 

How min max works:
0.1 * how close each of your paws in from becoming a king + 2 * your kings - opponents score.
We currently look 3 turns into the future. At about 5 the recursion takes a very long time and is not reasonable for gameplay.

You can play the UI or you can watch the UI’s play. We use depth-first search and recursion for our min max. This makes it hard to identify similar branches but is more efficient in the long run. If we had more time we might try implementing threading to speed up the search but more then 2X.

 

