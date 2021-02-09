import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CriminalDialog extends JDialog implements ActionListener{
	private String path="base.png";
	private JLabel name,surname,d,m,y,crimes;
	private JTextField nameField,surnameField,dField,mField,yField;
	private JTextArea crimesArea;
	private JButton save,cancel,setImage;
	private CriminalList list;
	private int criminalIndex;
	private Finestra parentFrame;
	private void initComponents() {
		name= new JLabel("Nome");
		surname= new JLabel("Cognome");
		d= new JLabel("Giorno di nascita");
		m= new JLabel("Mese di Nascita");
		y= new JLabel("Anno di nascita");
		crimes= new JLabel("Crimini");
		
		nameField= new JTextField(15);
		surnameField= new JTextField(15);
		dField= new JTextField(15);
		mField= new JTextField(15);
		yField= new JTextField(15);
		crimesArea= new JTextArea();
		
		setImage= new JButton("Imposta immagine");
		save = new JButton("Salva");
		cancel= new JButton("Annulla");
		
		this.setLayout(new GridLayout(6,3));
		
		this.add(name);
		this.add(surname);
		this.add(d);
		this.add(nameField);
		this.add(surnameField);
		this.add(dField);
		this.add(m);
		this.add(y);
		this.add(crimes);
		
		this.add(mField);
		this.add(yField);
		this.add(crimesArea);
		
		this.add(setImage);
		this.add(save);
		this.add(cancel);
		
		setImage.addActionListener(this);
		cancel.addActionListener(this);
		save.addActionListener(this);
	}
	public CriminalDialog(Finestra f, boolean modal,CriminalList list,int criminalIndex) {
		super(f, modal);
		parentFrame=f;
		this.list=list;
		initComponents();
		this.criminalIndex=criminalIndex;
		if(criminalIndex!=(-1)) {
			Criminal temp= list.get(criminalIndex);
			nameField.setText(temp.getName());
			surnameField.setText(temp.getSurname());
			Calendar c= temp.getBirthDate();
			dField.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
			mField.setText(String.valueOf(c.get(Calendar.MONTH)));
			yField.setText(String.valueOf(c.get(Calendar.YEAR)));
			crimesArea.setText(temp.getCrimes());
			this.path=temp.getPicturePath();
			if(this.path!=null && this.path!="base.png") {
				this.setImage.setText("Cambia Immagine");
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(setImage)) {
			JFileChooser jfc = new JFileChooser();
			//jfc.setFileFilter();
			//jfc.setAcceptAllFileFilterUsed(false);
			int rv= jfc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
		        File file = jfc.getSelectedFile();
		        path=file.getAbsolutePath();
				this.setImage.setText("Cambia Immagine");
			}
		}
		else if(e.getSource().equals(cancel)) {
			this.dispose();
		}
		else if(e.getSource().equals(save)) {
			if(path==null) path="base.png";
			if(this.criminalIndex!=-1) {
				list.set(this.criminalIndex, new Criminal(nameField.getText(), surnameField.getText(), new GregorianCalendar(Integer.parseInt(yField.getText()),Integer.parseInt(dField.getText()),Integer.parseInt(mField.getText())), crimesArea.getText(), path));
			}
			else {
				list.add(new Criminal(nameField.getText(), surnameField.getText(), new GregorianCalendar(Integer.parseInt(yField.getText()),Integer.parseInt(dField.getText()),Integer.parseInt(mField.getText())), crimesArea.getText(), path));
			}
	        parentFrame.updateTable();
	        parentFrame.getThumbnail().setPath(path);
	        this.dispose();
		}
		
	}
	
}
