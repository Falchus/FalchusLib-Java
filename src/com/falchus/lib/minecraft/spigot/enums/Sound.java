package com.falchus.lib.minecraft.spigot.enums;

import java.util.function.Function;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sound {
	AMBIENT_CAVE(
		v -> switch (v) {
			case v1_8_8 -> "AMBIENCE_CAVE";
			default -> null;
		}
	),
	BLOCK_ANVIL_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "ANVIL_BREAK";
			default -> null;
		}
	),
	BLOCK_ANVIL_LAND(
		v -> switch (v) {
			case v1_8_8 -> "ANVIL_LAND";
			default -> null;
		}
	),
	BLOCK_ANVIL_USE(
		v -> switch (v) {
			case v1_8_8 -> "ANVIL_USE";
			default -> null;
		}
	),
	BLOCK_CHEST_CLOSE(
		v -> switch (v) {
			case v1_8_8 -> "CHEST_CLOSE";
			default -> null;
		}
	),
	BLOCK_CHEST_OPEN(
		v -> switch (v) {
			case v1_8_8 -> "CHEST_OPEN";
			default -> null;
		}
	),
	BLOCK_FIRE_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "FIRE";
			default -> null;
		}
	),
	BLOCK_FIRE_EXTINGUISH(
		v -> switch (v) {
			case v1_8_8 -> "FIZZ";
			default -> null;
		}
	),
	BLOCK_GLASS_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "GLASS";
			default -> null;
		}
	),
	BLOCK_GRASS_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_GRASS";
			default -> null;
		}
	),
	BLOCK_GRASS_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_GRASS";
			default -> null;
		}
	),
	BLOCK_GRAVEL_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_GRAVEL";
			default -> null;
		}
	),
	BLOCK_GRAVEL_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_GRAVEL";
			default -> null;
		}
	),
	BLOCK_LADDER_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_LADDER";
			default -> null;
		}
	),
	BLOCK_LAVA_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "LAVA";
			default -> null;
		}
	),
	BLOCK_LAVA_POP(
		v -> switch (v) {
			case v1_8_8 -> "LAVA_POP";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_BASEDRUM(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_BASS_DRUM";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_BASEDRUM";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_BASS(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_BASS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_BASS";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_GUITAR(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_BASS_GUITAR";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_GUITAR";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_HARP(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_PIANO";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_HARP";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_HAT(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_STICKS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_HAT";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_PLING(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_PLING";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_PLING";
			default -> null;
		}
	),
	BLOCK_NOTE_BLOCK_SNARE(
		v -> switch (v) {
			case v1_8_8 -> "NOTE_SNARE_DRUM";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_NOTE_SNARE";
			default -> null;
		}
	),
	BLOCK_PISTON_CONTRACT(
		v -> switch (v) {
			case v1_8_8 -> "PISTON_RETRACT";
			default -> null;
		}
	),
	BLOCK_PISTON_EXTEND(
		v -> switch (v) {
			case v1_8_8 -> "PISTON_EXTEND";
			default -> null;
		}
	),
	BLOCK_PORTAL_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "PORTAL";
			default -> null;
		}
	),
	BLOCK_PORTAL_TRAVEL(
		v -> switch (v) {
			case v1_8_8 -> "PORTAL_TRAVEL";
			default -> null;
		}
	),
	BLOCK_PORTAL_TRIGGER(
		v -> switch (v) {
			case v1_8_8 -> "PORTAL_TRIGGER";
			default -> null;
		}
	),
	BLOCK_SAND_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_SAND";
			default -> null;
		}
	),
	BLOCK_SAND_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_SAND";
			default -> null;
		}
	),
	BLOCK_SNOW_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_SNOW";
			default -> null;
		}
	),
	BLOCK_SNOW_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_SNOW";
			default -> null;
		}
	),
	BLOCK_STONE_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_STONE";
			default -> null;
		}
	),
	BLOCK_STONE_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_STONE";
			default -> null;
		}
	),
	BLOCK_WATER_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "WATER";
			default -> null;
		}
	),
	BLOCK_WOODEN_BUTTON_CLICK_OFF(
		v -> switch (v) {
			case v1_8_8 -> "WOOD_CLICK";
			default -> null;
		}
	),
	BLOCK_WOODEN_DOOR_CLOSE(
		v -> switch (v) {
			case v1_8_8 -> "DOOR_CLOSE";
			default -> null;
		}
	),
	BLOCK_WOODEN_DOOR_OPEN(
		v -> switch (v) {
			case v1_8_8 -> "DOOR_OPEN";
			default -> null;
		}
	),
	BLOCK_WOOD_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_WOOD";
			default -> null;
		}
	),
	BLOCK_WOOD_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_WOOD";
			default -> null;
		}
	),
	BLOCK_WOOL_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "DIG_WOOL";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_CLOTH_BREAK";
			default -> null;
		}
	),
	BLOCK_WOOL_STEP(
		v -> switch (v) {
			case v1_8_8 -> "STEP_WOOL";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BLOCK_CLOTH_STEP";
			default -> null;
		}
	),
	ENTITY_ARROW_HIT(
		v -> switch (v) {
			case v1_8_8 -> "ARROW_HIT";
			default -> null;
		}
	),
	ENTITY_ARROW_HIT_PLAYER(
		v -> switch (v) {
			case v1_8_8 -> "SUCCESSFUL_HIT";
			default -> null;
		}
	),
	ENTITY_ARROW_SHOOT(
		v -> switch (v) {
			case v1_8_8 -> "SHOOT_ARROW";
			default -> null;
		}
	),
	ENTITY_BAT_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "BAT_IDLE";
			default -> null;
		}
	),
	ENTITY_BAT_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "BAT_DEATH";
			default -> null;
		}
	),
	ENTITY_BAT_HURT(
		v -> switch (v) {
			case v1_8_8 -> "BAT_HURT";
			default -> null;
		}
	),
	ENTITY_BAT_LOOP(
		v -> switch (v) {
			case v1_8_8 -> "BAT_LOOP";
			default -> null;
		}
	),
	ENTITY_BAT_TAKEOFF(
		v -> switch (v) {
			case v1_8_8 -> "BAT_TAKEOFF";
			default -> null;
		}
	),
	ENTITY_BLAZE_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "BLAZE_BREATH";
			default -> null;
		}
	),
	ENTITY_BLAZE_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "BLAZE_DEATH";
			default -> null;
		}
	),
	ENTITY_BLAZE_HURT(
		v -> switch (v) {
			case v1_8_8 -> "BLAZE_HIT";
			default -> null;
		}
	),
	ENTITY_CAT_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "CAT_MEOW";
			default -> null;
		}
	),
	ENTITY_CAT_HISS(
		v -> switch (v) {
			case v1_8_8 -> "CAT_HISS";
			default -> null;
		}
	),
	ENTITY_CAT_HURT(
		v -> switch (v) {
			case v1_8_8 -> "CAT_HIT";
			default -> null;
		}
	),
	ENTITY_CAT_PURR(
		v -> switch (v) {
			case v1_8_8 -> "CAT_PURR";
			default -> null;
		}
	),
	ENTITY_CAT_PURREOW(
		v -> switch (v) {
			case v1_8_8 -> "CAT_PURREOW";
			default -> null;
		}
	),
	ENTITY_CHICKEN_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "CHICKEN_IDLE";
			default -> null;
		}
	),
	ENTITY_CHICKEN_EGG(
		v -> switch (v) {
			case v1_8_8 -> "CHICKEN_EGG_POP";
			default -> null;
		}
	),
	ENTITY_CHICKEN_HURT(
		v -> switch (v) {
			case v1_8_8 -> "CHICKEN_HURT";
			default -> null;
		}
	),
	ENTITY_CHICKEN_STEP(
		v -> switch (v) {
			case v1_8_8 -> "CHICKEN_WALK";
			default -> null;
		}
	),
	ENTITY_COW_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "COW_IDLE";
			default -> null;
		}
	),
	ENTITY_COW_HURT(
		v -> switch (v) {
			case v1_8_8 -> "COW_HURT";
			default -> null;
		}
	),
	ENTITY_COW_STEP(
		v -> switch (v) {
			case v1_8_8 -> "COW_WALK";
			default -> null;
		}
	),
	ENTITY_CREEPER_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "CREEPER_DEATH";
			default -> null;
		}
	),
	ENTITY_CREEPER_PRIMED(
		v -> switch (v) {
			case v1_8_8 -> "CREEPER_HISS";
			default -> null;
		}
	),
	ENTITY_DONKEY_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "DONKEY_IDLE";
			default -> null;
		}
	),
	ENTITY_DONKEY_ANGRY(
		v -> switch (v) {
			case v1_8_8 -> "DONKEY_ANGRY";
			default -> null;
		}
	),
	ENTITY_DONKEY_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "DONKEY_DEATH";
			default -> null;
		}
	),
	ENTITY_DONKEY_HURT(
		v -> switch (v) {
			case v1_8_8 -> "DONKEY_HIT";
			default -> null;
		}
	),
	ENTITY_ENDERMAN_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "ENDERMAN_IDLE";
			default -> null;
		}
	),
	ENTITY_ENDERMAN_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "ENDERMAN_DEATH";
			default -> null;
		}
	),
	ENTITY_ENDERMAN_HURT(
		v -> switch (v) {
			case v1_8_8 -> "ENDERMAN_HIT";
			default -> null;
		}
	),
	ENTITY_ENDERMAN_SCREAM(
		v -> switch (v) {
			case v1_8_8 -> "ENDERMAN_SCREAM";
			default -> null;
		}
	),
	ENTITY_ENDERMAN_STARE(
		v -> switch (v) {
			case v1_8_8 -> "ENDERMAN_STARE";
			default -> null;
		}
	),
	ENTITY_ENDERMAN_TELEPORT(
		v -> switch (v) {
			case v1_8_8 -> "ENDERMAN_TELEPORT";
			default -> null;
		}
	),
	ENTITY_ENDER_DRAGON_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "ENDERDRAGON_DEATH";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_ENDERDRAGON_DEATH";
			default -> null;
		}
	),
	ENTITY_ENDER_DRAGON_FLAP(
		v -> switch (v) {
			case v1_8_8 -> "ENDERDRAGON_WINGS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_ENDERDRAGON_FLAP";
			default -> null;
		}
	),
	ENTITY_ENDER_DRAGON_GROWL(
		v -> switch (v) {
			case v1_8_8 -> "ENDERDRAGON_GROWL";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_ENDERDRAGON_GROWL";
			default -> null;
		}
	),
	ENTITY_ENDER_DRAGON_HURT(
		v -> switch (v) {
			case v1_8_8 -> "ENDERDRAGON_HIT";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_ENDERDRAGON_HURT";
			default -> null;
		}
	),
	ENTITY_EXPERIENCE_ORB_PICKUP(
		v -> switch (v) {
			case v1_8_8 -> "ORB_PICKUP";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_BLAST(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_BLAST";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_BLAST";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_BLAST_FAR(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_BLAST2";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_BLAST_FAR";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_LARGE_BLAST(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_LARGE_BLAST";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_LARGE_BLAST";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_LARGE_BLAST2";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_LARGE_BLAST_FAR";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_LAUNCH(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_LAUNCH";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_LAUNCH";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_TWINKLE(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_TWINKLE";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_TWINKLE";
			default -> null;
		}
	),
	ENTITY_FIREWORK_ROCKET_TWINKLE_FAR(
		v -> switch (v) {
			case v1_8_8 -> "FIREWORK_TWINKLE2";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_FIREWORK_TWINKLE_FAR";
			default -> null;
		}
	),
	ENTITY_FISHING_BOBBER_SPLASH(
		v -> switch (v) {
			case v1_8_8 -> "SPLASH2";
			default -> null;
		}
	),
	ENTITY_GENERIC_BIG_FALL(
		v -> switch (v) {
			case v1_8_8 -> "FALL_BIG";
			default -> null;
		}
	),
	ENTITY_GENERIC_DRINK(
		v -> switch (v) {
			case v1_8_8 -> "DRINK";
			default -> null;
		}
	),
	ENTITY_GENERIC_EAT(
		v -> switch (v) {
			case v1_8_8 -> "EAT";
			default -> null;
		}
	),
	ENTITY_GENERIC_EXPLODE(
		v -> switch (v) {
			case v1_8_8 -> "EXPLODE";
			default -> null;
		}
	),
	ENTITY_GENERIC_SMALL_FALL(
		v -> switch (v) {
			case v1_8_8 -> "FALL_SMALL";
			default -> null;
		}
	),
	ENTITY_GENERIC_SPLASH(
		v -> switch (v) {
			case v1_8_8 -> "SPLASH";
			default -> null;
		}
	),
	ENTITY_GENERIC_SWIM(
		v -> switch (v) {
			case v1_8_8 -> "SWIM";
			default -> null;
		}
	),
	ENTITY_GHAST_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "GHAST_MOAN";
			default -> null;
		}
	),
	ENTITY_GHAST_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "GHAST_DEATH";
			default -> null;
		}
	),
	ENTITY_GHAST_HURT(
		v -> switch (v) {
			case v1_8_8 -> "GHAST_SCREAM2";
			default -> null;
		}
	),
	ENTITY_GHAST_SCREAM(
		v -> switch (v) {
			case v1_8_8 -> "GHAST_SCREAM";
			default -> null;
		}
	),
	ENTITY_GHAST_SHOOT(
		v -> switch (v) {
			case v1_8_8 -> "GHAST_FIREBALL";
			default -> null;
		}
	),
	ENTITY_GHAST_WARN(
		v -> switch (v) {
			case v1_8_8 -> "GHAST_CHARGE";
			default -> null;
		}
	),
	ENTITY_HORSE_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_IDLE";
			default -> null;
		}
	),
	ENTITY_HORSE_ANGRY(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_ANGRY";
			default -> null;
		}
	),
	ENTITY_HORSE_ARMOR(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_ARMOR";
			default -> null;
		}
	),
	ENTITY_HORSE_BREATHE(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_BREATHE";
			default -> null;
		}
	),
	ENTITY_HORSE_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_DEATH";
			default -> null;
		}
	),
	ENTITY_HORSE_GALLOP(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_GALLOP";
			default -> null;
		}
	),
	ENTITY_HORSE_HURT(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_HIT";
			default -> null;
		}
	),
	ENTITY_HORSE_JUMP(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_JUMP";
			default -> null;
		}
	),
	ENTITY_HORSE_LAND(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_LAND";
			default -> null;
		}
	),
	ENTITY_HORSE_SADDLE(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_SADDLE";
			default -> null;
		}
	),
	ENTITY_HORSE_STEP(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_SOFT";
			default -> null;
		}
	),
	ENTITY_HORSE_STEP_WOOD(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_WOOD";
			default -> null;
		}
	),
	ENTITY_IRON_GOLEM_ATTACK(
		v -> switch (v) {
			case v1_8_8 -> "IRONGOLEM_THROW";
			default -> null;
		}
	),
	ENTITY_IRON_GOLEM_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "IRONGOLEM_DEATH";
			default -> null;
		}
	),
	ENTITY_IRON_GOLEM_HURT(
		v -> switch (v) {
			case v1_8_8 -> "IRONGOLEM_HIT";
			default -> null;
		}
	),
	ENTITY_IRON_GOLEM_STEP(
		v -> switch (v) {
			case v1_8_8 -> "IRONGOLEM_WALK";
			default -> null;
		}
	),
	ENTITY_ITEM_BREAK(
		v -> switch (v) {
			case v1_8_8 -> "ITEM_BREAK";
			default -> null;
		}
	),
	ENTITY_ITEM_PICKUP(
		v -> switch (v) {
			case v1_8_8 -> "ITEM_PICKUP";
			default -> null;
		}
	),
	ENTITY_LIGHTNING_BOLT_THUNDER(
		v -> switch (v) {
			case v1_8_8 -> "AMBIENCE_THUNDER";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENTITY_LIGHTNING_THUNDER";
			default -> null;
		}
	),
	ENTITY_MAGMA_CUBE_JUMP(
		v -> switch (v) {
			case v1_8_8 -> "MAGMACUBE_JUMP";
			default -> null;
		}
	),
	ENTITY_MAGMA_CUBE_SQUISH(
		v -> switch (v) {
			case v1_8_8 -> "MAGMACUBE_WALK";
			default -> null;
		}
	),
	ENTITY_MAGMA_CUBE_SQUISH_SMALL(
		v -> switch (v) {
			case v1_8_8 -> "MAGMACUBE_WALK2";
			default -> null;
		}
	),
	ENTITY_MINECART_INSIDE(
		v -> switch (v) {
			case v1_8_8 -> "MINECART_INSIDE";
			default -> null;
		}
	),
	ENTITY_MINECART_RIDING(
		v -> switch (v) {
			case v1_8_8 -> "MINECART_BASE";
			default -> null;
		}
	),
	ENTITY_PIG_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "PIG_IDLE";
			default -> null;
		}
	),
	ENTITY_PIG_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "PIG_DEATH";
			default -> null;
		}
	),
	ENTITY_PIG_STEP(
		v -> switch (v) {
			case v1_8_8 -> "PIG_WALK";
			default -> null;
		}
	),
	ENTITY_PLAYER_BURP(
		v -> switch (v) {
			case v1_8_8 -> "BURP";
			default -> null;
		}
	),
	ENTITY_PLAYER_HURT(
		v -> switch (v) {
			case v1_8_8 -> "HURT_FLESH";
			default -> null;
		}
	),
	ENTITY_PLAYER_LEVELUP(
		v -> switch (v) {
			case v1_8_8 -> "LEVEL_UP";
			default -> null;
		}
	),
	ENTITY_SHEEP_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "SHEEP_IDLE";
			default -> null;
		}
	),
	ENTITY_SHEEP_SHEAR(
		v -> switch (v) {
			case v1_8_8 -> "SHEEP_SHEAR";
			default -> null;
		}
	),
	ENTITY_SHEEP_STEP(
		v -> switch (v) {
			case v1_8_8 -> "SHEEP_WALK";
			default -> null;
		}
	),
	ENTITY_SILVERFISH_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "SILVERFISH_IDLE";
			default -> null;
		}
	),
	ENTITY_SILVERFISH_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "SILVERFISH_KILL";
			default -> null;
		}
	),
	ENTITY_SILVERFISH_HURT(
		v -> switch (v) {
			case v1_8_8 -> "SILVERFISH_HIT";
			default -> null;
		}
	),
	ENTITY_SILVERFISH_STEP(
		v -> switch (v) {
			case v1_8_8 -> "SILVERFISH_WALK";
			default -> null;
		}
	),
	ENTITY_SKELETON_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "SKELETON_IDLE";
			default -> null;
		}
	),
	ENTITY_SKELETON_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "SKELETON_DEATH";
			default -> null;
		}
	),
	ENTITY_SKELETON_HORSE_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_SKELETON_IDLE";
			default -> null;
		}
	),
	ENTITY_SKELETON_HORSE_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_SKELETON_DEATH";
			default -> null;
		}
	),
	ENTITY_SKELETON_HORSE_HURT(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_SKELETON_HIT";
			default -> null;
		}
	),
	ENTITY_SKELETON_HURT(
		v -> switch (v) {
			case v1_8_8 -> "SKELETON_HURT";
			default -> null;
		}
	),
	ENTITY_SKELETON_STEP(
		v -> switch (v) {
			case v1_8_8 -> "SKELETON_WALK";
			default -> null;
		}
	),
	ENTITY_SLIME_ATTACK(
		v -> switch (v) {
			case v1_8_8 -> "SLIME_ATTACK";
			default -> null;
		}
	),
	ENTITY_SLIME_JUMP(
		v -> switch (v) {
			case v1_8_8 -> "SLIME_WALK";
			default -> null;
		}
	),
	ENTITY_SLIME_JUMP_SMALL(
		v -> switch (v) {
			case v1_8_8 -> "SLIME_WALK2";
			default -> null;
		}
	),
	ENTITY_SPIDER_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "SPIDER_IDLE";
			default -> null;
		}
	),
	ENTITY_SPIDER_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "SPIDER_DEATH";
			default -> null;
		}
	),
	ENTITY_SPIDER_STEP(
		v -> switch (v) {
			case v1_8_8 -> "SPIDER_WALK";
			default -> null;
		}
	),
	ENTITY_TNT_PRIMED(
		v -> switch (v) {
			case v1_8_8 -> "FUSE";
			default -> null;
		}
	),
	ENTITY_VILLAGER_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "VILLAGER_IDLE";
			default -> null;
		}
	),
	ENTITY_VILLAGER_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "VILLAGER_DEATH";
			default -> null;
		}
	),
	ENTITY_VILLAGER_HURT(
		v -> switch (v) {
			case v1_8_8 -> "VILLAGER_HIT";
			default -> null;
		}
	),
	ENTITY_VILLAGER_NO(
		v -> switch (v) {
			case v1_8_8 -> "VILLAGER_NO";
			default -> null;
		}
	),
	ENTITY_VILLAGER_TRADE(
		v -> switch (v) {
			case v1_8_8 -> "VILLAGER_HAGGLE";
			default -> null;
		}
	),
	ENTITY_VILLAGER_YES(
		v -> switch (v) {
			case v1_8_8 -> "VILLAGER_YES";
			default -> null;
		}
	),
	ENTITY_WITHER_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "WITHER_IDLE";
			default -> null;
		}
	),
	ENTITY_WITHER_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "WITHER_DEATH";
			default -> null;
		}
	),
	ENTITY_WITHER_HURT(
		v -> switch (v) {
			case v1_8_8 -> "WITHER_HURT";
			default -> null;
		}
	),
	ENTITY_WITHER_SHOOT(
		v -> switch (v) {
			case v1_8_8 -> "WITHER_SHOOT";
			default -> null;
		}
	),
	ENTITY_WITHER_SPAWN(
		v -> switch (v) {
			case v1_8_8 -> "WITHER_SPAWN";
			default -> null;
		}
	),
	ENTITY_WOLF_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_BARK";
			default -> null;
		}
	),
	ENTITY_WOLF_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_DEATH";
			default -> null;
		}
	),
	ENTITY_WOLF_GROWL(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_GROWL";
			default -> null;
		}
	),
	ENTITY_WOLF_HOWL(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_HOWL";
			default -> null;
		}
	),
	ENTITY_WOLF_HURT(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_HURT";
			default -> null;
		}
	),
	ENTITY_WOLF_PANT(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_PANT";
			default -> null;
		}
	),
	ENTITY_WOLF_SHAKE(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_SHAKE";
			default -> null;
		}
	),
	ENTITY_WOLF_STEP(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_WALK";
			default -> null;
		}
	),
	ENTITY_WOLF_WHINE(
		v -> switch (v) {
			case v1_8_8 -> "WOLF_WHINE";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_IDLE";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_ATTACK_IRON_DOOR(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_METAL";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_WOOD";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_BREAK_WOODEN_DOOR(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_WOODBREAK";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_DEATH";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_HORSE_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_ZOMBIE_IDLE";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_HORSE_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_ZOMBIE_DEATH";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_HORSE_HURT(
		v -> switch (v) {
			case v1_8_8 -> "HORSE_ZOMBIE_HIT";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_HURT(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_HURT";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_INFECT(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_INFECT";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_STEP(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_WALK";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_VILLAGER_CONVERTED(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_UNFECT";
			default -> null;
		}
	),
	ENTITY_ZOMBIE_VILLAGER_CURE(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_REMEDY";
			default -> null;
		}
	),
	ENTITY_ZOMBIFIED_PIGLIN_AMBIENT(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_PIG_IDLE";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2 -> "ENTITY_ZOMBIE_PIG_AMBIENT";
			default -> null;
		}
	),
	ENTITY_ZOMBIFIED_PIGLIN_ANGRY(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_PIG_ANGRY";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2 -> "ENTITY_ZOMBIE_PIG_ANGRY";
			default -> null;
		}
	),
	ENTITY_ZOMBIFIED_PIGLIN_DEATH(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_PIG_DEATH";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2 -> "ENTITY_ZOMBIE_PIG_DEATH";
			default -> null;
		}
	),
	ENTITY_ZOMBIFIED_PIGLIN_HURT(
		v -> switch (v) {
			case v1_8_8 -> "ZOMBIE_PIG_HURT";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2 -> "ENTITY_ZOMBIE_PIG_HURT";
			default -> null;
		}
	),
	ITEM_FLINTANDSTEEL_USE(
		v -> switch (v) {
			case v1_8_8 -> "FIRE_IGNITE";
			default -> null;
		}
	),
	UI_BUTTON_CLICK(
		v -> switch (v) {
			case v1_8_8 -> "CLICK";
			default -> null;
		}
	),
	WEATHER_RAIN(
		v -> switch (v) {
			case v1_8_8 -> "AMBIENCE_RAIN";
			default -> null;
		}
	);

	private final Function<Version, String> name;

	Sound() {
		this(null);
	}

	public String getName() {
		if (name != null) {
			String n = name.apply(ServerUtils.getVersion());
			if (n != null) {
				return n;
			}
		}
		return name();
	}

	public org.bukkit.Sound toBukkit() {
		return org.bukkit.Sound.valueOf(getName());
	}
}
