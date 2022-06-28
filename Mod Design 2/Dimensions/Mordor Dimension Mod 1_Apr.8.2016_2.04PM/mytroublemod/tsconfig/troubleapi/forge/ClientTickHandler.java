package mytroublemod.tsconfig.troubleapi.forge;

import java.util.EnumSet;

import mytroublemod.tsconfig.troubleapi.dimension.EntityRendererOverride;
import mytroublemod.tsconfig.troubleapi.dimension.ModWorldProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler
{
	

	@Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    	
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
        	onTickInGame();
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() { return null; }
    

    public void onTickInGame()
    {
    	
    	WorldServer[] worlds = DimensionManager.getWorlds();
    	for (WorldServer world : worlds) {
    		if (world.provider instanceof ModWorldProvider) {
    			((ModWorldProvider)world.provider).tickClient();
    		}
    	}
    	
    	Minecraft mc = Minecraft.getMinecraft();
    	if (!(mc.entityRenderer instanceof EntityRendererOverride) && !(mc.entityRenderer instanceof api.dimension.EntityRendererOverride)) {
    		EntityRendererOverride temp = new EntityRendererOverride(mc);
	        mc.entityRenderer = temp;
    	}
    }
}
