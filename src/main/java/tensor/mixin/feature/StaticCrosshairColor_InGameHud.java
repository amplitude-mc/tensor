package tensor.mixin.feature;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tensor.option.TensorOptions;

@Mixin(InGameHud.class)
public abstract class StaticCrosshairColor_InGameHud extends DrawableHelper
{
    @Shadow
    private MinecraftClient client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;
    
    @Shadow
    public abstract boolean shouldRenderSpectatorCrosshair(HitResult hitResult);
    
    @Overwrite
    private void renderCrosshair(MatrixStack matrices)
    {
        GameOptions gameOptions = this.client.options;
        if(gameOptions.getPerspective().isFirstPerson())
        {
            if(this.client.interactionManager.getCurrentGameMode() != GameMode.SPECTATOR || this.shouldRenderSpectatorCrosshair(this.client.crosshairTarget))
            {
                if(gameOptions.debugEnabled && !gameOptions.hudHidden && !this.client.player.hasReducedDebugInfo() && !(Boolean)gameOptions.getReducedDebugInfo().getValue())
                {
                    Camera camera = this.client.gameRenderer.getCamera();
                    MatrixStack matrixStack = RenderSystem.getModelViewStack();
                    matrixStack.push();
                    matrixStack.translate(this.scaledWidth / 2.0, this.scaledHeight / 2.0, this.getZOffset());
                    matrixStack.multiply(Vec3f.NEGATIVE_X.getDegreesQuaternion(camera.getPitch()));
                    matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(camera.getYaw()));
                    matrixStack.scale(-1.0F, -1.0F, -1.0F);
                    RenderSystem.applyModelViewMatrix();
                    RenderSystem.renderCrosshair(10);
                    matrixStack.pop();
                    RenderSystem.applyModelViewMatrix();
                } else
                {
                    if(!TensorOptions.staticCrosshairColor)
                        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ONE_MINUS_DST_COLOR, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
                    this.drawTexture(matrices, (this.scaledWidth - 15) / 2, (this.scaledHeight - 15) / 2, 0, 0, 15, 15);
                    if(this.client.options.getAttackIndicator().getValue() == AttackIndicator.CROSSHAIR)
                    {
                        float f = this.client.player.getAttackCooldownProgress(0.0F);
                        boolean bl = false;
                        if(this.client.targetedEntity != null && this.client.targetedEntity instanceof LivingEntity && f >= 1.0F)
                        {
                            bl = this.client.player.getAttackCooldownProgressPerTick() > 5.0F;
                            bl &= this.client.targetedEntity.isAlive();
                        }
                        int j = this.scaledHeight / 2 - 7 + 16;
                        int k = this.scaledWidth / 2 - 8;
                        if(bl)
                        {
                            this.drawTexture(matrices, k, j, 68, 94, 16, 16);
                        } else if(f < 1.0F)
                        {
                            int l = (int)(f * 17.0F);
                            this.drawTexture(matrices, k, j, 36, 94, 16, 4);
                            this.drawTexture(matrices, k, j, 52, 94, l, 4);
                        }
                    }
                }
            }
        }
    }
}
