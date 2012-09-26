/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.mengke.koala.client.common.rpc.KoalaAsyncCallback;
import com.mengke.koala.client.images.DesktopImages;
import com.mengke.koala.client.solrcore.SolrCoreRPCService;
import com.mengke.koala.shared.solrcore.domain.SolrCoreBaseInfo;
import com.mengke.koala.shared.solrcore.domain.SolrCoresResult;
import com.sencha.gxt.desktop.client.layout.DesktopLayoutType;
import com.sencha.gxt.desktop.client.widget.Desktop;
import com.sencha.gxt.desktop.client.widget.Shortcut;
import com.sencha.gxt.desktop.client.widget.StartMainMenuItem;
import com.sencha.gxt.desktop.client.widget.StartToolMenuItem;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

import java.util.Iterator;
import java.util.List;

/**
 * Provides a desktop application view.
 *
 * @see DesktopAppView
 */
public class DesktopAppViewImpl implements DesktopAppView {

	private Desktop desktop;
	private StartMainMenuItem fileManagerStartMenuItem;
	private StartToolMenuItem cascadeToolMenuItem;
	private StartToolMenuItem tileToolMenuItem;
	private StartToolMenuItem updateProfileToolMenuItem;
	private StartToolMenuItem logoutToolMenuItem;
	private SelectionHandler<Item> fileManagerStartMenuListener;
	private SelectionHandler<Item> solrCoreStartMenuListener;
	private SelectHandler fileManagerShortcutListener;
	private SelectHandler solrCoreShortcutListener;
	private DesktopAppPresenter desktopAppPresenter;

	/**
	 * Creates a desktop application view that interacts with the rest of the
	 * application using the specified presenter.
	 *
	 * @param desktopAppPresenter the source of commands and data and the target
	 *                            of user initiated events.
	 */
	public DesktopAppViewImpl(DesktopAppPresenter desktopAppPresenter) {
		this.desktopAppPresenter = desktopAppPresenter;
	}

	@Override
	public void add(Widget widget) {
		getDesktop().activate(widget);
	}

	@Override
	public Widget asWidget() {
		return getDesktop().asWidget();
	}

