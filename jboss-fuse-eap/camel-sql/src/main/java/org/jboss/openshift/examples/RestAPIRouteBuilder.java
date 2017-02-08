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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.model.rest.RestBindingMode;

@ContextName("camel-sql-context")
public class RestAPIRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {
        restConfiguration()
            .contextPath("/example-camel-sql")
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Camel REST API")
            .apiProperty("api.version", "1.0")
            .apiProperty("cors", "true")
            .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json);

        rest("/books").description("Books REST service")
            .get("/").description("The list of all the books")
                .route().routeId("books-api")
                .to("sql:select distinct description from orders?" +
                        "dataSource=datasource&" +
                        "outputClass=org.jboss.openshift.examples.Book")
                .endRest()
            .get("order/{id}").description("Details of an order by id")
                .route().routeId("order-api")
                .to("sql:select * from orders where id = :#${header.id}?" +
                        "dataSource=datasource&outputType=SelectOne&" +
                        "outputClass=org.jboss.openshift.examples.Order");
    }
}
