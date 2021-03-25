import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IDAstar {
	public int count;
	private int[][] goal = {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
		};
	public board search(int[][] start, String problem) {
		this.count = 0;
		int size = 0;
		int L = 0;
		Solver Ary = new Solver(start, problem);
		board currAry = new board();
		List<board> openList = new ArrayList<board>();
		List<board> temp = new ArrayList<board>();
		
		openList.add(Ary.getCurrAry());
		board child = new board();
		L = openList.get(0).getF();
		
		while(!openList.isEmpty()) {
			currAry = openList.remove(openList.size() - 1);
			count++;
			if (isGoal(currAry.puzzle)) {
				
				return currAry;
			}
			else
			{
				Ary.generateChildren(currAry);
				size = Ary.children.size();
				int min = Ary.children.get(0).getF();
				// find the smallest min F value of children 
				for (int  i = 0; i < size; i++)
				{
					child = Ary.children.get(i);
					if ( min > child.getF()) min = child.getF();
				}
				// let L be the smallest F value of the children;
				if ( L < min)L = min;
				
				// add only the value of the smallest F value 
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
			
			if (count == 100000)return null;
			
				
			
		}
		return null;
		
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
