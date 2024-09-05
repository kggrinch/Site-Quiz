package Sites;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        Person.java
 * Description  A class representing a Person.
 * Date         10/06/2023     
 * @author      <i>Kirill Grichanichenko</i>
 * @see     	javax.swing.JFrame
 * @see     	java.awt.Toolkit            
 *****************************************************************************/
public abstract class Person 
{
    // Name of Person
    protected String name;

    // Age of Person
    protected int age;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Person()-Default Constructor
    * Description  Creates an instance of Person
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>           
    *****************************************************************************/
    public Person() 
    {
        this("", 0);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Person() - Constructor
    * Description  Creates an instance of Person
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       name String
    * @param       age int
    *****************************************************************************/
    public Person(String name, int age) 
    {
        this.name = name;
        this.age = age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Person() - Constructor
    * Description  Creates an instance of Person
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       another Person
    *****************************************************************************/
    public Person(Person another) 
    {
        this.name = another.name;
        this.age = another.age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getName()
    * Description  Returns the Persons name
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      name
    *****************************************************************************/
    public String getName() {
        return name;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setName()
    * Description  Sets the Persons name
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       name String
    *****************************************************************************/
    public void setName(String name) {
        this.name = name;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getAge()
    * Description  Returns the Persons age
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      age
    *****************************************************************************/
    public int getAge() {
        return age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setAge()
    * Description  Sets the Persons name
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       age
    *****************************************************************************/
    public void setAge(int age) {
        this.age = age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       toString()
     * Description  method that sets Person to string
     * @return      hashCode int
     * @author      <i>Kirill Grichanichenko</i>       
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString() {
        return name + ", "+ age;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       equals()
     * Description  Overridden method to check equality between Persons.
     * @return      true or false Boolean
     * @author      <i>Kirill Grichanichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        return true;
    } 
}