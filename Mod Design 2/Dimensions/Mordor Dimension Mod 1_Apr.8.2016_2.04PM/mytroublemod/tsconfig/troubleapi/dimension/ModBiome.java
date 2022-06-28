package mytroublemod.tsconfig.troubleapi.dimension;

import java.util.ArrayList;
import java.util.List;

import mytroublemod.tsconfig.troubleapi.dimension.gen.ModWorldGenEntry;
import net.minecraft.world.biome.BiomeGenBase;

public class ModBiome extends BiomeGenBase {
	
	public byte undergroundBlock;
	
	private List<ModWorldGenEntry> listGenerators = new ArrayList<ModWorldGenEntry>();

	public ModBiome(int par1) {
		super(par1, false);
	}
	
	/**
	 * Adds a new world generation entry to use for this biome
	 * 
	 * @param generator
	 */
	public void addWorldGenerator(ModWorldGenEntry generator) {
		listGenerators.add(generator);
	}
	
	public List<ModWorldGenEntry> getWorldGenerators() {
		return listGenerators;
	}
	
	public int getBlockTop() {
		return getIDFixed(this.topBlock);
	}
	
	public int getBlockFiller() {
		return getIDFixed(this.fillerBlock);
	}
	
	public int getBlockUnderground() {
		return getIDFixed(this.undergroundBlock);
	}
	
	public static int getIDFixed(byte ID) {
		int idFix = ID;
		if (idFix < 0) idFix += 256;
		return idFix;
	}

}
