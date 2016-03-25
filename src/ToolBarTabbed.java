/**
 * @author hugo
 *
 */
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ToolBarTabbed extends JPanel implements ActionListener, FocusListener
{	//Les variables
	public Color fond = Color.WHITE;
	public JPanel masterP = new JPanel();
	public JTextField nomTF, imageTF;
	public JLabel nom, image, choisis, info1, vide;
	public static JCheckBox tooltip, listener;
	public static JComboBox toolCB;
	public JButton toolBu;
	public ImageIcon add = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/action_add.png")));
	
	public ToolBarTabbed()
	{
		masterP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		masterP.setBackground(fond);
		masterP.setLayout(new BorderLayout());
		
		JPanel toolBarP = new JPanel(new GridBagLayout());
			toolBarP.setBackground(fond);
			toolBarP.setBorder(BorderFactory.createTitledBorder("JToolBar - Entrez le nom, l'image,  puis faites -ajouter-"));
		
		choisis = new JLabel("Choisis");	
		nom = new JLabel("Nom : ");
		image = new JLabel("Image :");
		info1 = new JLabel("Pour l'image, procéder comme indiqué dans l'onglet -General-");
		vide = new JLabel("");
		
		listener = new JCheckBox("Listeners ? ");
			listener.setBackground(fond);
			listener.setEnabled(true);
			listener.setSelected(true);
			//listener.addChangeListener(this);
			
		tooltip = new JCheckBox("ToolTips ? ");
			tooltip.setBackground(fond);
			tooltip.setEnabled(true);
			tooltip.setSelected(true);
			//tooltip.addChangeListener(this);
			
		nomTF = new JTextField("",35);
			nomTF.addFocusListener(this);
		imageTF = new JTextField("",35);
			imageTF.addFocusListener(this);
		
			
		toolBu = new JButton(add);
			toolBu.addActionListener(this);
			toolBu.setToolTipText("Ajouter");
			
		toolCB = new JComboBox();

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
		
		masterP.add(toolBarP,BorderLayout.NORTH);
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.25;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(nom,c);
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 1;
			c.ipadx = 0;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(nomTF,c);
			c.gridx = 3;
			c.gridy = 3;
			c.weightx = 0.4;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,15,10,3);
		toolBarP.add(toolBu,c);
			c.gridx = 2;
			c.gridy = 3;
			c.weightx = 1;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(vide,c);
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 0.25;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(choisis,c);
			c.gridx = 1;
			c.gridy = 3;
			c.weightx = 1;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(toolCB,c);
			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 1;
			c.ipadx = 0;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(info1,c);
			c.gridx = 3;
			c.gridy = 0;
			c.weightx = 0.4;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,15,10,3);
		toolBarP.add(tooltip,c);
			c.gridx = 3;
			c.gridy = 1;
			c.weightx = 0.4;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,15,10,3);
		toolBarP.add(listener,c);
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0.25;
			c.ipadx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(image,c);
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 1;
			c.ipadx = 0;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0,3,10,3);
		toolBarP.add(imageTF,c);
	
		
		this.setBackground(fond);
		this.add(masterP, BorderLayout.CENTER);
		
	}
	public boolean isEmpty()
	{		// .trim()= retire les éspaces avant et apres le String
		if (nomTF.getText().trim().equals("") || imageTF.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(this," Le JTextField correspondant est vide,\n" +
											" veuillez indiquer un nom,\n Merci", 
											"Erreur - JTextField vide",JOptionPane.ERROR_MESSAGE);
			return true;
		}
			return false;
	}
	
	public void focusGained(FocusEvent e) //Comme pour le GeneralTabbed
	{
		((JTextComponent) e.getSource()).selectAll();
		Fenetre.frame.getRootPane().setDefaultButton(toolBu);
	}

	public void focusLost(FocusEvent e) 
	{
		((JTextComponent) e.getSource()).setCaretPosition(0);
		Fenetre.frame.getRootPane().setDefaultButton(null);
		
	}

	public void actionPerformed(ActionEvent e)	//Ajoute le JButton dans JToolBar
	{
		if (!isEmpty())
		{
			toolCB.addItem(nomTF.getText()+";"+imageTF.getText());
		}
	}
}
