package mytroublemod.tsconfig.troubleapi.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModPortalBlock extends BlockBreakable {
    
    //going to use meta for rotation, first bit for 2 factor rotation, 0 for east west, 1 for north south
    
    private static int frameBlock;
    private static int portalHeight;
    private static int portalWidth;
    private boolean bBorderXPOS = false;
    private boolean bBorderXNEG = false;
    private boolean bBorderYPOS = false;
    private boolean bBorderYNEG = false;
    private boolean bBorderZPOS = false;
    private boolean bBorderZNEG = false;
    private Object tabToDisplayOn;
    private String texturePath = "mytroubledimensionmod:";
    
    public ModPortalBlock(int par1, String unlocalizedName) {
        super(par1, unlocalizedName, Material.portal, false);
        this.setTickRandomly(true);
        this.setHardness(-1.0F);
        this.setStepSound(soundGlassFootstep);
        this.setLightValue(0.75F);
        texturePath += unlocalizedName;
    }

    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(texturePath);
    }

    @Override
    public Block setCreativeTab(CreativeTabs par1CreativeTabs)
    {
        this.tabToDisplayOn = null;
        return this;
    }   
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4,
            Random par5Random) {
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }
   
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
            int par2, int par3, int par4) {
        return null;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
            int par2, int par3, int par4) {
        float f;
        float f1;
        int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        /*if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID
                && par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID) {*/
        if (meta == 1) {
            f = 0.125F;
            f1 = 0.5F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F,
                    0.5F + f1);
        } else {
            f = 0.5F;
            f1 = 0.125F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F,
                    0.5F + f1);
        }
    }

    /**
     * Is this block (a) opaque and (B) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Checks to see if this location is valid to create a portal and will
     * return True if it does. Args: world, x, y, z
     */
    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4) {
    
        byte b0 = 0;
        byte b1 = 0;

        if (par1World.getBlockId(par2 - 1, par3, par4) == frameBlock || par1World.getBlockId(par2 + 1, par3, par4) == frameBlock)
        {
            b0 = 1;
        }

        if (par1World.getBlockId(par2, par3, par4 - 1) == frameBlock || par1World.getBlockId(par2, par3, par4 + 1) == frameBlock)
        {
            b1 = 1;
        }

        if (b0 == b1)
        {
            return false;
        }
        else
        {
            if (par1World.isAirBlock(par2 - b0, par3, par4 - b1))
            {
                par2 -= b0;
                par4 -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l)
            {
                for (i1 = -1; i1 <= 3; ++i1)
                {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
                    {
                        int j1 = par1World.getBlockId(par2 + b0 * l, par3 + i1, par4 + b1 * l);
                        boolean isAirBlock = par1World.isAirBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);

                        if (flag)
                        {
                            if (j1 != frameBlock)
                            {
                                return false;
                            }
                        }
                        else if (!isAirBlock && (j1 != Block.fire.blockID))
                        {
                            return false;
                        }
                    }
                }
            }

            for (l = 0; l < 2; ++l)
            {
                for (i1 = 0; i1 < 3; ++i1)
                {
                    par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, this.blockID/*Main.MyPortalBlock_1.blockID*/, 0, 2);
                }
            }

            return true;
        }
        
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        /*byte b0 = 0;
        byte b1 = 1;
        if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
        {
            b0 = 1;
            b1 = 0;
        }
        int i1;
        for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == this.blockID; --i1) {
            ;
        }
        if (par1World.getBlockId(par2, i1 - 1, par4) != frameBlock) {
            par1World.setBlockToAir(par2, par3, par4);
        } else {
            int j1;
            for (j1 = 1; j1 < 4 && par1World.getBlockId(par2, i1 + j1, par4) == this.blockID; ++j1)
            {
                ;
            }
            if (j1 == 3 && par1World.getBlockId(par2, i1 + j1, par4) == frameBlock)
            {
                boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
                boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;
                if (flag && flag1)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else 
                {
                    if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != frameBlock || par1World.getBlockId(par2 - b0, par3, par4 - b1) != this.blockID) && (par1World.getBlockId(par2 - b0, par3, par4 - b1) != frameBlock || par1World.getBlockId(par2 + b0, par3, par4 + b1) != this.blockID))
                    {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
            } else {
                par1World.setBlockToAir(par2, par3, par4);
            }
        }*/
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates. Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
            int par2, int par3, int par4, int par5) {
        if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID) {
            return false;
        } else {
            boolean flag = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID
                    && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
            boolean flag1 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID
                    && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
            boolean flag2 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID
                    && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
            boolean flag3 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID
                    && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
            boolean flag4 = flag || flag1;
            boolean flag5 = flag2 || flag3;
            return flag4 && par5 == 4 ? true : (flag4 && par5 == 5 ? true
                    : (flag5 && par5 == 2 ? true : flag5 && par5 == 3));
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return 0;
    }
    
    public static void setFrameBlock(Block b)
    {
        frameBlock = b.blockID;
    }
    public static void setPortalSize(int pW, int pH)
    {
        portalWidth = pW;
        portalHeight = pH;
    }
}