package Sites;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        SiteGUI.java
 * Description  A class representing the GUI used screen used in Vacation Sites Quiz
 *              Application.
 * Date         10/06/2023     
 * @author      <i>Kirill Grichanichenko</i>
 * @see     	javax.swing.JFrame
 * @see     	java.awt.Toolkit            
 *****************************************************************************/
public class SiteGUI extends javax.swing.JFrame {
    
    // Class instance variables
    static int maxQuestions = 10; // Maximum number of questions: static for validation
    private int numberOfQuestions = 0;
    
    private final BinarySearchTree playersTree = new BinarySearchTree();
    private final DefaultListModel<String> playersNames = new DefaultListModel<String>();
    private final List<Site> sitesNames = new LinkedList<Site>();
    private final Map<String, String> sitesHashMap = new HashMap<String, String>();
    
    private String playersFileName = "src/Data/players.txt";
    private String sitesFileName = "src/Data/sites.txt";
    
    private final ArrayList<Integer> numbersUsedArrayList = new ArrayList<Integer>();
    private final ArrayList<Boolean> sitesUsedArrayList = new ArrayList<Boolean>();
    
    
    private int numberOfPlayers = 0;
    private int numberOfSites = 0;
    private int numberOfCountries = maxQuestions;
    private int countCorrect = 0;
    private String playerName = "";
    private final String siteName = "";
    private int currentIndex;
    
