package dvdlibrary.dao;

import dvdlibrary.dto.DVD;

import java.io.*;
import java.util.*;


public class DvdLibraryDaoFileImpl implements DvdLibraryDao{

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvds = new HashMap<>();
    @Override
    public DVD addDvd(String title, DVD dvd) throws DvdLibraryDaoException {
        loadLibrary();
        DVD newDvd = dvds.put(title, dvd);
        writeLibrary();
        return newDvd;
    }


    @Override
    public DVD removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        DVD removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList(dvds.values());
    }

    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList){
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    private String marshallDvd(DVD dvd) {
        String dvdAsText = dvd.getTitle()+DELIMITER;
        dvdAsText += dvd.getReleaseDate()+DELIMITER;
        dvdAsText += dvd.getMPAA()+DELIMITER;
        dvdAsText += dvd.getDirectorsName()+DELIMITER;
        dvdAsText += dvd.getStudio()+DELIMITER;
        dvdAsText += dvd.getUserRating()+DELIMITER;

        return dvdAsText;
    }

    @Override
    public DVD getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException {
        loadLibrary();
        DVD currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        writeLibrary();
        return currentDVD;
    }

    @Override
    public DVD editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadLibrary();
        DVD currentDVD = dvds.get(title);
        currentDVD.setMPAA(newMpaaRating);
        writeLibrary();
        return currentDVD;    }

    @Override
    public DVD editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException {
        loadLibrary();
        DVD currentDVD = dvds.get(title);
        currentDVD.setDirectorsName(newDirectorName);
        writeLibrary();
        return currentDVD;    }

    @Override
    public DVD editUserRating(String title, String newUserRating) throws DvdLibraryDaoException {
        loadLibrary();
        DVD currentDVD = dvds.get(title);
        currentDVD.setUserRating(newUserRating);
        writeLibrary();
        return currentDVD;    }

    @Override
    public DVD editStudio(String title, String newStudioName) throws DvdLibraryDaoException {
        loadLibrary();
        DVD currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        writeLibrary();
        return currentDVD;    }

    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }

        String currentLine;
        DVD currentDvd;

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getTitle(),currentDvd);
        }
        scanner.close();
    }

    private DVD unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        DVD dvdFromFile = new DVD(dvdTitle);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAA(dvdTokens[2]);
        dvdFromFile.setDirectorsName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
        return dvdFromFile;
    }
}
