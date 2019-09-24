package a8;

import java.util.Stack;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;
	private Stack<Picture> undoList;

	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy(true).createObservable();
		undoList = new Stack<Picture>();
		undoList.add(original);
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size, boolean circle, double opacity) {
		if (circle) {
			current.paint(x, y, (double) brush_size, brushColor, opacity);
		} else {
			current.paint(x-brush_size+1, y-brush_size+1, x+brush_size-1, y+brush_size-1, brushColor, opacity);
		}
	}

	public void blurAt(int x, int y, int brush_size, boolean circle, int blur) {
		if (!circle) {
			for(int i = x - brush_size/2; i < x + brush_size/2; i++) {
				for(int j = y - brush_size/2; j < y + brush_size/2; j++) {
					//blur
					current.paint(i, j, updateBlurPix(blur, i, j));
				}
			}
		} else {
			for(int i = x - brush_size/2; i < x + brush_size/2; i++) {
				for(int j = y - brush_size/2; j < y + brush_size/2; j++) {
					//blur
					if(Math.sqrt((x-i)*(x-i)+(y-j)*(y-j)) <= brush_size/2) {
					current.paint(i, j, updateBlurPix(blur, i, j));
					}
				}
			}
		}
	}

	public void changePic(Picture newPic) {
		original = newPic;
		current = original.copy(true).createObservable();
		undoList = new Stack<Picture>();
		undoList.add(original);
	}

	public void changeCurrent() {
		current = undoList.pop().createObservable();
	}

	public void addUndo() {
		undoList.add(current.copy(true));
	}
	public ObservablePicture peekUndo() {
		return undoList.peek().createObservable();
	}
	public int getSize() {
		return undoList.size();
	}

	public ColorPixel updateBlurPix(int offset, int x, int y) {
		int left = 0;
		int right = 0;
		int top = 0;
		int bottom = 0;
		double sumOfPix = 0;
		double redSum = 0;
		double greenSum = 0;
		double blueSum = 0;

		if(x - offset < 0) {
			left = 0;
		} else {
			left = x - offset;
		}

		if(x + offset > current.getWidth() - 1) {
			right = current.getWidth() - 1;
		} else {
			right = x + offset;
		}

		if(y - offset < 0) {
			top = 0;
		} else {
			top = y - offset;
		}

		if(y + offset > current.getHeight() - 1) {
			bottom = current.getHeight() - 1;
		} else {
			bottom = y + offset;
		}

		for(int i = left; i <= right; i++) {
			for(int j = top; j <= bottom; j++) {
				redSum += current.getPixel(i, j).getRed();
				greenSum += current.getPixel(i, j).getGreen();
				blueSum += current.getPixel(i, j).getBlue();
				sumOfPix++;
			}
		}

		ColorPixel newPix = new ColorPixel(redSum/sumOfPix, greenSum/sumOfPix, blueSum/sumOfPix);
		return newPix;
	}
}
