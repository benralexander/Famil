package re.own

class Ben {

    @Override
    public String toString() {
        return "Ben{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Ben ben = (Ben) o

        if (id != ben.id) return false
        if (version != ben.version) return false

        return true
    }

    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + version.hashCode()
        return result
    }

    static constraints = {
    }
}
