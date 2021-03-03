package com.webapp.TextBook.repository.data_access;


import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * <h1>UserRole</h1>
 * <h2>Type: Enum</h2>
 *
 * <p>Holds the possible values to capture the UserRole for a logged in User.
 * These values are determined and fully defined from the business context and are explicated within
 * the FRD and traceability document.</p>
 *
 */
public enum UserRole {
    Supervisor("Supervisor"), StudentEmployee("StudentEmployee");

    /**
     * Denotes the string literal representation of a nominal value for an UserRole.
     * Usage: for string creation of UserRole for ORM conversion of Oracle's DBO into DA POJO.
     */
    private final String _NOMINAL_VALUE;

    /**
     * Creates a UserRole
     *
     * @param nominalValue: literal representation of a nominal value for an UserRole
     */
    UserRole(String nominalValue){
        this._NOMINAL_VALUE = nominalValue;
    }

    /**
     *
     * @return String representing the nominal literal of the UserRole
     */
    public @NotNull String get_NOMINAL_VALUE(){
        return this._NOMINAL_VALUE;
    }

    /**
     *
     * Creates a UserRole from the literal representation of a nominal value for a UserRole.
     * If the nominal string does not match the inputted parameter then an empty optional is given.
     *
     * @param nominalValue: the string literal value to create an UserRole
     * @return Optional(type parameter one = UserRole) holding the UserRole.
     * If the nominal string does not match the inputted parameter then an empty optional is given.
     */
    public static @NotNull Optional<UserRole> createUserRoleByNominalValue(
            @NotNull final String nominalValue){
        if(nominalValue.equals(UserRole.Supervisor.get_NOMINAL_VALUE()))
            return Optional.of(UserRole.Supervisor);

        else if(nominalValue.equals(UserRole.StudentEmployee.get_NOMINAL_VALUE()))
            return Optional.of(UserRole.StudentEmployee);

        else
            return Optional.empty();

    }

}
