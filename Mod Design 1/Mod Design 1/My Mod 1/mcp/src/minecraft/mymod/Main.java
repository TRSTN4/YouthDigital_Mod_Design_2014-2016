package mymod;

import mymod.armor.MyArmor;
import mymod.biome.MyBiome;
import mymod.blocks.MyBlockGen;
import mymod.entity.pig.MyEntityPig;
import mymod.entity.pig.MyModelPig;
import mymod.entity.pig.MyRenderPig;
import mymod.items.MyFood;
import mymod.items.MyItem;
import mymod.items.MyPickaxe;
import mymod.items.MySword;
import mymod.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.util.Color;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;



/* 	MOD INFO */
	@Mod( modid = "mymod", name = "Tristan Mod", version = "1.0")
	@NetworkMod(clientSideRequired=true, serverSideRequired=false)	


public class Main {

/*	PROXY INFO */
	@SidedProxy(clientSide = "mymod.proxies.ClientProxy", serverSide = "mymod.proxies.CommonProxy")
	public static CommonProxy proxy;
		
	
/**	
 * DECLARATION SECTION 
 * *********************************************************** */

//  DECLARE THE SWORD 
        public static Item MySword_1;

//  DECLARE THE PICKAXE 
        public static Item MyPickaxe_1;
        
//  DECLARE NEW TOOL MATERIAL
                                                            /** Harvest Leve1, Max Uses, Efficiency (f), Damage (f), Enchantability */
        public static EnumToolMaterial MyToolMaterial = EnumHelper.addToolMaterial("The Super", 3, 500, 16.0F, 3.0F, 10);

//  DECLARE THE ITEM
        public static Item MyItem_1;

//  DECLARE THE FOOD
        public static Item MyFood_1;
        
//  DECLARE THE BLOCK
        public static Block MyBlock_1;

//  DECLARE THE ARMOR
        public static Item MyHelmet_1;
        public static Item MyChest_1;
        public static Item MyLeggings_1;
        public static Item MyBoots_1;       
 
//  DECLARE THE ARMOR MATERIAL
                                                        /**maxDamageFactor,damageReductionAmountArray,enchantability */  
        public static EnumArmorMaterial MyArmorMaterial_1 = EnumHelper.addArmorMaterial("Super Kracht", 33, new int[]{10, 10, 10, 10}, 30); 

//  DECLARE THE MOB ID
        static int MyEntityID = 300;
    
    //  SEARCH FOR UNIQUE ID    
        public static int getUniqueEntityId() {
            do {
                MyEntityID++;
            }
            while (EntityList.getStringFromID(MyEntityID) != null);
            return MyEntityID++;
        }
    
    //  DECLARE A NEW EGG
        public static void registerEntityEgg(Class <? extends Entity> entity, int primaryColor, int secondaryColor) {
            int id = getUniqueEntityId();
            EntityList.IDtoClassMapping.put(id, entity);
            EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
        }
       
/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	


@EventHandler	
	public  void preInit( FMLPreInitializationEvent event ) 
	{
/**	
 * LOAD SECTION 
 * *********************************************************** */ 


//  LOAD THE SWORD
        MySword_1 = new MySword(2021, EnumToolMaterial.IRON, "MySword_1");
        GameRegistry.registerItem(MySword_1, "MySword_1");
        LanguageRegistry.addName(MySword_1, "Super Sword");   
	
//  LOAD THE PICKAXE
        MyPickaxe_1 = new MyPickaxe(2022, EnumToolMaterial.IRON, "MyPickaxe_1");
        GameRegistry.registerItem(MyPickaxe_1, "MyPickaxe_1");
        LanguageRegistry.addName(MyPickaxe_1, "Emerald Pickaxe");

//  LOAD THE ITEM
        MyItem_1 = new MyItem(2030, "MyItem_1").setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);
        GameRegistry.registerItem(MyItem_1, "MyItem_1");
        LanguageRegistry.addName(MyItem_1, "HamBurger");

//  LOAD THE FOOD
        MyFood_1 = new MyFood(2040, 3, 3.0F, true, "MyFood_1").setAlwaysEdible().setPotionEffect(Potion.invisibility.id, 60, 10, 1.0F).setCreativeTab(CreativeTabs.tabFood);
        GameRegistry.registerItem(MyFood_1, "MyFood_1");
        LanguageRegistry.addName(MyFood_1, "Muffin");
        
//  LOAD THE BLOCK 
        MyBlock_1 = new MyBlock(250, Material.rock, "MyBlock_1").setLightValue(0.5F).setResistance(5.0F).setHardness(70.0F).setStepSound(Block.soundAnvilFootstep);
        GameRegistry.registerBlock(MyBlock_1, "MyBlock_1");
        LanguageRegistry.addName(MyBlock_1, "Super Kracht Ore"); 
		MinecraftForge.setBlockHarvestLevel(MyBlock_1, "pickaxe", 1);        

