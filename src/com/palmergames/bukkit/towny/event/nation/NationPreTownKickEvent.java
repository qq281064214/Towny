package com.palmergames.bukkit.towny.event.nation;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.Translation;

public class NationPreTownKickEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private final String townName;
	private final Town town;
	private final String nationName;
	private final Nation nation;
	private boolean isCancelled = false;
	private String cancelMessage = Translation.of("msg_err_command_disable");

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Cancellable event thrown when a nation kicks a town using /n kick.
	 * 
	 * @param nation Nation doing the kicking.
	 * @param town Town leaving the nation.
	 */
	public NationPreTownKickEvent(Nation nation, Town town) {
		super(!Bukkit.getServer().isPrimaryThread());
		this.townName = town.getName();
		this.town = town;
		this.nation = nation;
		this.nationName = nation.getName();
	}

	public String getTownName() {
		return townName;
	}

	public String getNationName() {
		return nationName;
	}

	public Town getTown() {
		return town;
	}

	public Nation getNation() {
		return nation;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		isCancelled = cancelled;
	}

	public String getCancelMessage() {
		return cancelMessage;
	}

	public void setCancelMessage(String cancelMessage) {
		this.cancelMessage = cancelMessage;
	}
}
