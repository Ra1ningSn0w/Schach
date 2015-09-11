package utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import schach.Schachbrett_OOP;
import schach.figuren.*;

public class XMLReader
{
	public static Schachbrett_OOP read()
	{
		try
		{
			JFileChooser chooser = new JFileChooser();
			
			FileFilter filter = new FileNameExtensionFilter("XML File (*.xml)","xml");
			chooser.setFileFilter(filter);
			
			chooser.showOpenDialog(null);
			
			if(chooser.getSelectedFile() == null)
				return null;
			
			Schachbrett_OOP brett = new Schachbrett_OOP();
			
			File fXmlFile = chooser.getSelectedFile();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("turn");
			brett.setBlackTurn(nList.item(0).getTextContent().equals("black"));
			
			nList = doc.getElementsByTagName("turm_a_black");
			brett.setTurm_a_black_moved((nList.item(0).getTextContent().equals("true")));
			
			nList = doc.getElementsByTagName("turm_b_black");
			brett.setTurm_b_black_moved((nList.item(0).getTextContent().equals("true")));
			
			nList = doc.getElementsByTagName("turm_a_white");
			brett.setTurm_a_white_moved((nList.item(0).getTextContent().equals("true")));
			
			nList = doc.getElementsByTagName("turm_b_white");
			brett.setTurm_b_white_moved((nList.item(0).getTextContent().equals("true")));
			
			nList = doc.getElementsByTagName("king_black");
			brett.setKing_black_moved((nList.item(0).getTextContent().equals("true")));
			
			nList = doc.getElementsByTagName("king_white");
			brett.setKing_white_moved((nList.item(0).getTextContent().equals("true")));
			
			nList = doc.getElementsByTagName("timer_black");
			brett.setTimer_black(Integer.parseInt(nList.item(0).getTextContent()));
			
			nList = doc.getElementsByTagName("timer_white");
			brett.setTimer_white(Integer.parseInt(nList.item(0).getTextContent()));
			
			nList = doc.getElementsByTagName("en_passant_x");
			brett.setEn_passant_x(Integer.parseInt(nList.item(0).getTextContent()));
			
			nList = doc.getElementsByTagName("en_passant_y");
			brett.setEn_passant_y(Integer.parseInt(nList.item(0).getTextContent()));
			
			nList = doc.getElementsByTagName("geschlagen");
			
			for(int i = 0; i < nList.getLength(); i++)
			{
				Node n = nList.item(i);
				
				if (n.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element)n;

					String type = e.getElementsByTagName("string").item(0).getTextContent();
					
					brett.getGeschlagen().add(type);
				}
			}
			
			nList = doc.getElementsByTagName("player");
			
			for(int i = 0; i < nList.getLength(); i++)
			{
				Node n = nList.item(i);
				
				if (n.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element)n;
					
					int x = Integer.parseInt(e.getElementsByTagName("x").item(0).getTextContent());
					int y = Integer.parseInt(e.getElementsByTagName("y").item(0).getTextContent());
					boolean black = e.getElementsByTagName("black").item(0).getTextContent().equals("true");
					
					String type = e.getElementsByTagName("type").item(0).getTextContent();
					
					if(type.equals("bauer"))
						brett.getBrett()[x][y] = new Bauer(x, y, black, brett);
					else if(type.equals("turm"))
						brett.getBrett()[x][y] = new Turm(x, y, black, brett);
					else if(type.equals("springer"))
						brett.getBrett()[x][y] = new Springer(x, y, black, brett);
					else if(type.equals("laeufer"))
						brett.getBrett()[x][y] = new Laeufer(x, y, black, brett);
					else if(type.equals("dame"))
						brett.getBrett()[x][y] = new Dame(x, y, black, brett);
					else if(type.equals("koenig"))
						brett.getBrett()[x][y] = new Koenig(x, y, black, brett);
					else
						brett.getBrett()[x][y] = new Dummy(x, y, brett);
				}
			}
			
			return brett;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