 //  LOAD HELMET 
        MyHelmet_1 = new MyArmor(2060, MyArmorMaterial_1, 0, 0, "myarmor");
        GameRegistry.registerItem(MyHelmet_1, "MyHelmet_1");
        LanguageRegistry.addName(MyHelmet_1, "Super Helmet");      
    
//  LOAD CHESTPLATE
        MyChest_1 = new MyArmor(2061, MyArmorMaterial_1, 0, 1, "myarmor");
        GameRegistry.registerItem(MyChest_1, "MyChest_1");
        LanguageRegistry.addName(MyChest_1, "Super Chestplate");

//  LOAD LEGGINGS    
        MyLeggings_1 = new MyArmor(2062, MyArmorMaterial_1, 0, 2, "myarmor");
        GameRegistry.registerItem(MyLeggings_1, "MyLeggings_1");
        LanguageRegistry.addName(MyLeggings_1, "Super Leggings");

//  LOAD BOOTS   
        MyBoots_1 = new MyArmor(2063, MyArmorMaterial_1, 0, 3, "myarmor");
        GameRegistry.registerItem(MyBoots_1, "MyBoots_1");
        LanguageRegistry.addName(MyBoots_1, "Super Boots");

//  LOAD BIOME
        MyBiome MyBiome_1 = new MyBiome(30);
        GameRegistry.addBiome(MyBiome_1);
        
//  REMOVE OTHER BIOMES
        GameRegistry.removeBiome(BiomeGenBase.beach);
        GameRegistry.removeBiome(BiomeGenBase.desert);
        GameRegistry.removeBiome(BiomeGenBase.desertHills);
        GameRegistry.removeBiome(BiomeGenBase.desertHills);
        GameRegistry.removeBiome(BiomeGenBase.extremeHills);
        GameRegistry.removeBiome(BiomeGenBase.extremeHillsEdge);
        GameRegistry.removeBiome(BiomeGenBase.forest);
        GameRegistry.removeBiome(BiomeGenBase.forestHills);
        GameRegistry.removeBiome(BiomeGenBase.frozenOcean);
        GameRegistry.removeBiome(BiomeGenBase.frozenRiver);
        GameRegistry.removeBiome(BiomeGenBase.iceMountains);
        GameRegistry.removeBiome(BiomeGenBase.icePlains);
        GameRegistry.removeBiome(BiomeGenBase.jungle);
        GameRegistry.removeBiome(BiomeGenBase.jungleHills);
        GameRegistry.removeBiome(BiomeGenBase.mushroomIsland);
        GameRegistry.removeBiome(BiomeGenBase.ocean);
        GameRegistry.removeBiome(BiomeGenBase.plains);
        GameRegistry.removeBiome(BiomeGenBase.river);
        GameRegistry.removeBiome(BiomeGenBase.swampland);
        GameRegistry.removeBiome(BiomeGenBase.taiga);
        GameRegistry.removeBiome(BiomeGenBase.taigaHills);
 
//  REGISTER YOUR ENTITY
        EntityRegistry.registerGlobalEntityID(MyEntityPig.class, "My Awesome Mob", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.addSpawn(MyEntityPig.class, 50, 1, 5, EnumCreatureType.monster, BiomeGenBase.desert);
        EntityRegistry.addSpawn(MyEntityPig.class, 50, 1, 5, EnumCreatureType.monster, MyBiome_1);     
        registerEntityEgg(MyEntityPig.class, (new Color(0, 255, 0)).getRGB(), (new Color(255, 0, 0)).getRGB());
        RenderingRegistry.registerEntityRenderingHandler(MyEntityPig.class, new MyRenderPig(new MyModelPig(), 0.3F));
        ModLoader.addLocalization(key, value);("entity.My Awesome Mob.name", "My Awesome Mob");
        
/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

	}

@EventHandler
	public static void init( FMLInitializationEvent event ) 
	{
	
/**	
 * RECIPES SECTION 
 * *********************************************************** */



//  SWORD RECIPE  
        GameRegistry.addRecipe(new ItemStack(MySword_1, 1), new Object[]
        {
                "X",
                "X",
                "S",
            'S', Item.redstone,
            'X', Item.diamond,
        });

//  PICKAXE RECIPE  
        GameRegistry.addRecipe(new ItemStack(MyPickaxe_1, 1), new Object[]
        {
                "XXX",
                " S ",
                " S ",
            'S', Item.stick,
            'X', Item.emerald,
        });
        
    //  ITEM RECIPE         
        GameRegistry.addRecipe(new ItemStack(MyItem_1, 1), new Object[]
        {
                " S ",
                " X ",
                " S ",
            'S', Item.bread,
            'X', Item.beefCooked,
        });    
     
     //  FOOD RECIPE         
        GameRegistry.addRecipe(new ItemStack(MyFood_1, 1), new Object[]
        {
                " X ",
                " X ",
                " S ",
            'S', Item.paper,
            'X', Item.sugar,
        });
     //  SMELTING RECIPE
        GameRegistry.addSmelting(MyBlock_1.blockID, (new ItemStack(MyFood_1, 1)), 11); 
     
     //  HELMET RECIPE   
    GameRegistry.addRecipe(new ItemStack(MyHelmet_1, 1), new Object[]
    {
            "XXX",
            "X X",
            "XXX",
        'X', MyItem_1,
    });         

//  CHESTPLATE RECIPE   
    GameRegistry.addRecipe(new ItemStack(MyChest_1, 1), new Object[]
    {
            "X X",
            "XXX",
            "XXX",
        'X', MyItem_1,
    });         

//  LEGGINGS RECIPE 
    GameRegistry.addRecipe(new ItemStack(MyLeggings_1, 1), new Object[]
    {
            "XXX",
            "X X",
            "X X",
        'X', MyItem_1,
    });         

//  BOOTS RECIPE    
    GameRegistry.addRecipe(new ItemStack(MyBoots_1, 1), new Object[]
    {
            "X X",
            "X X",
        'X', MyItem_1,
    });
          
/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

	
/**	
 * EXTRA METHODS SECTION 
 * *********************************************************** */

//  REGISTER THE ORE GENERATION 
    GameRegistry.registerWorldGenerator(new MyBlockGen());
        


/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

	
	}

@EventHandler
	public static void postInit( FMLPostInitializationEvent event ) 
	{

	}
	
}
