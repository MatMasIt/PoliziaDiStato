import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JThumbnail extends JLabel {
	private ImageIcon i;
	private String path, description;
	private int w,h;

	public JThumbnail(String path, String description, int w, int h) {
		this.i = new ImageIcon(path, description);
		this.path = path;
		this.description = description;
		this.w=w;
		this.h=h;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		ImageIcon i2 = new ImageIcon(path, this.description);
		this.i = i2;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		ImageIcon i2 = new ImageIcon(this.path, description);
		this.i = i2;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
		int nw = icon.getIconWidth();
		int nh = icon.getIconHeight();

		if (icon.getIconWidth() > w) {
			nw = w;
			nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (nh > h) {
			nh = h;
			nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
	}

}
