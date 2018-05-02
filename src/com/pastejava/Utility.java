package com.pastejava;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Utility methods for the LineClipboard plugin (stolen from nflath's plugin at
 * https://github.com/nflath/eclipse-hungry-delete/)
 */
public class Utility {

	public static ITextEditor getEditorForEvent(ExecutionEvent event)
			throws ExecutionException {
		IEditorPart part = HandlerUtil.getActiveEditorChecked(event);
		if (part instanceof ITextEditor)
		{
			return (ITextEditor) part;
		}
		else if (part instanceof MultiPageEditorPart)
		{
			MultiPageEditorPart multiPart = (MultiPageEditorPart)part;
			Object page = multiPart.getSelectedPage();
			if (page instanceof ITextEditor)
			{
				return (ITextEditor)page;
			}
		}
		return null;
	}
}
