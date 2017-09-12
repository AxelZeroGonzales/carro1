package entity;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import service.Instanciacion;

public class TrackPanel extends JPanel implements MouseListener, TableModel, ActionListener {
	private static final String SET_VEL = "asignarVelocidad";
	private static final String SET_COLOR = "asignarColor";
	private static final String SET_GASOLINA = "asignarGasolina";
	private static int distanciaPista = 450;
	private Image imagen;
	private StringBuffer text;
	private Instanciacion callback;
	private ArrayList listeners;
	private Carro[] pistas = new Carro[4];
	private int pistasOcupadas;
	private Timer clock;
	private long tickTime;
	private Carro selected;
	private JPopupMenu carroMnu;

	public TrackPanel(Instanciacion i) {
		this.text = new StringBuffer();
		this.listeners = new ArrayList();
		this.pistasOcupadas = 0;
		this.tickTime = 100L;
		this.clock = new Timer();
		this.callback = i;

		addMouseListener(this);

		this.carroMnu = new JPopupMenu("Métodos de la clase Auto");

		JMenuItem color = new JMenuItem(SET_COLOR);
		color.addActionListener(this);
		color.setActionCommand("COLOR");
		this.carroMnu.add(color);

		JMenuItem velocidad = new JMenuItem(SET_VEL);
		velocidad.addActionListener(this);
		velocidad.setActionCommand("VELOCIDAD");
		this.carroMnu.add(velocidad);

		JMenuItem gasolina = new JMenuItem(SET_GASOLINA);
		gasolina.addActionListener(this);
		gasolina.setActionCommand("GASOLINA");
		this.carroMnu.add(gasolina);

		loadImage();
	}

