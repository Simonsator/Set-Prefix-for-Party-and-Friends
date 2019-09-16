package de.simonsator.partyandfriends.extensions.displaynamecommand;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;

import java.util.List;

public class FriendSetDisplayNameCommand extends FriendSubCommand {
	private final DNCPlugin PLUGIN;
	private final String NOT_ENOUGH_NAMES;
	private final String DISPLAYNAME_SET;
	private final int MAX_LENGTH;
	private final String TOO_LONG;

	public FriendSetDisplayNameCommand(List<String> pCommands, int pPriority, String pHelp, String pPermission, DNCPlugin pPlugin, String pNotEnoughNames, String pDisplayNameSet, String pTooLong, int pMaxLength) {
		super(pCommands, pPriority, pHelp, pPermission);
		PLUGIN = pPlugin;
		NOT_ENOUGH_NAMES = PREFIX + pNotEnoughNames;
		DISPLAYNAME_SET = PREFIX + pDisplayNameSet;
		TOO_LONG = pTooLong;
		MAX_LENGTH = pMaxLength;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (args.length == 1) {
			pPlayer.sendMessage(NOT_ENOUGH_NAMES);
			return;
		}
		if (args.length > MAX_LENGTH) {
			pPlayer.sendMessage(TOO_LONG);
			return;
		}
		PLUGIN.setDisplayNames(pPlayer, args[1]);
		pPlayer.sendMessage(DISPLAYNAME_SET);
	}
}
