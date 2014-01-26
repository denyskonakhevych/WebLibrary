package model.db.entities;

/**
 *
 * @author koxa
 */
public class User {
    
    public static enum Role {
        UNAUTHORIZED("unauthorized"),
        USER("user"),
        MODERATOR("moderator");
        
        private String displayName;

        private Role(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
    
    private Integer id;
    private String login;
    private String password;
    private Role role;
    private String firstName;
    private String secondName;
    private String address;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isModerator() {
        return this.role.equals(Role.MODERATOR);
    }

    public User(int id, String login, String password, Role role, String firstName, String secondName, String address, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.email = email;
    }

    public User() {
        this.role = Role.UNAUTHORIZED;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", firstName=" + firstName + ", secondName=" + secondName + ", address=" + address + ", email=" + email + '}';
    }
}