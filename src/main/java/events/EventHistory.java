package events;

import java.util.List;
public class EventHistory {
    private final List<MotionEvent> events;

    public EventHistory(List<MotionEvent> events) {
        this.events = events;
    }

    public int getEventCount() {
        return events.size();
    }

    public MotionEvent getEvent(int index) {
        if(index < 0 || index >= events.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + events.size());
        }
        return events.get(index);
    }
    
    public List<MotionEvent> getEvents() {
        return List.copyOf(events);
    }

    public long getTotalDuration() {
        long total = 0; 

        for(MotionEvent event : events) {
            long duration = event.getDuration();

            if(duration == -1) {
                return -1;
            }
            total += duration;
        }
        return total;
    }
    public long getLongestDuration() {
        long longest = 0;

        for(MotionEvent event : events) {
            long duration = event.getDuration();

            if(duration == -1) {
                return -1;
            }

            if(duration > longest) {
                longest = duration;
            }
        }
        return longest;
    }
    public double getAverageDuration() {
        if(events.isEmpty()) {
            return 0.0;
        }

        long totalDuration = getTotalDuration();

        if(totalDuration == -1) {
            return -1;
        }
        return (double) totalDuration / events.size();
    }
}
