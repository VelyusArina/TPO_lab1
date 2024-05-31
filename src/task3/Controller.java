package task3;

public class Controller {

    private String remote;

    public Controller(String remote) {
        this.remote = remote;
    }

    public String moveTowardsRemote() {
        return " приблизился к " + remote;
    }

    @Override
    public String toString() {
        return remote;
    }
}
