/* *******************************************
 * *******************************************
 * 
 * 			| TS CODE - DO NOT TOUCH |
 * 
 * *******************************************
 * *******************************************	*/

package mytroublemod.items;

import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;


public class TroubleItem extends Item 
{
    
	private String texturePath = "troublemod:";
	
    public TroubleItem(int par1, String textureName) 
    {
        super(par1);
		this.setUnlocalizedName(textureName);
		texturePath += textureName;
    }

@Override
@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(texturePath);
    }
    
}