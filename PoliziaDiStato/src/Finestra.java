import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Finestra extends JFrame implements ActionListener{
	private JMenuBar bar;
	private JMenu file,data;
	private JMenuItem save,load,clear,add,edit,delete;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel defaultTableModel;
	private JThumbnail thumbnail;
	private CriminalList list;
	private void initComponents() {
		bar= new JMenuBar();
		file= new JMenu("File");
		data= new JMenu("Dati");
		save= new JMenuItem("Salva");
		load= new JMenuItem("Carica");
		clear= new JMenuItem("Elimina tutto");
		add= new JMenuItem("Aggiungi");
		edit= new JMenuItem("Modifica");
		delete= new JMenuItem("Elimina");
		
		Object[][] tableData = {};
		String[] columnNames = {"Nome","Cognome","Data di nascita","Reati commessi" };
		defaultTableModel = new DefaultTableModel(tableData, columnNames);
		table= new JTable(defaultTableModel);
		scrollPane= new JScrollPane(table);
	
		thumbnail= new JThumbnail("base.png","Seleziona un record",400,400);
		
		file.add(save);
		file.add(load);
		
		data.add(clear);
		data.add(add);
		data.add(edit);
		data.add(delete);
		
		bar.add(file);
		bar.add(data);
		
		add.addActionListener(this);
		edit.addActionListener(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints g= new GridBagConstraints();
		
		g.gridx=0;
		g.gridy=0;
		g.gridwidth=2;
		this.add(bar,g);
		
		g.gridx=0;
		g.gridy=1;
		g.gridwidth=1;
		this.add(getThumbnail(),g);
		
		g.gridx=1;
		g.gridy=1;
		g.gridwidth=1;
		this.add(scrollPane,g);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = table.getSelectedRow();
				if (index!=-1) getThumbnail().setPath(list.get(index).getPicturePath());
				
			}
			
		});

		delete.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
	}
	private void criminalDialog(int criminalIndex) {
		CriminalDialog td = new CriminalDialog(this, true, this.list,criminalIndex);
		td.setSize(800, 600);
		td.setVisible(true);
	}
	public Finestra() {
		this.list= new CriminalList();
		initComponents();
		
	}
	public static void main(String[] args) {
		Finestra f= new Finestra();
		f.setSize(1000,900);
		f.setVisible(true);
	}
	public void updateTable() {
		defaultTableModel.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			Calendar c= list.get(i).getBirthDate();
			String date=String.valueOf(c.get(Calendar.DAY_OF_MONTH))+"/"+String.valueOf(c.get(Calendar.MONTH))+"/"+String.valueOf(c.get(Calendar.YEAR));
			Object[] row = {list.get(i).getName(),list.get(i).getSurname(),date,list.get(i).getCrimes()};
			defaultTableModel.addRow(row);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(add)) {
			this.criminalDialog(-1);
		}
		else if(e.getSource().equals(clear)) {
			this.list.clear();
			this.updateTable();
			this.getThumbnail().setPath("base.png");
		}
		else if(e.getSource().equals(edit)) {
			this.criminalDialog(table.getSelectedRow());
		}
		else if(e.getSource().equals(load)) {
			JFileChooser jfc = new JFileChooser();
			//jfc.setFileFilter();
			//jfc.setAcceptAllFileFilterUsed(false);
			int rv= jfc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
		        File file = jfc.getSelectedFile();
		        try {
					list= CriminalList.load(file);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        this.updateTable();
				this.getThumbnail().setPath("base.png");
			}
		}
		else if(e.getSource().equals(save)) {
			JFileChooser jfc = new JFileChooser();
			//jfc.setFileFilter();
			//jfc.setAcceptAllFileFilterUsed(false);
			int rv= jfc.showSaveDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
		        File file = jfc.getSelectedFile();
		        try {
					list.save(file);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        this.updateTable();
				this.getThumbnail().setPath("base.png");
			}
		}
		else if(e.getSource().equals(delete)) {
			int i = table.getSelectedRow();
			if(i!=-1) {
				this.list.remove(i);
				this.updateTable();
				this.getThumbnail().setPath("base.png");
			}
		}
		
	}
	public JThumbnail getThumbnail() {
		return thumbnail;
	}
}
