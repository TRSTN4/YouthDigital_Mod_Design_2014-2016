/**
 *	TROUBLESHOOTING 7 INSTRUCTIONS
 *-----------------------------------
 *	1.	Fix all the Errors in this class
 *	2. 	Add Giant Zombies to this Biome
 *	3.	Run Minecraft and create a new Creative World
 *	4. 	Find a Giant Zombie and hit him
 */

package mytroublemod.biome;

import mymod.Main;
import mytroublemod.TroubleMain;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class TroubleBiome extends BiomeGenBase
{
    public TroubleBiome(int par1)
    {
        super(par1);
        
        this.setBiomeName("My Trouble Biome");
        
        this.topBlock = (byte)TroubleMain.TroubleBlock_1.blockID;
        this.fillerBlock = (byte)Block.blockDiamond.blockID;
        
        this.theBiomeDecorator.bigMushroomsPerChunk = 10;
        this.theBiomeDecorator.treesPerChunk = 50;

        this.spawnableCreatureList.add(new SpawnListEntry(EntityGiantZombie.class, 1, 1, 1));
        
        this.setMinMaxHeight(0.1F, 2.5F);
        this.setTemperatureRainfall(1.5F, 0.2F);
   
    }
}