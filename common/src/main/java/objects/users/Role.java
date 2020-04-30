package objects.users;

public enum Role {

    USER("User"),
    ADMIN("Admin");

    private final String value;

    Role(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Role{" +
                "value='" + value + '\'' +
                '}';
    }
}
