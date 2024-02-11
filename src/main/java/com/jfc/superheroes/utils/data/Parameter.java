package com.jfc.superheroes.utils.data;



public class Parameter {
    public static final Parameter EMPTY = new Parameter();
    private String name;
    private String value;

    public Parameter() {
    }

    public static Parameter of(String name, String value) {
        Parameter parameter = new Parameter();
        parameter.setName(name);
        parameter.setValue(value);
        return parameter;
    }

    public boolean isEmpty() {
        return this.name == null && this.value == null;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Parameter)) {
            return false;
        } else {
            Parameter other = (Parameter)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

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
        return other instanceof Parameter;
    }

    public String toString() {
        String var10000 = this.getName();
        return "Parameter(name=" + var10000 + ", value=" + this.getValue() + ")";
    }
}