/**
 * @(#)Chat_Bot.java
 *
 * Chat_Bot application
 *
 * @author
 * @version 1.00 2017/5/12
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener {

	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(20,50);
	JTextArea input = new JTextArea(1,50);
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

String [][] chatBot = {
	//standard greetings
	{"hi", "hello", "hola", "howdy", "hey"},
	{"hi", "hello", "hey"},
	//question greetings
	{"how are you", "how r u", "How r you"},
	{"good", "i'm doing well", "I'm doing great!"},
	//whatcha doing
	{"what are you doing", "whatcha up to", "what's up", "what up", "hey whats up"},
	{"nothing much", "just chillin", "nothing much. Just talking to you", "just relaxing. wbu?"},
	//wanna hang
	{"you wanna hang out", "wanna hang","lets hang", "wanna hang out"},
	{"sure lets do it", "sorry I can't", "no", "sure when?"},
	//reason
	{"why", "y", "give me a reason"},
	{"I'm too busy", "I don't really like you", "I don't feel like it sorry"},
	//thank you
	{"thank you", "thanks", "thanks fam", "appreciate it"},
	{"you're welcome", "no problem", "anytime"},
	//hobbies
	{"what do you like to do", "what do u like"},
	{"I like to play soccer", "I like music", "I like playing the guitar and drumset"},
	//default
	{"sorry I don't understand", "I don't understand sorry", "let's talk about something else","cool", "okay cool"},
};

String [][] verbs={
	{"is","'re"},
	{"was", "'re"},
	{"think", " think"},
	{"s", "'re"},
	{"'re", "'re"},
	{"are", "'re"}
};


public static void main(String[] args) {
		new ChatBot();
	}

public ChatBot() {
	super("Chat Bot");
	setSize(600, 400);
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	dialog.setEditable(false);
	input.addKeyListener(this);

	p.add(scroll);
	p.add(input);
	p.setBackground(new Color(255, 0, 0));
	add(p);

	setVisible(true);

}

public void keyPressed(KeyEvent e) {
	if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		input.setEditable(false);
		//-----grab quote------
		String quote = input.getText();
		input.setText("");
		addText("-->You:\t" +quote);
		quote.trim();

	while(quote.charAt(quote.length()-1)== '!'||
		quote.charAt(quote.length()-1)== '.'||
		quote.charAt(quote.length()-1)== '?')
			{
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();

	byte response=0;
		/*
		0: we're searching through chatBot[][] for matches
		1: we didn't find anything in chatBot[][]
		2: we did find something in chatBot[][]
		*/

		//-----check for matches----
		int j=0; //which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])) {
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->Bishesh:\t"+chatBot[(j*2)+1][r]);
					}
					j++;
					if(j*2==chatBot.length-1 && response==0) {
						response=1;
					}
			}
			//----try counter---
		if(response==1){
				String quoteWords[]=quote.split("[ ']");
				int c= counter(quoteWords);
				if(c!=-1) {
					String ext=quote.split(verbs[c][0])[1];
					addText("\n-->Bishesh:\tYou"+verbs[c][1]+ext);
					response=2;
				}
			}

			//----default------
			if(response == 1){
				int r= (int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Bishesh:\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			input.setEditable(true);
			}
	}

	public void keyTyped(KeyEvent e) {}

	public void addText (String str) {
		dialog.setText(dialog.getText()+str);
	}

	public boolean inArray(String in,String [] str) {
		boolean match = false;
		for(int i=0;i<str.length; i++) {
			if(str[i].equals(in)) {
				match=true;
			}
		}
		return match;
	}

	public int counter(String str[]) {
	int verbID = -1;
	for(int i=0; i<str.length; i++) {
		for(int j=0; j<verbs.length; j++) {
			if (str[i].equals(verbs[j][0])) {
				verbID = j;
			}
		}
	}
	return verbID;
}
}


