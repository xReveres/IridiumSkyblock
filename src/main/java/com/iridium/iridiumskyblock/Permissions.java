package com.iridium.iridiumskyblock;

public class Permissions {


    public boolean breakBlocks;
    public boolean placeBlocks;
    public boolean interact;
    public boolean kickMembers;
    public boolean inviteMembers;
    public boolean regen;
    public boolean islandprivate;
    public boolean promote;
    public boolean demote;

    public Permissions(boolean breakBlocks, boolean placeBlocks, boolean interact, boolean kickMembers, boolean inviteMembers, boolean regen, boolean islandprivate, boolean promote, boolean demote) {
        this.breakBlocks = breakBlocks;
        this.placeBlocks = placeBlocks;
        this.interact = interact;
        this.kickMembers = kickMembers;
        this.inviteMembers = inviteMembers;
        this.regen = regen;
        this.islandprivate = islandprivate;
        this.promote = promote;
        this.demote = demote;
    }

    public Permissions() {
        breakBlocks = true;
        placeBlocks = true;
        interact = true;
        kickMembers = true;
        inviteMembers = true;
        regen = true;
        islandprivate = true;
        promote = true;
        demote = true;
    }
}