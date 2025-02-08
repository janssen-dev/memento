package dev.janssensoftware.memento.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class HexagonalArchitectureTest {

    private static final JavaClasses classes = new ClassFileImporter().importPackages("dev.janssensoftware.memento");

    /**
     * Domain layer should only depend on itself, Lombok, Jakarta, and Java packages.
     */
    @Test
    void domain_should_only_depend_on_itself_or_db() {
        ArchRule rule = classes()
                .that().resideInAPackage("..domain..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..domain..", "lombok..", "jakarta..", "java..");

        rule.check(classes);
    }

    /**
     * Application layer should only depend on itself, domain, Spring, Lombok, and Java packages.
     */
    @Test
    void application_should_only_depend_on_itself_or_domain_or_frameworks() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..application..", "..domain..", "org.springframework..", "lombok..", "java..");

        rule.check(classes);
    }

    /**
     * Persistence ports should only be accessed by the application layer or infrastructure adapters.
     */
    @Test
    void ports_should_only_be_used_by_application_and_adapters() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application..port..")
                .should().onlyBeAccessed().byAnyPackage("..application..", "..infrastructure..adapter..");

        rule.check(classes);
    }
}
