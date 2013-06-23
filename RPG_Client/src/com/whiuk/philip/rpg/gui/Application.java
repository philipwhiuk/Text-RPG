package com.whiuk.philip.rpg.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.Element;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.whiuk.philip.rpg.xml.XMLDataSource;

import com.whiuk.philip.rpg.core.DataSourceManager;
import com.whiuk.philip.rpg.core.Game;
import com.whiuk.philip.rpg.core.Output;

/**
 * Represents an instance of the application.
 * @author Philip
 *
 */
public final class Application extends JFrame implements Output {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Application.class);
    /**
	 * Frame title
	 */
	private static final String FRAME_TITLE = "RPG";
	/**
	 * Command entry point
	 */
	private JTextField textfield;
	/**
	 * List of available options
	 */
	private String[] options;
    private JTextPane textarea;
    private HTMLDocument doc;
    private Element messagesElement;

	public Application() {
		super(FRAME_TITLE);
		textarea = new JTextPane();
		textarea.setEditorKit(new HTMLEditorKit());
		textarea.setContentType("text/html");
		textarea.setText("<html><head><style 'type=text/css'>body { font-family: 'Courier New', mono-spaced; }</style><title>An example HTMLDocument</title>"
		        + "</head><body><div id='messages'>"
		        + "</div>"
		        + "</body>"
		        + "</html>");
	    doc = (HTMLDocument) textarea.getStyledDocument();
	    messagesElement = doc.getElement("messages");
		textarea.setEnabled(true);
		textfield = new JTextField();
		textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                textfieldActionPerformed();
            }
		    
		});
		textfield.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				textfieldKeyTyped();
			}
			
		});
		setSize(640, 480);
		add(textarea,BorderLayout.CENTER);
		add(textfield,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	protected void textfieldActionPerformed() {
	    final String input = textfield.getText();
	    textfield.setText("");
	    SwingUtilities.invokeLater(new Runnable(){
	        public void run() {
	            processInput(input);	            
	        }
	    });
    }

    private void processInput(String input) {
        outputLine("> "+input);
        Game.processInput(input,this);
    }

    private void outputLine(String line) {
        try {
            doc.insertBeforeEnd(messagesElement,"<div>" + line + "</div>");
        } catch (BadLocationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void textfieldKeyTyped() {
		List<String> options = autoComplete(textfield.getText());
		//TODO: Create auto complete box with options
	}
	/**
	 * 
	 * @param needle
	 * @return
	 */
	private List<String> autoComplete(String needle) {
		ArrayList<String> autoCompletions = new ArrayList<String>();
		for(String toMatch : Game.getOptions()) {
			if(toMatch.startsWith(needle)) {
				autoCompletions.add(toMatch);
			}
		}
		return autoCompletions;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    //Some Setup
	    BasicConfigurator.configure();
	    DataSourceManager.registerDataSource(XMLDataSource.class, true);
	    
		Application application = new Application();
		application.setVisible(true);
	}

    @Override
    public void sendErrorMessage(String message) {
        try {
            doc.insertBeforeEnd(messagesElement,"<div class='error'><b>ERROR:</b> "+message+"</div>");
        } catch (BadLocationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(String message) {
        logger.info("Message:" + message);
        try {
            doc.insertBeforeEnd(messagesElement,"<div>" + message + "</div>");
        } catch (BadLocationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMultiMessage(String[] messages) {
        for(String message: messages) {
            try {
                doc.insertBeforeEnd(messagesElement,"<div>" + message + "</div>");
            } catch (BadLocationException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void locationChanged(String location) {
        try {
            doc.insertBeforeEnd(messagesElement,"<div><b>" +location + "</b></div>");
        } catch (BadLocationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
