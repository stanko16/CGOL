# Conway's Game Of Life

Language: Java


This program simuates the game of life. You will have a matrix as large as your screen. You can either turn on cells manually or generate a random matrix.

(Mathworld.wolfram.com/GameOfLife)

The game of life is the best-known two-dimensional cellular automaton. The life cellular automaton is run by placing a number of filled cells on a two-dimensional grid. Each generation then switches cells on or off depending on the state of the cells that surround it. The rules are defined as follows. All eight of the cells surrounding the current one are checked to see if they are on or not. Any cells that are on are counted, and this count is then used to determine what will happen to the current cell.

1. Death: if the count is less than 2 or greater than 3, the current cell is switched off.

2. Survival: if (a) the count is exactly 2, or (b) the count is exactly 3 and the current cell is on, the current cell is left unchanged.

3. Birth: if the current cell is off and the count is exactly 3, the current cell is switched on. 

