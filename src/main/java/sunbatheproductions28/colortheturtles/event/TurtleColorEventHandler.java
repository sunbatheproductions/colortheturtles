package sunbatheproductions28.colortheturtles.event;

import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import sunbatheproductions28.colortheturtles.mixin.TurtleColorAccessor;

public class TurtleColorEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onTurtleRightClick(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Turtle turtle) {
            Player player = event.getEntity();
            ItemStack itemStack = event.getItemStack();

            if (itemStack.getItem() instanceof DyeItem dyeItem) {
                int color = dyeItem.getDyeColor().getId();

                if (turtle instanceof TurtleColorAccessor accessor) {
                    accessor.setTurtleColor(color);
                }

                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}
