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

public class OrderProcessorRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {
        // Route to generate orders and persist them to the MySQL database
        from("timer:new-order?delay=1s&period={{quickstart.generateOrderPeriod:2s}}")
        .routeId("generate-order")
        .bean("orderService", "generateOrder")
        .to("sql:insert into orders (id, item, amount, description, processed) values " +
                "(:#${body.id} , :#${body.item}, :#${body.amount}, :#${body.description}, false)?" +
                "dataSource=datasource")
        .log("Inserted new order ${body.id}");

        // A second route polls the database for new orders and processes them
        from("sql:select * from orders where processed = false?" +
            "consumer.onConsume=update orders set processed = true where id = :#id&" +
            "consumer.delay={{quickstart.processOrderPeriod:5s}}&" +
            "dataSource=datasource")
        .routeId("process-order")
        .bean("orderService", "rowToOrder")
        .log("Processed order #id ${body.id} with ${body.amount} copies of the «${body.description}» book");
    }
}
