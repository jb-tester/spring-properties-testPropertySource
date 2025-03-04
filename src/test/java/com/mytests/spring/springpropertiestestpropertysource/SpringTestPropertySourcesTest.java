package com.mytests.spring.springpropertiestestpropertysource;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Spring Debugger:
// all properties can be evaluated in debugger
// for the overridden properties the actual value is shown in the corresponding *.properties files
// (application.properties and test_messages.properties added via @TestPropertySource#locations)
// Problems:
// Source|Override for the property evaluation results works only for local.prop2 that is set in application.properties only.
// Other sources (test_messages.properties and @TestPropertySource#properties) are not navigable
@SpringBootTest
@TestPropertySource(locations = {"classpath:test_messages.properties"}, properties = {"local.prop1 = local.prop1 from TestPropertySources", "tm.prop1 = tm.prop1 from TestPropertySources"})
class SpringTestPropertySourcesTest {

    // set in test_messages.properties, overridden in @TestPropertySource#properties
    @Value("${tm.prop1}") // no folded value is displayed
    String attr0;
    // set in test_messages.properties only
    @Value("${tm.prop2}") // no folded value is displayed
    String attr1;
    // set in application.properties, overridden in @TestPropertySource#properties
    @Value("${local.prop1}")   // folded value displays the one from application.properties instead of the overridden one
    String attr2;
    // set in application.properties only
    @Value("${local.prop2}")
    String attr3;
    // set in application.properties, overridden in @TestPropertySource#properties
    @Value("${local.prop0}")
    String attr00;





    @Test
    void testProperties() {

        assertEquals("local_prop0 from test_messages.properties", attr00);
        assertEquals("tm.prop1 from TestPropertySources", attr0);
        assertEquals("tm.prop2 from test_messages.properties", attr1);
        assertEquals("local.prop1 from TestPropertySources", attr2);
        assertEquals("local_prop2 from application.properties", attr3);
    }


}
