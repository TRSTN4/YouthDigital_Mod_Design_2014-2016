/**
 *	LOOK IN TROUBLEBIOME.JAVA FOR INSTRUCTIONS
 *----------------------------------------------
 */
 
 package mytroublemod;

import mymod.Main;
import mytroublemod.armor.TroubleArmor;
import mytroublemod.biome.TroubleBiome;
import mytroublemod.blocks.TroubleBlock;
import mytroublemod.items.TroubleFood;
import mytroublemod.items.TroubleItem;
import mytroublemod.items.TroublePickaxe;
import mytroublemod.items.TroubleSword;
import mytroublemod.tsconfig.TroubleEvent;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/*
 *	MOD INFO * 
 */
		@Mod( modid = "troublemod", name = "Troublesome Mod", version = "T.S.")
		@NetworkMod(clientSideRequired=true, serverSideRequired=false)	


public class TroubleMain {
			
	
/**	DECLARATION SECTION 
 * =============================================================
 */

	//  DECLARE THE SWORD 
		public static Item TroubleSword_1;
	
	//  DECLARE THE PICKAXE 
		public static Item TroublePickaxe_1;
		
    //  DECLARE NEW TOOL MATERIAL
		public static EnumToolMaterial TroubleToolMaterial = EnumHelper.addToolMaterial("Trouble", 2, 1, 8.0F, 3.0F, 12);

    //  DECLARE THE ARMOR MATERIAL
        public static EnumArmorMaterial TroubleArmorMaterial_1 = EnumHelper.addArmorMaterial("Trouble", 33, new int[]{5, 9, 7, 4}, 13);
		
	//  DECLARE THE ITEM
        public static Item TroubleItem_1;
        
	//  DECLARE THE FOOD
        public static Item TroubleFood_1;	

	//  DECLARE THE BLOCK
		public static Block TroubleBlock_1;

	//  DECLARE THE ARMOR
        public static Item TroubleHelmet_1;
        public static Item TroubleChest_1;
        public static Item TroubleLeggings_1;
        public static Item TroubleBoots_1;
 
