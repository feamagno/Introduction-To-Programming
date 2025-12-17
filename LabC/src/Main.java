import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        Lab lab = createLab(in);

        addProject(in, lab);

        addInsertions(in, lab);

        in.close();

        try {

            PrintWriter out = new PrintWriter("output.txt");
            sortedEmailsProposer(lab, out);
            out.close();

        } catch (FileNotFoundException out) {

            System.out.println("num acho");

        }
    }

    private static void addInsertions(Scanner in, Lab lab){

    }

    private static void addProject(Scanner in, Lab lab){

        ProjectsIterator it = lab.projectsIterator();

        for (int i = 0; i < lab.getNumOfProjects(); i++){

            int projectNumber = in.nextInt();
            in.nextLine();
            String emailProposer = in.nextLine();
            String description = in.nextLine();
            int maxCollaborators = in.nextInt();
            in.nextLine();

            lab.addProject(projectNumber, emailProposer, maxCollaborators, description);
            it.next();
        }

        lab.sortProjects();

    }

    private static Lab createLab(Scanner in){

        int numOfProjects = in.nextInt();
        in.nextLine();

        return new Lab(numOfProjects);
    }

    private static void sortedEmailsProposer(Lab lab, PrintWriter out) throws FileNotFoundException{

        ProjectsIterator it = lab.projectsIterator();

        while (it.hasNext()){
            out.println(it.getNumProjects());
            out.println(it.getNextEmail());
            it.next();
        }
    }

//    private static void setEmails(Scanner in, Project project){
//
//        int C = in.nextInt();
//        in.nextLine();
//
//        for (int i = 0; i < C; i++){
//            String email = in.nextLine();
//            project.addCollaborator(email);
//        }
//    }
//
//    private static void sortedEmails(Project project, PrintWriter out) throws FileNotFoundException{
//
//        project.sortCollaborators();
//        CollaboratorsIterator it = project.iterator();
//
//        while (it.hasNext()){
//            out.println(it.next());
//        }
//    }
//

//    private static Project createProject(Scanner in){
//
//        int projectNumber = in.nextInt();
//        in.nextLine();
//        String emailProposer = in.nextLine();
//        String description = in.nextLine();
//        int maxCollaborators = in.nextInt();
//        in.nextLine();
//
//        return new Project(projectNumber, emailProposer, maxCollaborators, description);
//    }
}
