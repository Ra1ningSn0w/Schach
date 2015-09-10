package schach.figuren;

import schach.Schachbrett_OOP;

public class Bauer extends Figur
{
	public Bauer(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		super(x, y, black, brett);
	}
	
	public Bauer(Bauer b, Schachbrett_OOP brett)
	{
		super(b, brett);
	}

	@Override
	public boolean isValid(Figur ziel, boolean schachCheck)
	{
		if(!schachCheck && ziel instanceof Koenig)
			return false;
		
		if(brett.mayBeSchach(this, ziel))
			return false;
		
		if(black)
		{
			if(ziel.x == 3 && ziel.y == y && x == 1)
			{
				if(ziel instanceof Dummy)
					return true;
			}
			else if(ziel.x == x + 1 && ziel.y == y)
			{
				if(ziel instanceof Dummy)
					return true;
			}
			else if((ziel.x == x + 1) && (ziel.y == y-1 || ziel.y == y+1))
			{
				if(ziel instanceof Dummy == false && !ziel.black)
					return true;
			}
		}
		else
		{
			if(ziel.x == 4 && ziel.y == y && x == 6)
			{
				if(ziel instanceof Dummy)
					return true;
			}
			else if(ziel.x == x - 1 && ziel.y == y)
			{
				if(ziel instanceof Dummy)
					return true;
			}
			else if((ziel.x == x - 1) && (ziel.y == y-1 || ziel.y == y+1))
			{
				if(ziel instanceof Dummy == false && ziel.black)
					return true;
			}
		}
		
		return false;
	}
}
