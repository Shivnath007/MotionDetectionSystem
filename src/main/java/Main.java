import java.util.Scanner;

import camera.CameraManager;
import org.opencv.core.Mat;
import detection.MotionDetector;
import events.MotionEvent;
import events.EventHistory;
import events.EventManager;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        CameraManager cameraManager = new CameraManager();
        MotionDetector motionDetector = new MotionDetector();
        EventManager eventManager = new EventManager();
        Scanner sc = new Scanner(System.in);
        AtomicBoolean running = new AtomicBoolean(true);

        Thread inputThread = new Thread(() -> {
            sc.nextLine();
            running.set(false);
        });
        inputThread.start();

        boolean opened = cameraManager.openCamera();

        if (opened) {

            Mat previousFrame = cameraManager.captureFrame();

            if (previousFrame == null) {
                System.out.println("Failed to capture initial frame.");
                cameraManager.releaseCamera();
                sc.close();
                return;
            }

            boolean previousMotionDetected = false;
            while (running.get()) {

                Mat currentFrame = cameraManager.captureFrame();

                if (currentFrame == null) {
                    System.out.println("Failed to capture frame.");
                    break;
                }

                MotionEvent motionEvent = motionDetector.detectMotion(previousFrame, currentFrame);

                if(motionEvent.isMotionDetected() && !previousMotionDetected) {
                    eventManager.addEvent(motionEvent);
                } 
                else if (!motionEvent.isMotionDetected() && previousMotionDetected) {
                    MotionEvent endedEvent = eventManager.endActiveEvent(motionEvent.getTimestamp());

                    if(endedEvent != null) {
                        System.out.println("Motion Duration: " + endedEvent.getDuration() + "ms");
                    }
                }

                System.out.println("Motion detected: " + motionEvent.isMotionDetected() + " at timestamp: "
                        + motionEvent.getTimestamp());
                System.out.println("Motion duration: " + motionEvent.getDuration() + "ms");
                System.out.println(motionEvent);

                previousFrame = currentFrame;

                previousMotionDetected = motionEvent.isMotionDetected();

                Thread.sleep(100);
            }
            EventHistory eventHistory = new EventHistory(eventManager.getEvents());
            System.out.println("Total motion events: " + eventHistory.getEventCount());
            System.out.println("First event: " + eventHistory.getEvent(0));
            cameraManager.releaseCamera();
        }
        sc.close();

    }
}
