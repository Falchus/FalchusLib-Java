package com.falchus.lib.minecraft.spigot.wrapper.world.item;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedItemStack extends SpigotWrapper implements ItemStack { // TODO
	
	Field count;
	Field popTime;

	private WrappedItemStack(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "ItemStack",
			worldItem + "ItemStack"
		));
		
		count = getField("count");
		popTime = getFirstField(
			"popTime",
			"c"
		);
	}
	
	@Override
	public int getCount() {
		return getFieldValue(count);
	}
	
	@Override
	public void setCount(int count) {
		setField(this.count, count);
	}
	
	@Override
	public int getPopTime() {
		return getFieldValue(popTime);
	}
	
	@Override
	public void setPopTime(int popTime) {
		setField(this.popTime, popTime);
	}
}
