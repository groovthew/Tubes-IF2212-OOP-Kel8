package Sun;

import Map.Map;

public interface ProduceSun {
    void increaseSun();
    int getAmount();
    void startProducingSun(Map map);
    void stopProducingSun();
    boolean reduceSun(int cost);
}
