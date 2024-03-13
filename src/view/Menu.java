package view;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class Menu {
    protected String title;
    protected ArrayList<String> choices;
    //Library week9 = new Library();
    public Menu() {
    }

    public Menu(String title, String[] mchon){
        this.title = title;
        choices= new ArrayList<>();
        Collections.addAll(choices, mchon);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
    //----------------------------------------------------
    public void display(){
        System.out.print("----");
        System.out.print(title);
        System.out.println("----");
        //System.out.println("==========================================================================");
        for(int i=0; i<choices.size();i++){
            System.out.println((i+1)+". "+choices.get(i));
        }
        System.out.println("==========================================================================");
    }
    //----------------------------------------------------
    public int getSelected(){
        display();
        Scanner sc= new Scanner(System.in);
        System.out.print("Please choice one option: ");
        return sc.nextInt();
    }
    //----------------------------------------------------
    public abstract void execute(int ch);
    //----------------------------------------------------
    public void run(){
        while(true){
            int ch=getSelected();
            if(ch<=choices.size()) execute(ch);
            else break;
        }
    }
//----------------------------------------------------
}
