// package Grid;
// import java.util.ArrayList;
// import java.util.List;

// import Tanaman.Plant;

// public class Inventory <T extends Plant> implements Grid {
//     private List<T> container;
//     public Inventory(){
//         this.container = new ArrayList<>();
//     }
//     public List<T> getContainer() {
//         return container;
//     }

//     public boolean isEmpty() {
//         return container.isEmpty();
//     }

//     public void displayInventory() {
//         if (isEmpty()) {
//             System.out.println("Inventory is empty.");
//         } else {
//             System.out.println("Inventory contains:");
//             for (T plant : container) {
//                 System.out.println(plant.getName());
//             }
//         }
//     }

//     @Override
//     public void addPlant(T plant) {
//         container.add(plant);
//     }

//     @Override
//     public void removePlant() {

//     }

//     @Override
//     public void switchPlant() {

//     }
//     @Override
//     public void addPlant() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'addPlant'");
//     }
//     @Override
//     public void removeZombie() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'removeZombie'");
//     }
//     @Override
//     public boolean hasZombie() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'hasZombie'");
//     }
// }
