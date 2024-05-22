package Sun;

public interface ProduceSun {
    void increaseSun();
    int getAmount();
    void startProducingSun();
    void stopProducingSun();
    void setSunListener(SunListener listener);
}
