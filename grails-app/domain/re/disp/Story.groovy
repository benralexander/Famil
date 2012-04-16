package re.disp

class Story  extends Disp {

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    static constraints = {
    }
}
