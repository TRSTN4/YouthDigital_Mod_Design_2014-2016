/* *******************************************
 * *******************************************
 * 
 * 			| TS CODE - DO NOT TOUCH |
 * 
 * *******************************************
 * *******************************************	*/

package mytroublemod.tsconfig;

import net.minecraft.world.World;

public class TSConfig {

 	public static String dS = "The Secret Code is: §b";
 	public static String fWC = "";
    public static float [] nC = {45.3F, 34.4F, 4.2F, 11.2F, 29.4F, 19.6F, 
    15.4F, 3.4F, 356.4F, 234.2F, 12.4F, 53.3F, 3.2F};
    public static float m = 1.4F;

    
    public static void cNtW (float[] cA, World world) {
	    	if (!world.isRemote) {
		    	String wC = "";
		    	String nL;
		    	for (int i = 2; i < cA.length - 6; i++) 
		    	{	
		    		nL = gCfMN(nC[i]);
		    		wC += nL;
		    	}
	    	fWC = wC;
	    	wC = "";
	    	}
    }

	private static String gCfMN(float i) {
	    return String.valueOf((char)((i/m + 'A' - 1)));
	}
	


}
