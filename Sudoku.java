
public class Sudoku {

	/**
	 * Default constructor -- construct an empty Sudoku puzzle
	 */
	public Sudoku () {
	}

	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param board
	 * @throws Exception
	 */
	public void inputBoard(char [][] board) throws Exception {
	}

	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 */
	public boolean solveSodoku () {
		return false;
	}

	/**
	 * Prints the Sudoku board to the console.
	 */
	public void printBoard() {
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		sudoku.inputBoard(char [][] board);
		sudoku.solveSodoku();
		sudoku.printBoard();
	}
	
}
