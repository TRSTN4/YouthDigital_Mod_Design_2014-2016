/* *******************************************
 * *******************************************
 * 
 * 			| TS CODE - DO NOT TOUCH |
 * 
 * *******************************************
 * *******************************************	*/

package mytroublemod.tsconfig.troubleapi.tsconfig;

import net.minecraft.world.World;

public class TSConfig {

	public static boolean teacherMode = false;

 	public static String dS = "The Secret Code is: §b";
 	public static String fWC = "";
    public static float [] nC = {8, 156, 128, 180, 152, 144, 76, 156, 144, 88, 184, 196, 172, 140};
    public static float m = 4;

    public static void cNtW (float[] cA) {
    		int s = 0;
    		int e = 0;
	    	String wC = "";
	    	String nL;
	    	for (int i = s; i < nC.length+s+e; i++) 
	    	{	
	    		nL = gCfMN(cA[i]);
	    		wC += nL;
	    	}
	    	fWC = wC;
	    	wC = "";
	    	if (teacherMode) {
	    		fWC = "Secret";
	    	}
    }

	private static String gCfMN(float i) {
	    return String.valueOf((char)((i/m + 'A')));
	}
	


}
