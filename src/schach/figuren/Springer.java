package schach.figuren;

import schach.Schachbrett_OOP;

public class Springer extends Figur
{
	public Springer(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		super(x, y, black, brett);
	}
	
	public Springer(Springer s, Schachbrett_OOP brett)
	{
		super(s, brett);
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
		
		int dx = x - ziel.x;
		int dy = y - ziel.y;
		
		if(dx < 0)
			dx = -dx;
		
		if(dy < 0)
			dy = -dy;
		
		if(dx == 2 && dy == 1)
			return true;
		
		if(dx == 1 && dy == 2)
			return true;
		
		return false;
	}	
}
