package sunbatheproductions28.colortheturtles;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;
import sunbatheproductions28.colortheturtles.client.TurtleColorRenderer;
import sunbatheproductions28.colortheturtles.event.TurtleColorEventHandler;

@Mod("colortheturtles")
public class ColortheTurtles {

    public static final String MOD_ID = "colortheturtles";

    public ColortheTurtles() {
        IEventBus modEventBus = MinecraftForge.EVENT_BUS;
        modEventBus.register(new TurtleColorEventHandler());
    }

    @Mod.EventBusSubscriber(modid = "colortheturtles", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
            // Directly register the custom Turtle renderer here
            event.registerEntityRenderer(EntityType.TURTLE, TurtleColorRenderer::new);
        }
    }
}