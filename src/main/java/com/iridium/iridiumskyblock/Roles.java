package com.iridium.iridiumskyblock;

public enum Roles {
    Owner(5), CoOwner(4), Admin(3), Moderator(2), Member(1), Visitor(-1);

    int rank;

    Roles(int rank) {
        this.rank = rank;
    }

    public static Roles getViaRank(int i) {
        for (Roles roles : values()) {
            if (roles.rank == i) {
                return roles;
            }
        }
        return null;
    }

    public int getRank() {
        return rank;
    }
}