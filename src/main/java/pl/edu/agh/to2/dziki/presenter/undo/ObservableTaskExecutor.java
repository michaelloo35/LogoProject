package pl.edu.agh.to2.dziki.presenter.undo;

public interface ObservableTaskExecutor {
    void subscribe(TaskExecutorObserver observer);
}
