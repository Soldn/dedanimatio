package me.soldin.deadanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathListener implements Listener {

    private final DeadAnimSoldin plugin;

    public DeathListener(DeadAnimSoldin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!plugin.getConfig().getBoolean("animation-enabled")) return;

        Player player = event.getEntity();
        Location loc = player.getLocation();
        World world = loc.getWorld();

        new BukkitRunnable() {
            int ticks = 0;
            Location animLoc = loc.clone();

            @Override
            public void run() {
                if (ticks > plugin.getConfig().getInt("ascend.duration-ticks")) {
                    this.cancel();
                    explode(animLoc);
                    return;
                }

                animLoc.add(0, plugin.getConfig().getDouble("ascend.speed-per-tick"), 0);
                if (plugin.getConfig().getBoolean("particles.enabled")) {
                    world.spawnParticle(Particle.SMOKE_NORMAL, animLoc, 5);
                    world.spawnParticle(Particle.REDSTONE, animLoc, 10);
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, plugin.getConfig().getInt("particles.tick-interval"));
    }

    private void explode(Location loc) {
        if (plugin.getConfig().getBoolean("explosion.enabled")) {
            loc.getWorld().createExplosion(
                loc,
                (float) plugin.getConfig().getDouble("explosion.power"),
                plugin.getConfig().getBoolean("explosion.set-fire"),
                plugin.getConfig().getBoolean("explosion.break-blocks")
            );
        }
    }
}
