package eu.tastr.infrastructure.specification;

public class SpecificationTestUtil {

    private SpecificationTestUtil(){}

    public static TrueSpecification trueSpecification(){
        return new TrueSpecification();
    }

    public static FalseSpecification falseSpecification(){
        return new FalseSpecification();
    }

    private static class TrueSpecification implements Specification<String> {
        @Override
        public boolean isSatisfiedBy(String s) {
            return true;
        }
    }

    private static class FalseSpecification implements Specification<String> {
        @Override
        public boolean isSatisfiedBy(String s) {
            return false;
        }
    }
}
