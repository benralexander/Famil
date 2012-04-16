package re.sec

import org.apache.commons.lang.builder.HashCodeBuilder

class ReUserReRole implements Serializable {

    ReUser reUser
    ReRole reRole

    boolean equals(other) {
        if (!(other instanceof ReUserReRole)) {
            return false
        }

        other.reUser?.id == reUser?.id &&
                other.reRole?.id == reRole?.id
    }

    @Override
    public String toString() {
        return "ReUserReRole{" +
                "id=" + id +
                ", reUser=" + reUser +
                ", reRole=" + reRole +
                ", version=" + version +
                '}';
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (reUser) builder.append(reUser.id)
        if (reRole) builder.append(reRole.id)
        builder.toHashCode()
    }

    static ReUserReRole get(long reUserId, long reRoleId) {
        find 'from ReUserReRole where reUser.id=:reUserId and reRole.id=:reRoleId',
                [reUserId: reUserId, reRoleId: reRoleId]
    }

    static ReUserReRole create(ReUser reUser, ReRole reRole, boolean flush = false) {
        new ReUserReRole(reUser: reUser, reRole: reRole).save(flush: flush, insert: true)
    }

    static boolean remove(ReUser reUser, ReRole reRole, boolean flush = false) {
        ReUserReRole instance = ReUserReRole.findByReUserAndReRole(reUser, reRole)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(ReUser reUser) {
        executeUpdate 'DELETE FROM ReUserReRole WHERE reUser=:reUser', [reUser: reUser]
    }

    static void removeAll(ReRole reRole) {
        executeUpdate 'DELETE FROM ReUserReRole WHERE reRole=:reRole', [reRole: reRole]
    }

    static mapping = {
        id composite: ['reRole', 'reUser']
        version false
    }
}
