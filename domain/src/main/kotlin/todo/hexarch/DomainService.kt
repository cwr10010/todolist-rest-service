package todo.hexarch

import java.lang.annotation.Inherited

/**
 * Annotation that decorates classes as beeing domain services. We use this annotation to tell the Rest adapter which
 * the domain services are.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Inherited
annotation class DomainService
