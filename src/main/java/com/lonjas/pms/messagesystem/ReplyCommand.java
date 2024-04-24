package com.lonjas.pms.messagesystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public class ReplyCommand implements CommandExecutor {

    private final MessageSystem messageSystem;

    public ReplyCommand(MessageSystem messageSystem){
        this.messageSystem = messageSystem;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,@NotNull Command command,@NotNull String s,@NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            if(strings.length >= 1) {
                if(messageSystem.getLastMessage().containsKey(player.getUniqueId())) {
                    UUID uuid = messageSystem.getLastMessage().get(player.getUniqueId());
                    if(Bukkit.getPlayer(uuid) !=null){
                        Player target = Bukkit.getPlayer(uuid);
                        assert target != null;
                        if(target.getUniqueId() != player.getUniqueId()){
                            StringBuilder message = new StringBuilder();
                            for (String string : strings) {
                                message.append(string).append(" ");
                            }
                            player.sendMessage("§7[" + "§a" + "Me" + "§7" + " -> " + "§a" + target.getName() + "§7" + "] " + "§f" + message);
                            target.sendMessage("§7[" + "§a" + player.getName() + "§7" + " -> " + "§a" + "Me" + "§7" + "] " + "§f" + message);
                            messageSystem.getLastMessage().put(player.getUniqueId(), target.getUniqueId());
                        }
                        else {
                            player.sendMessage("§cYou can't message yourself!");
                        }
                    }
                    else{
                        player.sendMessage("§cPlayer not found!");
                    }

                }
                else {
                    player.sendMessage("§cYou have no one to reply to!");
                }
            }
            else {
                player.sendMessage("§cInvalid arguments! Usage: /reply <message>");
            }
        }
        return false;
    }
}