	private void loadImage() {
		MediaTracker mt = new MediaTracker(new JLabel());

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		this.imagen = defaultToolkit.getImage(getClass().getResource("/image/track3.gif"));

		mt.addImage(this.imagen, 1);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void changeSelectedGasolina() {
		if (this.selected == null) {
			return;
		}
		JPanel params = new JPanel(new FlowLayout());

		JLabel l1 = new JLabel(this.selected.getIntanceName() + "." + "asignarGasolina" + "(");
		JLabel l2 = new JLabel(" ); ");

		JTextField vTxt = new JTextField(3);
		vTxt.setBorder(new LineBorder(Color.green, 1));
		vTxt.setToolTipText("int gasolinaP");

		params.add(l1);
		params.add(vTxt);
		params.add(l2);
		params.setBackground(Color.WHITE);

		int respuesta = JOptionPane.showConfirmDialog(this, params, "asignarGasolina(int gasolinaP)", 2);
		if (respuesta == 0) {
			int gas = 0;
			try {
				gas = Integer.parseInt(vTxt.getText());
				if (gas < 0) {
					JOptionPane.showMessageDialog(this, "Error en los parámetros de la invocación del método",
							"Error de creación de nueva instancia", 0);
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Error en los parámetros de la invocación del método",
						"Error de creación de nueva instancia", 0);
				return;
			}
			Automovil auxiliar = this.selected.darAutomovil();
			auxiliar.asignarGasolina(gas);
			this.selected.actualizarCarro(auxiliar);

			updateTable();
			repaint();

			this.text.append("<br/>");
			this.text.append(this.selected.getIntanceName());
			this.text.append(".");
			this.text.append("asignarGasolina");
			this.text.append("(");
			this.text.append(gas);
			this.text.append(");");

			updateSourceCode();
		}
	}

	protected void changeSelectedVelocidad() {
		if (this.selected == null) {
			return;
		}
		JPanel params = new JPanel(new FlowLayout());

		JLabel l1 = new JLabel(this.selected.getIntanceName() + "." + "asignarVelocidad" + "(");
		JLabel l2 = new JLabel(" ); ");

		JTextField vTxt = new JTextField(3);
		vTxt.setBorder(new LineBorder(Color.green, 1));
		vTxt.setToolTipText("int velocidadP");

		params.add(l1);
		params.add(vTxt);
		params.add(l2);
		params.setBackground(Color.WHITE);

		int respuesta = JOptionPane.showConfirmDialog(this, params, "asignarVelocidad(int velocidadP)", 2);
		if (respuesta == 0) {
			int vel = 0;
			try {
				vel = Integer.parseInt(vTxt.getText());
				if (vel < 0) {
					JOptionPane.showMessageDialog(this, "Error en los parámetros de la invocación del método",
							"Error de creación de nueva instancia", 0);
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Error en los parámetros de la invocación del método",
						"Error de creación de nueva instancia", 0);
				return;
			}
			Automovil auxiliar = this.selected.darAutomovil();
			auxiliar.asignarVelocidad(vel);
			this.selected.actualizarCarro(auxiliar);

			updateTable();
			repaint();

			this.text.append("<br/>");
			this.text.append(this.selected.getIntanceName());
			this.text.append(".");
			this.text.append("asignarVelocidad");
			this.text.append("(");
			this.text.append(vel);
			this.text.append(");");

			updateSourceCode();
		}
	}

	protected void changeSelectedColor() {
		if (this.selected == null) {
			return;
		}
		JPanel params = new JPanel(new FlowLayout());
		JLabel l1 = new JLabel(this.selected.getIntanceName() + "." + "asignarColor" + "(");
		JLabel l2 = new JLabel(" , ");
		JLabel l3 = new JLabel(" , ");
		JLabel l4 = new JLabel(" ); ");

		JTextField rTxt = new JTextField(3);
		rTxt.setBorder(new LineBorder(Color.green, 1));
		rTxt.setToolTipText("int rP");
		JTextField gTxt = new JTextField(3);
		gTxt.setBorder(new LineBorder(Color.green, 1));
		gTxt.setToolTipText("int gP");
		JTextField bTxt = new JTextField(3);
		bTxt.setBorder(new LineBorder(Color.green, 1));
		bTxt.setToolTipText("int bP");

		params.add(l1);
		params.add(rTxt);
		params.add(l2);
		params.add(gTxt);
		params.add(l3);
		params.add(bTxt);
		params.add(l4);

		params.setBackground(Color.white);
		int b;
		int g;
		int r;
		int respuesta = JOptionPane.showConfirmDialog(this, params, "asignarColor(int rP, int gP, int bP)", 2);
		if (respuesta == 0) {
			try {
				 r = Integer.parseInt(rTxt.getText());
				 g = Integer.parseInt(gTxt.getText());
				 b = Integer.parseInt(bTxt.getText());
				if ((r < 0) || (g < 0) || (b < 0) || (r > 255) || (b > 255) || (b > 255)) {
					JOptionPane.showMessageDialog(this, "Error en los parámetros de la invocación del método",
							"Error de creación de nueva instancia", 0);
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Error en los parámetros de la invocación del método",
						"Error de creación de nueva instancia", 0);
				return;
			}
			Automovil auxiliar = this.selected.darAutomovil();
			auxiliar.asignarColor(r, g, b);
			this.selected.actualizarCarro(auxiliar);

			updateTable();
			repaint();

			this.text.append("<br/>");
			this.text.append(this.selected.getIntanceName());
			this.text.append(".");
			this.text.append("asignarColor");
			this.text.append("(");
			this.text.append(r);
			this.text.append(",");
			this.text.append(g);
			this.text.append(",");
			this.text.append(b);
			this.text.append(");");

			updateSourceCode();
		}
	}

	public void addCar(Carro c) throws Exception {
		if (this.pistasOcupadas >= this.pistas.length) {
			throw new Exception("No hay pistas libres");
		}
		this.pistas[this.pistasOcupadas] = c;
		this.pistas[this.pistasOcupadas].darAutomovil().asignarColor(0, 0, 255);
		this.pistas[this.pistasOcupadas].setPosicion(new Point(0, this.pistasOcupadas * 100));
		if (this.selected != null) {
			this.selected.setSelected(false);
		}
		c.setSelected(true);
		this.selected = c;
		this.pistasOcupadas += 1;

		this.text.append("<br/>Automovil&nbsp; ");
		this.text.append(c.getIntanceName());
		this.text.append("=&nbsp;");
		this.text.append("<font color=\"#7f0055\">&nbsp;<b>new</b>&nbsp;</font> Automovil&nbsp;(");
		this.text.append(c.darAutomovil().darGasolina());
		this.text.append(");");

		updateSourceCode();
		updateTable();
	}

	private void updateSourceCode() {
		this.callback.getJEditorPane().setText(this.text.toString());
	}

	public void pause() {
		this.clock.cancel();
	}

	public void play() {
		this.clock = new Timer();
		this.clock.schedule(new TickTask(), 0L, this.tickTime);
	}

	public void step() {
		tickCarros();
	}

	private void paintBackground(Graphics g) {
		g.drawImage(this.imagen, 0, 0, this);
	}

	protected void finalize() throws Throwable {
		this.clock.cancel();
		super.finalize();
	}

	protected void paintChildren(Graphics g) {
		super.paintChildren(g);

		paintBackground(g);

		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < this.pistas.length; i++) {
			Carro carro = this.pistas[i];
			if (carro != null) {
				carro.paint(g2);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < this.pistas.length; i++) {
			Carro carro = this.pistas[i];
			if ((carro != null) && (carro.hit(e.getPoint()))) {
				this.selected.setSelected(false);
				carro.setSelected(true);
				this.selected = carro;

				paintChildren(getGraphics());
				updateTable();
				if (e.isPopupTrigger()) {
					this.carroMnu.show(this, e.getX(), e.getY());
				}
				return;
			}
		}
		this.selected = null;
	}

	private class TickTask extends TimerTask {
		private TickTask() {
		}

		public void run() {
			TrackPanel.this.tickCarros();
		}
	}

	private void tickCarros() {
		for (int i = 0; i < this.pistas.length; i++) {
			Carro carro = this.pistas[i];
			if (carro != null) {
				carro.tick();
			}
		}
		updateTable();
		repaint();
	}

	public static int getDistanciaPista() {
		return distanciaPista;
	}

	public static void setDistanciaPista(int distanciaPistaP) {
		distanciaPista = distanciaPistaP;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return 4;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Class getColumnClass(int columnIndex) {
		return String.class;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return rowAttrib(rowIndex);
		}
		return rowValue(rowIndex);
	}

	private Object rowValue(int rowIndex) {
		String retVal = "";
		if (this.selected != null) {
			switch (rowIndex) {
			case 0:
				retVal = retVal + this.selected.darAutomovil().darGasolina();
				break;
			case 1:
				retVal = retVal + this.selected.darAutomovil().darVelocidad();
				break;
			case 2:
				retVal = retVal + "(" + this.selected.darAutomovil().darColor().getRed() + ","
						+ this.selected.darAutomovil().darColor().getGreen() + ","
						+ this.selected.darAutomovil().darColor().getBlue() + ")";
				break;
			case 3:
				retVal = retVal + (int) this.selected.getPosicion().getX() + ","
						+ (int) this.selected.getPosicion().getY();
			}
		}
		return retVal;
	}

	private Object rowAttrib(int rowIndex) {
		String retVal = "";
		switch (rowIndex) {
		case 0:
			retVal = "gasolina";
			break;
		case 1:
			retVal = "velocidad";
			break;
		case 2:
			retVal = "color";
			break;
		case 3:
			retVal = "posicion";
		}
		return retVal;
	}

	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Atributo";
		}
		return "Valor";
	}

	public void addTableModelListener(TableModelListener l) {
		this.listeners.add(l);
	}

	public void removeTableModelListener(TableModelListener l) {
		this.listeners.remove(l);
	}

	public void updateTable() {
		for (Iterator iter = this.listeners.iterator(); iter.hasNext();) {
			TableModelListener listener = (TableModelListener) iter.next();
			listener.tableChanged(new TableModelEvent(this));
		}
	}

	public void actionPerformed(ActionEvent evento) {
		String command = evento.getActionCommand();
		if (command == "VELOCIDAD") {
			changeSelectedVelocidad();
		} else if (command == "COLOR") {
			changeSelectedColor();
		} else if (command == "GASOLINA") {
			changeSelectedGasolina();
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}
}
