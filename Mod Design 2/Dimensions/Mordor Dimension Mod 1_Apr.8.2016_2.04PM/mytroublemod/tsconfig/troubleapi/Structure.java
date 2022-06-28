package mytroublemod.tsconfig.troubleapi;

import java.util.Random;

import mytroublemod.tsconfig.troubleapi.dimension.ModBiome;
import mytroublemod.tsconfig.troubleapi.util.StructureHelper;
import mytroublemod.tsconfig.troubleapi.util.StructureHelper.StructureData;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class Structure implements IWorldGenerator {

	public static int FRONT = 0;
    public static int LEFT = 1;
    public static int BACK = 2;
    public static int RIGHT = 3;
    public static String SLICE = "SLICE";
    
	public StructureHelper.StructureData structureData;
	
	private int chanceToGenerateInChunk = 100;
	private boolean useScaffolding = true;

	public Structure() {
		initStructure();
	}
	
	public void initStructure() {
	
	}

	public int getChanceToGenerateInChunk() {
		return chanceToGenerateInChunk;
	}

	/**
	 * Percent chance to generate in a chunk
	 * 
	 * @param chanceToGenerateInChunk set between 0 and 100
	 */
	public void setChanceToGenerateInChunk(int chanceToGenerateInChunk) {
		this.chanceToGenerateInChunk = chanceToGenerateInChunk;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		
	}
	
	/**
	 * Set structure shape and recipe info
	 * 
	 * @param recipe
	 */
	public void setStructureShape(Object... recipe)
    {
        structureData = StructureHelper.setStructureShape(this, recipe);
    }
    
    /**
     * Generate the structure from the recipe
     * 
     * @param random
     * @param xStart
     * @param yStart
     * @param zStart
     * @param world
     * @param parCoordBaseMode rotation to use, ranging from 0-3
     */
    public void generateStructure(Random random, int xStart, int yStart, int zStart, World world, int parCoordBaseMode) {
    	//debug mode fix
    	this.initStructure();
    	structureData.generateStructure(random, xStart, yStart, zStart, world, parCoordBaseMode);
    }
    
    public void generateStructure(Random random, int xStart, int yStart, int zStart, World world, int parCoordBaseMode, Block scaffoldingReplacement) {
    	//debug mode fix
    	this.initStructure();
    	structureData.generateStructure(random, xStart, yStart, zStart, world, parCoordBaseMode, true, scaffoldingReplacement);
    }
    
    /**
     * Finds the highest up biome topBlock for this x/z coordinate  
     * 
     * @param world
     * @param x
     * @param z
     * @return
     */
    public int getBiomeTopBlock(World world, int x, int z) {
    	BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
    	int startY = world.getTopSolidOrLiquidBlock(x, z);
    	int topBlock = ModBiome.getIDFixed(biome.topBlock);
    	while (startY >= 0 && world.getBlockId(x, startY, z) != topBlock) {
    		startY--;
    	}
    	return startY;
    }
    
    /**
     * Used to enable or disable scaffolding generation, defaults to true
     * 
     * @param value
     */
    public void useScaffoldingOnGenerate(boolean value) {
    	this.useScaffolding = value;
    }

	public boolean isUsingScaffolding() {
		return useScaffolding;
	}

}