	@Override
	public void clear() {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	private Desktop getDesktop() {
		if (desktop == null) {
			desktop = new Desktop();
			desktop.addStartMenuItem(getFileManagerStartMenuItem());
			SolrCoreRPCService.App.getInstance().getSolrCores(new KoalaAsyncCallback<SolrCoresResult>() {
				@Override
				protected void handleResult(SolrCoresResult result) {
					List<SolrCoreBaseInfo> baseInfos = result.getSolrCoreList();
					for (SolrCoreBaseInfo baseInfo : baseInfos) {
//						desktop.addShortcut(getSolrCoreShortcut(baseInfo));
						desktop.addStartMenuItem(getSolrCoreStartMenuItem(baseInfo));
					}
				}
			});
			desktop.setStartMenuHeading(getPresenter().getCurrentUser());
			desktop.setStartMenuIcon(DesktopImages.INSTANCE.user());
			desktop.addToolSeparator();
			desktop.addToolMenuItem(getUpdateProfileToolMenuItem());
			desktop.addToolSeparator();
			desktop.addToolMenuItem(getLogoutToolMenuItem());
			desktop.layout(DesktopLayoutType.TILE);
		}
		return desktop;
	}

	private DesktopAppPresenter getDesktopAppPresenter() {
		return desktopAppPresenter;
	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Widget w) {
		// TODO: implement
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDesktopLayoutType(DesktopLayoutType desktopLayoutType) {
		getDesktop().setDesktopLayoutType(desktopLayoutType);
	}

	private StartToolMenuItem getCascadeToolMenuItem() {
		if (cascadeToolMenuItem == null) {
			cascadeToolMenuItem = new StartToolMenuItem("Cascade");
			cascadeToolMenuItem.setIcon(DesktopImages.INSTANCE.application_cascade());
			cascadeToolMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					getDesktop().layout(DesktopLayoutType.CASCADE);
				}
			});
		}
		return cascadeToolMenuItem;
	}

	private Shortcut getSolrCoreShortcut(SolrCoreBaseInfo baseInfo) {
		Shortcut solrCoreShortcut = new Shortcut();
		solrCoreShortcut.setText(baseInfo.getCoreName());
		solrCoreShortcut.setIcon(DesktopImages.INSTANCE.folder_shortcut());
		solrCoreShortcut.addSelectHandler(getSolrCoreShortcutListener(baseInfo));
		return solrCoreShortcut;
	}

	private SelectHandler getFileManagerShortcutListener() {
		if (fileManagerShortcutListener == null) {
			fileManagerShortcutListener = new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					getDesktopAppPresenter().onOpenFileManager();
				}
			};
		}
		return fileManagerShortcutListener;
	}

	private SelectHandler getSolrCoreShortcutListener(final SolrCoreBaseInfo baseinfo) {
		if (solrCoreShortcutListener == null) {
			solrCoreShortcutListener = new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					getDesktopAppPresenter().onOpenSolrCore(baseinfo);
				}
			};
		}
		return solrCoreShortcutListener;
	}

	private StartMainMenuItem getFileManagerStartMenuItem() {
		if (fileManagerStartMenuItem == null) {
			fileManagerStartMenuItem = new StartMainMenuItem("File Manager");
			fileManagerStartMenuItem.setIcon(DesktopImages.INSTANCE.folder());
			fileManagerStartMenuItem.addSelectionHandler(getFileManagerStartMenuListener());
		}
		return fileManagerStartMenuItem;
	}

	private StartMainMenuItem getSolrCoreStartMenuItem(SolrCoreBaseInfo baseInfo) {
		StartMainMenuItem solrCoreStartMenuItem = new StartMainMenuItem(baseInfo.getCoreName());
		solrCoreStartMenuItem.setIcon(DesktopImages.INSTANCE.folder());
		solrCoreStartMenuItem.addSelectionHandler(getSolrCoreStartMenuListener(baseInfo));
		return solrCoreStartMenuItem;
	}

	private SelectionHandler<Item> getFileManagerStartMenuListener() {
		if (fileManagerStartMenuListener == null) {
			fileManagerStartMenuListener = new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					getDesktopAppPresenter().onOpenFileManager();
				}
			};
		}
		return fileManagerStartMenuListener;
	}

	private SelectionHandler<Item> getSolrCoreStartMenuListener(final SolrCoreBaseInfo baseInfo) {
		SelectionHandler listener = new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				getDesktopAppPresenter().onOpenSolrCore(baseInfo);
			}
		};
		return listener;
	}

	private StartToolMenuItem getLogoutToolMenuItem() {
		if (logoutToolMenuItem == null) {
			logoutToolMenuItem = new StartToolMenuItem("Logout");
			logoutToolMenuItem.setIcon(DesktopImages.INSTANCE.door_out());
			logoutToolMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					getPresenter().onLogout();
				}
			});
		}
		return logoutToolMenuItem;
	}

	private DesktopAppPresenter getPresenter() {
		return desktopAppPresenter;
	}

	private StartToolMenuItem getTileToolMenuItem() {
		if (tileToolMenuItem == null) {
			tileToolMenuItem = new StartToolMenuItem("Tile");
			tileToolMenuItem.setIcon(DesktopImages.INSTANCE.application_tile_horizontal());
			tileToolMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					getDesktop().layout(DesktopLayoutType.TILE);
				}
			});
		}
		return tileToolMenuItem;
	}

	private StartToolMenuItem getUpdateProfileToolMenuItem() {
		if (updateProfileToolMenuItem == null) {
			updateProfileToolMenuItem = new StartToolMenuItem("Settings");
			updateProfileToolMenuItem.setIcon(DesktopImages.INSTANCE.user_edit());
			updateProfileToolMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					getDesktopAppPresenter().onOpenProfile();
				}
			});
		}
		return updateProfileToolMenuItem;
	}

}
