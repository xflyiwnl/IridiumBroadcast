package me.xflyiwnl.iridiumbroadcast;

import me.xflyiwnl.iridiumbroadcast.commands.BroadcastCommand;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;

    @Override
    public void onEnable() {

        main = this;

        new Config();
        Bukkit.getPluginCommand("iridiumbroadcast").setExecutor(new BroadcastCommand());
    }

    public static Main getMain() {
        return main;
    }
}
