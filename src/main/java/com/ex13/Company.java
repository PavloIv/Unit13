package com.ex13;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    @Override
    public String toString() {
        return "company:{" +
                "name:'" + name + '\'' +
                ", catchPhrase:'" + catchPhrase + '\'' +
                ", bs:'" + bs + '\'' +
                '}';
    }
}
