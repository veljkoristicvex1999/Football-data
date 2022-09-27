package sendData;

import java.util.ArrayList;

public class TeamsDataDto {
    private int count;



    private FiltersDto filters;
    private ArrayList<TeamDataDto> teams ;
    public int getCount() {
        return count;
    }
    public TeamsDataDto(int count, FiltersDto filters, ArrayList<TeamDataDto> teams) {
        this.count = count;
        this.filters = filters;
        this.teams = teams;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FiltersDto getFilters() {
        return filters;
    }

    public void setFilters(FiltersDto filters) {
        this.filters = filters;
    }



    public ArrayList<TeamDataDto> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<TeamDataDto> teams) {
        this.teams = teams;
    }

    public TeamsDataDto() {
    }

}
