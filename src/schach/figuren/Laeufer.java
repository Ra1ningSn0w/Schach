package schach.figuren;

import schach.Schachbrett_OOP;

public class Laeufer extends Figur
{
	public Laeufer(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		super(x, y, black, brett);
	}
	
	public Laeufer(Laeufer l, Schachbrett_OOP brett)
	{
		super(l, brett);
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
		
		int dx = ziel.x - x;
		int dy = ziel.y - y;
		
		if(dx == dy || dx == -dy)
		{			
			if(dx < 0)
			{
				for(int i = -1; i > dx; i--)
				{
					if(dx == dy)
					{
						if(brett.getBrett()[x + i][y + i] instanceof Dummy == false)
							return false;
					}
					else
					{
						if(brett.getBrett()[x + i][y - i] instanceof Dummy == false)
							return false;
					}
				}
				
				return true;
			}
			else
			{
				for(int i = 1; i < dx; i++)
				{
					if(dx == dy)
					{
						if(brett.getBrett()[x + i][y + i] instanceof Dummy == false)
							return false;
					}
					else
					{
						if(brett.getBrett()[x + i][y - i] instanceof Dummy == false)
							return false;
					}
				}
				
				return true;
			}
		}
		else
		{
			return false;
		}
	}
}
