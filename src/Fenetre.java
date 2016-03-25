import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Fenetre implements ActionListener, WindowListener {
	public static JFrame frame = new JFrame();
	public JPanel container, panel;
	public Color fond = Color.white;
	public boolean isSauver = true, isFull = true;
	public ImageIcon icoHelp;
	public static JTabbedPane onglets;
	
	
	//**********************************//
	//*************Listener*************//
	//**********************************//
	
	public GenererListener gListener = new GenererListener();
	public ContacterListener cListener = new ContacterListener();
	
	//**********************************//
	//*******JMenu et System Tray*******//
	//**********************************//
	
	public JMenuBar menuBar;
	public JMenu fichier, propos, aideM;
	public JMenuItem generer, developper, mContact, quitter, aide, cacherM;
	
	//**********************************//
	//*************ToolBar*************//
	//**********************************//
	
	public JToolBar toolBar;
	public JButton genererBut;
	
	public Fenetre()	//Initialisation de la JFrame, du JMenu, du JToolBar, et du SystemTray
	{
		frame.setTitle("IHM Generator");
		frame.setSize(700,600);		
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		frame.setUndecorated(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icone.gif")));	//Change l'image par défaut du Titre
		frame.setBackground(fond);
		frame.setContentPane(container());
		initMenu();
		initToolBar();
		frame.setVisible(true);
		aide();
		
	}	
	
	public Container container()	//Contenu du JPanel de la JFrame, contient le JTabbedPane
	{
		panel = new JPanel();
			panel.setBackground(fond);
			panel.setLayout(new BorderLayout());
		
		container = new JPanel();
			container.setBackground(fond);
			container.setLayout(new BorderLayout());
			container.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		onglets = new JTabbedPane(SwingConstants.TOP);
			onglets.setBackground(fond);
			onglets.addTab("Général",new GeneralTabbed());
			onglets.addTab("Menu",new MenuTabbed());
			onglets.addTab("ToolBar",new ToolBarTabbed());
			onglets.addTab("SystemTray",new SystemTrayTabbed());
		
		container.add(onglets, BorderLayout.CENTER);
		panel.add(container, BorderLayout.CENTER);
		return panel;	
	}

	public void initToolBar()	//Le JToolBar, facile, rien d'inhabituel
	{
		toolBar = new JToolBar();
			toolBar.setFloatable(false);
			
		genererBut = new JButton(new ImageIcon(getClass().getResource("/save.png")));
			genererBut.setBackground(fond);
			genererBut.setToolTipText("Générer");
			genererBut.addActionListener(gListener);
			
		toolBar.add(genererBut);
		
		frame.add(toolBar,BorderLayout.NORTH);
		
	}

	public void initMenu()	//Le Jmenu, facile, rien d'inhabituel, KeyStroke : VK_ = touche..., CTRL_DOWN_MASK = Ctrl
	{
		menuBar = new JMenuBar();
		
		fichier = new JMenu("Fichier");
			fichier.setMnemonic('F');
			
		propos = new JMenu("A propos");
			propos.setMnemonic('P');
			
		aideM = new JMenu("?");
			
		generer = new JMenuItem("Générer",new ImageIcon(getClass().getResource("/save.png")));
			generer.addActionListener(gListener);
			generer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,KeyEvent.CTRL_DOWN_MASK));
		quitter = new JMenuItem("Quitter",new ImageIcon(getClass().getResource("/action_delete.png")));
			quitter.addActionListener(this);
			quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK));
		developper = new JMenuItem("Developpeur",new ImageIcon(getClass().getResource("/user.png")));
			developper.addActionListener(this);
			developper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
		mContact = new JMenuItem("Me Contacter",new ImageIcon(getClass().getResource("/letter.png")));
			mContact.addActionListener(cListener);
			mContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_DOWN_MASK));
		aide = new JMenuItem("Aide",new ImageIcon(getClass().getResource("/comments.png")));
			aide.addActionListener(this);
			aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
		cacherM = new JMenuItem("Cacher",new ImageIcon(getClass().getResource("/minimize.png")));
			cacherM.addActionListener(this);
			cacherM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK));
					
			onglets.setEnabledAt(0,true);	//Pour dsésactiver, activer les différents onglets
			onglets.setEnabledAt(1,false);
			onglets.setEnabledAt(2,false);
			onglets.setEnabledAt(3,false);
			
		menuBar.add(fichier);
			fichier.add(generer);
			fichier.addSeparator();
			fichier.add(cacherM);
			fichier.add(quitter);
		menuBar.add(propos);
			propos.add(developper);
			propos.add(mContact);
		menuBar.add(aideM);
			aideM.add(aide);
		frame.setJMenuBar(menuBar);
		
	}
	
	public void developper()
	{
		JOptionPane.showMessageDialog(null," Bienvenue dans l'interface developpeur d'IhmGenerator :"
										+"\n Logiciel : Open-Source"
										+"\n Auteur : Sarathai"
										+"\n Année : 2008","Aide", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void aide()
	{
		JOptionPane.showMessageDialog(null," Bienvenue dans l'interface d'aide d'IhmGenerator :"
										+"\n Pour enregistrer votre travail, il faut"
										+"\n le générer."
										+"\n Laissez-vous guider par la simplicité"
										+"\n de la création de JFrame !", "Aide", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void close()
	{
		System.exit(0);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		if (src == developper)
		{
			developper();
		}else if (src == aide)
		{
			aide();
		}else 
		{
			close();
		}
		
	}

	class GenererListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Generer gen = new Generer();
		}
	}
	
	
	class ContacterListener implements ActionListener	// /!\ L'envoi d'un mail /!\
	{
		public void actionPerformed(ActionEvent ev) 
		{
			if(Desktop.isDesktopSupported())
			{		
				if(Desktop.getDesktop().isSupported(Desktop.Action.MAIL)){
					try 
					{							//mailto: + l'adresse du déstinataire + ? + subject(Objet du message)=...(%20 = un éspace)
				Desktop.getDesktop().mail(new URI("mailto:rodde.hugo@hotmail.fr?subject=A%20propos%20du%20programme%20:%20IhmGenrator." +
						"&body=Merci%20de%20me%20faire%20part%20de%20vos%20impressions%20sur%20ce%20programme."));
						// & + body(le corps du message)=...
					} catch (IOException e) 
					{
						e.printStackTrace();
					} catch (URISyntaxException e) 
					{
						e.printStackTrace();
					}
				}else{}
			
			}
		
		}	
	}
	
	public static void main(String[] args)
	{
		//*// //Pour le Look&Feel par défaut de votre systeme
		String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();
		
		try {

			UIManager.setLookAndFeel(lookAndFeelName);
		} 
		catch (ClassNotFoundException e) {} 
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		//*/
		
		Fenetre fe = new Fenetre();
		
	}

	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) 
	{
		close();
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

	
}