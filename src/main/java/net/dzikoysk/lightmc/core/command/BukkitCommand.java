/*
 * Copyright (c) 2015-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dzikoysk.lightmc.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;

public class BukkitCommand implements CommandExecutor, TabExecutor {

    private static final CommandMap commandMap;

    static {
        CommandMap map = null;
        try {
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            map = (CommandMap) f.get(Bukkit.getServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        commandMap = map;
    }

    private final BukkitCommandExecutor bukkitCommandExecutor;
    private final String command;
    private final String permission;
    private final List<String> aliases;

    public BukkitCommand(String command, BukkitCommandExecutor bukkitCommandExecutor, String permission, List<String> aliases) {
        this.bukkitCommandExecutor = bukkitCommandExecutor;
        this.command = command;
        this.permission = permission;
        this.aliases = aliases;
    }

    public void register() {
        BukkitCommandPerformer bukkitCommandPerformer = new BukkitCommandPerformer(command, this);
        if (aliases != null) {
            bukkitCommandPerformer.setAliases(aliases);
        }
        commandMap.register(command, bukkitCommandPerformer);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (permission != null && !sender.hasPermission(permission)) {
                return true;
            }
        }
        bukkitCommandExecutor.execute(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPermission() {
        return permission;
    }

    public String getCommand() {
        return command;
    }

    public BukkitCommandExecutor getBukkitCommandExecutor() {
        return bukkitCommandExecutor;
    }

    public static CommandMap getCommandMap() {
        return commandMap;
    }

}
