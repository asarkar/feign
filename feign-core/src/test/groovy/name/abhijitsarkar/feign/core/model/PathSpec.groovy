package name.abhijitsarkar.feign.core.model

import name.abhijitsarkar.feign.core.matcher.PathMatcher
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Abhijit Sarkar
 */
class PathSpec extends Specification {
    def path

    @Unroll
    def "sets uri when set to #uri"() {
        setup:
        path = new Path()
        path.uri = uri

        expect:
        path.uri == (uri ?: Path.WILDCARD_PATTERN)

        where:
        uri << [null, Path.WILDCARD_PATTERN, '/a']
    }

    @Unroll
    def "sets ignoreCase when set to #ignoreCase"() {
        setup:
        path = new Path()
        path.ignoreCase = ignoreCase

        expect:
        path.ignoreCase == (ignoreCase ?: Boolean.FALSE)

        where:
        ignoreCase << [null, Boolean.TRUE, Boolean.FALSE]
    }

    @Unroll
    def "sets matcher when set to #matcher"() {
        setup:
        path = new Path()
        path.matcher = matcher

        expect:
        path.matcher == (matcher ?: PathMatcher)

        where:
        matcher << [null, PathMatcher, TestMatcher]
    }
}