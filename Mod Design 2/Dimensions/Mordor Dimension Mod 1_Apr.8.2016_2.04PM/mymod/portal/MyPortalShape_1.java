package mymod.portal;

import java.util.Random;

import mymod.Main;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import api.Structure;

public class MyPortalShape_1 extends Structure {

    public void initStructure() {
        
        setStructureShape (new Object[] {
        
           " XXXX ",
           "X    X",
           "X    X",
           "X    X",
           "X F  X",
           " XXXX ",
        
            
                'X', Main.MyFrameBlock_1,
                'F', Block.fire,
        });
        
        useScaffoldingOnGenerate(false);
    }

}