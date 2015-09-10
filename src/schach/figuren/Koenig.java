package schach.figuren;

import schach.Schachbrett_OOP;

public class Koenig extends Figur
{
	public Koenig(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		super(x, y, black, brett);
	}
	
	public Koenig(Koenig k, Schachbrett_OOP brett)
	{
		super(k, brett);
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
		
		int dx = ziel.x - x;
		int dy = ziel.y - y;
		
		if(dx < 0)
			dx = -dx;
		
		if(dy < 0)
			dy = -dy;
		
		return dy <= 1 && dx <= 1 && checkBounds(x, y, ziel.x, ziel.y);
	}
	
	public boolean isSchach()
	{
		for(Figur[] a : brett.getBrett())
		{
			for(Figur b : a)
			{
				if(b instanceof Koenig == false && b instanceof Dummy == false)
				{
					if(black && !b.black && b.erzeugtSchach(this))
						return true;
					
					if(!black && b.black && b.erzeugtSchach(this))
						return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isSchachMatt()
	{
		boolean ret = false;
		
		if(isSchach())
		{
			ret = true;
			
			for(Figur[] a : brett.getBrett())
			{
				for(Figur fig1 : a)
				{
					if(fig1 instanceof Dummy == false && fig1.isBlack() == black)
					{
						for(Figur[] c : brett.getBrett())
						{
							for(Figur fig2 : c)
							{
								if(fig2 instanceof Koenig == false)
								{
									if(fig1.isValid(fig2, true))
									{	
										if(!brett.mayBeSchach(fig1, fig2))
										{
											/*System.out.println("Lösung, um aus Schach zu kommen: ");
											System.out.println("[" + fig1.getX() + ", " + fig1.getY() + "]");
											System.out.println("[" + fig2.getX() + ", " + fig2.getY() + "]");*/
											ret = false;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return ret;
	}
}
