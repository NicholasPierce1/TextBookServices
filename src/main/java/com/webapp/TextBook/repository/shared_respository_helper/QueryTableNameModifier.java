package com.webapp.TextBook.repository.shared_respository_helper;

import javax.validation.constraints.NotNull;

/**
 * <h1>QueryTableNameModifier</h1>
 *
 * <p>Provides helper method/s to assist in query-tablename resolution.</p>
 */
public class QueryTableNameModifier {
    /**
     * Replaces the targeted query with all nominal place holders (tableName)
     * with the selected table targeted for the repository.
     * @param query: String query to manipulate (final member) so new copy given
     * @param tableName: String name of table for which entity repository corresponds with
     * @return amended query with table names inserted at placeholders.
     */
    public static @NotNull String insertTableNameIntoQuery(
            @NotNull final String query,
            @NotNull final String tableName){

        return query.replaceAll("tableName",tableName );
    }
}
