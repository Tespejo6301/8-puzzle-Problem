import java.util.*;

public class Solver {
	private int totalCost = 0;
	private int h;
	private String problem = null;
	
	public List<board> children;
	public board currAry;
	private int[][] goal = {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
		};
	
	public Solver(int[][] start, String p) {
		this.problem = p;
		h = 0;
		int g = 0;
		currAry = new board();
		currAry.setPuzzle(start);
		if (problem == "regular") h = countMisplace(start);
		else if (problem == "manhattan") h = manhattan_Heuristic (start);
		countCost(g, h);
		currAry.setF(totalCost);
		currAry.setG(g);
		
		
	}
	
	
	public board getCurrAry() {
		return currAry;
	}
	//count the number of misplacement
	private int countMisplace(int[][] Ary) {
		int count = 0;
		
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				if(goal[i][j] != Ary[i][j]&& Ary[i][j] != 0 ) count++;
				}
				
			}
		
		return count;
		
	}
	// compute manhattan Heuristic
	private int manhattan_Heuristic(int[][] Ary) {
		int h = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						 if (goal[i][j] == Ary[k][l] && goal[i][j] != 0) h += Math.abs(i-k)+ Math.abs(j-l);
						 }
					}
				}
			}
		return h;
	}
	
	// count the total steps
	private void countCost(int g, int h) {
		this.totalCost = g + h;
	}
	//find the position of the empty tile
	private void findBlankSpace(int[][] Ary, int[] blank) {
		
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(Ary[i][j] == 0) {
					blank[0] = i;
					blank[1] = j;
				}
			}
		}
	}
	//generate all possible children
	public void generateChildren(board currNode) {
		board childAry = new board();
		
		this.children = new ArrayList<board>();
		currAry = currNode;
		int[] blank = new int[2];
		findBlankSpace(currAry.puzzle, blank);
		int x = blank[0];
		int y = blank[1];
		int g = currAry.getG() + 1;
		//if the column number is  not less than 0 swap left
		
	
		if (y - 1 != -1) {
		childAry = swapLeft(currAry, x, y);
		if (problem == "regular") h = countMisplace(childAry.puzzle);
		else if (problem == "manhattan") h = manhattan_Heuristic (childAry.puzzle);
		countCost(g, h);
		childAry.setF(totalCost);
		childAry.setG(g);
		childAry.setParent(currNode);
		children.add(childAry);
		
		}
		//if the column number is  not greater than 3 swap right
		if (y + 1 != 3)
		{
		
		childAry = swapRight(currAry, x, y);
		if (problem == "regular") h = countMisplace(childAry.puzzle);
		else if (problem == "manhattan") h = manhattan_Heuristic (childAry.puzzle);
		countCost(g, h);
		childAry.setF(totalCost);
		childAry.setG(g);
		childAry.setParent(currNode);
		children.add(childAry);
		}
		//if the row number is  not less than 0 swap up
		if (x - 1 != -1)
		{
			
		childAry = swapUp(currAry, x, y);
		if (problem == "regular") h = countMisplace(childAry.puzzle);
		else if (problem == "manhattan") h = manhattan_Heuristic (childAry.puzzle);
		countCost(g, h);
		childAry.setF(totalCost);
		childAry.setG(g);
		childAry.setParent(currNode);
		children.add(childAry);
		}
		//if the row number is  not greater than 3 swap down
		if (x + 1 != 3)
		{
			
		childAry = swapDown(currAry, x, y);
		if (problem == "regular") h = countMisplace(childAry.puzzle);
		else if (problem == "manhattan") h = manhattan_Heuristic (childAry.puzzle);
		countCost(g, h);
		childAry.setF(totalCost);
		childAry.setG(g);
		childAry.setParent(currNode);
		children.add(childAry);
		}
		
		
	}
	// swap empty tile with the tile left it
	public board swapLeft(board currAry, int x, int y) {
		int[][] Ary = new int [3][3];
		board newAry = new board();
		int temp = 0;
		for (int i = 0; i < 3; i++) 
			Ary[i] = currAry.puzzle[i].clone();
		
			temp = Ary[x][y];
			Ary[x][y] = Ary[x][y- 1];
			Ary[x][y- 1] = temp;
		newAry.setPuzzle(Ary);
		return newAry;
		
	}
	// swap empty tile with the tile right it
	public board swapRight(board currAry, int x, int y) {
		int[][] Ary = new int [3][3];
		board newAry = new board();
		int temp = 0;
		for (int i = 0; i < 3; i++) 
			Ary[i] = currAry.puzzle[i].clone();
		temp = Ary[x][y];
		Ary[x][y] = Ary[x][y + 1];
		Ary[x][y+ 1] = temp;
		newAry.setPuzzle(Ary);
		return newAry;
	}
	// swap empty tile with the tile above it
	public board swapUp(board currAry, int x, int y) {
		int[][] Ary = new int [3][3];
		board newAry = new board();
		int temp = 0;
		for (int i = 0; i < 3; i++) 
			Ary[i] = currAry.puzzle[i].clone();
	
		temp = Ary[x][y];
			
		Ary[x][y] = Ary[x - 1][y];
		Ary[x - 1][y] = temp;
		newAry.setPuzzle(Ary);
			
		return newAry;
	}
	// swap empty tile with the tile below it
	public board swapDown(board currAry, int x, int y) {
		int[][] Ary = new int [3][3];
		board newAry = new board();

		int temp = 0;
		for (int i = 0; i < 3; i++) 
			Ary[i] = currAry.puzzle[i].clone();
			temp = Ary[x][y];
			Ary[x][y] = Ary[x + 1][y];
			Ary[x + 1][y] = temp;
			newAry.setPuzzle(Ary);
		
		return newAry;
	}
	
	


	
	
}
