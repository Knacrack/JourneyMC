package de.knacrack.journeymc.utils.playerprofile;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.utils.Group;
import de.knacrack.journeymc.utils.uuid.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class PlayerProfile {

    private static final HashMap<String, PlayerProfile> cache = new HashMap<>();

    private final String uuid;

    private Group group;

    private double coins;

    PlayerProfile(String uniqueId) {
        this.uuid = uniqueId;
    }

    public PlayerProfile getProfile() {
        return this;
    }

    private PlayerProfile load() {
        PlayerProfile playerStats = this;

        File file = getFile();
        if (!file.exists()) return playerStats;

        try {
            FileReader fileReader = new FileReader(file);
            playerStats = Main.getGson().fromJson(fileReader, getClass());
            fileReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return playerStats;
    }

    public void save() {
        File file = getFile();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            Main.getGson().toJson(this, fileWriter);

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static File getDirectory() {
        return new File(Main.getInstance().getDataFolder().getPath() + "/profiles/");
    }

    private File getFile() {
        File directory = getDirectory();
        if (!directory.mkdirs()) {
            directory.mkdir();
        }

        return new File(directory.getPath(), this.uuid + ".json");
    }

    public static PlayerProfile getCharacter(String uuid) {
        if (!cache.containsKey(uuid)) {
            PlayerProfile playerStats = new PlayerProfile(uuid);
            playerStats = playerStats.load();
            cache.put(uuid, playerStats);
        }

        return cache.get(uuid);
    }

    public static Collection<PlayerProfile> getAllStats() {
        return cache.values();
    }

    public static void dispose(String uuid) {
        PlayerProfile playerStats = cache.get(uuid);
        if (playerStats != null) {
            playerStats.save();
        }
        cache.remove(uuid);
    }

    public String getCustomName() {
        return "§r" + getGroup().getPrefix() + getName() + "§r" + getGroup().getSuffix();
    }

    private String getName() {
        UUID uniqueId = UUID.fromString(uuid);

        Player player = Bukkit.getPlayer(uniqueId);
        if (player != null) return player.getName();

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uniqueId);
        if (offlinePlayer.getName() != null) return offlinePlayer.getName();

        return UUIDFetcher.getName(uniqueId);
    }

    public Group getGroup() {
        if (this.group == null) {
            this.group = Group.PLAYER;
        }
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
        save();
    }

    public double getCoins() {
        return this.coins;
    }

    public void setCoins(double coins) {
        if (coins < 0) return;

        this.coins = coins;
        save();
    }

    public void addCoins(double coins) {
        setCoins(this.coins + coins);
    }

    public void removeCoins(double coins) {
        setCoins(this.coins - coins);
    }

    public boolean hasEnoughCoins(double coins) {
        return coins > 0 && coins > getCoins();
    }

    public void resetCoins() {
        coins = 0;
    }

    public static PlayerProfile getCharacter(UUID uuid) {
        return uuid == null ? null : getCharacter(uuid.toString());
    }

    public static PlayerProfile getCharacter(Player player) {
        return player == null ? null : getCharacter(player.getUniqueId().toString());
    }

    public static PlayerProfile getCharacter(OfflinePlayer player) {
        if (player == null) return null;

        return getCharacter(player.getUniqueId().toString());
    }

    public static PlayerProfile getCharacter(CommandSender player) {
        if (player == null) return null;
        if (!(player instanceof Player)) return null;

        return getCharacter(((Player) player).getUniqueId().toString());
    }
}
