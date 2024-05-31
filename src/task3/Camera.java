package task3;

public class Camera {
    private String model;

    public Camera(String model) {
        this.model = model;
    }

    public void shooting() {
        System.out.println("Съемки на камере " + model);
    }

    public String zoom() {
        return  "наезд камеры ";
    }


}

