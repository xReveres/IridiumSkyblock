package com.iridium.iridiumskyblock;

public enum Roles {
    Owner(6), CoOwner(5), Admin(4), Moderator(3), Member(2), Visitor(1);

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