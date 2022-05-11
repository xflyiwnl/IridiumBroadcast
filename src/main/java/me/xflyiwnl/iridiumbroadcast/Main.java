package me.xflyiwnl.iridiumbroadcast;

import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.commands.BroadcastCommand;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.utils.BroadcastUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    public static HashMap<Player, Broadcast> broadcasts = new HashMap<Player, Broadcast>();

    private static Main main;

    @Override
    public void onEnable() {

        main = this;

        new Config();
        Bukkit.getPluginCommand("iridiumbroadcast").setExecutor(new BroadcastCommand());

        try {
            BroadcastUtil.loadBroadcasts();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Main getMain() {
        return main;
    }

    public static Broadcast getBroadcasts(Player player) {
        return broadcasts.get(player);
    }



}
