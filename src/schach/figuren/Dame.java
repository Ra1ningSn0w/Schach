package schach.figuren;

import schach.Schachbrett_OOP;

public class Dame extends Figur
{
	public Dame(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		super(x, y, black, brett);
	}
	
	public Dame(Dame d, Schachbrett_OOP brett)
	{
		super(d, brett);
	}

	@Override
	public boolean isValid(Figur ziel, boolean schachCheck)
	{
		if(!schachCheck && ziel instanceof Koenig)
			return false;
		
		if(brett.mayBeSchach(this, ziel))
			return false;
		
		if(black && ziel.black && ziel instanceof Dummy == false)
			return false;
		
		if(!black && !ziel.black && ziel instanceof Dummy == false)
			return false;
		
		//Laeufer
		boolean ret_laeufer;
		int dx = ziel.x - x;
		int dy = ziel.y - y;
		
		if(dx == dy || dx == -dy)
		{			
			if(dx < 0)
			{
				ret_laeufer = true;
				
				for(int i = -1; i > dx; i--)
				{
					if(dx == dy)
					{
						if(brett.getBrett()[x + i][y + i] instanceof Dummy == false)
							ret_laeufer = false;
					}
					else
					{
						if(brett.getBrett()[x + i][y - i] instanceof Dummy == false)
							ret_laeufer = false;
					}
				}
			}
			else
			{
				ret_laeufer = true;
				
				for(int i = 1; i < dx; i++)
				{
					if(dx == dy)
					{
						if(brett.getBrett()[x + i][y + i] instanceof Dummy == false)
							ret_laeufer = false;
					}
					else
					{
						if(brett.getBrett()[x + i][y - i] instanceof Dummy == false)
							ret_laeufer = false;
					}
				}
			}
		}
		else
		{
			ret_laeufer = false;
		}
		
		//Turm
		boolean ret_turm;
		
		if(y == ziel.y)
		{
			ret_turm = true;
			
			if(x < ziel.x)
			{
				for(int i = x+1; i < ziel.x; i++)
				{
					if(brett.getBrett()[i][y] instanceof Dummy == false)
						ret_turm = false;
				}
			}
			else
			{
				for(int i = x-1; i > ziel.x; i--)
				{
					if(brett.getBrett()[i][y] instanceof Dummy == false)
						ret_turm = false;
				}
			}
		}
		else if(x == ziel.x)
		{
			ret_turm = true;
			
			if(y < ziel.y)
			{
				for(int i = y+1; i < ziel.y; i++)
				{
					if(brett.getBrett()[x][i] instanceof Dummy == false)
								ret_turm = false;
				}
			}
			else
			{
				for(int i = y-1; i > ziel.y; i--)
				{
					if(brett.getBrett()[x][i] instanceof Dummy == false)
						ret_turm = false;
				}
			}
		}
		else
		{
			ret_turm = false;
		}
		
		return ret_laeufer || ret_turm;
	}
}
