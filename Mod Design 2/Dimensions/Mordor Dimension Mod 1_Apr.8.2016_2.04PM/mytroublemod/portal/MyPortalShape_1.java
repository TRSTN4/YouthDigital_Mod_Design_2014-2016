package mytroublemod.portal;

import mytroublemod.TroubleMain;
import mytroublemod.tsconfig.troubleapi.Structure;
import net.minecraft.block.Block;

public class MyPortalShape_1 extends Structure {

    public void initStructure() {
        
        setStructureShape (new Object[] {
        
            " XXXXX ",
            "XX   XX",
            "X     X",
            "X     X",
            "X     X",
            "XX F XX",
            " XXXXX ",
        
            
                'X', TroubleMain.MyFrameBlock_1,
                'F', Block.fire,
                
        });
        
        useScaffoldingOnGenerate(false);
    }

}