    private int count = 0;
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  SitesGUI()-Default Constructor
    * Description  Create an instance of GUI form, set the default JButton
    *              to be submitJButton, set icon image, center form, read sites
    *              and players from external files.
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023              
    *****************************************************************************/
    public SiteGUI() {
        initComponents();
        playerJList.setModel(playersNames); // To add names in the playerJList
        // Set the display JBUtton as the default key as ENTER:
        this.getRootPane().setDefaultButton(submitJButton);
        // Set a icon piture on the Title:
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/Great Barrier Reef.jpeg"));
        readPlayers(playersFileName);
        readSites(sitesFileName);
        playerJList.setSelectedIndex(0);
        
        Collections.sort(sitesNames); // Sort the array.
        fillComboBox(sitesNames); // Populate comboBox with sites countries
        
        submitJButton.setEnabled(false);
        nextJButton.setEnabled(false);
        playJButton.setEnabled(false);
        sitesJComboBox.setEnabled(true);
        questionsJTextField.requestFocus();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       fillComboBox 
    * Description  fill sites combo box with the given sites.
    * @author      <i>Kirill Grichanichenko</i>
    * @param       sites List
    * Date         10/06/2023          
    *****************************************************************************/
    private void fillComboBox(List<Site> sites)
    {
        sitesJComboBox.removeAllItems();
        for (Site site : sites)
        {
            sitesJComboBox.addItem(site.getCountry());
        }
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       readPlayers
    * Description  Read the players from the external file.
    * @author      <i>Kirill Grichanichenko</i>
    * @param       fileName String
    * Date         10/06/2023     
    * @see         java.io.File
    * @see         java.util.Scanner
    * @see         java.util.StringTokenizer
    * @see         java.io.FileNotFoundException
    * @see         javax.swing.JFileChooser
    *****************************************************************************/
    public void readPlayers(String fileName)
    {
        
        // Clear TreeSet and JList
        playersTree.removeAll();
        playersNames.removeAllElements();
        
        try
        {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String line = ""; String playersName = ""; // Read first line
            int age = 0; int correct = 0; int questions = 0;
            
            Player player = new Player();
            while(fileScanner.hasNextLine())
            {
                line = fileScanner.nextLine();
                StringTokenizer stringtokenizer = new StringTokenizer(line, ",");
                while(stringtokenizer.hasMoreElements())
                {
                    playersName = stringtokenizer.nextToken();
                    playersNames.addElement(playersName);
                    age = Integer.parseInt(stringtokenizer.nextElement().toString());
                    correct = Integer.parseInt(stringtokenizer.nextElement().toString());
                    questions = Integer.parseInt(stringtokenizer.nextElement().toString());
                    player = new Player(playersName, age, correct, questions);
                }
                playersTree.insertNode(player);
            }
            fileScanner.close();
            playerJList.setCellRenderer(new DefaultListCellRenderer());
            playerJList.setVisible(true);
            playerJList.setSelectedIndex(0);
        }
        catch(FileNotFoundException exp)
        {
            JOptionPane.showMessageDialog(null, fileName + " does not exist", "File Input Error", JOptionPane.WARNING_MESSAGE);
            JFileChooser chooser = new JFileChooser("src/Data");
            MyFilter filter = new MyFilter(".txt");
            
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION)
            {
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/Data/" + chosenFile.getName();
                readPlayers(fileName);
            }
        }
        catch(Exception exp)
        {
            JOptionPane.showMessageDialog(null, "Unable to read file", "File Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       savePlayers
    * Description  Save players to the external file and playerTree.
    * @author      <i>Kirill Grichanichenko</i>
    * @param       playersFile String
    * Date         10/06/2023     
    * @see         java.io.File
    * @see         java.io.FileWriter
    * @see         java.io.PrintWriter
    * @see         java.io.IOException
    * @see         javax.swing.JOptionPane
    *****************************************************************************/
    private void savePlayers(String playersFile)
    {
        try {
        FileWriter filePointer = new FileWriter(playersFile,false);
        PrintWriter writeFile = new PrintWriter(filePointer,false);
        BinarySearchTreeNode root = playersTree.getRoot(); // get

        playersTree.setBuffer(new StringBuilder()); // clears the buffer
        playersTree.buildBuffer(root); // build file content
        
        String buffer = playersTree.getBuffer().toString().replace(", ", ",");
        writeFile.print(buffer); // write to file
        writeFile.close();
    } catch (IOException exp) {
        JOptionPane.showMessageDialog(this, "Unable to write to file",
                "Write File Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       readSites
    * Description  Reads the sites from the external file.
    * @author      <i>Kirill Grichanichenko</i>
    * @param       fileName String
    * Date         10/06/2023     
    * @see         java.io.File
    * @see         java.util.Scanner
    * @see         java.util.StringTokenizer
    * @see         java.io.FileNotFoundException
    * @see         javax.swing.JFileChooser
    *****************************************************************************/
    public void readSites(String fileName)
    {
        int counter = 0;
        sitesNames.clear();
        numbersUsedArrayList.clear();
        sitesHashMap.clear();
        try
        {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String line = "", name = "", country = "", capital = "";
            float population = 0, area = 0;
            Site site = new Site();
            
            // Read from file and count how many there are to size the arrays
            while(fileScanner.hasNextLine())
            {
                line = fileScanner.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while(stringTokenizer.hasMoreElements())
                {
                    name = stringTokenizer.nextElement().toString();
                    country = stringTokenizer.nextElement().toString();
                    sitesHashMap.put(name, country);
                    population = Float.parseFloat(stringTokenizer.nextElement().toString());
                    capital = stringTokenizer.nextElement().toString();
                    area = Float.parseFloat(stringTokenizer.nextElement().toString());
                    site = new Site(name, country, population, capital, area);
                }
                sitesNames.add(site);
                // Initialize ArrayLists
                numbersUsedArrayList.add(counter); // Used in generate unique random number.
                sitesUsedArrayList.add(false); // Used to generate unique random number.
                counter++;
            }
            fileScanner.close();
            fillComboBox(sitesNames);
            maxQuestions = sitesNames.size();
            questionsJTextField.setToolTipText("Enter 1-" + maxQuestions + " and press Enter to start quiz.");
        }
        catch(FileNotFoundException exp)
        {
            JOptionPane.showMessageDialog(null, fileName + " does not exist", "File Input Error", JOptionPane.WARNING_MESSAGE);
            // Bring up JFileChooser to select file in current directory.
            JFileChooser chooser = new JFileChooser("src/Data");
            MyFilter filter = new MyFilter(".txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if(choice == JFileChooser.APPROVE_OPTION)
            {
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/Data/" + chosenFile.getName();
                readSites(fileName);
            }
        }
        catch(Exception exp)
        {
            JOptionPane.showMessageDialog(null, "Unable to read file", "File Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       searchSite
    * Description  finds a site from the List.
    * @author      <i>Kirill Grichanichenko</i>
    * @param       siteName String
    * Date         10/06/2023     
    * @return      i or -1
    *****************************************************************************/
    public int searchSite(String siteName)
    {
        for (int i = 0; i < sitesNames.size(); i++)
        {
            if (sitesNames.get(i).getCountry().equalsIgnoreCase(siteName))
                return i;
        }
        return -1;
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       searchPlayer
    * Description  Finds a player from the BinarySearchTree.
    * @author      <i>Kirill Grichanichenko</i>
    * @param       name String
    * Date         10/06/2023     
    * @return      Player
    *****************************************************************************/
    public BinarySearchTreeNode searchPlayer(String name) {
        return playersTree.nodeWith(name, playersTree.getRoot());
    }
    
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       displaySite
    * Description  Display the sites to siteJLabel.
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023     
    *****************************************************************************/
    private void displaySite()
    {
        currentIndex = getUniqueRandomNumber();
        // Create the path for file
        String site = sitesNames.get(currentIndex).getName();
        String sitePath = "src/Images/" + site + ".jpeg";
        siteJLabel.setIcon(new ImageIcon(sitePath));
        siteJLabel.setToolTipText(siteName);
        siteNameJLabel.setText(site);
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getUniqueRandomNumber
    * Description  returns a unique random number.
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023         
    *****************************************************************************/
    private int getUniqueRandomNumber()
    {
        Random generator = new Random();
        int randomNumber = 0;
        do
        {
            randomNumber = generator.nextInt(sitesNames.size());
        }
        while(sitesUsedArrayList.get(randomNumber));
        sitesUsedArrayList.set(randomNumber, true);
        return randomNumber;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       findIndex
    * Description  Finds the index of a given player in the playerJList.
    * @param       name String 
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023       
    *****************************************************************************/
    private int findIndex(String name)
    {
        int index = -1;
        for (int i = 0; i < playerJList.getModel().getSize(); i++)
        {
            if(name.equals(playerJList.getModel().getElementAt(i).toString())) {
                return i;
            }
        }
        return index;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getSitesNames
    * Description  Returns the siteNames.
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023        
    * @return      sitesNames.
    *****************************************************************************/
    public List<Site> getSitesNames() {
        return sitesNames;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleJPanel = new javax.swing.JPanel();
        TitleJLabel = new javax.swing.JLabel();
        siteNameJLabel = new javax.swing.JLabel();
        questionsJTextField = new javax.swing.JTextField();
        questionsJLabel = new javax.swing.JLabel();
        SelectJPanel = new javax.swing.JPanel();
        selectJLabel = new javax.swing.JLabel();
        sitesJComboBox = new javax.swing.JComboBox<>();
        siteJLabel = new javax.swing.JLabel();
        resultJTextField = new javax.swing.JTextField();
        playerJScrollPane = new javax.swing.JScrollPane();
        playerJList = new javax.swing.JList<>();
        controlJPanel = new javax.swing.JPanel();
        submitJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        playJButton = new javax.swing.JButton();
        sitesJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        newJMenuItem = new javax.swing.JMenuItem();
        clearJMenuItem = new javax.swing.JMenuItem();
        printFormJMenuItem = new javax.swing.JMenuItem();
        printPlayerJMenuItem = new javax.swing.JMenuItem();
        fileJSeparator = new javax.swing.JPopupMenu.Separator();
        ExitJMenuItem = new javax.swing.JMenuItem();
        playerJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        playerDetailsJMenuItem = new javax.swing.JMenuItem();
        siteDetailsJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vacation Sites Quiz");
        setResizable(false);

        TitleJLabel.setFont(new java.awt.Font("Times New Roman", 3, 34)); // NOI18N
        TitleJLabel.setForeground(new java.awt.Color(51, 102, 255));
        TitleJLabel.setText("Vacation Sites Quiz");

        siteNameJLabel.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        siteNameJLabel.setForeground(new java.awt.Color(51, 0, 204));
        siteNameJLabel.setText("Annapurna");
        siteNameJLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Name of Site"));

        questionsJTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        questionsJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionsJTextFieldActionPerformed(evt);
            }
        });

        questionsJLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 22)); // NOI18N
        questionsJLabel.setText("Questions:");

        SelectJPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 3));

        selectJLabel.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        selectJLabel.setForeground(new java.awt.Color(0, 153, 204));
        selectJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectJLabel.setText("Select Country:");
        selectJLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SelectJPanel.add(selectJLabel);

        sitesJComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sitesJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        SelectJPanel.add(sitesJComboBox);

        siteJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annapurna.jpeg"))); // NOI18N

        resultJTextField.setEditable(false);
        resultJTextField.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        resultJTextField.setForeground(new java.awt.Color(51, 255, 0));
        resultJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        playerJList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Players", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 18), new java.awt.Color(51, 102, 255))); // NOI18N
        playerJList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playerJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        playerJScrollPane.setViewportView(playerJList);

        controlJPanel.setLayout(new java.awt.GridLayout(1, 3, 3, 0));

        submitJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitJButton.setMnemonic('S');
        submitJButton.setText("Submit");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(submitJButton);

        nextJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nextJButton.setMnemonic('N');
        nextJButton.setText("Next Site");
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(nextJButton);

        playJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playJButton.setMnemonic('P');
        playJButton.setText("Play Again");
        playJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(playJButton);

        javax.swing.GroupLayout TitleJPanelLayout = new javax.swing.GroupLayout(TitleJPanel);
        TitleJPanel.setLayout(TitleJPanelLayout);
        TitleJPanelLayout.setHorizontalGroup(
            TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleJPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitleJPanelLayout.createSequentialGroup()
                        .addComponent(TitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(questionsJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(questionsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(TitleJPanelLayout.createSequentialGroup()
                        .addGroup(TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(controlJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siteJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SelectJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(siteNameJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultJTextField)
                    .addComponent(playerJScrollPane))
                .addGap(22, 22, 22))
        );
        TitleJPanelLayout.setVerticalGroup(
            TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleJPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(siteNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(questionsJLabel)
                        .addComponent(questionsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(TitleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitleJPanelLayout.createSequentialGroup()
                        .addComponent(siteJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(TitleJPanelLayout.createSequentialGroup()
                        .addComponent(SelectJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playerJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        siteNameJLabel.getAccessibleContext().setAccessibleName("Name of SIte");

        fileJMenu.setText("File");
        fileJMenu.setToolTipText("Opens File");

        newJMenuItem.setMnemonic('N');
        newJMenuItem.setText("New");
        newJMenuItem.setToolTipText("Creates new Sites");
        newJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(newJMenuItem);

        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clears Sites");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);

        printFormJMenuItem.setMnemonic('P');
        printFormJMenuItem.setText("Print Form");
        printFormJMenuItem.setToolTipText("Prints Sites");
        printFormJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printFormJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printFormJMenuItem);

        printPlayerJMenuItem.setMnemonic('r');
        printPlayerJMenuItem.setText("Print Player");
        printPlayerJMenuItem.setToolTipText("Prints Players");
        printPlayerJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPlayerJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printPlayerJMenuItem);
        fileJMenu.add(fileJSeparator);

        ExitJMenuItem.setMnemonic('x');
        ExitJMenuItem.setText("Exit");
        ExitJMenuItem.setToolTipText("Exits Sites");
        ExitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(ExitJMenuItem);

        sitesJMenuBar.add(fileJMenu);

        playerJMenu.setMnemonic('D');
        playerJMenu.setText("Players DataBase");
        playerJMenu.setToolTipText("Open Players DataBase");

        addJMenuItem.setMnemonic('A');
        addJMenuItem.setText("Add");
        addJMenuItem.setToolTipText("Add Player");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        playerJMenu.add(addJMenuItem);

        editJMenuItem.setMnemonic('E');
        editJMenuItem.setText("Edit");
        editJMenuItem.setToolTipText("Edit Player");
        editJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuItemActionPerformed(evt);
            }
        });
        playerJMenu.add(editJMenuItem);

        deleteJMenuItem.setMnemonic('D');
        deleteJMenuItem.setText("Delete");
        deleteJMenuItem.setToolTipText("Delete Player");
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        playerJMenu.add(deleteJMenuItem);

        searchJMenuItem.setMnemonic('S');
        searchJMenuItem.setText("Search");
        searchJMenuItem.setToolTipText("Search Player");
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuItemActionPerformed(evt);
            }
        });
        playerJMenu.add(searchJMenuItem);

        playerDetailsJMenuItem.setMnemonic('y');
        playerDetailsJMenuItem.setText("Player Details");
        playerDetailsJMenuItem.setToolTipText("Access Player Details");
        playerDetailsJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerDetailsJMenuItemActionPerformed(evt);
            }
        });
        playerJMenu.add(playerDetailsJMenuItem);

        siteDetailsJMenuItem.setMnemonic('t');
        siteDetailsJMenuItem.setText("Site Details");
        siteDetailsJMenuItem.setToolTipText("Access Site Details");
        siteDetailsJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siteDetailsJMenuItemActionPerformed(evt);
            }
        });
        playerJMenu.add(siteDetailsJMenuItem);

        sitesJMenuBar.add(playerJMenu);

        helpJMenu.setMnemonic('H');
        helpJMenu.setText("Help");
        helpJMenu.setToolTipText("Open Help");

        aboutJMenuItem.setMnemonic('b');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("Open About");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        sitesJMenuBar.add(helpJMenu);

        setJMenuBar(sitesJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(TitleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TitleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       newJMenuItemActionPerformed()
     * Description  Event handler to create a new game.
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void newJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newJMenuItemActionPerformed
         JFileChooser fileJFileChooser = new JFileChooser("src/Data");
        // Limit to txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt Files", "txt");
        fileJFileChooser.setFileFilter(filter);
        int returnVal = fileJFileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            
            File file = fileJFileChooser.getSelectedFile();
            String fileName = file.getName();
            if(fileName.contains("sites"))
            {
                sitesFileName = "src/Data/" + fileName;
                sitesUsedArrayList.clear();
                numbersUsedArrayList.clear();
                readSites(sitesFileName);
                clearJMenuItemActionPerformed(evt);
            }
            else if(fileName.contains("players"))
            {
                playersFileName = "src/Data/" + fileName;
                readPlayers(playersFileName);
                clearJMenuItemActionPerformed(evt);
            }
            else{
                System.out.println("File access cancelled by user");
            }
          }    
    }//GEN-LAST:event_newJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       clearJMenuItemActionPerformed()
     * Description  Event handler to clear the form and start anew. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023     
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJMenuItemActionPerformed
        playJButtonActionPerformed(evt);
    }//GEN-LAST:event_clearJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       printFormJMenuItemActionPerformed()
     * Description  Event handler to print the GUI form. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printFormJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printFormJMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printFormJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       printPlayerJMenuItemActionPerformed()
     * Description  Event handler to print the player GUI form. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printPlayerJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPlayerJMenuItemActionPerformed
        PrintUtilities.printComponent(this.playerDetailsJMenuItem);
    }//GEN-LAST:event_printPlayerJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       playJMenuItemActionPerformed()
     * Description  Event handler to start the game all over again. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023     
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void playJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playJButtonActionPerformed
        count = 0;;
        resultJTextField.setText("");
        numberOfSites = sitesNames.size();
        String playerName = playerJList.getSelectedValue();
        Player selectedPlayer = searchPlayer(playerName).data; // get player
        playersTree.remove(selectedPlayer); // Remove old player
        selectedPlayer.setCorrect(countCorrect);
        selectedPlayer.setTotalQuestions(numberOfQuestions);
        playersTree.insertNode(selectedPlayer); // update player in tree
        savePlayers(playersFileName); // save players in file
        submitJButton.setEnabled(false);
        nextJButton.setEnabled(false);
        playJButton.setEnabled(false);
        sitesJComboBox.setEnabled(true);
        searchJMenuItem.setEnabled(true);
        newJMenuItem.setEnabled(true);
        addJMenuItem.setEnabled(true);
        editJMenuItem.setEnabled(true);
        deleteJMenuItem.setEnabled(true);
        playerDetailsJMenuItem.setEnabled(true);
        siteDetailsJMenuItem.setEnabled(true);
        questionsJTextField.setEnabled(true);
        questionsJTextField.setText("");
        questionsJTextField.requestFocus();
        printFormJMenuItem.setEnabled(true);
        printPlayerJMenuItem.setEnabled(true);
        playerJList.setEnabled(true);
        sitesJComboBox.setSelectedIndex(0);
        for(int i = 0; i < sitesNames.size(); i++)
        {
            sitesUsedArrayList.set(i, false);
        }
       
        // Reset the sites.
        sitesJComboBox.removeAll();
        readSites(sitesFileName);
        playerJList.setSelectedIndex(0);
        
        Collections.sort(sitesNames); // Sort the array.
        fillComboBox(sitesNames);  // fill sites comboBox
    }//GEN-LAST:event_playJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       ExitJMenuItemActionPerformed()
     * Description  Event handler to exit the program. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void ExitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitJMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       addJMenuItemActionPerformed()
     * Description  Event handler to add a player in to the game. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJMenuItemActionPerformed
        try {
            AddPlayer playerDialog = new AddPlayer();
            playerDialog.setVisible(true);
            
            // The modal dialog takes focus, upon regaining focus
            Player newPlayer = playerDialog.getPlayer();
            if(newPlayer != null && !(playersTree.contains(newPlayer))){
                
                // Add the new player to the database and artistsJcomboBox
                playersTree.insertNode(newPlayer);
                playersNames.addElement(newPlayer.getName());
                numberOfPlayers = playersNames.size();
                savePlayers(playersFileName);             
            }
            else{
                JOptionPane.showMessageDialog(null, "Player " + newPlayer.getName() + " exists!", "Not Saved",
                        JOptionPane.INFORMATION_MESSAGE);
                playerJList.setVisible(true);
                playerJList.setSelectedIndex(0);
            }
        }
        catch(NullPointerException exp){
            JOptionPane.showMessageDialog(null, "Player not added", "Input Error", JOptionPane.WARNING_MESSAGE);
            playerJList.setVisible(true);
            playerJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_addJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       editJMenuItemActionPerformed()
     * Description  Event handler to edit an existing player in the game. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJMenuItemActionPerformed
        try{
            String playerName = playerJList.getSelectedValue().toString();
            Player playerToEdit = searchPlayer(playerName).data;
            EditPlayer editGUI = new EditPlayer(playerToEdit);
            editGUI.setVisible(true);
            
            Player editedPlayer = editGUI.getPlayer();
            
            if(editedPlayer != null && !(playersTree.contains(editedPlayer))){
                playersTree.remove(playerToEdit);
                playersNames.removeElement(playerName); // Removes the element from the linkedList
           
                playerJList.revalidate(); // Update the JList
                playerJList.repaint(); // removes the player from the playerJList.
                
                playersTree.insertNode(editedPlayer);
                playersNames.addElement(editedPlayer.getName());
                playerJList.revalidate(); // Update the JList
                playerJList.repaint(); // adds the player from the playerJList.
                
                savePlayers(playersFileName);
                playerJList.setSelectedIndex(0);
                playerJList.requestFocus();  
            }
        }
        catch(NullPointerException nullex)
        {
            JOptionPane.showMessageDialog(null, "Player not Edited", "Input Error", JOptionPane.WARNING_MESSAGE);
            
            playerJList.setVisible(true);
            playerJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_editJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       deleteJMenuItemActionPerformed()
     * Description  Event handler to delete an existing player from the game. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023   
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJMenuItemActionPerformed
        playerName = playerJList.getSelectedValue().toString();
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete " + playerName + "?",
                "Delete Player", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        if(result == JOptionPane.OK_OPTION){
            playersNames.removeElement(playerName);
            playerJList.revalidate(); // Update the JList
            playerJList.repaint(); // removes the player from the playerJList.
            
            BinarySearchTreeNode tempNode = playersTree.nodeWith(playerName, playersTree.getRoot());
            
            Player temp = tempNode.data;
            playersTree.remove(temp);
            numbersUsedArrayList.remove(numbersUsedArrayList.size() - 1);
            
            savePlayers(playersFileName);
            playerJList.setSelectedIndex(0);
            playerJList.requestFocus(); 
        }
    }//GEN-LAST:event_deleteJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       printPlayerJMenuItemActionPerformed()
     * Description  Event handler to find an existing player in the game. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023     
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJMenuItemActionPerformed
        playerName = JOptionPane.showInputDialog(null, "Enter name of player: ", "Search player by name", 
                JOptionPane.INFORMATION_MESSAGE);
        if((playerName != null) && (playerName.length() > 0))
        {
            BinarySearchTreeNode result = searchPlayer(playerName);
            int index = findIndex(playerName);
            if(result != null && index >= 0)
            {
                playerJList.setSelectedIndex(index);
            }
            else {
                JOptionPane.showMessageDialog(null, playerName + " is not in the database.");
            }
        }
    }//GEN-LAST:event_searchJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       printPlayerJMenuItemActionPerformed()
     * Description  Event handler to bring up the about GUI form. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023     
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuItemActionPerformed
        About aboutWindow = new About(this, true);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       questionsJTextFieldActionPerformed()
     * Description  Event handler to ask the user to enter the amount of question that they want to have in the game, and to start the game. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void questionsJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionsJTextFieldActionPerformed
        try{
            if(!Validation.isInteger(questionsJTextField.getText(), 1, maxQuestions))
                    throw new NumberFormatException();
            
            // Set the numberOfQuestions variable to the paresed value.
            numberOfQuestions = Integer.parseInt(questionsJTextField.getText());
            
            // Disable the questionsJTextField and enable the sitesJComboBox and submitJButton
            questionsJTextField.setEnabled(false);
            sitesJComboBox.setEnabled(true);
            playerJList.setEnabled(false);
            
            // Call the displaySites() method to display the sites
            displaySite();
            
            // Enable the submitJButton and disable the newJMenuItem
            submitJButton.setEnabled(true);
            newJMenuItem.setEnabled(false);
            searchJMenuItem.setEnabled(false);
            addJMenuItem.setEnabled(false);
            editJMenuItem.setEnabled(false);
            deleteJMenuItem.setEnabled(false);
            playerDetailsJMenuItem.setEnabled(false);
            siteDetailsJMenuItem.setEnabled(false);
            printFormJMenuItem.setEnabled(false);
            printPlayerJMenuItem.setEnabled(false);
            
            // Reset the count correct because it is a new quiz.
            countCorrect = 0;
            
        }
        catch(NumberFormatException exp)
        {
            // Display a warning message dialog indicating the input error.
            JOptionPane.showMessageDialog(null, "Input must be an integer in the range [1, " + maxQuestions + "].",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            
            // Set the focus on the questionsJTextField and select its entire text
            questionsJTextField.requestFocus();
            questionsJTextField.selectAll();
        }
    }//GEN-LAST:event_questionsJTextFieldActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       submitJButtonActionPerformed()
     * Description  Event handler to submit the users answer. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        count++;
        if(sitesJComboBox.getSelectedIndex() == currentIndex)
        {
            // Increment the count of correct answers
            countCorrect++;
            resultJTextField.setForeground(Color.GREEN);
            resultJTextField.setText("Correct! " + countCorrect + "/" + count);
        }
        else
        {
            resultJTextField.setForeground(Color.RED);
            resultJTextField.setText("Incorrect! " + countCorrect + "/" + count);
        }
        
        if(count == numberOfQuestions)
        {
            // Display the final result and disable certain components
            resultJTextField.setForeground(Color.BLUE);
            resultJTextField.setText("Score: " + countCorrect + "/" + numberOfQuestions);
            nextJButton.setEnabled(false);
            submitJButton.setEnabled(false);
            playJButton.setEnabled(true);
            playJButton.requestFocus();
            sitesJComboBox.setEnabled(false);
            searchJMenuItem.setEnabled(true);
            printPlayerJMenuItem.setEnabled(true);
            printFormJMenuItem.setEnabled(true); 
        }
        else 
        {
            // Enable and disable appropriate components for the next question.
            submitJButton.setEnabled(false);
            nextJButton.setEnabled(true);
            playJButton.setEnabled(false);
            searchJMenuItem.setEnabled(false);
            printPlayerJMenuItem.setEnabled(false);
            printFormJMenuItem.setEnabled(false); 
            playerDetailsJMenuItem.setEnabled(true);
            siteDetailsJMenuItem.setEnabled(true);
        }
    }//GEN-LAST:event_submitJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       nextJButtonActionPerformed()
     * Description  Event handler to go to the next question. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextJButtonActionPerformed
        displaySite();
        
        resultJTextField.setText("");
        sitesJComboBox.setSelectedIndex(0);
        submitJButton.setEnabled(true);
        siteDetailsJMenuItem.setEnabled(false);
        nextJButton.setEnabled(false);
    }//GEN-LAST:event_nextJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       siteDetailsJMenuItemActionPerformed()
     * Description  Event handler to bring up the site details GUI form. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023     
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void siteDetailsJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siteDetailsJMenuItemActionPerformed
            Site siteDetailsok = new Site(sitesNames.get(currentIndex));
            
            
            String nameDetail = siteDetailsok.getName();
            String countryDetail = siteDetailsok.getCountry();
            float populationDetail = siteDetailsok.getPopulation();
            String capitalDetail = siteDetailsok.getCapital();
            float areaDetail = siteDetailsok.getArea();
            
            SiteDetails theSiteDetails = new SiteDetails(this, true, nameDetail, countryDetail,
                    populationDetail, capitalDetail, areaDetail);
            
            theSiteDetails.setVisible(true); 
    }//GEN-LAST:event_siteDetailsJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       playerDetailsJMenuItemActionPerformed()
     * Description  Event handler to bring up the player details GUI form. 
     * @author      <i>Kirill Grichanichenko</i>
     * Date         09/19/2023    
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void playerDetailsJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerDetailsJMenuItemActionPerformed
        String playerDetailsName = playerJList.getSelectedValue();
        
