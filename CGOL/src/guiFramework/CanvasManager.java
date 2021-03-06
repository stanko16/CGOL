package guiFramework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasManager extends JPanel {

	private BufferedImage canvas;

	/**
	 * The canvas is created with the specified width and height
	 * @param width
	 * @param height
	 */
	public CanvasManager(int width, int height) {
		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * This method fills the zone corresponding to the cell with the desired color
	 * @param x x-value of the cell/point
	 * @param y y-value of the cell/point
	 * @param c The decided color
	 */
	public void fill(int x, int y, Color c) {
		try{
			x=x*10;
			y=y*10;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
				canvas.setRGB(x+i, y+j, c.getRGB());
				}
			}
		}catch(Exception e){}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(canvas, null, null);
	}

}