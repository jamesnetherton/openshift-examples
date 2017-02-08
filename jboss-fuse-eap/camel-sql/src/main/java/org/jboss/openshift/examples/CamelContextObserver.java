/*
 * #%L
 *
 * %%
 * Copyright (C) 2017 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.jboss.openshift.examples;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.camel.management.event.CamelContextStartingEvent;

@ApplicationScoped
public class CamelContextObserver {

    @Inject
    private DataSource dataSource;

    public void onContextStarting(@Observes CamelContextStartingEvent event) {
        // Initialize database
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS orders");
            statement.execute("CREATE TABLE orders (\n"
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "item VARCHAR(10),\n"
                + "amount INTEGER,\n"
                + "description VARCHAR(30),\n"
                + "processed BOOLEAN\n"
                + ")");
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to initialize database", e);
        }
    }
}
