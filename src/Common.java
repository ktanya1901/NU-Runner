import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Common {
    public int windowWidth;
    public int windowHeight;
    public UniversityMap map;
    public List<Academician> academicians;
    public List<Student> students;
    public List<Speaker> speakers;
    public List<Assessment> assessments;
    public List<AssessmentFactory> factories;

    Random rand=new Random();

    Vector2D gradPoint=new Vector2D(840, 490);
    boolean workIsOver;

    public Common(){
        this.map=new UniversityMap("Map", new Vector2D(0.0, 0.0),new Stationary(), this, "NUMap-Faded.jpg");

        this.windowWidth=this.map.width;
        this.windowHeight=this.map.height;

        this.academicians = new ArrayList<>();
        Academician Katsu=new Academician("Katsu",randomPosition(), new Rest(20), this, "ShigeoKatsu.gif");
        academicians.add(Katsu);
        Academician Tourassis=new Academician("Tourassis",randomPosition(),new Rest(20),this, "VassiliosTourassis.gif");
        academicians.add(Tourassis);
        Academician Nivelle=new Academician("Nivelle",randomPosition(),new Rest(20),this, "HansDeNivelle.gif");
        academicians.add(Nivelle);
        Academician Temizer=new Academician("Temizer",randomPosition(),new Rest(20),this, "SelimTemizer.gif");
        academicians.add(Temizer);

        this.factories=new ArrayList<>();
        this.factories.add(new QuizFactory(this));
        this.factories.add(new LabFactory(this));
        this.factories.add(new HomeworkFactory(this));

        this.assessments=new ArrayList<>();

        this.students=new ArrayList<>();
        for(int s=1;s<11;s++){
            Student student=new Student("Student "+(s),randomPosition(),new Rest(20),this,0);
            students.add(student);
        }

        this.speakers=new ArrayList<>();
        Speaker Tokayev=new Speaker("Tokayev",new Vector2D(750,485),new Stationary(),this, "KassymJomartTokayev.gif");
        Speaker Nazarbayev=new Speaker("Nazarbayev",new Vector2D(925,485),new Stationary(),this, "NursultanNazarbayev.gif");
        speakers.add(Tokayev);
        speakers.add(Nazarbayev);

        this.workIsOver=false;
    }

    public Vector2D randomPosition(){
        int x=randomInt(0,windowWidth);
        int y=randomInt(0,windowHeight);
        return new Vector2D(x,y);
    }

    public int randomInt(int from, int to){
        int ranInt;
        ranInt=this.rand.nextInt(to-from+1) + from;
        return ranInt;
    }

    public State setAcadState(){
        int caseInt = randomInt(0,2);
        switch (caseInt){
            case (0):
                return new GotoXY(randomPosition());
            case(1):
                return new Rest(100);
            case(2):
                return new ZigZag(randomPosition(),200);
            default:
                return null;
        }
    }

    public State setStudState(){
        int caseInt = randomInt(0,3);
        switch (caseInt){
            case (0):
                return new GotoXY(randomPosition());
            case(1):
                return new Rest(50);
            case(2):
                return new ZigZag(randomPosition(),30);
            case(3):
                return new Closest(130);
            default:
                return null;
        }
    }

    public void stepAllEntities(){
        Iterator acadStep=this.academicians.listIterator();
        while (acadStep.hasNext()){
            Academician academician=(Academician) acadStep.next();
            academician.step();

            int ranInt=this.randomInt(1,15);
            if(academician.state.toString() != "Rest" && ranInt==15){
                AssessmentFactory assessmentFactory=this.factories.get(this.randomInt(0,this.factories.size()-1));

                Vector2D position=academician.position;
                int x=(int) position.x+(this.randomInt(0,1)==0 ? -1:1) * this.randomInt(20,50);
                int y=(int) position.y+(this.randomInt(0,1)==0 ? -1:1) * this.randomInt(20,50);

                if(x <= 0){ x=1; }

                if (x >= this.windowWidth) { x=this.windowWidth-1; }

                if(y <= 0){ y=1; }

                if(y>=this.windowHeight){ y=this.windowHeight-1; }

                this.assessments.add(assessmentFactory.createAssessment(new Vector2D(x,y)));
            }

            if(academician.state.isOver == true){
                academician.state=setAcadState();
            }
        }

        Iterator studentStep=this.students.listIterator();

        while (studentStep.hasNext()){
            Student student = (Student) studentStep.next();
            student.step();

            if(student.grade < 100){
                for(int i = this.assessments.size()-1; i >= 0; i--){
                    Assessment assessment=this.assessments.get(i);

                    if(student.position.distanceTo(assessment.position)<5){
                        student.grade=student.grade + assessment.points;
                        this.assessments.remove(assessment);
                    }
                }
            }

            if(student.state.isOver==true){
                if(student.grade < 100){
                    student.state=this.setStudState();
                }
                else if(student.position.distanceTo(this.gradPoint) <= 10){
                    student.position.x=this.gradPoint.x+(this.randomInt(0,1)==0 ? -1:1) * this.randomInt(1,10);
                    student.position.y=this.gradPoint.y+(this.randomInt(0,1)==0 ? -1:1) * this.randomInt(1,10);

                    student.state=new Stationary();
                }
                else{
                    Vector2D finalPos=new Vector2D(0,0);

                    finalPos.x=this.gradPoint.x+(this.randomInt(0,1)==0 ? -1:1) * this.randomInt(1,10);
                    finalPos.y=this.gradPoint.y+(this.randomInt(0,1)==0 ? -1:1) * this.randomInt(1,10);

                    student.state=new GotoXY(finalPos);

                    if(student.state.isOver){
                        student.state=new Stationary();
                    }
                }
            }
        }

        Iterator studIter=this.students.listIterator();
        boolean haveFinished;
        haveFinished = true;

        while(studIter.hasNext()){
            Student student= (Student) studIter.next();
            if(student.grade < 100){
                haveFinished = false;
                break;
            }
        }

        if(haveFinished==true){
            this.workIsOver=true;
            for(int i=0; i <= this.academicians.size()-1; i++){
                Academician academician=this.academicians.get(i);
                academician.position.set((new Vector2D((double)685 + i*100,380)));
                academician.state=new Stationary();
            }
        }
    }

    public void drawAllEntities(Graphics2D g2d){
        this.map.draw(g2d);

        if(this.workIsOver==false){
            Iterator assessIter=this.assessments.listIterator();
            while(assessIter.hasNext()){
                Assessment assessment=(Assessment) assessIter.next();
                assessment.draw(g2d);
            }
        }
        else{
            this.assessments.clear();
        }

        Iterator acadIter=this.academicians.listIterator();
        while(acadIter.hasNext()){
            Academician academician=(Academician) acadIter.next();
            academician.draw(g2d);
        }

        Iterator studentIter = this.students.listIterator();
            while(studentIter.hasNext()){
                Student student= (Student) studentIter.next();
                student.draw(g2d);
            }

        if(this.workIsOver==true){
            Iterator speakerIter=this.speakers.listIterator();
            while (speakerIter.hasNext()){
                Speaker speaker=(Speaker) speakerIter.next();
                speaker.draw(g2d);
            }
        }
    }

}
