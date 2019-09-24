package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JPanel paintPane;
	private JPanel infoPane;
	private JPanel magPane;
	private JButton paintButton;
	private Pixel paintPix;
	private Pixel p;
	private Picture m;
	private Pixel[][] mArray;
	private PictureView magPic;
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");

		setLayout(new GridLayout(1, 3));
		
		magPane = new JPanel();
		magPane.setLayout(new BorderLayout());
		magPic = new PictureView(Picture.createSolidPicture(100, 100, Pixel.WHITE, true).createObservable());
		add(magPic);
		
		infoPane = new JPanel();
		infoPane.setLayout(new GridLayout(3, 1));
		infoPane.add(x_label);
		infoPane.add(y_label);
		infoPane.add(pixel_info);
		add(infoPane);
		
		paintPane = new JPanel();
		paintPane.setLayout(new BorderLayout());
		
		paintButton = new JButton();
		paintButton.setText("Paint");
		paintButton.addActionListener(this);
		paintPane.add(paintButton);
		add(paintPane);
		
		p = null;
		paintPix = null;
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
		this.p = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (p == null) {
			
		} else {
			paintPix = p;
		}		
	}
	
	public Pixel getPaintPixel() {
		return paintPix;
	}

	public void magnify(int x, int y, Picture pic) {
		ObservablePicture mag = magPic.getPicture();
		mArray = new Pixel[100][100];
		
		m = pic.extract(x - 25, y - 25, 50, 50);
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				mArray[i*2][j*2] = m.getPixel(i, j);
				mArray[i*2 + 1][j*2] = m.getPixel(i, j);
				mArray[i*2][j*2 + 1] = m.getPixel(i, j);
				mArray[i*2 + 1][j*2 + 1] = m.getPixel(i, j);
			}
		}
		Picture magnify = new MutablePixelArrayPicture(mArray, "Magnify");
		mag = new ObservablePictureImpl(magnify);
		magPic.setPicture(mag);
	}

}
