package org.nrg.transporter;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Destination {
    private String name;

    // A non-exhaustive list of other possibly necessary properties:
    // private String url;
    // private String protocol;
    // private String username;
    // private String password;
    // private String authToken;

    public Destination() {}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Destination that = (Destination) o;

        return Objects.equal(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
