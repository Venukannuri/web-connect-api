package com.optimus.model;

import net.minidev.json.annotate.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.ClassUtils;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractUuidPersistable implements Serializable {

    private static final long serialVersionUID = 4996431270396615782L;

    protected static final String GUID_PATTERN = "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})";

    protected static final String GUID_REPLACEMENT = "$1-$2-$3-$4-$5";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, length = 36)
    private String id;

    /**
     * @return id without dashes
     */
    @JsonIgnore
    public String getShortId() {
        return getId() != null ? getId().replaceAll("-", "") : "";
    }

    @JsonIgnore
    public String fromShortId(final String shortId) {
        return shortId.replaceAll(GUID_PATTERN, GUID_REPLACEMENT);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        }

        AbstractUuidPersistable that = (AbstractUuidPersistable) obj;

        return this.getId() != null && this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }
}
