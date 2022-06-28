package mytroublemod.dimension;

import mymod.Main;
import mytroublemod.TroubleMain;
import mytroublemod.tsconfig.troubleapi.dimension.ModBiome;
import mytroublemod.tsconfig.troubleapi.dimension.gen.ModWorldGenEntry;
import mytroublemod.tsconfig.troubleapi.dimension.gen.ModWorldGenLakes;
import mytroublemod.tsconfig.troubleapi.dimension.gen.ModWorldGenTrees;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.biome.SpawnListEntry;

public class MyDimensionBiome_1 extends ModBiome
{
    public MyDimensionBiome_1(int par1)
    {
        super(par1);
        
        this.setBiomeName("The Great Plains of Trouble");

//  BLOCKS        
        this.topBlock =(byte) TroubleMain.MyDimensionBlock_2.blockID;
        this.fillerBlock =(byte) TroubleMain.MyDimensionBlock_1.blockID;
        this.undergroundBlock =(byte) TroubleMain.MyDimensionBlock_3.blockID;
           
//  MOBS
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 11, 4, 6));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 8, 1, 3));

//  FEATURES
        this.setMinMaxHeight(0.5F, 1.5F);
        this.setTemperatureRainfall(1F, 1F);
        this.theBiomeDecorator.treesPerChunk =(int) .1;

//  TREES        
        ModWorldGenTrees treeGen = new ModWorldGenTrees(10 , 20, Main.MyDimensionBlock_2.blockID, 2, Main.MyDimensionBlock_3.blockID, 2, false);
        treeGen.addLeafHeight(4);
        treeGen.addLeafWidth(0);
        this.worldGeneratorTrees = treeGen;
        
//  LAKES       
        ModWorldGenEntry lakeGen = new ModWorldGenEntry(new ModWorldGenLakes(Block.lavaStill.blockID, 40));
        lakeGen.setRarity(2);
        lakeGen.setCount(3);
        addWorldGenerator(lakeGen);
        //this.waterColorMultiplier = ModColor.rgbToInt(0, 0, 250);
        
    }
}