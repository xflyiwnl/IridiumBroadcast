package me.xflyiwnl.iridiumbroadcast.manager;

import me.xflyiwnl.iridiumbroadcast.Main;
import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.chat.ChatMessages;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.gui.BroadcastGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class EventManager implements Listener {

    @EventHandler
    public void onClickedSlot(InventoryClickEvent event) throws IOException {

        Inventory inv = event.getInventory();
        Integer slot = event.getSlot();
        Player player = (Player) event.getWhoClicked();

        if (inv == null) {
            return;
        }

        if (inv.equals(BroadcastGUI.getBroadcastGUI())) {

            event.setCancelled(true);

            if (!BroadcastGUI.getSlot().containsKey(slot)) {
                return;
            }

            if (event.getClick().isLeftClick()) {

                BroadcastManager.broadcast(BroadcastGUI.getSlot().get(slot), player,
                        BroadcastManager.broadcasts.get(BroadcastGUI.getSlot().get(slot)).getBroadcast());

                BroadcastManager.broadcasts.remove(BroadcastGUI.getSlot().get(slot));

                Config.getDatabaseYaml().set("database." + BroadcastGUI.getSlot().get(slot).getName(), null);
                Config.getDatabaseYaml().save(Config.getDatabaseFile());

                player.closeInventory();

                ChatMessages.accepted(player);

                if (BroadcastGUI.getSlot().get(slot).isOnline()) {
                    ChatMessages.broadcastAccepted(BroadcastGUI.getSlot().get(slot));

                    return;
                }

                return;
            }
            if (event.getClick().isRightClick()) {

                BroadcastManager.broadcasts.remove(BroadcastGUI.getSlot().get(slot));

                Config.getDatabaseYaml().set("database." + BroadcastGUI.getSlot().get(slot).getName(), null);
                Config.getDatabaseYaml().save(Config.getDatabaseFile());

                player.closeInventory();

                ChatMessages.refused(player);

                if (BroadcastGUI.getSlot().get(slot).isOnline()) {
                    ChatMessages.broadcastRefused(BroadcastGUI.getSlot().get(slot));

                    return;
                }
            }

        }

    }

}
