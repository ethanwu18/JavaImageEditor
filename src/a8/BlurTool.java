package a8;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurTool implements Tool {

	private BlurUI ui;
	private ImageEditorModel model;
	
	public BlurTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurUI();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!model.getCurrent().equals(model.peekUndo())) {
			model.addUndo();
		}
		model.blurAt(e.getX(), e.getY(), ui.getBrushSize(), ui.getCircle(), ui.getBlur());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBrushSize(), ui.getCircle(), ui.getBlur());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Blur";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}
}
