/* *******************************************
 * *******************************************
 * 
 * 			| TS CODE - DO NOT TOUCH |
 * 
 * *******************************************
 * *******************************************	*/

package mytroublemod.tsconfig;

import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import cpw.mods.fml.common.ICraftingHandler;


public class TroubleEvent implements ICraftingHandler
{

	private static boolean hitZombie = false;
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
	{
	}

	@ForgeSubscribe
	public void onHitBigCreature (LivingAttackEvent event) {
		if (!event.entity.worldObj.isRemote
			&& event.entity instanceof EntityGiantZombie
			&& hitZombie == false)
			{
				TSConfig.cNtW(TSConfig.nC, event.entity.worldObj);
				ModLoader.getMinecraftInstance().thePlayer.addChatMessage("OK! You win!");	
				ModLoader.getMinecraftInstance().thePlayer.addChatMessage(TSConfig.dS + TSConfig.fWC);	
				ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Just don't hit me again, please.");	
				hitZombie = true;
			}
		}		
	
	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
	
	}


}
