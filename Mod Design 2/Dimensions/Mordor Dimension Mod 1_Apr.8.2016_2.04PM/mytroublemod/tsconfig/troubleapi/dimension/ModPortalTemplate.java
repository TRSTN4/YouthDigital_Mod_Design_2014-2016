package mytroublemod.tsconfig.troubleapi.dimension;

import java.util.Random;

import mytroublemod.tsconfig.troubleapi.ModAPI;
import mytroublemod.tsconfig.troubleapi.Structure;
import mytroublemod.tsconfig.troubleapi.util.SpawnEggs;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class ModPortalTemplate extends Structure {

	public void initStructure() {
		
		useScaffoldingOnGenerate(false);
		
		setChanceToGenerateInChunk(10);
		
		setStructureShape (new Object[] {
            "XXXX",
            "X  X",
            "X  X",
            "X* X",
            "XXXX",
                'X', ModAPI.ydPortalTemplateFrame,
                '*', Block.fire
        });
		
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		
	}

}
