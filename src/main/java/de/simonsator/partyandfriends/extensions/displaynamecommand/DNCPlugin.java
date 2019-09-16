package de.simonsator.partyandfriends.extensions.displaynamecommand;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.pafplayers.DisplayNameProvider;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerClass;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DNCPlugin extends PAFExtension implements DisplayNameProvider, Listener {
	private Map<UUID, String> displayNames = new HashMap<>();
	private ConfigurationCreator configuration;

	@Override
	public void onEnable() {
		try {
			configuration = new DNCConfig(new File(getConfigFolder(), "config.yml"), this);
			Friends.getInstance().addCommand(new FriendSetDisplayNameCommand(configuration.getStringList("Command.SetDisplayName.Names"),
					configuration.getInt("Command.SetDisplayName.Priority"),
					configuration.getString("Command.SetDisplayName.Help"), configuration.getString("Command.SetDisplayName.Permission"),
					this, configuration.getString("Messages.NotEnoughArguments"), configuration.getString("Messages.DisplayNameSet"),
					configuration.getString("Messages.PrefixTooLong"), configuration.getInt("General.MaxLength")));
			PAFPlayerClass.setDisplayNameProvider(this);
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onLeave(PlayerDisconnectEvent pDisconnectEvent) {
		displayNames.remove(pDisconnectEvent.getPlayer().getUniqueId());
	}

	public void removeDisplayName(UUID pUUID) {
		displayNames.remove(pUUID);
	}

	public void setDisplayNames(OnlinePAFPlayer pafPlayer, String pName) {
		displayNames.put(pafPlayer.getUniqueId(), configuration.getString("General.NameStyle").replace("[Prefix]", pName).replace("[PLAYER_NAME]", pafPlayer.getName()));
	}

	@Override
	public String getDisplayName(PAFPlayer pPlayer) {
		return pPlayer.getName();
	}

	@Override
	public String getDisplayName(OnlinePAFPlayer pPlayer) {
		String name = displayNames.get(pPlayer.getUniqueId());
		if (displayNames == null)
			return pPlayer.getName();
		return name;
	}
}
