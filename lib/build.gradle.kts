
plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("extra-java-module-info")
}


version = "1.0.2"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")
}

tasks.test {
    // Use junit platform for unit tests.
    useJUnitPlatform()
}

tasks.compileJava {
    options.javaModuleVersion.set(provider { project.version as String })
}


extraJavaModuleInfo {
    module("commons-math3-3.6.1.jar", "commons.math3", "3.6.1") {
        requiresTransitive("java.desktop")

        exports("org.apache.commons.math3")
        exports("org.apache.commons.math3.analysis")
        exports("org.apache.commons.math3.analysis.differentiation")
        exports("org.apache.commons.math3.analysis.function")
        exports("org.apache.commons.math3.analysis.integration")
        exports("org.apache.commons.math3.analysis.integration.gauss")
        exports("org.apache.commons.math3.analysis.interpolation")
        exports("org.apache.commons.math3.analysis.polynomials")
        exports("org.apache.commons.math3.analysis.solvers")
        exports("org.apache.commons.math3.complex")
        exports("org.apache.commons.math3.dfp")
        exports("org.apache.commons.math3.distribution")
        exports("org.apache.commons.math3.distribution.fitting")
        exports("org.apache.commons.math3.exception")
        exports("org.apache.commons.math3.exception.util")
        exports("org.apache.commons.math3.filter")
        exports("org.apache.commons.math3.fitting")
        exports("org.apache.commons.math3.fitting.leastsquares")
        exports("org.apache.commons.math3.fraction")
        exports("org.apache.commons.math3.genetics")
        exports("org.apache.commons.math3.geometry")
        exports("org.apache.commons.math3.geometry.enclosing")
        exports("org.apache.commons.math3.geometry.euclidean.oned")
        exports("org.apache.commons.math3.geometry.euclidean.threed")
        exports("org.apache.commons.math3.geometry.euclidean.twod")
        exports("org.apache.commons.math3.geometry.euclidean.twod.hull")
        exports("org.apache.commons.math3.geometry.hull")
        exports("org.apache.commons.math3.geometry.partitioning")
        exports("org.apache.commons.math3.geometry.partitioning.utilities")
        exports("org.apache.commons.math3.geometry.spherical.oned")
        exports("org.apache.commons.math3.geometry.spherical.twod")
        exports("org.apache.commons.math3.linear")
        exports("org.apache.commons.math3.ml.clustering")
        exports("org.apache.commons.math3.ml.clustering.evaluation")
        exports("org.apache.commons.math3.ml.distance")
        exports("org.apache.commons.math3.ml.neuralnet")
        exports("org.apache.commons.math3.ml.neuralnet.oned")
        exports("org.apache.commons.math3.ml.neuralnet.sofm")
        exports("org.apache.commons.math3.ml.neuralnet.sofm.util")
        exports("org.apache.commons.math3.ml.neuralnet.twod")
        exports("org.apache.commons.math3.ml.neuralnet.twod.util")
        exports("org.apache.commons.math3.ode")
        exports("org.apache.commons.math3.ode.events")
        exports("org.apache.commons.math3.ode.nonstiff")
        exports("org.apache.commons.math3.ode.sampling")
        exports("org.apache.commons.math3.optim")
        exports("org.apache.commons.math3.optim.linear")
        exports("org.apache.commons.math3.optim.nonlinear.scalar")
        exports("org.apache.commons.math3.optim.nonlinear.scalar.gradient")
        exports("org.apache.commons.math3.optim.nonlinear.scalar.noderiv")
        exports("org.apache.commons.math3.optim.nonlinear.vector")
        exports("org.apache.commons.math3.optim.nonlinear.vector.jacobian")
        exports("org.apache.commons.math3.optim.univariate")
        exports("org.apache.commons.math3.optimization")
        exports("org.apache.commons.math3.optimization.direct")
        exports("org.apache.commons.math3.optimization.fitting")
        exports("org.apache.commons.math3.optimization.general")
        exports("org.apache.commons.math3.optimization.linear")
        exports("org.apache.commons.math3.optimization.univariate")
        exports("org.apache.commons.math3.primes")
        exports("org.apache.commons.math3.random")
        exports("org.apache.commons.math3.special")
        exports("org.apache.commons.math3.stat")
        exports("org.apache.commons.math3.stat.clustering")
        exports("org.apache.commons.math3.stat.correlation")
        exports("org.apache.commons.math3.stat.descriptive")
        exports("org.apache.commons.math3.stat.descriptive.moment")
        exports("org.apache.commons.math3.stat.descriptive.rank")
        exports("org.apache.commons.math3.stat.descriptive.summary")
        exports("org.apache.commons.math3.stat.inference")
        exports("org.apache.commons.math3.stat.interval")
        exports("org.apache.commons.math3.stat.ranking")
        exports("org.apache.commons.math3.stat.regression")
        exports("org.apache.commons.math3.transform")
        exports("org.apache.commons.math3.util")
    }
}

