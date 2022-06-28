package mytroublemod.portal;

import mytroublemod.TroubleMain;
import mytroublemod.tsconfig.troubleapi.block.ModPortalFrameBlock;
import mytroublemod.tsconfig.troubleapi.util.DimensionHelper;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class MyFrameBlock_1 extends ModPortalFrameBlock {

    public MyFrameBlock_1(int par1, Material blockMaterial, String textureName) {
        super(par1, blockMaterial, textureName);
    }
    
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
            int par4, int par5) {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);

        DimensionHelper.tryToCreateLargePortal(par1World, par2, par3, par4, TroubleMain.MyFrameBlock_1, TroubleMain.MyPortalBlock_1, TroubleMain.MyPortal_1);
    }

}
