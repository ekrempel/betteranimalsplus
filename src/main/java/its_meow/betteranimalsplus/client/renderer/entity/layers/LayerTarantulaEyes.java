package its_meow.betteranimalsplus.client.renderer.entity.layers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import its_meow.betteranimalsplus.client.renderer.entity.RenderTarantula;
import its_meow.betteranimalsplus.common.entity.EntityTarantula;
import its_meow.betteranimalsplus.init.TextureRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerTarantulaEyes implements LayerRenderer<EntityTarantula>
{
	private final RenderTarantula tarantulaRenderer;

	public LayerTarantulaEyes(RenderTarantula rendererIn)
	{
		this.tarantulaRenderer = rendererIn;
	}

	public void doRenderLayer(EntityTarantula entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		if (!entitylivingbaseIn.isInvisible() && !entitylivingbaseIn.isChild())
		{
			this.tarantulaRenderer.bindTexture(TextureRegistry.tarantula_eyes);

			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

			if (entitylivingbaseIn.isInvisible())
			{
				GlStateManager.depthMask(false);
			}
			else
			{
				GlStateManager.depthMask(true);
			}

			int i = 61680;
			int j = i % 65536;
			int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
			this.tarantulaRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
			i = entitylivingbaseIn.getBrightnessForRender();
			j = i % 65536;
			k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
			this.tarantulaRenderer.setLightmap(entitylivingbaseIn);
			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();

		}
	}

	public boolean shouldCombineTextures()
	{
		return true;
	}
}