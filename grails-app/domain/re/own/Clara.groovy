package re.own

class Clara {
    @Override
    public String toString() {
        return "Clara{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Clara clara = (Clara) o

        if (id != clara.id) return false
        if (version != clara.version) return false

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
