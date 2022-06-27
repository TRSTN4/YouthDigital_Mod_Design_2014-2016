package mymod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemStack;


public class MySword extends ItemSword {
    
    private String texturePath = "mymod:";
    
    public MySword(int ItemID, EnumToolMaterial material, String textureName)
    {
        super(ItemID, material);
        this.setUnlocalizedName(textureName);
        texturePath += textureName;
    }

@Override   
@SideOnly(Side.CLIENT)

    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(texturePath);
    }   

/** Adds Lightning Power to your Sword */

    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase player)
    {
        target.worldObj.addWeatherEffect(new EntityLightningBolt(target.worldObj, target.posX, target.posY, target.posZ));
        return true;
    }
    
}