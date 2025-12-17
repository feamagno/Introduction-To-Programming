public class Project {

    private final int numProject;
    private final String proposerEmail;
    private final int maxCollaborators;
    private String[] collaboratorEmails;
    private int numCollaborators;
    private final String description;

    public Project(int numProject, String proposerEmail, int maxCollaborators, String description) {

        this.numProject = numProject;
        this.proposerEmail = proposerEmail;
        this.maxCollaborators = maxCollaborators;
        this.description = description;
        collaboratorEmails = new String[maxCollaborators];
        numCollaborators = 0;

    }


    private boolean isFull(){
        return numCollaborators == maxCollaborators;
    }

    public void addCollaborator(String email){
        if (!isFull()) {
            collaboratorEmails[numCollaborators] = email;
            numCollaborators++;
        }
    }

    public String getProposerEmail() {
        return proposerEmail;
    }

    public int getNumProject(){
        return numProject;
    }

    public void sortCollaborators(){

        for (int i = 0; i < numCollaborators-1; i++){

            int idxOfMin = i;

            for (int j = i+1; j < numCollaborators; j++){

                if (collaboratorEmails[j].compareTo(collaboratorEmails[idxOfMin]) < 0){
                    idxOfMin = j;
                }
            }

            String tmp = collaboratorEmails[i];
            collaboratorEmails[i] = collaboratorEmails[idxOfMin];
            collaboratorEmails[idxOfMin] = tmp;
        }
    }

    public CollaboratorsIterator iterator(){
        return new CollaboratorsIterator(collaboratorEmails, numCollaborators);
    }

}
