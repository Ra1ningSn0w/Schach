package utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import schach.Schachbrett_OOP;
import schach.figuren.Bauer;
import schach.figuren.Dame;
import schach.figuren.Dummy;
import schach.figuren.Figur;
import schach.figuren.Koenig;
import schach.figuren.Laeufer;
import schach.figuren.Springer;
import schach.figuren.Turm;

public class XMLWriter
{
	public static void write(Schachbrett_OOP brett)
	{
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("chess");
			doc.appendChild(rootElement);
			
			Element turn = doc.createElement("turn");
			turn.appendChild(doc.createTextNode(brett.isBlackTurn() ? "black" : "white"));
			rootElement.appendChild(turn);
			
			Element turm_a_black = doc.createElement("turm_a_black");
			turm_a_black.appendChild(doc.createTextNode(brett.isTurm_a_black_moved() ? "true" : "false"));
			rootElement.appendChild(turm_a_black);
			
			Element turm_b_black = doc.createElement("turm_b_black");
			turm_b_black.appendChild(doc.createTextNode(brett.isTurm_b_black_moved() ? "true" : "false"));
			rootElement.appendChild(turm_b_black);
			
			Element turm_a_white = doc.createElement("turm_a_white");
			turm_a_white.appendChild(doc.createTextNode(brett.isTurm_a_white_moved() ? "true" : "false"));
			rootElement.appendChild(turm_a_white);
			
			Element turm_b_white = doc.createElement("turm_b_white");
			turm_b_white.appendChild(doc.createTextNode(brett.isTurm_b_white_moved() ? "true" : "false"));
			rootElement.appendChild(turm_b_white);
			
			Element king_black = doc.createElement("king_black");
			king_black.appendChild(doc.createTextNode(brett.isKing_black_moved() ? "true" : "false"));
			rootElement.appendChild(king_black);
			
			Element king_white = doc.createElement("king_white");
			king_white.appendChild(doc.createTextNode(brett.isKing_white_moved() ? "true" : "false"));
			rootElement.appendChild(king_white);

			int counter = 0;
			
			for(String s : brett.getGeschlagen())
			{
				Element geschlagen = doc.createElement("geschlagen");
				rootElement.appendChild(geschlagen);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(String.valueOf(counter));
				geschlagen.setAttributeNode(attr);
				
				Element string = doc.createElement("string");
				string.appendChild(doc.createTextNode(s));
				geschlagen.appendChild(string);
				
				counter++;
			}
			
			counter = 0;
			
			for(Figur[] a : brett.getBrett())
			{
				for(Figur b : a)
				{
					Element player = doc.createElement("player");
					rootElement.appendChild(player);
					
					Attr attr = doc.createAttribute("id");
					attr.setValue(String.valueOf(counter));
					player.setAttributeNode(attr);
					
					Element x = doc.createElement("x");
					x.appendChild(doc.createTextNode(String.valueOf(b.getX())));
					player.appendChild(x);
					
					Element y = doc.createElement("y");
					y.appendChild(doc.createTextNode(String.valueOf(b.getY())));
					player.appendChild(y);
					
					Element black = doc.createElement("black");
					black.appendChild(doc.createTextNode(String.valueOf(b.isBlack())));
					player.appendChild(black);
					
					Element type = doc.createElement("type");				
					
					if(b instanceof Bauer)
						type.appendChild(doc.createTextNode("bauer"));
					else if(b instanceof Turm)
						type.appendChild(doc.createTextNode("turm"));
					else if(b instanceof Springer)
						type.appendChild(doc.createTextNode("springer"));
					else if(b instanceof Laeufer)
						type.appendChild(doc.createTextNode("laeufer"));
					else if(b instanceof Dame)
						type.appendChild(doc.createTextNode("dame"));
					else if(b instanceof Koenig)
						type.appendChild(doc.createTextNode("koenig"));
					else
						type.appendChild(doc.createTextNode("dummy"));
					
					player.appendChild(type);
					
					counter++;
				}
			}
			
			doc.getDocumentElement().normalize();
			
			JFileChooser chooser = new JFileChooser();
			
			FileFilter filter = new FileNameExtensionFilter("XML File (*.xml)","xml");
			chooser.setFileFilter(filter);
			
			chooser.showSaveDialog(null);
			
			if(chooser.getSelectedFile() != null)
			{			
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(chooser.getSelectedFile());
			
				transformer.transform(source, result);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
