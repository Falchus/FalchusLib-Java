package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import com.falchus.lib.minecraft.spigot.packets.wrapper.block.PacketBlock;

public interface PacketBlockDig extends PacketBlock {
	
	public enum Direction {
		DOWN,
		UP,
		NORTH,
		SOUTH,
		WEST,
		EAST
	}
	Direction getDirection();
	void setDirection(Direction direction);
	
	public enum Action {
		START_DESTROY_BLOCK,
		ABORT_DESTROY_BLOCK,
		STOP_DESTROY_BLOCK,
		DROP_ALL_ITEMS,
		DROP_ITEM,
		RELEASE_USE_ITEM
	}
	Action getAction();
	void setAction(Action action);
}
