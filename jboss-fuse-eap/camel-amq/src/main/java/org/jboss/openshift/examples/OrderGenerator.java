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

import java.io.InputStream;
import java.util.Random;

public class OrderGenerator {

    private int count = 1;
    private Random random = new Random();

    public InputStream generateOrder() {
        int number = random.nextInt(5) + 1;
        String name = "/data/order" + number + ".xml";
        return getClass().getResourceAsStream(name);
    }

    public String generateFileName() {
        return "order" + count++ + ".xml";
    }
}
