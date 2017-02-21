package slotMachineGUI_CW;

import java.awt.Image;

import javax.swing.ImageIcon;

public interface ISymbol {
	// this is the interface for symbol clz

	public void setImage(String imgName);

	public String getImage();

	public void setValue(int value);

	public int getValue();

}
