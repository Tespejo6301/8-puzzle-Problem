import java.util.*;
import java.io.*;
public class Project1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new BufferedReader( new FileReader( args[0])));
		
		int[][] Ary = new int [3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j <3; j++) {
				if (input.hasNextInt()) Ary[i][j] = input.nextInt();
			}
		}
		input.close();
		// compute the heuristic by misplacement
		String problem = "regular";
		//double endTime = 0;
		Astar_Search x = new  Astar_Search();
		board y = new board();
		long startTime = System.currentTimeMillis();
		 y= x.Astar(Ary,problem);
		 long  endTime = System.currentTimeMillis();
		 long totalTime = (endTime - startTime)/ 1000 ;
		 System.out.println(totalTime + "seconds" );
		 System.out.println("AStar with heuristic is misplacement");
		 printPath(y, x.count);
		 
		 problem = "manhattan";
		 System.out.println();
		 System.out.println("Astar and manhattan");
		 startTime = System.currentTimeMillis();
		 y = x.Astar(Ary,problem);
		 endTime = System.currentTimeMillis();
		 totalTime = (endTime - startTime)/ 1000 ;
		 System.out.println(totalTime + "seconds" );
		 printPath(y, x.count);
		 
		 System.out.println();
		 System.out.println("DFBnB");
		 DFBnB z = new DFBnB();
		 y = z.search(Ary, problem);
		 startTime = System.currentTimeMillis();
		 printPath(y, z.count);
		 endTime = System.currentTimeMillis();
		 totalTime = (endTime - startTime)/ 1000;
	     System.out.println(totalTime + "seconds" );
		 
		 
		 System.out.println("Iterative Deepening");
		 IDAstar a = new IDAstar();
		 startTime = System.currentTimeMillis();
		 y =  a.search(Ary, problem);
		 endTime = System.currentTimeMillis();
		 totalTime = (endTime - startTime)/ 1000 ;
		 System.out.println(totalTime + "seconds" );
	     printPath(y, a.count);
		 
		 
		 
		 
		
	}

	private static void printPath(board y, int numsteps) {
		board n = y;
		int g = 0;
		if (n == null)System.out.println("no solution");
		List <board> x = new ArrayList<board>();
		// store the path of the optimal solution
		while(n != null) {
			x.add(n);
			n = n.parent;
			
			
		}
		Collections.reverse(x);
		for (int a = 0; a < x.size(); a++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					 if (x.get(a).puzzle[i][j] != 0)System.out.print(x.get(a).puzzle[i][j] + " ");
					 else System.out.print("  ");
				}
				System.out.println();
			}
			g = x.get(a).getG() + g;
			System.out.println();
		}
		System.out.println("totalcost: " + g);
		System.out.println("total number of expansion: " + numsteps);
		System.out.println("number of expansion of optimal solution: " + x.size());
		
	}
	
		
	

}
