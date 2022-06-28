package mytroublemod.tsconfig.troubleapi.forge;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mytroublemod.tsconfig.troubleapi.entity.EntityLightningBoltNew;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class ModPacketHandler implements IPacketHandler
{
    public ModPacketHandler()
    {
    	
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        try {
    		if (packet.channel.equals("mymodData")) {
	        	
	        	NBTTagCompound data = Packet.readNBTTagCompound(dis);
	        	
	        	if (side == Side.CLIENT) {
			
	        		int dimID = data.getInteger("dimID");
			
	        		if (data.getString("packetType").equals("lightningNew")) {
	        		
	        			NBTTagCompound nbt = data.getCompoundTag("data");
					
						int posXS = nbt.getInteger("posX");
						int posYS = nbt.getInteger("posY");
						int posZS = nbt.getInteger("posZ");
						
						double posX = (double)posXS;// / 32D;
						double posY = (double)posYS;// / 32D;
						double posZ = (double)posZS;// / 32D;
						
						World world = Minecraft.getMinecraft().theWorld;
						
						if (world != null) {
							EntityLightningBoltNew ent = new EntityLightningBoltNew(world, posX, posY, posZ);
							ent.serverPosX = posXS;
							ent.serverPosY = posYS;
							ent.serverPosZ = posZS;
							ent.rotationYaw = 0.0F;
							ent.rotationPitch = 0.0F;
							ent.entityId = nbt.getInteger("entityID");
							world.addWeatherEffect(ent);
						}
	        		}
	        		
	        	}
	        }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
    
    public static Packet250CustomPayload createPacketForServerToClientSerialization(String channel, NBTTagCompound par) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try {
			writeNBTTagCompound(par, dos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = channel;
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		pkt.isChunkDataPacket = false;
		return pkt;
	}
	
	public static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, DataOutputStream par1DataOutputStream) throws IOException
    {
        if (par0NBTTagCompound == null)
        {
            par1DataOutputStream.writeShort(-1);
        }
        else
        {
            byte[] abyte = CompressedStreamTools.compress(par0NBTTagCompound);
            par1DataOutputStream.writeShort((short)abyte.length);
            par1DataOutputStream.write(abyte);
        }
    }
    
}
