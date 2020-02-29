public class HomeworkFactory extends AssessmentFactory {
    public HomeworkFactory(Common common){
        super(common);
    }
    @Override
    public Assessment createAssessment(Vector2D position) {
        Homework homework=new Homework("Homework",position,new Stationary(),
                this.common,this.common.randomInt(1,3));
        return homework;
    }
}
