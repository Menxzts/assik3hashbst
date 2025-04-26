public class MyTestingClass {
    private final int id;
    private final String code;

    public MyTestingClass(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        MyTestingClass that = (MyTestingClass) o;
        if (id != that.id) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        if (code != null) {
            for (int i = 0; i < code.length(); i++) {
                result = 31 * result + code.charAt(i);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", code: '" + code + "'}";
    }
}

