package dev.janssensoftware.memento.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class HexagonalArchitectureTest {

    private static final JavaClasses classes = new ClassFileImporter().importPackages("dev.janssensoftware.memento");

    /**
     * Domain layer should not depend on any external frameworks or infrastructure-related packages.
     */
    @Test
    void domain_should_not_depend_on_frameworks_or_adapters() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "..application..",
                        "..infrastructure..",
                        "org.springframework..",
                        "javax.."
                );

        rule.check(classes);
    }

    /**
     * Application layer should only depend on the domain layer, avoiding direct dependencies on infrastructure.
     */
    @Test
    void application_should_only_depend_on_domain() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

        rule.check(classes);
    }

    /**
     * Infrastructure layer should not directly depend on the domain layer but instead use application ports.
     */
    @Test
    void infrastructure_should_not_depend_on_domain_directly() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..infrastructure.adapter..")
                .should().dependOnClassesThat().resideInAPackage("..domain..")
                .allowEmptyShould(true);

        rule.check(classes);
    }

    /**
     * Persistence ports should only be accessed by the application layer or infrastructure adapters.
     */
    @Test
    void ports_should_only_be_used_by_application_and_adapters() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application.port..")
                .should().onlyBeAccessed().byAnyPackage("..application..", "..infrastructure.adapter..");

        rule.check(classes);
    }

    /**
     * Use case classes should reside in the application.usecase package.
     */
    @Test
    void use_cases_should_reside_in_application_usecase() {
        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("UseCase")
                .should().resideInAPackage("..application.usecase..")
                .allowEmptyShould(true);

        rule.check(classes);
    }
}
