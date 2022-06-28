package mytroublemod.tsconfig.troubleapi.dimension;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

import org.lwjgl.opengl.GL11;

public class EntityRendererOverride extends EntityRenderer {

	private Minecraft mc;
    private Random random = new Random();
    public int rendererUpdateCount;
    
    /** Rain X coords */
    public float[] rainXCoords;

    /** Rain Y coords */
    public float[] rainYCoords;
    
    private static final ResourceLocation locationRainPng = new ResourceLocation("textures/environment/rain.png");
    private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");

	public EntityRendererOverride(Minecraft par1Minecraft) {
		super(par1Minecraft);
		this.mc = par1Minecraft;
	}
	
	@Override
	public void updateRenderer() {
		super.updateRenderer();
		
		++this.rendererUpdateCount;
	}
	
	@Override
	protected void renderRainSnow(float par1) {
		//super.renderRainSnow(par1);
		
		float f1 = this.mc.theWorld.getRainStrength(par1);

        if (f1 > 0.0F)
        {
            this.enableLightmap((double)par1);

            if (this.rainXCoords == null)
            {
                this.rainXCoords = new float[1024];
                this.rainYCoords = new float[1024];

                for (int i = 0; i < 32; ++i)
                {
                    for (int j = 0; j < 32; ++j)
                    {
                        float f2 = (float)(j - 16);
                        float f3 = (float)(i - 16);
                        float f4 = MathHelper.sqrt_float(f2 * f2 + f3 * f3);
                        this.rainXCoords[i << 5 | j] = -f3 / f4;
                        this.rainYCoords[i << 5 | j] = f2 / f4;
                    }
                }
            }

            EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
            ResourceLocation resSnow = locationSnowPng;
            if (entitylivingbase.worldObj.provider instanceof ModWorldProvider) {
            	resSnow = ((ModWorldProvider)entitylivingbase.worldObj.provider).getSnowTexture();
            }
            WorldClient worldclient = this.mc.theWorld;
            int k = MathHelper.floor_double(entitylivingbase.posX);
            int l = MathHelper.floor_double(entitylivingbase.posY);
            int i1 = MathHelper.floor_double(entitylivingbase.posZ);
            Tessellator tessellator = Tessellator.instance;
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.01F);
            this.mc.getTextureManager().bindTexture(resSnow);
            double d0 = entitylivingbase.lastTickPosX + (entitylivingbase.posX - entitylivingbase.lastTickPosX) * (double)par1;
            double d1 = entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * (double)par1;
            double d2 = entitylivingbase.lastTickPosZ + (entitylivingbase.posZ - entitylivingbase.lastTickPosZ) * (double)par1;
            int j1 = MathHelper.floor_double(d1);
            byte b0 = 5;

            if (this.mc.gameSettings.fancyGraphics)
            {
                b0 = 10;
            }

            boolean flag = false;
            byte b1 = -1;
            float f5 = (float)this.rendererUpdateCount + par1;

            if (this.mc.gameSettings.fancyGraphics)
            {
                b0 = 10;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            flag = false;
            
            ResourceLocation resRain = locationRainPng;
            if (entitylivingbase.worldObj.provider instanceof ModWorldProvider) {
            	resRain = ((ModWorldProvider)entitylivingbase.worldObj.provider).getRainTexture();
            }

            for (int k1 = i1 - b0; k1 <= i1 + b0; ++k1)
            {
                for (int l1 = k - b0; l1 <= k + b0; ++l1)
                {
                    int i2 = (k1 - i1 + 16) * 32 + l1 - k + 16;
                    float f6 = this.rainXCoords[i2] * 0.5F;
                    float f7 = this.rainYCoords[i2] * 0.5F;
                    BiomeGenBase biomegenbase = worldclient.getBiomeGenForCoords(l1, k1);

                    if (biomegenbase.canSpawnLightningBolt() || biomegenbase.getEnableSnow())
                    {
                        int j2 = worldclient.getPrecipitationHeight(l1, k1);
                        int k2 = l - b0;
                        int l2 = l + b0;

                        if (k2 < j2)
                        {
                            k2 = j2;
                        }

                        if (l2 < j2)
                        {
                            l2 = j2;
                        }

                        float f8 = 1.0F;
                        int i3 = j2;

                        if (j2 < j1)
                        {
                            i3 = j1;
                        }

                        if (k2 != l2)
                        {
                            this.random.setSeed((long)(l1 * l1 * 3121 + l1 * 45238971 ^ k1 * k1 * 418711 + k1 * 13761));
                            float f9 = biomegenbase.getFloatTemperature();
                            double d3;
                            float f10;

                            if (worldclient.getWorldChunkManager().getTemperatureAtHeight(f9, j2) >= 0.15F)
                            {
                                if (b1 != 0)
                                {
                                    if (b1 >= 0)
                                    {
                                        tessellator.draw();
                                    }

                                    b1 = 0;
                                    this.mc.getTextureManager().bindTexture(resRain);
                                    tessellator.startDrawingQuads();
                                }

                                f10 = ((float)(this.rendererUpdateCount + l1 * l1 * 3121 + l1 * 45238971 + k1 * k1 * 418711 + k1 * 13761 & 31) + par1) / 32.0F * (3.0F + this.random.nextFloat());
                                double d4 = (double)((float)l1 + 0.5F) - entitylivingbase.posX;
                                d3 = (double)((float)k1 + 0.5F) - entitylivingbase.posZ;
                                float f11 = MathHelper.sqrt_double(d4 * d4 + d3 * d3) / (float)b0;
                                float f12 = 1.0F;
                                tessellator.setBrightness(worldclient.getLightBrightnessForSkyBlocks(l1, i3, k1, 0));
                                tessellator.setColorRGBA_F(f12, f12, f12, ((1.0F - f11 * f11) * 0.5F + 0.5F) * f1);
                                tessellator.setTranslation(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.addVertexWithUV((double)((float)l1 - f6) + 0.5D, (double)k2, (double)((float)k1 - f7) + 0.5D, (double)(0.0F * f8), (double)((float)k2 * f8 / 4.0F + f10 * f8));
                                tessellator.addVertexWithUV((double)((float)l1 + f6) + 0.5D, (double)k2, (double)((float)k1 + f7) + 0.5D, (double)(1.0F * f8), (double)((float)k2 * f8 / 4.0F + f10 * f8));
                                tessellator.addVertexWithUV((double)((float)l1 + f6) + 0.5D, (double)l2, (double)((float)k1 + f7) + 0.5D, (double)(1.0F * f8), (double)((float)l2 * f8 / 4.0F + f10 * f8));
                                tessellator.addVertexWithUV((double)((float)l1 - f6) + 0.5D, (double)l2, (double)((float)k1 - f7) + 0.5D, (double)(0.0F * f8), (double)((float)l2 * f8 / 4.0F + f10 * f8));
                                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                            }
                            else
                            {
                                if (b1 != 1)
                                {
                                    if (b1 >= 0)
                                    {
                                        tessellator.draw();
                                    }

                                    b1 = 1;
                                    this.mc.getTextureManager().bindTexture(resSnow);
                                    tessellator.startDrawingQuads();
                                }

                                f10 = ((float)(this.rendererUpdateCount & 511) + par1) / 512.0F;
                                float f13 = this.random.nextFloat() + f5 * 0.01F * (float)this.random.nextGaussian();
                                float f14 = this.random.nextFloat() + f5 * (float)this.random.nextGaussian() * 0.001F;
                                d3 = (double)((float)l1 + 0.5F) - entitylivingbase.posX;
                                double d5 = (double)((float)k1 + 0.5F) - entitylivingbase.posZ;
                                float f15 = MathHelper.sqrt_double(d3 * d3 + d5 * d5) / (float)b0;
                                float f16 = 1.0F;
                                tessellator.setBrightness((worldclient.getLightBrightnessForSkyBlocks(l1, i3, k1, 0) * 3 + 15728880) / 4);
                                tessellator.setColorRGBA_F(f16, f16, f16, ((1.0F - f15 * f15) * 0.3F + 0.5F) * f1);
                                tessellator.setTranslation(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.addVertexWithUV((double)((float)l1 - f6) + 0.5D, (double)k2, (double)((float)k1 - f7) + 0.5D, (double)(0.0F * f8 + f13), (double)((float)k2 * f8 / 4.0F + f10 * f8 + f14));
                                tessellator.addVertexWithUV((double)((float)l1 + f6) + 0.5D, (double)k2, (double)((float)k1 + f7) + 0.5D, (double)(1.0F * f8 + f13), (double)((float)k2 * f8 / 4.0F + f10 * f8 + f14));
                                tessellator.addVertexWithUV((double)((float)l1 + f6) + 0.5D, (double)l2, (double)((float)k1 + f7) + 0.5D, (double)(1.0F * f8 + f13), (double)((float)l2 * f8 / 4.0F + f10 * f8 + f14));
                                tessellator.addVertexWithUV((double)((float)l1 - f6) + 0.5D, (double)l2, (double)((float)k1 - f7) + 0.5D, (double)(0.0F * f8 + f13), (double)((float)l2 * f8 / 4.0F + f10 * f8 + f14));
                                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
            }

            if (b1 >= 0)
            {
                tessellator.draw();
            }

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            this.disableLightmap((double)par1);
        }
	}

}
