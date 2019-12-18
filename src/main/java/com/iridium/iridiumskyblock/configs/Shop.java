package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.MultiversionMaterials;

import java.util.Arrays;
import java.util.List;

public class Shop {

    public List<String> lore = Arrays.asList("&7Buy Price: &c${buyvaultprice}", "&7Sell Price: &a${sellvaultprice}");

    public List<ShopObject> shop = Arrays.asList(
            new ShopObject(MultiversionMaterials.GRASS_BLOCK, "&9&lBlocks", "Blocks", Arrays.asList(
                    new ShopItem(MultiversionMaterials.GRASS_BLOCK, "&9&lGrass Block", 64, 50, 5, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.DIRT, "&9&lDirt Block", 64, 30, 3, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GRAVEL, "&9&lGravel", 64, 50, 5, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.COBBLESTONE, "&9&lCobblestone", 64, 30, 3, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.MOSSY_COBBLESTONE, "&9&lMossy Cobblestone", 64, 50, 5, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.STONE, "&9&lStone", 64, 50, 5, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.GRANITE, "&9&lGranite", 64, 30, 8, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.DIORITE, "&9&lDiorite", 64, 30, 3, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.ANDESITE, "&9&lAndesite", 64, 30, 3, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.OAK_LOG, "&9&lOak Log", 32, 100, 10, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.SPRUCE_LOG, "&9&lSpruce Log", 32, 100, 10, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.BIRCH_LOG, "&9&lBirch Log", 32, 100, 10, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.JUNGLE_LOG, "&9&lJungle Log", 32, 100, 10, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.ACACIA_LOG, "&9&lAcacia Log", 32, 100, 10, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.DARK_OAK_LOG, "&9&lDark Oak Log", 32, 100, 10, 0, 0, 28, 1),
                    new ShopItem(MultiversionMaterials.SNOW_BLOCK, "&9&lSnow Block", 16, 20, 2, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.ICE, "&9&lIce", 16, 30, 3, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.PACKED_ICE, "&9&lPacked Ice", 16, 30, 3, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.SPONGE, "&9&lSponge", 8, 100, 10, 0, 0, 32, 1),
                    new ShopItem(MultiversionMaterials.SAND, "&9&lSand", 64, 30, 3, 0, 0, 33, 1),
                    new ShopItem(MultiversionMaterials.SANDSTONE, "&9&lSandstone", 32, 30, 3, 0, 0, 34, 1),
                    new ShopItem(MultiversionMaterials.RED_SANDSTONE, "&9&lRed Sandstone", 32, 30, 3, 0, 0, 10, 2),
                    new ShopItem(MultiversionMaterials.GLASS, "&9&lGlass", 16, 20, 4, 0, 0, 11, 2),
                    new ShopItem(MultiversionMaterials.CLAY, "&9&lClay", 32, 40, 4, 0, 0, 12, 2),
                    new ShopItem(MultiversionMaterials.TERRACOTTA, "&9&lTerracotta", 32, 50, 5, 0, 0, 13, 2),
                    new ShopItem(MultiversionMaterials.BRICK, "&9&lBrick", 64, 70, 7, 0, 0, 14, 2),
                    new ShopItem(MultiversionMaterials.OBSIDIAN, "&9&lObsidian", 16, 30, 3, 0, 0, 15, 2),
                    new ShopItem(MultiversionMaterials.NETHERRACK, "&9&lNether Rack", 64, 30, 3, 0, 0, 16, 2),
                    new ShopItem(MultiversionMaterials.GLOWSTONE, "&9&lGlowstone", 64, 50, 5, 0, 0, 19, 2),
                    new ShopItem(MultiversionMaterials.SOUL_SAND, "&9&lSoul Sand", 64, 30, 3, 0, 0, 20, 2),
                    new ShopItem(MultiversionMaterials.NETHER_BRICK, "&9&lNether Bricks", 64, 50, 5, 0, 0, 21, 2),
                    new ShopItem(MultiversionMaterials.END_STONE, "&9&lEnd Stone", 64, 30, 3, 0, 0, 22, 2),
                    new ShopItem(MultiversionMaterials.PRISMARINE, "&9&lPrismarine", 64, 50, 5, 0, 0, 23, 2),
                    new ShopItem(MultiversionMaterials.BOOKSHELF, "&9&lBookshelf", 16, 30, 6, 0, 0, 24, 2),
                    new ShopItem(MultiversionMaterials.ENCHANTING_TABLE, "&9&lEnchanting Table", 1, 100, 20, 0, 0, 25, 2)
            ), 11),
            new ShopObject(MultiversionMaterials.COOKED_CHICKEN, "&9&lFood", "Food", Arrays.asList(
                    new ShopItem(MultiversionMaterials.MELON, "&9&lMelon", 10, 30, 6, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.APPLE, "&9&lApple", 10, 20, 4, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_APPLE, "&9&lGolden Apple", 10, 1000, 200, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.ENCHANTED_GOLDEN_APPLE, "&9&lEnchanted Golden Apple", 10, 30, 6, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.CARROT, "&9&lCarrot", 10, 20, 4, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.PUMPKIN_PIE, "&9&lPumpkin Pie", 10, 20, 4, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.BREAD, "&9&lBread", 10, 25, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.COOKED_CHICKEN, "&9&lCooked Chicken", 10, 25, 5, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.BAKED_POTATO, "&9&lBaked Potato", 10, 30, 6, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.MUSHROOM_STEW, "&9&lMushroom Stew", 10, 20, 4, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.COOKED_COD, "&9&lCooked Cod", 10, 20, 4, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.COOKED_SALMON, "&9&lCooked Salmon", 10, 25, 5, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.COOKED_RABBIT, "&9&lCooked Rabbit", 10, 20, 4, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.RABBIT_STEW, "&9&lRabbit Stew", 10, 40, 8, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.COOKED_PORKCHOP, "&9&lCooked Porkchop", 10, 30, 6, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.COOKED_BEEF, "&9&lCooked Beef", 10, 30, 6, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.COOKED_MUTTON, "&9&lCooked Mutton", 10, 25, 5, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.COOKIE, "&9&lCookie", 10, 30, 6, 0, 0, 32, 1),
                    new ShopItem(MultiversionMaterials.CAKE, "&9&lCake", 10, 50, 10, 0, 0, 33, 1)
            ), 12),
            new ShopObject(MultiversionMaterials.GOLD_INGOT, "&9&lOres", "Ores", Arrays.asList(
                    new ShopItem(MultiversionMaterials.COAL, "&9&lCoal", 32, 100, 10, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.REDSTONE, "&9&lRedstone", 64, 20, 4, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.LAPIS_LAZULI, "&9&lLapis Lazuli", 64, 20, 4, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.IRON_INGOT, "&9&lIron Ingot", 8, 300, 30, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.GOLD_INGOT, "&9&lGold Ingot", 8, 300, 30, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND, "&9&lDiamond", 8, 1000, 100, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.EMERALD, "&9&lEmerald", 8, 200, 40, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.QUARTZ, "&9&lQuartz", 64, 100, 10, 0, 0, 23, 1)
            ), 13),
            new ShopObject(MultiversionMaterials.IRON_PICKAXE, "&9&lTools", "Tools", Arrays.asList(
                    new ShopItem(MultiversionMaterials.GOLDEN_PICKAXE, "&9&lGolden Pickaxe", 1, 50, 10, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.IRON_PICKAXE, "&9&lIron Pickaxe", 1, 100, 20, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_PICKAXE, "&9&lDiamond Pickaxe", 1, 1000, 200, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_AXE, "&9&lGolden Axe", 1, 50, 10, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.IRON_AXE, "&9&lIron Axe", 1, 100, 20, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_AXE, "&9&lDiamond Axe", 1, 1000, 200, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_SHOVEL, "&9&lGolden Shovel", 1, 50, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.IRON_SHOVEL, "&9&lIron Shovel", 1, 100, 10, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_SHOVEL, "&9&lDiamond Shovel", 1, 750, 150, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_HOE, "&9&lGolden Hoe", 1, 50, 10, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.IRON_HOE, "&9&lIron Hoe", 1, 100, 20, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_HOE, "&9&lDiamond Hoe", 1, 1000, 200, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.FLINT_AND_STEEL, "&9&lFlint and Steel", 1, 50, 10, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.SHEARS, "&9&lShears", 1, 100, 20, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.FISHING_ROD, "&9&lFishing Rod", 1, 150, 30, 0, 0, 31, 1)
            ), 14),
            new ShopObject(MultiversionMaterials.DIAMOND_CHESTPLATE, "&9&lArmor", "Armor", Arrays.asList(
                    new ShopItem(MultiversionMaterials.LEATHER_HELMET, "&9&lLether Helmet", 1, 40, 8, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_HELMET, "&9&lGolden Helmet", 1, 160, 32, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.IRON_HELMET, "&9&lIron Helmet", 1, 480, 96, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_HELMET, "&9&lDiamond Helmet", 1, 800, 160, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.LEATHER_CHESTPLATE, "&9&lLether Chestplate", 1, 50, 10, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_CHESTPLATE, "&9&lGolden Chestplate", 1, 200, 40, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.IRON_CHESTPLATE, "&9&lIron Chestplate", 1, 600, 120, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_CHESTPLATE, "&9&lDiamond Chestplate", 1, 1000, 200, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.LEATHER_LEGGINGS, "&9&lLether Leggings", 1, 50, 10, 0, 0, 28, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_LEGGINGS, "&9&lGolden Leggings", 1, 200, 40, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.IRON_LEGGINGS, "&9&lIron Leggings", 1, 600, 120, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_LEGGINGS, "&9&lDiamond Leggings", 1, 1000, 200, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.LEATHER_BOOTS, "&9&lLether Boots", 1, 30, 6, 0, 0, 37, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_BOOTS, "&9&lGolden Boots", 1, 120, 24, 0, 0, 38, 1),
                    new ShopItem(MultiversionMaterials.IRON_BOOTS, "&9&lIron Boots", 1, 360, 72, 0, 0, 39, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_BOOTS, "&9&lDiamond Boots", 1, 600, 120, 0, 0, 40, 1),
                    new ShopItem(MultiversionMaterials.WOODEN_SWORD, "&9&lWooden Sword", 1, 20, 4, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.GOLDEN_SWORD, "&9&lGolden Sword", 1, 50, 10, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.IRON_SWORD, "&9&lIron Sword", 1, 100, 20, 0, 0, 33, 1),
                    new ShopItem(MultiversionMaterials.DIAMOND_SWORD, "&9&lDiamond Sword", 1, 1000, 200, 0, 0, 42, 1),
                    new ShopItem(MultiversionMaterials.BOW, "&9&lBow", 1, 100, 20, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.ARROW, "&9&lArrow", 10, 50, 10, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.SNOWBALL, "&9&lSnowball", 16, 30, 5, 0, 0, 34, 1),
                    new ShopItem(MultiversionMaterials.FIRE_CHARGE, "&9&lFire Charge", 1, 50, 5, 0, 0, 43, 1)
            ), 15),
            new ShopObject(MultiversionMaterials.WHEAT, "&9&lFarming", "Farming", Arrays.asList(
                    new ShopItem(MultiversionMaterials.WHEAT_SEEDS, "&9&lWheat Seeds", 16, 20, 2, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.PUMPKIN_SEEDS, "&9&lPumpkin Seeds", 16, 50, 5, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.MELON_SEEDS, "&9&lMelon Seeds", 16, 50, 5, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.BONE_MEAL, "&9&lBone Meal", 16, 50, 5, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.NETHER_WART, "&9&lNether Wart", 16, 50, 5, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.SUGAR_CANE, "&9&lSugar Cane", 16, 50, 5, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.WHEAT, "&9&lWheat", 16, 50, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.PUMPKIN, "&9&lPumpkin", 16, 50, 5, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.MELON_SLICE, "&9&lMelon Slice", 16, 50, 5, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.CACTUS, "&9&lCactus", 16, 20, 2, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.OAK_SAPLING, "&9&lOak Sapling", 16, 20, 2, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.SPRUCE_SAPLING, "&9&lSpruce Sapling", 16, 20, 2, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.BIRCH_SAPLING, "&9&lBirch Sapling", 16, 20, 2, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.JUNGLE_SAPLING, "&9&lJungle Sapling", 16, 20, 2, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.ACACIA_SAPLING, "&9&lAcacia Sapling", 16, 20, 2, 0, 0, 29, 1),
                    new ShopItem(MultiversionMaterials.DARK_OAK_SAPLING, "&9&lDark Oak Sapling", 16, 50, 5, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.BROWN_MUSHROOM, "&9&lBrown Mushroom", 8, 50, 5, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.RED_MUSHROOM, "&9&lRed Mushroom", 8, 50, 5, 0, 0, 32, 1)
            ), 21),
            new ShopObject(MultiversionMaterials.SPIDER_EYE, "&9&lMob Drops", "Mobdrops", Arrays.asList(
                    new ShopItem(MultiversionMaterials.ROTTEN_FLESH, "&9&lRotten Flesh", 16, 20, 2, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.BONE, "&9&lBone", 16, 30, 3, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GUNPOWDER, "&9&lGunpowder", 16, 30, 3, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.STRING, "&9&lString", 16, 30, 3, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.SPIDER_EYE, "&9&lSpider Eye", 16, 50, 5, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.ENDER_PEARL, "&9&lEnder Pearl", 4, 50, 5, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.SLIME_BALL, "&9&lSlime Ball", 16, 50, 5, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.PRISMARINE_CRYSTALS, "&9&lPrismarine Crystals", 16, 50, 5, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.PRISMARINE_SHARD, "&9&lPrismarine Shard", 16, 30, 3, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.BLAZE_ROD, "&9&lBlaze Rod", 16, 50, 5, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.MAGMA_CREAM, "&9&lMagma Cream", 16, 40, 4, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.GHAST_TEAR, "&9&lGhast Tear", 16, 50, 5, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.LEATHER, "&9&lLeather", 16, 50, 5, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.RABBIT_HIDE, "&9&lRabbit Hide", 16, 20, 2, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.RABBIT_FOOT, "&9&lRabbit Foot", 16, 50, 5, 0, 0, 30, 1),
                    new ShopItem(MultiversionMaterials.INK_SAC, "&9&lInk Sac", 16, 50, 5, 0, 0, 31, 1),
                    new ShopItem(MultiversionMaterials.FEATHER, "&9&lFeather", 16, 20, 2, 0, 0, 32, 1)
            ), 22),
            new ShopObject(MultiversionMaterials.LIGHT_BLUE_DYE, "&9&lDyes", "Dyes", Arrays.asList(
                    new ShopItem(MultiversionMaterials.INK_SAC, "&9&lInk Sac", 16, 50, 5, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.RED_DYE, "&9&lRed Dye", 16, 100, 20, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.GREEN_DYE, "&9&lGreen Dye", 16, 100, 20, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.COCOA_BEANS, "&9&lBrown Dye", 16, 100, 20, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.LAPIS_LAZULI, "&9&lBlue Dye", 16, 20, 4, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.PURPLE_DYE, "&9&lPurple Dye", 16, 100, 20, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.CYAN_DYE, "&9&lCyan Dye", 16, 100, 20, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.LIGHT_GRAY_DYE, "&9&lLight Gray Dye", 16, 100, 20, 0, 0, 19, 1),
                    new ShopItem(MultiversionMaterials.GRAY_DYE, "&9&lGray Dye", 16, 100, 20, 0, 0, 20, 1),
                    new ShopItem(MultiversionMaterials.PINK_DYE, "&9&lPink Dye", 16, 100, 20, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.LIME_DYE, "&9&lLime Dye", 16, 100, 20, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.YELLOW_DYE, "&9&lYellow Dye", 16, 100, 20, 0, 0, 23, 1),
                    new ShopItem(MultiversionMaterials.LIGHT_BLUE_DYE, "&9&lLight Blue Dye", 16, 100, 20, 0, 0, 24, 1),
                    new ShopItem(MultiversionMaterials.MAGENTA_DYE, "&9&lMagenta Dye", 16, 100, 20, 0, 0, 25, 1),
                    new ShopItem(MultiversionMaterials.ORANGE_DYE, "&9&lOrange Dye", 16, 100, 20, 0, 0, 31, 1)
            ), 23),
            new ShopObject(MultiversionMaterials.SADDLE, "&9&lMiscellanous", "Miscellanous", Arrays.asList(
                    new ShopItem(MultiversionMaterials.BUCKET, "&9&lBucket", 1, 100, 10, 0, 0, 10, 1),
                    new ShopItem(MultiversionMaterials.WATER_BUCKET, "&9&lWater Bucket", 1, 200, 20, 0, 0, 11, 1),
                    new ShopItem(MultiversionMaterials.LAVA_BUCKET, "&9&lBucket", 1, 200, 20, 0, 0, 12, 1),
                    new ShopItem(MultiversionMaterials.CAULDRON, "&9&lCauldron", 1, 300, 30, 0, 0, 13, 1),
                    new ShopItem(MultiversionMaterials.BREWING_STAND, "&9&lBrewing Stand", 1, 1000, 100, 0, 0, 14, 1),
                    new ShopItem(MultiversionMaterials.RED_BED, "&9&lBed", 1, 80, 15, 0, 0, 15, 1),
                    new ShopItem(MultiversionMaterials.NAME_TAG, "&9&lName Tag", 1, 100, 10, 0, 0, 16, 1),
                    new ShopItem(MultiversionMaterials.LEAD, "&9&lLead", 1, 20, 2, 0, 0, 21, 1),
                    new ShopItem(MultiversionMaterials.SADDLE, "&9&lSaddle", 1, 200, 50, 0, 0, 22, 1),
                    new ShopItem(MultiversionMaterials.WRITABLE_BOOK, "&9&lBook and Quill", 1, 500, 50, 0, 0, 23, 1)
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