//@name: Mia Kobayashi
//@date and version: 6 Feb 2022, v.1
//CS245 Lab 2: Sudoku

public class Sudoku {
	private final int SIZE = 9;
	private char[][] board; //instance variable, space O(1) (generally n^2, but bc size is constant, it's technically 81 for the board, but reduce to 1 bc 81 is 1

	/**
	 * Default constructor -- construct an empty Sudoku board / puzzle.
	 * And each board position is set to an inital int "0".
	 */
	public Sudoku () {
		this.board = new char[SIZE][SIZE]; //instance
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				this.board[row][col] = '.';
			}
		}
	} //end Sudoku()


	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param numForBoard 9x9 Sudoku board
	 * @throws IllegalArgumentException
	 */
	public void inputBoard(char[][] numForBoard) throws IllegalArgumentException {
		//checks if valid board size
		if (numForBoard.length != SIZE) { //checks if height is SIZE
			throw new IllegalArgumentException("Wrong dimension of a Sudoku board.");
		}
		else {
			for (int i = 0; i < SIZE; i++) { //jagged array situation, checks if each row is SIZE
				if (numForBoard[i].length != SIZE) {
					throw new IllegalArgumentException("Wrong dimension of a Sudoku board.");
				}
			}
		}

		//board to solve
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				//check if starting nums are valid
				if (numForBoard[row][col] != '0' && numForBoard[row][col] != '.') { //if not empty spot, get row & col #
					if (isSafe(numForBoard, row, col, numForBoard[row][col]) == false) { //!isSafe()
						throw new IllegalArgumentException("Invalid starting Sudoku board.  Puzzle cannot be solved.");
					}
				}
				this.board[row][col] = numForBoard[row][col];
			}
		}
	} //end inputBoard()


	/**
	 * Checks if the place on Sudoku board is safe (row, col, box)
	 * @return true if empty spot is valid place on board, false if not
	 */
	public boolean isSafe(char[][] board, int row, int col, char ch) { //runtime O(n^2), space O(1), 5 vars created
		int boardLen = board.length;

		//check if num is already in row, change col#
		for (int c = 0; c < boardLen; c++) {
			if (c != col && board[row][c] == ch) {
				return false; //desiredNum cannot be placed here
			}
		}

		//check if num is already in col, change row#
		for (int r = 0; r < boardLen; r++) {
			if (r != row && board[r][col] == ch) {
				return false; //desiredNum cannot be placed here
			}
		}

		//check if num is already in box, checking 1 of 9 3x3 boxes
		int sqrt = (int)Math.sqrt(boardLen); //3
		int boxRowStart = (row / 3) * 3; //start 0, 3, or 6		//alt: row - (row % sqrt);
		int boxColStart = (col / 3) * 3; //start 0, 3, or 6		//alt: col - (col % sqrt);
		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
			for (int c = boxColStart; c < boxColStart + sqrt; c++) {
				if (r != row && c != col && board[r][c] == ch) {
					return false; //desiredNum cannot be placed here
				}
			}
		}

		return true; //safe to place desiredNum
	} //end isSafe()


	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 * Utilizes backtracking
	 */
	public boolean solveSudoku(char[][] board) { // O(9^m), m is blank positions that should be filled in
		//backtracking: find first empty box, try numbers, validate row/col/3x3 through isSafe(), repeat

		//check row
		int row = -1; //initialize row, col
		int col = -1;
		boolean isEmpty = true;
		for (int r = 0; r < SIZE; r++) { // O(n^2) time
			for (int c = 0; c < SIZE; c++) {
				if (this.board[r][c] == '0' || this.board[r][c] == '.') { //if empty spot, get row & col #
					row = r;
					col = c;

					isEmpty = false; //currently no longer empty, break nested for loop
					break;
				}
			}
			if (isEmpty == false) { //!isEmpty, break outer for loop
				break;
			}
		}

		if (isEmpty == true) { //isEmpty
			return true; //puzzle is solved
		}

		//backtrack !
		for (int desiredNum = 1; desiredNum <= SIZE; desiredNum++) { //1-9, inclusive
			char dNum = String.valueOf(desiredNum).charAt(0);
			if (isSafe(this.board, row, col, dNum) == true) { //check if spot is valid
				this.board[row][col] = dNum; //place desired num in spot

				if (solveSudoku(board) == true) { //recursive call, try to solve next empty spot
					return true; //board[row][col] stays as desiredNum

				}
				else { //else, replace desiredNum back to 'default' / empty spot
					this.board[row][col] = '0'; //replace board[row][col]
				}
			}
		}

		return false; //cannot solve puzzle in this way
	} //end solveSudoku()


	/**
	 * Checks the final Sudoku board to makes sure it was solved.
	 * @param board 9x9 Sudoku board
	 * @throws IllegalArgumentException
	 */
	public void checkFinalBoard(char[][] board) throws IllegalArgumentException {
		//if empty spot still exists, it's an unsolveable board, throw exception
		for (int row = 0; row < SIZE; row ++) {
			for (int col = 0; col < SIZE; col++) {
				if (this.board[row][col] == '0' || this.board[row][col] == '.') {
					throw new IllegalArgumentException("Invalid starting Sudoku board.  Puzzle cannot be solved.");
				}
			}
		}
	}


	/**
	 * Prints the Sudoku board to the console.
	 * A String representation of the board, showing the contents of each space
	 */
	public void printBoard() {
		int sqrt = (int)Math.sqrt(SIZE); //3
		System.out.print(" -----------------------\n"); //top row

		//starts top left
		for (int row = 0; row < SIZE; row++) {
			System.out.print("| "); //board edge (left)

			for (int col = 0; col < SIZE; col++) {
				System.out.print(this.board[row][col] + " ");
				if ((col + 1) % sqrt == 0) {
					System.out.print("| "); //dividing vert. lines + board edge (right)
				}
			}
			System.out.print("\n");
			if ((row + 1) % sqrt == 0) {
				System.out.print(" -----------------------\n"); //dividing horz. lines + bottom row
			}
		}
	} //end printBoard()


	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(); //instantiating class
