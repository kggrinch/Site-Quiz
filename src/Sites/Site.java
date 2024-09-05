package Sites;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        Site.java
 * Description  A class representing the Site in Vacation Sites Quiz. 
 *              Implements the Comparable interface.
 * Date         10/06/2023
 * @author	<i>Kirill Grichanichenko</i>
 * @see         java.lang.Comparable
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Site implements Comparable
{
    private String name;
    private String country;
    private float population;
    private String capital;
    private float area;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Site()-Default Constructor
    * Description  Creates an instance of Site
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>           
    *****************************************************************************/
    public Site()
    {
        this("", "", 0, "",0);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Site()-Default Constructor
    * Description  Creates an instance of Site
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>           
    * @param       name       String            
    * @param       country    String        
    * @param       population float           
    * @param       capital    String     
    * @param       area       float         
    *****************************************************************************/
    public Site(String name, String country, float population, String capital, float area)
    {
        this.name = name;
        this.country = country;
        this.population = population;
        this.capital = capital;
        this.area = area;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  Site()-Default Constructor
    * Description  Creates an instance of Site
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i> 
    * @param        another 
    *****************************************************************************/
    public Site(Site another) 
    {
        this.name = another.name;
        this.country = another.country;
        this.population = another.population;
        this.capital = another.capital;
        this.area = another.area;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getName()
    * Description  Returns the name.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      name
     *****************************************************************************/
    public String getName() {
        return name;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setName()
    * Description  Sets the name.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param name String
     *****************************************************************************/
    public void setName(String name) {
        this.name = name;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getCountry()
    * Description  Returns the country.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      country
     *****************************************************************************/
    public String getCountry() {
        return country;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setCountry()
    * Description  Sets the country.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       country String
     *****************************************************************************/
    public void setCountry(String country) {
        this.country = country;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getPopulation()
    * Description  Returns the population.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      population
     *****************************************************************************/
    public float getPopulation() {
        return population;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setPopulation()
    * Description  Sets the population.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       population Float
     *****************************************************************************/
    public void setPopulation(float population) {
        this.population = population;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getCapital()
    * Description  Returns the capital.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      capital
     *****************************************************************************/
    public String getCapital() {
        return capital;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setCapital()
    * Description  Sets the capital.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param      capital String
     *****************************************************************************/
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getArea()
    * Description  Returns the area.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      area
     *****************************************************************************/
    public float getArea() {
        return area;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       setArea()
    * Description  Sets the area.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       area Float
     *****************************************************************************/
    public void setArea(float area) {
        this.area = area;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       toString()
     * Description  method that sets Site to string
     * @return      String
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString() {
        return "Site{" + "name=" + name + ", country=" + country + ", population=" + population + ", capital=" + capital + ", area=" + area + '}';
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       hashCode()
     * Description  Overridden method to set hash code
     * @return      hashCode int
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       equals()
     * Description  Overridden method to check equality between Sites.
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
        final Site other = (Site) obj;
        if (Float.floatToIntBits(this.population) != Float.floatToIntBits(other.population)) {
            return false;
        }
        if (Float.floatToIntBits(this.area) != Float.floatToIntBits(other.area)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return true;
        }
        if (!Objects.equals(this.country, other.country)) {
            return true;
        }
        return Objects.equals(this.capital, other.capital);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  compareTo()
     * Description  Required method to compare two Sites by names
     * @param       obj Object
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int compareTo(Object obj)
    {
        return this.getName().compareToIgnoreCase(((Site)obj).getName());
    }
   
}
