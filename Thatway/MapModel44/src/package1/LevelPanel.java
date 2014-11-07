package package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class LevelPanel extends JPanel 
{
	private int tileX;// width of a tile in terms of pixels
	private int tileY;// height of a tile in terms of pixels

	private int levelWidth;
	private int levelHeight;

	public LevelPanel(int width, int height) 
	{
		super();

		if (width != 0 && height != 0) 
		{
			tileX = Constants.LEVEL_PANE_WIDTH / width;
			tileY = Constants.LEVEL_PANE_HEIGHT / height;
			levelWidth = width;
			levelHeight = height;

			if (tileX == 0 || tileY == 0) 
			{
				System.out.println("Problem - given width or height was too big");
				System.err.println("Error - given width or height was too big");
			}
			
		} 
		else 
		{
			System.out.println("Problem - given width or height is 0");
			System.err.println("Error - given width or height is 0");
		}

	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.WHITE);
		g2.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2.setColor(Color.GRAY);

		// Draw vertical grid lines
		for (int i = 0; i < levelWidth; ++i) 
		{
			int column = i * tileX;
			g2.drawLine(column, 0, column, levelHeight * tileY);
		}

		// Draw horizontal grid lines
		for (int j = 0; j < levelHeight; ++j) 
		{
			int row = j * tileY;
			g2.drawLine(0, row, levelWidth * tileX, row);
		}

		// TODO draw ramps

		// TODO draw paths

		// TODO draw height difference

		// TODO insert images

		// System.out.println("Drawing loop");
	}

}
