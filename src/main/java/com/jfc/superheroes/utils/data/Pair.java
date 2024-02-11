package com.jfc.superheroes.utils.data;


public class Pair<L, R> {
    public static final Pair<Object, Object> EMPTY = new Pair();
    private L left;
    private R right;

    public Pair() {
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        Pair<L, R> pair = new Pair();
        pair.setLeft(left);
        pair.setRight(right);
        return pair;
    }

    public boolean isEmpty() {
        return this.right == null && this.left == null;
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    public void setLeft(final L left) {
        this.left = left;
    }

    public void setRight(final R right) {
        this.right = right;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Pair)) {
            return false;
        } else {
            Pair<?, ?> other = (Pair)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$left = this.getLeft();
                Object other$left = other.getLeft();
                if (this$left == null) {
                    if (other$left != null) {
                        return false;
                    }
                } else if (!this$left.equals(other$left)) {
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
        return other instanceof Pair;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $left = this.getLeft();
        result = result * 59 + ($left == null ? 43 : $left.hashCode());
        Object $right = this.getRight();
        result = result * 59 + ($right == null ? 43 : $right.hashCode());
        return result;
    }

    public String toString() {
        Object var10000 = this.getLeft();
        return "Pair(left=" + var10000 + ", right=" + this.getRight() + ")";
    }
}