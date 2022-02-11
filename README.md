# Lab02-Sudoku-Solver
The backtracking algorithm, an algorithm used to solve problems incrementally.  
For this lab, it will be used to solve a 9x9 sudoku puzzle.  



# Example:
<pre><code> 
Input: board = {{"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}}
                
Output: solution = {{"5","3","4","6","7","8","9","1","2"},
                    {"6","7","2","1","9","5","3","4","8"},
                    {"1","9","8","3","4","2","5","6","7"},
                    {"8","5","9","7","6","1","4","2","3"},
                    {"4","2","6","8","5","3","7","9","1"},
                    {"7","1","3","9","2","4","8","5","6"},
                    {"9","6","1","5","3","7","2","8","4"},
                    {"2","8","7","4","1","9","6","3","5"},
                    {"3","4","5","2","8","6","1","7","9"}}
</code></pre>

**How the code works:**  
An initial sudoku board is declared and initialized to a 'board' variable in the main method.  The initial board is then checked with the inputBoard() method to see if it is solveable by its dimensions and starting numbers (through the isSafe() method).  

In the case that an initial board is unsolveable or of the wrong dimension (not 9x9), an IllegalArgumentException will be thrown.  The exception is either thrown from the inputBoard() method, which catches an invalid board before solving, or the checkFinalBoard() method, which catches an invalid board after trying to solve the puzzle.  checkFinalBoard() specifically catches an unsolveable puzzle even if the inital board meets the correct dimension and supposedly proper starting numbers.  

If an inital board is solveable, the board is solved with solveSudoku().  
Until the board is solved, solveSudoku() is called recursively, finding the first empty spot, trying desired numbers that could be placed in the spot, validates the desired number in the spot through isSafe(), and then repeats the process.  
When a desired number is being placed onto the board at a specific spot, the isSafe() method checks that spot's row, column, and 1 of 9 3x3 boxes on the board.  


**Runtime:**  
Sudoku(): O(n^2)  
inputBoard(): O(n^2)  
isSafe(): O(n^2)  
solveSudoku(): O(n^2)  
printBoard(): O(n^2)  
Where n is the size of the array, starting from 0 going up to n by 1.  
Thus each loop runs n times (and a nested for loop resulting in n^2).  

**Space complexity:**  
Sudoku(): O(n^2); an nxn array is created; in this case, since the size of the 2d array is known as 9, O(1)  
inputBoard(): O(1); 3 int variables are created  
isSafe(): O(1); 8 int variables are created  
solveSudoku(): O(1); 5 int, 1 char, 1 boolean variables are created  
printBoard(): O(1); 3 int variables are created  
