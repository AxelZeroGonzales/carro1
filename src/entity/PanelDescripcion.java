package entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class PanelDescripcion extends JPanel {

	private JTextPane labEtiqueta;

	public PanelDescripcion(String descripcion, String titulo) {
		setPreferredSize(new Dimension(750, 105));
		this.labEtiqueta = new JTextPane();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Entrenador " + titulo));

		this.labEtiqueta.setBackground(Color.WHITE);
		this.labEtiqueta.setFont(new Font("Arial", 0, 12));
		this.labEtiqueta.setText(descripcion);

		this.labEtiqueta.setSize(25, 25);
		this.labEtiqueta.setEditable(false);
		add(this.labEtiqueta);
	}
}
