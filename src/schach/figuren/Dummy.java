package schach.figuren;

import schach.Schachbrett_OOP;

public class Dummy extends Figur
{
	public Dummy(int x, int y, Schachbrett_OOP brett)
	{
		super(x, y, true, brett);
	}
	
	public Dummy(Dummy d, Schachbrett_OOP brett)
	{
		super(d, brett);
	}

	@Override
	public boolean isValid(Figur ziel, boolean SchachCheck)
	{
		return false;
	}
}
