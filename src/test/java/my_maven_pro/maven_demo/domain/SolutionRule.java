package my_maven_pro.maven_demo.domain;

import java.util.List;

public class SolutionRule {

    private List<String> actionType;
    private String solutionCode;
    private Integer ability;

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public String getSolutionCode() {
        return solutionCode;
    }

    public void setSolutionCode(String solutionCode) {
        this.solutionCode = solutionCode;
    }

    public List<String> getActionType() {
        return actionType;
    }

    public void setActionType(List<String> actionType) {
        this.actionType = actionType;
    }
}
