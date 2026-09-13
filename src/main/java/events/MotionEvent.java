package events;

public class MotionEvent {
    private final long timestamp;
    private final boolean motionDetected;
    private final int changedPixels;
    private long endTimestamp = -1;

    public MotionEvent(long timestamp, boolean motionDetected, int changedPixels) {
        this.timestamp = timestamp;
        this.motionDetected = motionDetected;
        this.changedPixels = changedPixels;
    }

    public long getTimestamp() {
        return timestamp;
    } 

    public boolean isMotionDetected() {
        return motionDetected;
    }
    public void end(long timestamp) {
        this.endTimestamp = timestamp;
    }
    public int getChangedPixels() {
        return changedPixels;
    }
    public long getDuration() {
        if(endTimestamp == -1) {
            return -1;
        }
        return endTimestamp - timestamp;
    }
    @Override 
    public String toString() {
        return "MotionEvent{" +
                "timestamp=" + timestamp +
                ", motionDetected=" + motionDetected +
                ", changedPixels=" + changedPixels +
                ", endTimestamp=" + endTimestamp +
                '}';
    }
}
