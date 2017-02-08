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

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Named;

@Named
public class OrderService {

    private final AtomicInteger counter = new AtomicInteger();
    private final Random amount = new Random();

    public Order generateOrder() {
        Order order = new Order();
        order.setItem(counter.incrementAndGet() % 2 == 0 ? "Camel" : "ActiveMQ");
        order.setAmount(amount.nextInt(10) + 1);
        order.setDescription(counter.get() % 2 == 0 ? "Camel in Action" : "ActiveMQ in Action");
        return order;
    }

    public Order rowToOrder(Map<String, Object> row) {
        Order order = new Order();
        order.setId((Integer) row.get("id"));
        order.setItem((String) row.get("item"));
        order.setAmount((Integer) row.get("amount"));
        order.setDescription((String) row.get("description"));
        order.setProcessed((Boolean) row.get("processed"));
        return order;
    }
}