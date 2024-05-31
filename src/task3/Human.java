package task3;

public class Human {

    private String name;

    private String gender;
    private static Hair hair;

    public Human(String name, String gender, Hair hair) {
        this.name = name;
        this.gender = gender;
        this.hair = hair;
    }

        void feeling() {
            System.out.println(name + " почувствовал это.");
        }

        void move(String speed) {
            Controller controller = new Controller("Пульт");
            System.out.println(name + " " + speed + " двигался" + controller.moveTowardsRemote());
            hair.moveHair(this);
            feeling();
            think();
        }

        void think(){
            Camera camera = new Camera("камера");
            System.out.println(name + " понял, что это " + camera.zoom());
        }

        @Override
        public String toString(){
            return this.name;
        }

    public Hair getHair() {
        return hair;
    }


    public static class Hair {
        private String color;
        private int length;

        public Hair(String color, int length) {
            this.color = color;
            this.length = length;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void moveHair(Human human) {
            System.out.println(human.toString()+"Волосы зашевелились.");
        }
    }
}
