# Camel on EAP ActiveMQ Example

This quickstart shows how to use Camel on EAP for connecting to the A-MQ xPaaS message broker on OpenShift.
This example will connect to the A_MQ message broker and send messages to a queue TEST.FOO

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- The Red Hat JBoss A-MQ xPaaS product should already be installed and running on your OpenShift installation, one simple way to run a A-MQ service is following the documentation of the A-MQ xPaaS image for OpenShift related to the `amq62-basic` template.

When you Deployed the JBoss A-MQ xPaaS product, it should have been assigned a few serivce names based on the broker name and be configured with a user name and password.  The service this application needs to connect to is ${broker-name}-amq.
To see all the running service names run `oc get services`.  

### Running via an S2I Application Template

Applicaiton templates allow you deploy applications to OpenShift by filling out a form in the OpenShift console that allows you to adjust deployment parameters.  This template uses an S2I source build so that it handle building and deploying the application for you.

Create the quickstart template:

    oc create -f quickstart-template.json

Now when you use "Add to Project" button in the OpenShift console, you should see a template for this quickstart.
