public class StudentVal {
    private final String name;
    private final int age;
    private final int course; // заменили GPA на course

    public StudentVal(String name, int age, int course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "{" + name + ", age=" + age + ", course=" + course + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentVal other = (StudentVal) obj;
        if (age != other.age) return false;
        if (course != other.course) return false;
        return (name == null ? other.name == null : name.equals(other.name));
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + course;
        return result;
    }
}

