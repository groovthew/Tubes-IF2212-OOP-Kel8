// package Sun;

// import Tanaman.*;
// import Sun.*;

// public class tes {
//     public static void main(String[] args) {
//         SunManager sunManager = new SunManager();

//         Sun sun = new Sun(0);
//         Sunflower sunflower = new Sunflower(null, 0, 0, 0, 0, 0, 0);

//         sunManager.addProducer(sun);
//         sunManager.addProducer(sunflower);

//         // Start producing sun
//         sun.startProducingSun(false);
//         sunflower.startProducingSun(false);

//         // Simulate game running for some time
//         try {
//             Thread.sleep(15000); // Game runs for 15 seconds
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Simulate game over
//         System.out.println("Game Over! Stopping sun production.");
//         sunManager.sunGameOver();

//         // Allow some time to ensure all sun production threads are stopped
//         try {
//             Thread.sleep(5000); // Wait for 5 seconds to ensure all threads are stopped
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Display total sun after game over
//         System.out.println("Total Sun after game over: " + sunManager.getTotalSun());

//         // Validate that sun production has stopped
//         if (!sun.isProducingSun() && !sunflower.isProducingSun()) {
//             System.out.println("Sun production has stopped successfully.");
//         } else {
//             System.out.println("Sun production is still running.");
//         }
//     }
// }
