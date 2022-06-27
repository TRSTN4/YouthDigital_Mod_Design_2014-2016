package mymod.biome;

import mymod.Main;
import mytroublemod.TroubleMain;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityMooshroom;    
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class MyBiome extends BiomeGenBase
{
    public MyBiome(int par1)
    {
        super(par1);
        
        this.setBiomeName("Super Biome");
        
        this.topBlock = (byte)Main.MyBlock_1.blockID;
        this.fillerBlock = (byte)TroubleMain.TroubleBlock_1.blockID;
        
        this.theBiomeDecorator.deadBushPerChunk = 1000;
        this.theBiomeDecorator.bigMushroomsPerChunk = 20;

        this.spawnableCreatureList.add(new SpawnListEntry(EntityDragon.class, 0, 0, 0));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityGiantZombie.class, 0, 0, 0));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySlime.class, 0, 0, 0));
        
        this.setMinMaxHeight(0.1F, 10.0F);
        this.setTemperatureRainfall(10.0F, 1.0F);
   
    }
}