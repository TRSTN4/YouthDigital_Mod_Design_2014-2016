package mytroublemod.tsconfig.troubleapi.dimension.gen;

import net.minecraft.world.gen.feature.WorldGenerator;

public class ModWorldGenEntry {

	private WorldGenerator gen;
	private int count = 1;
	private int rarity = 0;
	
	/**
	 * Create entry for world generator
	 * 
	 * @param gen
	 */
	public ModWorldGenEntry(WorldGenerator gen) {
		this.setGen(gen);
	}
	
	/**
	 * Create entry for world generator, with added rarity 
	 * 
	 * @param gen
	 * @param rarity higher value means less common
	 */
	public ModWorldGenEntry(WorldGenerator gen, int rarity) {
		this.setGen(gen);
		this.setRarity(rarity);
	}
	
	/**
	 * Create entry for world generator, with added rarity and count 
	 * 
	 * @param gen
	 * @param rarity higher value means less common
	 * @param count how many randomly positioned features to generate, if rarity decides to generate it
	 */
	public ModWorldGenEntry(WorldGenerator gen, int rarity, int count) {
		this.setGen(gen);
		this.setRarity(rarity);
		this.setCount(count);
	}

	public WorldGenerator getGen() {
		return gen;
	}

	public void setGen(WorldGenerator gen) {
		this.gen = gen;
	}

	public int getCount() {
		return count;
	}

	/**
	 * Set how many randomly positioned features to generate, if rarity decides to generate it
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	public int getRarity() {
		return rarity;
	}
	
	/**
	 * Set how rare feature should be, higher value means less common
	 * 
	 * @param rarity
	 */
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	
}
