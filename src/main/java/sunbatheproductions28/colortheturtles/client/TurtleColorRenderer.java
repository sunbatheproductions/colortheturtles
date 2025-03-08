package sunbatheproductions28.colortheturtles.client;

import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.world.entity.animal.Turtle;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import java.awt.Color;

public class TurtleColorRenderer extends TurtleRenderer {

    public TurtleColorRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new TurtleColorLayer(this));
    }

    private static class TurtleColorLayer extends RenderLayer<Turtle, TurtleModel<Turtle>> {

        public TurtleColorLayer(RenderLayerParent<Turtle, TurtleModel<Turtle>> renderer) {
            super(renderer);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Turtle turtle, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            int overlayColor = getOverlayColor(turtle, partialTicks);
            float red = (overlayColor >> 16 & 255) / 255.0F;
            float green = (overlayColor >> 8 & 255) / 255.0F;
            float blue = (overlayColor & 255) / 255.0F;

            renderColoredCutoutModel(this.getParentModel(), getTextureLocation(turtle), poseStack, bufferSource, packedLight, turtle, red, green, blue);
        }

        private int getOverlayColor(Turtle turtle, float partialTicks) {
            if (turtle.hasCustomName() && "jeb_".equals(turtle.getName().getString())) {
                return getRainbowColor(turtle.tickCount);
            }

            CompoundTag nbt = turtle.getPersistentData();

            if (!nbt.contains("TurtleColor")) {
                return 0xFFFFFF; // Default white if no color is set
            }

            int colorId = nbt.getInt("TurtleColor");

            int color = switch (colorId) {
                case 0 -> DyeColor.WHITE.getTextColor();
                case 1 -> DyeColor.ORANGE.getTextColor();
                case 2 -> DyeColor.MAGENTA.getTextColor();
                case 3 -> DyeColor.LIGHT_BLUE.getTextColor();
                case 4 -> DyeColor.YELLOW.getTextColor();
                case 5 -> DyeColor.LIME.getTextColor();
                case 6 -> DyeColor.PINK.getTextColor();
                case 7 -> DyeColor.GRAY.getTextColor();
                case 8 -> DyeColor.LIGHT_GRAY.getTextColor();
                case 9 -> DyeColor.CYAN.getTextColor();
                case 10 -> DyeColor.PURPLE.getTextColor();
                case 11 -> DyeColor.BLUE.getTextColor();
                case 12 -> DyeColor.BROWN.getTextColor();
                case 13 -> DyeColor.GREEN.getTextColor();
                case 14 -> DyeColor.RED.getTextColor();
                case 15 -> DyeColor.BLACK.getTextColor();
                default -> 0xFFFFFF; // Default white
            };

            return color;
        }

        private int getRainbowColor(int tickCount) {
            float hue = (tickCount % 100) / 100.0f;
            return Color.HSBtoRGB(hue, 1.0f, 1.0f);
        }
    }
}