public class StudentKey {
    private final String name;
    private final int id;

    public StudentKey(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentKey other = (StudentKey) obj;
        if (id != other.id) return false;
        return (name == null ? other.name == null : name.equals(other.name));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + name + ", id=" + id + "}";
    }
}

