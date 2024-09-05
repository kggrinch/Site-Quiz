package Sites;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        Player.java
 * Description  A class representing a Player extends Person and implements Comparable.
 * Date         10/06/2023     
 * @author      <i>Kirill Grichanichenko</i>    
 *****************************************************************************/
public class Player extends Person implements Comparable
{
    //Name and age inherited from Student class.
    private int correct; // Number of correct question
    private int totalQuestions; //Number of total questions.
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Player()-Default Constructor
    * Description  Creates an instance of Player
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>           
    *****************************************************************************/
    public Player()
    {
        this(0, 0);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Player()-Constructor
    * Description  Creates an instance of Player
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i> 
    * @param       correct int
    * @param       total int
    *****************************************************************************/
    public Player(int correct, int total)
    {
        this.correct = correct;
        this.totalQuestions = total;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Player()-Constructor
    * Description  Creates an instance of Player
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i> 
    * @param       name String
    * @param       age int
    * @param       correct int
    * @param       total int
    *****************************************************************************/
    public Player(String name, int age, int correct, int total)
    {
        super(name, age);
        this.correct = correct;
        this.totalQuestions = total;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Player()-Constructor
    * Description  Creates an instance of Player
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i> 
    * @param       another Player
    *****************************************************************************/
    public Player(Player another) 
    {
        this.name = another.name;
        this.age = another.age;
        this.correct = another.correct;
        this.totalQuestions = another.totalQuestions;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getCorrect()
    * Description  Returns the correct int.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      correct
     *****************************************************************************/
    public int getCorrect() {
        return correct;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setCorrect()
    * Description  Sets the correct int.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       right int
     *****************************************************************************/
    public void setCorrect(int right) 
    {
        if(right >= 0)
            correct = right;
        else
            correct = 0;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getTotalQuestions()
    * Description  Returns the total Questions.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      totalQuestions
     *****************************************************************************/
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setTotalQuestions
    * Description  Sets the correct totalQuestions.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       totalQuestions int
     *****************************************************************************/
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       toString()
     * Description  method that sets Player to string
     * @return      String
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString()
    {
        return super.toString() + "," + correct + "," + totalQuestions;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       hashCode()
     * Description  Overridden method to set hash code
     * @return      hashCode int
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       equals()
     * Description  Overridden method to check equality between Players.
     * @return      true or false Boolean
     * @author      <i>Kirill Grichanichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Player other = (Player) obj;
        if (!(this.name.equals(other.name))) 
        {
            return false;
        }
        if(this.age != other.age)
        {
            return false;
        }
        return true;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       calculatePercent()
    * Description  Calculates the percentage of the players score.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      calculatedPercent
     *****************************************************************************/
    public double calculatePercent()
    {
        if(totalQuestions == 0)
            return 0;
        else
            return Math.round((1.0 * correct / totalQuestions) * 100);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  compareTo()
     * Description  Required method to compare two players by names and if names
     *              are equal then by age.
     * @param       obj Object
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int compareTo(Object obj)
    {
        try
        {
            Player otherStudent = (Player) obj;
            if((this.getName()).equalsIgnoreCase(otherStudent.getName()))
                return this.age - otherStudent.age;
            else
                return (this.getName()).compareToIgnoreCase(otherStudent.getName());
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            return 0;
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  compareTo()
     * Description  Required method to compare two players by names and if names
     *              are equal then by age.
     * @param       obj Object
     * @param       partial Boolean
     * @return      Int
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int compareTo(Object obj, boolean partial) 
    {
        Player otherStudent = (Player) obj;
        if((this.getName().toLowerCase()).equals(otherStudent.getName().toLowerCase()))
                return this.age - ((Player) obj).age;
        else 
            return (this.getName()).compareToIgnoreCase(otherStudent.getName());
    }
}
