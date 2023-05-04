package de.knacrack.journeymc.utils;

import de.knacrack.journeymc.utils.playerprofile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ServerScoreboard {

    private static final ServerScoreboard serverScoreboard = new ServerScoreboard();

    public static ServerScoreboard getServerScoreboard() {
        return serverScoreboard;
    }

    private Scoreboard scoreboard;
    private Objective objective;

    public ServerScoreboard() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective("a", "b");

        registerGroups();
        addPlayers();
    }

    private void registerGroups() {
        for (Group group : Group.values()) {
            this.scoreboard.registerNewTeam(group.getTablistGroupName());
        }
    }

    private void addPlayers() {
        Bukkit.getOnlinePlayers().forEach(this::addPlayer);
        Bukkit.getOnlinePlayers().forEach(this::setScoreboard);
    }

    public void addPlayer(Player player) {
        Group group = PlayerProfile.getCharacter(player).getGroup();
        Team team = this.scoreboard.getTeam(group.getTablistGroupName());

        assert team != null;
        team.addPlayer(player);
    }

    public void removePlayer(Player player) {
        Group group = PlayerProfile.getCharacter(player).getGroup();
        Team team = this.scoreboard.getTeam(group.getTablistGroupName());

        assert team != null;
        team.removePlayer(player);
    }

    public void setScoreboard(Player player) {
        player.setScoreboard(this.scoreboard);
    }
}