package com.aracomp.TUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aracomp.fileSystem.Disk;
import com.aracomp.fileSystem.DiskManager;
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
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class InitialScreen {

	private static List<String> fileList = new ArrayList<>();
	private static Disk disk = new Disk(32);
	private static DiskManager diskManager = new DiskManager(disk);

	public static void main(String[] args) {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		disk.setDiskManager(diskManager);

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

				String fileName = selectedFile.get(0);

				Panel dialogPanel = new Panel(new GridLayout(4));

				dialogPanel.addComponent(new Label("Escolha uma ação para o arquivo \"" + fileName + "\"."));

				BasicWindow actionWindow = new BasicWindow("Escolher Ação");

				Button readButton = new Button("Ler", () -> {
					String fileContent = diskManager.read(fileName);
					showMessage("Conteúdo do Arquivo", "Conteúdo de " + fileName + ": \n" + fileContent, screen);
				});

				Button deleteButton = new Button("Apagar", () -> {
					diskManager.delete(fileName);

					int selectedIndex = fileTable.getSelectedRow();
					fileTable.getTableModel().removeRow(selectedIndex);

					showMessage("Arquivo Apagado", "O arquivo \"" + fileName + "\" foi apagado com sucesso.", screen);
					actionWindow.close();
				});

				Button backButton = new Button("Voltar", () -> {
					actionWindow.close();
				});

				dialogPanel.addComponent(readButton);
				dialogPanel.addComponent(deleteButton);
				dialogPanel.addComponent(backButton);

				actionWindow.setComponent(dialogPanel);

				new MultiWindowTextGUI(screen).addWindowAndWait(actionWindow);
			});

			mainPanel.addComponent(fileTable, GridLayout.createLayoutData(GridLayout.Alignment.FILL,
					GridLayout.Alignment.FILL, true, true, 2, 1));

			Button addButton = new Button("Adicionar Arquivo", () -> {
				try {
					String fileName = TextInputDialog.showDialog(new MultiWindowTextGUI(screen), "Adicionar Arquivo",
							"Digite o nome do arquivo:", "");
					String fileContent = TextInputDialog.showDialog(new MultiWindowTextGUI(screen),
							"Adicionar Conteúdo", "Digite o conteúdo do arquivo:", "");
					diskManager.add(fileName, fileContent);
					fileTable.getTableModel().addRow(fileName);
					fileList.add(fileName);
					showMessage("Arquivo Adicionado", "Arquivo " + fileName + " adicionado com sucesso!", screen);
				} catch (Exception e) {
					showMessage("Erro", "Operação invalida ao adicionar arquivo", screen);
				}
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

	private static void showMessage(String title, String message, Screen screen) {
		MessageDialog.showMessageDialog(new MultiWindowTextGUI(screen), title, message, MessageDialogButton.OK);
	}
}
