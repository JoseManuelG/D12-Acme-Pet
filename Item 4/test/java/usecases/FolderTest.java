/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ActorService;
import services.FolderService;
import utilities.AbstractTest;
import domain.Folder;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FolderTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private FolderService	folderService;
	@Autowired
	private ActorService	actorService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un vet:
	//final String actorBean, final String folderName, final Class<?> expected
	//test positivo
	@Test
	public void createFolderTest1() {
		this.templateCreateFolder("animaniac1", "FunctionalTestFolder", null);
	}
	//blank folderName
	@Test
	public void createFolderTest2() {
		this.templateCreateFolder("animaniac1", "", IllegalArgumentException.class);
	}

	//sin loguearse
	@Test
	public void createFolderTest3() {
		this.templateCreateFolder(null, "FunctionalTestFolder", IllegalArgumentException.class);
	}

	//Caso de uso de editar un vet:
	//final String actorBean, final String folderBean, final String folderName, final Class<?> expected
	//test positivo
	@Test
	public void editFolderTest1() {
		this.templateEditFolder("animaniac1", "folder5Animaniac1", "aaaa", null);
	}
	//folderName ya existente
	@Test
	public void editFolderTest3() {
		this.templateEditFolder("animaniac1", "folder5Animaniac1", "spambox", IllegalArgumentException.class);
	}
	//sin loguearse
	@Test
	public void editFolderTest2() {
		this.templateEditFolder(null, "folder5Animaniac1", "FunctionalTestFolder", IllegalArgumentException.class);
	}

	//no se el dueño del folder
	@Test
	public void editFolderTest4() {
		this.templateEditFolder("animaniac2", "folder5Animaniac1", "FunctionalTestFolder", IllegalArgumentException.class);
	}
	//blanks
	@Test
	public void editFolderTest5() {
		this.templateEditFolder("animaniac1", "folder5Animaniac1", "", IllegalArgumentException.class);
	}
	//noEsxist
	@Test
	public void editFolderTest6() {
		this.templateEditFolder("animaniac1", "noExist", "FunctionalTestFolder", IllegalArgumentException.class);
	}
	//OnlyRead
	@Test
	public void editFolderTest7() {
		this.templateEditFolder("animaniac1", "folder4Animaniac1", "FunctionalTestFolder", IllegalArgumentException.class);
	}

	//Caso de uso de borrar un vet:
	//final String actorBean, final String folderBean, final Class<?> expected
	//test positivo
	@Test
	public void deleteFolderTest1() {
		this.templateDeleteFolder("animaniac1", "folder5Animaniac1", null);
	}
	//sin loguearse
	@Test
	public void deleteFolderTest2() {
		this.templateDeleteFolder(null, "folder5Animaniac1", IllegalArgumentException.class);
	}
	//no es tu folder
	@Test
	public void deleteFolderTest3() {
		this.templateDeleteFolder("animaniac2", "folder5Animaniac1", IllegalArgumentException.class);
	}
	//noExist
	@Test
	public void deleteFolderTest4() {
		this.templateDeleteFolder("animaniac2", "noExist", IllegalArgumentException.class);
	}
	//onlyRead
	@Test
	public void deleteFolderTest5() {
		this.templateDeleteFolder("animaniac2", "folder4Animaniac1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateFolder(final String actorBean, final String folderName, final Class<?> expected) {
		Class<?> caught;
		final Folder folder;

		caught = null;
		try {
			this.authenticate(actorBean);

			folder = this.folderService.create(this.actorService.findActorByPrincipal());
			folder.setName(folderName);
			folder.setReadOnly(false);

			this.folderService.save(folder);
			this.folderService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateEditFolder(final String actorBean, final String folderBean, final String folderName, final Class<?> expected) {
		Class<?> caught;
		final Folder folder, folder2;
		final int folderId;
		caught = null;
		try {
			this.authenticate(actorBean);
			folderId = this.extract(folderBean);
			folder = this.folderService.findOne(folderId);
			folder2 = new Folder();
			folder2.setName(folderName);
			folder2.setId(folder.getId());
			folder2.setActor(folder.getActor());
			folder2.setVersion(folder.getVersion());
			folder2.setReadOnly(folder.getReadOnly());
			this.folderService.save(folder2);
			this.folderService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}
	protected void templateDeleteFolder(final String actorBean, final String folderBean, final Class<?> expected) {
		Class<?> caught;
		final Folder folder;
		final int folderId;
		caught = null;
		try {
			this.authenticate(actorBean);
			folderId = this.extract(folderBean);
			folder = this.folderService.findOne(folderId);

			this.folderService.delete(folder);
			this.folderService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
