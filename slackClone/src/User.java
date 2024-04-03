public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private String sz_iconPath;
    // Constructor
    public User(
            String username,
            String firstName,
            String lastName,
            String email,
            String address,
            String password,
            String iconpath
    ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.sz_iconPath = iconpath;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void set_szIconPath(String sz_iconpath) {
        this.sz_iconPath = sz_iconpath;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public String get_szIconPath() {
        return sz_iconPath;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
