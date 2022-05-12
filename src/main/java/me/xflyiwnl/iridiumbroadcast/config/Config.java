package me.xflyiwnl.iridiumbroadcast.config;

import me.xflyiwnl.iridiumbroadcast.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private static File settingsFile;
    private static YamlConfiguration settingsYaml;

    private static File languageFile;
    private static YamlConfiguration languageYaml;

    private static File databaseFile;
    private static YamlConfiguration databaseYaml;

    private static File guiFile;
    private static YamlConfiguration guiYaml;

    public Config() {

        settingsFile = new File(Main.getMain().getDataFolder(), "settings.yml");
        if (!settingsFile.exists()) {
            Main.getMain().saveResource("settings.yml", true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "Iridium Broadcast " + ChatColor.GRAY + "| " + ChatColor.WHITE + "settings.yml не найден, создание нового конфига...");
        }
        settingsYaml = YamlConfiguration.loadConfiguration(settingsFile);

        languageFile = new File(Main.getMain().getDataFolder(), "language.yml");
        if (!languageFile.exists()) {
            Main.getMain().saveResource("language.yml", true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "Iridium Broadcast " + ChatColor.GRAY + "| " + ChatColor.WHITE + "language.yml не найден, создание нового конфига...");
        }
        languageYaml = YamlConfiguration.loadConfiguration(languageFile);

        databaseFile = new File(Main.getMain().getDataFolder(), "database.yml");
        if (!databaseFile.exists()) {
            Main.getMain().saveResource("database.yml", true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "Iridium Broadcast " + ChatColor.GRAY + "| " + ChatColor.WHITE + "database.yml не найден, создание нового конфига...");
        }
        databaseYaml = YamlConfiguration.loadConfiguration(databaseFile);

        guiFile = new File(Main.getMain().getDataFolder(), "gui.yml");
        if (!guiFile.exists()) {
            Main.getMain().saveResource("gui.yml", true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "Iridium Broadcast " + ChatColor.GRAY + "| " + ChatColor.WHITE + "gui.yml не найден, создание нового конфига...");
        }
        guiYaml = YamlConfiguration.loadConfiguration(guiFile);

    }

    public static void reloadConfigurations() {
        settingsYaml = YamlConfiguration.loadConfiguration(settingsFile);
        languageYaml = YamlConfiguration.loadConfiguration(languageFile);
        guiYaml = YamlConfiguration.loadConfiguration(guiFile);
    }

    public static File getSettingsFile() {
        return settingsFile;
    }

    public static YamlConfiguration getSettingsYaml() {
        return settingsYaml;
    }

    public static File getLanguageFile() {
        return languageFile;
    }

    public static YamlConfiguration getLanguageYaml() {
        return languageYaml;
    }

    public static File getDatabaseFile() {
        return databaseFile;
    }

    public static YamlConfiguration getDatabaseYaml() {
        return databaseYaml;
    }

    public static File getGuiFile() {
        return guiFile;
    }

    public static YamlConfiguration getGuiYaml() {
        return guiYaml;
    }
}
