//import javax.lang.model.element.Element;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Student {
//
//    private int id;
//    private String name;
//
//    public Student(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public List<String> chempions(ElementsCollection elements) {
//
//        List<String> arr = new ArrayList<>();
//                for(int i = 0; i< elements.length; i++) {
//                    arr.add(elements.get(i).getText());
//                }
//                return arr;
//    }
//
//   ElementsCollection locator = By.xpath("//*[@class='wide']/tbody/tr/td/b/a");
//
//
//
//}
//
//  class StudentManager {
//    private List<Student> students;
//
//      public StudentManager(List<Student> students) {
//          this.students = new ArrayList<>(students);
//      }
//
//      public StudentManager(){
//          this.students = new ArrayList<>();
//      }
//
//
//      List<Student> student1 = new ArrayList<>();
//
//
//      // добавление студентов
//      public void addStudents (Student student) {
//          students.add(student);
//      }
//
//      // добавление студентов
//      public boolean removeStudents (int id) {
//          return students.removeIf(student-> student.getId() == id);
//
//
//
//
//      }
//
//
//      public static void main(String[] args) {
//          Student student1 = new Student(1, "Masha");
//          StudentManager studentManager = new StudentManager();
//
//          studentManager.addStudents(student1);
//          System.out.println(student1.getId());
//          System.out.println(student1.getName());
//
//          studentManager.removeStudents(1);
//          System.out.println(student1.getId());
//
//      }
//}
////123321
//
//
//
