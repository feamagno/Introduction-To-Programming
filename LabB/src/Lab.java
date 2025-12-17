public class Lab {

    private final int numOfProjects;
    private final Project[] projects;
    private int size;

    public Lab(int numOfProjects) {
        this.numOfProjects = numOfProjects;
        projects = new Project[numOfProjects];
        size = 0;
    }

    public void addProject(int numProject, String proposerEmail, int maxCollaborators, String description){
        Project project = new Project(numProject, proposerEmail, maxCollaborators, description);
        projects[size++] = project;
    }

    public void sortProjects(){
        for (int i = 0; i < size-1; i++){
            int idxOfMin = i;
            for (int j = i+1; j < size; j++){

                if (projects[j].getNumProject()< projects[idxOfMin].getNumProject()){
                    idxOfMin = j;
                }
            }
            Project tmp = projects[i];
            projects[i] = projects[idxOfMin];
            projects[idxOfMin] = tmp;
        }
    }

    public int getNumOfProjects() {
        return numOfProjects;
    }

    public ProjectsIterator projectsIterator(){
        return new ProjectsIterator(projects, size);
    }

}
