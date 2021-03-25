
import java.util.*;
public class Astar_Search { 
	public int count;
	private int[][] goal = {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
		};
	
	
	

	public board Astar(int[][] start, String problem) {
	    this.count = 0;
		int size = 0;
		Solver Ary = new Solver(start, problem);
		board currAry = new board();
		List<board> openList = new ArrayList<board>();
		List<board> closeList = new ArrayList<board>();
		openList.add(Ary.getCurrAry());
		board child = new board();
		
		while (true) {
			currAry = openList.remove(0);
			this.count++;
			// if goal and the current board solution is the same return the current board
			if (isGoal(currAry.puzzle)) {
				return currAry;
				
			}
			
			Ary.generateChildren(currAry);
			size = Ary.children.size();
			for (int  i = 0; i <  size; i++)
			{
				
				child = Ary.children.remove(0);
				// if the child is not already in the openList and if it is not in close list add the child to the openList
				if (!ifExist(child, openList) && !ifExist(child, closeList) ) {
					openList.add(child);
					
				}
				closeList.add(currAry);
				
			}
			// make sure that the lowest cost solution would have priority over the other solution
			Collections.sort(openList);	
			
	
			
		}
		
		
	}
	// check if there the board is already present
	private boolean ifExist(board child, List<board> x) {
		boolean result = false;
		for (int i = 0; i < x.size(); i++)
		{
			result = Arrays.equals(child.puzzle, x.get(i).puzzle);
			
		}
		return result;
	}
	// check if reach the goal
	private boolean isGoal(int[][] currAry) {
		boolean result = true;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
			
				if(currAry[i][j] != goal[i][j]) 
					result = false;
			}
		}
		return result;
	}
	
	

	
	
}
