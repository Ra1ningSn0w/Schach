package schach;

import schach.figuren.Figur;

public class Zug
{
	Figur feld1, feld2;
	
	public Zug(Figur feld1, Figur feld2)
	{
		this.feld1 = feld1;
		this.feld2 = feld2;
	}

	public Figur getFeld1() {
		return feld1;
	}

	public void setFeld1(Figur feld1) {
		this.feld1 = feld1;
	}

	public Figur getFeld2() {
		return feld2;
	}

	public void setFeld2(Figur feld2) {
		this.feld2 = feld2;
	}
}
