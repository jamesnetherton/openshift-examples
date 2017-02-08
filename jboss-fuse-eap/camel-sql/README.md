# Camel on EAP SQL Example

This quickstart demonstrates how to use SQL via JDBC together with the Camel REST DSL to expose a RESTful API.

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- A MySQL container is running on your OpenShift installation. To get started with MySQL on OpenShift follow the guide to deploying [database images](https://docs.openshift.com/enterprise/3.1/using_images/db_images/mysql.html).

The service name allocated to MySQL is important. For this example, you should ensure that it is prefixed with a descriptive name. For example `camel-mysql`.

To see all the running service names run `oc get services`.  

### Accessing the REST service

When the example is running, a REST service is available to list the books that can be ordered, and as well the order statuses.

The actual endpoint is using the _context-path_ `example-camel-sql/books` and the REST service provides two services:

- `books`: to list all the available books that can be ordered,
- `order/{id}`: to output order status for the given order `id`.

The example automatically creates new orders with a running order `id` starting from 1.

You can then access these services from your Web browser, e.g.:

- <http://my-openshift-host/example-camel-sql/books>
- <http://my-openshift-host/example-camel-sql/books/order/1>

### Swagger API

The example provides API documentation of the service using Swagger using the _context-path_ `camel-rest-sql/api-doc`. You can access the API documentation from your Web browser at <http://my-openshift-host/camel-rest-sql/api-doc>.

### Running via an S2I Application Template

Applicaiton templates allow you deploy applications to OpenShift by filling out a form in the OpenShift console that allows you to adjust deployment parameters.  This template uses an S2I source build so that it handle building and deploying the application for you.

Create the quickstart template:

    oc create -f quickstart-template.json

Now when you use "Add to Project" button in the OpenShift console, you should see a template for this quickstart.
