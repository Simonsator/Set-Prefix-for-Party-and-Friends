package de.simonsator.partyandfriends.extensions.displaynamecommand;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class DNCConfig extends ConfigurationCreator {
	protected DNCConfig(File file, PAFExtension pPlugin) throws IOException {
		super(file, pPlugin);
		readFile();
		loadDefaults();
		saveFile();
		process();
	}

	private void loadDefaults() {
		set("General.NameStyle", "[[Prefix]] [PLAYER_NAME]");
		set("General.MaxLength", 16);
		set("Command.SetDisplayName.Names", "setname", "setprefix");
		set("Command.SetDisplayName.Priority", 0);
		set("Command.SetDisplayName.Permission", "");
		set("Command.SetDisplayName.Help", "&8/&5friend setprefix [Prefix] &8- &7Set your own Prefix");
		set("Messages.NotEnoughArguments", " &7You need to give a Prefix which should be set.");
		set("Messages.DisplayNameSet", " &7The prefix was set.");
		set("Messages.PrefixTooLong", " &7The chosen Prefix is too long.");
	}

}
