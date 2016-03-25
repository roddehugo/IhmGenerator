/**
 * @author hugo
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;


public class MenuTabbed extends JPanel implements FocusListener, ActionListener, ChangeListener
{	//Les variables
	public Color fond = Color.WHITE;
	public JPanel masterP = new JPanel();
	public JLabel nomJMenu,nomJMenuItem, choisis, choisis2, dans, vide, iconeL;
	public JTextField JMenuTF, JMenuItemTF, iconeTF;
	public JButton JMenuBu, JMenuItemBu, JSeparatorBu;
	public ImageIcon add = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/action_add.png")));
	public static JComboBox JMenuCB, JMenuItemCB, JMenuSelCB;
	public static JCheckBox mnemonic, raccourci, listener, icone;
	public boolean added = false;
	
	public MenuTabbed()
	{
		masterP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		masterP.setBackground(fond);
		masterP.setLayout(new BorderLayout());
		
		JPanel menuP = new JPanel(new GridBagLayout());
			menuP.setBackground(fond);
			menuP.setBorder(BorderFactory.createTitledBorder("JMenu - Entrez le nom, puis faites -ajouter-"));
			//Une bordure titrée !
		
		JPanel menuItemP = new JPanel(new GridBagLayout());
			menuItemP.setBackground(fond);
			menuItemP.setBorder(BorderFactory.createTitledBorder("JMenuItem - Entrez le nom, puis le menu d'appartenance, puis faites -ajouter-"));
		
		String nom = "Nom :";
		nomJMenu = new JLabel(nom);
		nomJMenuItem = new JLabel(nom);
		choisis = new JLabel("Choisis");
		choisis2 = new JLabel("Choisis");
		dans = new JLabel("dans");
		vide = new JLabel("");
		iconeL = new JLabel("Icone :");
		
		JMenuTF = new JTextField("Ne pas mettre deux fois le même nom. Sinon, Beug assuré ! :p",35);
			JMenuTF.addFocusListener(this);
		JMenuItemTF = new JTextField("",35);
			JMenuItemTF.addFocusListener(this);
		iconeTF = new JTextField("Procéder comme dans l'onglet -General-",35);
			iconeTF.addFocusListener(this);
			iconeTF.setEditable(false);
			
		mnemonic = new JCheckBox("Mnemonics ? ");
			mnemonic.setBackground(fond);	
			mnemonic.setEnabled(true);
			mnemonic.setSelected(true);
			//mnemonic.addChangeListener(this);
		raccourci = new JCheckBox("Raccourcis ? ");
			raccourci.setBackground(fond);
			raccourci.setEnabled(true);
			raccourci.setSelected(true);
			//raccourci.addChangeListener(this);
		listener = new JCheckBox("Listeners ? ");
			listener.setBackground(fond);
			listener.setEnabled(true);
			listener.setSelected(true);
			//listener.addChangeListener(this);
		icone = new JCheckBox("icone ?");
			icone.setBackground(fond);
			icone.setEnabled(true);
			icone.setSelected(false);
			icone.addChangeListener(this);
			
		JMenuBu = new JButton(add);
			JMenuBu.addActionListener(this);
			JMenuBu.setToolTipText("Ajouter");
		JMenuItemBu = new JButton(add);
			JMenuItemBu.addActionListener(this);
			JMenuItemBu.setToolTipText("Ajouter");
		JSeparatorBu = new JButton("JSeparator",add);
			JSeparatorBu.addActionListener(this);
			JSeparatorBu.setToolTipText("Ajouter une séparation");
			
		JMenuCB = new JComboBox();
		
		JMenuItemCB = new JComboBox();
		
		JMenuSelCB = new JComboBox();
			JMenuSelCB.addFocusListener(this);
			
		GridBagConstraints c = new GridBagConstraints ();
		GridBagConstraints d = new GridBagConstraints ();
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
			
			
		masterP.add(menuP,BorderLayout.NORTH);
				c.gridx = 0;
				c.gridy = 0;
				c.weightx = 0.25;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.NONE;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0,3,10,3);
			menuP.add(nomJMenu,c);
				c.gridx = 1;
				c.gridy = 0;
				c.weightx = 1;
				c.ipadx = 0;
				c.gridwidth = 2;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.CENTER;
				c.insets = new Insets(0,3,10,3);
			menuP.add(JMenuTF,c);
				c.gridx = 3;
				c.gridy = 1;
				c.weightx = 0.4;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.NONE;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0,15,10,0);
			menuP.add(JMenuBu,c);
				c.gridx = 0;
				c.gridy = 1;
				c.weightx = 0.25;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.NONE;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0,3,10,3);
			menuP.add(choisis,c);
				c.gridx = 1;
				c.gridy = 1;
				c.weightx = 1;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.CENTER;
				c.insets = new Insets(0,3,10,3);
			menuP.add(JMenuCB,c);
				c.gridx = 2;
				c.gridy = 1;
				c.weightx = 1;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.CENTER;
				c.insets = new Insets(0,3,10,3);
			menuP.add(vide,c);
				c.gridx = 3;
				c.gridy = 0;
				c.weightx = 0.4;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.NONE;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0,15,10,0);
			menuP.add(mnemonic,c);
		//masterP.add(separator,BorderLayout.CENTER);
		masterP.add(menuItemP,BorderLayout.CENTER);
				c.gridx = 0;
				c.gridy = 1;
				c.weightx = 0.25;
				c.ipadx = 0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.NONE;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0,3,10,3);
			menuItemP.add(iconeL,c);
				d.gridx = 0;
				d.gridy = 0;
				d.weightx = 0.25;
				d.ipadx = 0;
				c.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(nomJMenuItem,d);
				d.gridx = 1;
				d.gridy = 0;
				d.weightx = 1;
				d.ipadx = 0;
				d.gridwidth = 2;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(JMenuItemTF,d);
				d.gridx = 3;
				d.gridy = 0;
				d.weightx = 1;
				d.ipadx = 0;
				c.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,15,10,0);
			menuItemP.add(icone,d);
				d.gridx = 1;
				d.gridy = 1;
				d.weightx = 1;
				d.ipadx = 0;
				d.gridwidth = 2;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(iconeTF,d);
				d.gridx = 3;
				d.gridy = 3;
				d.weightx = 0.4;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,15,10,0);
			menuItemP.add(JMenuItemBu,d);
				d.gridx = 0;
				d.gridy = 3;
				d.weightx = 0.25;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(choisis2,d);
				d.gridx = 1;
				d.gridy = 3;
				d.weightx = 1;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(JMenuItemCB,d);
				d.gridx = 1;
				d.gridy = 2;
				d.weightx = 1;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(JMenuSelCB,d);
				d.gridx = 0;
				d.gridy = 2;
				d.weightx = 0.25;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(dans,d);
				d.gridx = 3;
				d.gridy = 1;
				d.weightx = 0.4;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,15,10,0);
			menuItemP.add(listener,d);
				d.gridx = 3;
				d.gridy = 2;
				d.weightx = 0.4;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,15,10,0);
			menuItemP.add(raccourci,d);
				d.gridx = 2;
				d.gridy = 2;
				d.weightx = 0.5;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_END;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(JSeparatorBu,d);
			
		this.setBackground(fond);
		this.add(masterP, BorderLayout.CENTER);
	}
	
	public boolean isEmpty()
	{				// .trim()= retire les éspaces avant et apres le String
		if ((JMenuTF.getText().trim().equals("") ||
				JMenuTF.getText().trim().equals("Ne pas mettre deux fois le même nom. Sinon, Beug assuré ! :p")) &&
				(JMenuItemTF.getText().trim().equals("") || JMenuItemTF.getText().trim().equals("")) ||
				(icone.isSelected() && (iconeTF.getText().trim().equals("") ||
						iconeTF.getText().trim().equals("Procéder comme dans l'onglet -General-"))))
		{
			JOptionPane.showMessageDialog(this," Le JTextField correspondant est vide,\n" +
											" veuillez indiquer un nom,\n Merci", 
											"Erreur - JTextField vide",JOptionPane.ERROR_MESSAGE);
			return true;
		}
			return false;
	}
	
	public void ajouterJMenu()	//Pour ajouter le nom du JMenu dans le JMenuCheckBox
	{
		if (!isEmpty())
		{
			JMenuCB.addItem(JMenuTF.getText());
			JMenuSelCB.addItem(JMenuTF.getText());
		}
	}

	public void ajouterJMenuItem()	//Pour ajouter le nom du JMenuItem dans le JMenuItemCheckBox ainsi que le JMenu auquel
	{								//il appartient, et son icone
		if (!isEmpty())
		{
			if (icone.isSelected())
			{
				JMenuItemCB.addItem(JMenuSelCB.getSelectedItem()+";"+JMenuItemTF.getText()+";"+iconeTF.getText());
			}else
			{
				JMenuItemCB.addItem(JMenuSelCB.getSelectedItem()+";"+JMenuItemTF.getText());
			}
		}
	}

	public void ajouterSeparator()	//Pour ajouter un Separator
	{
		if (JMenuCB.getItemCount()!= 0)
		{
			JMenuItemCB.addItem(JMenuSelCB.getSelectedItem().toString().toLowerCase()+".Separator");
		}
	}
	
	public void focusGained(FocusEvent e) 
	{
		if (e.getSource()==JMenuItemTF)
		{
			Fenetre.frame.getRootPane().setDefaultButton(JMenuItemBu);	//Définie le bouton par défaut, suivant le JtextField séléctionner
			JMenuItemTF.selectAll();
		}else if (e.getSource()==JMenuTF)
		{
			JMenuTF.selectAll();
			Fenetre.frame.getRootPane().setDefaultButton(JMenuBu);
		}else
		{
			Fenetre.frame.getRootPane().setDefaultButton(JMenuItemBu);
		}
	}

	public void focusLost(FocusEvent e) //Comme pour le GeneralTabbed
	{
		if (e.getSource()==JMenuItemTF)
		{
			JMenuItemTF.setCaretPosition(0);
		}else if (e.getSource()==JMenuTF)
		{
			JMenuTF.setCaretPosition(0);
		}
		Fenetre.frame.getRootPane().setDefaultButton(null); //Met le Jbutton par défault égal à null, soit pas de bouton par défaut
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object src = e.getSource();
		
		if (src==JMenuBu)
		{
			ajouterJMenu();
		}else if (src==JMenuItemBu)
		{
			ajouterJMenuItem();
		}else
		{
			ajouterSeparator();
		}
	}

	public void stateChanged(ChangeEvent e) 
	{
		if (icone.isSelected())
		{
			iconeTF.setEditable(true);
		}else
		{
			iconeTF.setEditable(false);
		}
	}
	
	
}
