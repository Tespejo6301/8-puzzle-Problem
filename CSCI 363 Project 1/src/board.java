
//properties of the 8 puzzle board
public class board implements Comparable <board>  {
	int g = 0; 
	int f = 0; 
	board parent = null;
	int[][] puzzle;

	public board() {
		
	}
	public int[][] puzzle()
	{
		return puzzle;	
	}
	public int getG()
	{
		return g;	
	}
	public Integer getF()
	{
		return f;	
	}
	public board getParent()
	{
		return parent;	
	}
	public void setG(int a)
	{
		this.g = a;
	}
	public void setF(int a)
	{
		this.f = a;
	}
	public void setParent(board currNode)
	{
		this.parent = currNode;
	}
	
	public void setPuzzle(int[][] p)
	{
		this.puzzle = p;
	}

	@Override
	public int compareTo(board o) {
		
		return getF().compareTo(o.getF());
	}
	
	
}


