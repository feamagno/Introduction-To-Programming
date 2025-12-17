import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        Project project = createProject(in);

        setEmails(in, project);

        in.close();

        try {

            PrintWriter out = new PrintWriter("output.txt");
            sortedEmails(project, out);
            out.close();

        } catch (FileNotFoundException out) {

            System.out.println("num acho");

        }
    }

    private static Project createProject(Scanner in){

        int projectNumber = in.nextInt();
        in.nextLine();
        String emailProposer = in.nextLine();
        String description = in.nextLine();
        int maxCollaborators = in.nextInt();
        in.nextLine();

        return new Project(projectNumber, emailProposer, maxCollaborators, description);
    }

    private static void setEmails(Scanner in, Project project){

        int C = in.nextInt();
        in.nextLine();

        for (int i = 0; i < C; i++){
            String email = in.nextLine();
            project.addCollaborator(email);
        }
    }

    private static void sortedEmails(Project project, PrintWriter out) throws FileNotFoundException{

        project.sortCollaborators();

        CollaboratorsIterator it = project.iterator();

        while (it.hasNext()){
            out.println(it.next());
        }
    }
}
