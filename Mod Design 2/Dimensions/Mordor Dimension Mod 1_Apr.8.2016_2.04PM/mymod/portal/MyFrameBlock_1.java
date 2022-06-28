package mymod.portal;

import mymod.Main;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import api.block.ModPortalFrameBlock;
import api.util.DimensionHelper;

public class MyFrameBlock_1 extends ModPortalFrameBlock {

    public MyFrameBlock_1(int par1, Material blockMaterial, String textureName) {
        super(par1, blockMaterial, textureName);
    }
    
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
            int par4, int par5) {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);

        DimensionHelper.tryToCreateLargePortal(par1World, par2, par3, par4, Main.MyFrameBlock_1, Main.MyPortalBlock_1, Main.MyPortal_1);
    }

}
