package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import schach.Schachbrett_OOP;
import schach.Zug;
import schach.figuren.*;
import utils.XMLReader;
import utils.XMLWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ClientGUI_OOP extends JFrame {

	private JPanel contentPane;
	private JButton[][] buttons = new JButton[8][8];
	private Schachbrett_OOP brett = new Schachbrett_OOP();
	
	private ImageIcon blank_dark = null;
	private ImageIcon blank_light = null;
	
	private ImageIcon bauer_black_dark = null;
	private ImageIcon bauer_black_light = null;
	private ImageIcon turm_black_dark = null;
	private ImageIcon turm_black_light = null;
	private ImageIcon laeufer_black_dark = null;
	private ImageIcon laeufer_black_light = null;
	private ImageIcon springer_black_dark = null;
	private ImageIcon springer_black_light = null;
	private ImageIcon koenig_black_dark = null;
	private ImageIcon koenig_black_light = null;
	private ImageIcon dame_black_dark = null;
	private ImageIcon dame_black_light = null;
	
	private ImageIcon bauer_white_dark = null;
	private ImageIcon bauer_white_light = null;
	private ImageIcon turm_white_dark = null;
	private ImageIcon turm_white_light = null;
	private ImageIcon laeufer_white_dark = null;
	private ImageIcon laeufer_white_light = null;
	private ImageIcon springer_white_dark = null;
	private ImageIcon springer_white_light = null;
	private ImageIcon koenig_white_dark = null;
	private ImageIcon koenig_white_light = null;
	private ImageIcon dame_white_dark = null;
	private ImageIcon dame_white_light = null;
	
	private JLabel zug, schach;
	private JTextArea textArea;
	private JLabel lblZeit;
	
	int fromx = -1, fromy = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI_OOP frame = new ClientGUI_OOP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI_OOP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 555);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLWriter.write(brett);
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Schachbrett_OOP load = XMLReader.read();
				
				if(load != null)
					brett = load;
				
				update();
			}
		});
		mnFile.add(mntmOpen);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton a8 = new JButton("");
		a8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 0);
			}
		});
		a8.setBounds(12, 13, 45, 45);
		contentPane.add(a8);
		
		JButton b8 = new JButton("");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 1);
			}
		});
		b8.setBounds(69, 13, 45, 45);
		contentPane.add(b8);
		
		JButton c8 = new JButton("");
		c8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 2);
			}
		});
		c8.setBounds(126, 13, 45, 45);
		contentPane.add(c8);
		
		JButton d8 = new JButton("");
		d8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 3);
			}
		});
		d8.setBounds(183, 13, 45, 45);
		contentPane.add(d8);
		
		JButton e8 = new JButton("");
		e8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 4);
			}
		});
		e8.setBounds(240, 13, 45, 45);
		contentPane.add(e8);
		
		JButton f8 = new JButton("");
		f8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 5);
			}
		});
		f8.setBounds(297, 13, 45, 45);
		contentPane.add(f8);
		
		JButton g8 = new JButton("");
		g8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 6);
			}
		});
		g8.setBounds(354, 13, 45, 45);
		contentPane.add(g8);
		
		JButton h8 = new JButton("");
		h8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(0, 7);
			}
		});
		h8.setBounds(411, 13, 45, 45);
		contentPane.add(h8);
		
		JButton a7 = new JButton("");
		a7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 0);
			}
		});
		a7.setBounds(12, 72, 45, 45);
		contentPane.add(a7);
		
		JButton b7 = new JButton("");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 1);
			}
		});
		b7.setBounds(69, 72, 45, 45);
		contentPane.add(b7);
		
		JButton c7 = new JButton("");
		c7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 2);
			}
		});
		c7.setBounds(126, 72, 45, 45);
		contentPane.add(c7);
		
		JButton d7 = new JButton("");
		d7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 3);
			}
		});
		d7.setBounds(183, 72, 45, 45);
		contentPane.add(d7);
		
		JButton e7 = new JButton("");
		e7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 4);
			}
		});
		e7.setBounds(240, 72, 45, 45);
		contentPane.add(e7);
		
		JButton f7 = new JButton("");
		f7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 5);
			}
		});
		f7.setBounds(297, 72, 45, 45);
		contentPane.add(f7);
		
		JButton g7 = new JButton("");
		g7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 6);
			}
		});
		g7.setBounds(354, 72, 45, 45);
		contentPane.add(g7);
		
		JButton h7 = new JButton("");
		h7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(1, 7);
			}
		});
		h7.setBounds(411, 72, 45, 45);
		contentPane.add(h7);
		
		JButton a6 = new JButton("");
		a6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 0);
			}
		});
		a6.setBounds(12, 130, 45, 45);
		contentPane.add(a6);
		
		JButton b6 = new JButton("");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 1);
			}
		});
		b6.setBounds(69, 130, 45, 45);
		contentPane.add(b6);
		
		JButton c6 = new JButton("");
		c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 2);
			}
		});
		c6.setBounds(126, 130, 45, 45);
		contentPane.add(c6);
		
		JButton d6 = new JButton("");
		d6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 3);
			}
		});
		d6.setBounds(183, 130, 45, 45);
		contentPane.add(d6);
		
		JButton e6 = new JButton("");
		e6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 4);
			}
		});
		e6.setBounds(240, 130, 45, 45);
		contentPane.add(e6);
		
		JButton f6 = new JButton("");
		f6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 5);
			}
		});
		f6.setBounds(297, 130, 45, 45);
		contentPane.add(f6);
		
		JButton g6 = new JButton("");
		g6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 6);
			}
		});
		g6.setBounds(354, 130, 45, 45);
		contentPane.add(g6);
		
		JButton h6 = new JButton("");
		h6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(2, 7);
			}
		});
		h6.setBounds(411, 130, 45, 45);
		contentPane.add(h6);
		
		JButton a5 = new JButton("");
		a5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 0);
			}
		});
		a5.setBounds(12, 188, 45, 45);
		contentPane.add(a5);
		
		JButton b5 = new JButton("");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 1);
			}
		});
		b5.setBounds(69, 188, 45, 45);
		contentPane.add(b5);
		
		JButton c5 = new JButton("");
		c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 2);
			}
		});
		c5.setBounds(126, 188, 45, 45);
		contentPane.add(c5);
		
		JButton d5 = new JButton("");
		d5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 3);
			}
		});
		d5.setBounds(183, 188, 45, 45);
		contentPane.add(d5);
		
		JButton e5 = new JButton("");
		e5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 4);
			}
		});
		e5.setBounds(240, 188, 45, 45);
		contentPane.add(e5);
		
		JButton f5 = new JButton("");
		f5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 5);
			}
		});
		f5.setBounds(297, 188, 45, 45);
		contentPane.add(f5);
		
		JButton g5 = new JButton("");
		g5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 6);
			}
		});
		g5.setBounds(354, 188, 45, 45);
		contentPane.add(g5);
		
		JButton h5 = new JButton("");
		h5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(3, 7);
			}
		});
		h5.setBounds(411, 188, 45, 45);
		contentPane.add(h5);
		
		JButton a4 = new JButton("");
		a4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 0);
			}
		});
		a4.setBounds(12, 246, 45, 45);
		contentPane.add(a4);
		
		JButton b4 = new JButton("");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 1);
			}
		});
		b4.setBounds(69, 246, 45, 45);
		contentPane.add(b4);
		
		JButton c4 = new JButton("");
		c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 2);
			}
		});
		c4.setBounds(126, 246, 45, 45);
		contentPane.add(c4);
		
		JButton d4 = new JButton("");
		d4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 3);
			}
		});
		d4.setBounds(183, 246, 45, 45);
		contentPane.add(d4);
		
		JButton e4 = new JButton("");
		e4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 4);
			}
		});
		e4.setBounds(240, 246, 45, 45);
		contentPane.add(e4);
		
		JButton f4 = new JButton("");
		f4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 5);
			}
		});
		f4.setBounds(297, 246, 45, 45);
		contentPane.add(f4);
		
		JButton g4 = new JButton("");
		g4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 6);
			}
		});
		g4.setBounds(354, 246, 45, 45);
		contentPane.add(g4);
		
		JButton h4 = new JButton("");
		h4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(4, 7);
			}
		});
		h4.setBounds(411, 246, 45, 45);
		contentPane.add(h4);
		
		JButton a3 = new JButton("");
		a3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 0);
			}
		});
		a3.setBounds(12, 304, 45, 45);
		contentPane.add(a3);
		
		JButton b3 = new JButton("");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 1);
			}
		});
		b3.setBounds(69, 304, 45, 45);
		contentPane.add(b3);
		
		JButton c3 = new JButton("");
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 2);
			}
		});
		c3.setBounds(126, 304, 45, 45);
		contentPane.add(c3);
		
		JButton d3 = new JButton("");
		d3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 3);
			}
		});
		d3.setBounds(183, 304, 45, 45);
		contentPane.add(d3);
		
		JButton e3 = new JButton("");
		e3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 4);
			}
		});
		e3.setBounds(240, 304, 45, 45);
		contentPane.add(e3);
		
		JButton f3 = new JButton("");
		f3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 5);
			}
		});
		f3.setBounds(297, 304, 45, 45);
		contentPane.add(f3);
		
		JButton g3 = new JButton("");
		g3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 6);
			}
		});
		g3.setBounds(354, 304, 45, 45);
		contentPane.add(g3);
		
		JButton h3 = new JButton("");
		h3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(5, 7);
			}
		});
		h3.setBounds(411, 304, 45, 45);
		contentPane.add(h3);
		
		JButton a2 = new JButton("");
		a2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 0);
			}
		});
		a2.setBounds(12, 362, 45, 45);
		contentPane.add(a2);
		
		JButton b2 = new JButton("");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 1);
			}
		});
		b2.setBounds(69, 362, 45, 45);
		contentPane.add(b2);
		
		JButton c2 = new JButton("");
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 2);
			}
		});
		c2.setBounds(126, 362, 45, 45);
		contentPane.add(c2);
		
		JButton d2 = new JButton("");
		d2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 3);
			}
		});
		d2.setBounds(183, 362, 45, 45);
		contentPane.add(d2);
		
		JButton e2 = new JButton("");
		e2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 4);
			}
		});
		e2.setBounds(240, 362, 45, 45);
		contentPane.add(e2);
		
		JButton f2 = new JButton("");
		f2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 5);
			}
		});
		f2.setBounds(297, 362, 45, 45);
		contentPane.add(f2);
		
		JButton g2 = new JButton("");
		g2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 6);
			}
		});
		g2.setBounds(354, 362, 45, 45);
		contentPane.add(g2);
		
		JButton h2 = new JButton("");
		h2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(6, 7);
			}
		});
		h2.setBounds(411, 362, 45, 45);
		contentPane.add(h2);
		
		JButton a1 = new JButton("");
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 0);
			}
		});
		a1.setBounds(12, 420, 45, 45);
		contentPane.add(a1);
		
		JButton b1 = new JButton("");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 1);
			}
		});
		b1.setBounds(69, 420, 45, 45);
		contentPane.add(b1);
		
		JButton c1 = new JButton("");
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 2);
			}
		});
		c1.setBounds(126, 420, 45, 45);
		contentPane.add(c1);
		
		JButton d1 = new JButton("");
		d1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 3);
			}
		});
		d1.setBounds(183, 420, 45, 45);
		contentPane.add(d1);
		
		JButton e1 = new JButton("");
		e1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 4);
			}
		});
		e1.setBounds(240, 420, 45, 45);
		contentPane.add(e1);
		
		JButton f1 = new JButton("");
		f1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 5);
			}
		});
		f1.setBounds(297, 420, 45, 45);
		contentPane.add(f1);
		
		JButton g1 = new JButton("");
		g1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 6);
			}
		});
		g1.setBounds(354, 420, 45, 45);
		contentPane.add(g1);
		
		JButton h1 = new JButton("");
		h1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle(7, 7);
			}
		});
		h1.setBounds(411, 420, 45, 45);
		contentPane.add(h1);
		
		buttons[0][0] = a8;
		buttons[0][1] = b8;
		buttons[0][2] = c8;
		buttons[0][3] = d8;
		buttons[0][4] = e8;
		buttons[0][5] = f8;
		buttons[0][6] = g8;
		buttons[0][7] = h8;
		
		buttons[1][0] = a7;
		buttons[1][1] = b7;
		buttons[1][2] = c7;
		buttons[1][3] = d7;
		buttons[1][4] = e7;
		buttons[1][5] = f7;
		buttons[1][6] = g7;
		buttons[1][7] = h7;
		
		buttons[2][0] = a6;
		buttons[2][1] = b6;
		buttons[2][2] = c6;
		buttons[2][3] = d6;
		buttons[2][4] = e6;
		buttons[2][5] = f6;
		buttons[2][6] = g6;
		buttons[2][7] = h6;
		
		buttons[3][0] = a5;
		buttons[3][1] = b5;
		buttons[3][2] = c5;
		buttons[3][3] = d5;
		buttons[3][4] = e5;
		buttons[3][5] = f5;
		buttons[3][6] = g5;
		buttons[3][7] = h5;
		
		buttons[4][0] = a4;
		buttons[4][1] = b4;
		buttons[4][2] = c4;
		buttons[4][3] = d4;
		buttons[4][4] = e4;
		buttons[4][5] = f4;
		buttons[4][6] = g4;
		buttons[4][7] = h4;
		
		buttons[5][0] = a3;
		buttons[5][1] = b3;
		buttons[5][2] = c3;
		buttons[5][3] = d3;
		buttons[5][4] = e3;
		buttons[5][5] = f3;
		buttons[5][6] = g3;
		buttons[5][7] = h3;
		
		buttons[6][0] = a2;
		buttons[6][1] = b2;
		buttons[6][2] = c2;
		buttons[6][3] = d2;
		buttons[6][4] = e2;
		buttons[6][5] = f2;
		buttons[6][6] = g2;
		buttons[6][7] = h2;
		
		buttons[7][0] = a1;
		buttons[7][1] = b1;
		buttons[7][2] = c1;
		buttons[7][3] = d1;
		buttons[7][4] = e1;
		buttons[7][5] = f1;
		buttons[7][6] = g1;
		buttons[7][7] = h1;
		
		zug = new JLabel("Wei\u00DF ist am Zug");
		zug.setFont(new Font("Tahoma", Font.BOLD, 22));
		zug.setBounds(490, 13, 291, 45);
		contentPane.add(zug);
		
		schach = new JLabel("");
		schach.setFont(new Font("Tahoma", Font.BOLD, 22));
		schach.setBounds(490, 72, 291, 45);
		contentPane.add(schach);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brett = new Schachbrett_OOP();
				update();
				update_time();
				resetColors();
			}
		});
		btnReset.setBounds(490, 420, 291, 45);
		contentPane.add(btnReset);
		
		JLabel lblGeschlagen = new JLabel("Geschlagen:");
		lblGeschlagen.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGeschlagen.setBounds(490, 188, 291, 45);
		contentPane.add(lblGeschlagen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(490, 246, 291, 161);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setText("");
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		lblZeit = new JLabel("Zeit:");
		lblZeit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblZeit.setBounds(490, 130, 291, 45);
		contentPane.add(lblZeit);
		
		try
		{
			blank_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/blank_light.png")));
			blank_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/blank_dark.png")));
			
			bauer_black_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/bauer_black_light.png")));
			bauer_black_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/bauer_black_dark.png")));
			
			turm_black_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/turm_black_light.png")));
			turm_black_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/turm_black_dark.png")));
			
			springer_black_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/springer_black_light.png")));
			springer_black_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/springer_black_dark.png")));
			
			laeufer_black_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/laeufer_black_light.png")));
			laeufer_black_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/laeufer_black_dark.png")));
			
			koenig_black_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/koenig_black_light.png")));
			koenig_black_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/koenig_black_dark.png")));
			
			dame_black_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/dame_black_light.png")));
			dame_black_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/dame_black_dark.png")));
			
			bauer_white_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/bauer_white_light.png")));
			bauer_white_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/bauer_white_dark.png")));
			
			turm_white_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/turm_white_light.png")));
			turm_white_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/turm_white_dark.png")));
			
			springer_white_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/springer_white_light.png")));
			springer_white_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/springer_white_dark.png")));
			
			laeufer_white_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/laeufer_white_light.png")));
			laeufer_white_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/laeufer_white_dark.png")));
			
			koenig_white_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/koenig_white_light.png")));
			koenig_white_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/koenig_white_dark.png")));
			
			dame_white_light = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/dame_white_light.png")));
			dame_white_dark = new ImageIcon(ImageIO.read(this.getClass().getResource("/res/dame_white_dark.png")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		update();
		resetColors();
		
		Timer update_timer = new Timer();
		update_timer.schedule(new TimerTask()
				{
					@Override
					public void run()
					{
						update_time();
					}
				}, 0, 100);
		
		Timer tick_timer = new Timer();
		tick_timer.schedule(new TimerTask()
				{
					@Override
					public void run()
					{
						brett.tick();
					}
				}, 0, 1000);
	}
	
	private void update_time()
	{
		String minutes = String.format("%02d", brett.getTimer().toMinutes());
		String seconds = String.format("%02d", brett.getTimer().getSeconds() - brett.getTimer().toMinutes()*60);
		lblZeit.setText("Zeit: " + minutes + ":" + seconds);
		
		if(brett.isBlackTurn() && brett.getTimer_black() <= 0)
		{
			schach.setText("Schwarz timeout");
			return;
		}
		
		if(!brett.isBlackTurn() && brett.getTimer_white() <= 0)
		{
			schach.setText("Weiﬂ timeout");
			return;
		}
	}
	
	private void handle(int x, int y)
	{
		resetColors();
		
		if(brett.isBlackTurn() && brett.getTimer_black() <= 0)
		{
			schach.setText("Schwarz timeout");
			return;
		}
		
		if(!brett.isBlackTurn() && brett.getTimer_white() <= 0)
		{
			schach.setText("Weiﬂ timeout");
			return;
		}
		
		if(fromx == -1 && fromy == -1)
		{
			if(brett.getBrett()[x][y].isBlack() == brett.isBlackTurn() == brett.getBrett()[x][y] instanceof Dummy == false)
			{
				fromx = x;
				fromy = y;
				setColor(fromx, fromy, Color.BLACK);
				highlightZuege();
			}
		}
		else
		{
			if(x != fromx || y != fromy)
			{
				brett.zug(fromx, fromy, x, y);
				update();
			}
			
			fromx = -1;
			fromy = -1;
		}
	}
	
	private void resetColors()
	{
		for(JButton[] a : buttons)
			for(JButton b : a)
				b.setBorder(new JButton().getBorder());
	}
	
	private void setColor(int i , int j, Color color)
	{
		buttons[i][j].setBorder(BorderFactory.createLineBorder(color, 3, false));
	}
	
	private void highlightZuege()
	{
		List<Zug> zuege = brett.getBrett()[fromx][fromy].moeglicheZuege();
		
		for(Zug z : zuege)
		{
			setColor(z.getFeld2().getX(), z.getFeld2().getY(), Color.CYAN);
		}
		
		List<Zug> schlaege = brett.getBrett()[fromx][fromy].moeglicheSchlaege();
		
		for(Zug z : schlaege)
		{
			setColor(z.getFeld2().getX(), z.getFeld2().getY(), Color.RED);
		}
	}
	
	private void update()
	{
		textArea.setText("");
		
		for(String f : brett.getGeschlagen())
		{
			textArea.append(f + "\n");
		}
		
		if(brett.isBlackTurn())
			zug.setText("Schwarz ist am Zug");
		else
			zug.setText("Weiﬂ ist am Zug");
		
		if(brett.isSchach(true))
		{
			if(brett.isSchachMatt(true))
			{
				schach.setText("Schwarz ist Schach Matt");
				zug.setText("");
			}
			else
				schach.setText("Schwarz ist Schach");
		}
		else if(brett.isSchach(false))
		{
			if(brett.isSchachMatt(false))
			{
				schach.setText("Weiﬂ ist Schach Matt");
				zug.setText("");
			}
			else
				schach.setText("Weiﬂ ist Schach");
		}
		else
			schach.setText("");
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(brett.getBrett()[i][j] instanceof Dummy)
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(blank_light);
					else
						buttons[i][j].setIcon(blank_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Bauer && brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(bauer_black_light);
					else
						buttons[i][j].setIcon(bauer_black_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Turm && brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(turm_black_light);
					else
						buttons[i][j].setIcon(turm_black_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Springer && brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(springer_black_light);
					else
						buttons[i][j].setIcon(springer_black_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Laeufer && brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(laeufer_black_light);
					else
						buttons[i][j].setIcon(laeufer_black_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Koenig && brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(koenig_black_light);
					else
						buttons[i][j].setIcon(koenig_black_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Dame && brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(dame_black_light);
					else
						buttons[i][j].setIcon(dame_black_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Bauer && !brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(bauer_white_light);
					else
						buttons[i][j].setIcon(bauer_white_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Turm && !brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(turm_white_light);
					else
						buttons[i][j].setIcon(turm_white_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Springer && !brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(springer_white_light);
					else
						buttons[i][j].setIcon(springer_white_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Laeufer && !brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(laeufer_white_light);
					else
						buttons[i][j].setIcon(laeufer_white_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Koenig && !brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(koenig_white_light);
					else
						buttons[i][j].setIcon(koenig_white_dark);
				}
				else if(brett.getBrett()[i][j] instanceof Dame && !brett.getBrett()[i][j].isBlack())
				{
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						buttons[i][j].setIcon(dame_white_light);
					else
						buttons[i][j].setIcon(dame_white_dark);
				}
			}
		}
	}
}
