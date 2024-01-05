import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class test {
  @Test
  public void test(){
    a a = new a();
    a.setAge(12);
    a.setName("asd");
    b b = new b();
    b.setAge(123);
    BeanUtils.copyProperties(a,b);
    System.out.println(b.toString());
  }
  class  a{
    String name;
    int age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
  class  b {
    String name;
    int age;
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "b{" +
              "name='" + name + '\'' +
              ", age=" + age +
              '}';
    }
  }
}
