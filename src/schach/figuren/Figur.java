package schach.figuren;

import java.util.LinkedList;
import java.util.List;

import schach.Schachbrett_OOP;
import schach.Zug;

public abstract class Figur
{
	protected boolean black;
	protected Schachbrett_OOP brett;
	protected int x, y;

	public Figur(int x, int y, boolean black, Schachbrett_OOP brett)
	{
		this.brett = brett;
		this.black = black;
		this.x = x;
		this.y = y;
	}
	
	public Figur(Figur f, Schachbrett_OOP brett)
	{
		this.brett = brett;
		this.black = f.black;
		this.x = f.x;
		this.y = f.y;
	}
	
	protected boolean checkBounds(int fromx, int fromy, int tox, int toy)
	{
		if(tox < 0 || tox > 7 || toy < 0 || toy > 7)
			return false;
		
		return true;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public abstract boolean isValid(Figur ziel, boolean schachCheck);
	
	public boolean erzeugtSchach(Figur koenig)
	{
		if(isValid(koenig, true))
			return true;
		
		return false;
	}
	
	public boolean isBlack()
	{
		return black;
	}
	
	public List<Zug> moeglicheZuege()
	{
		List<Zug> ret = new LinkedList();
		
		for(Figur[] a : brett.getBrett())
		{
			for(Figur b : a)
			{
				if(isValid(b, false))
					ret.add(new Zug(this, b));
			}
		}
		
		return ret;
	}
	
	public List<Zug> moeglicheSchlaege()
	{
		List<Zug> ret = new LinkedList();
		
		for(Figur[] a : brett.getBrett())
		{
			for(Figur b : a)
			{
				if(isValid(b, false) && (b.isBlack() != black) && (b instanceof Dummy == false))
					ret.add(new Zug(this, b));				
				else if(black && brett.getEn_passant_x() != -1 && brett.getEn_passant_y() != -1 && x == brett.getEn_passant_x() && b.getY() == brett.getEn_passant_y() && (x - b.getX()) == -1 && b instanceof Dummy && this instanceof Bauer)
					ret.add(new Zug(this, b));
				else if(!black && brett.getEn_passant_x() != -1 && brett.getEn_passant_y() != -1 && x == brett.getEn_passant_x() && b.getY() == brett.getEn_passant_y() && (x - b.getX() == 1) && b instanceof Dummy && this instanceof Bauer)
					ret.add(new Zug(this, b));
			}
		}
		
		return ret;
	}
}
