package mytroublemod.tsconfig.troubleapi.util;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class EntityHelper {

	/**
	 * Find the closest entity near the scanner
	 * 
	 * @param scanner Entity to look around
	 * @param target What class to look for
	 * @param scanRange How far to look around scanner
	 * @return
	 */
    public static Entity findNearestEntity(Entity scanner, Class target, int scanRange) {
                        
        double scanX = scanner.posX;
        double scanY = scanner.posY;
        double scanZ = scanner.posZ;
        AxisAlignedBB box = AxisAlignedBB.getBoundingBox(scanX, scanY, scanZ, scanX, scanY, scanZ).expand(scanRange, scanRange, scanRange);
        List listEntities = scanner.worldObj.getEntitiesWithinAABB(target, box);
        
        if (listEntities.size() > 0) {
            return (Entity) listEntities.get(0);
        }
        
        return null;
    }
    
    private static Entity findEntityLookingAt(EntityLivingBase scanner, Class target, int furthestLookRange, int accuracyRange) {
        
        int currentRange = 1;
        
        while (currentRange < furthestLookRange) {
            
            Vec3 playerLook = scanner.getLook(1);
            double scanX = scanner.posX + playerLook.xCoord * currentRange;
            double scanY = scanner.posY + playerLook.yCoord * currentRange;
            double scanZ = scanner.posZ + playerLook.zCoord * currentRange;
            
            AxisAlignedBB box = AxisAlignedBB.getBoundingBox(scanX, scanY, scanZ, scanX, scanY, scanZ).expand(accuracyRange, accuracyRange, accuracyRange);
            List listEntities = scanner.worldObj.getEntitiesWithinAABB(target, box);
            
            if (listEntities.size() > 0) {
                return (Entity) listEntities.get(0);
            }

            currentRange++;
        }
        
        return null;
    }
    
    /**
     * Looks for a specific entity type to mount on to based on where the mounter is looking
     * 
     * @param mounter What to mount onto found entity
     * @param mounted What class to look for
     * @param range How far to look
     * @param accuracy How closely within the look area it should search as it scans outwards
     */
    public static void findAndMount(EntityLivingBase mounter, Class mounted, int range, int accuracy) {
        
        if (!mounter.worldObj.isRemote) {
            Entity nearestEntity = findEntityLookingAt(mounter, mounted, range, accuracy);
            if (nearestEntity != null) {
                mounter.mountEntity(nearestEntity);
            }
        }
    }
    
    /**
     * Makes dragon target an area to move to ahead of where the player is looking
     * 
     * @param player
     * @param dragon
     * @param lookAheadDistance How far ahead he should move to
     */
    public static void setLookTargetForDragon(EntityPlayer player, EntityDragon dragon, double lookAheadDistance) {
        Vec3 lookVec = player.getLook(1F);
        
        dragon.targetX = player.posX + lookVec.xCoord * lookAheadDistance;
        dragon.targetY = player.posY + lookVec.yCoord * lookAheadDistance;
        dragon.targetZ = player.posZ + lookVec.zCoord * lookAheadDistance;
    }

}
