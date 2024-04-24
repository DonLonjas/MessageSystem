package com.lonjas.pms.messagesystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;

public final class MessageSystem extends JavaPlugin implements Listener{

    private HashMap <UUID, UUID> lastMessage;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("message")).setExecutor(new MessageCommand(this));
        Objects.requireNonNull(getCommand("reply")).setExecutor(new ReplyCommand(this));

        getLogger().log(Level.INFO, ChatColor.GREEN + "MessageSystem has been enabled");

        lastMessage = new HashMap<>();
    }

    public HashMap<UUID, UUID> getLastMessage() {
        return lastMessage;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        lastMessage.remove(event.getPlayer().getUniqueId());
    }


    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, ChatColor.RED + "MessageSystem has been disabled");
    }


}
