package events;

import java.util.List;

public class EventHistoryPrinter {
    
    public void printEvent(MotionEvent event) {
        System.out.println("Motion Event");
        System.out.println("Start time: " + event.getTimestamp());
        System.out.println("End time: " + event.getEndTimestamp());
        System.out.println("Duration: " + event.getDuration());
        System.out.println("Changed pixels: " + event.getChangedPixels());
    }
    public void printHistory(EventHistory history) {
        List<MotionEvent> events = history.getEvents();

        for(int i = 0; i < events.size(); i++) {
            System.out.println("Event #" + (i + 1));
            printEvent(events.get(i));
            System.out.println("__________________");
        }
    }
}
