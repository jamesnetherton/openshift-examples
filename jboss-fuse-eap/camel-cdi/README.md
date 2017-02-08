# Camel CDI Example

This quickstart shows how to configure Camel routes using CDI. The example exposes an HTTP endpoint
which can be interacted with.

### Building

The example can be built with

    mvn clean install

### Running via an S2I Application Template

Applicaiton templates allow you deploy applications to OpenShift by filling out a form in the OpenShift console that allows you to adjust deployment parameters.  This template uses an S2I source build so that it handle building and deploying the application for you.

Create the quickstart template:

    oc create -f quickstart-template.json

Now when you use "Add to Project" button in the OpenShift console, you should see a template for this quickstart.


### HTTP Endponint

When the quickstart has deployed successfully, you can test it in a web browser by navigating to the following URL:

- <http://my-openshift-host/example-camel-cdi?name=Test>
 
