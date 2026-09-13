package camera;

import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;

import nu.pattern.OpenCV;

public class CameraManager {

    private VideoCapture camera;

    public boolean openCamera() {

        OpenCV.loadLocally();

        camera = new VideoCapture(0);

        return camera.isOpened();
    }

    public Mat captureFrame() {

        Mat frame = new Mat();

        if(camera.read(frame) && !frame.empty()) {
            return frame;
        }
        return null; 
    }

    public void releaseCamera() {

        if(camera != null && camera.isOpened()) {

            camera.release();
            System.out.println("Camera released.");
        }
    }

}