/* *******************************************
 * *******************************************
 * 
 * 			| TS CODE - DO NOT TOUCH |
 * 
 * *******************************************
 * *******************************************	*/

package mytroublemod.tsconfig.troubleapi.tsconfig;

import java.util.EnumSet;
import java.util.Map;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.TickType;
import mytroublemod.tsconfig.troubleapi.ModAPI;
import mytroublemod.tsconfig.troubleapi.Structure;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorldAccess;


public class TroubleEvent implements IWorldAccess, ITickHandler
{
	 
	public boolean ts1 = false;
	public boolean ts2 = false;
	public boolean ts3 = false;
	public boolean ts4 = false;
	public boolean ts5 = false;
	
	private boolean troubleComplete = false;
	
	private boolean hookWorldAccessAdded = false;
	private int hookDelay = 0;
	
	private static TroubleEvent instance = null;
	
	private static boolean messageShown = false;
	
	public static TroubleEvent instance() {
		if (instance == null) {
			instance = new TroubleEvent();
		}
		return instance;
	}
	
	public void checkWorldGenRuns() {
		ts1 = true;
		checkTroubleComplete();
	}
	
	public void checkGravity(float gravity) {
		if (gravity < 0.5F) {
			ts2 = true;
		}
		checkTroubleComplete();
	}
	
	public void checkStarBrightnessRuns() {
		ts3 = true;
		checkTroubleComplete();
	}
	
	public void checkSunsetColor(int r, int g, int b) {
		if (r > g && r > b) {
			ts4 = true;
		}
		checkTroubleComplete();
	}
	
	public void checkMethodRun() {
		ts5 = true;
		checkTroubleComplete();
	}

	public void checkTroubleComplete() {
		if (ts1 && ts2 && ts3 && ts4 && ts5) {
			troubleComplete = true;
		}
		
		if (!messageShown && troubleComplete)
		{
			TSConfig.cNtW(TSConfig.nC);
			Minecraft.getMinecraft().thePlayer.addChatMessage(TSConfig.dS + TSConfig.fWC);
			messageShown = true;
		}
	}

	@Override
	public void markBlockForUpdate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockForRenderUpdate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockRangeForRenderUpdate(int i, int j, int k, int l,
			int i1, int j1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(String s, double d0, double d1, double d2, float f,
			float f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSoundToNearExcept(EntityPlayer entityplayer, String s,
			double d0, double d1, double d2, float f, float f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(String s, double d0, double d1, double d2,
			double d3, double d4, double d5) {
		
	}

	@Override
	public void onEntityCreate(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityDestroy(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playRecord(String s, int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcastSound(int i, int j, int k, int l, int i1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playAuxSFX(EntityPlayer entityplayer, int i, int j, int k,
			int l, int i1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyBlockPartially(int i, int j, int k, int l, int i1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
        	if (!hookWorldAccessAdded) {
        		if (Minecraft.getMinecraft().theWorld != null) {
        			if (hookDelay++ > 40) {
	        			hookWorldAccessAdded = true;
	        			Minecraft.getMinecraft().theWorld.addWorldAccess(TroubleEvent.instance());
        			}
        		}
        		
        	}
        	/*if (Minecraft.getMinecraft().theWorld.wo)
        		Minecraft.getMinecraft().theWorld.addWorldAccess(TroubleEvent.instance());*/
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}



}
