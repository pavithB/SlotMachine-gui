package slotMachineGUI_CW;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Symbol implements ISymbol {

	// this symbol clz implement the al the abstract methos in isymbol clz
	// to store a image path
	private String image;
	// to store value of a image
	private int value;

	// to use the image path
	public String getImage() {

		return image;
	}

	// to set the image path
	public void setImage(String imgName) {

		// image = createImageIcon("images/Watermelon.png", "Watermelon");
		// this.image = image;
		image = "images/" + imgName + ".png";

	}

	// to use the value of a image
	public int getValue() {
		return value;
	}

	/// to set a value of a image
	public void setValue(int value) {
		this.value = value;
	}

}
