module snippets {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires org.apache.commons.io;
    requires jackson.annotations;
    requires com.fasterxml.jackson.module.paramnames;
    requires org.apache.commons.lang3;

    exports com.akos.json;
}