package Module_8;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    static int getId(){
        File forder = new File("NOTES");
        File[] files = forder.listFiles();
        int ID;
        String num = "";

        if(files != null){
            for(File note : files){
               num = note.getName().substring(5,note.getName().length() - 4);
            }
        }
        ID = (files.length == 0) ? 0 : Integer.parseInt(num);
        return ID + 1;
    }

    static void CreateNote(Scanner sc){
        System.out.print("Enter note content: ");
        String content = sc.nextLine();
        int id = getId();
        try {
            File file = new File("NOTES/note-"+id+".txt");
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            System.out.println("Note Create successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void ViewNotes(){
        File folder = new File("NOTES");
        File[] files = folder.listFiles();

        if(files.length == 0){
            System.out.println("Files not exits");
        }
        else {
            for (File n : files){
                String note = n.getName();
                try {
                    Scanner fileReader = new Scanner(n);
                    System.out.println("\n---" + note + "---");
                    while (fileReader.hasNextLine()){
                        System.out.println(fileReader.nextLine());
                    }
                    fileReader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    static void UpdateNote(Scanner sc){
        ViewNotes();
        System.out.print("\nEnter note ID to update: ");
        String id = sc.nextLine();

        System.out.println("1. Replace");
        System.out.println("2. Append");
        System.out.print("Choose option: ");
        int chose = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new content: ");
        String newContent = sc.nextLine();

        try {
            FileWriter writer;
            if(chose == 1){
                writer = new FileWriter("NOTES/note-"+id+".txt");
                writer.write(newContent);
            }
            else {
                writer = new FileWriter("NOTES/note-"+id+".txt",true);
                writer.write(" " + newContent);
            }
            writer.close();
            System.out.println("Note updated successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void DeleteNote(Scanner sc){
        ViewNotes();

        System.out.print("\nEnter note ID to delete: ");
        String id = sc.nextLine();

        File file = new File("NOTES/note-"+ id +".txt");
        if(file != null){
            file.delete();
            System.out.println("Note deleted successfully");
        }
        else {
            System.out.println("Note not found!");
        }
    }

    static void ResetNote(){
        File folder = new File("NOTES");
        File[] files = folder.listFiles();
        if(files != null){
            for(File note : files){
                note.delete();
            }
            System.out.println("All notes deleted.");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("\n---------NOTE TAKING APPLICATION-----------");
            System.out.println("1. Create New Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Update Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Reset");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    CreateNote(sc); break;
                case 2:
                    ViewNotes(); break;
                case 3:
                    UpdateNote(sc); break;
                case 4:
                    DeleteNote(sc); break;
                case 5:
                    ResetNote(); break;
                case 0:
                    System.out.println("Thank you...GoodBye");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
