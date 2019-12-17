package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.MultiversionMaterials;

import java.util.Arrays;
import java.util.List;

public class Shop {

    public List<String> lore = Arrays.asList("&7Buy Price: &c${buyvaultprice}", "&7Sell Price: &a${sellvaultprice}");

    public List<ShopObject> shop = Arrays.asList(
            new ShopObject(MultiversionMaterials.GRASS_BLOCK, "&b&lBlocks", "Blocks", Arrays.asList(
                    new ShopItem(MultiversionMaterials.GRASS_BLOCK, "&b&lGrass Block", 64, 50, 5, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.DIRT, "&b&lGrass Block", 64, 30, 3, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GRAVEL, "&b&lGrass Block", 64, 50, 5, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.COBBLESTONE, "&b&lGrass Block", 64, 30, 3, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.MOSSY_COBBLESTONE, "&b&lGrass Block", 64, 50, 5, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.STONE, "&b&lStone", 64, 50, 5, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.GRANITE, "&b&lGranite", 64, 30, 8, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.DIORITE, "&b&lDiorite", 64, 30, 3, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.ANDESITE, "&b&lAndesite", 64, 30, 3, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.OAK_LOG, "&b&lOak Log", 32, 100, 10, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.SPRUCE_LOG, "&b&lSpruce Log", 32, 100, 10, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.BIRCH_LOG, "&b&lBirch Log", 32, 100, 10, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.JUNGLE_LOG, "&b&lJungle Log", 32, 100, 10, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.ACACIA_LOG, "&b&lAcacia Log", 32, 100, 10, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.DARK_OAK_LOG, "&b&lDark Oak Log", 32, 100, 10, 0, 0, 28, 1),
                    new ShopItem(MultiversionMaterials.SNOW_BLOCK, "&b&lSnow Block", 16, 20, 2, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.ICE, "&b&lIce", 16, 30, 3, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.PACKED_ICE, "&b&lPacked Ice", 16, 30, 3, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.SPONGE, "&b&lSponge", 8, 100, 10, 0, 0, 32, 1),
                    new ShopItem(MultiversionMaterials.SAND, "&b&lSand", 64, 30, 3, 0, 0, 33, 1),
                    new ShopItem(MultiversionMaterials.SANDSTONE, "&b&lSandstone", 32, 30, 3, 0, 0, 34, 1),
                    new ShopItem(MultiversionMaterials.RED_SANDSTONE, "&b&lRed Sandstone", 32, 30, 3, 0, 0, 10, 2),
                    new ShopItem(MultiversionMaterials.GLASS, "&b&lGlass", 16, 20, 4, 0, 0, 11, 2),
                    new ShopItem(MultiversionMaterials.CLAY, "&b&lClay", 32, 40, 4, 0, 0, 12, 2),
                    new ShopItem(MultiversionMaterials.TERRACOTTA, "&b&lTerracotta", 32, 50, 5, 0, 0, 13, 2),
                    new ShopItem(MultiversionMaterials.BRICK, "&b&lBrick", 64, 70, 7, 0, 0, 14, 2),
                    new ShopItem(MultiversionMaterials.OBSIDIAN, "&b&lObsidian", 16, 30, 3, 0, 0, 15, 2),
                    new ShopItem(MultiversionMaterials.NETHERRACK, "&b&lNether Rack", 64, 30, 3, 0, 0, 16, 2),
                    new ShopItem(MultiversionMaterials.GLOWSTONE, "&b&lGlowstone", 64, 50, 5, 0, 0, 19, 2),
                    new ShopItem(MultiversionMaterials.SOUL_SAND, "&b&lSoul Sand", 64, 30, 3, 0, 0, 20, 2),
                    new ShopItem(MultiversionMaterials.NETHER_BRICK, "&b&lNether Bricks", 64, 50, 5, 0, 0, 21, 2),
                    new ShopItem(MultiversionMaterials.END_STONE, "&b&lEnd Stone", 64, 30, 3, 0, 0, 22, 2),
                    new ShopItem(MultiversionMaterials.PRISMARINE, "&b&lPrismarine", 64, 50, 5, 0, 0, 23, 2),
                    new ShopItem(MultiversionMaterials.BOOKSHELF, "&b&lBookshelf", 16, 30, 6, 0, 0, 24, 2),
                    new ShopItem(MultiversionMaterials.ENCHANTING_TABLE, "&b&lEnchanting Table", 1, 100, 20, 0, 0, 25, 2)
            ), 11),
            new ShopObject(MultiversionMaterials.COOKED_CHICKEN, "&b&lFood", "Food", Arrays.asList(
                    new ShopItem(MultiversionMaterials.MELON, "&b&lMelon", 10, 30, 6, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.APPLE, "&b&lApple", 10, 20, 4, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_APPLE, "&b&lGolden Apple", 10, 1000, 200, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.ENCHANTED_GOLDEN_APPLE, "&b&lEnchanted Golden Apple", 10, 30, 6, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.CARROT, "&b&lCarrot", 10, 20, 4, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.PUMPKIN_PIE, "&b&lPumpkin Pie", 10, 20, 4, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.BREAD, "&b&lBread", 10, 25, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.COOKED_CHICKEN, "&b&lCooked Chicken", 10, 25, 5, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.BAKED_POTATO, "&b&lBaked Potato", 10, 30, 6, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.MUSHROOM_STEW, "&b&lMushroom Stew", 10, 20, 4, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.COOKED_COD, "&b&lCooked Cod", 10, 20, 4, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.COOKED_SALMON, "&b&lCooked Salmon", 10, 25, 5, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.COOKED_RABBIT, "&b&lCooked Rabbit", 10, 20, 4, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.RABBIT_STEW, "&b&lRabbit Stew", 10, 40, 8, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.COOKED_PORKCHOP, "&b&lCooked Porkchop", 10, 30, 6, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.COOKED_BEEF, "&b&lCooked Beef", 10, 30, 6, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.COOKED_MUTTON, "&b&lCooked Mutton", 10, 25, 5, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.COOKIE, "&b&lCookie", 10, 30, 6, 0, 0, 32, 1),
                    new ShopItem(MultiversionMaterials.CAKE, "&b&lCake", 10, 50, 10, 0, 0, 33, 1)
            ), 12),
            new ShopObject(MultiversionMaterials.GOLD_INGOT, "&b&lOres", "Ores", Arrays.asList(
                    new ShopItem(MultiversionMaterials.COAL, "&b&lCoal", 32, 100, 10, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.REDSTONE, "&b&lRedstone", 64, 20, 4, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.LAPIS_LAZULI, "&b&lLapis Lazuli", 64, 20, 4, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.IRON_INGOT, "&b&lIron Ingot", 8, 300, 30, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.GOLD_INGOT, "&b&lGold Ingot", 8, 300, 30, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND, "&b&Diamond", 8, 1000, 100, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.EMERALD, "&b&lEmerald", 8, 200, 40, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.QUARTZ, "&b&lQuartz", 64, 100, 10, 0, 0, 23, 1)
            ), 13),
            new ShopObject(MultiversionMaterials.IRON_PICKAXE, "&b&lTools", "Tools", Arrays.asList(
                    new ShopItem(MultiversionMaterials.GOLDEN_PICKAXE, "&b&lGolden Pickaxe", 1, 50, 10, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.IRON_PICKAXE, "&b&lIron Pickaxe", 1, 100, 20, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_PICKAXE, "&b&lDiamond Pickaxe", 1, 1000, 200, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_AXE, "&b&lGolden Axe", 1, 50, 10, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.IRON_AXE, "&b&lIron Axe", 1, 100, 20, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_AXE, "&b&lDiamond Axe", 1, 1000, 200, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_SHOVEL, "&b&lGolden Shovel", 1, 50, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.IRON_SHOVEL, "&b&lIron Shovel", 1, 100, 10, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_SHOVEL, "&b&lDiamond Shovel", 1, 750, 150, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_HOE, "&b&lGolden Hoe", 1, 50, 10, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.IRON_HOE, "&b&lIron Hoe", 1, 100, 20, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_HOE, "&b&lDiamond Hoe", 1, 1000, 200, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.FLINT_AND_STEEL, "&b&lFlint and Steel", 1, 50, 10, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.SHEARS, "&b&lShears", 1, 100, 20, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.FISHING_ROD, "&b&lFishing Rod", 1, 150, 30, 0, 0, 31, 1)
            ), 14),
            new ShopObject(MultiversionMaterials.DIAMOND_CHESTPLATE, "&b&lArmor", "Armor", Arrays.asList(
                    new ShopItem(MultiversionMaterials.LEATHER_HELMET, "&b&lLether Helmet", 1, 40, 8, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_HELMET, "&b&lGolden Helmet", 1, 160, 32, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.IRON_HELMET, "&b&lIron Helmet", 1, 480, 96, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_HELMET, "&b&lDiamond Helmet", 1, 800, 160, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.LEATHER_CHESTPLATE, "&b&lLether Chestplate", 1, 50, 10, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_CHESTPLATE, "&b&lGolden Chestplate", 1, 200, 40, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.IRON_CHESTPLATE, "&b&lIron Chestplate", 1, 600, 120, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_CHESTPLATE, "&b&lDiamond Chestplate", 1, 1000, 200, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.LEATHER_LEGGINGS, "&b&lLether Leggings", 1, 50, 10, 0, 0, 28, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_LEGGINGS, "&b&lGolden Leggings", 1, 200, 40, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.IRON_LEGGINGS, "&b&lIron Leggings", 1, 600, 120, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_LEGGINGS, "&b&lDiamond Leggings", 1, 1000, 200, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.LEATHER_BOOTS, "&b&lLether Boots", 1, 30, 6, 0, 0, 37, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_BOOTS, "&b&lGolden Boots", 1, 120, 24, 0, 0, 38, 1),
                    new ShopItem(MultiversionMaterials.IRON_BOOTS, "&b&lIron Boots", 1, 360, 72, 0, 0, 39, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_BOOTS, "&b&lDiamond Boots", 1, 600, 120, 0, 0, 40, 1),
                    new ShopItem(MultiversionMaterials.WOODEN_SWORD, "&b&lWooden Sword", 1, 20, 4, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_SWORD, "&b&lGolden Sword", 1, 50, 10, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.IRON_SWORD, "&b&lIron Sword", 1, 100, 20, 0, 0, 33, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_SWORD, "&b&lDiamond Sword", 1, 1000, 200, 0, 0, 42, 1),
                    new ShopItem(MultiversionMaterials.BOW, "&b&lBow", 1, 100, 20, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.ARROW, "&b&lArrow", 10, 50, 10, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.SNOWBALL, "&b&lSnowball", 16, 30, 5, 0, 0, 34, 1),
                    new ShopItem(MultiversionMaterials.FIRE_CHARGE, "&b&lFire Charge", 1, 50, 5, 0, 0, 43, 1)
            ), 15),
            new ShopObject(MultiversionMaterials.WHEAT, "&b&lFarming", "Farming", Arrays.asList(
                    new ShopItem(MultiversionMaterials.WHEAT_SEEDS, "&b&lWheat Seeds", 16, 20, 2, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.PUMPKIN_SEEDS, "&b&lPumpkin Seeds", 16, 50, 5, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.MELON_SEEDS, "&b&lMelon Seeds", 16, 50, 5, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.BONE_MEAL, "&b&lBone Meal", 16, 50, 5, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.NETHER_WART, "&b&lNether Wart", 16, 50, 5, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.SUGAR_CANE, "&b&lSugar Cane", 16, 50, 5, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.WHEAT, "&b&lWheat", 16, 50, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.PUMPKIN, "&b&lPumpkin", 16, 50, 5, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.MELON_SLICE, "&b&lMelon Slice", 16, 50, 5, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.CACTUS, "&b&lCactus", 16, 20, 2, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.OAK_SAPLING, "&b&lOak Sapling", 16, 20, 2, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.SPRUCE_SAPLING, "&b&lSpruce Sapling", 16, 20, 2, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.BIRCH_SAPLING, "&b&lBirch Sapling", 16, 20, 2, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.JUNGLE_SAPLING, "&b&lJungle Sapling", 16, 20, 2, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.ACACIA_SAPLING, "&b&lAcacia Sapling", 16, 20, 2, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.DARK_OAK_SAPLING, "&b&lDark Oak Sapling", 16, 50, 5, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.BROWN_MUSHROOM, "&b&lBrown Mushroom", 8, 50, 5, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.RED_MUSHROOM, "&b&lRed Mushroom", 8, 50, 5, 0, 0, 32, 1)
            ), 21),
            new ShopObject(MultiversionMaterials.SPIDER_EYE, "&b&lMob Drops", "Mobdrops", Arrays.asList(
                    new ShopItem(MultiversionMaterials.ROTTEN_FLESH, "&b&lRotten Flesh", 16, 20, 2, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.BONE, "&b&lBone", 16, 30, 3, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GUNPOWDER, "&b&lGunpowder", 16, 30, 3, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.STRING, "&b&lString", 16, 30, 3, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.SPIDER_EYE, "&b&lSpider Eye", 16, 50, 5, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.ENDER_PEARL, "&b&lEnder Pearl", 4, 50, 5, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.SLIME_BALL, "&b&lSlime Ball", 16, 50, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.PRISMARINE_CRYSTALS, "&b&lPrismarine Crystals", 16, 50, 5, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.PRISMARINE_SHARD, "&b&lPrismarine Shard", 16, 30, 3, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.BLAZE_ROD, "&b&lBlaze Rod", 16, 50, 5, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.MAGMA_CREAM, "&b&lMagma Cream", 16, 40, 4, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.GHAST_TEAR, "&b&lGhast Tear", 16, 50, 5, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.LEATHER, "&b&lLeather", 16, 50, 5, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.RABBIT_HIDE, "&b&lRabbit Hide", 16, 20, 2, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.RABBIT_FOOT, "&b&lRabbit Foot", 16, 50, 5, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.INK_SAC, "&b&lInk Sac", 16, 50, 5, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.FEATHER, "&b&lFeather", 16, 20, 2, 0, 0, 32, 1)
            ), 22),
            new ShopObject(MultiversionMaterials.LIGHT_BLUE_DYE, "&b&lDyes", "Dyes", Arrays.asList(
                    new ShopItem(MultiversionMaterials.INK_SAC, "&b&lInk Sac", 16, 50, 5, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.RED_DYE, "&b&lRed Dye", 16, 100, 20, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GREEN_DYE, "&b&lGreen Dye", 16, 100, 20, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.COCOA_BEANS, "&b&lBrown Dye", 16, 100, 20, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.LAPIS_LAZULI, "&b&lBlue Dye", 16, 20, 4, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.PURPLE_DYE, "&b&lPurple Dye", 16, 100, 20, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.CYAN_DYE, "&b&lCyan Dye", 16, 100, 20, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.LIGHT_GRAY_DYE, "&b&lLight Gray Dye", 16, 100, 20, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.GRAY_DYE, "&b&lGray Dye", 16, 100, 20, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.PINK_DYE, "&b&lPink Dye", 16, 100, 20, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.LIME_DYE, "&b&lLime Dye", 16, 100, 20, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.YELLOW_DYE, "&b&lYellow Dye", 16, 100, 20, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.LIGHT_BLUE_DYE, "&b&lLight Blue Dye", 16, 100, 20, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.MAGENTA_DYE, "&b&lMagenta Dye", 16, 100, 20, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.ORANGE_DYE, "&b&lOrange Dye", 16, 100, 20, 0, 0, 31, 1)
            ), 23),
            new ShopObject(MultiversionMaterials.SADDLE, "&b&lMiscellanous", "Miscellanous", Arrays.asList(
                    new ShopItem(MultiversionMaterials.BUCKET, "&b&lBucket", 1, 100, 10, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.WATER_BUCKET, "&b&lWater Bucket", 1, 200, 20, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.LAVA_BUCKET, "&b&lBucket", 1, 200, 20, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.CAULDRON, "&b&lCauldron", 1, 300, 30, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.BREWING_STAND, "&b&lBrewing Stand", 1, 1000, 100, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.RED_BED, "&b&lBed", 1, 80, 15, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.NAME_TAG, "&b&lName Tag", 1, 100, 10, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.LEAD, "&b&lLead", 1, 20, 2, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.SADDLE, "&b&lSaddle", 1, 200, 50, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.WRITABLE_BOOK, "&b&lBook and Quill", 1, 500, 50, 0, 0, 23, 1)
            ), 31)
    );

    public static class ShopItem {
        public MultiversionMaterials material;
        public String displayName;
        public int amount;
        public int buyVault;
        public int sellVault;
        public int buyCrystals;
        public int sellCrystals;
        public int slot;
        public int page;

        public ShopItem(MultiversionMaterials material, String displayName, int amount, int buyVault, int sellVault, int buyCrystals, int sellCrystals, int slot, int page) {
            this.material = material;
            this.displayName = displayName;
            this.amount = amount;
            this.buyVault = buyVault;
            this.sellVault = sellVault;
            this.buyCrystals = buyCrystals;
            this.sellCrystals = sellCrystals;
            this.slot = slot;
            this.page = page;
        }
    }

    public static class ShopObject {
        public MultiversionMaterials display;
        public String displayName;
        public String name;
        public List<ShopItem> items;
        public int slot;

        public ShopObject(MultiversionMaterials display, String displayName, String name, List<ShopItem> items, int slot) {
            this.display = display;
            this.displayName = displayName;
            this.name = name;
            this.items = items;
            this.slot = slot;
        }
    }
}