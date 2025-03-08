package sunbatheproductions28.colortheturtles.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Turtle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Turtle.class)
public abstract class TurtleColorMixin {

    @Unique
    private int turtleColor = 5; // Default Lime color

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void saveColorData(CompoundTag tag, CallbackInfo ci) {
        tag.putInt("TurtleColor", this.turtleColor);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void loadColorData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("TurtleColor")) {
            this.turtleColor = tag.getInt("TurtleColor");
        }
    }

    @Unique
    public void setTurtleColor(int color) {
        this.turtleColor = color;
    }

    @Unique
    public int getTurtleColor() {
        return this.turtleColor;
    }
}