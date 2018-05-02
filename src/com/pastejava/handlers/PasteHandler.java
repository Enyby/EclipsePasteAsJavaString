package com.pastejava.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.pastejava.Utility;

/**
 * The paste handler wraps the default paste action. If the contents of the
 * clipboard contains a newline at the end, and nothing is currently selected,
 * the cursor is moved to the start of the line to past the lines as is, rather
 * than in the middle of the text.
 */
public class PasteHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITextEditor editor = Utility.getEditorForEvent(event);
		org.eclipse.swt.dnd.Clipboard cb = new org.eclipse.swt.dnd.Clipboard(
				PlatformUI.getWorkbench().getDisplay());
		TextTransfer tt = TextTransfer.getInstance();
		cb.getContents(tt);
		String textData = (String) cb.getContents(tt);
		String newText = textData;
		
		newText = "\"" + newText			
			.replace("\"", "\\" + "\"")
			.replace("\t", "\\" + "t")
			.replace("\r", "\\" + "r")
			.replace("\n", "\\" + "n" + "\" +\n\"")
//			.replace("\f", "\\" + "f")
//			.replace("\b", "\\" + "b")
//			.replace("\\", "\\" + "\\")
			+ "\"";
		
		{
			TextTransfer textTransfer = TextTransfer.getInstance();
	        Transfer[] transfers = new Transfer[]{textTransfer};
	        Object[] data = new Object[]{newText};
	        cb.setContents(data, transfers);
		}
		
		// Paste!
		editor.getAction(ITextEditorActionConstants.PASTE).run();
		
		{
	        Transfer[] transfers = new Transfer[]{tt};
	        Object[] data = new Object[]{textData};
	        cb.setContents(data, transfers);
		}

		return null;
	}
}
