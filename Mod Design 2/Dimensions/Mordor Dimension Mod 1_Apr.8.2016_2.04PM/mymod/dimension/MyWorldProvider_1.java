package mymod.dimension;

import mymod.Main;
import api.dimension.ModSingleBiomeManager;
import api.dimension.ModWorldProvider;

public class MyWorldProvider_1 extends ModWorldProvider {

    public MyWorldProvider_1() {
        super();
        
    //  WORLD PROPERTIES
    
        //sun and moon
        setSunTexture("MySun.png");
        setSunSize(2);
        setMoonTexture("MyMoon.png");
        setMoonSize(.5F);
        
        //sky
        setStarCount(200);
        setStarBrightness(2);
        setSunsetColor(175, 149, 107);
        setFogColor(200, 200, 200);
        setLightningColor(200, 0, 0);
        
        //misc
        setGravityAddition(-.05F);
        
        
    /** initialize world and sky with the properties above **/
        init();
    }
    
    @Override
    public void registerWorldChunkManager() {
        super.registerWorldChunkManager();
        this.dimensionId = Main.MyDimensionID_1;
        this.worldChunkMgr = new ModSingleBiomeManager(Main.MyDimensionBiome_1, this.dimensionId, this.dimensionId);
    }

}
