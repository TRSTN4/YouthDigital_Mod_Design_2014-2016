package mymod.dimension;

import mymod.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.biome.SpawnListEntry;
import api.dimension.ModBiome;
import api.dimension.gen.ModWorldGenEntry;
import api.dimension.gen.ModWorldGenLakes;
import api.dimension.gen.ModWorldGenTrees;
import api.util.ModColor;

public class MyDimensionBiome_1 extends ModBiome
{
    public MyDimensionBiome_1(int par1)
    {
        super(par1);
        
        this.setBiomeName("Mordor Forest");

//  BLOCKS        
        this.topBlock =(byte) Main.MyDimensionBlock_1.blockID;
        this.fillerBlock =(byte) Main.MyDimensionBlock_4.blockID;
        this.undergroundBlock =(byte) Main.MyDimensionBlock_2.blockID;
           
//  MOBS
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 10, 1, 5));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderCrystal.class, 10, 1, 5));
 

//  FEATURES
        this.setMinMaxHeight(0.5F, 1F);
        this.setTemperatureRainfall(1F, 5F);
        this.theBiomeDecorator.treesPerChunk =(int) 0.5;
        
//  TREES        
        ModWorldGenTrees treeGen = new ModWorldGenTrees(10 , 10, Main.MyDimensionBlock_2.blockID, 2, Main.MyDimensionBlock_3.blockID, 2, false);
        treeGen.addLeafHeight(1);
        treeGen.addLeafWidth(3);
        this.worldGeneratorTrees = treeGen;
        
//  LAKES       
        ModWorldGenEntry lakeGen = new ModWorldGenEntry(new ModWorldGenLakes(Block.lavaStill.blockID, 16));
        lakeGen.setRarity(4);
        lakeGen.setCount(3);
        addWorldGenerator(lakeGen);
        this.waterColorMultiplier = ModColor.rgbToInt(0, 0, 250);
    }
}