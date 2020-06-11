package com.github.java.pattern.establish.builder;

/**
 * @author liweigao
 * @date 2020/6/11 下午5:23
 */
public final class PersonBuilder {
    private Integer age;
    private String name;
    private String motto;
    private String address;
    private String telephone;
    private String email;

    private PersonBuilder() {
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }

    public PersonBuilder age(Integer age) {
        this.age = age;
        return this;
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder motto(String motto) {
        this.motto = motto;
        return this;
    }

    public PersonBuilder address(String address) {
        this.address = address;
        return this;
    }

    public PersonBuilder telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public PersonBuilder email(String email) {
        this.email = email;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        person.setMotto(motto);
        person.setAddress(address);
        person.setTelephone(telephone);
        person.setEmail(email);
        return person;
    }

    public static void main(String[] args) {
        Person person = PersonBuilder.aPerson().name("soberChina").age(26).address("北京xx区xxx街道xxx小区xxx号楼").email("soberchina0@gamil.com").build();
        System.out.println(person.toString());
    }
}
