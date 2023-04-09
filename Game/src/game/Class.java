
package game;
public class Class {
    public Moves [] moveset = new Moves [5];
    public String Name;
    public int HP = 100;
    boolean blocked;
    public String className;
    //get actionArray
    public Moves [] getMoveset(){
        return moveset;
    }
    //get & set for className
    public String getClassName(){
        return className;
    }
    public void setClassName(String className){
        this.className = className;
    }
    //get & set for Name
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    //get for HP
    public int getHP(){
        return HP;
    }
    //class constructor
    public Class(String Name,int Class){
        this.Name = Name;
        //different moveset for each class
        switch(Class){
            case 1 : className = "Mercenary";
            moveset[0] = new Moves("cannot act");
            moveset[1] = new Moves("Physical");
            moveset[2] = new Moves("Range");
            moveset[3] = new Moves("Counter");
            moveset[4] = new Moves("Defense");
            break;
            case 2 : className = "Paladin";
            moveset[0] = new Moves("cannot act");
            moveset[1] = new Moves("Physical");
            moveset[2] = new Moves("Magic");
            moveset[3] = new Moves("Counter");
            moveset[4] = new Moves("Defense");
            break;
            case 3 : className = "Runeslayer";
            moveset[0] = new Moves("cannot act");
            moveset[1] = new Moves("Magic");
            moveset[2] = new Moves("Range");
            moveset[3] = new Moves("Counter");
            moveset[4] = new Moves("Defense");
            break;
            case 4 : className = "Shade";
            moveset[0] = new Moves("cannot act");
            moveset[1] = new Moves("Physical");
            moveset[2] = new Moves("Range");
            moveset[3] = new Moves("Counter");
            moveset[4] = new Moves("Magic");
            break;
            case 5 : className = "Spellcaster";
            moveset[0] = new Moves("cannot act");
            moveset[1] = new Moves("Physical");
            moveset[2] = new Moves("Range");
            moveset[3] = new Moves("Magic");
            moveset[4] = new Moves("Defense");
            break;
        }
    }
}