//		sudoku.printBoard(); //starter board

		//board with only 2 filled in numbers
//		char[][] board = new char[][] {{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '1', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '2', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
//				{'0', '0', '0', '0', '0', '0', '0', '0', '0'}};

		//https://www.sudokuoftheday.com/dailypuzzles/2022-02-06/beginner/solution
		char[][] board = new char[][] {{'0', '0', '0', '1', '2', '7', '0', '0', '0'},
										{'0', '3', '8', '0', '0', '0', '0', '9', '0'},
										{'0', '0', '4', '9', '0', '0', '0', '0', '6'},
										{'4', '2', '7', '3', '5', '0', '0', '1', '8'},
										{'3', '0', '0', '2', '0', '8', '0', '0', '5'},
										{'8', '5', '0', '0', '4', '1', '6', '3', '2'},
										{'5', '0', '0', '0', '0', '9', '7', '0', '0'},
										{'0', '9', '0', '0', '0', '0', '2', '8', '0'},
										{'0', '0', '0', '4', '7', '2', '0', '0', '0'}};
		// solu:
		// 		{{9, 6, 5, 1, 2, 7, 8, 4, 3},
		//		{2, 3, 8, 5, 6, 4, 1, 9, 7},
		//		{1, 7, 4, 9, 8, 3, 5, 2, 6},
		//		{4, 2, 7, 3, 5, 6, 9, 1, 8},
		//		{3, 1, 6, 2, 9, 8, 4, 7, 5},
		//		{8, 5, 9, 7, 4, 1, 6, 3, 2},
		//		{5, 4, 2, 8, 3, 9, 7, 6, 1},
		//		{7, 9, 3, 6, 1, 5, 2, 8, 4},
		//		{6, 8, 1, 4, 7, 2, 3, 5, 9}};

		sudoku.inputBoard(board); //sudoku.inputBoard(int[][] board); //starter code
		sudoku.printBoard(); //unsolved board
		sudoku.solveSudoku(board);
		sudoku.checkFinalBoard(board);
		sudoku.printBoard(); //solved board
	} //end main
} //end class Sudoku
