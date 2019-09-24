package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javafx.beans.value.ObservableValue;

public class BlurUI extends JPanel implements ActionListener {
	
	private JButton brushShape;
	private JSlider size_slider;
	private JSlider blur_slider;
	private boolean circle;
	
	public BlurUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		brushShape = new JButton();
		brushShape.setText("Chane to Circle");
		brushShape.addActionListener(this);
		
		JPanel size_slider_panel = new JPanel();
		JLabel size_label = new JLabel("Brush Size: ");
		size_slider_panel.setLayout(new BorderLayout());
		size_slider_panel.add(size_label, BorderLayout.WEST);
		size_slider = new JSlider(1,50);
		size_slider_panel.add(size_slider, BorderLayout.CENTER);

		JPanel blur_slider_panel = new JPanel();
		JLabel blur_label = new JLabel("Blur: ");
		blur_slider_panel.setLayout(new BorderLayout());
		blur_slider_panel.add(blur_label, BorderLayout.WEST);
		blur_slider = new JSlider(0,10);
		blur_slider_panel.add(blur_slider, BorderLayout.CENTER);
		
		slider_panel.add(brushShape);
		slider_panel.add(size_slider_panel);
		slider_panel.add(blur_slider_panel);
		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);
		add(color_chooser_panel);
		
		circle = false;
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
	
	public int getBrushSize() {
		return size_slider.getValue();
	}
	
	public int getBlur() {
		return blur_slider.getValue();
	}
	
	public boolean getCircle() {
		return circle;
	}
}