	 //  DECLARE THE BIOME
	    public static  BiomeGenBase TroubleBiome_1; 

	

/*
 * -------------------------------------------------------------	
 */

@EventHandler
	public  void preInit( FMLPreInitializationEvent event ) 
	{

/**	
 * 	LOAD SECTION 
 * ==========================================================	
 */
	
	//  LOAD THE SWORD
	    TroubleSword_1 = new TroubleSword(3021, TroubleToolMaterial, "TroubleSword_1");
	    GameRegistry.registerItem(TroubleSword_1, "TroubleSword_1");
	    LanguageRegistry.addName(TroubleSword_1, "Trouble Sword");

	//  LOAD THE PICKAXE
	    TroublePickaxe_1 = new TroublePickaxe(3022, TroubleToolMaterial, "TroublePickaxe_1");
	    GameRegistry.registerItem(TroublePickaxe_1, "TroublePickaxe_1");
	    LanguageRegistry.addName(TroublePickaxe_1, "Trouble Pickaxe");	  
	    
	//  LOAD THE ITEM IN THE GAME
	    TroubleItem_1 = new TroubleItem(3030, "TroubleItem_1").setCreativeTab(CreativeTabs.tabMaterials);
	    GameRegistry.registerItem(TroubleItem_1, "TroubleItem_1");
	    LanguageRegistry.addName(TroubleItem_1, "Trouble Item");  

    //  LOAD THE FOOD IN THE GAME
    /** Item ID, Heal Amount, Saturation Modifier (F), Icon Texture */
	    TroubleFood_1 = new TroubleFood(3040, 1, 0.1F, "TroubleFood_1").setAlwaysEdible();
	    GameRegistry.registerItem(TroubleFood_1, "TroubleFood_1");
	    LanguageRegistry.addName(TroubleFood_1, "Trouble Food");
		
	//  LOAD THE BLOCK IN THE GAME
	/** Block ID, Hardness, Resistance, Light Value, Icon Texture */
		TroubleBlock_1 = new TroubleBlock(255, "TroubleBlock_1");
		GameRegistry.registerBlock(TroubleBlock_1, "TroubleBlock_1");
		LanguageRegistry.addName(TroubleBlock_1, "Trouble Block");	

    //  LOAD HELMET 
        TroubleHelmet_1 = new TroubleArmor(3060, TroubleArmorMaterial_1, 0, 0, "troublearmor");
        GameRegistry.registerItem(TroubleHelmet_1, "TroubleHelmet_1");
        LanguageRegistry.addName(TroubleHelmet_1, "Trouble Helmet");      

	//  LOAD CHESTPLATE
        TroubleChest_1 = new TroubleArmor(3061, TroubleArmorMaterial_1, 0, 1, "troublearmor");
        GameRegistry.registerItem(TroubleChest_1, "TroubleChest_1");
        LanguageRegistry.addName(TroubleChest_1, "Trouble Chestplate");
	
	//  LOAD LEGGINGS    
        TroubleLeggings_1 = new TroubleArmor(3062, TroubleArmorMaterial_1, 0, 2, "troublearmor");
        GameRegistry.registerItem(TroubleLeggings_1, "TroubleLeggings_1");
        LanguageRegistry.addName(TroubleLeggings_1, "Trouble Leggings");

	//  LOAD BOOTS   
        TroubleBoots_1 = new TroubleArmor(3063, TroubleArmorMaterial_1, 0, 3, "troublearmor");
        GameRegistry.registerItem(TroubleBoots_1, "TroubleBoots_1");
        LanguageRegistry.addName(TroubleBoots_1, "Trouble Boots");

	//  LOAD BIOME
	    TroubleBiome_1 = new TroubleBiome(31);
	    GameRegistry.addBiome(TroubleBiome_1);



/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

}

@EventHandler
public static void init( FMLInitializationEvent event ) 
{

/**	
* RECIPES SECTION 
* *********************************************************** */

	//	SWORD RECIPE  
	    GameRegistry.addRecipe(new ItemStack(TroubleSword_1, 1), new Object[]
	    {
	            "X",
	            "X",
	            "S",
	        'S', Item.blazeRod,
	        'X', Item.blazePowder,
	    });

	//	PICKAXE RECIPE  
	    GameRegistry.addRecipe(new ItemStack(TroublePickaxe_1, 1), new Object[]
	    {
	            "XXX",
	            " S ",
	            " S ",
	        'S', Item.stick,
	        'X', TroubleItem_1,
	    });
	    
	//  ITEM RECIPE         
        GameRegistry.addRecipe(new ItemStack(TroubleItem_1, 1), new Object[]
        {
                "SSS",
                "SSS",
                "SSS",
            'S', Item.sugar,
        });
        
	//  FOOD RECIPE         
        GameRegistry.addRecipe(new ItemStack(TroubleFood_1, 1), new Object[]
        {
                "SSS",
                "SUS",
                "SSS",
            'S', TroubleItem_1,
            'U', Item.sugar,
        });
		
	//  BLOCK RECIPE
		GameRegistry.addSmelting(TroubleBlock_1.blockID, (new ItemStack(TroubleItem_1, 2)), 10);

    //  HELMET RECIPE   
	    GameRegistry.addRecipe(new ItemStack(TroubleHelmet_1, 1), new Object[]
	    {
	            "XXX",
	            "X X",
	        'X', TroubleItem_1,
	    });         
	
	//  CHESTPLATE RECIPE   
	    GameRegistry.addRecipe(new ItemStack(TroubleChest_1, 1), new Object[]
	    {
	            "X X",
	            "XXX",
	            "XXX",
	        'X', TroubleItem_1,
	    });         
	
	//  LEGGINGS RECIPE 
	    GameRegistry.addRecipe(new ItemStack(TroubleLeggings_1, 1), new Object[]
	    {
	            "XXX",
	            "X X",
	            "X X",
	        'X', TroubleItem_1,
	    });         
	
	//  BOOTS RECIPE    
	    GameRegistry.addRecipe(new ItemStack(TroubleBoots_1, 1), new Object[]
	    {
	            "X X",
	            "X X",
	        'X', TroubleItem_1,
	    });             
	    
/*
 * --------------------------------------------------------------	
 */

	
	
/**
 * 	EXTRA METHODS SECTION
 * =========================================================================
 */


/* ********* | TS CODE - DO NOT TOUCH | ***************/			
	MinecraftForge.EVENT_BUS.register(new TroubleEvent());
	final TroubleEvent Trouble = new TroubleEvent();
    GameRegistry.registerCraftingHandler(Trouble);
/* ****************************************************/			
		

/*
 * --------------------------------------------------------------	
 */
	
	}

@EventHandler
	public static void postInit( FMLPostInitializationEvent event ) 
	{

	}

}
