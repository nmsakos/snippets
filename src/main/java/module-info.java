module snippets {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires slf4j.api;
    requires org.apache.commons.io;
    requires jackson.annotations;
    requires com.fasterxml.jackson.module.paramnames;

    exports com.akos.json;
}