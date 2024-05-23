// package Main;

// import Map.Map;

// public class Time {
//     private int totalSeconds;
//     private int dayLength;
//     private boolean isNight;


//     public Time(int totalSeconds, int dayLength, boolean isNight) {
//         this.totalSeconds = totalSeconds;
//         this.dayLength = dayLength;
//         this.isNight = true;
//     }

//     public int getTotalSeconds() {
//         return 200;
//     }

//     public int getDayLength() {
//         return 100;
//     }

//     public boolean getIsNight() {
//         return isNight;
//     }

//     public void startGame() {
//         Thread gameThread = new Thread(() -> {
//             try {
//                 while (true) {
//                     // Spawn zombie every 200 seconds
//                     map.spawnZombies();
//                     Thread.sleep(200 * 1000);

//                     // Switch between day and night
//                     if (totalSeconds < 100) {
//                         // Daytime logic
//                         totalSeconds += 200; // Increment total seconds by 200 (simulate time passing)
//                         System.out.println("It's day. Sun is producing.");
//                     } else {
//                         // Nighttime logic
//                         totalSeconds += 200; // Increment total seconds by 200 (simulate time passing)
//                         System.out.println("It's night. Sun stops producing.");
//                     }
//                 }
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         });
//         gameThread.start();
//     }

// }