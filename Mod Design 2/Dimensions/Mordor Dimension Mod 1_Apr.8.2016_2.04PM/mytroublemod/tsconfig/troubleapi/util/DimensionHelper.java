package mytroublemod.tsconfig.troubleapi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mytroublemod.tsconfig.troubleapi.ModAPI;
import mytroublemod.tsconfig.troubleapi.Structure;
import mytroublemod.tsconfig.troubleapi.block.ModPortalBlock;
import mytroublemod.tsconfig.troubleapi.block.ModPortalFrameBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DimensionHelper {

	/*public static void tryToCreateLargePortal(World par1World, int x, int y,
            int z, Block frameBlock, Block portalBlock) {
            
        YDPortalFrameBlock frameBlockCast = (YDPortalFrameBlock) frameBlock;
		YDPortalBlock portalBlockCast = (YDPortalBlock) portalBlock;
        
        if (par1World.getBlockId(x-1, y, z) == Block.fire.blockID) {
            portalBlockCast.tryToCreatePortal(par1World, x-1, y, z);
        } else if (par1World.getBlockId(x+1, y, z) == Block.fire.blockID) {
            portalBlockCast.tryToCreatePortal(par1World, x+1, y, z);
        } else if (par1World.getBlockId(x, y, z-1) == Block.fire.blockID) {
            portalBlockCast.tryToCreatePortal(par1World, x, y, z-1);
        } else if (par1World.getBlockId(x, y, z+1) == Block.fire.blockID) {
            portalBlockCast.tryToCreatePortal(par1World, x, y, z+1);
        }
    }*/
    
    /**
     * Tries to create or validate portal, will break existing portal if it finds a missing frame connection, uses the default portal shape
     * 
     * @param par1World
     * @param x
     * @param y
     * @param z
     * @param frameBlock
     * @param portalBlock
     */
    public static void tryToCreateLargePortal(World par1World, int x, int y,
            int z, Block frameBlock, Block portalBlock) {
    	tryToCreateLargePortal(par1World, x, y, z, frameBlock, portalBlock, ModAPI.ydPortalTemplate);
    }
	
	/**
	 * Tries to create or validate portal, will break existing portal if it finds a missing frame connection, uses a custom portal recipe
	 * 
	 * @param worldIn
	 * @param x
	 * @param y
	 * @param z
	 * @param frameBlock
	 * @param portalBlock
	 * @return
	 */
	public static boolean tryToCreateLargePortal(World worldIn, int x, int y, int z, Block frameBlock, Block portalBlock, Structure portal) {
		if (worldIn.isRemote) return false;
		
		ModPortalFrameBlock frameBlockCast = (ModPortalFrameBlock) frameBlock;
		ModPortalBlock portalBlockCast = (ModPortalBlock) portalBlock;
		
		if (worldIn.getBlockId(x - 1, y, z) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x - 1, y, z, frameBlockCast, portalBlockCast, portal);
		} else if (worldIn.getBlockId(x + 1, y, z) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x + 1, y, z, frameBlockCast, portalBlockCast, portal);
		} else if (worldIn.getBlockId(x, y, z - 1) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y, z - 1, frameBlockCast, portalBlockCast, portal);
		} else if (worldIn.getBlockId(x, y, z + 1) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y, z + 1, frameBlockCast, portalBlockCast, portal);
		} else if (worldIn.getBlockId(x, y + 1, z) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y + 1, z, frameBlockCast, portalBlockCast, portal);
		} else if (worldIn.getBlockId(x, y - 1, z) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y - 1, z, frameBlockCast, portalBlockCast, portal);
		} else if (worldIn.getBlockId(x, y, z) == Block.fire.blockID) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y, z, frameBlockCast, portalBlockCast, portal);
		} else if (Block.blocksList[(worldIn.getBlockId(x - 1, y, z))] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x - 1, y, z, frameBlockCast, portalBlockCast, portal, true);
		} else if (Block.blocksList[worldIn.getBlockId(x + 1, y, z)] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x + 1, y, z, frameBlockCast, portalBlockCast, portal, true);
		} else if (Block.blocksList[worldIn.getBlockId(x, y, z - 1)] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y, z - 1, frameBlockCast, portalBlockCast, portal, true);
		} else if (Block.blocksList[worldIn.getBlockId(x, y, z + 1)] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y, z + 1, frameBlockCast, portalBlockCast, portal, true);
		} else if (Block.blocksList[worldIn.getBlockId(x, y + 1, z)] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y + 1, z, frameBlockCast, portalBlockCast, portal, true);
		} else if (Block.blocksList[worldIn.getBlockId(x, y - 1, z)] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y - 1, z, frameBlockCast, portalBlockCast, portal, true);
		} else if (Block.blocksList[worldIn.getBlockId(x, y, z)] instanceof ModPortalBlock) {
			DimensionHelper.tryToCreateLargePortalAt(worldIn, x, y, z, frameBlockCast, portalBlockCast, portal, true);
		}
		return false;
	}
	
	public static boolean tryToCreateLargePortalAt(World world, int frameBlockX, int frameBlockY, int frameBlockZ, ModPortalFrameBlock frameBlock, ModPortalBlock portalBlock, Structure portal) {
		return tryToCreateLargePortalAt(world, frameBlockX, frameBlockY, frameBlockZ, frameBlock, portalBlock, portal, false);
	}
	
	public static boolean tryToCreateLargePortalAt(World world, int frameBlockX, int frameBlockY, int frameBlockZ, ModPortalFrameBlock frameBlock, ModPortalBlock portalBlock, Structure portal, boolean onlyBreakIfInvalid) {
		
		//x y z are coords of frame block that detected change
		//tryToCreatePortal fed in fire coord, lets not do that for this one
		//or maybe we should, itll make it easier to validate on breaking of a portal block (in creative mode) or breaking of a frame block
		
		//given potential for large size, we must identify direction of portal by scanning under fire, then left/right/front/back
		
		//must find border of portal given a max allowed size
		
		//border traversing idea 1:
		//- start from bottom, mark y coord (might not be lowest y)
		//- do per layer upwards
		//- scan lengthwise finding borders, validate size
		//- go up another y, repeat till top found
		//- might not be real top
		//-- iterate along this 
		
		//idea 2:
		//- PF node traversal
		//- store list, cache most -X/-Z, X/Z, -Y, Y
		
		boolean validPortal = true;
		
		
		int maxLengthHeight = 20;
		
		//DimensionEntry entry = RegistryDimensions.instance().getEntryFromPortalFrameBlock(frameBlock);
		//BlockWrapper portalBlock = entry.getWrapper().getPortalBlock();
		//Block portalBlock = frameBlock.portal;
		World mcWorld = world;
		
		/*if (mcWorld.getBlockState(new ChunkCoordinates(frameBlockX, frameBlockY + 1, frameBlockZ)).getBlock() != Blocks.fire.getBlock()) {
			return false;
		}*/
		
		byte b0 = 0;
        byte b1 = 0;

        if (mcWorld.getBlockId(frameBlockX - 1, frameBlockY, frameBlockZ) == frameBlock.blockID || 
        		mcWorld.getBlockId(frameBlockX + 1, frameBlockY, frameBlockZ) == frameBlock.blockID || 
        		mcWorld.getBlockId(frameBlockX - 1, frameBlockY - 1, frameBlockZ) == frameBlock.blockID || 
        		mcWorld.getBlockId(frameBlockX + 1, frameBlockY - 1, frameBlockZ) == frameBlock.blockID)
        {
            b0 = 1;
        }

        if (mcWorld.getBlockId(frameBlockX, frameBlockY, frameBlockZ - 1) == frameBlock.blockID || 
        		mcWorld.getBlockId(frameBlockX, frameBlockY, frameBlockZ + 1) == frameBlock.blockID || 
        		mcWorld.getBlockId(frameBlockX, frameBlockY - 1, frameBlockZ - 1) == frameBlock.blockID || 
        		mcWorld.getBlockId(frameBlockX, frameBlockY - 1, frameBlockZ + 1) == frameBlock.blockID)
        {
            b1 = 1;
        }

        if (b0 == b1)
        {
            //return false;
        	validPortal = false;
        } else {
        	
        	int minLength = 0;
    		int minHeight = frameBlockY;
    		
    		int maxLength = 0;
    		int maxHeight = frameBlockY;
        	
        	EnumFacing portalDirection = null;
        	
        	//x
        	if (b0 == 1) {
        		portalDirection = EnumFacing.WEST; //east west
        		minLength = frameBlockX;
        		maxLength = frameBlockX;
        	
        	//z
        	} else {
        		portalDirection = EnumFacing.SOUTH; //north south
        		minLength = frameBlockZ;
        		maxLength = frameBlockZ;
        	}
        	
        	//System.out.println(portalDirection.getFrontOffsetX() + " - " + portalDirection.getFrontOffsetZ());
        	
        	HashMap<Integer, ChunkCoordinates> lookupHashTraversed = new HashMap<Integer, ChunkCoordinates>();
        	List<ChunkCoordinates> listPosOpenSpots = new ArrayList<ChunkCoordinates>();
        	
        	ChunkCoordinates startPos = new ChunkCoordinates(frameBlockX, frameBlockY/* + 1*/, frameBlockZ);
        	
        	listPosOpenSpots.add(startPos);
        	lookupHashTraversed.put(getHashCode(startPos), startPos);
        	
        	int pathOptions = 1;
        	while (pathOptions > 0) {
        		if (!validPortal) break;
        		pathOptions = 0;
        		for (int i = 0; i < listPosOpenSpots.size(); i++) {
        			if (!validPortal) break;
        			ChunkCoordinates pos = listPosOpenSpots.get(i);
        			//System.out.println("for " + pos.posX + ", " + pos.posY + ", " + pos.posZ);
        			//traverse x unless direction makes it 0
        			int x = 0;
        			int y = 0;
        			int z = 0;
        			
        			for (int xx = /*pos.posX */- (1 * portalDirection.getFrontOffsetX()); xx <= /*pos.posX*/ + (1 * portalDirection.getFrontOffsetX()); xx++) {
        				if (!validPortal) break;
        				//traverse z unless direction makes it 0
        				for (int zz = /*pos.posZ*/ - (1 * portalDirection.getFrontOffsetZ()); zz <= /*pos.posZ*/ + (1 * portalDirection.getFrontOffsetZ()); zz++) {
        					if (!validPortal) break;
        					
        					for (int yy = /*pos.posY*/ - 1; yy <= /*pos.posY*/ + 1; yy++) {
        						if (!validPortal) break;
        						
        						//if ((yy == -1 && yy == 1) && ()) {
        						//test z axis
        						if (portalDirection.getFrontOffsetX() == 0) {
        							if (yy != 0 && zz != 0) continue;
        						//test x axis
        						} else if (portalDirection.getFrontOffsetZ() == 0) {
        							if (yy != 0 && xx != 0) continue;
        						}
        						
        						
        						x = pos.posX + xx;
        						y = pos.posY + yy;
        						z = pos.posZ + zz;
        						
        						ChunkCoordinates tryPos = new ChunkCoordinates(x, y, z);
        						//System.out.println("explore " + tryPos.posX + ", " + tryPos.posY + ", " + tryPos.posZ);
        						int id = mcWorld.getBlockId(tryPos.posX, tryPos.posY, tryPos.posZ);
        						if (id == 0 || id == Block.fire.blockID || id == portalBlock.blockID) {
        							if (!lookupHashTraversed.containsKey(getHashCode(tryPos))) {
        								
        								//System.out.println("add " + tryPos.posX + ", " + tryPos.posY + ", " + tryPos.posZ + ", id " + id);
        								pathOptions++;
        								listPosOpenSpots.add(tryPos);
        								lookupHashTraversed.put(getHashCode(tryPos), tryPos);
        								
        								if (portalDirection == EnumFacing.WEST) {
        									if (tryPos.posX < minLength) {
        										minLength = tryPos.posX;
        									}
        									
        									if (tryPos.posX > maxLength) {
        										maxLength = tryPos.posX;
        									}
        								} else {
        									if (tryPos.posZ < minLength) {
        										minLength = tryPos.posZ;
        									}
        									
        									if (tryPos.posZ > maxLength) {
        										maxLength = tryPos.posZ;
        									}
        								}
    									
    									if (tryPos.posY < minHeight) {
    										minHeight = tryPos.posY;
    									}
    									
    									if (tryPos.posY > maxHeight) {
    										maxHeight = tryPos.posY;
    									}
    									
    									//validate length and bail out if needed
    									if (maxLength - minLength > maxLengthHeight) {
    										//return false;
    										validPortal = false;
    									}
    									if (maxHeight - minHeight > maxLengthHeight) {
    										//return false;
    										validPortal = false;
    									}
        							}
        						} else if (mcWorld.getBlockId(tryPos.posX, tryPos.posY, tryPos.posZ) != frameBlock.blockID) {
        							//return false;
        							validPortal = false;
        						}
        						
        					}
        					
        				}
        			}
        		}
        	}
        	
        	//step 2, strict validation for recipe shape (focused on frame shape or full recipe? if full, account for skipping fire/air in validation)
        	if (validPortal) {
        	
				boolean foundMatch = false;
				for (int tryRotation = 0; tryRotation < 2 && !foundMatch; tryRotation++) {
	        		for (int tryY = 0; tryY < portal.structureData.structureHeight && !foundMatch; tryY++) {
						for (int tryZ = 0; tryZ < portal.structureData.structureDepth && !foundMatch; tryZ++) {
							//String out = "";
							for (int tryX = 0; tryX < portal.structureData.structureWidth && !foundMatch; tryX++) {
								boolean failed = false;
								int frameBlocksInRecipe = 0;
								int frameBlocksFound = 0;
				        		for (int yy = 0; yy < portal.structureData.structureHeight && !failed; yy++) {
									for (int zz = 0; zz < portal.structureData.structureDepth && !failed; zz++) {
										//String out = "";
										for (int xx = 0; xx < portal.structureData.structureWidth && !failed; xx++) {
											
											//out += portal.structureData.verifyDataBlock[xx][yy][zz] + ", ";
											int blockID = 0;//world.getBlockId(frameBlockX - tryX + xx, frameBlockY - tryY + yy, frameBlockZ - tryZ + zz);
											int blockIDRecipe = 0;
											blockIDRecipe = portal.structureData.verifyDataBlock[xx][yy][zz];
											
											if (tryRotation == 0) {
												blockID = world.getBlockId(frameBlockX - tryX + xx, frameBlockY - tryY + yy, frameBlockZ - tryZ + zz);
												//blockIDRecipe = portal.structureData.verifyDataBlock[xx][yy][zz];
											} else if (tryRotation == 1) {
												blockID = world.getBlockId(frameBlockX - tryZ + zz, frameBlockY - tryY + yy, frameBlockZ - tryX + xx);
												//blockIDRecipe = portal.structureData.verifyDataBlock[zz][yy][xx];
											}
											
											//replace built in scaffolding portal with portal blocks student is checking against
											if (portal == ModAPI.ydPortalTemplate) {
												if (blockIDRecipe == ModAPI.ydPortalTemplateFrame.blockID) { 
													blockIDRecipe = frameBlock.blockID;
												}
											}
											
											//only validate frame blocks for now
											if (blockIDRecipe == frameBlock.blockID) {
												frameBlocksInRecipe++;
												if (blockIDRecipe != blockID) {
													failed = true;
												}
											}
											
											if (blockID == frameBlock.blockID) {
												frameBlocksFound++;
											}
										}
										//System.out.println(out);
									}
									//System.out.println(" ");
								}
								
								System.out.println("recipe vs world: " + frameBlocksInRecipe + " : " + frameBlocksFound);
								
								if (!failed) {
									
									if (frameBlocksInRecipe == frameBlocksFound) {
										foundMatch = true;
									}
								}
							}
						}
	        		}
				}
	        	
        		if (!foundMatch) {
        			validPortal = false;
        		}
        		
        	}
        	
        	System.out.println("air blocks found: " + lookupHashTraversed.size());
        	System.out.println("portal valid? " + validPortal);
        	
        	if (validPortal) {
	        	if (!onlyBreakIfInvalid) {
		        	Iterator iter = lookupHashTraversed.entrySet().iterator();
		        	while (iter.hasNext()) {
		        		Map.Entry<Integer, ChunkCoordinates> entrySet = (Entry<Integer, ChunkCoordinates>) iter.next();
		        		
		        		/*IBlockState state = null;
		        		if (portalDirection == EnumFacing.EAST) {
		            		state = ((BlockTemplatePortal)portalBlock.getBlock()).getBlockState().getBaseState().withProperty(BlockTemplatePortal.AXIS, EnumFacing.Axis.X);
		            	} else {
		            		state = ((BlockTemplatePortal)portalBlock.getBlock()).getBlockState().getBaseState().withProperty(BlockTemplatePortal.AXIS, EnumFacing.Axis.Z);
		            	}*/
		            	
		            	
		            	ChunkCoordinates coords = entrySet.getValue();
		            	if (portalDirection == EnumFacing.WEST) {
		            		mcWorld.setBlock(coords.posX, coords.posY, coords.posZ, portalBlock.blockID, 0, 2);
		            	} else {
		            		mcWorld.setBlock(coords.posX, coords.posY, coords.posZ, portalBlock.blockID, 1, 2);
		            	}
		            	
		                //mcWorld.setBlockState(entrySet.getValue(), state, 2);
		        	}
	        	}
        	} else {
        		Iterator iter = lookupHashTraversed.entrySet().iterator();
	        	while (iter.hasNext()) {
	        		Map.Entry<Integer, ChunkCoordinates> entrySet = (Entry<Integer, ChunkCoordinates>) iter.next();
	        		
	        		//remove all portal blocks we found, but nothing else
	        		ChunkCoordinates coords = entrySet.getValue();
	        		if (mcWorld.getBlockId(coords.posX, coords.posY, coords.posZ) == portalBlock.blockID) {
	        			mcWorld.setBlock(coords.posX, coords.posY, coords.posZ, 0, 0, 2);
	        		}
	        	}
        	}
        }
        
        return false;
	}
	
	public static int getHashCode(ChunkCoordinates coords) {
		return (coords.posY + coords.posZ * 31) * 31 + coords.posX;
	}

}
