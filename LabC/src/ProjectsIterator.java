public class ProjectsIterator {

    private final Project[] projects;
    private final int size;
    private int nextInt;

    public ProjectsIterator(Project[] projects, int size) {
        this.projects = projects;
        this.size = size;
        nextInt = 0;
    }

    public boolean hasNext(){
        return nextInt < size;
    }

    public void next(){
        nextInt++;
    }

    public String getNextEmail(){
        return projects[nextInt].getProposerEmail();
    }

    public int getNumProjects(){
        return projects[nextInt].getNumProject();
    }

}
