package com.aracomp.TUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class InitialScreen {

	private static List<String> fileList = new ArrayList<>();

	public static void main(String[] args) {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

		try {
			Screen screen = terminalFactory.createScreen();
			screen.startScreen();

			Panel mainPanel = new Panel();
			mainPanel.setLayoutManager(new GridLayout(2));

			Label title = new Label("Sistemas de Arquivos").setForegroundColor(TextColor.ANSI.WHITE)
					.setBackgroundColor(TextColor.ANSI.BLUE);
			title.setPreferredSize(new TerminalSize(40, 2));
			mainPanel.addComponent(title, GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
					GridLayout.Alignment.CENTER, true, false, 2, 1));

			Table<String> fileTable = new Table<>("Arquivos");
			fileTable.setPreferredSize(new TerminalSize(40, 10));

			fileTable.setSelectAction(() -> {
				List<String> selectedFile = fileTable.getTableModel().getRow(fileTable.getSelectedRow());
				MessageDialog.showMessageDialog(new MultiWindowTextGUI(screen), "Conteúdo do Arquivo",
						"Conteúdo de " + selectedFile, MessageDialogButton.OK);
			});

			mainPanel.addComponent(fileTable, GridLayout.createLayoutData(GridLayout.Alignment.FILL,
					GridLayout.Alignment.FILL, true, true, 2, 1));

			Button addButton = new Button("Adicionar Arquivo", () -> {
				String newFileName = "file" + (fileList.size() + 1) + ".txt";
				fileTable.getTableModel().addRow(newFileName);
				fileList.add(newFileName);
				MessageDialog.showMessageDialog(new MultiWindowTextGUI(screen), "Arquivo Adicionado",
						"Arquivo " + newFileName + " adicionado!", MessageDialogButton.OK);
			});
			mainPanel.addComponent(addButton, GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
					GridLayout.Alignment.CENTER, true, false, 2, 1));

			Button exitButton = new Button("Sair", () -> {
				System.out.println("Encerrando aplicação...");
				try {
					screen.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			mainPanel.addComponent(exitButton, GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
					GridLayout.Alignment.CENTER, true, false, 2, 1));

			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			BasicWindow window = new BasicWindow();
			window.setComponent(mainPanel);

			gui.addWindowAndWait(window);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
