package com.jfc.superheroes.utils.data;


public class Triple<L, M, R> {
    public static final Triple<Object, Object, Object> EMPTY = new Triple();
    private L left;
    private M middle;
    private R right;

    public Triple() {
    }

    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        Triple<L, M, R> triple = new Triple();
        triple.setLeft(left);
        triple.setMiddle(middle);
        triple.setRight(right);
        return triple;
    }

    public boolean isEmpty() {
        return this.right == null && this.middle == null && this.left == null;
    }

    public L getLeft() {
        return this.left;
    }

    public M getMiddle() {
        return this.middle;
    }

    public R getRight() {
        return this.right;
    }

    public void setLeft(final L left) {
        this.left = left;
    }

    public void setMiddle(final M middle) {
        this.middle = middle;
    }

    public void setRight(final R right) {
        this.right = right;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Triple)) {
            return false;
        } else {
            Triple<?, ?, ?> other = (Triple)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$left = this.getLeft();
                    Object other$left = other.getLeft();
                    if (this$left == null) {
                        if (other$left == null) {
                            break label47;
                        }
                    } else if (this$left.equals(other$left)) {
                        break label47;
                    }

                    return false;
                }

                Object this$middle = this.getMiddle();
                Object other$middle = other.getMiddle();
                if (this$middle == null) {
                    if (other$middle != null) {
                        return false;
                    }
                } else if (!this$middle.equals(other$middle)) {
                    return false;
                }

                Object this$right = this.getRight();
                Object other$right = other.getRight();
                if (this$right == null) {
                    if (other$right != null) {
                        return false;
                    }
                } else if (!this$right.equals(other$right)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Triple;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $left = this.getLeft();
        result = result * 59 + ($left == null ? 43 : $left.hashCode());
        Object $middle = this.getMiddle();
        result = result * 59 + ($middle == null ? 43 : $middle.hashCode());
        Object $right = this.getRight();
        result = result * 59 + ($right == null ? 43 : $right.hashCode());
        return result;
    }

    public String toString() {
        Object var10000 = this.getLeft();
        return "Triple(left=" + var10000 + ", middle=" + this.getMiddle() + ", right=" + this.getRight() + ")";
    }
}
