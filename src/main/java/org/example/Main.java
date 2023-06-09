package org.example;
import java.util.ArrayList;
import java.util.Scanner;
class TuringMachine{
    ArrayList<String> States=new ArrayList<>();
   ArrayList<String>  Transition=new ArrayList<>();
    ArrayList<Character> Sympols=new ArrayList<>();
    ArrayList<Character> MachineAlphaPet=new ArrayList<>();
    String stringTocheck;
    public void setStates(String states) {
            States.add(states);
       System.out.println(States);
    }

    public void setTransitionFunctions(String transition) {
//        Transition = new ArrayList<String>(n);
        Transition.add(transition);
        System.out.println(Transition);
    }

    public void setSympolsNumber(char sympolsNumber) {
//        Sympols=new ArrayList<Character>(SympolsNumber);
        Sympols.add(sympolsNumber);
        System.out.println(Sympols);
    }

    public void setMachineAlphaPet(char machineAlphaPet) {
//        MachineAlphaPet=new ArrayList<Character>(nom);
        MachineAlphaPet.add(machineAlphaPet);
        System.out.println(MachineAlphaPet);
    }
//    public boolean search(String x,ArrayList<String>y){
//    for(int i=0;i<y.size();i++){
//        if(y==)
//    }
//    }
    public int search(String c,ArrayList<String>x,int head,String State){
//        for (int i = 0; i < x.size(); i++) {
//            for (int j = 0; j < c.length(); j++) {
//                if (x.get(j).contains(State + "-" + c.charAt(head))) return i;
//
//}
//        }
//        return -1;
        for(String z:x){
            if(z.contains(State+"-"+c.charAt(head))) return x.indexOf(z);
        }

        return -1;
    }



    public boolean Transition1(int head,String state) throws Exception{
       //Check if not the String contain Machine Sympols or input Sympols
        for(int i=0;i<stringTocheck.length();i++){
            if(!Sympols.contains(stringTocheck.charAt(i))) {
                throw new Exception("Your Input Violate Turing Machine Sympol You Enter");
            }
        }


       if(head!=stringTocheck.length()-1) {
           if (search(stringTocheck, Transition, head,state)!=-1) {
            String y=Transition.get(search(stringTocheck, Transition, head,state));
           System.out.println(search(stringTocheck, Transition, head,state));
               String newState=y.substring(5,7);
               //Handle Write Action
               char[] z=stringTocheck.toCharArray();
               z[head]=y.charAt(8);
               stringTocheck=String.valueOf(z);

               /*Special Cas
               First We Now Fitch The String Which Hold State We Want
               According To Input
               1-Handle if the Tape Of Machine Stop At LeftMark '<'
               2-Handle Right And Left Action
               3-I will Deal With # as Input Sympol Or Null String
                */


               if (y.charAt(y.length() - 1) == 'R'){ head++;}
               else if (y.charAt(y.length() - 1) == 'L') {
                   if (y.charAt(head - 1) == '<') return false;
                   else head--;
               }


               //Y if We accept The action will be 'Y'es
               if (y.charAt(y.length() - 1) =='Y') return true;

               else if (y.charAt(y.length() - 1) == 'N') return false;

               return Transition1(head,newState);
           }
           else if(search(stringTocheck, Transition, head,state)==-1) {
               throw new Exception("Not Avilable Transition");
           }


      }
        return false;
    }



}

public class Main {
    public static void main(String[] args) {
        TuringMachine t1=new TuringMachine();
        System.out.println("Enter Number Of States");
        Scanner sc = new Scanner(System.in);
        int NumberoFStates = sc.nextInt();
        for(int i=0;i<NumberoFStates;i++){
            System.out.println("Enter"+i+"st"+"States");
            String s=sc.next();
            t1.setStates(s);
        }


        System.out.println("Enter Number of Input Sympols");
        int NumberOfAlphpetinput = sc.nextInt();
        for (int i = 1; i <= NumberOfAlphpetinput; ++i) {
            System.out.println("Enter " + i + "st" + "Alphbet");
            char InputSympols = sc.next().charAt(0);
              t1.setSympolsNumber(InputSympols);
        }
        System.out.println("Enter Number of Machine Alphbet");
        int AlphbetMachine = sc.nextInt();
        for (int i = 1; i <= AlphbetMachine; i++) {
            System.out.println("Enter" + i + "st" + "Alphbet Machine (GAMMA Tuble)");
            char InputMachineAlphaPet = sc.next().charAt(0);
            t1.setMachineAlphaPet(InputMachineAlphaPet );
        }
        System.out.println("Enter Number Of Transition Functions");
        System.err.println("* NOTE TO PUT '-' BETWEEN TRANSITION FUNCTION SYMPOLS");
        System.err.println("* IF MACHINE ACCEPT OR REJECT PUT ',' BEFORE ACTION");

        int NumberOfTransitionOfMachine = sc.nextInt();
        for (int i = 1; i <= NumberOfTransitionOfMachine; i++) {
            System.out.println("Enter" + i + "st" + "Transition Machine (Sigma Tuble)");
            String Function = sc.next();
            t1.setTransitionFunctions(Function);
        }
        System.out.println("Enter String You Want To Check");
        String x=sc.next();
        t1.stringTocheck=x;

        System.out.println("Enter Head Number");
      int head= sc.nextInt();
      try{
      if(t1.Transition1(head,"q0")==true) System.out.println("Accept");
      else System.out.println("Reject");
      System.out.println(t1.stringTocheck);
          System.out.println();



      } catch (Exception e) {
          System.err.println(e);
      }


    }

}