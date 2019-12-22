package com.iridium.iridiumskyblock;

public enum Role {
    Owner(4), CoOwner(3), Moderator(2), Member(1), Visitor(-1);

    int rank;

    Role(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        if (IridiumSkyblock.getMessages().roles.containsKey(this)) {
            return IridiumSkyblock.getMessages().roles.get(this);
        }
        return this.name();
    }

    public static Role getViaRank(int i) {
        for (Role role : values()) {
            if (role.rank == i) {
                return role;
            }
        }
        return null;
    }

    public int getRank() {
        return rank;
    }
}