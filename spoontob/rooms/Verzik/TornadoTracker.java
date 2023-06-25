package net.runelite.client.plugins.spoontob.rooms.Verzik;

import net.runelite.api.NPC;
import net.runelite.api.coords.WorldPoint;

public class TornadoTracker {
    private NPC npc;
    private WorldPoint prevLoc;

    TornadoTracker(NPC npc) {
        this.npc = npc;
        this.prevLoc = null;
    }

    public int checkMovement(WorldPoint playerWp, WorldPoint nadoWp) {
        return this.prevLoc != null && nadoWp != null && this.prevLoc.distanceTo(nadoWp) != 0 ? playerWp.distanceTo(nadoWp) - playerWp.distanceTo(this.prevLoc) : -1;
    }

    NPC getNpc() {
        return this.npc;
    }

    WorldPoint getPrevLoc() {
        return this.prevLoc;
    }

    void setPrevLoc(WorldPoint prevLoc) {
        this.prevLoc = prevLoc;
    }
}
