package com.mytests.spring.springpropertiestestpropertysource;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Spring Debugger:
// the properties that reference another properties via placeholders are not evaluated properly:
// the placeholders are not expanded

@SpringBootTest
@TestPropertySource(properties = {"local.prop3 = prop3 from TestPropertySources","local.prop4 = placeholders: ${local.prop5},${local.prop3}","local.prop6 = ${ref.prop1}"})
class SpringTestPropertySourcesPlaceholdersTest {

    // property set in application.properties and overridden in @TestPropertySource
    @Value("${local.prop3}")
    String attr1;
    // property is set in @TestPropertySource and references another properties via placeholders:
    // one of these properties is set in application.properties, another is set (overridden) in @TestPropertySource
    @Value("${local.prop4}")
    String attr2;
    // property is set in @TestPropertySource and references another property via placeholders:
    // this property is set in application.properties and references another property, set in application.properties
    @Value("${local.prop6}")
    String attr3;



    @Test
    void testProperties() {

        assertEquals("prop3 from TestPropertySources", attr1);
        assertEquals("placeholders: local_prop2 from application.properties,prop3 from TestPropertySources", attr2);
        assertEquals("referenced property", attr3);
    }


}
