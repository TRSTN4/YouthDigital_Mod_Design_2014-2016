/*
 * Class Description 
 * - A special class of Block that allows you to include Mob Spawning in Building Recipes
 */

package mytroublemod.tsconfig.troubleapi.util;

import net.minecraft.entity.passive.EntityPig;

public class MyRecipeSpawnBlock {
    
    public Class mobToSpawn = EntityPig.class;
    public int numberToSpawn = 1;
    
    /**
     * Used to specify an entity and number of them to spawn in your recipe
     * 
     * @param parClass
     * @param parCount
     */
    public MyRecipeSpawnBlock(Class parClass, int parCount) {
        mobToSpawn = parClass;
        numberToSpawn = parCount;
    }
    
}

