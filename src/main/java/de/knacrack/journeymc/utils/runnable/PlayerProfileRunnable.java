package de.knacrack.journeymc.utils.runnable;

import de.knacrack.journeymc.utils.playerprofile.PlayerProfile;

public class PlayerProfileRunnable implements Runnable {
    @Override
    public void run() {
        PlayerProfile.getAllStats().forEach(PlayerProfile::save);
    }
}
