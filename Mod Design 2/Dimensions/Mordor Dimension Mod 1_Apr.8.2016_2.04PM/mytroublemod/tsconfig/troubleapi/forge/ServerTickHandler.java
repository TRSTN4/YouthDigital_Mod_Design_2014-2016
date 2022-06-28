package mytroublemod.tsconfig.troubleapi.forge;

import java.util.EnumSet;

import mytroublemod.tsconfig.troubleapi.dimension.ModWorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
	

	@Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    	
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.SERVER)))
        {
        	onTickInGame();
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() { return null; }
    

    public void onTickInGame()
    {
    	//System.out.println("SDfsdfsdfsdf");
    	
    	WorldServer[] worlds = DimensionManager.getWorlds();
    	for (WorldServer world : worlds) {
    		if (world.provider instanceof ModWorldProvider) {
    			((ModWorldProvider)world.provider).tickServer();
    		}
    	}
    }
}
