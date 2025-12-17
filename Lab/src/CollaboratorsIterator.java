public class CollaboratorsIterator {

    private final String[] collaboratorEmails;
    private final int numCollaborators;
    private int next;

    public CollaboratorsIterator(String[] collaboratorEmails, int numCollaborators) {
        this.collaboratorEmails = collaboratorEmails;
        this.numCollaborators = numCollaborators;
        next = 0;
    }

    public boolean hasNext(){
        return next < numCollaborators;
    }

    public String next(){
        return collaboratorEmails[next++];
    }

}
