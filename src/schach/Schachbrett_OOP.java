package schach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import schach.figuren.*;

public class Schachbrett_OOP
{
	private Figur[][] brett = null;
	private boolean isBlackTurn = false;
	
	boolean turm_a_black_moved = false;
	boolean turm_b_black_moved = false;
	boolean king_black_moved = false;
	boolean turm_a_white_moved = false;
	boolean turm_b_white_moved = false;
	boolean king_white_moved = false;
	
	List<String> geschlagen = new LinkedList();
	
	public Schachbrett_OOP()
	{		
		brett = new Figur[8][8];
		
		brett[0][0] = new Turm(0, 0, true, this);
		brett[0][1] = new Springer(0, 1, true, this);
		brett[0][2] = new Laeufer(0, 2, true, this);
		brett[0][3] = new Dame(0, 3, true, this);
		brett[0][4] = new Koenig(0, 4, true, this);
		brett[0][5] = new Laeufer(0, 5, true, this);
		brett[0][6] = new Springer(0, 6, true, this);
		brett[0][7] = new Turm(0, 7, true, this);
		
		brett[1][0] = new Bauer(1, 0, true, this);
		brett[1][1] = new Bauer(1, 1, true, this);
		brett[1][2] = new Bauer(1, 2, true, this);
		brett[1][3] = new Bauer(1, 3, true, this);
		brett[1][4] = new Bauer(1, 4, true, this);
		brett[1][5] = new Bauer(1, 5, true, this);
		brett[1][6] = new Bauer(1, 6, true, this);
		brett[1][7] = new Bauer(1, 7, true, this);
		
		for(int i = 2; i <= 5; i++)
			for(int j = 0; j < 8; j++)
				brett[i][j] = new Dummy(i, j, this);
		
		brett[6][0] = new Bauer(6, 0, false, this);
		brett[6][1] = new Bauer(6, 1, false, this);
		brett[6][2] = new Bauer(6, 2, false, this);
		brett[6][3] = new Bauer(6, 3, false, this);
		brett[6][4] = new Bauer(6, 4, false, this);
		brett[6][5] = new Bauer(6, 5, false, this);
		brett[6][6] = new Bauer(6, 6, false, this);
		brett[6][7] = new Bauer(6, 7, false, this);
		
		brett[7][0] = new Turm(7, 0, false, this);
		brett[7][1] = new Springer(7, 1, false, this);
		brett[7][2] = new Laeufer(7, 2, false, this);
		brett[7][3] = new Dame(7, 3, false, this);
		brett[7][4] = new Koenig(7, 4, false, this);
		brett[7][5] = new Laeufer(7, 5, false, this);
		brett[7][6] = new Springer(7, 6, false, this);
		brett[7][7] = new Turm(7, 7, false, this);
	}
	
	public static Figur getClone(Figur f, Schachbrett_OOP schach)
	{
		if(f instanceof Bauer)
			return new Bauer((Bauer)f, schach);
		else if(f instanceof Turm)
			return new Turm((Turm)f, schach);
		else if(f instanceof Springer)
			return new Springer((Springer)f, schach);
		else if(f instanceof Laeufer)
			return new Laeufer((Laeufer)f, schach);
		else if(f instanceof Dame)
			return new Dame((Dame)f, schach);
		else if(f instanceof Koenig)
			return new Koenig((Koenig)f, schach);
		else
			return new Dummy((Dummy)f, schach);
	}
	
