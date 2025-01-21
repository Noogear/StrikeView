package cn.warriorView;

import cn.warriorView.Manager.AnimationManager;
import cn.warriorView.Manager.ReplacementManager;
import cn.warriorView.Util.Scheduler.XScheduler;
import cn.warriorView.Util.XLogger;
import cn.warriorView.Manager.ViewManager;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.tofaa.entitylib.APIConfig;
import me.tofaa.entitylib.EntityLib;
import me.tofaa.entitylib.spigot.SpigotEntityLibPlatform;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private ViewManager viewManager;
    private AnimationManager animationManager;
    private ReplacementManager replacementManager;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        new XLogger(this);

        PacketEvents.getAPI().init();
        SpigotEntityLibPlatform platform = new SpigotEntityLibPlatform(this);
        APIConfig settings = new APIConfig(PacketEvents.getAPI());
        EntityLib.init(platform, settings);

        animationManager = new AnimationManager();
        replacementManager = new ReplacementManager();
        viewManager = new ViewManager();
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            new XScheduler(this, true);
        } catch (ClassNotFoundException e) {
            new XScheduler(this, false);
        }


    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public ReplacementManager getReplacementManager() {
        return replacementManager;
    }

}
