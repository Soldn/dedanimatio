package me.soldin.deadanim;

import org.bukkit.plugin.java.JavaPlugin;

public class DeadAnimSoldin extends JavaPlugin {

    private static DeadAnimSoldin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getCommand("deadanimsoldin").setExecutor(new ReloadCommand(this));
        getLogger().info("DeadAnimSoldin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeadAnimSoldin disabled!");
    }

    public static DeadAnimSoldin getInstance() {
        return instance;
    }
}
