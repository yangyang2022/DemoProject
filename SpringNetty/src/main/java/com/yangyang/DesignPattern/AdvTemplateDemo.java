package com.yangyang.DesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AdvTemplate{

    private String advSubject = "XX activity";
    private String advContext = "advice: send money!";

    public String getAdvSubject() {
        return advSubject;
    }

    public String getAdvContext() {
        return advContext;
    }
}
class Mail implements Cloneable{

    private String reciver;
    private String subject;
    private String appellation;
    private String context;
    private String tail;

    public Mail(AdvTemplate advTemplate){
        this.context = advTemplate.getAdvContext();
        this.subject = advTemplate.getAdvSubject();
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    @Override
    protected Mail clone() throws CloneNotSupportedException {
        return (Mail) super.clone();
    }
}
class Thing implements Cloneable{
    private ArrayList<String> arrayList = new ArrayList<>();

    public void setValue(String value){
        this.arrayList.add(value);
    }
    public List<String> getValue(){return this.arrayList;}

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        Thing thing = (Thing) super.clone();
        thing.arrayList = new ArrayList<>();
        return thing;
    }
}
public class AdvTemplateDemo {

    private static int MAX_COUNT = 6;

    public static void main(String[] args) throws CloneNotSupportedException {
        Thing thing = new Thing();
        thing.setValue("yangyang");

        Thing thing1 = (Thing) thing.clone();
        thing1.setValue("hello");
        System.out.println(thing1.getValue());
    }

    public static void main1(String[] args) throws CloneNotSupportedException {

        int i = 0;
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xx银行版权所有!");

        while (i<MAX_COUNT){
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(getRandString(5)+" 先生(女士)");
            cloneMail.setReciver(getRandString(5)+"@"+getRandString(8)+".com");
            sendMail(cloneMail);
            i++;
        }
    }

    private static void sendMail(Mail mail){
        System.out.println("title: "+mail.getSubject()+"\treciver:"+mail.getReciver()+"\t...sucess!");
    }
    private static String getRandString(int maxLength){
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < maxLength; ++i) {
            sb.append(source.charAt(random.nextInt(source.length())));
        }
        return sb.toString();
    }
}
