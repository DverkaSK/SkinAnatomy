package ru.dverkask.skinanatomy.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SkinAnatomyTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
                                                @NotNull Command command,
                                                @NotNull String label,
                                                @NotNull String[] args) {
        List<String> recommendations = new ArrayList<>();

        if (args.length == 1) {
            recommendations.add("set");
            recommendations.add("get");
        } else if (args.length == 2) {
            if (args[0].equals("set")) {
                recommendations.add("head");
                recommendations.add("body");
                recommendations.add("lefthand");
                recommendations.add("righthand");
                recommendations.add("leftleg");
                recommendations.add("rightleg");
            }
        }

        return recommendations;
    }
}