        if(playerDetailsName.contains(","))
        {
            playerDetailsName = playerDetailsName.substring(0,playerDetailsName.indexOf(""));
        }
        
        Player playerDetailsok = new Player(searchPlayer(playerDetailsName).data);
        
        String nameDetail = playerDetailsok.getName();
        int ageDetail = playerDetailsok.getAge();
        int correctDetail = playerDetailsok.getCorrect();
        int totalDetail = playerDetailsok.getTotalQuestions();
        double calculatedPercentDetail = playerDetailsok.calculatePercent();
        
        PlayerDetails playerDetails = new PlayerDetails(this, true, nameDetail, ageDetail,
                correctDetail, totalDetail, calculatedPercentDetail);
        
        playerDetails.setVisible(true); 
    }//GEN-LAST:event_playerDetailsJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       main()
     * Description  Displays splash screen and the main GUI form.
     * Date         09/19/2023    
     * @param       args are the command line strings
     * @author      <i>Kirill Grichanichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SiteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SiteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SiteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SiteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Splash mySplash = new Splash(4000);
        mySplash.showSplash();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SiteGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ExitJMenuItem;
    private javax.swing.JPanel SelectJPanel;
    private javax.swing.JLabel TitleJLabel;
    private javax.swing.JPanel TitleJPanel;
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JPopupMenu.Separator fileJSeparator;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JMenuItem newJMenuItem;
    private javax.swing.JButton nextJButton;
    private javax.swing.JButton playJButton;
    private javax.swing.JMenuItem playerDetailsJMenuItem;
    private javax.swing.JList<String> playerJList;
    private javax.swing.JMenu playerJMenu;
    private javax.swing.JScrollPane playerJScrollPane;
    private javax.swing.JMenuItem printFormJMenuItem;
    private javax.swing.JMenuItem printPlayerJMenuItem;
    private javax.swing.JLabel questionsJLabel;
    private javax.swing.JTextField questionsJTextField;
    private javax.swing.JTextField resultJTextField;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.JLabel selectJLabel;
    private javax.swing.JMenuItem siteDetailsJMenuItem;
    private javax.swing.JLabel siteJLabel;
    private javax.swing.JLabel siteNameJLabel;
    private javax.swing.JComboBox<String> sitesJComboBox;
    private javax.swing.JMenuBar sitesJMenuBar;
    private javax.swing.JButton submitJButton;
    // End of variables declaration//GEN-END:variables
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Inner Class  MyFilter
 * Description  An inner class that filters out the users file input.
 * Date         09/27/2023
 * @author	<i>Kirill Grichanichenko</i>
 * @see     	javax.swing.filechooser.FileFilter
 * @see         java.awt.Toolkit 
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    class MyFilter extends javax.swing.filechooser.FileFilter
    {
    private String startSequence = "";
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  MyFilter-Default Constructor
    * Description  Create an instance of the myFilter class.
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023                  
    *****************************************************************************/
    public MyFilter(String filter)
    {
        startSequence = filter;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       accept
    * Description  checks the requirements of the input and accepts or rejects it 
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023     
              
    *****************************************************************************/
    public boolean accept(File file)
    {
        String fileName = file.getName();
        return fileName.contains(startSequence);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getDescription
    * Description  returns the description of the input.
    * @author      <i>Kirill Grichanichenko</i>
    * Date         10/06/2023               
    *****************************************************************************/
    public String getDescription()
    {
        return "*.txt";
        }
    }
}