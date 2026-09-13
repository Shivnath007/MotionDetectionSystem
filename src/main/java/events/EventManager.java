package events;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<MotionEvent> events = new ArrayList<>();
    private MotionEvent activeEvent;

    public void addEvent(MotionEvent event) {
        events.add(event);
        activeEvent = event;
    }

    public int getEventCount() {
        return events.size();
    }

    public List<MotionEvent> getEvents() {
        return List.copyOf(events);
    }

    public MotionEvent endActiveEvent(long timestamp) {
        if(activeEvent != null) {
            activeEvent.end(timestamp);
            MotionEvent event = activeEvent;
            activeEvent = null;
            return event;
        }
        return null;
    }
    public long getTimestamp() {
        if (events.isEmpty()) {
            return -1;
        }
        return events.get(events.size() - 1).getTimestamp();
    }
}