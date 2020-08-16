package chapter_3;

public class TestNull {

    public static class Model{
        private String name;
        private Integer id;

        public Model() {
        }

        public String getName() {
            return name;
        }

        public Integer getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public static void main(String[] args  ) {
        Model model = new Model();
        Model model2 = new Model();

        model2.setName(model.getName());

    }
}
