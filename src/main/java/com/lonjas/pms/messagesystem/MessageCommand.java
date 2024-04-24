package com.lonjas.pms.messagesystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageCommand implements CommandExecutor {

    private final MessageSystem messageSystem;

    public MessageCommand(MessageSystem messageSystem){
        this.messageSystem = messageSystem;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){

            if(strings.length >= 2){
                if(Bukkit.getPlayerExact(strings[0]) != null){
                    Player target = Bukkit.getPlayerExact(strings[0]);
                    assert target != null;
                    if(target.getUniqueId() != player.getUniqueId()){
                        StringBuilder message = new StringBuilder();
                        for(int i = 1; i < strings.length; i++){
                            message.append(strings[i]).append(" ");
                        }
                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Me" + ChatColor.GRAY + " -> " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + message);
                        target.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " -> " + ChatColor.GREEN + "Me" + ChatColor.GRAY + "] " + ChatColor.WHITE + message);
                        messageSystem.getLastMessage().put(player.getUniqueId(), target.getUniqueId());
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "You can't message yourself!");

                    }
                }
                else{
                    player.sendMessage(ChatColor.RED + "Player not found!");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /message <player> <message>");
            }
        }
        else{
            commandSender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
        }
        return false;
    }
}
