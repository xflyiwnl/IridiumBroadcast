package me.xflyiwnl.iridiumbroadcast.config;

import me.xflyiwnl.iridiumbroadcast.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private static File settingsFile;
    private static YamlConfiguration settingsYaml;

    private static File messagesFile;
    private static YamlConfiguration messagesYaml;

    private static File databaseFile;
    private static YamlConfiguration databaseYaml;

    public Config() {

        settingsFile = new File(Main.getMain().getDataFolder(), "settings.yml");
        if (!settingsFile.exists()) {
            Main.getMain().saveResource("settings.yml", true);
            Bukkit.getConsoleSender().sendMessage("settings.yml не найден, создаём новый конфиг...");
        }
        settingsYaml = YamlConfiguration.loadConfiguration(settingsFile);

        messagesFile = new File(Main.getMain().getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            Main.getMain().saveResource("messages.yml", true);
            Bukkit.getConsoleSender().sendMessage("messages.yml не найден, создаём новый конфиг...");
        }
        messagesYaml = YamlConfiguration.loadConfiguration(messagesFile);

        databaseFile = new File(Main.getMain().getDataFolder(), "database.yml");
        if (!databaseFile.exists()) {
            Main.getMain().saveResource("database.yml", true);
            Bukkit.getConsoleSender().sendMessage("database.yml не найден, создаём новый конфиг...");
        }
        databaseYaml = YamlConfiguration.loadConfiguration(databaseFile);

    }

    public static File getSettingsFile() {
        return settingsFile;
    }

    public static YamlConfiguration getSettingsYaml() {
        return settingsYaml;
    }

    public static File getMessagesFile() {
        return messagesFile;
    }

    public static YamlConfiguration getMessagesYaml() {
        return messagesYaml;
    }

    public static File getDatabaseFile() {
        return databaseFile;
    }

    public static YamlConfiguration getDatabaseYaml() {
        return databaseYaml;
    }
}
