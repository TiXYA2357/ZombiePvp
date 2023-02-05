package org.sobadfish.zombiepvp.thread;

import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import org.sobadfish.zombiepvp.room.GameRoom;
import org.sobadfish.zombiepvp.room.floattext.FloatTextInfo;
import org.sobadfish.zombiepvp.room.world.WorldInfo;


public class WorldInfoMasterThread extends PluginTask<Plugin> {

    private final WorldInfo worldInfo;
    private final GameRoom room;

    public WorldInfoMasterThread(GameRoom room,WorldInfo worldInfo,Plugin bedWarMain) {
        super(bedWarMain);
        this.worldInfo = worldInfo;
        this.room = room;
    }

    @Override
    public void onRun(int i) {
        if (worldInfo != null){
            worldInfo.onUpdate();
            for (FloatTextInfo floatTextInfo : room.getFloatTextInfos()) {
                if (!floatTextInfo.stringUpdate(room)) {

                    break;
                }
            }
        }
    }
}
