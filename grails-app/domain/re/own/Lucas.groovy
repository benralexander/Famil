package re.own

class Lucas {

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Lucas)) return false

        Lucas lucas = (Lucas) o

        if (id != lucas.id) return false
        if (version != lucas.version) return false

        return true
    }

    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + version.hashCode()
        return result
    }

    @Override
    public String toString() {
        return "Lucas{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    static constraints = {
    }
}
