package org.sobadfish.zombiepvp.room.event.defaults;

import cn.nukkit.potion.Effect;
import org.sobadfish.zombiepvp.manager.TotalManager;
import org.sobadfish.zombiepvp.room.GameRoom;
import org.sobadfish.zombiepvp.room.config.GameRoomEventConfig;
import org.sobadfish.zombiepvp.room.event.IGameRoomEvent;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Sobadfish
 */
public class EffectEvent extends IGameRoomEvent {

    public EffectEvent(GameRoomEventConfig.GameRoomEventItem item) {
        super(item);
    }


    @Override
    public void onStart(GameRoom room) {
        List<Effect> effects = new ArrayList<>();
        if(item.value instanceof List){
            List<?> oList = (List<?>) item.value;
            for(Object os:oList){
                Effect e = stringToEffect(os.toString());
                if(e != null){
                    effects.add(e);
                }
            }
        }else{
            String s = item.value.toString();
            Effect e = stringToEffect(s);
            if(e != null){
                effects.add(e);
            }
        }
        effects.forEach(room::addEffect);
        room.sendMessage(TotalManager.getLanguage().getLanguage("room-event-effect-getting-info","&r获得 [1]",
                display()));
    }

    public Effect stringToEffect(String s){
        String[] r = s.split(":");
        int id = Integer.parseInt(r[0]);
        int level = 1;
        int time = 20;
        if(r.length > 2){
            level = Integer.parseInt(r[1]);
            time = Integer.parseInt(r[2]) * 20;
        }else if(r.length > 1){
            time = Integer.parseInt(r[1]) * 20;
        }
        Effect effect = Effect.getEffect(id);
        if(effect == null){
            return null;
        }
        effect.setAmplifier(level).setDuration(time);
        return effect;
    }
}
