package mytroublemod.tsconfig.troubleapi.util;

public enum SpawnEggs {

	creeper(50), skeleton(51), spider(52), zombie(54), slime(55), ghast(56), zombiePigman(
			57), enderman(58), caveSpider(59), silverfish(60), blaze(61), magmaCube(
			62), bat(65), witch(66), endermite(67), guardian(68), pig(90), sheep(
			91), cow(92), chicken(93), squid(94), wolf(95), mooshroom(96), ocelot(
			98), horse(100), rabbit(101), villager(120);

	public int metaVal = 0;

	/**
	 * Used for specify what type of entity the spawn egg item should be for
	 * 
	 * @param val
	 */
	private SpawnEggs(int val) {
		this.metaVal = val;
	}
	
	/**
	 * Get the ID of the entity you want to spawn
	 * 
	 * @return
	 */
	public int getID() {
		return metaVal;
	}

}
