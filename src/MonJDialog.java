import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

/**
 * @author hugo
 *
 */
public class MonJDialog implements ActionListener, ChangeListener //Class pour ouvrir un JDialogde confirmation
{	//Les variables													//avec possibilité de modifier la source générée
	public static JDialog dial = new JDialog();
	public Color fond = Color.white;
	public JPanel panel = new JPanel(new BorderLayout());
	public JPanel container = new JPanel(new GridBagLayout());
	public ImageIcon help = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/help.png")));
	public ImageIcon down = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/arrow_down.png")));
	public ImageIcon next = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/arrow_next.png")));
	public ImageIcon validate = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/action_check.png")));
	public ImageIcon delete = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/action_delete.png")));
	public JButton openClose;
	public static JButton okay;
	public JButton cancel;
	public JLabel stateJT, label1,label2, img;
	public JTextArea jta;
	public JCheckBox editable;
	public JScrollPane scroll;
	public static Object src;
	public File fichier;
	public String fichierComplet, toWrite = Generer.general+Generer.menuEnt+Generer.toolbar+Generer.system+Generer.action+Generer.fin;
	public static JFileChooser fileChooser = new JFileChooser();
	public static FileFilter java = new Filtre("Java (*.java)",".java");
	
	public MonJDialog() //Initialise le JDialog
	{
		dial.setTitle("Générer - IHM Generator");
		dial.setSize(450,180);		
		dial.setResizable(false);
		dial.setLocationRelativeTo(null);
		dial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dial.setUndecorated(false);
		dial.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icone.gif")));	
		dial.setBackground(fond);
		dial.setContentPane(container());
		dial.setVisible(true);
		
	}

