import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CriminalList extends ArrayList<Criminal> implements Serializable{
	public CriminalList() {
		super();
	}
	public void save(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}
	public static CriminalList load(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fos = new FileInputStream(f);
		ObjectInputStream oos = new ObjectInputStream(fos);
		return (CriminalList) oos.readObject();
	}
}
