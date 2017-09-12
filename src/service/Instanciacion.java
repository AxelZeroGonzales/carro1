package service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import entity.Automovil;
import entity.Carro;
import entity.PanelDescripcion;
import entity.TrackPanel;

public class Instanciacion extends JFrame {
	private JPanel jContentPane;
	private JPanel jPanel;
	private JPanel controlPnl;
	private JButton classBtn;
	private JLabel stateLbl;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton stepBtn;
	private TrackPanel trackPanel;
	private JScrollPane jScrollPane;
	private JTable jTable;
	private JPanel jPanel3;
	private JScrollPane jScrollPane1;
	private JEditorPane jEditorPane;
	private JFrame sourceFrame;
	private JEditorPane sourceCode;
	private JPanel panelDescricion;
	private ArrayList<String> nombresInstancias;

	public Instanciacion() {
		init();
	}

	public static void main(String[] args) {
		Instanciacion interfazPrincipal = new Instanciacion();
		interfazPrincipal.setVisible(true);
	
	}

	public void init() {
		setSize(749, 623);
		setPreferredSize(new Dimension(749, 623));
		setDefaultCloseOperation(3);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Entrenador instanciación carros");
		this.nombresInstancias = new ArrayList();
		setLayout(new BorderLayout());

		add(getJPanel1(), "West");
		add(getJPanel3(), "Center");

		JPanel panelSur = new JPanel();
		panelSur.setLayout(new BorderLayout());

		panelSur.add(getPanelDescripcion(), "Center");
		panelSur.add(getJPanel(), "South");

		add(panelSur, "South");
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.setBorder(BorderFactory.createEtchedBorder(0));
			this.jContentPane.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 8;
			gbc.fill = 1;
			this.jContentPane.add(getJPanel1(), gbc);

			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = 1;
			gbc.gridheight = 8;
			this.jContentPane.add(getJPanel3(), gbc);

			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 8;
			gbc.fill = 1;
			gbc.gridwidth = 2;
			this.jContentPane.add(getPanelDescripcion(), gbc);

			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 9;
			gbc.fill = 1;
			gbc.gridwidth = 2;
			this.jContentPane.add(getJPanel(), gbc);
		}
		return this.jContentPane;
	}

	public JPanel getPanelDescripcion() {
		if (this.panelDescricion == null) {
			String descripcion = "Bienvenido al entrenador para la instanciación de automóviles. Para crear un nuevo carro haga click sobre el diagrama de la clase Automóvil. Si desea conocer la información de los atributos de un auto haga click sobre el carro respectivo. Si desea modificar el valor de alguno de los atributos de un carro (gasolina, color o velocidad) haga click derecho  sobre él. Si desea ver la declaración de la clase Automóvil haga click derecho sobre el diagrama de clases. El carro que aparece en el recuadro azul es el que se encuentra seleccionado (y del cual se está mostrando la información de los atributos en la tabla).";

			this.panelDescricion = new PanelDescripcion(descripcion, "Instanciación Automóviles");
		}
		return this.panelDescricion;
	}

	public JPanel getJPanel() {
		if (this.jPanel == null) {
			this.stateLbl = new JLabel();
			FlowLayout flowLayout1 = new FlowLayout();

			this.jPanel = new JPanel();
			this.jPanel.setLayout(flowLayout1);
			this.jPanel.setPreferredSize(new Dimension(25, 25));
			this.jPanel.setBorder(BorderFactory.createBevelBorder(1));
			this.stateLbl.setPreferredSize(new Dimension(700, 16));
			this.stateLbl.setFont(new Font("Dialog", 0, 12));
			flowLayout1.setAlignment(0);
			flowLayout1.setVgap(2);
			this.jPanel.add(this.stateLbl, null);
		}
		return this.jPanel;
	}

	private JPanel getJPanel1() {
		if (this.controlPnl == null) {
			this.controlPnl = new JPanel();
			this.controlPnl.setLayout(new BorderLayout());
			this.controlPnl.setPreferredSize(new Dimension(170, 10));
			this.controlPnl.setBackground(Color.white);
			this.controlPnl.add(getJButton(), "Center");
			this.controlPnl.add(getJScrollPane(), "South");
		}
		return this.controlPnl;
	}

	private JButton getJButton() {
		if (this.classBtn == null) {
			this.classBtn = new JButton();
			this.classBtn.setPreferredSize(new Dimension(170, 206));
			this.classBtn.setBackground(Color.white);
			this.classBtn.setBorder(null);
			this.classBtn.setBorderPainted(false);
			this.classBtn.setOpaque(false);
			this.classBtn.setContentAreaFilled(false);
			this.classBtn.setIcon(new ImageIcon(getClass().getResource("/image/class.gif")));

			this.classBtn.addMouseListener(new MouseAdapter() {
				public void mouseExited(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("");
				}

				public void mouseEntered(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("Click para crear una nueva instancia");
				}

				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == 1) {
						Instanciacion.this.createInstance();
					} else if (e.isPopupTrigger()) {
						Instanciacion.this.showCode();
					}
				}
			});
		}
		return this.classBtn;
	}

	protected void showCode() {
		getSourceFrame().setVisible(true);
	}

	private JPanel getJPanel12() {
		if (this.jPanel1 == null) {
			this.jPanel1 = new JPanel();
			this.jPanel1.setLayout(new BorderLayout());
			this.jPanel1.add(getJPanel2(), "South");
			this.jPanel1.add(getTrackPanel(), "Center");
		}
		return this.jPanel1;
	}

	private JPanel getJPanel2() {
		if (this.jPanel2 == null) {
			FlowLayout flowLayout12 = new FlowLayout();

			this.jPanel2 = new JPanel();
			this.jPanel2.setLayout(flowLayout12);
			this.jPanel2.setPreferredSize(new Dimension(300, 25));
			flowLayout12.setVgap(2);
			this.jPanel2.add(getPlayBtn(), null);
			this.jPanel2.add(getPauseBtn(), null);
			this.jPanel2.add(getStepBtn(), null);
			this.jPanel2.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("Controles de la Simulación: Play, Pause, Step");
				}

				public void mouseExited(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("");
				}
			});
		}
		return this.jPanel2;
	}

	private JButton getPlayBtn() {
		if (this.playBtn == null) {
			this.playBtn = new JButton();
			this.playBtn.setText("");
			this.playBtn
					.setIcon(new ImageIcon(getClass().getResource("/image/play.gif")));

			this.playBtn.setToolTipText("Play");
			this.playBtn.setPreferredSize(new Dimension(54, 20));
			this.playBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Instanciacion.this.getTrackPanel().play();
					Instanciacion.this.getPlayBtn().setEnabled(false);
					Instanciacion.this.getPauseBtn().setEnabled(true);
					Instanciacion.this.getStepBtn().setEnabled(false);
				}
			});
		}
		return this.playBtn;
	}

	private JButton getPauseBtn() {
		if (this.pauseBtn == null) {
			this.pauseBtn = new JButton();
			this.pauseBtn.setText("");
			this.pauseBtn.setEnabled(false);
			this.pauseBtn
					.setIcon(new ImageIcon(getClass().getResource("/image/pause.gif")));

			this.pauseBtn.setToolTipText("Pause");
			this.pauseBtn.setPreferredSize(new Dimension(54, 20));
			this.pauseBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Instanciacion.this.getTrackPanel().pause();
					Instanciacion.this.getPlayBtn().setEnabled(true);
					Instanciacion.this.getPauseBtn().setEnabled(false);
					Instanciacion.this.getStepBtn().setEnabled(true);
				}
			});
		}
		return this.pauseBtn;
	}

	private JButton getStepBtn() {
		if (this.stepBtn == null) {
			this.stepBtn = new JButton();
			this.stepBtn.setText("");
			this.stepBtn.setEnabled(true);
			this.stepBtn
					.setIcon(new ImageIcon(getClass().getResource("/image/step.gif")));

			this.stepBtn.setToolTipText("Step");
			this.stepBtn.setPreferredSize(new Dimension(54, 20));
			this.stepBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Instanciacion.this.getTrackPanel().step();
				}
			});
		}
		return this.stepBtn;
	}

	private TrackPanel getTrackPanel() {
		if (this.trackPanel == null) {
			this.trackPanel = new TrackPanel(this);
			this.trackPanel.setPreferredSize(new Dimension(550, 500));
			this.trackPanel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("instancias creadas");
				}

				public void mouseExited(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("");
				}
			});
			this.trackPanel.setBackground(new Color(102, 102, 102));
		}
		return this.trackPanel;
	}

	private JScrollPane getJScrollPane() {
		if (this.jScrollPane == null) {
			this.jScrollPane = new JScrollPane();
			this.jScrollPane.setViewportView(getJTable());
			this.jScrollPane.setPreferredSize(new Dimension(100, 100));
		}
		return this.jScrollPane;
	}

	private JTable getJTable() {
		if (this.jTable == null) {
			this.jTable = new JTable();
			this.jTable.setModel(getTrackPanel());
			this.jTable.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("Atributos de la instancia seleccionada");
				}

				public void mouseExited(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("");
				}
			});
		}
		return this.jTable;
	}

	protected void createInstance() {
		JPanel params = new JPanel(new FlowLayout());

		JLabel l1 = new JLabel(" Automovil ");
		JLabel l2 = new JLabel(" = new Automovil(");
		JLabel l3 = new JLabel(");");

		JTextField iTxt = new JTextField(3);
		iTxt.setBorder(new LineBorder(Color.green, 1));
		iTxt.setToolTipText("nombre de la instancia");
		JTextField gTxt = new JTextField(2);
		gTxt.setBorder(new LineBorder(Color.green, 1));
		gTxt.setToolTipText("int gasolinaP");

		params.add(l1);
		params.add(iTxt);
		params.add(l2);
		params.add(gTxt);
		params.add(l3);
		params.setBackground(Color.WHITE);

		int respuesta = JOptionPane.showConfirmDialog(this, params, "Automovil(int gasolinaP)", 2);
		if (respuesta == 0) {
			int gasolina = 0;
			String iName = iTxt.getText();
			if (iName.indexOf(' ') != -1) {
				JOptionPane.showMessageDialog(this, "El Nombre de la instancia no puede tener espacios",
						"Error de creación de nueva instancia", 0);
				return;
			}
			if ((iName.equals("")) || (iName.equals(" ")) || (((iName.charAt(0) < 'A') || (iName.charAt(0) > 'Z'))
					&& ((iName.charAt(0) < 'a') || (iName.charAt(0) > 'z')))) {
				JOptionPane.showMessageDialog(this, "El Nombre de la instancia debe empezar por una letra",
						"Error de creación de nueva instancia", 0);
				return;
			}
			if (checkName(iName)) {
				JOptionPane.showMessageDialog(this, "Una instancia con el mismo nombre ya ha sido creada",
						"Error de creación de nueva instancia", 0);
				return;
			}
			char[] caracteres = iName.toCharArray();
			for (int cont = 0; cont < caracteres.length; cont++) {
				char caracter = caracteres[cont];
				if (((caracter >= 0) && (caracter <= '/')) || ((caracter >= ':') && (caracter <= '@'))
						|| ((caracter >= '[') && (caracter <= '`')) || ((caracter >= '{') && (caracter <= ''))) {
					JOptionPane.showMessageDialog(this, "El Nombre de la instancia no debe contener símbolos",
							"Error de creación de nueva instancia", 0);
					return;
				}
			}
			try {
				gasolina = Integer.parseInt(gTxt.getText());
				if (gasolina < 0) {
					JOptionPane.showMessageDialog(this, "Error en los parámetros del constructor",
							"Error de creación de nueva instancia", 0);
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Error en los parámetros del constructor",
						"Error de creación de nueva instancia", 0);
				return;
			}
			Automovil auto = new Automovil(gasolina);
			Carro c = new Carro(auto);

			c.setIntanceName(iName);
			this.nombresInstancias.add(iName);
			try {
				getTrackPanel().addCar(c);
				getTrackPanel().repaint();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error de creación de nueva instancia", 0);
			}
		}
	}

	private boolean checkName(String name) {
		for (Iterator iter = this.nombresInstancias.iterator(); iter.hasNext();) {
			String nombre = (String) iter.next();
			if (nombre.equals(name)) {
				return true;
			}
		}
		return false;
	}

	private JPanel getJPanel3() {
		if (this.jPanel3 == null) {
			this.jPanel3 = new JPanel();
			this.jPanel3.setLayout(new BorderLayout());
			this.jPanel3.add(getJPanel12(), "Center");
			this.jPanel3.add(getJScrollPane1(), "South");
		}
		return this.jPanel3;
	}

	private JScrollPane getJScrollPane1() {
		if (this.jScrollPane1 == null) {
			this.jScrollPane1 = new JScrollPane();
			this.jScrollPane1.setPreferredSize(new Dimension(600, 55));
			this.jScrollPane1.setViewportView(getJEditorPane());
		}
		return this.jScrollPane1;
	}

	public JEditorPane getJEditorPane() {
		if (this.jEditorPane == null) {
			this.jEditorPane = new JEditorPane();
			this.jEditorPane.setEditable(false);
			this.jEditorPane.setContentType("text/html");
			this.jEditorPane.setPreferredSize(new Dimension(2, 2));
			this.jEditorPane.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("CÃ³digo Fuente");
				}

				public void mouseExited(MouseEvent e) {
					Instanciacion.this.stateLbl.setText("");
				}
			});
		}
		return this.jEditorPane;
	}

	public JFrame getSourceFrame() {
		if (this.sourceFrame == null) {
			this.sourceFrame = new JFrame();
			JScrollPane sc = new JScrollPane();
			sc.setViewportView(getSourceCode());
			this.sourceFrame.getContentPane().add(sc);
			this.sourceFrame.setSize(120, 240);
		}
		return this.sourceFrame;
	}

	public void setSourceFrame(JFrame sourceFrameP) {
		this.sourceFrame = sourceFrameP;
	}

	public JEditorPane getSourceCode() {
		if (this.sourceCode == null) {
			this.sourceCode = new JEditorPane();
			this.sourceCode.setContentType("text/html");
			this.sourceCode.setEditable(false);
			try {
				this.sourceCode
						.setPage(getClass().getResource("/image/Automovil.java.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.sourceCode;
	}
}
