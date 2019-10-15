package com.iridium.iridiumskyblock;

public enum MissionRestart {
    Instantly(0), Daily(1), Weekly(2);

    private int i;

    MissionRestart(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public MissionRestart getNext(){
        if(getViaRank(i+1) != null){
            return getViaRank(i+1);
        }
        return getViaRank(0);
    }

    public MissionRestart getPrevious(){
        if(getViaRank(i-1) != null){
            return getViaRank(i-1);
        }
        return getHighestRank();
    }

    public MissionRestart getViaRank(int i) {
        for (MissionRestart mr : values()) {
            if (mr.getI() == i) return mr;
        }
        return null;
    }

    public MissionRestart getHighestRank() {
        int i = 0;
        for (MissionRestart mr : values()) {
            if (mr.getI() > i) {
                i = mr.getI();
            }
        }
        return getViaRank(i);
    }
}
