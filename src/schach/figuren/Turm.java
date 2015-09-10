package schach.figuren;

import schach.Schachbrett_OOP;

public class Turm extends Figur
{
	public Turm(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		super(x, y, black, brett);
	}
	
	public Turm(Turm t, Schachbrett_OOP brett)
	{
		super(t, brett);
	}

	@Override
	public boolean isValid(Figur ziel, boolean schachCheck)
	{
		if(!schachCheck && ziel instanceof Koenig)
			return false;
		
		if(black && ziel.black && ziel instanceof Dummy == false)
			return false;
		
		if(!black && !ziel.black && ziel instanceof Dummy == false)
			return false;
		
		if(brett.mayBeSchach(this, ziel))
			return false;
		
		if(y == ziel.y)
		{
			if(x < ziel.x)
			{
				for(int i = x+1; i < ziel.x; i++)
				{
					if(brett.getBrett()[i][y] instanceof Dummy == false)
						return false;
				}
			}
			else
			{
				for(int i = x-1; i > ziel.x; i--)
				{
					if(brett.getBrett()[i][y] instanceof Dummy == false)
						return false;
				}
			}
					
			return true;
		}
		else if(x == ziel.x)
		{
			if(y < ziel.y)
			{
				for(int i = y+1; i < ziel.y; i++)
				{
					if(brett.getBrett()[x][i] instanceof Dummy == false)
								return false;
				}
			}
			else
			{
				for(int i = y-1; i > ziel.y; i--)
				{
					if(brett.getBrett()[x][i] instanceof Dummy == false)
						return false;
				}
			}
					
			return true;
		}
		else
		{
			return false;
		}
	}
}
