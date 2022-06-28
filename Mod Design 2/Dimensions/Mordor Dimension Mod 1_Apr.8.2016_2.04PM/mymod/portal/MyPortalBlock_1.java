package mymod.portal;

import java.util.Random;

import mymod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import api.block.ModPortalBlock;
import api.dimension.ModTeleporter;
import api.util.ModColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MyPortalBlock_1 extends ModPortalBlock {

    public MyPortalBlock_1(int par1, String textureName) {
        super(par1, textureName);
    }
    
    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
   public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
            int par4, Entity par5Entity) {
        if ((par5Entity.ridingEntity == null)
                && (par5Entity.riddenByEntity == null)
                && ((par5Entity instanceof EntityPlayerMP))) {
            EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
            if (thePlayer.timeUntilPortal > 0) {
                thePlayer.timeUntilPortal = 10;
            } else if (thePlayer.dimension != Main.MyDimensionID_1) {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer
                        .getConfigurationManager()
                        .transferPlayerToDimension(
                                thePlayer,
                                Main.MyDimensionID_1,
                                new ModTeleporter(
                                        thePlayer.mcServer
                                                .worldServerForDimension(Main.MyDimensionID_1), Main.MyFrameBlock_1, Main.MyPortalBlock_1, Main.MyPortal_1));
            } else {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager()
                        .transferPlayerToDimension(
                                thePlayer,
                                0,
                                new ModTeleporter(thePlayer.mcServer.worldServerForDimension(0), Main.MyFrameBlock_1, Main.MyPortalBlock_1, Main.MyPortal_1));
            }
        }
    }
    


    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3,
            int par4, Random par5Random) {
        if (par5Random.nextInt(100) == 0) {
            par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D,
                    (double) par4 + 0.5D, "liquid.lava", 0.5F,
                    par5Random.nextFloat() * 0.4F + 0.8F, false);
        }
        for (int l = 0; l < 4; ++l) {
            double d0 = (double) ((float) par2 + par5Random.nextFloat());
            double d1 = (double) ((float) par3 + par5Random.nextFloat());
            double d2 = (double) ((float) par4 + par5Random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
            if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID
                    && par1World.getBlockId(par2 + 1, par3, par4) != this.blockID) {
                d0 = (double) par2 + 0.5D + 0.25D * (double) i1;
                d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
            } else {
                d2 = (double) par4 + 0.5D + 0.25D * (double) i1;
                d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
            }
            par1World.spawnParticle("magicCrit", d0, d1, d2, d3, d4, d5);
        }
    }
    

}
