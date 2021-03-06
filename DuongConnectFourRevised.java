package ConnectFourProject;
import java.util.Scanner; import java.util.InputMismatchException;
public class DuongConnectFourRevised 
{
	String color;
	public DuongConnectFourRevised(String c)
	{
		this.color = c;
	}
	public static void main(String[]args)
	{
		Scanner input = new Scanner(System.in);
		DuongConnectFourRevised red = new DuongConnectFourRevised("R");
		DuongConnectFourRevised yellow = new DuongConnectFourRevised("Y");
		String [][] gameBoard = new String[6][15]; boolean win = false; boolean resume = false;
		for(int i = 0; i < gameBoard.length; i++){
			for(int h = 1; h < gameBoard[i].length-1; h += 2){
				gameBoard[i][h] = " ";
			}
			for(int j = 0; j < gameBoard[i].length; j += 2){
				gameBoard[i][j] = "|";
			}
		}
		do {
			do {
			try {
				resume = false;
				System.out.println("Drop a red disk at column (0-6): ");
				if(red.placeDisk(input.nextInt(), gameBoard) == true)
					resume = true;
				display(gameBoard);
				if(red.checkLines(gameBoard) == true){
					win = true;
					System.out.println("\nThe red player won.");
					break;
				}
				if(red.checkDiagonals(gameBoard) == true){
					win = true;
					System.out.println("\nThe red player won.");
					break;
				}
				if(draw(gameBoard) == true)
				{
					System.out.println("The game is a draw.");
					win = true;
				}
			}
			catch(IndexOutOfBoundsException ex){
				System.out.println("Please enter a column between 0 and 6. Try again.");
				resume = true;
			}
			catch(InputMismatchException ex) {
				System.out.println("Please enter an integer column between 0 and 6. Try again.");
				resume = true; input.nextLine();
			}}while(resume == true);
			if(win == false) {
				do {
					try {
						resume = false;
						System.out.println("Drop a yellow disk at column (0-6): ");
						if(yellow.placeDisk(input.nextInt(), gameBoard) == true)
							resume = true;
						resume = false;}
					catch(IndexOutOfBoundsException ex){
						System.out.println("Please enter a column between 0 and 6");
						resume = true;
					}
					catch(InputMismatchException ex) {
						System.out.println("Please enter an integer column between 0 and 6");
						resume = true; input.nextLine();
					}}while(resume == true);
				display(gameBoard);
				if(yellow.checkLines(gameBoard) == true){
					win = true;
					System.out.println("\nThe yellow player won.");
					break;
				}
				if(yellow.checkDiagonals(gameBoard) == true){
					win = true;
					System.out.println("\nThe yellow player won.");
					break;
				}
				if(draw(gameBoard) == true)
				{
					System.out.println("The game is a draw.");
					win = true;
				}
				System.out.println();
			}while(resume == true && win == false);
		}while(win == false);
		input.close();
	}
/*
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------
 * placeDisk method which accepts an int parameter and a 2D String array parameter
 * places a chip at a specified column
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
	public boolean placeDisk(int c, String [][] game)
	{
		int column = c * 2 + 1;
		int rowTracker = 5;
		if(game[0][column] != " ")
		{
			System.out.println("Column Full");
			return(true);
		}
		else{
			while(rowTracker > 0 && game[rowTracker][column] != " ")
			{
				rowTracker--;
			}
			game[rowTracker][column] = this.color;
			rowTracker = 5;
		}
		return(false);
	}
/*
 * -------------------------------------------------------------------------------------------------------------------------------------------------------------
 * checkLines method which takes a 2D String array parameter and returns a boolean
 * checks rows and columns for 4 chips in a row
 * if 4 chips in a row, returns true; else, returns false
 * -------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
	public boolean checkLines(String [][] game)
	{
		int counter = 0; 
		for(int a = 0; a < game.length; a++)
		{
			counter = 0;
			for(int b = 1; b < game[a].length; b += 2)
			{
				if(game[a][b].equals(this.color))
				{
					counter++;
					if(counter == 4)
					{
						return(true);
					}
				}
				else
				{
					counter = 0;
				}
			}
		}
		counter = 0;
		for(int c = 1; c < game[0].length; c += 2)
		{
			counter = 0;
			for(int d = 5; d >= 0; d--)
			{
				if(game[d][c].equals(this.color))
				{
					counter++;
					if(counter == 4)
					{
						return(true);
					}
				}
				else
				{
					counter = 0;
				}
			}
		}
		return(false);
	}
/*
 * -------------------------------------------------------------------------------------------------------------------------------------------------------------
 * checkDiagonals method which takes a 2D String array parameter and returns a boolean
 * checks both diagonals for 4 chips in a row
 * if 4 chips in a row, returns true; else, returns false
 * -------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
	public boolean checkDiagonals(String [][] game)
	{
		int traverseColumn; int traverseRow; int counter;
		for(int i = 3; i < 6; i++) {
			counter = 0; traverseColumn = 1; traverseRow = i;
			do {
				if(game[traverseRow][traverseColumn].equals(this.color)) {
					counter++; traverseRow--; traverseColumn+=2;
					if(counter == 4)
						return(true);
				}
				else {
					counter = 0; traverseRow--; traverseColumn+=2;
				}
			}while(traverseRow >= 0);
		}
		for(int j = 3; j < 9; j+=2) {
			counter = 0; traverseRow = 5; traverseColumn = j;
			do {
				if(game[traverseRow][traverseColumn].equals(this.color)) {
					counter++; traverseRow--; traverseColumn+=2;
					if(counter == 4) {
						return(true);
					}
				}
				else {
					counter = 0; traverseRow--; traverseColumn+=2;
				}
			}while(traverseColumn < 15);
		} 
		
		for(int i = 2; i >= 0; i--) {
			counter = 0; traverseColumn = 1; traverseRow = i;
			do {
				if(game[traverseRow][traverseColumn].equals(this.color)) {
					counter++; traverseRow++; traverseColumn+=2;
					if(counter == 4)
						return(true);
				}
				else {
					counter = 0; traverseRow++; traverseColumn+=2;
				}
			}while(traverseRow <= 5);
		}
		for(int j = 3; j < 9; j+=2) {
			counter = 0; traverseRow = 0; traverseColumn = j;
			do {
				if(game[traverseRow][traverseColumn].equals(this.color)) {
					counter++; traverseRow++; traverseColumn+=2;
					if(counter == 4) {
						return(true);
					}
				}
				else {
					counter = 0; traverseRow++; traverseColumn+=2;
				}
			}while(traverseColumn < 15);
		} return(false);
	}
/*
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------
 * draw method which traverses the 2D String array gameboard and detects a draw
 * if game is a draw, returns true; else, returns false
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
	public static boolean draw(String[][]game)
	{
		int emptySpaces = 0;
		for(int a = 0; a < game.length; a++){
			for(int b = 0; b < game[a].length; b++){
				if(game[a][b] == " ")
					emptySpaces++;
			}
		}
		if(emptySpaces == 0){
			return(true);
		}return(false);		
	}
/*
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------
 * display method which takes a 2D String array parameter
 * prints out the current game board
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
	public static void display(String [][] game)
	{
		for(int a = 0; a < game.length; a++){
			System.out.println();
			for(int b = 0; b < game[a].length; b++){
				System.out.print(game[a][b]);
			}
		}
		System.out.println("\n-------------------------");
	}
}