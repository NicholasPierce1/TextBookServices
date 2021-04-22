package com.webapp.TextBook.repository.data_access;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Entity
public class User extends Person {

    /**
     * <p>
     * This is the DA for the User table, however, a user is partially comprised of the
     * User table and the Spriden table.  The definitions in here are distinct to the User
     * table, but will rely on the Person (Spriden) for partial DA construction and definition.
     * </p>
     */


    /**
     * <p>
     * enumerates the instance members that are distinct to User (not Student)
     *
     * the local id value that uniquely identifies a user with the POJO/DA class
     * required by JPA repository and Hibernate (though Hibernate is not utilized)
     * </p>
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    /**
     * <p>
     *  denotes the hierarchical role of the user (either Supervisor or StudentEmployee)
     *</p>
     */
    public UserRole userRole;


    /**
     * <p>
     * holds the password of the logged in user
     * NOTE: the username (919 number, or id in Person, or spriden_id in Spriden)
     * is defined in Person
     *</p>
     */
    public String password;
    private static final String NOMINAL_PASSWORD = "password";

    /**
     * <p>
     *  Creates a User -- Empty constructor.
     *</p>
     */
    public User() {this._ID = UUID.randomUUID(); }

    /**
     * <p>
     *  Creates a User -- Full constructor with all variables.
     *</p>
     *
     * @param pidm: String pidm variable
     * @param id: String id variable
     * @param firstName: String firstName variable
     * @param lastName: String lastName variable
     * @param middleName: String middleName variable
     * @param userRole: UserRole userRole variable
     * @param password: String password variable
     */
    public User(String pidm, String id, String firstName, String lastName,
                String middleName, UserRole userRole, String password) {
        super(pidm, id, firstName, lastName, middleName);
        this._ID = UUID.randomUUID();
        this.userRole = userRole;
        this.password = password;
    }

    /**
     *<p>
     *     Returns the _ID.
     *</p>
     *
     * @return UUID _ID information.
     */
    public UUID get_ID() {
        return _ID;
    }

    /**
     *<p>
     *     Sets the _ID to a new UUID.
     *</p>
     *
     * @param _ID: UUID _ID variable
     */
    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    /**
     *<p>
     *     Returns the userRole.
     *</p>
     *
     * @return UserRole userRole information.
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     *<p>
     *     Sets the userRole to a new UserRole.
     *</p>
     *
     * @param userRole: UserRole userRole variable
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     *<p>
     *     Returns the password.
     *</p>
     *
     * @return String password information.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     *<p>
     *     Sets the password to a new String.
     *</p>
     *
     * @param password: String password variable
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * conglomerates the two partials into a contiguous array for DA construction
     * @param spridenDbo: Object[] spridenDbo variable
     * @param textbookServiceLoginDbo: Object textbookServiceLoginDbo variable
     * @return Object[] conglomerateDataAccess variable
     */
    public static @NotNull Object[] conglomerateDboArrays(@NotNull final Object[] spridenDbo, @NotNull final Object[] textbookServiceLoginDbo){

        // creates object array and set it to the copy
        Object[] congolmerateDataAccess = Arrays.copyOf(
                spridenDbo,
                spridenDbo.length + textbookServiceLoginDbo.length -1);
        // -1 (username of textbook service dbo being omitted)

        // sets latter partial values to conglomerate -- username being omitted
        // given: username is the first column in the dbo
        for(int i = 0; i < textbookServiceLoginDbo.length - 1; i++)
            congolmerateDataAccess[spridenDbo.length + i] = textbookServiceLoginDbo[i + 1];

        return congolmerateDataAccess;
    }

    /**
     *<p>
     * Updates a Java User object with the values given from the DBO object information.
     *</p>
     *
     * @param values: Generic object holding the DBO information of a User
     */
    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {

        // invokes super to set Person attributes
        // given: person attributes come first
        super.updateDataAccessObject(values);


        // given: order of values goes pkey (username, password), userRole
        // ["blah", "blah", "username", "password", "user-role"]
        this.password = (String)values[values.length - 2];
        this.userRole = UserRole.createUserRoleByNominalValue((String)values[values.length - 1]).orElseThrow();

    }

    /**
     * <p>Checks for equality among Users</p>
     * @param objectB an upcasted object representing a User
     * @return a boolean indicating if the two Users are equal
     */
    @Override
    public boolean equals(Object objectB){

        // if objectB is null then return false
        if(objectB == null)
            return false;

        // if object addresses are same then return true
        if(this == objectB)
            return true;

        // type cast objectB to User
        final User user = (User)objectB;

        // equality check (Person state equal & user-role, password) are equal
        return
                super.equals(objectB) &&
                        this.getPassword().equals(user.getPassword()) &&
                        this.getUserRole() == user.getUserRole();
    }

    @Override
    public String toString(){
        return super.toString() +
                "\npassword: " + this.getPassword() +
                "\nuser role: " + this.getUserRole();
    }
    @Override
    public @NotNull JSONObject createJsonObjectForm() throws JSONException {

        // create a new json object, and use the nominal keys to place the instance/field in
        final JSONObject jsonObject = super.createJsonObjectForm();
        jsonObject.put(NOMINAL_PASSWORD,this.getPassword());


        return jsonObject;
    }
}
