package com.gyp.springboot.clones;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 9:55 - 2021/2/19
 */
public class Music implements Cloneable{

   private String name;

   private Person person;

   Music(String name){
       this.name = name;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    protected Music clone()  {
        try {
            return (Music)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }

    public static void main(String[] args) {
        Music music = new Music("分手快乐");
        Person person = new Person();
        person.setActor("梁静茹");
        music.setPerson(person);

        Music musicCopy =  music.clone();
        music.setName("赤伶");
        Person person1 = new Person();
        music.setPerson(person1);
        System.out.println("music----"+music);
        System.out.println("musicCopy----"+musicCopy);

    }

     static class Person{
       String actor;

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getActor() {
            return actor;
        }
    }
}
