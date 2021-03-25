import java.util.*;

public class DFBnB {
	public int count;
	private int[][] goal = {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
		};
	public board search(int[][] start, String problem) {
		this.count = 0;
		int size = 0;
		int L = 99999;
		Solver Ary = new Solver(start, problem);
		board currAry = new board();
		List<board> openList = new ArrayList<board>();
		List<board> closeList = new ArrayList<board>();
		List<board> temp = new ArrayList<board>();
		openList.add(Ary.getCurrAry());
		board child = new board();
		board bestSolution = new board();
		
		while(!openList.isEmpty()) {
			currAry = openList.remove(openList.size() - 1);
			
			if (isGoal(currAry.puzzle)) {
				
				L = Math.min(L, currAry.getF());
				bestSolution = currAry;
			}
			else
			{
				Ary.generateChildren(currAry);
				size = Ary.children.size();
				for (int  i = 0; i < size; i++)
				{
					child = Ary.children.remove(0);
					
					if (child.getF() <= L ) { 
						temp.add(child);
					}

				}
				size = temp.size();
				
				Collections.sort(temp);
				
				for (int  i = size - 1; i >= 0; i--)
				{
					child = temp.remove(i);
					openList.add(child);
				}
				
				
			}
			closeList.add(currAry);
			count++;
			
		}
		
		return bestSolution;
		
		
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
