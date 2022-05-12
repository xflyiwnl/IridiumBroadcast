package me.xflyiwnl.iridiumbroadcast.commands;

import me.xflyiwnl.iridiumbroadcast.Main;
import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.chat.ChatMessages;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.gui.BroadcastGUI;
import me.xflyiwnl.iridiumbroadcast.manager.CooldownManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            ChatMessages.isNotPlayer((Player) sender);
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            ChatMessages.args(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("broadcast")) {

            if (!player.hasPermission("iridiumbroadcast.broadcast")) {
                ChatMessages.noPermission(player);
                return true;
            }

            if (args.length == 1) {
                ChatMessages.use(player);
                return true;
            }

            if (!(Main.broadcasts.get(player) == null)) {
                ChatMessages.alreadySent(player);
                return true;
            }

            if (CooldownManager.getCooldownTimer().containsKey(player.getUniqueId())) {
                ChatMessages.broadcastCooldown(player);
                return true;
            }

            if (Main.getEconomy().getBalance(player) < Config.getSettingsYaml().getInt("settings.broadcast-cost")) {
                ChatMessages.noMoney(player);
                return true;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(args[1]);
            for (int i = 2; i < args.length; i++) {
                sb.append(" " + args[i]);
            }

            try {
                Main.broadcasts.put(player, new Broadcast(player, sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ChatMessages.broadcastSent(player);
            Broadcast.notification();

            CooldownManager.getCooldownTimer().put(player.getUniqueId(), Config.getSettingsYaml().getInt("settings.broadcast-cooldown"));
            Main.getEconomy().withdrawPlayer(player, Config.getSettingsYaml().getInt("settings.broadcast-cost"));

        }

        if (args[0].equalsIgnoreCase("admin")) {

            if (!player.hasPermission("iridiumbroadcast.admin")) {
                ChatMessages.noPermission(player);
                return true;
            }

            BroadcastGUI.createInventory(player);

        }

        if (args[0].equalsIgnoreCase("clear")) {

            if (!player.hasPermission("iridiumbroadcast.admin.clear")) {
                ChatMessages.noPermission(player);
                return true;
            }

            ChatMessages.cleared(player);

            Main.broadcasts.clear();

            if (Config.getDatabaseYaml().getConfigurationSection("database") == null) {
                return true;
            }

            for (String s : Config.getDatabaseYaml().getConfigurationSection("database").getKeys(false)) {
                Config.getDatabaseYaml().set("database." + s, null);
            }

            try {
                Config.getDatabaseYaml().save(Config.getDatabaseFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (!player.hasPermission("iridiumbroadcast.admin.clear")) {
                ChatMessages.noPermission(player);
                return true;
            }

            Config.reloadConfigurations();

            ChatMessages.reloaded(player);

        }

        return true;
    }
}