	private Container container()	//Contenu du JDialog
	{
		container.setBackground(fond);
		//container.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(fond);
		
		
		openClose = new JButton(next);
			openClose.setToolTipText("Afficher/Masquer le JTextArea");
			openClose.addActionListener(this);
			
		okay = new JButton("Ok",validate);
			okay.addActionListener(this);
		cancel = new JButton("Annuler",delete);
			cancel.addActionListener(this);
		
		jta = new JTextArea(20,25);
			//jta.setLineWrap(true);
			jta.setText(toWrite);
			jta.setEditable(false);
			jta.setCaretPosition(0);	//Met le pointeur au début du JTA
			
		editable = new JCheckBox("Editer ?");
			editable.setBackground(fond);
			editable.addChangeListener(this);
			editable.setVisible(false);
		
		label1 = new JLabel("Vous étes sur le point de générer");
		label2 = new JLabel("le document que vous avez parametré... Continuer ?");
		img = new JLabel(help);
		
		scroll = new JScrollPane(jta);
			scroll.setBackground(fond);
			scroll.setVisible(false);
					
		GridBagConstraints c = new GridBagConstraints ();
			//c.gridx = 0;	// .gridx = abscisse de l'objet
			//c.gridy = 0;	// .gridy = ordonnée de l'objet
			//c.weightx = 1;	// .weightx ou .weighty = défini un poid pour l'objet en largeur et/ou hauteur
			//c.gridwidth = 1;	// .gridwidth = s'étend sur 1, 2, 3, ... cases en largeur
			//c.gridheight = 1;	// .gridheight = s'étend sur 1, 2, 3, ... cases en hauteur
			//c.ipadx = 0;	// .ipadx = marges internes en largeur
			//c.ipady = 0;	// .ipady = marges internes en hauteur
			//c.insets = new Insets(10,10,10,10);	// .insets... = défini des marges
			//c.fill = GridBagConstraints.NONE;	// .fill = rempli, soit Horizontalement, verticalement ou les deux
			//c.anchor = GridBagConstraints.CENTER;	// .anchor = accroche l'objet, en début, milieu ou fin de ligne
		
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 0;
			c.gridheight = 2;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,0,10,0);
		container.add(img,c);
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 0;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,0,10,0);
		container.add(label1,c);
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 0;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(-30,0,10,0);
		container.add(label2,c);
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 0;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_END;
			c.insets = new Insets(-30,0,10,-26);
		container.add(openClose,c);
			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 0;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(-30,0,10,0);
		container.add(editable,c);
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 1;
			c.weighty = 1;
			c.gridheight = 1;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0,0,10,0);
		container.add(scroll,c);
			c.gridx = 0;
			c.gridy = 5;
			c.weightx = 1;
			c.weighty = 0;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LAST_LINE_START;
			c.insets = new Insets(0,0,0,0);
		container.add(okay,c);
			c.gridx = 1;
			c.gridy = 5;
			c.weightx = 1;
			c.weighty = 1;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LAST_LINE_END;
			c.insets = new Insets(0,0,0,0);
		container.add(cancel,c);
		
		panel.add(container,BorderLayout.CENTER);
		
		return panel;
	}

	public void save()	//Pour sauvegarder le document créé
	{
				//*//
				fichier = new File(Generer.name+".java");	//Nom du fichier identique à la Class, regle Java !!!!
				fileChooser.addChoosableFileFilter(java);	//Ajout du Filtre Java
				fileChooser.setBackground(fond);			//Met un fond blanc
				fileChooser.setFileFilter(java);			//Positionne le Filtre sur Java
		        fileChooser.setSelectedFile(fichier);		//Met le nom du fichier dans l'emplacement prévu, ne pas remplacer !
				
				int i = fileChooser.showSaveDialog(dial);	//Ouvre le Dialog de Sauvegarde
		        if(i == fileChooser.APPROVE_OPTION)			//Si on clique sur Okay
				{
					fichier = fileChooser.getSelectedFile();	//Le chemin, + le nom du fichier
					
					String extFichier = Filtre.theExtension;	//Recupere le nom de l'éxtension de la class Filre
					String nomFichier = fichier.getName();		//Recupere le nom du fichier
					
					if(nomFichier.endsWith(".java"))			//Si l'utilisateur à préciser l'extension
					{											//On fait tout normalement 
						try 
						{
							BufferedWriter bwriter = new BufferedWriter(new FileWriter(fichier));
							bwriter.write(jta.getText());
							bwriter.close();
							
						} catch (IOException e) 
						{
							e.printStackTrace();
						}
					}else
					{		
						fichierComplet = (fichier+extFichier);	//Sinon on ajoute l'éxtension au nom ! :)	
																//Et on enregistre
						try 
						{
							BufferedWriter bwriter = new BufferedWriter(new FileWriter(fichierComplet));
							bwriter.write(jta.getText());
							bwriter.close();
							
						} catch (IOException e) 
						{
							e.printStackTrace();
						}		
					}
					dial.dispose();
				}
	}

	public void permute()	//Permet d'afficher oui ou non la JTextArea(JTA), et le JChekBox "editable"
	{
		if (scroll.isVisible()==false)
		{
			dial.setSize(450,550);
			dial.setLocationRelativeTo(null);
			openClose.setIcon(down);
			scroll.setVisible(true);
			editable.setVisible(true);
			editable.repaint();
			openClose.repaint();
			scroll.repaint();
			dial.repaint();
		}else
		{
			dial.setSize(450,180);
			dial.setLocationRelativeTo(null);
			openClose.setIcon(next);
			scroll.setVisible(false);
			editable.setVisible(false);
			editable.repaint();
			openClose.repaint();
			scroll.repaint();
			dial.repaint();
		}
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		src = e.getSource();
		
		if (src == openClose)
		{
			permute();
			
		}else if (src == okay)
		{
			save();
			
		}else
		{
			jta.setText("");
			toWrite = "";
			Generer.general = "";
			Generer.menu = "";
			Generer.menuIt = "";
			Generer.menuEnt = "";
			Generer.toolbar = "";
			Generer.implement = "";
			Generer.fin = "";
			Generer.system = "";
			Generer.toolbar = "";
			Generer.button = "a";
			Generer.action = "";
			dial.dispose();
		}
	}

	public void stateChanged(ChangeEvent e) 
	{
		if (editable.isSelected())
		{
			jta.setEditable(true);
		}else
		{
			jta.setEditable(false);
		}
	}

}
