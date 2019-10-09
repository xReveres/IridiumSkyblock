package com.peaches.iridiumskyblock;

import com.peaches.iridiumskyblock.api.IslandCreateEvent;
import com.peaches.iridiumskyblock.api.IslandDeleteEvent;
import com.peaches.iridiumskyblock.gui.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class Island {

    public class Warp {
        Location location;
        String name;
        String password;

        public Warp(Location location, String name, String password) {
            this.location = location;
            this.name = name;
            this.password = password;
        }

        public Location getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }
    }

    private transient List<Chunk> chunks;

    private String owner;
    private HashSet<String> members;
    private Location pos1;
    private Location pos2;
    private Location center;
    private Location home;

    private transient UpgradeGUI upgradeGUI;
    private transient BoosterGUI boosterGUI;
    private transient MissionsGUI missionsGUI;
    private transient MembersGUI membersGUI;
    private transient WarpGUI warpGUI;
    private transient BorderColorGUI borderColorGUI;
    private transient PermissionsGUI permissionsGUI;

    private int id;

    private int spawnerBooster;
    private int farmingBooster;
    private int expBooster;
    private int flightBooster;

    private int boosterid;

    private int crystals;

    private int sizeLevel;
    private int memberLevel;
    private int warpLevel;
    private int oreLevel;

    private int a;

    private int value;

    public HashSet<Location> blocks;

    private List<Warp> warps;

    private int startvalue;

    public int treasureHunter;
    public int competitor;
    public int miner;
    public int farmer;
    public int hunter;
    public int fisherman;
    public int builder;

    private boolean visit;

    private NMSUtils.Color borderColor;

    private HashMap<Roles, Permissions> permissions;

    public Island(Player owner, Location pos1, Location pos2, Location center, Location home, int id) {
        User user = User.getUser(owner);
        user.role = Roles.Owner;
        blocks = new HashSet<>();
        this.owner = user.player;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.center = center;
        this.home = home;
        this.members = new HashSet<>(Collections.singletonList(user.player));
        this.id = id;
        upgradeGUI = new UpgradeGUI(this);
        boosterGUI = new BoosterGUI(this);
        missionsGUI = new MissionsGUI(this);
        membersGUI = new MembersGUI(this);
        warpGUI = new WarpGUI(this);
        borderColorGUI = new BorderColorGUI(this);
        spawnerBooster = 0;
        farmingBooster = 0;
        expBooster = 0;
        flightBooster = 0;
        crystals = 0;
        sizeLevel = 1;
        memberLevel = 1;
        warpLevel = 1;
        oreLevel = 1;
        value = 0;
        warps = new ArrayList<>();
        treasureHunter = 0;
        competitor = 0;
        miner = 0;
        farmer = 0;
        hunter = 0;
        fisherman = 0;
        builder = 0;
        startvalue = -1;
        borderColor = NMSUtils.Color.Blue;
        visit = true;
        permissions = new HashMap<Roles, Permissions>() {{
            for (Roles role : Roles.values()) {
                put(role, new Permissions());
            }
        }};
        init();
        Bukkit.getPluginManager().callEvent(new IslandCreateEvent(owner, this));
    }

    public Permissions getPermissions(Roles role) {
        if (!permissions.containsKey(role)) {
            permissions.put(role, new Permissions());
        }
        return permissions.get(role);
    }

    public void sendBorder() {
        for (Chunk c : chunks) {
            for (Entity e : c.getEntities()) {
                if (e instanceof Player) {
                    if (isInIsland(e.getLocation())) {
                        Player p = (Player) e;
                        sendBorder(p);
                    }
                }
            }
        }
    }

    public void hideBorder() {
        for (Chunk c : chunks) {
            for (Entity e : c.getEntities()) {
                if (e instanceof Player) {
                    if (isInIsland(e.getLocation())) {
                        Player p = (Player) e;
                        hideBorder(p);
                    }
                }
            }
        }
    }

    public void sendBorder(Player p) {
        NMSUtils.sendWorldBorder(p, borderColor, IridiumSkyblock.getUpgrades().size.get(sizeLevel).getSize(), getCenter());
    }

    public void hideBorder(Player p) {
        NMSUtils.sendWorldBorder(p, borderColor, Integer.MAX_VALUE, getCenter());
    }

    public void completeMission(String Mission, int Reward) {
        crystals += Reward;
        for (String member : members) {
            Player p = Bukkit.getPlayer(member);
            if (p != null) {
                NMSUtils.sendTitle(p, "&b&lMission Complete: &7" + Mission, 20, 40, 20);
                NMSUtils.sendSubTitle(p, "&bReward: &7" + Reward, 20, 40, 20);
            }
        }
    }

    public void calculateIslandValue() {
        int v = 0;
        for (Location loc : blocks) {
            Block b = loc.getBlock();
            if (IridiumSkyblock.getConfiguration().blockvalue.containsKey(b.getType())) {
                v += IridiumSkyblock.getConfiguration().blockvalue.get(b.getType());
            }
            if (loc.getBlock().getState() instanceof CreatureSpawner) {
                CreatureSpawner spawner = (CreatureSpawner) b.getState();
                if (IridiumSkyblock.getConfiguration().spawnervalue.containsKey(spawner.getSpawnedType().name())) {
                    v += IridiumSkyblock.getConfiguration().spawnervalue.get(spawner.getSpawnedType().name());
                }
            }
        }
        this.value = v;
        if (startvalue == -1) startvalue = v;

        if (competitor != Integer.MIN_VALUE) {
            this.competitor = v - startvalue;
            if (competitor >= IridiumSkyblock.getMissions().competitor.getAmount()) {
                competitor = Integer.MIN_VALUE;
                completeMission("Competitor", IridiumSkyblock.getMissions().competitor.getReward());
            }
        }
    }

    public void initBlocks() {
        this.a = Bukkit.getScheduler().scheduleSyncRepeatingTask(IridiumSkyblock.getInstance(), new Runnable() {
            double Y = 0;

            @Override
            public void run() {
                try {
                    int lpt = IridiumSkyblock.getConfiguration().layersPerTick;
                    for (int i = 0; i < lpt; i++) {
                        if (Y <= IridiumSkyblock.getIslandManager().getWorld().getMaxHeight()) {
                            for (double X = getPos1().getX(); X <= getPos2().getX(); X++) {
                                for (double Z = getPos1().getZ(); Z <= getPos2().getZ(); Z++) {
                                    Location loc = new Location(getPos1().getWorld(), X, Y, Z);
                                    if (Utils.isBlockValuable(loc.getBlock())) {
                                        blocks.add(loc);
                                    }
                                }
                            }
                        } else {
                            Bukkit.getScheduler().cancelTask(a);
                            a = -1;
                            for (Location location : blocks) {
                                if (!Utils.isBlockValuable(location.getBlock())) {
                                    blocks.remove(location);
                                }
                            }
                            IridiumSkyblock.getInstance().updatingBlocks = false;
                        }
                        Y++;
                    }
                } catch (Exception e) {
                    IridiumSkyblock.getInstance().sendErrorMessage(e);
                }
            }
        }, 0, 1);
    }

    public void addWarp(Player player, Location location, String name, String password) {
        if (warps.size() < IridiumSkyblock.getUpgrades().warp.get(warpLevel).getSize()) {
            warps.add(new Warp(location, name, password));
            player.sendMessage(Utils.color(IridiumSkyblock.getMessages().warpAdded.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        } else {
            player.sendMessage(Utils.color(IridiumSkyblock.getMessages().maxWarpsReached.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    public void addUser(User user) {
        if (members.size() < IridiumSkyblock.getUpgrades().member.get(memberLevel).getSize()) {
            user.islandID = id;
            user.role = Roles.Visitor;
            user.invites.clear();
            members.add(user.player);
            teleportHome(Bukkit.getPlayer(user.name));
            user.invites.clear();
        } else {
            if (Bukkit.getPlayer(user.name) != null) {
                Bukkit.getPlayer(user.name).sendMessage(Utils.color(IridiumSkyblock.getMessages().maxMemberCount.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));

            }
        }
    }

    public void removeUser(User user) {
        user.islandID = 0;
        Player player = Bukkit.getPlayer(user.name);
        player.setFlying(false);
        player.setAllowFlight(false);
        members.remove(user.player);
        for (String member : members) {
            User u = User.getUser(member);
            Player p = Bukkit.getPlayer(u.name);
            if (p != null) {
                p.sendMessage(Utils.color(IridiumSkyblock.getMessages().kickedMember.replace("%member%", user.name).replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        }
    }

    public boolean isInIsland(Location location) {
        return (location.getX() > getPos1().getX() && location.getX() <= getPos2().getX()) && (location.getZ() > getPos1().getZ() && location.getZ() <= getPos2().getZ());
    }

    public void init() {
        if (blocks == null) blocks = new HashSet<>();
        initChunks();
        boosterid = Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), () -> {
            if (spawnerBooster > 0) spawnerBooster--;
            if (farmingBooster > 0) farmingBooster--;
            if (expBooster > 0) expBooster--;
            if (flightBooster > 0) flightBooster--;
            if (flightBooster == 0) {
                for (String player : members) {
                    Player p = Bukkit.getPlayer(player);
                    if (p != null) {
                        if (!p.hasPermission("EpicSkyblock.Fly") && p.getGameMode().equals(GameMode.SURVIVAL)) {
                            p.setAllowFlight(false);
                            p.setFlying(false);
                        }
                    }
                }
            }
        }, 0, 20);
        if (permissions == null) {
            permissions = new HashMap<Roles, Permissions>() {{
                for (Roles role : Roles.values()) {
                    put(role, new Permissions());
                }
            }};
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(IridiumSkyblock.getInstance(), this::calculateIslandValue, 0, 20);
    }

    public void initChunks() {
        chunks = new ArrayList<>();
        for (int X = getPos1().getChunk().getX(); X <= getPos2().getChunk().getX(); X++) {
            for (int Z = getPos1().getChunk().getZ(); Z <= getPos2().getChunk().getZ(); Z++) {
                chunks.add(IridiumSkyblock.getIslandManager().getWorld().getChunkAt(X, Z));
            }
        }
    }

    public void generateIsland() {
        deleteBlocks();
        killEntities();
        IridiumSkyblock.getIslandManager().pasteSchematic(getCenter().clone());
        clearInventories();
    }

    public void clearInventories() {
        for (Chunk c : chunks) {
            for (Entity e : c.getEntities()) {
                if (e instanceof Player) {
                    if (isInIsland(e.getLocation())) {
                        ((Player) e).getInventory().clear();
                    }
                }
            }
        }
    }

    public void teleportHome(Player p) {
        p.setFallDistance(0);
        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().teleportingHome.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        if (Utils.isSafe(getHome(), this)) {
            p.teleport(getHome());
            sendBorder(p);
        } else {

            Location loc = Utils.getNewHome(this, this.home);
            if (loc != null) {
                this.home = loc;
                p.teleport(this.home);
                sendBorder(p);
            } else {
                generateIsland();
                teleportHome(p);
                sendBorder(p);
            }
        }
    }

    public void delete() {
        Bukkit.getPluginManager().callEvent(new IslandDeleteEvent(this));

        Bukkit.getScheduler().cancelTask(getMembersGUI().scheduler);
        Bukkit.getScheduler().cancelTask(getBoosterGUI().scheduler);
        Bukkit.getScheduler().cancelTask(getMissionsGUI().scheduler);
        Bukkit.getScheduler().cancelTask(getUpgradeGUI().scheduler);
        Bukkit.getScheduler().cancelTask(getWarpGUI().scheduler);
        permissions.clear();
        if (a != -1) Bukkit.getScheduler().cancelTask(a);
        deleteBlocks();
        killEntities();
        for (String player : members) {
            User.getUser(player).islandID = 0;
            if (Bukkit.getPlayer(player) != null) Bukkit.getPlayer(player).closeInventory();
        }
        hideBorder();
        this.owner = null;
        this.pos1 = null;
        this.pos2 = null;
        this.members = null;
        this.chunks = null;
        this.center = null;
        this.home = null;
        IridiumSkyblock.getIslandManager().islands.remove(this.id);
        this.id = 0;
        IridiumSkyblock.getInstance().saveConfigs();
        Bukkit.getScheduler().cancelTask(boosterid);
        boosterid = -1;
    }

    public void deleteBlocks() {
        for (double X = getPos1().getX(); X <= getPos2().getX(); X++) {
            for (double Y = 0; Y <= IridiumSkyblock.getIslandManager().getWorld().getMaxHeight(); Y++) {
                for (double Z = getPos1().getZ(); Z <= getPos2().getZ(); Z++) {
                    new Location(getPos1().getWorld(), X, Y, Z).getBlock().setType(Material.AIR, false);
                }
            }
        }
    }

    public void killEntities() {
        for (Chunk c : chunks) {
            for (Entity e : c.getEntities()) {
                if (isInIsland(e.getLocation())) {
                    if (e.getType() != EntityType.PLAYER) {
                        e.remove();
                    }
                }
            }
        }
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public Location getCenter() {
        return center;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public UpgradeGUI getUpgradeGUI() {
        if (upgradeGUI == null) upgradeGUI = new UpgradeGUI(this);
        return upgradeGUI;
    }

    public BoosterGUI getBoosterGUI() {
        if (boosterGUI == null) boosterGUI = new BoosterGUI(this);
        return boosterGUI;
    }

    public MissionsGUI getMissionsGUI() {
        if (missionsGUI == null) missionsGUI = new MissionsGUI(this);
        return missionsGUI;
    }

    public MembersGUI getMembersGUI() {
        if (membersGUI == null) membersGUI = new MembersGUI(this);
        return membersGUI;
    }

    public WarpGUI getWarpGUI() {
        if (warpGUI == null) warpGUI = new WarpGUI(this);
        return warpGUI;
    }

    public BorderColorGUI getBorderColorGUI() {
        if (borderColorGUI == null) {
            borderColorGUI = new BorderColorGUI(this);
        }
        return borderColorGUI;
    }

    public PermissionsGUI getPermissionsGUI() {
        if (permissionsGUI == null) permissionsGUI = new PermissionsGUI(this);
        return permissionsGUI;
    }

    public int getSpawnerBooster() {
        return spawnerBooster;
    }

    public void setSpawnerBooster(int spawnerBooster) {
        this.spawnerBooster = spawnerBooster;
    }

    public int getFarmingBooster() {
        return farmingBooster;
    }

    public void setFarmingBooster(int farmingBooster) {
        this.farmingBooster = farmingBooster;
    }

    public int getExpBooster() {
        return expBooster;
    }

    public void setExpBooster(int expBooster) {
        this.expBooster = expBooster;
    }

    public int getFlightBooster() {
        return flightBooster;
    }

    public void setFlightBooster(int flightBooster) {
        this.flightBooster = flightBooster;
    }

    public int getCrystals() {
        return crystals;
    }

    public void setCrystals(int crystals) {
        this.crystals = crystals;
    }

    public HashSet<String> getMembers() {
        return members;
    }

    public int getSizeLevel() {
        return sizeLevel;
    }

    public void setSizeLevel(int sizeLevel) {
        this.sizeLevel = sizeLevel;

        pos1 = getCenter().clone().subtract(IridiumSkyblock.getUpgrades().size.get(sizeLevel).getSize() / 2.00, 0, IridiumSkyblock.getUpgrades().size.get(sizeLevel).getSize() / 2.00);
        pos2 = getCenter().clone().add(IridiumSkyblock.getUpgrades().size.get(sizeLevel).getSize() / 2.00, 0, IridiumSkyblock.getUpgrades().size.get(sizeLevel).getSize() / 2.00);
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public int getWarpLevel() {
        return warpLevel;
    }

    public void setWarpLevel(int warpLevel) {
        this.warpLevel = warpLevel;
    }

    public int getOreLevel() {
        return oreLevel;
    }

    public void setOreLevel(int oreLevel) {
        this.oreLevel = oreLevel;
    }

    public void removeWarp(Warp warp) {
        warps.remove(warp);
    }

    public List<Warp> getWarps() {
        return warps;
    }

    public int getValue() {
        return value;
    }

    public NMSUtils.Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(NMSUtils.Color borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }
}