	public Schachbrett_OOP(Schachbrett_OOP s)
	{
		brett = new Figur[8][8];
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				brett[i][j] = Schachbrett_OOP.getClone(s.brett[i][j], this);
			}
		}
	}
	
	public Figur[][] getBrett()
	{
		return brett;
	}
	
	public boolean isSchach(boolean black)
	{
		if(getKoenig(black) == null)
			return true;
		else
			return getKoenig(black).isSchach();
	}
	
	public boolean isSchachMatt(boolean black)
	{
		return getKoenig(black).isSchachMatt();
	}
	
	public Koenig getKoenig(boolean black)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(getBrett()[i][j] instanceof Koenig)
				{
					Koenig k = (Koenig)getBrett()[i][j];				
					
					if(k.isBlack() == black)
					{
						return k;
					}
				}
			}
		}
		
		return null;
	}
	
	public boolean mayBeSchach(Figur start, Figur ziel)
	{
		boolean ret = false;
		
		Figur start_alias = start;
		Figur ziel_alias = ziel;
		
		brett[ziel.getX()][ziel.getY()] = getClone(start, this);
		brett[ziel.getX()][ziel.getY()].setX(ziel.getX());
		brett[ziel.getX()][ziel.getY()].setY(ziel.getY());
		brett[start.getX()][start.getY()] = new Dummy(start.getX(), start.getY(), this);
		
		if(isSchach(start.isBlack()))
			ret = true;
		
		brett[ziel.getX()][ziel.getY()] = ziel_alias;
		brett[start.getX()][start.getY()] = start_alias;
		
		return ret;
	}
	
	public void zug(int x1, int y1, int x2, int y2)
	{
		if(brett[x1][y1].isValid(brett[x2][y2], false) && brett[x1][y1].isBlack() == isBlackTurn)
		{
			//Bauer durch
			if(brett[x1][y1].isBlack() && brett[x1][y1] instanceof Bauer && x2 == 7)
			{
				brett[x1][y1] = new Dame(x1, y1, true, this);
			}
			else if(!brett[x1][y1].isBlack() && brett[x1][y1] instanceof Bauer && x2 == 0)
			{
				brett[x1][y1] = new Dame(x1, y1, false, this);
			}
			
			if(brett[x1][y1].isBlack() && brett[x1][y1] instanceof Turm && x1 == 0 && y1 == 0)
				turm_a_black_moved = true;
			
			if(brett[x1][y1].isBlack() && brett[x1][y1] instanceof Turm && x1 == 0 && y1 == 7)
				turm_b_black_moved = true;
			
			if(!brett[x1][y1].isBlack() && brett[x1][y1] instanceof Turm && x1 == 7 && y1 == 0)
				turm_a_white_moved = true;
			
			if(!brett[x1][y1].isBlack() && brett[x1][y1] instanceof Turm && x1 == 7 && y1 == 7)
				turm_b_white_moved = true;
			
			if(brett[x1][y1].isBlack() && brett[x1][y1] instanceof Koenig && x1 == 0 && y1 == 4)
				king_black_moved = true;
			
			if(!brett[x1][y1].isBlack() && brett[x1][y1] instanceof Koenig && x1 == 7 && y1 == 4)
				king_white_moved = true;

			setzeFigur(x1, y1, x2, y2);
				
			isBlackTurn = !isBlackTurn;
		}
		//Rochade
		else if(!isSchach(brett[x1][y1].isBlack()))
		{
			if(brett[x1][y1].isBlack() && brett[x1][y1] instanceof Koenig && brett[x2][y2] instanceof Turm)
			{
				if(x2 == 0 && y2 == 0 && !turm_a_black_moved && !king_black_moved)
				{
					if(brett[0][1] instanceof Dummy && brett[0][2] instanceof Dummy && brett[0][3] instanceof Dummy)
					{
						if(!mayBeSchach(brett[0][4], brett[0][3]) && !mayBeSchach(brett[0][4], brett[0][2]))
						{
							setzeFigur(0, 4, 0, 2);
							setzeFigur(0, 0, 0, 3);
							isBlackTurn = !isBlackTurn;
						}
					}
				}
				else if(x2 == 0 && y2 == 7 && !turm_b_black_moved && !king_black_moved)
				{
					if(brett[0][6] instanceof Dummy && brett[0][5] instanceof Dummy)
					{
						if(!mayBeSchach(brett[0][4], brett[0][5]) && !mayBeSchach(brett[0][4], brett[0][6]))
						{
							setzeFigur(0, 4, 0, 6);
							setzeFigur(0, 7, 0, 5);
							isBlackTurn = !isBlackTurn;
						}
					}
				}
			}
			else if(!brett[x1][y1].isBlack() && brett[x1][y1] instanceof Koenig && brett[x2][y2] instanceof Turm)
			{
				if(x2 == 7 && y2 == 0 && !turm_a_white_moved && !king_white_moved)
				{
					if(brett[7][1] instanceof Dummy && brett[7][2] instanceof Dummy && brett[7][3] instanceof Dummy)
					{
						if(!mayBeSchach(brett[7][4], brett[7][3]) && !mayBeSchach(brett[7][4], brett[7][2]))
						{
							setzeFigur(7, 4, 7, 2);
							setzeFigur(7, 0, 7, 3);
							isBlackTurn = !isBlackTurn;
						}
					}
				}
				else if(x2 == 7 && y2 == 7 && !turm_b_white_moved && !king_white_moved)
				{
					if(brett[7][6] instanceof Dummy && brett[7][5] instanceof Dummy)
					{
						if(!mayBeSchach(brett[7][4], brett[7][5]) && !mayBeSchach(brett[7][4], brett[7][6]))
						{
							setzeFigur(7, 4, 7, 6);
							setzeFigur(7, 7, 7, 5);
							isBlackTurn = !isBlackTurn;
						}
					}
				}
			}
		}
	}
	
	public List<String> getGeschlagen()
	{
		return geschlagen;
	}
	
	private void setzeFigur(int x1, int y1, int x2, int y2)
	{
		if(brett[x2][y2] instanceof Dummy == false)
		{
			Figur f = brett[x2][y2];
			
			if(f instanceof Bauer)
				geschlagen.add("Bauer " + (f.isBlack() ? "Schwarz" : "Weiﬂ"));
			else if(f instanceof Turm)
				geschlagen.add("Turm " + (f.isBlack() ? "Schwarz" : "Weiﬂ"));
			else if(f instanceof Springer)
				geschlagen.add("Springer " + (f.isBlack() ? "Schwarz" : "Weiﬂ"));
			else if(f instanceof Laeufer)
				geschlagen.add("Laeufer " + (f.isBlack() ? "Schwarz" : "Weiﬂ"));
			else if(f instanceof Dame)
				geschlagen.add("Dame " + (f.isBlack() ? "Schwarz" : "Weiﬂ"));
		}
		
		brett[x2][y2] = brett[x1][y1];
		brett[x2][y2].setX(x2);
		brett[x2][y2].setY(y2);
		brett[x1][y1] = new Dummy(x1, y1, this);
	}
	
	public boolean isBlackTurn()
	{
		return isBlackTurn;
	}

	public boolean isTurm_a_black_moved() {
		return turm_a_black_moved;
	}

	public void setTurm_a_black_moved(boolean turm_a_black_moved) {
		this.turm_a_black_moved = turm_a_black_moved;
	}

	public boolean isTurm_b_black_moved() {
		return turm_b_black_moved;
	}

	public void setTurm_b_black_moved(boolean turm_b_black_moved) {
		this.turm_b_black_moved = turm_b_black_moved;
	}

	public boolean isKing_black_moved() {
		return king_black_moved;
	}

	public void setKing_black_moved(boolean king_black_moved) {
		this.king_black_moved = king_black_moved;
	}

	public boolean isTurm_a_white_moved() {
		return turm_a_white_moved;
	}

	public void setTurm_a_white_moved(boolean turm_a_white_moved) {
		this.turm_a_white_moved = turm_a_white_moved;
	}

	public boolean isTurm_b_white_moved() {
		return turm_b_white_moved;
	}

	public void setTurm_b_white_moved(boolean turm_b_white_moved) {
		this.turm_b_white_moved = turm_b_white_moved;
	}

	public boolean isKing_white_moved() {
		return king_white_moved;
	}

	public void setKing_white_moved(boolean king_white_moved) {
		this.king_white_moved = king_white_moved;
	}

	public void setBlackTurn(boolean isBlackTurn) {
		this.isBlackTurn = isBlackTurn;
	}
	
	
}
