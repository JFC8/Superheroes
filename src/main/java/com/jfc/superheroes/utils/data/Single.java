package com.jfc.superheroes.utils.data;


public class Single<V> {
    public static final Single<Object> EMPTY = new Single();
    private V value;

    public Single() {
    }

    public static <V> Single<V> of(V value) {
        Single<V> single = new Single();
        single.setValue(value);
        return single;
    }

    public boolean isEmpty() {
        return this.value == null;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Single)) {
            return false;
        } else {
            Single<?> other = (Single)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Single;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "Single(value=" + this.getValue() + ")";
    }
}