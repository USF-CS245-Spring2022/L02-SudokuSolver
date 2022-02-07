
public class Sudoku {
	private final int SIZE = 9;
	private String[][] board;
	/**
	 * Default constructor -- construct an empty Sudoku board / puzzle.
	 * And each board position is set to an inital String " X ".
	 */
	public Sudoku () {
		board = new String[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = "x";
			}
		}
	} //end Sodoku()

	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param board
	 * @throws Exception
	 */
	public void inputBoard(char [][] board) throws Exception {

	} //end inputBoard()

	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 */
	public boolean solveSodoku() {
		return false;
	} //end solveSodoku()

	/**
	 * Prints the Sudoku board to the console.
	 * A String representation of the board, showing the contents of each space
	 */
	public void printBoard() {
		System.out.print("  -----------------------\n"); //top row

		//starts top left
		for (int row = 0; row < SIZE; row++) {
			System.out.print(" | "); //board edge (left)

			for (int col = 0; col < SIZE; col++) {
				System.out.print(board[row][col] + " ");
				if ((col + 1) % 3 == 0) {
					System.out.print("| "); //dividing vert. lines + board edge (right)
				}
			}
			System.out.print("\n");
			if ((row + 1) % 3 == 0) {
				System.out.print("  -----------------------\n"); //dividing horz. lines + bottom row
			}
		}
	} //end printBoard()
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
//		sudoku.inputBoard(char [][] board);
//		sudoku.solveSodoku();
		sudoku.printBoard();
	}
	
}
