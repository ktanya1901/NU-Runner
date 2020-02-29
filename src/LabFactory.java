public class LabFactory extends AssessmentFactory {
    public LabFactory(Common common){
        super(common);
    }
    @Override
    public Assessment createAssessment(Vector2D position) {
        Lab lab=new Lab("Lab",position,new Stationary(),
                this.common,this.common.randomInt(1,3));
        this.common.assessments.add(lab);
        return lab;
    }
}