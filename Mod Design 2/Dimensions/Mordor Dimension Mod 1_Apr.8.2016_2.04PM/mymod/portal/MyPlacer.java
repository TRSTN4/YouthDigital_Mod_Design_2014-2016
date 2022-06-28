package mymod.portal;

import java.util.List;
import java.util.Random;

import mymod.Main;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MyPlacer extends Item {
    private String texturePath = "mydimensionmod:";
    
    public MyPlacer(int par1, String textureName) {
        super(par1);
        setCreativeTab(CreativeTabs.tabTools);
        this.setUnlocalizedName(textureName);
        texturePath += textureName;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int X, int Y, int Z, int par7, float par8, float par9, float par10)
    {
                
        if (!par3World.isRemote) 
        {
            int direction = MathHelper.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
            
            /** Generate structure */
            Main.MyPortal_1.generateStructure(new Random(), X, Y, Z, par3World, direction);
                
                /** Spawn Explosive Particles */

par3World.spawnParticle("explode", X, Y+2, Z, 0.0D, 0.0D, 0.0D);
par3World.spawnParticle("largeexplode", X, Y+2, Z, 0.0D, 0.0D, 0.0D);
par3World.spawnParticle("hugeexplosion", X, Y+2, Z, 0.0D, 0.0D, 0.0D);
par3World.playSoundAtEntity(par2EntityPlayer, "random.explode", 0.4F, 0.7F);
                
            return true;
        }
        return true;
    }
    
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(texturePath);
    }
    
   
}