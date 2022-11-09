import java.util.*;
import java.lang.*;
import java.io.*;

public class technicalTest{
    static final int c = 4;// number of colors
	static final int n = 5;// number of lines
	static final int m = 5;// number of columns
	
	static int count;
	static final int visited[][] = new int [n][m];
	static final int result[][] = new int [n][m];

	static int [][] initTable (int nbLines, int nbColumns, int nbColors){
		int array[][] = new int [nbLines][nbColumns];
		Random rand;

		for(int i = 0; i < nbLines; i++) {
			for(int j = 0; j < nbColumns; j++) {
				rand = new Random();
				array[i][j]= (int)(rand.nextInt((nbColors)));
			}
		}
		return array;
	}

	static void displayTable (int nbLines, int nbColumns, int nbColors, int[][] array){
		System.out.println ("The generated table with " +nbLines+ " lines, " +nbColumns +" columns and "+nbColors+" colors is:" );
		System.out.println();
		for(int i = 0; i < nbLines; i++) {
			for(int j = 0; j < nbColumns; j++) {
				System.out.print(array[i][j]+ " ");
			}
			System.out.println("");
		}
	}

	static void BreadthFirstSearch(int x, int y, int i, int j, int input[][]) {
		if (x != y) return;

		visited[i][j] = 1;
		count++;

		int xMove[] = { 0, 0, 1, -1 };
		int yMove[] = { 1, -1, 0, 0 };

		for (int u = 0; u < 4; u++)
			if ((isValid(i + yMove[u], j + xMove[u], x, input)) == true)
				BreadthFirstSearch(x, y, i + yMove[u], j + xMove[u], input);
	}

    static boolean isValid(int x, int y, int key, int input[][]) {
		if (x < n && y < m && x >= 0 && y >= 0) {
			if (visited[x][y] == 0 && input[x][y] == key)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	static void resetVisited() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				visited[i][j] = 0;
	}

	static void resetResult(int key,int input[][]) {
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (visited[i][j] ==1 && input[i][j] == key)
					result[i][j] = visited[i][j];
				else
					result[i][j] = 0;
			}
		}
	}

	static void FindLargestArea(int input[][]) {
		int currentMax = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				resetVisited();
				count = 0;

				if (j + 1 < m)
					BreadthFirstSearch(input[i][j], input[i][j + 1],i, j, input);

				if (count >= currentMax) {
					currentMax = count;
					resetResult(input[i][j], input);
				}
				resetVisited();
				count = 0;

				if (i + 1 < n)
					BreadthFirstSearch(input[i][j], input[i + 1][j], i, j, input);

				if (count >= currentMax) {
					currentMax = count;
					resetResult(input[i][j], input);
				}
			}
		}
		printResult(currentMax, input);
	}

	static void printResult(int res, int [][] input) {
		System.out.println();
		System.out.println ("The largest area contains " +res +" cells as shown below:" );
		System.out.println();

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (result[i][j] != 0)
					System.out.print(input[i][j] + " ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
	}

	public static void main(String args[]) {
		int input[][] = initTable(n,m,c) ;
		displayTable(n, m, c, input);
		FindLargestArea(input);
	}
}
