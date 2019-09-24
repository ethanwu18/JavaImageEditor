package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageEditorView extends JPanel implements ActionListener {

	private JFrame main_frame;
	private PictureView frame_view;
	private ImageEditorModel model;
	private ToolChooserWidget chooser_widget;
	private JPanel tool_ui_panel;
	private JPanel tool_ui;
	private JButton undoButton;
	private JButton openButton;
	private JPanel openPanel;
	private JPanel topPanel;
	private JTextField openText;
	private Picture newPic;
	
	public ImageEditorView(JFrame main_frame, ImageEditorModel model) {
		this.main_frame = main_frame;
		this.model = model;
		setLayout(new BorderLayout());

		frame_view = new PictureView(model.getCurrent());		
		add(frame_view, BorderLayout.CENTER);

		tool_ui_panel = new JPanel();
		tool_ui_panel.setLayout(new BorderLayout());

		chooser_widget = new ToolChooserWidget();
		tool_ui_panel.add(chooser_widget, BorderLayout.NORTH);
		add(tool_ui_panel, BorderLayout.SOUTH);
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1));
		
		undoButton = new JButton();
		undoButton.setText("Undo");
		undoButton.addActionListener(this);
		topPanel.add(undoButton);

		openPanel = new JPanel();
		openPanel.setLayout(new BorderLayout());
		
		openText = new JTextField();
		openPanel.add(openText, BorderLayout.SOUTH);
		
		openButton = new JButton();
		openButton.setText("Open");
		openButton.addActionListener(this);
		
		openPanel.add(openButton);
		topPanel.add(openPanel);
		add(topPanel, BorderLayout.NORTH);
		tool_ui = null;
		newPic = null;
	}

	public void addToolChoiceListener(ToolChoiceListener l) {
		chooser_widget.addToolChoiceListener(l);
	}

	public String getCurrentToolName() {
		return chooser_widget.getCurrentToolName();
	}

	public void installToolUI(JPanel ui) {
		if (tool_ui != null) {
			tool_ui_panel.remove(tool_ui);
		}
		tool_ui = ui;
		tool_ui_panel.add(tool_ui, BorderLayout.CENTER);
		validate();
		main_frame.pack();
	}

	@Override
	public void addMouseMotionListener(MouseMotionListener l) {
		frame_view.addMouseMotionListener(l);
	}

	@Override
	public void removeMouseMotionListener(MouseMotionListener l) {
		frame_view.removeMouseMotionListener(l);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		frame_view.addMouseListener(l);
	}

	public void removeMouseListener(MouseListener l) {
		frame_view.removeMouseListener(l);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == undoButton) {	
			if(model.getSize() == 1) {
				return;
			} else {
				model.changeCurrent();
				frame_view.setPicture(model.getCurrent());
			}
		}
		if(e.getSource() == openButton) {
			try {
				Picture f = Picture.readFromURL(openText.getText());
				newPic = f;
				model.changePic(newPic);
				frame_view.setPicture(model.getCurrent());
			} catch (IOException e1) {
				
			}
		}
	}
}
