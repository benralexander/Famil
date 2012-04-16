package re.disp

class Disp {
    String name
    Disp(){
    }

    @Override
    public String toString() {
        return "Disp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }

    Disp(String p_name){
        name = p_name
    }
    static constraints = {
        name(nullable: false)
    }
}
