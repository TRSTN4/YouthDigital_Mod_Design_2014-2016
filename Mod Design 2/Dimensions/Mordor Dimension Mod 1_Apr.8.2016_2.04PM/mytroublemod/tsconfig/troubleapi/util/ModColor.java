package mytroublemod.tsconfig.troubleapi.util;

public class ModColor {

	/**
	 * Convert a 3 integer color value to a single integer one for minecrafts needs
	 * 
	 * @param red ranging between 0-255
	 * @param green ranging between 0-255
	 * @param blue ranging between 0-255
	 * @return
	 */
	public static int rgbToInt(int red, int green, int blue) {
    	return ((red & 0xFF) << 16) + ((green & 0xFF) << 8) + (blue & 0xFF);
	}

}
