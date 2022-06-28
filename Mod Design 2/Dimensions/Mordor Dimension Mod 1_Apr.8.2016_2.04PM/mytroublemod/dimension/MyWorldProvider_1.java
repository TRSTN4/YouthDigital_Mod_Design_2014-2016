package mytroublemod.dimension;

import mytroublemod.TroubleMain;
import mytroublemod.tsconfig.troubleapi.dimension.ModSingleBiomeManager;
import mytroublemod.tsconfig.troubleapi.dimension.ModWorldProvider;

public class MyWorldProvider_1 extends ModWorldProvider {

    public MyWorldProvider_1() {
        super();
        
    //  WORLD PROPERTIES
        
        //sun and moon
        setSunTexture("MySun.png");
        setSunSize(.5F);
        setMoonTexture("MyMoon.png");
        setMoonSize(.5F);   
        
        //sky
        setStarCount(200);
        setStarBrightness(2);
        setSunsetColor(0, 200, 0);
        setFogColor(175, 149, 107);
        setSkyColor(200, 0, 0);
        setLightningColor(180, 20, 20);
        
        //misc
        setGravityAddition(-0.5F);
        
        


    /** initialize world and sky with the properties above **/
        init();
    }
    
    @Override
    public void registerWorldChunkManager() {
        super.registerWorldChunkManager();
        this.dimensionId = TroubleMain.MyDimensionID_1;
        this.worldChunkMgr = new ModSingleBiomeManager(TroubleMain.MyDimensionBiome_1, this.dimensionId, this.dimensionId);
    }

}
