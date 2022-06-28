package mytroublemod.tsconfig.troubleapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mytroublemod.tsconfig.troubleapi.client.entity.RenderLightningBoltNew;
import mytroublemod.tsconfig.troubleapi.dimension.ModPortalTemplate;
import mytroublemod.tsconfig.troubleapi.entity.EntityLightningBoltNew;
import mytroublemod.tsconfig.troubleapi.forge.ClientTickHandler;
import mytroublemod.tsconfig.troubleapi.forge.ServerTickHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ModAPI {

	public static ModPortalTemplate ydPortalTemplate;
	public static Block ydPortalTemplateFrame;
	
	public static HashMap<Integer, List<ChunkCoordinates>> listStructureCoordinates = new HashMap<Integer, List<ChunkCoordinates>>();
	
	public static void registerWorldGenerator(IWorldGenerator generator) {
		GameRegistry.registerWorldGenerator(generator);
	}
	
	/**
	 * Initializes some stuff the API needs to help your mod work
	 */
	public static void init() {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBoltNew.class, new RenderLightningBoltNew());
		
		ydPortalTemplateFrame = Block.blockRedstone;
		ydPortalTemplate = new ModPortalTemplate();
	}
	
	public static void addStructureEntry(World world, int x, int y, int z) {
		List<ChunkCoordinates> listCoords = null;
		if (listStructureCoordinates.containsKey((world.provider.dimensionId))) {
			listCoords = listStructureCoordinates.get(world.provider.dimensionId);
		} else {
			listCoords = new ArrayList<ChunkCoordinates>();
			listStructureCoordinates.put(world.provider.dimensionId, listCoords);
		}
		
		listCoords.add(new ChunkCoordinates(x, y, z));
	}
	
	public static float getClosestStructureDist(World world, int x, int y, int z) {
		
		float distClosest = 99999;
			
		if (!listStructureCoordinates.containsKey((world.provider.dimensionId))) {
			return distClosest;
		} else {
			List<ChunkCoordinates> listCoords = listStructureCoordinates.get(world.provider.dimensionId);
			
			for (ChunkCoordinates coord : listCoords) {
				float dist = (float) Math.sqrt(coord.getDistanceSquared(x, y, z));
				if (dist < distClosest) {
					distClosest = dist;
				}
			}
			return distClosest;
		}
		
	}

}
