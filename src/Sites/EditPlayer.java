package Sites;

import java.awt.Color;
import java.util.List;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        EditPlayer.java
 * Description  A class representing the EditPlayer JDialog
 * Date         10/06/2023     
 * @author      <i>Kirill Grichanichenko</i>           
 *****************************************************************************/
public class EditPlayer extends javax.swing.JDialog {
    
    SiteGUI siteGUI = new SiteGUI(); // Intialize SiteGUI to get the list of sites
    private List<Site> sitesNames = siteGUI.getSitesNames(); // Get the List of site
    
    private final int MAX_QUESTIONS = sitesNames.size(); // Get the List of sites size to get the max questions.
    
    private Player newPlayer;                   // Player to be created.
    private boolean error = false;              // Checking for errors in input
    private String errorMessage = "";           // Error message
    private final int MAX_AGE = 150;            // Maximum birth year for player
    private final int MIN_AGE = 1;              // Minimum birth year for player 
    private final Color white = Color.WHITE;    // Default background color for input textfield
    private final Color pink = Color.PINK;      // Background color for bad input textfield

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  EditPlayer()-Default Constructor
    * Description  Creates an instance of EditPlayer JDialog form
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>           
    *****************************************************************************/
    public EditPlayer(){
        initComponents();
        //set saveJButton as default button
        this.getRootPane().setDefaultButton(saveJButton);
        this.setLocationRelativeTo(null);       //center form
        ageJTextField.requestFocus();
        newPlayer = null;                       // player to be returned	       
        setAlwaysOnTop(true);
        setModal(true);
        nameJTextField.requestFocus();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor  EditPlayer()-Constructor
    * Description  Creates an instance of EditPlayer JDialog form
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i> 
    * @param       player Player
    *****************************************************************************/
    public EditPlayer(Player player){
        this(); // Call default constructor
        nameJTextField.setText(player.getName());
        ageJTextField.setText(String.valueOf(player.getAge()));
        correctJTextField.setText(String.valueOf(player.getCorrect()));
        totalJTextField.setText(String.valueOf(player.getTotalQuestions()));
        nameJTextField.requestFocus();
        nameJTextField.selectAll();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       getPlayer()
    * Description  Gets the new player and returns it.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @return      newPlayer
    *****************************************************************************/
    public Player getPlayer(){
        return newPlayer;
    }
    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       EditPlayer()
    * Description  Edit an existing player.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       parent java.awt.Frame
    * @param       model Boolean
    *****************************************************************************/
    public EditPlayer(java.awt.Frame parent, boolean model) {
        super(parent, model);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJPanel = new javax.swing.JPanel();
        addEditJLabel = new javax.swing.JLabel();
        logoJLabel = new javax.swing.JLabel();
        controlJPanel = new javax.swing.JPanel();
        saveJButton = new javax.swing.JButton();
        cancelJButton = new javax.swing.JButton();
        displayJPanel = new javax.swing.JPanel();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        ageJLabel = new javax.swing.JLabel();
        ageJTextField = new javax.swing.JTextField();
        correctJLabel = new javax.swing.JLabel();
        correctJTextField = new javax.swing.JTextField();
        totalJLabel = new javax.swing.JLabel();
        totalJTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        addEditJLabel.setFont(new java.awt.Font("Tamil MN", 2, 28)); // NOI18N
        addEditJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        addEditJLabel.setText("Edit Player");

        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/World Map Logo.jpeg"))); // NOI18N

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addEditJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addEditJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        controlJPanel.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        saveJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveJButton.setMnemonic('S');
        saveJButton.setText("Save");
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(saveJButton);

        cancelJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cancelJButton.setMnemonic('C');
        cancelJButton.setText("Cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(cancelJButton);

        displayJPanel.setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        nameJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameJLabel.setText("Name:");
        displayJPanel.add(nameJLabel);

        nameJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(nameJTextField);

        ageJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ageJLabel.setText("Age:");
        displayJPanel.add(ageJLabel);

        ageJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ageJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(ageJTextField);

        correctJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        correctJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        correctJLabel.setText("Correct Answers:");
        displayJPanel.add(correctJLabel);

        correctJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correctJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(correctJTextField);

        totalJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalJLabel.setText("Total Questions:");
        displayJPanel.add(totalJLabel);

        totalJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                totalJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(totalJTextField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       saveJButtonActionPerformed()
    * Description  An event handler that saves the given information.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       evt java.awt.event.ActionEvent
    *****************************************************************************/
    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed
        // Read all inputs as strings
        String playerName = nameJTextField.getText();
        String ageString = ageJTextField.getText();
        String correctString = correctJTextField.getText();
        String totalString = totalJTextField.getText();
        String maxQuestions = Integer.toString(MAX_QUESTIONS);
        
        // Validate all inputs
        if(!Validation.isValidName(playerName))
        {
            errorMessage += "Invalid Player Name\n";
            nameJTextField.requestFocus();
            error = true;
            nameJTextField.setToolTipText(nameJTextField.getToolTipText() + 
                    "--Invalid Player Name");
        }
        else if(!Character.isUpperCase(playerName.charAt(0)))
        {
            errorMessage += "Not Capatalized Name\n";
            nameJTextField.requestFocus();
            error = true;
            nameJTextField.setToolTipText(nameJTextField.getToolTipText() + "--Invalid Player Name. Check capitalization.");
        }
        else if(!Validation.isInteger(ageString, MIN_AGE, MAX_AGE))
        {
            errorMessage += "Invalid Age\n";
            ageJTextField.requestFocus();
            error = true;
            ageJTextField.setToolTipText(ageJTextField.getToolTipText() + 
                    "--Invalid Age");
        }
        else if(!Validation.isInteger(correctString))
        {
            errorMessage += "Invalid Number\n";
            correctJTextField.requestFocus();
            error = true;
            correctJTextField.setToolTipText(correctJTextField.getToolTipText() 
                + "--Invalid Number");
        }
        // Checks to see if correct questions is greater than or equal to total questions
        else if(Validation.isGreaterThan(correctString, totalString))
        {
            errorMessage += "Invalid Number\n";
            correctJTextField.requestFocus();
            error = true;
            correctJTextField.setToolTipText(correctJTextField.getToolTipText() 
                + "--Correct questions must be less than or equal to total questions");
        }
        else if(!Validation.isInteger(totalString))
        {
            errorMessage += "Invalid Total\n";
            totalJTextField.requestFocus();
            error = true;
            totalJTextField.setToolTipText(totalJTextField.getToolTipText() 
                + "--Invalid Total");
        }
        // Checks to see if total questions is less than or equal to max questions.
        else if(Validation.isGreaterThan(totalString, maxQuestions))
        {
            errorMessage += "Invalid Total\n";
            totalJTextField.requestFocus();
            error = true;
            totalJTextField.setToolTipText(totalJTextField.getToolTipText() 
                + "--Total questions must be less than or equal to " + MAX_QUESTIONS);
        }
        else
            error = false;
       
        if (!error) // all fields are valid, create a newArtist
        {
            try
            {
                playerName = nameJTextField.getText();
                int age = Integer.parseInt(ageJTextField.getText());
                int correct = Integer.parseInt(correctJTextField.getText());
                int total = Integer.parseInt(totalJTextField.getText());
                newPlayer = new Player(playerName, age, correct, total);                
                this.dispose();     //close form
            }
            catch(NumberFormatException ex)
            {
                //We should never get here, throw runtime exception
                throw new RuntimeException("Error parsing input");
            }
        }
    }//GEN-LAST:event_saveJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       cancelJButtonActionPerformed()
    * Description  An event handler that exits the JDialog
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       evt java.awt.event.ActionEvent
    *****************************************************************************/
    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       nameJTextFieldFocusLost()
    * Description  An event handler that checks the validation of the name.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       evt java.awt.event.FocusEvent
    *****************************************************************************/
    private void nameJTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameJTextFieldFocusLost
        // Set background textfield color     
        String input = nameJTextField.getText();
        if(Validation.isValidName(input)&& Character.isUpperCase(input.charAt(0)))
            nameJTextField.setBackground(white);
        else
            nameJTextField.setBackground(pink);
    }//GEN-LAST:event_nameJTextFieldFocusLost

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       ageJTextFieldFocusLost()
    * Description  An event handler that checks the validation of the age.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       evt java.awt.event.FocusEvent
    *****************************************************************************/
    private void ageJTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageJTextFieldFocusLost
        // Set background textfield color     
        String input = ageJTextField.getText();
        if(Validation.isInteger(input, MIN_AGE, MAX_AGE))
            ageJTextField.setBackground(white);
        else
            ageJTextField.setBackground(pink);
    }//GEN-LAST:event_ageJTextFieldFocusLost

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       correctJTextFieldFocusLost()
    * Description  An event handler that checks the validation of the correctQuestions.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       evt java.awt.event.FocusEvent
    *****************************************************************************/
    private void correctJTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correctJTextFieldFocusLost
        // Set background textfield color     
        String input = correctJTextField.getText();
        String total = totalJTextField.getText();
        if(Validation.isInteger(input,0,MAX_QUESTIONS) && !Validation.isGreaterThan(input,total))
            correctJTextField.setBackground(white);
        else
            correctJTextField.setBackground(pink);
    }//GEN-LAST:event_correctJTextFieldFocusLost

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Method       nameJTextFieldFocusLost()
    * Description  An event handler that checks the validation of the totalQuestions.
    * Date         10/06/2023     
    * @author      <i>Kirill Grichanichenko</i>
    * @param       evt java.awt.event.FocusEvent
    *****************************************************************************/
    private void totalJTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_totalJTextFieldFocusLost
         // Set background textField color
        String input = totalJTextField.getText();
        if(Validation.isInteger(input,0,MAX_QUESTIONS))
            totalJTextField.setBackground(white);
        else
            totalJTextField.setBackground(pink);
    }//GEN-LAST:event_totalJTextFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addEditJLabel;
    private javax.swing.JLabel ageJLabel;
    private javax.swing.JTextField ageJTextField;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JLabel correctJLabel;
    private javax.swing.JTextField correctJTextField;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JButton saveJButton;
    private javax.swing.JPanel titleJPanel;
    private javax.swing.JLabel totalJLabel;
    private javax.swing.JTextField totalJTextField;
    // End of variables declaration//GEN-END:variables
}
