package me.xflyiwnl.iridiumbroadcast.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> argument = new ArrayList<>();

        if (argument.isEmpty()) {
            if (sender.hasPermission("iridiumbroadcast.broadcast")) {
                argument.add("broadcast");
            }

            if (sender.hasPermission("iridiumbroadcast.admin")) {
                argument.add("admin");
            }

            if (sender.hasPermission("iridiumbroadcast.admin.clear")) {
                argument.add("clear");
            }

            if (sender.hasPermission("iridiumbroadcast.admin.reload")) {
                argument.add("reload");
            }

        }

        if (args.length <= 1) {
            return argument;
        }

        return null;
    }
}
