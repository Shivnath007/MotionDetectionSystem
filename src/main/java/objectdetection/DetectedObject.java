package objectdetection;

public class DetectedObject {
    private final String objectName;
    private final double confidence;

    public DetectedObject(String objectName, double confidence) {
        this.objectName = objectName;
        this.confidence = confidence;
    }

    public String getObjectName() {
        return objectName;
    }

    public double getConfidence() {
        return confidence;
    }
}
