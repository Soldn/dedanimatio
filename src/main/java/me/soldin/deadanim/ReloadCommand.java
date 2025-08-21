package me.soldin.deadanim;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final DeadAnimSoldin plugin;

    public ReloadCommand(DeadAnimSoldin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(plugin.getConfig().getString("messages.reloaded"));
            return true;
        }
        sender.sendMessage("§cИспользуй: /" + label + " reload");
        return true;
    }
}
