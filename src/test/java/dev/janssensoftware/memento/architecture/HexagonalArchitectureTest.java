package dev.janssensoftware.memento.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class HexagonalArchitectureTest {

    private static final JavaClasses classes = new ClassFileImporter().importPackages("dev.janssensoftware.memento");

    /**
     * Domain layer should only depend on itself, Lombok, Jakarta, and Java packages.
     */
    @Test
    void domain_should_only_depend_on_itself_lombok_jakarta_java() {
        ArchRule rule = classes()
                .that().resideInAPackage("..domain..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..domain..", "lombok..", "jakarta..", "java..");

        rule.check(classes);
    }

    /**
     * Application layer should only depend on itself, domain, Spring, Lombok, and Java packages.
     */
    @Test
    void application_should_only_depend_on_itself_domain_spring_lombok_java() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..application..", "..port..", "..domain..", "org.springframework..", "lombok..", "java..");

        rule.check(classes);
    }

    /**
     * Input ports should only be accessed by input adapters.
     */
    @Test
    void input_ports_should_only_be_accessed_by_adapters() {
        ArchRule rule = classes()
                .that().resideInAPackage("..port..in")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..adapter..in..");

        rule.check(classes);
    }

    /**
     * Output ports should only be accessed by the application layer.
     */
    @Test
    void output_ports_should_only_be_accessed_by_application_adapter() {
        ArchRule rule = classes()
                .that().resideInAPackage("..port..out")
                .should().onlyBeAccessed().byClassesThat().resideInAnyPackage("..application..", "..adapter..");

        rule.check(classes);
    }

    /**
     * Ports should only depend on domain and Java packages.
     */
    @Test
    void ports_should_only_depend_on_domain_application_java() {
        ArchRule rule = classes()
                .that().resideInAPackage("..port..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..domain..", "..application..", "..java..");

        rule.check(classes);
    }

    /**
     * Infrastructure layer should not depend on the application layer, domain layer or ports.
     */
    @Test
    void infrastructure_should_not_depend_on_application_domain_port() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..infrastructure..")
                .should().dependOnClassesThat().resideInAnyPackage("..application..", "..port..", "..domain..");

        rule.check(classes);
    }
}