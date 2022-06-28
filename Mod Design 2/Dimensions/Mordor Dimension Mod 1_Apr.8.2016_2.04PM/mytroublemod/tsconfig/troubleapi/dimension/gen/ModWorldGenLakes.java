package mytroublemod.tsconfig.troubleapi.dimension.gen;

import java.util.Random;

import mytroublemod.tsconfig.troubleapi.tsconfig.TroubleEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ModWorldGenLakes extends WorldGenerator
{
    private int blockIndex;
    private int lakeSize = 16;

    /**
     * Generates lakes of a custom block
     * 
     * @param par1
     */
    public ModWorldGenLakes(int par1)
    {
        this.blockIndex = par1;
    }
    
    /**
     * Generates lakes of a custom block and size, default size is 16, larger sizes will slow down world gen greatly!
     * 
     * @param par1
     * @param lakeSize
     */
    public ModWorldGenLakes(int par1, int lakeSize)
    {
        this.blockIndex = par1;
        this.lakeSize = lakeSize;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    
    	int lakeHeight = 8;
    
        par3 -= 8;

        for (par5 -= 8; par4 > 5 && par1World.isAirBlock(par3, par4, par5); --par4)
        {
            ;
        }

        if (par4 <= 4)
        {
            return false;
        }
        else
        {
            par4 -= 4;
            //boolean[] aboolean = new boolean[2048];
            boolean[] bBoolean = new boolean[lakeSize];
            boolean[] aboolean = new boolean[lakeSize * lakeSize * lakeHeight];
            int l = par2Random.nextInt(4) + 4;
            int i1;

            for (i1 = 0; i1 < l; ++i1)
            {
                double d0 = par2Random.nextDouble() * (((double)lakeSize / 2D) - 4D) + (((double)lakeSize / 4D) - 2D)/*12.0D + 6.0D*/;
                double d1 = par2Random.nextDouble() * 4.0D + 2.0D;
                double d2 = par2Random.nextDouble() * (((double)lakeSize / 2D) - 4D) + (((double)lakeSize / 4D) - 2D)/*12.0D + 6.0D*/;
                double d3 = par2Random.nextDouble() * (lakeSize - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = par2Random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = par2Random.nextDouble() * (lakeSize - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int j1 = 1; j1 < lakeSize - 1; ++j1)
                {
                    for (int k1 = 1; k1 < lakeSize - 1; ++k1)
                    {
                        for (int l1 = 1; l1 < lakeHeight - 1; ++l1)
                        {
                            double d6 = ((double)j1 - d3) / (d0 / 2.0D);
                            double d7 = ((double)l1 - d4) / (d1 / 2.0D);
                            double d8 = ((double)k1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D)
                            {
                                aboolean[(j1 * 16 + k1) * 8 + l1] = true;
                            }
                        }
                    }
                }
            }
            
            
            
            TroubleEvent.instance().checkWorldGenRuns();

            int i2;
            int j2;
            boolean flag;

            for (i1 = 0; i1 < lakeSize; ++i1)
            {
                for (j2 = 0; j2 < lakeSize; ++j2)
                {
                    for (i2 = 0; i2 < 8; ++i2)
                    {
                        flag = !aboolean[(i1 * 16 + j2) * 8 + i2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + i2] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + i2] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + i2] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + i2] || i2 < 7 && aboolean[(i1 * 16 + j2) * 8 + i2 + 1] || i2 > 0 && aboolean[(i1 * 16 + j2) * 8 + (i2 - 1)]);

                        if (flag)
                        {
                            Material material = par1World.getBlockMaterial(par3 + i1, par4 + i2, par5 + j2);

                            if (i2 >= 4 && material.isLiquid())
                            {
                                return false;
                            }

                            if (i2 < 4 && !material.isSolid() && par1World.getBlockId(par3 + i1, par4 + i2, par5 + j2) != this.blockIndex)
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            for (i1 = 0; i1 < lakeSize; ++i1)
            {
                for (j2 = 0; j2 < lakeSize; ++j2)
                {
                    for (i2 = 0; i2 < 8; ++i2)
                    {
                        if (aboolean[(i1 * 16 + j2) * 8 + i2])
                        {
                            par1World.setBlock(par3 + i1, par4 + i2, par5 + j2, i2 >= 4 ? 0 : this.blockIndex, 0, 2);
                        }
                    }
                }
            }

            for (i1 = 0; i1 < lakeSize; ++i1)
            {
                for (j2 = 0; j2 < lakeSize; ++j2)
                {
                    for (i2 = 4; i2 < 8; ++i2)
                    {
                        if (aboolean[(i1 * 16 + j2) * 8 + i2] && par1World.getBlockId(par3 + i1, par4 + i2 - 1, par5 + j2) == Block.dirt.blockID && par1World.getSavedLightValue(EnumSkyBlock.Sky, par3 + i1, par4 + i2, par5 + j2) > 0)
                        {
                            BiomeGenBase biomegenbase = par1World.getBiomeGenForCoords(par3 + i1, par5 + j2);

                            if (biomegenbase.topBlock == Block.mycelium.blockID)
                            {
                                par1World.setBlock(par3 + i1, par4 + i2 - 1, par5 + j2, Block.mycelium.blockID, 0, 2);
                            }
                            else
                            {
                                par1World.setBlock(par3 + i1, par4 + i2 - 1, par5 + j2, Block.grass.blockID, 0, 2);
                            }
                        }
                    }
                }
            }

            if (Block.blocksList[this.blockIndex].blockMaterial == Material.lava)
            {
                for (i1 = 0; i1 < lakeSize; ++i1)
                {
                    for (j2 = 0; j2 < lakeSize; ++j2)
                    {
                        for (i2 = 0; i2 < 8; ++i2)
                        {
                            flag = !aboolean[(i1 * 16 + j2) * 8 + i2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + i2] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + i2] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + i2] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + i2] || i2 < 7 && aboolean[(i1 * 16 + j2) * 8 + i2 + 1] || i2 > 0 && aboolean[(i1 * 16 + j2) * 8 + (i2 - 1)]);

                            if (flag && (i2 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlockMaterial(par3 + i1, par4 + i2, par5 + j2).isSolid())
                            {
                                par1World.setBlock(par3 + i1, par4 + i2, par5 + j2, Block.stone.blockID, 0, 2);
                            }
                        }
                    }
                }
            }

            if (Block.blocksList[this.blockIndex].blockMaterial == Material.water)
            {
                for (i1 = 0; i1 < lakeSize; ++i1)
                {
                    for (j2 = 0; j2 < lakeSize; ++j2)
                    {
                        byte b0 = 4;

                        if (par1World.isBlockFreezable(par3 + i1, par4 + b0, par5 + j2))
                        {
                            par1World.setBlock(par3 + i1, par4 + b0, par5 + j2, Block.ice.blockID, 0, 2);
                        }
                    }
                }
            }

            return true;
        }
    }
}
