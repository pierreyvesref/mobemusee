package dto;

public enum RoleDTO {

    USER("User"),
    ADMIN("Admin");

    private final String value;

    private RoleDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
