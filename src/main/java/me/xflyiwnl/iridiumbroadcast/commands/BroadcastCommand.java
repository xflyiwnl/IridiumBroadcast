package me.xflyiwnl.iridiumbroadcast.commands;

import me.xflyiwnl.iridiumbroadcast.Main;
import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.chat.ChatMessages;
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

        if (!(Main.broadcasts.get(player) == null)) {
            ChatMessages.alreadySent(player);
            return true;
        }

        try {
            Main.broadcasts.put(player, new Broadcast(player, args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
