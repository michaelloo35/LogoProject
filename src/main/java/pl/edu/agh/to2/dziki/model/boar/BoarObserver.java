package pl.edu.agh.to2.dziki.model.boar;

public interface BoarObserver {
    void onRotate(BoarActionData data);

    void onMove(BoarActionData data);

    void onHide();

    void onShow(BoarActionData data);

    void onInitialize(BoarActionData data);

    void onClear();

    void onRectangle(BoarActionData data, double width, double height);

    void onOval(BoarActionData data, double width, double height);

}
