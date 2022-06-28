package mytroublemod.tsconfig.troubleapi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import mytroublemod.tsconfig.troubleapi.ModAPI;
import mytroublemod.tsconfig.troubleapi.Structure;
import mytroublemod.tsconfig.troubleapi.dimension.ModBiome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureHelper {

	public static class StructureData {
    
        public int structureWidth;
        public int structureHeight;
        public int structureDepth;  
        public Object[] structureBlocks;
        public int[] structureBlockMetaData;
        
        public int[][][] verifyDataBlock;
        public int[][][] verifyDataMeta;
        
        //list of chests with list of items to spawn in that chest list entry
        public List<List<ItemStack>> chestContentsList = new ArrayList<List<ItemStack>>();
        public List<Integer> blockIDsSkipFirstPass = new ArrayList<Integer>();
        
        public Structure structure;
        
        public StructureData(Structure structure) {
        	this.structure = structure;
            blockIDsSkipFirstPass = new ArrayList<Integer>();
            blockIDsSkipFirstPass.add(Block.tnt.blockID);
            blockIDsSkipFirstPass.add(Block.redstoneRepeaterActive.blockID);
            blockIDsSkipFirstPass.add(Block.redstoneRepeaterIdle.blockID);
            blockIDsSkipFirstPass.add(Block.redstoneWire.blockID);
            blockIDsSkipFirstPass.add(Block.torchRedstoneActive.blockID);
            blockIDsSkipFirstPass.add(Block.torchRedstoneIdle.blockID);
            blockIDsSkipFirstPass.add(Block.torchWood.blockID);
            blockIDsSkipFirstPass.add(Block.fire.blockID);
            blockIDsSkipFirstPass.add(Block.pressurePlateGold.blockID);
            blockIDsSkipFirstPass.add(Block.pressurePlateIron.blockID);
            blockIDsSkipFirstPass.add(Block.pressurePlatePlanks.blockID);
            blockIDsSkipFirstPass.add(Block.pressurePlateStone.blockID);
            blockIDsSkipFirstPass.add(Block.doorIron.blockID);
            blockIDsSkipFirstPass.add(Block.doorWood.blockID);
            blockIDsSkipFirstPass.add(Block.plantYellow.blockID);
            blockIDsSkipFirstPass.add(Block.plantRed.blockID);
            blockIDsSkipFirstPass.add(Block.ladder.blockID);
            blockIDsSkipFirstPass.add(Block.crops.blockID);
            blockIDsSkipFirstPass.add(Block.potato.blockID);
            blockIDsSkipFirstPass.add(Block.carrot.blockID);
            blockIDsSkipFirstPass.add(Block.bed.blockID);
        }
        
        public void generateArrayData() {
	        try {
				verifyDataBlock = new int[structureWidth][structureHeight][structureDepth];
	        	verifyDataMeta = new int[structureWidth][structureHeight][structureDepth];
	        	
	        	int maxDepthArea = structureBlocks.length / structureDepth;
	            int i = 0;
	            int maxHeightLevel = structureBlocks.length / structureWidth / structureDepth;
	            int k = maxHeightLevel;
	            //int w = 0;  
	            int X = 0;
	            int Y = 0;
	            int Z = 0;
	            
	            int dir = 0;
	            
	            int direction;
	            boolean mirrorFix = false;
	            
	            if(dir == 1 || dir == 3) {
	                direction = dir == 3 ? 1 : -1;
	            } else {
	                direction = dir == 0 ? 1 : -1;
	            }
	            
	            i = 0;
	            int w = 0;
	            
	            //???
	            int offsetFixDepth = 0;
	            int offsetFixWidth = 0;
	            boolean offsetMode = false;
	        
	            for(int d = 1; d < structureDepth + 1; d++)
	            {
	                for(int l = i; l >= maxDepthArea * (d - 1) && l < maxDepthArea * (d); l++)
	                {
	                    
	                    int curHeightLevel = l / structureWidth;
	                    int j = curHeightLevel;
	                    
	                    int xx, yy, zz;
	                    
	                    //System.out.println("direction: " + direction + " - dir: " + dir);
	                    
	                    yy = Y + k - j + w + ((structureHeight - structureWidth) * (d - 1));
	                    if(dir == 1 || dir == 3) {
	                        xx = X + (direction * (d - 1 + offsetFixDepth));
	                        zz = Z + l + offsetFixWidth + (offsetMode ? 2 : 0) - (j * structureWidth) - (structureWidth / 2); //added +2
	                    } else {
	                        if (mirrorFix) {
	                            xx = X + (-1 * (l + offsetFixDepth - (j * structureWidth) - (structureWidth / 2)));
	                        } else {
	                            xx = X + l + offsetFixDepth - (j * structureWidth)/* - (structureWidth / 2)*/;
	                        }
	                        zz = Z + (direction * (d - (offsetMode ? 2 : 1) + offsetFixWidth));
	                    }
	                    
	                    Object obj = structureBlocks[l];
	                    if (obj instanceof Block) {
		                    try {
								verifyDataBlock[xx][yy-1][zz] = ((Block) obj).blockID;
								verifyDataMeta[xx][yy-1][zz] = structureBlockMetaData[l];
							} catch (Exception e) {
								//System.out.println(e.getLocalizedMessage());
								e.printStackTrace();
							}
	                    }
	                    
	                    i++;
	                }
	                w = i / structureHeight;
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//debug output array
			/*for (int yy = 0; yy < structureHeight; yy++) {
				for (int zz = 0; zz < structureDepth; zz++) {
					String out = "";
					for (int xx = 0; xx < structureWidth; xx++) {
						out += verifyDataBlock[xx][yy][zz] + ", ";
					}
					System.out.println(out);
				}
				System.out.println(" ");
			}*/
        }
        
        public void generateStructure(Random random, int xStart, int yStart, int zStart, World world, int parCoordBaseMode)
        {
            generateStructure(random, xStart, yStart, zStart, world, parCoordBaseMode, true, null);
        }
        
        public void generateStructure(Random random, int xStart, int yStart, int zStart, World world, int parCoordBaseMode, boolean offsetMode, Block scaffoldingReplacement)
        {
        
        
        	int ID = world.getBlockId(xStart, yStart, zStart);
        	if (ID != 0 && Block.blocksList[ID].isBlockReplaceable(world, xStart, yStart, zStart)) {
        		yStart--;
        	}
        
        	HashMap<Integer, ChunkCoordinates> lookupXZToHeight = new HashMap<Integer, ChunkCoordinates>();
        	int lowestOverallHeight = 255;
        	
            ModAPI.addStructureEntry(world, xStart, yStart, zStart);
            
            //this helped for a building of a set size, now fix it so it works for all sizes, make other classes not center up first
            /*int offsetFixDepth = -5;
            int offsetFixWidth = -1;
            
            offsetFixWidth = -structureWidth/2;
            offsetFixDepth = -structureDepth/2;
            
            if (!offsetMode) {
                offsetFixWidth = 0;
                offsetFixDepth = 0;
            }*/
            
            //rework way it fixes centering
            int offsetFixWidth = 0;
            int offsetFixDepth = 0;
            
            //1 and 3 are off for z centering, shift it -z, half structure width
            if (parCoordBaseMode == 1 || parCoordBaseMode == 3) {
            	zStart -= structureWidth/2;
            }
            
            int dir = parCoordBaseMode;
            int curChestToFill = 0;
            
            int curPass = 0;
            int lastPass = 1;
            
            if (!world.isRemote) 
            {
                int maxDepthArea = structureBlocks.length / structureDepth;
                int i = 0;
                int maxHeightLevel = structureBlocks.length / structureWidth / structureDepth;
                int k = maxHeightLevel;
                //int w = 0;  
                int X = xStart;
                int Y = yStart;
                int Z = zStart;
                
                int direction;
                boolean mirrorFix = false;
                
                if(dir == 1 || dir == 3) {
                    direction = dir == 3 ? 1 : -1;
                } else {
                    direction = dir == 0 ? 1 : -1;
                }
                
                /*if (dir == 0 || dir == 1) {
                    mirrorFix = true;
                }*/
                
                while (curPass <= lastPass) {
                
                    i = 0;
                    int w = 0;
                
                    for(int d = 1; d < structureDepth + 1; d++)
                    {
                        for(int l = i; l >= maxDepthArea * (d - 1) && l < maxDepthArea * (d); l++)
                        {
                            
                            int curHeightLevel = l / structureWidth;
                            int j = curHeightLevel;
                            
                            int xx, yy, zz;
                            
                            //System.out.println("direction: " + direction + " - dir: " + dir);
                            
                            yy = Y + k - j + w + ((structureHeight - structureWidth) * (d - 1));
                            if(dir == 1 || dir == 3) {
                                xx = X + (direction * (d - 1 + offsetFixDepth));
                                zz = Z + l + offsetFixWidth + (offsetMode ? 2 : 0) - (j * structureWidth) - (structureWidth / 2); //added +2
                            } else {
                                if (mirrorFix) {
                                    xx = X + (-1 * (l + offsetFixDepth - (j * structureWidth) - (structureWidth / 2)));
                                } else {
                                    xx = X + l + offsetFixDepth - (j * structureWidth) - (structureWidth / 2);
                                }
                                zz = Z + (direction * (d - (offsetMode ? 2 : 1) + offsetFixWidth));
                            }
                            
                            if (!world.checkChunksExist(xx, 0, zz, xx, 255, zz)) {
                                return;
                            }
                                
                            if(structureBlocks[l] != null)
                            {
                                
                                if (structureBlocks[l] instanceof Block) {
                                    
                                    //replace scaffolding block with students replacement
                                    Block block = (Block) structureBlocks[l];
                                    if (scaffoldingReplacement != null) {
                                    	if (block == ModAPI.ydPortalTemplateFrame) {
                                    		block = scaffoldingReplacement;
                                    	}
                                    }
                                    
                                    if ((curPass == 0 && !blockIDsSkipFirstPass.contains(block.blockID)) || (curPass == 1 && blockIDsSkipFirstPass.contains(block.blockID))) {
                                    
                                    	//see if this block is lowest height for this XZ coord
                                    	int coordHash = makeHash(xx, zz);
                                    	int curHeight = yy;
                                    	if (lookupXZToHeight.containsKey(coordHash)) {
                                    		ChunkCoordinates lowestHeight = lookupXZToHeight.get(coordHash);
                                    		if (curHeight < lowestHeight.posY) {
                                    			lookupXZToHeight.put(coordHash, new ChunkCoordinates(xx, curHeight, zz));
                                    		}
                                    	} else {
                                    		lookupXZToHeight.put(coordHash, new ChunkCoordinates(xx, curHeight, zz));
                                    	}
                                    	if (yy < lowestOverallHeight) {
                                    		lowestOverallHeight = yy;
                                    	}
                                    
                                        if(block.blockID == Block.doorWood.blockID || block.blockID == Block.doorIron.blockID)
                                        {
                                            if(block.blockID == Block.doorWood.blockID)
                                            ItemDoor.placeDoorBlock(world, xx, yy, zz, dir, Block.doorWood);
                                            if(block.blockID == Block.doorIron.blockID)
                                            ItemDoor.placeDoorBlock(world, xx, yy, zz, dir, Block.doorIron);
                                        } else if (block.blockID == Block.bed.blockID) {
								            BlockBed blockbed = (BlockBed)Block.bed;
								            int curMeta = structureBlockMetaData[l];
                                            int fixedMeta = getMetadataWithOffset(block.blockID, curMeta, parCoordBaseMode);
								            int i1 = fixedMeta;//MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
								            byte b0 = 0;
								            byte b1 = 0;
								
								            if (i1 == 0)
								            {
								                b1 = 1;
								            }
								
								            if (i1 == 1)
								            {
								                b0 = -1;
								            }
								
								            if (i1 == 2)
								            {
								                b1 = -1;
								            }
								
								            if (i1 == 3)
								            {
								                b0 = 1;
								            }
								
								            world.setBlock(xx, yy, zz, blockbed.blockID, i1, 3);
								
						                    if (world.getBlockId(xx, yy, zz) == blockbed.blockID)
						                    {
						                        world.setBlock(xx + b0, yy, zz + b1, blockbed.blockID, i1 + 8, 3);
						                    }
                                        }
                                        else
                                        {
                                            int curMeta = structureBlockMetaData[l];
                                            int fixedMeta = getMetadataWithOffset(block.blockID, curMeta, parCoordBaseMode);
                                            
                                            if(j * structureWidth <= l && l < (j + 1) * structureWidth) {
                                            	world.setBlock(xx, yy, zz, block.blockID, fixedMeta, 3);
                                            }
                                            
                                            //reforce metadata values on tile entity based blocks, so far chests and furnaces need this
                                            if (world.getBlockTileEntity(xx, yy, zz) instanceof TileEntity) {
                                                int checkMeta = world.getBlockMetadata(xx, yy, zz);
                                                world.setBlockMetadataWithNotify(xx, yy, zz, fixedMeta, 3);
                                            }
                                            
                                            if (world.getBlockTileEntity(xx, yy, zz) instanceof TileEntityChest) {
                                                
                                                TileEntityChest chest = (TileEntityChest) world.getBlockTileEntity(xx, yy, zz);
                                                
                                                try {
                                                    List<ItemStack> listStacks = chestContentsList.get(curChestToFill++);
                                                    
                                                    for (ItemStack item : listStacks)
                                                    {
                                                        chest.setInventorySlotContents(world.rand.nextInt(chest.getSizeInventory()), item);
                                                    }
                                                } catch (Exception ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                            
                                        }
                                    }
                                } else if (structureBlocks[l] instanceof MyRecipeSpawnBlock) {
                                    if (curPass == 1) {
                                        //get spawn datas
                                        MyRecipeSpawnBlock block = (MyRecipeSpawnBlock) structureBlocks[l];
                                        
                                        if(j * structureWidth <= l && l < (j + 1) * structureWidth) {
                                            spawnMobs(world, xx, yy, zz, block.numberToSpawn, block.mobToSpawn);
                                        }
                                    }
                                }
                            } else {
                            	//set non allocated coordinates to air
                            	if (curPass == 0) {
                            		world.setBlock(xx, yy, zz, 0, 0, 3);
                            	}
                            }
                            i++;
                        }
                        w = i / structureHeight;
                    }
                    curPass++;
                }
                
                if (structure.isUsingScaffolding()) {
	                Iterator<Entry<Integer, ChunkCoordinates>> it = lookupXZToHeight.entrySet().iterator();
	                while (it.hasNext()) {
	                	Map.Entry<Integer, ChunkCoordinates> entry = it.next();
	                	
	                	//if this point is one that reached lowest, we will put scaffolding under it
	                	if (entry.getValue().posY == lowestOverallHeight) {
	                		ChunkCoordinates coords = entry.getValue();
	                		if (world.getBlockId(coords.posX, coords.posY - 1, coords.posZ) == 0) {
	                		
	                			int startY = coords.posY - 1;
	                			int curY = startY;
	                			BiomeGenBase biome = world.getBiomeGenForCoords(coords.posX, coords.posZ);
	                			List<Integer> listBlocksToFind = new ArrayList<Integer>();
	                			listBlocksToFind.add(ModBiome.getIDFixed(biome.topBlock));
	                			listBlocksToFind.add(ModBiome.getIDFixed(biome.fillerBlock));
	                			if (biome instanceof ModBiome) {
	                				listBlocksToFind.add(((ModBiome)biome).getBlockUnderground());
	                			}
	                			listBlocksToFind.add(Block.stone.blockID);
	                			listBlocksToFind.add(Block.grass.blockID);
	                			listBlocksToFind.add(Block.dirt.blockID);
	                			listBlocksToFind.add(Block.sand.blockID);
	                			listBlocksToFind.add(Block.gravel.blockID);
	                			listBlocksToFind.add(Block.sandStone.blockID);
								while (curY >= 0 && !listBlocksToFind.contains(world.getBlockId(coords.posX, curY, coords.posZ))) {
									curY--;
								}
								
								int foundBlock = world.getBlockId(coords.posX, curY, coords.posZ);
								if (foundBlock == Block.grass.blockID) {
									foundBlock = Block.dirt.blockID;
								}
								
								for (int yy = curY + 1; yy <= startY; yy++) {
									world.setBlock(coords.posX, yy, coords.posZ, foundBlock);
								}
	                			
	                		}
	                	}
	                }
                }
            }
        }
        
        protected void spawnMobs(World par1World, int par3, int par4, int par5, int par6, Class mobToSpawn)
        {
            int mobsSpawned = 0;
            if (mobsSpawned < par6)
            {
                for (int i1 = mobsSpawned; i1 < par6; ++i1)
                {
                    int j1 = par3;
                    int k1 = par4;
                    int l1 = par5;
    
                    ++mobsSpawned;
                    try
                    {
                        EntityLiving entityliving = (EntityLiving)mobToSpawn.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
                        entityliving.setLocationAndAngles((double)j1 + 0.5D, (double)k1, (double)l1 + 0.5D, 0.0F, 0.0F);
                        entityliving.onSpawnWithEgg(null);
                        
                        //does not help
                        /*try {
							ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, entityliving, -5000, "field_70708_bq", "entityAge");
						} catch (Exception e) {
							e.printStackTrace();
						}*/
						
						//make all mobs persistant, if performance issues, add flag to disable this later on
						entityliving.func_110163_bv();
                        
                        par1World.spawnEntityInWorld(entityliving);
                    }
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static StructureData setStructureShape(Structure structure, /*int depth, */Object... par1ArrayOfObj)
    {
        
        //chestContentsList.clear();
        StructureData newData = new StructureData(structure);
        
        String s = "";
        int i = 0;
        int sWidthLargest = 0;
        int sH = 0;
        int sD = 0;//depth;
        
        for (Object obj : par1ArrayOfObj) {
        	if (obj instanceof String) {
        		if (obj.equals(Structure.SLICE)) {
        			sD++;
        		}
        	}
        }
        
        //automatically add 1 layer now that end of recipe slice wont be used
        sD++;
        
        //first pass find longest string to define as width
        while (par1ArrayOfObj[i] instanceof String)
        {
        	if (par1ArrayOfObj[i].equals(Structure.SLICE)) {
        		i++;
        		continue;
        	}
            String s2 = (String)par1ArrayOfObj[i++];
            if (s2.length() > sWidthLargest) sWidthLargest = s2.length();
        }
        
        i = 0;
        
        //second pass, iterate string entries, add onto 1 long string + whitespace to match length of longest string
        while (par1ArrayOfObj[i] instanceof String)
        {
        	if (par1ArrayOfObj[i].equals(Structure.SLICE)) {
        		i++;
        		continue;
        	}
            String s2 = (String)par1ArrayOfObj[i++];
            ++sH;
            s = s + s2;
            for (int ii = s2.length(); ii < sWidthLargest; ii++) {
                s += " ";
            }
        }
    
        HashMap hashmapBlock = new HashMap();
        HashMap hashmapBlockMeta = new HashMap();
    
        //???
        while (par1ArrayOfObj[i] instanceof String) i++;
        
        /**iterate symbol to block entries, factoring in a new third entry, symbol to block metadata
         * so each entry can be either:
         * 1: String, Block
         * 2: String, Block, int
         * 
         * how to factor in chest and mob spawner entries????????
         */
        try {
            while (i < par1ArrayOfObj.length) {
            
                Character character = (Character)par1ArrayOfObj[i];
                
                if (par1ArrayOfObj[i + 1] instanceof Block) {
                    Block block = (Block)par1ArrayOfObj[i + 1];
                    hashmapBlock.put(character, block);
                    
                    //check for metadata entry
                    boolean metaEntry = false;
                    boolean updateIndex = !(block instanceof BlockChest);
                    
                    //if not going past end of array
                    if (i + 2 <= par1ArrayOfObj.length-1) {
                        Object obj = par1ArrayOfObj[i + 2];
                        
                        //if detected metadata integer
                        if (obj instanceof Integer) {
                        
                            if (block instanceof BlockLadder) {
                                int test = 0;
                                test++;
                            }
                        
                            int meta = (Integer)obj;
                            //rotate block from default north to whats specified in recipe, in theory wont interfere with non rotation metadata as method wont do anything to those blocks
                            meta = convertDirectionToMetaAndRotate(block, meta);
                            hashmapBlockMeta.put(character, meta);
                            if (updateIndex) i += 3;
                            metaEntry = true;
                        }
                    }
                    
                    //fill in default meta if last entry is past array size (no meta) or if no entry 
                    if (!metaEntry) {
                        hashmapBlockMeta.put(character, 0);
                        if (updateIndex) i += 2;
                    }
                    
                    if (block instanceof BlockChest) {
                        
                        //jump to block entry
                        if (metaEntry) {
                            i+=2;
                        } else {
                            i++;
                        }
                    
                        List<ItemStack> listStacks = new ArrayList<ItemStack>();
                        int stackCount = 0;
                        //find out how many itemstacks we have to add
                        
                        //check ahead for itemstack and length limit, work with stack, end when no more found
                        while (i + 1 <= par1ArrayOfObj.length-1 && par1ArrayOfObj[i + 1] instanceof ItemStack) {
                            ItemStack stack = (ItemStack) par1ArrayOfObj[i + 1];
                            listStacks.add(stack);
                            
                            //jump to next stack
                            i++;
                        }
                        
                        //jump to next index that could be another stack, or next mapping
                        i++;
                        
                        newData.chestContentsList.add(listStacks);
                    } else {
                        
                    }
                } else if (Entity.class.isAssignableFrom(((Class)par1ArrayOfObj[i + 1]))) {
                    hashmapBlock.put(character, new MyRecipeSpawnBlock(((Class)par1ArrayOfObj[i + 1]), ((Integer)par1ArrayOfObj[i + 2])));
                    
                    i += 3;
                }
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
        Object[] blocks = new Object[sWidthLargest * sH];
        int[] meta = new int[sWidthLargest * sH];
    
        for (int i1 = 0; i1 < sWidthLargest * sH; ++i1)
        {
            char c0 = s.charAt(i1);
    
            if (hashmapBlock.containsKey(Character.valueOf(c0)))
            {
                Object obj = hashmapBlock.get(Character.valueOf(c0));
                if (obj instanceof MyRecipeSpawnBlock/* || obj instanceof BlockChest*/) {
                    meta[i1] = 0;
                } else {
                    try {
                        meta[i1] = ((Integer)hashmapBlockMeta.get(Character.valueOf(c0)));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                blocks[i1] = obj;
                
            }
            else
            {
                blocks[i1] = null;
                meta[i1] = 0;
            }
        }
        
        sH /= sD;
        
        newData.structureWidth = sWidthLargest;
        newData.structureHeight = sH;
        newData.structureDepth = sD;
        newData.structureBlocks = blocks;
        newData.structureBlockMetaData = meta;
        
        newData.generateArrayData();
        
        return newData;
    }
    
    /** Converting from north being '2' to what a block metadata uses for north, actually all blocks use north as 2 amazingly, this method is moot */
    public static int convertDirectionToMetaAndRotate(Block parBlock, int parNewRotation) {
        int meta = 2;
        if (parBlock instanceof BlockTorch) {
            meta = 4;
        }/* else if (parBlock instanceof BlockChest || parBlock instanceof BlockFurnace) {
            meta = 3;
        }*/
        
        return rotateBlock(parBlock.blockID, meta, parNewRotation);
    }
    
    public static int rotateBlock(Block parBlock, int parNewRotation) {
        return getMetadataWithOffset(parBlock.blockID, 2, parNewRotation);
    }
    
    public static int rotateBlock(int parBlock, int parMeta, int parNewRotation) {
        return getMetadataWithOffset(parBlock, parMeta, parNewRotation);
    }
    
    /** Note, SOUTH and WEST generate mirrored, original code factors this in, this suggests that vanilla buildings also generate mirrored */
    public static int getMetadataWithOffset(int par1, int parCurMeta, int parNewRotation)
    {
    
        //when adding new blocks, to account for mirror effect:
        //- for south, just account for north<->south
        //- for west, make original east and west go the other way
        //- east is correct, north is default
        
        //chests and furnaces need their data inverted for some reason, and still fixed with notes above
        
    
        if (par1 == Block.rail.blockID)
        {
            if (parNewRotation == 1 || parNewRotation == 3)
            {
                if (parCurMeta == 1)
                {
                    return 0;
                }

                return 1;
            }
        }
        else if (Block.blocksList[par1] instanceof BlockTorch) {
            if (parNewRotation == 0) {
                /*if (parCurMeta == 1) {
                    return 2;
                } else */if (parCurMeta == 4) {
                    return 3;
                /*} else if (parCurMeta == 2) {
                    return 1;*/
                } else if (parCurMeta == 3) {
                    return 4;
                }
            } else if (parNewRotation == 1) {
                if (parCurMeta == 1) {
                    return /*4*/3;
                } else if (parCurMeta == 4) {
                    return 2;
                } else if (parCurMeta == 2) {
                    return /*3*/4;
                } else if (parCurMeta == 3) {
                    return 1;
                }
            } else if (parNewRotation == 3) {
                if (parCurMeta == 1) {
                    return 3;
                } else if (parCurMeta == 4) {
                    return 1;
                } else if (parCurMeta == 2) {
                    return 4;
                } else if (parCurMeta == 3) {
                    return 2;
                }
            }
        }
        else if (Block.blocksList[par1] instanceof BlockLadder) {
            if (parNewRotation == 0) {
                if (parCurMeta == 3) {
                    return 2;
                /*} else if (parCurMeta == 4) {
                    return 5;*/
                } else if (parCurMeta == 2) {
                    return 3;
                }/* else if (parCurMeta == 5) {
                    return 4;
                }*/
            } else if (parNewRotation == 1) {
                if (parCurMeta == 3) {
                    return 5;
                } else if (parCurMeta == 4) {
                    return /*3*/2;
                } else if (parCurMeta == 2) {
                    return 4;
                } else if (parCurMeta == 5) {
                    return /*2*/3;
                }
            } else if (parNewRotation == 3) {
                if (parCurMeta == 3) {
                    return 4;
                } else if (parCurMeta == 4) {
                    return 2;
                } else if (parCurMeta == 2) {
                    return 5;
                } else if (parCurMeta == 5) {
                    return 3;
                }
            }
        }
        else if ((Block.blocksList[par1] instanceof BlockChest) || (Block.blocksList[par1] instanceof BlockFurnace)) {
            /*if (parNewRotation == 0)
            {
                if (parCurMeta == 3) {
                    return 2;
                //} else if (parCurMeta == 4) {
                    //return 5;
                } else if (parCurMeta == 2) {
                    return 3;
                //} else if (parCurMeta == 5) {
                    //return 4;
                }
            }
            else if (parNewRotation == 1)
            {
                if (parCurMeta == 3) {
                    return 5;
                } else if (parCurMeta == 4) {
                    return 2;//3
                } else if (parCurMeta == 2) {
                    return 4;
                } else if (parCurMeta == 5) {
                    return 3;//2
                }
            }
            else if (parNewRotation == 3)
            {
                if (parCurMeta == 3) {
                    return 4;
                } else if (parCurMeta == 4) {
                    return 2;
                } else if (parCurMeta == 2) {
                    return 5;
                } else if (parCurMeta == 5) {
                    return 3;
                }
            }*/
            if (parNewRotation == 0)
            {
                if (parCurMeta == 3) {
                    return 2;
                //} else if (parCurMeta == 4) {
                    //return 5;
                } else if (parCurMeta == 2) {
                    return 3;
                //} else if (parCurMeta == 5) {
                    //return 4;
                }
            }
            else if (parNewRotation == 1)
            {
                if (parCurMeta == 3) {
                    return 5;
                } else if (parCurMeta == 4) {
                    return 2;//3;
                } else if (parCurMeta == 2) {
                    return 4;
                } else if (parCurMeta == 5) {
                    return 3;//2;
                }
            }
            else if (parNewRotation == 3)
            {
                if (parCurMeta == 3) {
                    return 4;
                } else if (parCurMeta == 4) {
                    return 2;
                } else if (parCurMeta == 2) {
                    return 5;
                } else if (parCurMeta == 5) {
                    return 3;
                }
            }
        }
        else if (par1 != Block.doorWood.blockID && par1 != Block.doorIron.blockID)
        {
            if (!(Block.blocksList[par1] instanceof BlockStairs))
            {
                if (par1 == Block.ladder.blockID)
                {
                    if (parNewRotation == 0)
                    {
                        if (parCurMeta == 2)
                        {
                            return 3;
                        }

                        if (parCurMeta == 3)
                        {
                            return 2;
                        }
                    }
                    else if (parNewRotation == 1)
                    {
                        if (parCurMeta == 2)
                        {
                            return 4;
                        }

                        if (parCurMeta == 3)
                        {
                            return 5;
                        }

                        if (parCurMeta == 4)
                        {
                            return 2;
                        }

                        if (parCurMeta == 5)
                        {
                            return 3;
                        }
                    }
                    else if (parNewRotation == 3)
                    {
                        if (parCurMeta == 2)
                        {
                            return 5;
                        }

                        if (parCurMeta == 3)
                        {
                            return 4;
                        }

                        if (parCurMeta == 4)
                        {
                            return 2;
                        }

                        if (parCurMeta == 5)
                        {
                            return 3;
                        }
                    }
                }
                else if (par1 == Block.stoneButton.blockID)
                {
                    if (parNewRotation == 0)
                    {
                        if (parCurMeta == 3)
                        {
                            return 4;
                        }

                        if (parCurMeta == 4)
                        {
                            return 3;
                        }
                    }
                    else if (parNewRotation == 1)
                    {
                        if (parCurMeta == 3)
                        {
                            return 1;
                        }

                        if (parCurMeta == 4)
                        {
                            return 2;
                        }

                        if (parCurMeta == 2)
                        {
                            return 3;
                        }

                        if (parCurMeta == 1)
                        {
                            return 4;
                        }
                    }
                    else if (parNewRotation == 3)
                    {
                        if (parCurMeta == 3)
                        {
                            return 2;
                        }

                        if (parCurMeta == 4)
                        {
                            return 1;
                        }

                        if (parCurMeta == 2)
                        {
                            return 3;
                        }

                        if (parCurMeta == 1)
                        {
                            return 4;
                        }
                    }
                }
                else if (par1 != Block.tripWireSource.blockID && (Block.blocksList[par1] == null || !(Block.blocksList[par1] instanceof BlockDirectional)))
                {
                    if (par1 == Block.pistonBase.blockID || par1 == Block.pistonStickyBase.blockID || par1 == Block.lever.blockID || par1 == Block.dispenser.blockID)
                    {
                        if (parNewRotation == 0)
                        {
                            if (parCurMeta == 2 || parCurMeta == 3)
                            {
                                return Facing.oppositeSide[parCurMeta];
                            }
                        }
                        else if (parNewRotation == 1)
                        {
                            if (parCurMeta == 2)
                            {
                                return 4;
                            }

                            if (parCurMeta == 3)
                            {
                                return 5;
                            }

                            if (parCurMeta == 4)
                            {
                                return 2;
                            }

                            if (parCurMeta == 5)
                            {
                                return 3;
                            }
                        }
                        else if (parNewRotation == 3)
                        {
                            if (parCurMeta == 2)
                            {
                                return 5;
                            }

                            if (parCurMeta == 3)
                            {
                                return 4;
                            }

                            if (parCurMeta == 4)
                            {
                                return 2;
                            }

                            if (parCurMeta == 5)
                            {
                                return 3;
                            }
                        }
                    }
                }
                else if (parNewRotation == 0)
                {
                    if (parCurMeta == 0 || parCurMeta == 2)
                    {
                        return Direction.rotateOpposite[parCurMeta];
                    }
                }
                else if (parNewRotation == 1)
                {
                    if (parCurMeta == 2)
                    {
                        return 1;
                    }

                    if (parCurMeta == 0)
                    {
                        return 3;
                    }

                    if (parCurMeta == 1)
                    {
                        return 2;
                    }

                    if (parCurMeta == 3)
                    {
                        return 0;
                    }
                }
                else if (parNewRotation == 3)
                {
                    if (parCurMeta == 2)
                    {
                        return 3;
                    }

                    if (parCurMeta == 0)
                    {
                        return 1;
                    }

                    if (parCurMeta == 1)
                    {
                        return 2;
                    }

                    if (parCurMeta == 3)
                    {
                        return 0;
                    }
                }
            }
            else if (parNewRotation == 0)
            {
                if (parCurMeta == 2)
                {
                    return 3;
                }

                if (parCurMeta == 3)
                {
                    return 2;
                }
            }
            else if (parNewRotation == 1)
            {
                if (parCurMeta == 0)
                {
                    return 2; //opposite
                }

                if (parCurMeta == 1)
                {
                    return 3; //opposite
                }

                if (parCurMeta == 2)
                {
                    return 0;
                }

                if (parCurMeta == 3)
                {
                    return 1;
                }
            }
            else if (parNewRotation == 3)
            {
                if (parCurMeta == 0)
                {
                    return 2;
                }

                if (parCurMeta == 1)
                {
                    return 3;
                }

                if (parCurMeta == 2)
                {
                    return 1;
                }

                if (parCurMeta == 3)
                {
                    return 0;
                }
            }
        }
        else if (parNewRotation == 0)
        {
            if (parCurMeta == 0)
            {
                return 2;
            }

            if (parCurMeta == 2)
            {
                return 0;
            }
        }
        else
        {
            if (parNewRotation == 1)
            {
                return parCurMeta + 1 & 3;
            }

            if (parNewRotation == 3)
            {
                return parCurMeta + 3 & 3;
            }
        }

        return parCurMeta;
    }
    
    public static int makeHash(int par0, /*int par1, */int par2)
    {
        return /*par1 & 255 | */(par0 & 32767) << 8 | (par2 & 32767) << 24 | (par0 < 0 ? Integer.MIN_VALUE : 0) | (par2 < 0 ? 32768 : 0);
    }

}
