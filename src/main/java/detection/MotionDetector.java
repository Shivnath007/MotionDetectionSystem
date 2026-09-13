package detection;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.imgproc.Imgproc;
import events.MotionEvent;

public class MotionDetector {

    public MotionEvent detectMotion(Mat previousFrame, Mat currentFrame) {
        Mat difference = new Mat();

        Core.absdiff(previousFrame,
                currentFrame,
                difference);

        Mat grayDifference = new Mat();

        Imgproc.cvtColor(difference,
                grayDifference,
                Imgproc.COLOR_BGR2GRAY);

        Mat thresholdDifference = new Mat();
        Imgproc.threshold(grayDifference,
                thresholdDifference,
                25,
                255,
                Imgproc.THRESH_BINARY);

        int changedPixels = Core.countNonZero(thresholdDifference);

        System.out.println("Changed pixels: " + changedPixels);

        boolean motionDetected = changedPixels > 5000 && previousFrame != null && currentFrame != null;

        return new MotionEvent(System.currentTimeMillis(), motionDetected, changedPixels);
    }

}
