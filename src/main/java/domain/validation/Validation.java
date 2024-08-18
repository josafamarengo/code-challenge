package domain.validation;

public interface Validation<T> {
    public void validate(T request);
}
