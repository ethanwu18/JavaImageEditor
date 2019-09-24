package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener, ActionListener {

	private JButton brushShape;
	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private JSlider size_slider;
	private JSlider opaque_slider;
	private PictureView color_preview;
	private boolean circle;
	private double opaque;
	
	public PaintBrushToolUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		brushShape = new JButton();
		brushShape.setText("Change to Circle");
		brushShape.addActionListener(this);
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);

		JPanel size_slider_panel = new JPanel();
		JLabel size_label = new JLabel("Brush Size: ");
		size_slider_panel.setLayout(new BorderLayout());
		size_slider_panel.add(size_label, BorderLayout.WEST);
		size_slider = new JSlider(1,50);
		size_slider.addChangeListener(this);
		size_slider_panel.add(size_slider, BorderLayout.CENTER);
		
		JPanel opaque_slider_panel = new JPanel();
		JLabel opaque_label = new JLabel("Opacity: ");
		opaque_slider_panel.setLayout(new BorderLayout());
		opaque_slider_panel.add(opaque_label, BorderLayout.WEST);
		opaque_slider = new JSlider(0,100);
		opaque_slider.addChangeListener(this);
		opaque_slider_panel.add(opaque_slider, BorderLayout.CENTER);

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = size_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		green_label.setPreferredSize(d);
		opaque_label.setPreferredSize(d);
		
		slider_panel.add(brushShape);
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		slider_panel.add(size_slider_panel);
		slider_panel.add(opaque_slider_panel);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new PictureView(Picture.createSolidPicture(50, 50, Pixel.WHITE, true).createObservable());
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);
		
		circle = false;
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				                 green_slider.getValue()/100.0,
				                 blue_slider.getValue()/100.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.paint(0, 0, preview_frame.getWidth()-1, preview_frame.getHeight()-1, p);
		
	}
	
	public Pixel getBrushColor() {
		
		return color_preview.getPicture().getPixel(0,0);
	}
	
	public int getBrushSize() {
		return size_slider.getValue();
	}
	
	public void changePaintColor(Pixel p) {
		if (p == null) {
			return;
		}
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.paint(0, 0, preview_frame.getWidth()-1, preview_frame.getHeight()-1, p);
		red_slider.setValue((int) (p.getRed() * 100));
		blue_slider.setValue((int) (p.getBlue() * 100));
		green_slider.setValue((int) (p.getGreen() * 100));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (circle) {
			circle = false;
			brushShape.setText("Change to Circle");
		} else {
			circle = true;
			brushShape.setText("Change to Rectangle");
		}
	}
	
	public boolean getCircle() {
		return circle;
	}
	
	public double getOpacity() {
		return (double) (opaque_slider.getValue()) / 100.0;
	}
}
