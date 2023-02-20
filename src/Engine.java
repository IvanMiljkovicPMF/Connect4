public class Engine 
{
	int numYellow, numRed;
	int move;
	Tile[][] table = new Tile[6][7];
	
	public Engine()
	{
		this.init();
	}
	public Colors whoWasOnTheMove()
	{
		if( this.move*-1 == -1)
			return Colors.RED;
		else
			return Colors.YELLOW;
	}
	public void init()
	{
		this.move = -1;
		for( int i=0; i<6; i++)
			for( int j=0; j<7; j++)
			{
				this.table[i][j] = new Tile( i, j);
			}
		this.numRed = this.numYellow = 0;
	}
	public int addChip( int y)
	{
		if( y>7 || y<0 )
		{
			return( -1);
		}
		else
		{
			int x=-1;
			for( int i=5; i>=0; i-- )
				if( this.table[i][y].state == Colors.EMPTY )
				{
					x = i;
					break;
				}
			
			if( x!=-1)
			{
				if( this.move == -1 ) // RED
				{
					this.table[x][y].state = Colors.RED;
					this.numRed++;
				}
				else // YELLOW
				{
					this.table[x][y].state = Colors.YELLOW;
					this.numYellow++;
				}
				this.move*=-1;
				System.out.println(x +" "+ y);
			}

			return( x);
		}
	}
	public boolean areFourConnected(Colors player){

	    // horizontalCheck 
	    for (int j = 0; j<7-3 ; j++ ){
	        for (int i = 0; i<6; i++){
	            if (this.table[i][j].state == player && this.table[i][j+1].state == player && this.table[i][j+2].state == player && this.table[i][j+3].state == player){
	                return true;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<6-3 ; i++ ){
	        for (int j = 0; j<7; j++){
	            if (this.table[i][j].state == player && this.table[i+1][j].state == player && this.table[i+2][j].state == player && this.table[i+3][j].state == player){
	                return true;
	            }           
	        }
	    }
	    // ascendingDiagonalCheck 
	    for (int i=3; i<6; i++){
	        for (int j=0; j<7-3; j++){
	            if (this.table[i][j].state == player && this.table[i-1][j+1].state == player && this.table[i-2][j+2].state == player && this.table[i-3][j+3].state == player)
	                return true;
	        }
	    }
	    // descendingDiagonalCheck
	    for (int i=3; i<6; i++){
	        for (int j=3; j<7; j++){
	            if (this.table[i][j].state == player && this.table[i-1][j-1].state == player && this.table[i-2][j-2].state == player && this.table[i-3][j-3].state == player)
	                return true;
	        }
	    }
	    return false;
	}
}


















